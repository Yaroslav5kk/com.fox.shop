package com.fox.menu.bot.merchant.ui.generator.i;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface PlaceIKeyboardGenerator {
    InlineKeyboardMarkup checkOfAvailableItem(long placeId);

    InlineKeyboardMarkup checkOfAvailableTitleEnd();

    InlineKeyboardMarkup beginBeck();
}
