package com.fox.menu.bot.merchant.model;

import com.fox.menu.bot.merchant.model.type.UserDomainState;
import com.fox.menu.bot.merchant.model.type.UserProcessState;

public class UserHistoryStateModel {
    private int userId;
    private String command;
    private UserDomainState domainState;
    private UserProcessState processState;

    public UserHistoryStateModel(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserDomainState getDomainState() {
        return domainState;
    }

    public void setDomainState(UserDomainState domainState) {
        this.domainState = domainState;
    }

    public UserProcessState getProcessState() {
        return processState;
    }

    public void setProcessState(UserProcessState processState) {
        this.processState = processState;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
