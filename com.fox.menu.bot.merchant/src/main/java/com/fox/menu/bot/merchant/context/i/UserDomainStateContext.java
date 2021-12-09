package com.fox.menu.bot.merchant.context.i;

import com.fox.menu.bot.merchant.model.type.UserDomainState;

public interface UserDomainStateContext {
    void start(Integer userId);

    void finish(Integer userId);

    void confirmAvailablePlace(Integer userId);

    void emptyAvailablePlace(Integer userId);

    UserDomainState current(Integer userId);

    void put(Integer userId, UserDomainState domainState);
}
