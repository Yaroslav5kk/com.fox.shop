package com.fox.shop.ordering.api.client.i;

import com.fox.protocol.user.UserModel;
import com.fox.shop.protocol.MerchantModel;

public interface BaseApiClient {

    /*--------------------------------------------- users ----------------------------------------------------*/
    UserModel getUserById(long id);

  /*--------------------------------------------- users ----------------------------------------------------*/
  MerchantModel getMerchantByProductId(long productId);
}
