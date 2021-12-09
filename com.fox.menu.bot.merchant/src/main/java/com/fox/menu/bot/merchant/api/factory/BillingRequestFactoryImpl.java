package com.fox.menu.bot.merchant.api.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.menu.bot.merchant.api.factory.i.BillingRequestFactory;
import com.fox.menu.bot.merchant.api.factory.i.FatherRequestFactory;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BillingRequestFactoryImpl implements BillingRequestFactory {

    @Value("${billing.api.url}")
    private String url;
    @Value("${billing.api.endpoint.confirm-place}")
    private String confirmPlace;

    private final ObjectMapper objectMapper;


    public BillingRequestFactoryImpl() {
        objectMapper = new ObjectMapper();
    }


    /*----------------------------------------------places-------------------------------------------------*/
    @Override
    public HttpUriRequest confirmPlace(
            final long waiterId,
            final long placeId
    ) {
        final String fullUri = buildFullUri(
                url,
                confirmPlace,
                Arrays.asList(
                        Pair.of("userId", String.valueOf(waiterId)),
                        Pair.of("placeId", String.valueOf(placeId))
                )
        );
        return new HttpPut(fullUri);
    }

}
