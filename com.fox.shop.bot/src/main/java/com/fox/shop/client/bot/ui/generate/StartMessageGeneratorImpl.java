package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.ui.generate.i.StartMessageGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ReplyKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.StartIKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.StartViewer;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Optional;


@Service
public class StartMessageGeneratorImpl implements StartMessageGenerator {
    private final ReplyKeyboardGenerator replyKeyboardGenerator;
    private final StartIKeyboardGenerator startIKeyboardGenerator;

    public StartMessageGeneratorImpl(
            final ReplyKeyboardGenerator replyKeyboardGenerator,
            final StartIKeyboardGenerator startIKeyboardGenerator
    ) {
        this.replyKeyboardGenerator = replyKeyboardGenerator;
        this.startIKeyboardGenerator = startIKeyboardGenerator;
    }

    @Override
    public SendMessage start(
            final long chatId,
            final Optional<Long> cartSessionId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setParseMode("HTML");
        result.setText(StartViewer.base());
        result.setReplyMarkup(startIKeyboardGenerator.base(cartSessionId));
        return result;
    }

    @Override
    public SendMessage getPhone(
            final long chatId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setParseMode("HTML");
        result.setReplyMarkup(replyKeyboardGenerator.generateGetPhone());
        result.setText(StartViewer.setPhone());
        return result;
    }

    @Override
    public SendMessage getName(
            final long chatId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setParseMode("HTML");
        result.setText(StartViewer.setName());
        return result;
    }

}









