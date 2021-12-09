package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.ui.generate.i.PaginationMessageGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.PaginationKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.PaginationViewer;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class PaginationMessageGeneratorImpl implements PaginationMessageGenerator {

    private final PaginationKeyboardGenerator paginationKeyboardGenerator;

    public PaginationMessageGeneratorImpl(
            final PaginationKeyboardGenerator paginationKeyboardGenerator
    ) {
        this.paginationKeyboardGenerator = paginationKeyboardGenerator;
    }

    @Override
    public SendMessage pagination(
            final long chatId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(PaginationViewer.pagination());
        result.setReplyMarkup(paginationKeyboardGenerator.pagination());
        result.setParseMode("HTML");
        return result;
    }

    @Override
    public boolean isNeedPagination(final int amountElements){
        return paginationKeyboardGenerator.isNeedPagination(amountElements);
    }
}
