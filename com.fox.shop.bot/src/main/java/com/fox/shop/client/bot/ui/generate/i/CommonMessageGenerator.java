package com.fox.shop.client.bot.ui.generate.i;

import org.springframework.data.util.Pair;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public interface CommonMessageGenerator {
    SendMessage id(Long chatId);

    SendMessage value(
            Long chatId
    );

    SendMessage priority(
            Long chatId
    );

    SendMessage url(
            Long chatId
    );

    SendMessage description(Long chatId);

    SendMessage image(Long chatId);

    SendMessage finish(Long chatId);

  SendMessage numbering(
      String textToDisplayInHtml,
      long chatId,
      int startNumbering,
      int endNumbering,
      boolean isEnableEnterOther,
      boolean isEnablePaginationPages
  );

  List<Pair<?, ?>> buildTextDataToKeyboardNumbering(
      int startNumbering,
      int endNumbering,
      boolean isEnableEnterOther,
      boolean isEnablePaginationPages
  );
}
