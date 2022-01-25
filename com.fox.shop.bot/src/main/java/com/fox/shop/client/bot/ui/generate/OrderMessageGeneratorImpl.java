package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.ShoppingCartApiClient;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.ui.generate.i.InlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.i.OrderMessageGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.OrderIKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.OrderViewer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class OrderMessageGeneratorImpl implements OrderMessageGenerator {

    @Value("${telegram.storage.make-order.file-id-on-telegram}")
    private String makeOrderFileIdOnTelegram;

    private final BaseApiClient baseApiClient;
    private final InlineKeyboardGenerator inlineKeyboardGenerator;
    private final UserProcessStateContext userProcessStateContext;
    private final UserDomainStateContext userDomainStateContext;
    private final UserModelDataContext modelDataContext;
    private final ShoppingCartApiClient shoppingCartApiClient;
    private final OrderIKeyboardGenerator orderIKeyboardGenerator;

    public OrderMessageGeneratorImpl(
            final BaseApiClient baseApiClient,
            final InlineKeyboardGenerator inlineKeyboardGenerator,
            final UserProcessStateContext userProcessStateContext,
            final UserDomainStateContext userDomainStateContext,
            final UserModelDataContext modelDataContext,
            final ShoppingCartApiClient shoppingCartApiClient,
            final OrderIKeyboardGenerator orderIKeyboardGenerator
    ) {
        this.baseApiClient = baseApiClient;
        this.inlineKeyboardGenerator = inlineKeyboardGenerator;
        this.userProcessStateContext = userProcessStateContext;
        this.userDomainStateContext = userDomainStateContext;
        this.modelDataContext = modelDataContext;
        this.shoppingCartApiClient = shoppingCartApiClient;
        this.orderIKeyboardGenerator = orderIKeyboardGenerator;
    }

    @Override
    public SendPhotoFileIdRequest makeOrderTitle(
            final long chatId
    ) {
        final SendPhotoFileIdRequest result = new SendPhotoFileIdRequest();
        result.setChatId(chatId);
        result.setParseMode("HTML");
        result.setPhoto(makeOrderFileIdOnTelegram);
        result.setReplyMarkup(orderIKeyboardGenerator.beginBack());
        result.setCaption(OrderViewer.successfulMadeOrder());
        return result;
    }

    @Override
    public SendMessage setOrderContactInfoTitle(
            final long chatId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setReplyMarkup(orderIKeyboardGenerator.setOrderContactInfoTitle());
        result.setParseMode("HTML");
        result.setText(OrderViewer.setOrderContactInfoTitle());
        return result;
    }

    @Override
    public SendMessage successfulMadeOrder(
            final long chatId,
            final long userId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setParseMode("HTML");
        result.setText(OrderViewer.successfulMadeOrder());
        result.setReplyMarkup(orderIKeyboardGenerator.beginBack());
        return result;
    }

}
