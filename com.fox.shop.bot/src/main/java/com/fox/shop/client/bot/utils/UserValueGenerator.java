package com.fox.shop.client.bot.utils;

import org.telegram.telegrambots.meta.api.objects.User;

public interface UserValueGenerator {
    String username(
            Integer userId,
            String userName
    );

    String password(User userModelWrapper);
}
