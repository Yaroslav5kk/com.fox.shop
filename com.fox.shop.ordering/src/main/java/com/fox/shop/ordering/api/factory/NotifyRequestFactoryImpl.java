package com.fox.shop.ordering.api.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import com.fox.shop.ordering.api.factory.i.FatherRequestFactory;
import com.fox.shop.ordering.api.factory.i.NotifyRequestFactory;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class NotifyRequestFactoryImpl implements FatherRequestFactory, NotifyRequestFactory {

    @Value("${notify.telegram.api.url}")
    private String url;
    @Value("${notify.telegram.api.endpoint.notify-order}")
    private String notifyOrder;


    private final ObjectMapper objectMapper;


    public NotifyRequestFactoryImpl() {
        objectMapper = new ObjectMapper();
    }


    /*----------------------------------------------order-------------------------------------------------*/
    @Override
    public HttpUriRequest notifyOrder(final OrderNotifyRequest request) {
        final String fullUri = buildFullUri(
                url,
                notifyOrder,
                null
        );
        final HttpPost result = new HttpPost(fullUri);
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
