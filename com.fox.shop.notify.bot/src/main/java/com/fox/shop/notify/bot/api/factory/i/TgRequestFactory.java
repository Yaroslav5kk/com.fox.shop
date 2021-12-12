package com.fox.shop.notify.bot.api.factory.i;

import com.fox.shop.notify.bot.model.tg.request.SendPhotoFileIdRequest;
import org.springframework.data.util.Pair;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public interface TgRequestFactory extends FatherRequestFactory {
  WebClient.RequestHeadersSpec<?> sendMessage(
      WebClient webClient,
      SendMessage request
  );

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

    WebClient.RequestHeadersSpec<?> sendPhoto(
            WebClient webClient,
            SendPhotoFileIdRequest request
    );
}
