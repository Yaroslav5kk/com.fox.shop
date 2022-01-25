package com.fox.shop.client.bot.command.process;

import com.fox.shop.client.bot.command.AnonymizerCommand;
import com.fox.shop.client.bot.context.i.UserHistoryContext;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.generate.i.CommonMessageGenerator;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class SetUrlCommand extends AnonymizerCommand {
    private final UserProcessStateContext userProcessStateContext;
    private final CommonMessageGenerator commonMessageGenerator;

    SetUrlCommand(
            final UserDomainStateContext userDomainStateContext,
            final UserProcessStateContext userProcessStateContext,
            final CommonMessageGenerator commonMessageGenerator,
            final UserHistoryContext userHistoryContext
    ) {
        super(CommandData.SET_URL.getValue(), CommandData.SET_URL.getDescription(), userDomainStateContext, userHistoryContext);
        this.userProcessStateContext = userProcessStateContext;
        this.commonMessageGenerator = commonMessageGenerator;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        userProcessStateContext.setDescription(user.getId());
        execute(absSender, commonMessageGenerator.description(chat.getId()), user);
    }
}
