package com.fox.menu.bot.merchant.model.type;

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
    SET_PRIORITY(CommandData.START, UserDomainState.START);

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
    }}
