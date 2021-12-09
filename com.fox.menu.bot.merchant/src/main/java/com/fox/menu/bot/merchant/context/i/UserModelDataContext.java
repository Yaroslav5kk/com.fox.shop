package com.fox.menu.bot.merchant.context.i;

import com.fox.menu.bot.merchant.context.model.CheckAvailablePlaceStateModel;

public interface UserModelDataContext {
    void checkAvailablePlaceState(
            Long userId,
            CheckAvailablePlaceStateModel checkAvailablePlaceState
    );

    CheckAvailablePlaceStateModel getCheckAvailablePlaceState(Long userId);
}
