package com.fox.shop.client.bot.ui.generate.keyboard.inline;

import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.generate.i.InlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.PrePostCommandHandleKeyboardGenerator;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrePostCommandHandleIKeyboardGeneratorImpl implements PrePostCommandHandleKeyboardGenerator {

    private final InlineKeyboardGenerator inlineKeyboardGenerator;

    public PrePostCommandHandleIKeyboardGeneratorImpl(
            final InlineKeyboardGenerator inlineKeyboardGenerator
    ) {
        this.inlineKeyboardGenerator = inlineKeyboardGenerator;
    }

    @Override
    public InlineKeyboardMarkup postHandleStart(final int userId) {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.START.getDescription(),
                CommandData.START.getValue()
        ));
        textDataToKeyboard.add(Pair.of(
                CommandData.GET_CART_SESSION.getDescription(),
                CommandData.GET_CART_SESSION.getValue() + " " + userId
        ));
        return inlineKeyboardGenerator.generate(textDataToKeyboard, 1);
    }
}

















