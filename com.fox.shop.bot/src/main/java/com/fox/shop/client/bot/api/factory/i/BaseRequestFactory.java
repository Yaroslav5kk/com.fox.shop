package com.fox.shop.client.bot.api.factory.i;

import com.fox.protocol.user.UserModel;
import com.fox.shop.protocol.type.ProductGroupType;
import org.apache.http.client.methods.HttpUriRequest;

import java.util.List;

public interface BaseRequestFactory {

    HttpUriRequest categoryByMenu(long menuId);

    HttpUriRequest productByCategory(long categoryId);

    HttpUriRequest productByIds(List<Long> ids);

    HttpUriRequest mainImageByteByCategory(long categoryId);

    HttpUriRequest searchProductsByName(String name);

    HttpUriRequest mainImageByteByProduct(long productId);

    HttpUriRequest allProductGroups(ProductGroupType type);

    HttpUriRequest productsByGroup(long groupId);

    HttpUriRequest downloadImageByteById(long id);

    /*----------------------------------------------users-------------------------------------------------*/
    HttpUriRequest saveUser(UserModel request);

    /*----------------------------------------------delivery-------------------------------------------------*/
    HttpUriRequest getAllDelivery();
}
