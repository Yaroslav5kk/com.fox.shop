package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.PriceApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.ui.generate.i.InlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.i.ShoppingCartMessageGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ShoppingSessionIKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.StartIKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.ShoppingSessionViewer;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartItemModel;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ShoppingCartMessageGeneratorImpl implements ShoppingCartMessageGenerator {

  private final BaseApiClient baseApiClient;
  private final InlineKeyboardGenerator inlineKeyboardGenerator;
  private final ShoppingSessionIKeyboardGenerator shoppingSessionIKeyboardGenerator;
  private final StartIKeyboardGenerator startIKeyboardGenerator;
  private final PriceApiClient priceApiClient;
  private final StorageApiClient storageApiClient;

  public ShoppingCartMessageGeneratorImpl(
      final BaseApiClient baseApiClient,
      final InlineKeyboardGenerator inlineKeyboardGenerator,
      final ShoppingSessionIKeyboardGenerator shoppingSessionIKeyboardGenerator,
      final StartIKeyboardGenerator startIKeyboardGenerator,
      final PriceApiClient priceApiClient,
      final StorageApiClient storageApiClient
  ) {
    this.baseApiClient = baseApiClient;
    this.inlineKeyboardGenerator = inlineKeyboardGenerator;
    this.shoppingSessionIKeyboardGenerator = shoppingSessionIKeyboardGenerator;
    this.startIKeyboardGenerator = startIKeyboardGenerator;
    this.priceApiClient = priceApiClient;
    this.storageApiClient = storageApiClient;
  }

  @Override
  public SendMessage getCartSession(
      final long chatId,
      final FullCartSessionModel cartSession
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setReplyMarkup(shoppingSessionIKeyboardGenerator.getCartSession(cartSession.getId()));
    result.setParseMode("HTML");
    result.setText(ShoppingSessionViewer.view(cartSession, getSessionTotalPrice(cartSession)));
    return result;
  }

  @Override
  public List<SendPhotoFileIdRequest> editCartSessionTitle(
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

  @Override
  public SendMessage setItemQuantityTitle(
      final long chatId
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setParseMode("HTML");
    result.setText(ShoppingSessionViewer.setNewCartItemQuantityTitle());
    result.setReplyMarkup(shoppingSessionIKeyboardGenerator.setQuantity());
    return result;
  }

  @Override
  public SendMessage emptyCartSession(
      final long chatId
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setParseMode("HTML");
    result.setText(ShoppingSessionViewer.emptySession());
    return result;
  }

  @Override
  public SendMessage successClearCartSession(
      final long chatId
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setParseMode("HTML");
    result.setText(ShoppingSessionViewer.successClearSession());
    return result;
  }

  private String getSessionTotalPrice(final FullCartSessionModel cartSession) {
    return cartSession.getItems()
        .stream()
        .map(fullCartItemModel -> priceApiClient.getByProductId(
            fullCartItemModel.getProductId(),
            fullCartItemModel.getQuantity()).getPrice())
        .reduce(Integer::sum).get().toString();
  }

}
