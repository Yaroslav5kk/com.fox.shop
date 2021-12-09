package com.fox.shop.base.service.i;

import com.fox.shop.protocol.ProductGroupModel;
import com.fox.shop.protocol.request.GroupOnCreateRequest;
import com.fox.shop.protocol.type.ProductGroupType;

import java.util.List;

public interface GroupsService {

    /*------------------------------------------product----------------------------------------------------------*/
    ProductGroupModel addProductToGroup(
            long productId,
            long groupId
    );

    ProductGroupModel saveProductGroup(GroupOnCreateRequest request);

    List<ProductGroupModel> findAllProductGroup();

    List<ProductGroupModel> findAllProductGroupByType(ProductGroupType type);
}
