package com.fox.shop.client.bot.ui.generate.i;

import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.protocol.ProductModel;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;

import java.util.List;
import java.util.Optional;

public interface ProductMessageGenerator {
  List<SendPhotoFileIdRequest> productByCategory(
      long chatId,
      long categoryId
  );

  SendMessage afterProductByCategory(
      long chatId,
      long userId,
      Optional<Long> shoppingCartId
  );

  SendMessage beginBack(
      long chatId
  );

  SendPhotoFileIdRequest product(
      long chatId,
      ProductModel product,
      String fileId
  );

  EditMessageCaption viewProductDescription(
      long chatId,
      long messageId,
      ProductModel product
  );
}
