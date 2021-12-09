package com.fox.shop.client.bot.ui.generate.keyboard.inline;

import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.generate.i.InlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ProductIKeyboardGenerator;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductIKeyboardGeneratorImpl implements ProductIKeyboardGenerator {

  private final InlineKeyboardGenerator inlineKeyboardGenerator;

  public ProductIKeyboardGeneratorImpl(
      final InlineKeyboardGenerator inlineKeyboardGenerator
  ) {
    this.inlineKeyboardGenerator = inlineKeyboardGenerator;
  }

  @Override
  public InlineKeyboardMarkup beginBack() {
    final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
    textDataToKeyboard.add(Pair.of(
        CommandData.BACK.getDescription(),
        CommandData.BACK.getValue()
    ));
    textDataToKeyboard.add(Pair.of(
        CommandData.START.getDescription(),
        CommandData.START.getValue()
    ));
    return inlineKeyboardGenerator.generate(textDataToKeyboard, 1);
  }

  @Override
  public InlineKeyboardMarkup afterAllProduct(final long userId) {
    final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
    textDataToKeyboard.add(Pair.of(
        CommandData.GET_CART_SESSION.getDescription(),
        CommandData.GET_CART_SESSION.getValue() + " " + userId
    ));
    textDataToKeyboard.add(Pair.of(
        CommandData.BACK.getDescription(),
        CommandData.BACK.getValue()
    ));
    textDataToKeyboard.add(Pair.of(
        CommandData.START.getDescription(),
        CommandData.START.getValue()
    ));
    return inlineKeyboardGenerator.generate(textDataToKeyboard, 1);
  }

  @Override
  public InlineKeyboardMarkup product(final long productId) {
    final List<Pair<?, ?>> textDataToKeyboard = new ArrayList<>();
    textDataToKeyboard.add(Pair.of(
        CommandData.ADD_TO_CART.getDescription(),
        CommandData.ADD_TO_CART.getValue() + " " + productId
    ));
    textDataToKeyboard.add(Pair.of(
        CommandData.VIEW_PRODUCT_DESCRIPTION.getDescription(),
        CommandData.VIEW_PRODUCT_DESCRIPTION.getValue() + " " + productId
    ));
    return inlineKeyboardGenerator.generate(textDataToKeyboard, 1);
  }
}
