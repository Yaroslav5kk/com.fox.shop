package com.fox.shop.client.bot.api.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.client.bot.api.factory.i.FatherRequestFactory;
import com.fox.shop.client.bot.api.factory.i.OrderingRequestFactory;
import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class OrderingRequestFactoryImpl implements FatherRequestFactory, OrderingRequestFactory {
    @Value("${ordering.api.url}")
    private String url;
    @Value("${ordering.api.endpoint.init-order}")
    private String initOrder;

    private final ObjectMapper objectMapper;


    public OrderingRequestFactoryImpl(
    ) {
        objectMapper = new ObjectMapper();
    }

    @Override
    public HttpUriRequest initOrder(
            final OrderOnCreateRequest request
    ) {
        final String fullUri = buildFullUri(
                url,
                initOrder,
                null
        );
        final HttpPost result = new HttpPost(fullUri);
        result.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
        try {
            result.setEntity(new StringEntity(objectMapper.writeValueAsString(request)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}












