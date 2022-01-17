package com.fox.shop.base.service.i;

import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.request.ProductOnCreateRequest;
import com.fox.shop.protocol.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
  ProductModel save(ProductOnCreateRequest request);

  ProductModel get(long id);

  PageResponse<ProductModel> allByGroup(long groupId, Pageable pageable);

  List<ProductModel> byIds(List<Long> ids);

  PageResponse<ProductModel> searchByNameMatch(String toSearch, Pageable pageable);
}
