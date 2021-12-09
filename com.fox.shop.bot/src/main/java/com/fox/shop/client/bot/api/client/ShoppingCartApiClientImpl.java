package com.fox.shop.client.bot.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fox.shop.client.bot.api.client.i.FatherApiClient;
import com.fox.shop.client.bot.api.client.i.ShoppingCartApiClient;
import com.fox.shop.client.bot.api.factory.i.ShoppingCartRequestFactory;
import com.fox.shop.client.bot.repository.UserInfoRepository;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import com.fox.shop.shoppingcart.protocol.model.request.AddToCartRequest;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.response.HasActiveSessionResponse;
import com.fox.shop.shoppingcart.protocol.model.response.SimpleResponse;
import com.fox.shop.shoppingcart.protocol.types.SessionOriginType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShoppingCartApiClientImpl implements ShoppingCartApiClient, FatherApiClient {

    private final ShoppingCartRequestFactory shoppingCartRequestFactory;
    private final UserInfoRepository userInfoRepository;
    private final ObjectMapper objectMapper;
    private final CloseableHttpClient client;

    public ShoppingCartApiClientImpl(
            final ShoppingCartRequestFactory shoppingCartRequestFactory,
            final UserInfoRepository userInfoRepository
    ) {
        this.shoppingCartRequestFactory = shoppingCartRequestFactory;
        this.userInfoRepository = userInfoRepository;
        objectMapper = new ObjectMapper();
        client = HttpClients.createDefault();
    }

    @Override
    public FullCartSessionModel addToCart(final AddToCartRequest request) {
        request.setUserId(userInfoRepository.findByTelegramUserId(request.getUserId()).getBaseUserId());
        final Optional<FullCartSessionModel> response = executeRequestAndExtractResponse(
                shoppingCartRequestFactory.addToCart(request),
                SimpleType.constructUnsafe(FullCartSessionModel.class)
        );
        return response.isPresent()
                ? response.get()
                : new FullCartSessionModel();
    }

    @Override
    public SimpleResponse createCartItem(final CartItemOnCreateRequest request) {
        final Optional<SimpleResponse> response = executeRequestAndExtractResponse(
                shoppingCartRequestFactory.createCartItem(request),
                SimpleType.constructUnsafe(SimpleResponse.class)
        );
        return response.isPresent()
                ? response.get()
                : SimpleResponse.failed();
    }

    @Override
    public FullCartSessionModel getCartSessionById(final long sessionId) {
        final Optional<FullCartSessionModel> response = executeRequestAndExtractResponse(
                shoppingCartRequestFactory.getCartSessionById(sessionId),
                SimpleType.constructUnsafe(FullCartSessionModel.class)
        );
        return response.isPresent()
                ? response.get()
                : new FullCartSessionModel();
    }

    @Override
    public FullCartSessionModel getCartSessionByUser(final long userId) {
        final long baseUserId = userInfoRepository.findByTelegramUserId(userId).getBaseUserId();
        final Optional<FullCartSessionModel> response = executeRequestAndExtractResponse(
                shoppingCartRequestFactory.getCartSessionByUser(baseUserId),
                SimpleType.constructUnsafe(FullCartSessionModel.class)
        );
        return response.isPresent()
                ? response.get()
                : new FullCartSessionModel();
    }

    @Override
    public FullCartSessionModel clearCartSessionByUser(final long userId) {
        final long baseUserId = userInfoRepository.findByTelegramUserId(userId).getBaseUserId();
        final Optional<FullCartSessionModel> response = executeRequestAndExtractResponse(
                shoppingCartRequestFactory.clearCartSessionByUser(baseUserId),
                SimpleType.constructUnsafe(FullCartSessionModel.class)
        );
        return response.isPresent()
                ? response.get()
                : new FullCartSessionModel();
    }

    @Override
    public FullCartSessionModel updateCartItemQuantity(
            final long cartItemId,
            final short newQuantity
    ) {
        final Optional<FullCartSessionModel> response = executeRequestAndExtractResponse(
                shoppingCartRequestFactory.updateCartItemQuantity(cartItemId, newQuantity),
                SimpleType.constructUnsafe(FullCartSessionModel.class)
        );
        return response.isPresent()
                ? response.get()
                : new FullCartSessionModel();
    }

    @Override
    public HasActiveSessionResponse hasActiveSession(
            final long userId
    ) {
        final long baseUserId = userInfoRepository.findByTelegramUserId(userId).getBaseUserId();
        final Optional<HasActiveSessionResponse> response = executeRequestAndExtractResponse(
                shoppingCartRequestFactory.hasActiveSession(baseUserId, SessionOriginType.TELEGRAM),
                SimpleType.constructUnsafe(HasActiveSessionResponse.class)
        );
        return response.isPresent()
                ? response.get()
                : new HasActiveSessionResponse(false);
    }

    @Override
    public CloseableHttpClient getClient() {
        return client;
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
