package com.fox.shop.client.bot.api.factory.i;

import com.fox.protocol.user.UserModel;
import com.fox.shop.protocol.type.ProductGroupType;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseRequestFactory {

    HttpUriRequest productsByIds(List<Long> ids);

    HttpUriRequest searchProductsByName(String name);

    HttpUriRequest allProductGroups(ProductGroupType type);

    HttpUriRequest productById(long id);

    HttpUriRequest productsByGroup(long groupId, Pageable pageable);

    /*----------------------------------------------users-------------------------------------------------*/
    HttpUriRequest saveUser(UserModel request);

    /*----------------------------------------------delivery-------------------------------------------------*/
    HttpUriRequest getAllDelivery();
}
