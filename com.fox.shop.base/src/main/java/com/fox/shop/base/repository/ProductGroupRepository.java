package com.fox.shop.base.repository;

import com.fox.shop.base.entity.ProductGroupEntity;
import com.fox.shop.protocol.type.ProductGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroupEntity, Long> {

    List<ProductGroupEntity> findAllByType(ProductGroupType type);
}
