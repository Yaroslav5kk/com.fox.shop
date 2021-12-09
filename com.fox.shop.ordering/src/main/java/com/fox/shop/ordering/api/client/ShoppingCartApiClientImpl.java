package com.fox.shop.ordering.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fox.shop.ordering.api.client.i.FatherApiClient;
import com.fox.shop.ordering.api.client.i.ShoppingCartApiClient;
import com.fox.shop.ordering.api.factory.i.ShoppingCartRequestFactory;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartApiClientImpl implements FatherApiClient, ShoppingCartApiClient {

    private final CloseableHttpClient client;
    private final ObjectMapper objectMapper;
    private final ShoppingCartRequestFactory shoppingCartRequestFactory;

    public ShoppingCartApiClientImpl(
            final ObjectMapper objectMapper,
            final ShoppingCartRequestFactory shoppingCartRequestFactory
    ) {
        this.shoppingCartRequestFactory = shoppingCartRequestFactory;
        this.client = HttpClients.createDefault();
        this.objectMapper = objectMapper;
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
    public CloseableHttpClient getClient() {
        return client;
    }


    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
