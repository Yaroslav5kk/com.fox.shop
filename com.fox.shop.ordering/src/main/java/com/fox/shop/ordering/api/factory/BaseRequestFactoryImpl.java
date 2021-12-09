package com.fox.shop.ordering.api.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.ordering.api.factory.i.BaseRequestFactory;
import com.fox.shop.ordering.api.factory.i.FatherRequestFactory;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BaseRequestFactoryImpl implements FatherRequestFactory, BaseRequestFactory {

    @Value("${base.api.url}")
    private String url;
    @Value("${base.endpoint.get-user-by-id}")
    private String getUserById;


    private final ObjectMapper objectMapper;


    public BaseRequestFactoryImpl() {
        objectMapper = new ObjectMapper();
    }

    /*----------------------------------------------users-------------------------------------------------*/
    @Override
    public HttpUriRequest getUserById(final long id) {
        final String fullUri = buildFullUri(
                url,
                getUserById + "/" + id,
                null
        );
        return new HttpGet(fullUri);
    }

}



















