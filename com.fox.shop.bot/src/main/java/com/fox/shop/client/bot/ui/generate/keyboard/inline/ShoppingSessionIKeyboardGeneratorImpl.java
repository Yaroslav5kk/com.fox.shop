package com.fox.shop.client.bot.ui.generate.keyboard.inline;

import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.generate.i.InlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ShoppingSessionIKeyboardGenerator;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingSessionIKeyboardGeneratorImpl implements ShoppingSessionIKeyboardGenerator {

    private final InlineKeyboardGenerator inlineKeyboardGenerator;

    public ShoppingSessionIKeyboardGeneratorImpl(
            final InlineKeyboardGenerator inlineKeyboardGenerator
    ) {
        this.inlineKeyboardGenerator = inlineKeyboardGenerator;
    }

    @Override
    public InlineKeyboardMarkup getCartSession(final long cartSessionId) {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.MAKE_ORDER_TITLE.getDescription(),
                CommandData.MAKE_ORDER_TITLE.getValue() + " " + cartSessionId
        ));
        textDataToKeyboard.add(Pair.of(
                CommandData.EDIT_CART_SESSION.getDescription(),
                CommandData.EDIT_CART_SESSION.getValue() + " " + cartSessionId
        ));
        textDataToKeyboard.add(Pair.of(
                CommandData.CLEAN_CART_SESSION.getDescription(),
                CommandData.CLEAN_CART_SESSION.getValue()
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

    @Override
    public InlineKeyboardMarkup startBack() {
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
    public InlineKeyboardMarkup editSessionItem(final long sessionItemId) {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.SET_ITEM_QUANTITY_ON_UPDATE_CART_TITLE.getDescription(),
                CommandData.SET_ITEM_QUANTITY_ON_UPDATE_CART_TITLE.getValue() + " " + sessionItemId
        ));
        return inlineKeyboardGenerator.generate(textDataToKeyboard, 1);
    }


    @Override
    public InlineKeyboardMarkup setQuantity() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                1,
                1
        ));
        textDataToKeyboard.add(Pair.of(
                2,
                2
        ));
        textDataToKeyboard.add(Pair.of(
                5,
                5
        ));
        return inlineKeyboardGenerator.generate(textDataToKeyboard, 1);
    }


    @Override
    public InlineKeyboardMarkup beginBack() {
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
    public InlineKeyboardMarkup successAddToCart(final long cartSessionId) {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.MAKE_ORDER_TITLE.getDescription(),
                CommandData.MAKE_ORDER_TITLE.getValue() + " " + cartSessionId
        ));
        textDataToKeyboard.add(Pair.of(
                CommandData.EDIT_CART_SESSION.getDescription(),
                CommandData.EDIT_CART_SESSION.getValue() + " " + cartSessionId
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
