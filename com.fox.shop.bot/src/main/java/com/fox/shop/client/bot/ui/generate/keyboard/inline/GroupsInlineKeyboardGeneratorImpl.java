package com.fox.shop.client.bot.ui.generate.keyboard.inline;

import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.generate.i.InlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.GroupsInlineKeyboardGenerator;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupsInlineKeyboardGeneratorImpl implements GroupsInlineKeyboardGenerator {

    private final InlineKeyboardGenerator inlineKeyboardGenerator;

    public GroupsInlineKeyboardGeneratorImpl(
            final InlineKeyboardGenerator inlineKeyboardGenerator
    ) {
        this.inlineKeyboardGenerator = inlineKeyboardGenerator;
    }

    @Override
    public InlineKeyboardMarkup productGroup(
            final long userId,
            final long groupId
    ) {
        final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
        textDataToKeyboard.add(Pair.of(
                CommandData.PRODUCTS_BY_GROUP.getDescription(),
                CommandData.PRODUCTS_BY_GROUP.getValue() + " " + groupId
        ));
        return inlineKeyboardGenerator.generate(textDataToKeyboard, 1);
    }
}























































