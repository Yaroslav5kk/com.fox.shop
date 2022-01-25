package com.fox.shop.client.bot.processor.title;

import com.fox.shop.client.bot.annotation.CommandProcessorComponent;
import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.PriceApiClient;
import com.fox.shop.client.bot.api.client.i.ShoppingCartApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.processor.title.i.TgCommandTitleProcessorFather;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ReplyKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ShoppingSessionIKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.ShoppingSessionViewer;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartItemModel;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CommandProcessorComponent
public class EditCartSessionTitleProcessorImpl implements TgCommandTitleProcessorFather {
  private final TelegramApiMediator telegramApiMediator;
  private final ShoppingCartApiClient shoppingCartApiClient;
  private final ShoppingSessionIKeyboardGenerator shoppingSessionIKeyboardGenerator;
  private final PriceApiClient priceApiClient;
  private final StorageApiClient storageApiClient;
  private final BaseApiClient baseApiClient;

  public EditCartSessionTitleProcessorImpl(
      final TelegramApiMediator telegramApiMediator,
      final ShoppingCartApiClient shoppingCartApiClient,
      final ShoppingSessionIKeyboardGenerator shoppingSessionIKeyboardGenerator,
      final PriceApiClient priceApiClient,
      final StorageApiClient storageApiClient,
      final BaseApiClient baseApiClient
  ) {
    this.telegramApiMediator = telegramApiMediator;
    this.shoppingCartApiClient = shoppingCartApiClient;
    this.shoppingSessionIKeyboardGenerator = shoppingSessionIKeyboardGenerator;
    this.priceApiClient = priceApiClient;
    this.storageApiClient = storageApiClient;
    this.baseApiClient = baseApiClient;
  }

  @Override
  public void process(final TgIncomingCommandModel incomingCommand) {
    editCartSession(incomingCommand);
  }

  private void editCartSession(
      final TgIncomingCommandModel incomingCommand
  ) {
    final FullCartSessionModel sessionModel = shoppingCartApiClient.getCartSessionByUser(incomingCommand.getUserId());
    if (sessionModel.getId() == 0)
      telegramApiMediator.addMessage(incomingCommand.getUserId(), emptyCartSession(incomingCommand));
    else {
      editCartSessionTitle(incomingCommand.getChatId(), sessionModel).forEach(sendPhotoFileIdRequest ->
          telegramApiMediator.addMessage(incomingCommand.getUserId(), sendPhotoFileIdRequest));
    }
  }

  private List<SendPhotoFileIdRequest> editCartSessionTitle(
      final long chatId,
      final FullCartSessionModel cartSession
  ) {
    final List<SendPhotoFileIdRequest> result = new LinkedList<>();
    final List<FullCartItemModel> items = cartSession.getItems();
    final Map<Long, String> productIdMainImageId = baseApiClient.productsByIds(items
            .stream()
            .map(FullCartItemModel::getProductId)
            .collect(Collectors.toList())
        )
        .stream()
        .collect(Collectors.toMap(ProductModel::getId, productModel -> productModel.getMainImageStorageId()));
    for (var itItem : items) {
      final SendPhotoFileIdRequest photo = new SendPhotoFileIdRequest();
      photo.setPhoto(storageApiClient.getTelegramIdById(productIdMainImageId.get(itItem.getProductId())));
      photo.setChatId(chatId);
      photo.setReplyMarkup(shoppingSessionIKeyboardGenerator.editSessionItem(itItem.getId()));
      photo.setParseMode("HTML");
      photo.setCaption(ShoppingSessionViewer.viewSessionItem(itItem, priceApiClient.getByProductId(itItem.getProductId(), itItem.getQuantity()).getPriceToView()));
      result.add(photo);
    }
    return result;
  }

  private SendMessage emptyCartSession(
      final TgIncomingCommandModel incomingCommand
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(incomingCommand.getChatId());
    result.setReplyMarkup(shoppingSessionIKeyboardGenerator.startBack());
    result.setParseMode("HTML");
    result.setText(ShoppingSessionViewer.emptySession());
    return result;
  }

  @Override
  public CommandData getResponsibleCommand() {
    return CommandData.GET_PHONE_TITLE;
  }
}
