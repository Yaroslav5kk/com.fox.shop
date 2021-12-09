package com.fox.shop.client.bot.service.i;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface AnswerCallBackQuerySelector {
    void select(
            Update update
    );
}
