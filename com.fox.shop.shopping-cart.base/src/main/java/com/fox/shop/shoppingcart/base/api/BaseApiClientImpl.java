package com.fox.shop.shoppingcart.base.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.protocol.ProductModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;


@Component
public class BaseApiClientImpl implements BaseApiClient {

    private final BaseRequestFactory baseRequestFactory;
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public BaseApiClientImpl(
            final BaseRequestFactory baseRequestFactory,
            final ObjectMapper objectMapper
    ) {
        this.baseRequestFactory = baseRequestFactory;
        this.client = HttpClient.newHttpClient();
        this.objectMapper = objectMapper;
    }

    /*@Override
    public ProductPriceModel productPriceById(final long productId) {
        final Optional<HttpResponse<String>> response = clientSend(
                baseRequestFactory.productPriceById(productId),
                HttpResponse.BodyHandlers.ofString()
        );
        try {
            return response.isPresent()
                    ? objectMapper.readValue(response.get().body(), ProductPriceModel.class)
                    : new ProductPriceModel();
        } catch (
                JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ProductPriceModel();
    }*/

    @Override
    public ProductModel productById(final long productId) {
        final Optional<HttpResponse<String>> response = clientSend(
                baseRequestFactory.productById(productId),
                HttpResponse.BodyHandlers.ofString()
        );
        try {
            return response.isPresent()
                    ? objectMapper.readValue(response.get().body(), ProductModel.class)
                    : new ProductModel();
        } catch (
                JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ProductModel();
    }

    private <T> Optional<HttpResponse<T>> clientSend(
            final HttpRequest request,
            final HttpResponse.BodyHandler bodyHandler
    ) {
        try {
            return Optional.of(client.send(request, bodyHandler));
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
