package com.fox.shop.ordering.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.ordering.api.client.i.FatherApiClient;
import com.fox.shop.ordering.api.client.i.PriceApiClient;
import com.fox.shop.ordering.api.factory.i.PriceRequestFactory;
import com.fox.shop.price.protocol.model.full.ProductPriceModel;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
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

    /*--------------------------------------------- product ----------------------------------------------------*/
    @Override
    public List<ProductPriceModel> allProductPriceByIds(final List<Long> ids) {
        final Optional<List<ProductPriceModel>> response = executeRequestAndExtractResponse(
                priceRequestFactory.allProductPriceByIds(ids),
                objectMapper.getTypeFactory().constructCollectionType(List.class, ProductPriceModel.class)
        );
        return response.isPresent()
                ? response.get()
                : Collections.emptyList();
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
