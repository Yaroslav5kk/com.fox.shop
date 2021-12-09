package com.fox.menu.bot.merchant.context;

import com.fox.menu.bot.merchant.context.i.UserDomainStateContext;
import com.fox.menu.bot.merchant.model.type.UserDomainState;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserDomainStateContextImpl implements UserDomainStateContext {
    private final Map<Integer, UserDomainState> userDomainStateMap;

    public UserDomainStateContextImpl() {
        userDomainStateMap = new HashMap<>();
    }

    @Override
    public void start(final Integer userId) {
        this.userDomainStateMap.put(userId, UserDomainState.START);
    }

    @Override
    public void finish(final Integer userId) {
        this.userDomainStateMap.put(userId, UserDomainState.FINISH);
    }

    @Override
    public void confirmAvailablePlace(final Integer userId) {
        this.userDomainStateMap.put(userId, UserDomainState.CONFIRM_AVAILABLE_PLACE);
    }

    @Override
    public void emptyAvailablePlace(final Integer userId) {
        this.userDomainStateMap.put(userId, UserDomainState.EMPTY_AVAILABLE_PLACE);
    }

    @Override
    public UserDomainState current(final Integer userId) {
        if (userDomainStateMap.containsKey(userId))
            return userDomainStateMap.get(userId);
        userDomainStateMap.put(userId, UserDomainState.START);
        return UserDomainState.START;
    }

    @Override
    public void put(final Integer userId, final UserDomainState domainState) {
        userDomainStateMap.put(userId, domainState);
    }


}
