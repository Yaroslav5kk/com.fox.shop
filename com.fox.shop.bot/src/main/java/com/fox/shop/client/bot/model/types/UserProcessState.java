package com.fox.shop.client.bot.model.types;

import java.util.Arrays;
import java.util.List;

public enum UserProcessState {
    FREE(CommandData.START, UserDomainState.START),
    FINISH(CommandData.START, UserDomainState.START),
    SET_ID(CommandData.START, UserDomainState.START),
    SET_VALUE(CommandData.START, UserDomainState.START),
    SET_DESCRIPTION(CommandData.START, UserDomainState.START),
    SET_IMAGE(CommandData.START, UserDomainState.START),
    SET_URL(CommandData.START, UserDomainState.START),
    SET_PRIORITY(CommandData.START, UserDomainState.START),
    ENTER_OTHER_VALUE(CommandData.ENTER_OTHER_VALUE, UserDomainState.ADD_TO_CART),
    PLUS_ONE(CommandData.CLEAR_CART_SESSION, UserDomainState.EDIT_CART_SESSION),
    MINUS_ONE(CommandData.MINUS_ONE, UserDomainState.EDIT_CART_SESSION),
    DELETE_CART_ITEM(CommandData.DELETE_CART_ITEM, UserDomainState.EDIT_CART_SESSION);

    CommandData command;
    List<UserDomainState> fatherDomains;

    UserProcessState(
            final CommandData commandData,
            final UserDomainState... fatherDomainState
    ) {
        this.command = commandData;
        this.fatherDomains = Arrays.asList(fatherDomainState);
    }

    public static UserProcessState fromCommand(final String commandValue) {
        for (var itState : values()) {
            if (itState.getCommand().getValue().equals(commandValue)) {
                return itState;
            }
        }
        return UserProcessState.FREE;
    }

    public static boolean isContainsCommandValue(final String commandValue) {
        for (var itState : values()) {
            if (itState.getCommand().getValue().equals(commandValue)) {
                return true;
            }
        }
        return false;
    }

    public CommandData getCommand() {
        return command;
    }

    public List<UserDomainState> getFatherDomains() {
        return fatherDomains;
    }
}
