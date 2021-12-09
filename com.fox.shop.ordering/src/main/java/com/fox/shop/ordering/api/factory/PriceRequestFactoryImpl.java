package com.fox.shop.ordering.api.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.ordering.api.factory.i.FatherRequestFactory;
import com.fox.shop.ordering.api.factory.i.PriceRequestFactory;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceRequestFactoryImpl implements FatherRequestFactory, PriceRequestFactory {

    @Value("${price.api.url}")
    private String url;
    @Value("${price.endpoint.all-product-price-by-ids}")
    private String allProductPriceById;


    private final ObjectMapper objectMapper;


    public PriceRequestFactoryImpl() {
        objectMapper = new ObjectMapper();
    }


    /*----------------------------------------------products-------------------------------------------------*/
    @Override
    public HttpUriRequest allProductPriceByIds(final List<Long> ids) {
        final String fullUri = buildFullUri(
                url,
                allProductPriceById,
                Arrays.asList(Pair.of(
                        "productIds",
                        ids.stream().map(String::valueOf).collect(Collectors.joining(","))
                ))
        );
        return new HttpGet(fullUri);
    }

}



















