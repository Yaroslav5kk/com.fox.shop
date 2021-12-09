package com.fox.menu.bot.merchant.api.factory.i;

import org.apache.http.client.methods.HttpUriRequest;

import java.util.List;

public interface BaseRequestFactory {
    /*----------------------------------------------places-------------------------------------------------*/
    HttpUriRequest merchantPlacesByWaiterId(
            long waiterId
    );

    HttpUriRequest placeByIdOnMap(
            String idOnMap,
            long merchantId
    );

    /*----------------------------------------------places-------------------------------------------------*/
    HttpUriRequest placesByIds(
            List<Long> ids
    );
}
