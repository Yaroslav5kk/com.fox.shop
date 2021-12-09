package com.fox.menu.bot.merchant.api.client.i;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.Optional;

public interface FatherApiClient {

    CloseableHttpClient getClient();

    ObjectMapper getObjectMapper();

    default <T> Optional<T> executeRequestAndExtractResponse(
            final HttpUriRequest request,
            final JavaType typeResponse
    ) {
        final Optional<CloseableHttpResponse> response = clientSend(
                request
        );
        try {
            return response.isPresent() ?
                    Optional.of(getObjectMapper().readValue(response.get().getEntity().getContent(), typeResponse)) :
                    Optional.empty();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.get().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    default Optional<CloseableHttpResponse> clientSend(
            final HttpUriRequest request
    ) {
        Optional<CloseableHttpResponse> result = Optional.empty();
        try {
            result = Optional.of(getClient().execute(request));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
