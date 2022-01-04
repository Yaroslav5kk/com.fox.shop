package com.fox.shop.client.bot.ui.scenarios.i;

import org.telegram.telegrambots.meta.api.objects.User;

public interface ProductScenarios extends Scenarios{
    void allProductByGroup(
            long chatId,
            int userId,
            long groupId
    );

  void viewProductDescription(
      long chatId,
      long messageId,
      long productId
  );
}
