package com.fox.shop.client.bot.ui.generate.keyboard.inline;

import com.fox.shop.client.bot.ui.generate.i.InlineKeyboardGenerator;
import org.javatuples.Triplet;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class InlineKeyboardGeneratorImpl implements InlineKeyboardGenerator {

  @Override
  public InlineKeyboardMarkup generate(
          final List<Pair<?, ?>> textData,
          final int numberOfColumns
  ) {
    final InlineKeyboardMarkup result = new InlineKeyboardMarkup();
    final List<List<InlineKeyboardButton>> rows = new LinkedList<>();
    final List<InlineKeyboardButton> columns = new LinkedList<>();
    for (int i = 0; i < textData.size(); i++) {
      columns.add(new InlineKeyboardButton().
              setText(String.valueOf(textData.get(i).getFirst())).
              setCallbackData(String.valueOf(textData.get(i).getSecond()))
      );

      if (i % numberOfColumns == 0) {
        rows.add(new LinkedList<>(columns));
        columns.clear();
      }
    }
    result.setKeyboard(rows);
    return result;
  }

  @Override
  public InlineKeyboardMarkup generateHorizontal(final List<Pair<?, ?>> textData) {
    final InlineKeyboardMarkup result = new InlineKeyboardMarkup();
    final List<List<InlineKeyboardButton>> rows = new LinkedList<>();
    final List<InlineKeyboardButton> columns = new LinkedList<>();
    for (int i = 0; i < textData.size(); i++) {
      columns.add(new InlineKeyboardButton().
              setText(String.valueOf(textData.get(i).getFirst())).
              setCallbackData(String.valueOf(textData.get(i).getSecond()))
      );
    }
    rows.add(columns);
    result.setKeyboard(rows);
    return result;
  }

  @Override
  public InlineKeyboardMarkup generateTextUrl(
          final List<Pair<?, ?>> textUrl,
          final int numberOfColumns) {
    final InlineKeyboardMarkup result = new InlineKeyboardMarkup();
    final List<List<InlineKeyboardButton>> rows = new LinkedList<>();
    final List<InlineKeyboardButton> columns = new LinkedList<>();
    for (int i = 0; i < textUrl.size(); i++) {
      columns.add(new InlineKeyboardButton().
              setText(String.valueOf(textUrl.get(i).getFirst())).
              setUrl(String.valueOf(textUrl.get(i).getSecond()))
      );

      if (i % numberOfColumns == 0) {
        rows.add(new LinkedList<>(columns));
        columns.clear();
      }
    }
    result.setKeyboard(rows);
    return result;
  }

  @Override
  public InlineKeyboardMarkup generateTextDataUrl(
          final List<Triplet<?, ?, ?>> textDataUrl,
          final int numberOfColumns) {
    final InlineKeyboardMarkup result = new InlineKeyboardMarkup();
    final List<List<InlineKeyboardButton>> rows = new LinkedList<>();
    final List<InlineKeyboardButton> columns = new LinkedList<>();
    for (int i = 0; i < textDataUrl.size(); i++) {
      columns.add(new InlineKeyboardButton().
              setText(String.valueOf(textDataUrl.get(i).getValue0())).
              setCallbackData(String.valueOf(textDataUrl.get(i).getValue1())).
              setUrl(String.valueOf(textDataUrl.get(i).getValue2()))
      );

      if (i % numberOfColumns == 0) {
        rows.add(new ArrayList<>(columns));
        columns.clear();
      }
    }
    result.setKeyboard(rows);
    return result;
  }
}



















