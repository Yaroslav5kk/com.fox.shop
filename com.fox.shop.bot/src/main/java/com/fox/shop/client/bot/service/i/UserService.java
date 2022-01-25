package com.fox.shop.client.bot.service.i;

import com.fox.shop.client.bot.model.wrapper.UserWrapper;
import org.telegram.telegrambots.meta.api.objects.User;

public interface UserService {

  void createCustomer(
      long userId,
      String userName
  );

  long getBaseUserIdByTelegramId(long telegramId);

    boolean isActivatedUser(long telegramUserId);

    UserWrapper getByUsername(String username);

}
