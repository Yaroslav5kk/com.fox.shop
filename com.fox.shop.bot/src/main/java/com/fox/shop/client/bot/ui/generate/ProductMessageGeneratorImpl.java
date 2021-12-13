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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductMessageGeneratorImpl implements ProductMessageGenerator {
  private final BaseApiClient baseApiClient;
  private final ProductIKeyboardGenerator productIKeyboardGenerator;
  private final PriceApiClient priceApiClient;

  public ProductMessageGeneratorImpl(
      final BaseApiClient baseApiClient,
      final ProductIKeyboardGenerator productIKeyboardGenerator,
      final PriceApiClient priceApiClient
  ) {
    this.baseApiClient = baseApiClient;
    this.productIKeyboardGenerator = productIKeyboardGenerator;
    this.priceApiClient = priceApiClient;
  }

  @Override
  public List<SendPhotoFileIdRequest> productByCategory(
      final long chatId,
      final long categoryId
  ) {
    return baseApiClient.productByCategory(categoryId).stream().
        map(product -> product(chatId, product, baseApiClient.mainImageByteByProduct(product.getId()))).
        collect(Collectors.toList());
  }

  @Override
  public SendMessage afterProductByCategory(
      final long chatId,
      final long userId,
      final Optional<Long> shoppingCartId
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setText(ProductViewer.afterProductBycategory());
    result.setReplyMarkup(productIKeyboardGenerator.afterAllProduct(userId, shoppingCartId));
    result.setParseMode("HTML");
    return result;
  }

  @Override
  public SendMessage beginBack(
      final long chatId
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setText(ProductViewer.afterProductBycategory());
    result.setReplyMarkup(productIKeyboardGenerator.beginBack());
    result.setParseMode("HTML");
    return result;
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
    result.setCaption(ProductViewer.view(product, priceApiClient.getByProductId(product.getId(), 1).getPriceToView()));
    result.setReplyMarkup(productIKeyboardGenerator.onlyAddToCart(product.getId()));
    return result;
  }
}
















