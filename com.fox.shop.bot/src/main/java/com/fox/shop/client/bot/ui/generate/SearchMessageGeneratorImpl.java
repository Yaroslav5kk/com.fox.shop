package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.ui.generate.i.SearchMessageGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.SearchInlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.SearchViewer;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class SearchMessageGeneratorImpl implements SearchMessageGenerator {


    private final BaseApiClient baseApiClient;
    private final SearchInlineKeyboardGenerator searchInlineKeyboardGenerator;

    public SearchMessageGeneratorImpl(
        final BaseApiClient baseApiClient,
        final SearchInlineKeyboardGenerator searchInlineKeyboardGenerator
        ) {
        this.baseApiClient = baseApiClient;
        this.searchInlineKeyboardGenerator = searchInlineKeyboardGenerator;
    }

    @Override
    public SendMessage searchTitle(
        final long chatId
    ){
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(SearchViewer.searchTitle());
        result.setParseMode("HTML");
        result.setReplyMarkup(searchInlineKeyboardGenerator.searchTitle());
        return result;
    }

    @Override
    public SendMessage searchMerchantTitle(
        final long chatId
    ){
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(SearchViewer.searchMerchantTitle());
        result.setParseMode("HTML");
        return result;
    }

    @Override
    public SendMessage searchProductTitle(
        final long chatId
    ){
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(SearchViewer.searchProductTitle());
        result.setParseMode("HTML");
        return result;
    }
}
