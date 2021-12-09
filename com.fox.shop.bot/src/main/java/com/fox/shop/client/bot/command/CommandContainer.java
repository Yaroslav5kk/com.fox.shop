package com.fox.shop.client.bot.command;

import com.fox.shop.client.bot.command.menu.StartCommand;
import com.fox.shop.client.bot.command.process.SetUrlCommand;
import com.fox.shop.client.bot.command.process.SetValueCommand;
import org.springframework.stereotype.Component;

@Component
public class CommandContainer {
    private final StartCommand startCommand;
    private final SetValueCommand setValueCommand;
    private final SetUrlCommand setUrlCommand;

    public CommandContainer(
            final StartCommand startCommand,
            final SetValueCommand setValueCommand,
            final SetUrlCommand setUrlCommand
    ) {
        this.startCommand = startCommand;
        this.setValueCommand = setValueCommand;
        this.setUrlCommand = setUrlCommand;
    }

    public StartCommand getStartCommand() {
        return startCommand;
    }

    public SetValueCommand getSetValueCommand() {
        return setValueCommand;
    }

    public SetUrlCommand getSetUrlCommand() {
        return setUrlCommand;
    }
}
