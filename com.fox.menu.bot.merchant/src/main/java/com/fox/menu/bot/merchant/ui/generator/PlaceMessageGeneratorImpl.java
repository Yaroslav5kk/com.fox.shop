package com.fox.menu.bot.merchant.ui.generator;

import com.fox.menu.bot.merchant.model.MediaModelTelegram;
import com.fox.menu.bot.merchant.model.request.SendMediaGroupRequest;
import com.fox.menu.bot.merchant.ui.generator.i.PlaceIKeyboardGenerator;
import com.fox.menu.bot.merchant.ui.generator.i.PlaceMessageGenerator;
import com.fox.menu.bot.merchant.ui.view.i.PlaceViewer;
import com.fox.menu.protocol.PlaceModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaceMessageGeneratorImpl implements PlaceMessageGenerator {

    private final PlaceViewer placeViewer;
    private final PlaceIKeyboardGenerator placeIKeyboardGenerator;

    public PlaceMessageGeneratorImpl(
            final PlaceViewer placeViewer,
            final PlaceIKeyboardGenerator placeIKeyboardGenerator
    ) {
        this.placeViewer = placeViewer;
        this.placeIKeyboardGenerator = placeIKeyboardGenerator;
    }

    @Override
    public SendMessage checkOfAvailableTitle(
            final long chatId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(placeViewer.checkOfAvailableTitle());
        result.setParseMode("HTML");
        return result;
    }

    @Override
    public List<SendMessage> checkOfAvailableItems(
            final long chatId,
            final List<PlaceModel> places
    ) {
        final List<SendMessage> result = new ArrayList<>();
        for (var it : places) {
            final SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(placeViewer.view(it));
            message.setReplyMarkup(placeIKeyboardGenerator.checkOfAvailableItem(it.getId()));
            message.setParseMode("HTML");
        }
        return result;
    }

    @Override
    public SendMessage checkOfAvailableTitleEnd(
            final long chatId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(placeViewer.beginBeck());
        result.setReplyMarkup(placeIKeyboardGenerator.checkOfAvailableTitleEnd());
        result.setParseMode("HTML");
        return result;
    }

    @Override
    public SendMediaGroupRequest allMerchantPlaces(
            final long chatId,
            final List<String> images
    ) {
        final SendMediaGroupRequest result = new SendMediaGroupRequest();
        result.setChatId(chatId);
        result.setMedia(images.stream().map(MediaModelTelegram::buildPhotoFileIdOnTelegram).collect(Collectors.toList()));
        return result;
    }

    @Override
    public SendMessage emptyAvailablePlacesTitle(
            final long chatId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(placeViewer.emptyAvailablePlacesTitle());
        result.setParseMode("HTML");
        return result;
    }

    @Override
    public SendMessage successConfirmedPlace(
            final long chatId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(placeViewer.successConfirmedPlace());
        result.setReplyMarkup(placeIKeyboardGenerator.beginBeck());
        result.setParseMode("HTML");
        return result;
    }

    @Override
    public SendMessage begin(
            final long chatId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(placeViewer.beginBeck());
        result.setReplyMarkup(placeIKeyboardGenerator.beginBeck());
        result.setParseMode("HTML");
        return result;
    }
}
















