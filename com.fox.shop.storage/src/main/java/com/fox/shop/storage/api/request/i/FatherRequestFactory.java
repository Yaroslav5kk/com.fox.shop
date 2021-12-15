package com.fox.shop.storage.api.request.i;


import org.springframework.core.io.PathResource;
import org.springframework.data.util.Pair;
import org.springframework.http.client.MultipartBodyBuilder;

import java.io.IOException;
import java.util.List;

public interface FatherRequestFactory {
  default String buildFullUri(
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

  default MultipartBodyBuilder buildMultipartData(
      final List<Pair<String, String>> nameAndStringValue,
      final List<Pair<String, String>> nameAndFilePathValue
  ) {
    final MultipartBodyBuilder builder = new MultipartBodyBuilder();
    nameAndStringValue.forEach(nameValue ->
        builder.part(nameValue.getFirst(), nameValue.getSecond())
    );
    nameAndFilePathValue.forEach(nameValue ->
        {
          try {
            builder.part(nameValue.getFirst(), new PathResource(nameValue.getSecond()).getInputStream().readAllBytes());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
    );
    return builder;
  }

}
