package com.fox.shop.client.bot.command.menu;

import com.fox.shop.client.bot.command.AnonymizerCommand;
import com.fox.shop.client.bot.context.i.UserDomainStateContext;
import com.fox.shop.client.bot.context.i.UserHistoryContext;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.service.i.UserService;
import com.fox.shop.client.bot.ui.scenarios.i.StartScenariosMenu;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class StartCommand extends AnonymizerCommand {

    private final UserService userService;
    private final StartScenariosMenu startScenariosMenu;
    private final UserHistoryContext userHistoryContext;

    public StartCommand(
            final UserDomainStateContext userDomainStateContext,
            final UserService userService,
            final StartScenariosMenu startScenariosMenu,
            final UserHistoryContext userHistoryContext
    ) {
        super(CommandData.START.getValue(), CommandData.START.getDescription(), userDomainStateContext, userHistoryContext);
        this.userService = userService;
        this.startScenariosMenu = startScenariosMenu;
        this.userHistoryContext = userHistoryContext;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        startScenariosMenu.base(chat.getId(), user);
        userHistoryContext.snapshot(user.getId(), CommandData.START.getValue());
    }
}
