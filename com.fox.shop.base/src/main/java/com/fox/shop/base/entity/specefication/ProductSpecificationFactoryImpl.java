package com.fox.shop.base.entity.specefication;

import com.fox.shop.base.entity.ProductBalanceEntity_;
import com.fox.shop.base.entity.ProductEntity;
import com.fox.shop.base.entity.ProductEntity_;
import com.fox.shop.base.entity.ProductGroupEntity_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSpecificationFactoryImpl implements ProductSpecificationFactory {

  @Override
  public Specification<ProductEntity> byGroupId(
      final long groupId
  ) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.in(root.get(ProductEntity_.GROUPS).get(ProductGroupEntity_.ID)).value(groupId);
  }

  @Override
  public Specification<ProductEntity> byIds(
      final List<Long> productIds
  ) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.in(root.get(ProductEntity_.ID)).value(productIds);
  }

  @Override
  public Specification<ProductEntity> productBalanceGreaterThan(
      final short balance
  ) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(ProductEntity_.PRODUCT_BALANCE).get(ProductBalanceEntity_.BALANCE), balance);
  }

  @Override
  public Specification<ProductEntity> productDescriptionILike(
      final String description
  ) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(ProductEntity_.DESCRIPTION)), "%" + description.toLowerCase() + "%");
  }

  @Override
  public Specification<ProductEntity> productNameILike(
      final String name
  ) {
    return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(ProductEntity_.NAME)), "%" + name.toLowerCase() + "%");
  }

}
