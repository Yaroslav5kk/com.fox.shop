package com.fox.shop.ordering.api.client.i;

import com.fox.protocol.user.UserModel;

public interface BaseApiClient {

    /*--------------------------------------------- users ----------------------------------------------------*/
    UserModel getUserById(long id);
}
