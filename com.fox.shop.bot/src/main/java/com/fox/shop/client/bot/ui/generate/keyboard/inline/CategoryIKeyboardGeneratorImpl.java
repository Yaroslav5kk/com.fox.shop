package com.fox.shop.client.bot.ui.generate.keyboard.inline;

import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.generate.i.InlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.CategoryIKeyboardGenerator;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryIKeyboardGeneratorImpl implements CategoryIKeyboardGenerator {

    private final InlineKeyboardGenerator inlineKeyboardGenerator;

    public CategoryIKeyboardGeneratorImpl(
            final InlineKeyboardGenerator inlineKeyboardGenerator
    ) {
        this.inlineKeyboardGenerator = inlineKeyboardGenerator;
    }

    @Override
    public InlineKeyboardMarkup afterAllCategory(final long userId) {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.BACK.getDescription(),
                CommandData.BACK.getValue()
        ));
        textDataToKeyboard.add(Pair.of(
                CommandData.START.getDescription(),
                CommandData.START.getValue()
        ));
        return inlineKeyboardGenerator.generate(textDataToKeyboard, 1);
    }

    @Override
    public InlineKeyboardMarkup category(final long categoryId) {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.PRODUCT_BY_CATEGORY.getDescription(),
                CommandData.PRODUCT_BY_CATEGORY.getValue() + " " + categoryId
        ));
        return inlineKeyboardGenerator.generate(textDataToKeyboard, 1);
    }
}















