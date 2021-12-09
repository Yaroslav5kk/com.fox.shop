package com.fox.shop.client.bot.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fox.shop.client.bot.api.client.i.FatherApiClient;
import com.fox.shop.client.bot.api.client.i.OrderingApiClient;
import com.fox.shop.client.bot.api.factory.i.OrderingRequestFactory;
import com.fox.shop.ordering.protocol.model.OrderModel;
import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderingApiClientImpl implements FatherApiClient, OrderingApiClient {
    private final OrderingRequestFactory orderingRequestFactory;
    private final ObjectMapper objectMapper;
    private final CloseableHttpClient client;

    public OrderingApiClientImpl(
            final OrderingRequestFactory orderingRequestFactory
    ) {
        this.orderingRequestFactory = orderingRequestFactory;
        objectMapper = new ObjectMapper();
        client = HttpClients.createDefault();
    }

    @Override
    public OrderModel initOrder(
            final OrderOnCreateRequest request
    ) {
        final Optional<OrderModel> response = executeRequestAndExtractResponse(
                orderingRequestFactory.initOrder(request),
                SimpleType.constructUnsafe(OrderModel.class)
        );
        return response.isPresent()
                ? response.get()
                : new OrderModel();
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
