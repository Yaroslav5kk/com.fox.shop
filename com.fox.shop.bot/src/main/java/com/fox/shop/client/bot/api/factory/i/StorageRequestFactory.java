package com.fox.shop.client.bot.api.factory.i;

import org.apache.http.client.methods.HttpUriRequest;

public interface StorageRequestFactory {
  /*---------------------------------------------- telegramId -------------------------------------------------*/
  HttpUriRequest getTelegramIdById(
          String id
  );
}
