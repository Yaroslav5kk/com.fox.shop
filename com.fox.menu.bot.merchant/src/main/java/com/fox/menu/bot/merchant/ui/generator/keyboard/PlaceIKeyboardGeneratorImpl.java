package com.fox.menu.bot.merchant.ui.generator.keyboard;

import com.fox.menu.bot.merchant.model.type.CommandData;
import com.fox.menu.bot.merchant.ui.generator.i.InlineKeyboardGenerator;
import com.fox.menu.bot.merchant.ui.generator.i.PlaceIKeyboardGenerator;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaceIKeyboardGeneratorImpl implements PlaceIKeyboardGenerator {

    private final InlineKeyboardGenerator inlineKeyboardGenerator;

    public PlaceIKeyboardGeneratorImpl(
            final InlineKeyboardGenerator inlineKeyboardGenerator
    ) {
        this.inlineKeyboardGenerator = inlineKeyboardGenerator;
    }

    @Override
    public InlineKeyboardMarkup checkOfAvailableItem(final long placeId) {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.CONFIRM_AVAILABLE_PLACE.getDescription(),
                CommandData.CONFIRM_AVAILABLE_PLACE.getValue() + " " + placeId
        ));
        return inlineKeyboardGenerator.generate(textDataToKeyboard, 1);
    }

    @Override
    public InlineKeyboardMarkup checkOfAvailableTitleEnd() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.EMPTY_AVAILABLE_PLACE.getDescription(),
                CommandData.EMPTY_AVAILABLE_PLACE.getValue()
        ));
        return inlineKeyboardGenerator.generate(textDataToKeyboard, 1);
    }

    @Override
    public InlineKeyboardMarkup beginBeck() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.START.getDescription(),
                CommandData.START.getValue()
        ));
        return inlineKeyboardGenerator.generate(textDataToKeyboard, 1);
    }
}















