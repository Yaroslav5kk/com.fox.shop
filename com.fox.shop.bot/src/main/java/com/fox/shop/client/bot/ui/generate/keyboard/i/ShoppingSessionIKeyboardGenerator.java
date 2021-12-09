package com.fox.shop.client.bot.ui.generate.keyboard.i;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface ShoppingSessionIKeyboardGenerator {
    InlineKeyboardMarkup getCartSession(long cartSessionId);

    InlineKeyboardMarkup startBack();

    InlineKeyboardMarkup editSessionItem(long sessionItemId);

    InlineKeyboardMarkup setQuantity();

    InlineKeyboardMarkup beginBack();

    InlineKeyboardMarkup successAddToCart(long cartSessionId);
}
