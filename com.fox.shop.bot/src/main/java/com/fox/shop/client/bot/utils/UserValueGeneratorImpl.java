package com.fox.shop.client.bot.utils;

import com.google.common.base.Strings;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public class UserValueGeneratorImpl implements UserValueGenerator {

    @Override
    public String username(
            final Integer userId,
            final String userName
    ) {
        return Strings.nullToEmpty(userName) + userId;
    }

    @Override
    public String password(final User userModelWrapper) {
        return new StringBuilder().
                append(userModelWrapper.getFirstName()).
                append(userModelWrapper.getLastName()).
                toString();
    }
}
