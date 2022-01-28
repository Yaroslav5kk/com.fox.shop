package com.fox.shop.client.bot.context.i;

import com.fox.protocol.user.UserModel;
import com.fox.shop.client.bot.model.TgUserSessionModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;

import java.util.Optional;

public interface TgUserSessionContext {

  void setupCommand(
      long userId,
      CommandData command
  );

  CommandData getCommand(long userId);

  TgUserSessionModel getSession(
      long userId
  );

  void clearAll(long userId);
}
