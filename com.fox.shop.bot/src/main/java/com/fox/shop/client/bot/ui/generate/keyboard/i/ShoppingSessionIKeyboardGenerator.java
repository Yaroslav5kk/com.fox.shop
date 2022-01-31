package com.fox.shop.client.bot.ui.generate.keyboard.i;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface ShoppingSessionIKeyboardGenerator {
    InlineKeyboardMarkup getCartSession(long cartSessionId);

    InlineKeyboardMarkup editSessionItem(long sessionItemId);

    InlineKeyboardMarkup setQuantity();
}
