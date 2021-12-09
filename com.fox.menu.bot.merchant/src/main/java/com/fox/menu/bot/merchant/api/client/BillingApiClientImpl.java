package com.fox.menu.bot.merchant.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fox.menu.billing.protocol.response.GeneralResponse;
import com.fox.menu.bot.merchant.api.client.i.BillingApiClient;
import com.fox.menu.bot.merchant.api.client.i.FatherApiClient;
import com.fox.menu.bot.merchant.api.factory.i.BillingRequestFactory;
import com.fox.menu.bot.merchant.service.i.UploadImageToTelegramService;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillingApiClientImpl implements FatherApiClient, BillingApiClient {

    private final BillingRequestFactory billingRequestFactory;
    private final UploadImageToTelegramService uploadImageToTelegramService;
    private final CloseableHttpClient client;
    private final ObjectMapper objectMapper;

    public BillingApiClientImpl(
            final BillingRequestFactory billingRequestFactory,
            final ObjectMapper objectMapper,
            final UploadImageToTelegramService uploadImageToTelegramService
    ) {
        this.billingRequestFactory = billingRequestFactory;
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
                billingRequestFactory.confirmPlace(customerId, placeId),
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
