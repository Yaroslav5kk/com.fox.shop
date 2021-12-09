package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.service.i.CommandConfigurationService;
import com.fox.shop.client.bot.ui.generate.i.PrePostCommandHandleMessageGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.PrePostCommandHandleKeyboardGenerator;
import org.springframework.stereotype.Component;

@Component
public class PrePostCommandHandleMessageGeneratorImpl implements PrePostCommandHandleMessageGenerator {

    private final CommandConfigurationService commandConfigurationService;
    private final PrePostCommandHandleKeyboardGenerator prePostCommandHandleKeyboardGenerator;

    public PrePostCommandHandleMessageGeneratorImpl(
            final CommandConfigurationService commandConfigurationService,
            final PrePostCommandHandleKeyboardGenerator prePostCommandHandleKeyboardGenerator
    ) {
        this.commandConfigurationService = commandConfigurationService;
        this.prePostCommandHandleKeyboardGenerator = prePostCommandHandleKeyboardGenerator;
    }

    @Override
    public SendPhotoFileIdRequest preHandle(
            final long chatId,
            final int userId,
            final String command
    ) {
        final SendPhotoFileIdRequest result = new SendPhotoFileIdRequest();
        result.setChatId(chatId);
        result.setParseMode("HTML");
        result.setPhoto(commandConfigurationService.get(command).getPreHandleFileIdOnTelegram());
        result.setCaption(commandConfigurationService.get(command).getPreHandleMessageText());
        return result;
    }

    @Override
    public SendPhotoFileIdRequest postHandle(
            final long chatId,
            final int userId,
            final String command
    ) {
        final SendPhotoFileIdRequest result = new SendPhotoFileIdRequest();
        result.setChatId(chatId);
        result.setParseMode("HTML");
        result.setReplyMarkup(prePostCommandHandleKeyboardGenerator.postHandleStart(userId));
        result.setPhoto(commandConfigurationService.get(command).getPostHandleFileIdOnTelegram());
        result.setCaption(commandConfigurationService.get(command).getPostHandleMessageText());
        return result;
    }
}
















