package com.fox.shop.client.bot.ui.generate.keyboard.i;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface OrderIKeyboardGenerator {

    InlineKeyboardMarkup initOrder();

    InlineKeyboardMarkup beginBack();

    InlineKeyboardMarkup setOrderContactInfoTitle();

    InlineKeyboardMarkup orderOnTime();

    InlineKeyboardMarkup orderOnName(String nameFromProfile);
}
