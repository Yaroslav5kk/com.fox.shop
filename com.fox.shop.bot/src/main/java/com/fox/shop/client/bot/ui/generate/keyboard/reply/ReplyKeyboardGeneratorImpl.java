package com.fox.shop.client.bot.ui.generate.keyboard.reply;

import com.fox.shop.client.bot.ui.generate.keyboard.i.ReplyKeyboardGenerator;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReplyKeyboardGeneratorImpl implements ReplyKeyboardGenerator {


    @Override
    public ReplyKeyboardMarkup generateGetLocation() {
        final ReplyKeyboardMarkup result = new ReplyKeyboardMarkup();
        final List<KeyboardRow> rows = new ArrayList<>();
        final KeyboardRow row = new KeyboardRow();
        final KeyboardButton button = new KeyboardButton();
        button.setText("Nearby merchants");
        button.setRequestLocation(true);
        row.add(button);
        rows.add(row);
        result.setKeyboard(rows);
        return result;
    }

    @Override
    public ReplyKeyboardMarkup generateGetPhone() {
        final ReplyKeyboardMarkup result = new ReplyKeyboardMarkup();
        final List<KeyboardRow> rows = new ArrayList<>();
        final KeyboardRow row = new KeyboardRow();
        final KeyboardButton button = new KeyboardButton();
        button.setText("Відправляю");
        button.setRequestContact(true);
        row.add(button);
        rows.add(row);
        result.setKeyboard(rows);
        return result;
    }
}


















