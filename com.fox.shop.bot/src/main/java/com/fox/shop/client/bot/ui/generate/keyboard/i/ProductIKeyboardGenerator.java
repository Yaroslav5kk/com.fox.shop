package com.fox.shop.client.bot.ui.generate.keyboard.i;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface ProductIKeyboardGenerator {
    InlineKeyboardMarkup beginBack();

    InlineKeyboardMarkup afterAllProduct(long userId);

    InlineKeyboardMarkup product(long productId);
}
