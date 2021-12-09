package com.fox.menu.bot.merchant.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fox.menu.billing.protocol.response.GeneralResponse;
import com.fox.menu.bot.merchant.api.client.i.CustomerBotApiClient;
import com.fox.menu.bot.merchant.api.factory.i.CustomerBotRequestFactory;
import com.fox.menu.bot.merchant.service.i.UploadImageToTelegramService;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerBotApiClientImpl implements CustomerBotApiClient {

    private final CustomerBotRequestFactory customerBotRequestFactory;
    private final UploadImageToTelegramService uploadImageToTelegramService;
    private final CloseableHttpClient client;
    private final ObjectMapper objectMapper;

    public CustomerBotApiClientImpl(
            final CustomerBotRequestFactory customerBotRequestFactory,
            final ObjectMapper objectMapper,
            final UploadImageToTelegramService uploadImageToTelegramService
    ) {
        this.customerBotRequestFactory = customerBotRequestFactory;
        this.uploadImageToTelegramService = uploadImageToTelegramService;
        this.client = HttpClients.createDefault();
        this.objectMapper = objectMapper;
    }

    /*--------------------------------------------- place ----------------------------------------------------*/
    @Override
    public GeneralResponse confirmPlace(
            final long customerId,
            final long placeId
    ) {
        final Optional<GeneralResponse> response = executeRequestAndExtractResponse(
                customerBotRequestFactory.confirmPlace(customerId, placeId),
                SimpleType.constructUnsafe(GeneralResponse.class)
        );
        return response.isPresent()
                ? response.get()
                : new GeneralResponse();
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
