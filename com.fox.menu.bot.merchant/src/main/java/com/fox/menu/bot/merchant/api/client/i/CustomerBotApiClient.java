package com.fox.menu.bot.merchant.api.client.i;

import com.fox.menu.billing.protocol.response.GeneralResponse;
import com.fox.menu.bot.merchant.api.client.i.FatherApiClient;

public interface CustomerBotApiClient extends FatherApiClient {
    /*--------------------------------------------- place ----------------------------------------------------*/
    GeneralResponse confirmPlace(
            long userId,
            long placeId
    );
}
