package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.ui.generate.i.ResetMessageGenerator;
import com.fox.shop.client.bot.ui.view.ResetViewer;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

@Component
public class ResetMessageGeneratorImpl implements ResetMessageGenerator {


  @Override
  public SendPhotoFileIdRequest reset(
      final long chatId
  ) {
    final SendPhotoFileIdRequest result = new SendPhotoFileIdRequest();
    result.setChatId(chatId);
    result.setParseMode("HTML");
    result.setCaption(ResetViewer.reset());
    return result;
  }
}
