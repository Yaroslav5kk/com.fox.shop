package com.fox.shop.client.bot;

import com.fox.shop.client.bot.command.CommandContainer;
import com.fox.shop.client.bot.service.i.AnswerCallBackQuerySelector;
import com.fox.shop.client.bot.service.i.AnswerHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public final class AnonymizerBot extends TelegramLongPollingCommandBot {

    private static final Logger LOG = LogManager.getLogger(AnonymizerBot.class);

    //@Value("${telegram.bot.name}")
    private final static String botName = "dev_smart_collector_bot";
    @Value("${telegram.bot.token}")
    private String botToken;
    private final AnswerCallBackQuerySelector callBackQuerySelector;
    private final AnswerHandler answerHandler;


    public AnonymizerBot(
            final DefaultBotOptions botOptions,
            final CommandContainer commandContainer,
            final AnswerCallBackQuerySelector callBackQuerySelector,
            final AnswerHandler answerHandler
    ) {
        super(botOptions);
        this.callBackQuerySelector = callBackQuerySelector;
        this.answerHandler = answerHandler;
        registerCommand(commandContainer);

        handleNotCommand();
    }

    // обработка сообщения не начинающегося с '/'
    @Override
    public void processNonCommandUpdate(final Update update) {
        if (update.hasCallbackQuery())
            callBackQuerySelector.select(update);
        else if (update.hasMessage())
            answerHandler.handle(update);
    }


    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    private void registerCommand(final CommandContainer commandContainer) {
        register(commandContainer.getStartCommand());
    }

    private void handleNotCommand() {
        registerDefaultAction(((absSender, message) -> {
            SendMessage text = new SendMessage();
            text.setChatId(message.getChatId());
            text.setText(message.getText() + " command not found!");

        }));
    }
}
