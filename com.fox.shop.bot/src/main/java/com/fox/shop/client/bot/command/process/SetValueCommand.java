package com.fox.shop.client.bot.command.process;

import com.fox.shop.client.bot.context.i.UserDomainStateContext;
import com.fox.shop.client.bot.context.i.UserHistoryContext;
import com.fox.shop.client.bot.context.i.UserProcessStateContext;
import com.fox.shop.client.bot.ui.generate.i.CommonMessageGenerator;
import com.fox.shop.client.bot.command.AnonymizerCommand;
import com.fox.shop.client.bot.model.types.CommandData;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class SetValueCommand extends AnonymizerCommand {
    private final UserProcessStateContext userProcessStateContext;
    private final CommonMessageGenerator commonMessageGenerator;

    public SetValueCommand(
            final UserDomainStateContext userDomainStateContext,
            final UserProcessStateContext userProcessStateContext,
            final CommonMessageGenerator commonMessageGenerator,
            final UserHistoryContext userHistoryContext
    ) {
        super(CommandData.SET_VALUE.getValue(), CommandData.SET_VALUE.getDescription(), userDomainStateContext, userHistoryContext);
        this.userProcessStateContext = userProcessStateContext;
        this.commonMessageGenerator = commonMessageGenerator;
    }

    @Override
    public void execute(
            final AbsSender absSender,
            final User user,
            final Chat chat,
            final String[] strings
    ) {
        userProcessStateContext.setValue(user.getId());
        execute(absSender, commonMessageGenerator.value(chat.getId()), user);
    }
}
