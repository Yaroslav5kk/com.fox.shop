package com.fox.shop.shoppingcart.base.mapper;

import com.fox.shop.shoppingcart.base.entity.SessionEntity;
import com.fox.shop.shoppingcart.base.mapper.i.CartItemMapper;
import com.fox.shop.shoppingcart.base.mapper.i.SessionMapper;
import com.fox.shop.shoppingcart.base.repository.CartItemRepository;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import com.fox.shop.shoppingcart.protocol.model.request.CartSessionOnCreateRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

@Component
public class SessionMapperImpl implements SessionMapper {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    public SessionMapperImpl(
            final CartItemRepository cartItemRepository,
            final CartItemMapper cartItemMapper
    ) {
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public SessionEntity fromRequestToEntity(final CartSessionOnCreateRequest request) {
        final SessionEntity result = fromRequestToEntity(request.getUserId(), request.getItemId());
        request.setOriginType(request.getOriginType());
        return result;
    }

    @Override
    public FullCartSessionModel entityToModel(final SessionEntity entity) {
        final FullCartSessionModel result = new FullCartSessionModel();
        result.setUserId(entity.getUserId());
        result.setOriginType(entity.getOriginType());
        result.setStatus(entity.getStatus());
        result.setId(entity.getId());
        result.setUpdatedAt(entity.getUpdatedAt());
        result.setCreatedAt(entity.getCreatedAt());
        if (!CollectionUtils.isEmpty(entity.getItems()))
            result.setItems(entity.getItems().stream().map(cartItemMapper::entityToModel).collect(Collectors.toList()));
        return result;
    }

    @Override
    public SessionEntity fromRequestToEntity(final long userId, final long itemId) {
        final SessionEntity result = new SessionEntity();
        result.setUserId(userId);
        return result;
    }

}
