package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.ui.generate.i.SearchMessageGenerator;
import com.fox.shop.client.bot.ui.view.SearchViewer;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class SearchMessageGeneratorImpl implements SearchMessageGenerator {

  @Override
  public SendMessage searchTitle(
      final long chatId
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setText(SearchViewer.searchTitle());
    result.setParseMode("HTML");
    return result;
  }

}
