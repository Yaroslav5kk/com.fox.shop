package com.fox.menu.bot.merchant.ui.generator.i;

import com.fox.menu.bot.merchant.model.request.SendMediaGroupRequest;
import com.fox.menu.protocol.PlaceModel;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public interface PlaceMessageGenerator {
    SendMessage checkOfAvailableTitle(
            long chatId
    );

    List<SendMessage> checkOfAvailableItems(
            long chatId,
            List<PlaceModel> places
    );

    SendMessage checkOfAvailableTitleEnd(
            long chatId
    );

    SendMediaGroupRequest allMerchantPlaces(
            long chatId,
            List<String> images
    );

    SendMessage emptyAvailablePlacesTitle(
            long chatId
    );

    SendMessage successConfirmedPlace(
            long chatId
    );

    SendMessage begin(
            long chatId
    );
}
