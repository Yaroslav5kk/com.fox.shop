package com.fox.shop.shoppingcart.base.service;

import com.fox.shop.protocol.ProductModel;
import com.fox.shop.shoppingcart.base.api.BaseApiClient;
import com.fox.shop.shoppingcart.base.entity.CartItemEntity;
import com.fox.shop.shoppingcart.base.entity.SessionEntity;
import com.fox.shop.shoppingcart.base.mapper.i.CartItemMapper;
import com.fox.shop.shoppingcart.base.mapper.i.SessionMapper;
import com.fox.shop.shoppingcart.base.repository.CartItemRepository;
import com.fox.shop.shoppingcart.base.repository.SessionRepository;
import com.fox.shop.shoppingcart.base.service.i.SessionService;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import com.fox.shop.shoppingcart.protocol.model.request.AddToCartRequest;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.request.CartSessionOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.response.HasActiveSessionResponse;
import com.fox.shop.shoppingcart.protocol.types.SessionOriginType;
import com.fox.shop.shoppingcart.protocol.types.SessionStatusType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final BaseApiClient baseApiClient;

    public SessionServiceImpl(
            final SessionRepository sessionRepository,
            final SessionMapper sessionMapper,
            final CartItemRepository cartItemRepository,
            final CartItemMapper cartItemMapper,
            final BaseApiClient baseApiClient
    ) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
        this.baseApiClient = baseApiClient;
    }

    @Override
    @Transactional
    public void save(final CartSessionOnCreateRequest request) {
        sessionRepository.save(sessionMapper.fromRequestToEntity(request));
    }

    @Override
    @Transactional
    public FullCartSessionModel cleanSession(
            final long userId,
            final SessionOriginType originType
    ) {
        final SessionEntity toDelete = sessionRepository.findByUserIdAndOriginType(userId, originType);
        sessionRepository.deleteById(toDelete.getId());
        return sessionMapper.entityToModel(toDelete);
    }

    @Override
    public FullCartSessionModel get(
            final Long id,
            final Long userId,
            final SessionOriginType originType
    ) {
        if (id != null) {
            return sessionMapper.entityToModel(sessionRepository.getById(id));
        }
        if (!sessionRepository.existsByUserIdAndOriginType(userId, originType))
            return new FullCartSessionModel();
        return sessionMapper.entityToModel(sessionRepository.findByUserIdAndOriginType(userId, originType));
    }

    @Override
    public FullCartSessionModel addToCart(final AddToCartRequest request) {
        final CartItemOnCreateRequest cartItemOnCreateRequest = request.getCartItem();
        final long sessionId = request.getSessionId();
        final long userId = request.getUserId();
        final SessionOriginType originType = request.getOriginType();
        SessionEntity result = new SessionEntity();
        if (sessionId != 0 && sessionRepository.existsById(sessionId)) {
            result = sessionRepository.getById(sessionId);
        } else if (sessionRepository.existsByUserIdAndOriginType(userId, originType)) {
            result = sessionRepository.findByUserIdAndOriginType(userId, originType);
        }
        result.setUserId(userId);
        result.setOriginType(originType);
        result.setStatus(SessionStatusType.ACTIVE);
        if (result.getItemAsProductIdCartItem().containsKey(cartItemOnCreateRequest.getProductId())) {
            result.getItemAsProductIdCartItem().get(cartItemOnCreateRequest.getProductId()).plusQuantity(cartItemOnCreateRequest.getQuantity());
        } else {
            final CartItemEntity newItemToSave = cartItemRepository.save(cartItemMapper.fromRequestToEntity(request.getCartItem()));
            final ProductModel product = baseApiClient.productById(cartItemOnCreateRequest.getProductId());
            newItemToSave.setProductName(product.getName());
            newItemToSave.setProductMainImageId(product.getMainImage().getId());
            result.getItems().add(newItemToSave);
        }

        return sessionMapper.entityToModel(sessionRepository.save(result));
    }

    @Override
    public FullCartSessionModel updateItemQuantity(
            final long itemId,
            final short newQuantity
    ) {
        final CartItemEntity item = cartItemRepository.getById(itemId);
        final SessionEntity session = sessionRepository.findByItemsContaining(item);
        session.getItems().remove(item);
        if (newQuantity != 0) {
            item.setQuantity(newQuantity);
            session.getItems().add(item);
        }
        if (CollectionUtils.isEmpty(session.getItems())) {
            sessionRepository.deleteById(session.getId());
            return new FullCartSessionModel();
        }
        return sessionMapper.entityToModel(sessionRepository.save(session));
    }

    @Override
    public HasActiveSessionResponse hasActiveSession(
            final long userId,
            final SessionOriginType originType
    ) {
        if (sessionRepository.findByUserIdAndOriginType(userId, originType) == null)
            return new HasActiveSessionResponse(false);
        return new HasActiveSessionResponse(sessionRepository.findByUserIdAndOriginType(userId, originType).getId(), true);

    }
}