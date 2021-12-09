package com.fox.menu.bot.merchant.api.client.i;

import com.fox.menu.billing.protocol.response.GeneralResponse;

public interface BillingApiClient {
    /*--------------------------------------------- place ----------------------------------------------------*/
    GeneralResponse confirmPlace(
            long userId,
            long placeId
    );
}
