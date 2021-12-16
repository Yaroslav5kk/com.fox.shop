package com.fox.shop.storage.api.request.factory.i;


import org.springframework.core.io.FileSystemResource;
import org.springframework.data.util.Pair;
import org.springframework.http.client.MultipartBodyBuilder;

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
      final List<Pair<String, FileSystemResource>> nameAndFilePathValue
  ) {
    final MultipartBodyBuilder builder = new MultipartBodyBuilder();
    nameAndStringValue.forEach(nameValue ->
        builder.part(nameValue.getFirst(), nameValue.getSecond())
    );
    nameAndFilePathValue.forEach(nameValue ->
        {
            builder.part(nameValue.getFirst(), nameValue.getSecond());
        }
    );
    return builder;
  }

}
