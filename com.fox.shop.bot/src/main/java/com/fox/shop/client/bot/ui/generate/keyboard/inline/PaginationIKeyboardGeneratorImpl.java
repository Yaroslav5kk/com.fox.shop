package com.fox.shop.client.bot.ui.generate.keyboard.inline;

import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.generate.i.InlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.PaginationKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.EmojiElementGenerator;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.LinkedList;
import java.util.List;

@Component
public class PaginationIKeyboardGeneratorImpl implements PaginationKeyboardGenerator {
    private final short amountPages = 5;
    private final short numberOfNeedPagination = 10;
    private final InlineKeyboardGenerator inlineKeyboardGenerator;

    public PaginationIKeyboardGeneratorImpl(
            final InlineKeyboardGenerator inlineKeyboardGenerator
    ) {
        this.inlineKeyboardGenerator = inlineKeyboardGenerator;
    }

    @Override
    public InlineKeyboardMarkup pagination() {
        final List<Pair<?, ?>> textDataToKeyboard = new LinkedList<>();
        textDataToKeyboard.add(Pair.of(
                EmojiElementGenerator.previousPage(),
                CommandData.PREVIOUS_PAGE
        ));
        for (int i = 1; i < amountPages; i++) {
            textDataToKeyboard.add(Pair.of(
                    i,
                    CommandData.PAGINATION.getValue() + " " + i
            ));
        }
        textDataToKeyboard.add(Pair.of(
                EmojiElementGenerator.nextPage(),
                CommandData.NEXT_PAGE
        ));
        return inlineKeyboardGenerator.generateHorizontal(textDataToKeyboard);
    }

    @Override
    public boolean isNeedPagination(final int amountElements){
        return amountElements > numberOfNeedPagination;
    }
}
