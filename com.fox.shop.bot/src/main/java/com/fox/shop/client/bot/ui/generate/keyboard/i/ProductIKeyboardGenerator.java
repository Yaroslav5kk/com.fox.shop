package com.fox.shop.client.bot.ui.generate.keyboard.i;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Optional;

public interface ProductIKeyboardGenerator {
    InlineKeyboardMarkup beginBack();

    InlineKeyboardMarkup afterAllProduct(long userId, Optional<Long> cartSessionId);

    InlineKeyboardMarkup product(long productId);

    InlineKeyboardMarkup onlyAddToCart(long productId);
}
