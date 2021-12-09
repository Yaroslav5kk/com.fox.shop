package com.fox.menu.bot.merchant.api.client.i;

import com.fox.menu.protocol.PlaceModel;

import java.util.List;

public interface BaseApiClient {
    /*--------------------------------------------- place ----------------------------------------------------*/
    List<PlaceModel> placesByIds(List<Long> ids);

    List<String> merchantPlacesByWaiterId(long waiterId);

    PlaceModel placeByIdOnMap(
            String idOnMap,
            long waiterId
    );
}
