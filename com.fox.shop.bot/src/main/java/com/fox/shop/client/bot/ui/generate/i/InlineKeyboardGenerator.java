package com.fox.shop.client.bot.ui.generate.i;

import org.javatuples.Triplet;
import org.springframework.data.util.Pair;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

public interface InlineKeyboardGenerator {
    InlineKeyboardMarkup generate(
            List<Pair<?, ?>> textData,
            int numberOfColumns);

    InlineKeyboardMarkup generateHorizontal(List<Pair<?, ?>> textData);

    InlineKeyboardMarkup generateTextUrl(
            List<Pair<?, ?>> textUrl,
            int numberOfColumns);

    InlineKeyboardMarkup generateTextDataUrl(
            List<Triplet<?, ?, ?>> textDataUrl,
            int numberOfColumns);
}
