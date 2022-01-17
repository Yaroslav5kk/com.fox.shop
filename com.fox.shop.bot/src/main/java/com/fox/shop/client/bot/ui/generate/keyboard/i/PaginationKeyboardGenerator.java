package com.fox.shop.client.bot.ui.generate.keyboard.i;

import com.fox.shop.client.bot.model.types.CommandData;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface PaginationKeyboardGenerator {
    InlineKeyboardMarkup pagination(String commandData);
}
