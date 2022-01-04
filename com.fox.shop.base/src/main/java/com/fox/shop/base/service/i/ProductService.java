package com.fox.shop.base.service.i;

import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.request.ProductOnCreateRequest;

import java.util.List;

public interface ProductService {
    ProductModel save(ProductOnCreateRequest request);

    ProductModel get(long id);

    List<ProductModel> allByGroup(long groupId);

    List<ProductModel> byIds(List<Long> ids);

    List<ProductModel> searchByNameMatch(String toSearch);
}
