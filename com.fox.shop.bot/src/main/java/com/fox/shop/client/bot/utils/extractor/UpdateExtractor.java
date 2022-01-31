package com.fox.shop.client.bot.utils.extractor;

import com.fox.shop.client.bot.model.types.CommandData;
import org.telegram.telegrambots.meta.api.objects.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UpdateExtractor {

  public static Location location(final Update update) {
    return message(update).getLocation();
  }

  public static Optional<String> phone(final Update update) {
    if (message(update).getContact() == null)
      return Optional.empty();
    return Optional.ofNullable(message(update).getContact().getPhoneNumber());
  }

  public static long chatId(final Update update) {
    return chat(update).getId();
  }

  public static int userId(final Update update) {
    return user(update).getId();
  }

  public static String userName(final Update update) {
    return user(update).getUserName();
  }

  public static User user(final Update update) {
    return update.hasCallbackQuery()
        ? callbackQuery(update).getFrom()
        : message(update).getFrom();
  }

  public static Chat chat(final Update update) {
    return
        update.hasCallbackQuery()
            ? callbackQuery(update).getMessage().getChat()
            : message(update).getChat();
  }

  public static Message message(final Update update) {
    return update.getMessage();
  }

  public static long messageId(final Update update) {
    return
        update.hasCallbackQuery()
            ? callbackQuery(update).getMessage().getMessageId()
            : message(update).getMessageId();
  }

  public static long callBackQueryMessageId(final Update update) {
    return update.getCallbackQuery().getMessage().getMessageId();
  }


  public static String enteredText(final Update update) {
    return update.hasCallbackQuery()
        ? callbackQueryData(update)
        : messageText(update);
  }

  public static String callbackQueryData(final Update update) {
    return callbackQuery(update).getData();
  }

  public static String messageText(final Update update) {
    return message(update).getText();
  }

  public static CallbackQuery callbackQuery(final Update update) {
    return update.getCallbackQuery();
  }

  public static Optional<CommandData> command(final Update update) {
    final String command = enteredText(update).split(" ")[0];
    if (CommandData.contains(command))
      return Optional.of(CommandData.fromValue(command));
    return Optional.empty();
  }

  public static List<String> params(final Update update) {
    final String[] splited = enteredText(update).split(" ");
    if (splited == null || splited.length < 2)
      return Collections.emptyList();
    return Arrays.asList(Arrays.copyOfRange(splited, 1, splited.length ));
  }

  public static String firstParameter(final Update update) {
    return enteredText(update).split(" ")[1];
  }
}



















