package com.fox.shop.client.bot.api.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.client.bot.api.factory.i.FatherRequestFactory;
import com.fox.shop.client.bot.api.factory.i.PriceRequestFactory;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PriceRequestFactoryImpl implements FatherRequestFactory, PriceRequestFactory {

    @Value("${price.api.url}")
    private String url;
    @Value("${price.api.endpoint.price-by-product-id}")
    private String priceByProductId;

    private final ObjectMapper objectMapper;


    public PriceRequestFactoryImpl() {
        objectMapper = new ObjectMapper();
    }

    /*----------------------------------------------price-------------------------------------------------*/
    @Override
    public HttpUriRequest getByProductId(
            final long productId,
            final int quantity
    ) {
        final String fullUri = buildFullUri(
                url,
                priceByProductId + "/" + productId,
                Arrays.asList(
                        Pair.of("quantity", String.valueOf(quantity))
                )
        );
        final HttpGet result = new HttpGet(fullUri);
        result.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        return result;
    }
}
