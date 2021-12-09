package com.fox.shop.shoppingcart.base.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

@Component
public class BaseRequestFactoryImpl implements BaseRequestFactory {

    @Value("${base.api.url}")
    private String url;
    @Value("${base.api.endpoint.product-price-by-id}")
    private String productPriceById;
    @Value("${base.api.endpoint.product-by-id}")
    private String productById;
    @Value("${base.api.endpoint.merchant-by-product-id}")
    private String merchantByProductId;

    private final ObjectMapper objectMapper;


    public BaseRequestFactoryImpl() {
        objectMapper = new ObjectMapper();
    }

    @Override
    public HttpRequest productPriceById(final long productId) {
        final String fullUri = buildFullUri(
                url,
                productPriceById + "/" + productId,
                null
        );
        return HttpRequest.newBuilder().
                uri(URI.create(fullUri)).
                GET().
                build();
    }

    @Override
    public HttpRequest productById(final long productId) {
        final String fullUri = buildFullUri(
                url,
                productById + "/" + productId,
                null
        );
        return HttpRequest.newBuilder().
                uri(URI.create(fullUri)).
                GET().
                build();
    }

    @Override
    public HttpRequest merchantByProductId(final long productId) {
        final String fullUri = buildFullUri(
                url,
                merchantByProductId + "/" + productId,
                null
        );
        return HttpRequest.newBuilder().
                uri(URI.create(fullUri)).
                GET().
                build();
    }

    private String buildFullUri(
            final String url,
            final String method,
            final List<Pair<String, String>> keyValue
    ) {
        final StringBuilder result = new StringBuilder(url).append(method).append("?");
        if (keyValue == null) {
            result.deleteCharAt(result.length() - 1);
            return result.toString();
        }
        keyValue.forEach(queryIt ->
                result.append(queryIt.getFirst()).
                        append("=").
                        append(queryIt.getSecond()).
                        append("&")
        );
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

}
