package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
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


  private final OrderIKeyboardGenerator orderIKeyboardGenerator;

  public OrderMessageGeneratorImpl(
      final OrderIKeyboardGenerator orderIKeyboardGenerator
  ) {
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
