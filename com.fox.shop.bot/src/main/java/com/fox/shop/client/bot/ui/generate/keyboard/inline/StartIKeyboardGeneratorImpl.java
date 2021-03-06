package com.fox.shop.client.bot.ui.generate.keyboard.inline;

import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.generate.i.InlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.StartIKeyboardGenerator;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StartIKeyboardGeneratorImpl implements StartIKeyboardGenerator {

    private final InlineKeyboardGenerator inlineKeyboardGenerator;

    public StartIKeyboardGeneratorImpl(
            final InlineKeyboardGenerator inlineKeyboardGenerator
    ) {
        this.inlineKeyboardGenerator = inlineKeyboardGenerator;
    }

    @Override
    public InlineKeyboardMarkup base(final Optional<Long> cartSessionId) {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.GET_CART_SESSION.getDescription(),
                CommandData.GET_CART_SESSION.getValue()
        ));
        cartSessionId.ifPresent(aLong ->
            textDataToKeyboard.add(Pair.of(
                CommandData.MAKE_ORDER_TITLE.getDescription(),
                CommandData.MAKE_ORDER_TITLE.getValue() + " " + aLong
            )));
        textDataToKeyboard.add(Pair.of(
                CommandData.SEARCH_TITLE.getDescription(),
                CommandData.SEARCH_TITLE.getValue()
        ));
        return inlineKeyboardGenerator.generate(textDataToKeyboard, 1);
    }

}












