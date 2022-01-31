package com.fox.shop.client.bot.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class AnonymizerCommand extends BotCommand {
    protected final Logger log = LogManager.getLogger(getClass());

    public AnonymizerCommand(
            String commandIdentifier,
            String description
    ) {
        super(commandIdentifier, description);
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] arguments) {
        this.execute(absSender, message.getFrom(), message.getChat(), arguments);
    }


    protected void execute(AbsSender sender, SendMessage message, User user) {
        try {
            sender.execute(message);
            //log.log(Level.getLevel(LogLevel.SUCCESS.getValue()), LogTemplate.COMMAND_SUCCESS.getTemplate(), user.getId(), getCommandIdentifier());
        } catch (TelegramApiException e) {
            //log.error(LogTemplate.COMMAND_EXCEPTION.getTemplate(), user.getId(), getCommandIdentifier(), e);
        }
    }
}
