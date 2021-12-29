package com.fox.shop.client.bot.utils.extractor;

import org.telegram.telegrambots.meta.api.objects.*;

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
    return update.getMessage().getMessageId();
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

  public static String command(final Update update) {
    return enteredText(update).split(" ")[0];
  }

  public static String firstParameter(final Update update) {
    return enteredText(update).split(" ")[1];
  }
}
