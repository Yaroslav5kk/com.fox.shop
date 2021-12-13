package com.fox.shop.client.bot.service.i;

import com.fox.shop.client.bot.model.types.UserDomainState;

public interface UserHistoryService {

    void snapshot(int userId, String command);

    String handle(
            int userId,
            String command
    );

    void removeOldMessages(long chatId, UserDomainState domainState);
}
