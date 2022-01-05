package com.fox.shop.base.entity.specefication;

import com.fox.shop.base.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProductSpecificationFactory {

  Specification<ProductEntity> byGroupId(
      long groupId
  );

  Specification<ProductEntity> byIds(
      List<Long> productIds
  );

  Specification<ProductEntity> productBalanceGreaterThan(
      short balance
  );

  Specification<ProductEntity> productDescriptionILike(
      String description
  );

  Specification<ProductEntity> productNameILike(
      String name
  );
}
