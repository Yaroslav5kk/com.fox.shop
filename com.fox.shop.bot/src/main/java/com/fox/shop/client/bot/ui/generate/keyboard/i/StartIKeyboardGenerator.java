package com.fox.shop.client.bot.ui.generate.keyboard.i;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Optional;

public interface StartIKeyboardGenerator {
    InlineKeyboardMarkup base(Optional<Long> cartSessionId);
}
