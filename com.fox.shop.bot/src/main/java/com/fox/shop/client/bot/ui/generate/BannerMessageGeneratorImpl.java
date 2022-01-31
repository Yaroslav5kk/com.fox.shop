package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.entity.BannerEntity;
import com.fox.shop.client.bot.ui.generate.i.BannerMessageGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ReplyKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.BannerViewer;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


@Service
public class BannerMessageGeneratorImpl implements BannerMessageGenerator {
  private final ReplyKeyboardGenerator replyKeyboardGenerator;

  public BannerMessageGeneratorImpl(
      final ReplyKeyboardGenerator replyKeyboardGenerator
  ) {
    this.replyKeyboardGenerator = replyKeyboardGenerator;
  }

  @Override
  public SendMessage build(
      final BannerEntity banner,
      final long chatId
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setParseMode("HTML");
    result.setText(BannerViewer.general(banner));
    return result;
  }

}









