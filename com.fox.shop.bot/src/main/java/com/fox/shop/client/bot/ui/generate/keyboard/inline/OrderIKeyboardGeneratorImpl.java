package com.fox.shop.client.bot.ui.generate.keyboard.inline;

import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.generate.i.InlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.OrderIKeyboardGenerator;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OrderIKeyboardGeneratorImpl implements OrderIKeyboardGenerator {

    private final InlineKeyboardGenerator inlineKeyboardGenerator;

    public OrderIKeyboardGeneratorImpl(
            final InlineKeyboardGenerator inlineKeyboardGenerator
    ) {
        this.inlineKeyboardGenerator = inlineKeyboardGenerator;
    }

    @Override
    public InlineKeyboardMarkup initOrder() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                "accept",
                CommandData.MAKE_ORDER_TITLE.getValue()
        ));
        textDataToKeyboard.add(Pair.of(
                CommandData.START.getDescription(),
                CommandData.START.getValue()
        ));
        textDataToKeyboard.add(Pair.of(
                CommandData.BACK.getDescription(),
                CommandData.BACK.getValue()
        ));
        return inlineKeyboardGenerator.generate(textDataToKeyboard, 1);
    }

    @Override
    public InlineKeyboardMarkup beginBack() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.START.getDescription(),
                CommandData.START.getValue()
        ));
        textDataToKeyboard.add(Pair.of(
                CommandData.BACK.getDescription(),
                CommandData.BACK.getValue()
        ));
        return inlineKeyboardGenerator.generate(textDataToKeyboard, 1);
    }

    @Override
    public InlineKeyboardMarkup setOrderContactInfoTitle() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.SET_ORDER_CONTACT_INFO_FROM_PROFILE.getDescription(),
                CommandData.SET_ORDER_CONTACT_INFO_FROM_PROFILE.getValue()
        ));
        textDataToKeyboard.add(Pair.of(
                CommandData.ENTER_ORDER_CONTACT_INFO.getDescription(),
                CommandData.ENTER_ORDER_CONTACT_INFO.getValue()
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
    public InlineKeyboardMarkup orderOnTime() {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                "soon",
                CommandData.ORDER_ON_TIME.getValue() + " " + new Date().getTime()
        ));
        textDataToKeyboard.add(Pair.of(
                "an hour later",
                CommandData.ORDER_ON_TIME.getValue() + " " + Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()));
        textDataToKeyboard.add(Pair.of(
                "an three hour later",
                CommandData.ORDER_ON_TIME.getValue() + " " + Instant.now().plus(3, ChronoUnit.HOURS).toEpochMilli()
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
    public InlineKeyboardMarkup orderOnName(final String nameFromProfile) {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                "from profile",
                CommandData.ORDER_ON_NAME.getValue() + " " + nameFromProfile
        ));
        textDataToKeyboard.add(Pair.of(
                "enter name",
                CommandData.ORDER_ON_NAME.getValue()
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












