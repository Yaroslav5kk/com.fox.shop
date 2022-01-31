package com.fox.shop.client.bot;

import com.fox.shop.client.bot.command.StartCommand;
import com.fox.shop.client.bot.interceptor.i.FatherIncomingInterceptor;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public final class AnonymizerBot extends TelegramLongPollingCommandBot {

  private static final Logger LOG = LogManager.getLogger(AnonymizerBot.class);

  //@Value("${telegram.bot.name}")
  private final static String botName = "dev_smart_collector_bot";
  private final List<FatherIncomingInterceptor> interceptors;
  @Value("${telegram.bot.token}")
  private String botToken;


  public AnonymizerBot(
          final DefaultBotOptions botOptions,
          final StartCommand startCommand,
          final List<FatherIncomingInterceptor> interceptors
  ) {
    super(botOptions);
    this.interceptors = interceptors;
    register(startCommand);
    handleNotCommand();
  }

  // обработка сообщения не начинающегося с '/'
  @Override
  public void processNonCommandUpdate(final Update update) {
    interceptors.forEach(fatherIncomingInterceptor -> fatherIncomingInterceptor.interapt(TgIncomingCommandModel.of(update)));
  }

  @Override
  public void onUpdatesReceived(List<Update> updates) {
    updates.forEach(this::processNonCommandUpdate);
  }

  @Override
  public String getBotToken() {
    return botToken;
  }

  @Override
  public String getBotUsername() {
    return botName;
  }


  private void handleNotCommand() {
    registerDefaultAction(((absSender, message) -> {
      SendMessage text = new SendMessage();
      text.setChatId(String.valueOf(message.getChatId()));
      text.setText(message.getText() + " command not found!");

    }));
  }
}
