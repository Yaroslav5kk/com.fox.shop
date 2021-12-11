package com.fox.shop.ordering.api.client.i;

import com.fox.protocol.user.UserModel;
import com.fox.shop.protocol.response.GeneralResponse;

public interface BaseApiClient {

    /*--------------------------------------------- users ----------------------------------------------------*/
    UserModel getUserById(long id);

  /*--------------------------------------------- users ----------------------------------------------------*/
  GeneralResponse<Long> getMerchantIdByProductId(long productId);
}
