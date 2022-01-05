package com.fox.shop.client.bot.ui.generate.i;

import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

public interface ResetMessageGenerator {
  SendPhotoFileIdRequest reset(
      long chatId
  );
}
