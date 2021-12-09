package com.fox.shop.client.bot.config;

import com.fox.shop.client.bot.AnonymizerBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.facilities.filedownloader.TelegramFileDownloader;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Configuration
public class BotConfiguration {

    @Value("${telegram.bot.token}")
    private String botToken;


    @Bean
    TelegramBotsApi telegramBotsApi(final AnonymizerBot anonymizerBot) {
        final TelegramBotsApi result = new TelegramBotsApi();

        try {
            result.registerBot(anonymizerBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Bean
    DefaultBotOptions defaultBotOptions() {
        return ApiContext.getInstance(DefaultBotOptions.class);
    }

    @Bean
    TelegramFileDownloader telegramFileDownloader() {
        return new TelegramFileDownloader(botToken::toString);
    }

}
