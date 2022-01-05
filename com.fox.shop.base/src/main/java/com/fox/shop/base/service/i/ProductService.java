package com.fox.shop.base.service.i;

import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.request.ProductOnCreateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
  ProductModel save(ProductOnCreateRequest request);

  ProductModel get(long id);

  Page<ProductModel> allByGroup(long groupId, Pageable pageable);

  Page<ProductModel> byIds(List<Long> ids, Pageable pageable);

  Page<ProductModel> searchByNameMatch(String toSearch, Pageable pageable);
}
