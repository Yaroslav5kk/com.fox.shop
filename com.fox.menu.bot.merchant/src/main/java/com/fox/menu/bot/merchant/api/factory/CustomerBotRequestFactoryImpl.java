package com.fox.menu.bot.merchant.api.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.menu.bot.merchant.api.factory.i.CustomerBotRequestFactory;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerBotRequestFactoryImpl implements CustomerBotRequestFactory {

    @Value("${customer-bot.api.url}")
    private String url;
    @Value("${customer-bot.api.endpoint.confirm-place}")
    private String confirmPlace;

    private final ObjectMapper objectMapper;


    public CustomerBotRequestFactoryImpl() {
        objectMapper = new ObjectMapper();
    }


    /*----------------------------------------------places-------------------------------------------------*/
    @Override
    public HttpUriRequest confirmPlace(
            final long userId,
            final long placeId
    ) {
        final String fullUri = buildFullUri(
                url,
                confirmPlace,
                Arrays.asList(
                        Pair.of("userId", String.valueOf(userId)),
                        Pair.of("placeId", String.valueOf(placeId))
                )
        );
        return new HttpPut(fullUri);
    }

}
