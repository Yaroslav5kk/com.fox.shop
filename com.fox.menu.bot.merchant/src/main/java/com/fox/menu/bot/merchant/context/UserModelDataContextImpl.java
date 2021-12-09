package com.fox.menu.bot.merchant.context;

import com.fox.menu.bot.merchant.context.i.UserModelDataContext;
import com.fox.menu.bot.merchant.context.model.CheckAvailablePlaceStateModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserModelDataContextImpl implements UserModelDataContext {
    private final Map<Long, CheckAvailablePlaceStateModel> userIdCheckAvailablePlaceState;

    public UserModelDataContextImpl() {
        userIdCheckAvailablePlaceState = new HashMap<>();
    }

    @Override
    public void checkAvailablePlaceState(
            final Long userId,
            final CheckAvailablePlaceStateModel checkAvailablePlaceState
    ) {
        userIdCheckAvailablePlaceState.put(userId, checkAvailablePlaceState);
    }

    @Override
    public CheckAvailablePlaceStateModel getCheckAvailablePlaceState(final Long userId) {
        if (!userIdCheckAvailablePlaceState.containsKey(userId))
            userIdCheckAvailablePlaceState.put(userId, new CheckAvailablePlaceStateModel());
        return userIdCheckAvailablePlaceState.get(userId);
    }
}
