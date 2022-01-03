package com.fox.shop.base.converter;

import com.fox.shop.base.entity.ProductEntity;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.request.ProductOnCreateRequest;

public class ProductConverter {

  public static ProductEntity fromRequestOnCreateToEntity(final ProductOnCreateRequest request) {
    final ProductEntity result = new ProductEntity();
    result.setName(request.getName());
    result.setDescription(request.getDescription());
    result.setMainImageStorageId(result.getMainImageStorageId());
    return result;
  }

  public static ProductModel fromEntity(final ProductEntity entity) {
    final ProductModel result = new ProductModel();
    result.setId(entity.getId());
    result.setName(entity.getName());
    result.setDescription(entity.getDescription());
    result.setMainImageStorageId(entity.getMainImageStorageId());
    if (entity.getMainImage() != null)
      result.setMainImage(ImageConverter.fromEntity(entity.getMainImage()));
    return result;
  }

  public static ProductEntity toEntity(final ProductModel model) {
    final ProductEntity result = new ProductEntity();
    result.setId(model.getId());
    result.setName(model.getName());
    result.setDescription(model.getDescription());
    result.setMainImageStorageId(model.getMainImageStorageId());
    return result;
  }
}
