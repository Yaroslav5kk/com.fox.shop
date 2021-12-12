package com.fox.shop.ordering.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import com.fox.shop.notify.protocol.response.NotifyResponse;
import com.fox.shop.ordering.api.client.i.FatherApiClient;
import com.fox.shop.ordering.api.client.i.NotifyApiClient;
import com.fox.shop.ordering.api.factory.i.NotifyRequestFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotifyApiClientImpl implements FatherApiClient, NotifyApiClient {

    private final CloseableHttpClient client;
    private final ObjectMapper objectMapper;
    private final NotifyRequestFactory notifyRequestFactory;

    public NotifyApiClientImpl(
            final ObjectMapper objectMapper,
            final NotifyRequestFactory notifyRequestFactory
    ) {
        this.notifyRequestFactory = notifyRequestFactory;
        this.client = HttpClients.createDefault();
        this.objectMapper = objectMapper;
    }

    /*--------------------------------------------- order ----------------------------------------------------*/
    @Override
    public NotifyResponse notifyOrder(final OrderNotifyRequest request) {
        final Optional<NotifyResponse> response = executeRequestAndExtractResponse(
                notifyRequestFactory.notifyOrder(request),
                SimpleType.constructUnsafe(NotifyResponse.class)
        );
        return response.isPresent()
                ? response.get()
                : new NotifyResponse();
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
