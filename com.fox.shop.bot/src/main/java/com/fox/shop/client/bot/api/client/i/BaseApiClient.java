package com.fox.shop.client.bot.api.client.i;

import com.fox.protocol.user.UserModel;
import com.fox.shop.protocol.CategoryModel;
import com.fox.shop.protocol.DeliveryModel;
import com.fox.shop.protocol.ProductGroupModel;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.type.ProductGroupType;

import java.util.List;

public interface BaseApiClient {

    List<CategoryModel> categoryByMenu(long menuId);

    List<ProductModel> productsByGroup(long groupId);

    List<ProductModel> searchProductsByName(String name);

    List<ProductModel> productByCategory(long categoryId);

    List<ProductModel> productByIds(List<Long> ids);

    List<ProductGroupModel> allProductGroups(ProductGroupType type);

    String mainImageByteByCategory(long categoryId);

    String mainImageByteByProduct(long productId);

    /*--------------------------------------------- image ----------------------------------------------------*/
    String downloadImageByteById(long imageId);

    /*--------------------------------------------- users ----------------------------------------------------*/
    UserModel saveUser(UserModel userModel);

    /*--------------------------------------------- delivery ----------------------------------------------------*/
    List<DeliveryModel> getALlDelivery();
}
