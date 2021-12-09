package com.fox.shop.client.bot.ui.generate.keyboard.i;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface CategoryIKeyboardGenerator {
    InlineKeyboardMarkup afterAllCategory(long userId);

    InlineKeyboardMarkup category(long categoryId);
}
