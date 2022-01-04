package com.fox.shop.base.converter;

import com.fox.shop.base.entity.ProductGroupEntity;
import com.fox.shop.protocol.ProductGroupModel;
import com.fox.shop.protocol.request.GroupOnCreateRequest;

public class ProductGroupConverter {

  public static ProductGroupEntity fromRequestToEntity(final GroupOnCreateRequest request) {
    final ProductGroupEntity result = new ProductGroupEntity();
    result.setName(request.getName());
    result.setDescription(request.getDescription());
    result.setType(request.getType());
    result.setMainImageStorageId(result.getMainImageStorageId());
    return result;
  }

  public static ProductGroupModel fromEntity(final ProductGroupEntity entity) {
    final ProductGroupModel result = new ProductGroupModel();
    result.setId(entity.getId());
    result.setName(entity.getName());
    result.setDescription(entity.getDescription());
    result.setMainImageStorageId(entity.getMainImageStorageId());
    result.setType(entity.getType());
    return result;
  }

  public static ProductGroupEntity toEntity(final ProductGroupModel model) {
    final ProductGroupEntity result = new ProductGroupEntity();
    result.setName(model.getName());
    result.setDescription(model.getDescription());
    result.setMainImageStorageId(model.getMainImageStorageId());
    result.setType(model.getType());
    return result;
  }

}
