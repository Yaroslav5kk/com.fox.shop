package com.fox.shop.client.bot.api.client.i;

import com.fox.protocol.user.UserModel;
import com.fox.shop.protocol.DeliveryModel;
import com.fox.shop.protocol.ProductGroupModel;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.type.ProductGroupType;

import java.util.List;

public interface BaseApiClient {

  List<ProductModel> productsByGroup(long groupId);

  List<ProductModel> searchProductsByName(String name);

  List<ProductModel> productsByIds(List<Long> ids);

  ProductModel productById(long id);

  List<ProductGroupModel> allProductGroups(ProductGroupType type);

  /*--------------------------------------------- users ----------------------------------------------------*/
  UserModel saveUser(UserModel userModel);

  /*--------------------------------------------- delivery ----------------------------------------------------*/
  List<DeliveryModel> getALlDelivery();
}
