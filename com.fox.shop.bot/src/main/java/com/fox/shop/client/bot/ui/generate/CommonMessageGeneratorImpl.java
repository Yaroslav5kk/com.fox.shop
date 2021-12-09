package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.generate.i.CommonMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.InlineKeyboardGenerator;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonMessageGeneratorImpl implements CommonMessageGenerator {
  private final static String ANSWER_ON_ID = "input id: ";
  private final static String ANSWER_ON_VALUE = "input value: ";
  private final static String ANSWER_ON_URL = "input url: ";
  private final static String ANSWER_ON_PRIORITY = "input priority: ";
  private final static String ANSWER_ON_DESCRIPTION = "input description: ";
  private final static String ANSWER_ON_IMAGE = "input image as file";
  private final static String ANSWER_ON_FINISH = "completed current process";
  private final static String ANSWER_ON_LIST_TAG = "input tags with spliter: <;>";
  private final static String ANSWER_ON_LIST_THEME = "input thems with spliter: <;>";

  private final InlineKeyboardGenerator inlineKeyboardGenerator;

  public CommonMessageGeneratorImpl(
      final InlineKeyboardGenerator inlineKeyboardGenerator
  ) {
    this.inlineKeyboardGenerator = inlineKeyboardGenerator;
  }

  @Override
  public SendMessage id(final Long chatId) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setText(ANSWER_ON_ID);
    return result;
  }

  @Override
  public SendMessage value(
      final Long chatId
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setText(ANSWER_ON_VALUE);
    return result;
  }

  @Override
  public SendMessage priority(
      final Long chatId
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setText(ANSWER_ON_PRIORITY);
    return result;
  }

  @Override
  public SendMessage url(
      final Long chatId
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setText(ANSWER_ON_URL);
    return result;
  }

  @Override
  public SendMessage description(final Long chatId) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setText(ANSWER_ON_DESCRIPTION);
    return result;
  }

  @Override
  public SendMessage image(final Long chatId) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setText(ANSWER_ON_IMAGE);
    return result;
  }

  @Override
  public SendMessage finish(final Long chatId) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setText(ANSWER_ON_FINISH);
    return result;
  }

  @Override
  public SendMessage numbering(
      final String textToDisplayInHtml,
      final long chatId,
      final int startNumbering,
      final int endNumbering,
      final boolean isEnableEnterOther,
      final boolean isEnablePaginationPages
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setText(textToDisplayInHtml);
    result.setParseMode("HTML");
    result.setReplyMarkup(inlineKeyboardGenerator.generate(buildTextDataToKeyboardNumbering(
        startNumbering,
        endNumbering,
        isEnableEnterOther,
        isEnablePaginationPages
    ), 1));
    return result;
  }

  @Override
  public List<Pair<?, ?>> buildTextDataToKeyboardNumbering(
      final int startNumbering,
      final int endNumbering,
      final boolean isEnableEnterOther,
      final boolean isEnablePaginationPages
  ) {
    final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
    for (int i = startNumbering; i < endNumbering; i++) {
      textDataToKeyboard.add(Pair.of(
          i,
          i
      ));
    }
    if (isEnableEnterOther) {
      textDataToKeyboard.add(Pair.of(
          CommandData.ENTER_OTHER_VALUE.getDescription(),
          CommandData.ENTER_OTHER_VALUE.getValue()
      ));
    }
    if (isEnablePaginationPages) {
      textDataToKeyboard.add(Pair.of(
          CommandData.NEXT_PAGE.getDescription(),
          CommandData.NEXT_PAGE.getValue()
      ));

      textDataToKeyboard.add(Pair.of(
          CommandData.NEXT_PAGE.getDescription(),
          CommandData.NEXT_PAGE.getValue()
      ));
    }
    return textDataToKeyboard;
  }

}
