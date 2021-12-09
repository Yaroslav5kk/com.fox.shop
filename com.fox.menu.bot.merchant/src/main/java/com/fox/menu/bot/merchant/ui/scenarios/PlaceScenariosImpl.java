package com.fox.menu.bot.merchant.ui.scenarios;

import com.fox.menu.bot.merchant.api.client.i.BaseApiClient;
import com.fox.menu.bot.merchant.api.client.i.BillingApiClient;
import com.fox.menu.bot.merchant.api.client.i.CustomerBotApiClient;
import com.fox.menu.bot.merchant.api.client.i.TelegramApiClient;
import com.fox.menu.bot.merchant.context.i.UserModelDataContext;
import com.fox.menu.bot.merchant.context.model.CheckAvailablePlaceStateModel;
import com.fox.menu.bot.merchant.model.MediaModelTelegram;
import com.fox.menu.bot.merchant.model.request.SendMediaGroupRequest;
import com.fox.menu.bot.merchant.repository.WaiterInfoRepository;
import com.fox.menu.bot.merchant.ui.generator.i.PlaceMessageGenerator;
import com.fox.menu.bot.merchant.ui.scenarios.i.PlaceScenarios;
import com.fox.menu.protocol.PlaceModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaceScenariosImpl implements PlaceScenarios {

    private final WaiterInfoRepository waiterInfoRepository;
    private final TelegramApiClient telegramApiClient;
    private final BaseApiClient baseApiClient;
    private final BillingApiClient billingApiClient;
    private final CustomerBotApiClient customerBotApiClient;
    private final PlaceMessageGenerator placeMessageGenerator;
    private final UserModelDataContext userModelDataContext;

    public PlaceScenariosImpl(
            final WaiterInfoRepository waiterInfoRepository,
            final TelegramApiClient telegramApiClient,
            final BaseApiClient baseApiClient,
            final BillingApiClient billingApiClient,
            final CustomerBotApiClient customerBotApiClient,
            final PlaceMessageGenerator placeMessageGenerator,
            final UserModelDataContext userModelDataContext
    ) {
        this.waiterInfoRepository = waiterInfoRepository;
        this.telegramApiClient = telegramApiClient;
        this.baseApiClient = baseApiClient;
        this.billingApiClient = billingApiClient;
        this.customerBotApiClient = customerBotApiClient;
        this.placeMessageGenerator = placeMessageGenerator;
        this.userModelDataContext = userModelDataContext;
    }

    @Override
    public void checkOfAvailableTitle(
            final long customerId,
            final long waiterId,
            final List<Long> placeIds
    ) {
        userModelDataContext.checkAvailablePlaceState(waiterId, new CheckAvailablePlaceStateModel(customerId, waiterId));
        final long chatId = waiterInfoRepository.findById(waiterId).get().getChatId();
        final List<PlaceModel> places = baseApiClient.placesByIds(placeIds);
        sendMerchantPlaces(chatId, waiterId);
        telegramApiClient.sendMessage(placeMessageGenerator.checkOfAvailableTitle(chatId));
        placeMessageGenerator.checkOfAvailableItems(chatId, places).forEach(telegramApiClient::sendMessage);
        telegramApiClient.sendMessage(placeMessageGenerator.checkOfAvailableTitleEnd(chatId));
    }

    @Override
    public void confirmAvailable(
            final long chatId,
            final long waiterId,
            final long placeId
    ) {
        userModelDataContext.getCheckAvailablePlaceState(waiterId).confirmedPlaceId(placeId);
        final long customerId = userModelDataContext.getCheckAvailablePlaceState(waiterId).getCustomerId();
        billingApiClient.confirmPlace(customerId, placeId);
        customerBotApiClient.confirmPlace(customerId, placeId);
        telegramApiClient.sendMessage(placeMessageGenerator.successConfirmedPlace(chatId));
    }

    @Override
    public void emptyAvailablePlaceTitle(
            final long chatId,
            final long waiterId
    ) {
        sendMerchantPlaces(chatId, waiterId);
        telegramApiClient.sendMessage(placeMessageGenerator.emptyAvailablePlacesTitle(chatId));
    }

    @Override
    public void emptyAvailablePlaceHandle(
            final long chatId,
            final long waiterId,
            final String placeIdOnMap
    ) {
        final long placeId = baseApiClient.placeByIdOnMap(placeIdOnMap, waiterId).getId();
        final long customerId = userModelDataContext.getCheckAvailablePlaceState(waiterId).getCustomerId();
        billingApiClient.confirmPlace(customerId, placeId);
        customerBotApiClient.confirmPlace(customerId, placeId);
        telegramApiClient.sendMessage(placeMessageGenerator.successConfirmedPlace(chatId));
    }

    private void sendMerchantPlaces(
            final long chatId,
            final long waiterId
    ) {
        final List<MediaModelTelegram> placeImages = baseApiClient.merchantPlacesByWaiterId(waiterId)
                .stream()
                .map(MediaModelTelegram::buildPhotoFileIdOnTelegram)
                .collect(Collectors.toList());
        final SendMediaGroupRequest request = new SendMediaGroupRequest();
        request.setChatId(chatId);
        request.setMedia(placeImages);
        telegramApiClient.sendMediaGroup(request);
    }
}





















