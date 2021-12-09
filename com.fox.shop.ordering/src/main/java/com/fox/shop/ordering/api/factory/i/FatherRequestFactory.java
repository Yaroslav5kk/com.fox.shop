package com.fox.shop.ordering.api.factory.i;

import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.data.util.Pair;

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
}
