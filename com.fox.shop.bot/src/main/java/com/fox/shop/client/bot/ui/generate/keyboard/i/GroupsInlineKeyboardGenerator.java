package com.fox.shop.client.bot.ui.generate.keyboard.i;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface GroupsInlineKeyboardGenerator {

    InlineKeyboardMarkup productGroup(
            int userId,
            long groupId
    );
}
