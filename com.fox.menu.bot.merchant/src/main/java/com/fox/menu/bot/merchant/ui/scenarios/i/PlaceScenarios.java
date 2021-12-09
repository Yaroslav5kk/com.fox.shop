package com.fox.menu.bot.merchant.ui.scenarios.i;

import java.util.List;

public interface PlaceScenarios {
    void checkOfAvailableTitle(
            long customerId,
            long waiterId,
            List<Long> placeIds
    );

    void confirmAvailable(
            long chatId,
            long waiterId,
            long placeId
    );

    void emptyAvailablePlaceTitle(
            long chatId,
            long waiterId
    );

    void emptyAvailablePlaceHandle(
            long chatId,
            long waiterId,
            String placeIdOnMap
    );
}
