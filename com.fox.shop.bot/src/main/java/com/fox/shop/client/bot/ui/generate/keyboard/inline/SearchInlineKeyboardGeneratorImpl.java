package com.fox.shop.client.bot.ui.generate.keyboard.inline;

import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.generate.i.InlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.SearchInlineKeyboardGenerator;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchInlineKeyboardGeneratorImpl implements SearchInlineKeyboardGenerator {

    private final InlineKeyboardGenerator inlineKeyboardGenerator;

    public SearchInlineKeyboardGeneratorImpl(
            final InlineKeyboardGenerator inlineKeyboardGenerator
    ) {
        this.inlineKeyboardGenerator = inlineKeyboardGenerator;
    }

    @Override
    public InlineKeyboardMarkup searchTitle() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
            CommandData.SEARCH_PRODUCT.getDescription(),
            CommandData.SEARCH_PRODUCT.getValue()
        ));
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

}
