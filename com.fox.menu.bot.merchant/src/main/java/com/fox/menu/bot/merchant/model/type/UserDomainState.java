package com.fox.menu.bot.merchant.model.type;

public enum UserDomainState {
    START(CommandData.START),
    FINISH(CommandData.FINISH),
    RESET(CommandData.RESET),
    BACK(CommandData.BACK),
    CONFIRM_AVAILABLE_PLACE(CommandData.CONFIRM_AVAILABLE_PLACE),
    EMPTY_AVAILABLE_PLACE(CommandData.EMPTY_AVAILABLE_PLACE);

    private final CommandData command;

    UserDomainState(final CommandData command) {
        this.command = command;
    }

    public CommandData getCommand() {
        return command;
    }

    public static UserDomainState fromCommand(final CommandData command) {
        for (var itState : values()) {
            if (itState.getCommand().equals(command))
                return itState;
        }
        return START;
    }

    public static boolean isExists(final CommandData command) {
        for (var itState : values()) {
            if (itState.getCommand().equals(command)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMain(final CommandData command) {
        if (!isExists(command))
            return false;
        return true;
    }
}
