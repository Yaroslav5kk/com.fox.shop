package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.PriceApiClient;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.ui.generate.i.ProductMessageGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ProductIKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.ProductViewer;
import com.fox.shop.protocol.ProductModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;

@Component
public class ProductMessageGeneratorImpl implements ProductMessageGenerator {
  private final ProductIKeyboardGenerator productIKeyboardGenerator;
  private final PriceApiClient priceApiClient;

  public ProductMessageGeneratorImpl(
      final ProductIKeyboardGenerator productIKeyboardGenerator,
      final PriceApiClient priceApiClient
  ) {
    this.productIKeyboardGenerator = productIKeyboardGenerator;
    this.priceApiClient = priceApiClient;
  }

  @Override
  public SendPhotoFileIdRequest product(
      final long chatId,
      final ProductModel product,
      final String fileId
  ) {
    final SendPhotoFileIdRequest result = new SendPhotoFileIdRequest();
    result.setChatId(chatId);
    result.setCaption(ProductViewer.view(product, priceApiClient.getByProductId(product.getId(), 1).getPriceToView()));
    result.setParseMode("HTML");
    result.setReplyMarkup(productIKeyboardGenerator.product(product.getId()));
    result.setPhoto(fileId);
    return result;
  }

  @Override
  public EditMessageCaption viewProductDescription(
      final long chatId,
      final long messageId,
      final ProductModel product
  ) {
    final EditMessageCaption result = new EditMessageCaption();
    result.setChatId(String.valueOf(chatId));
    result.setParseMode("HTML");
    result.setMessageId((int) messageId);
    result.setCaption(ProductViewer.viewWithDescription(product, priceApiClient.getByProductId(product.getId(), 1).getPriceToView()));
    result.setReplyMarkup(productIKeyboardGenerator.onlyAddToCart(product.getId()));
    return result;
  }
}
















