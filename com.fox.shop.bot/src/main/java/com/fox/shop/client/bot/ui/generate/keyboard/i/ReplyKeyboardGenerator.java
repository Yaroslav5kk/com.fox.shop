package com.fox.shop.client.bot.ui.generate.keyboard.i;

import org.javatuples.Pair;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

public interface ReplyKeyboardGenerator {
    ReplyKeyboardMarkup generateGetLocation();

    ReplyKeyboardMarkup generateGetPhone();
}
