package com.fox.shop.client.bot.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fox.shop.client.bot.api.client.i.FatherApiClient;
import com.fox.shop.client.bot.api.client.i.PriceApiClient;
import com.fox.shop.client.bot.api.factory.i.PriceRequestFactory;
import com.fox.shop.price.protocol.model.full.ProductPriceModel;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceApiClientImpl implements FatherApiClient, PriceApiClient {

    private final CloseableHttpClient client;
    private final ObjectMapper objectMapper;
    private final PriceRequestFactory priceRequestFactory;

    public PriceApiClientImpl(
            final ObjectMapper objectMapper,
            final PriceRequestFactory priceRequestFactory
            ) {
        this.priceRequestFactory = priceRequestFactory;
        this.client = HttpClients.createDefault();
        this.objectMapper = objectMapper;
    }

    /*--------------------------------------------- price ----------------------------------------------------*/

    @Override
    public ProductPriceModel getByProductId(
            final long productId,
            final int quantity
            ) {
        final Optional<ProductPriceModel> response = executeRequestAndExtractResponse(
                priceRequestFactory.getByProductId(productId,quantity),
                SimpleType.constructUnsafe(ProductPriceModel.class)
        );
        return response.isPresent()
                ? response.get()
                : new ProductPriceModel();
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
