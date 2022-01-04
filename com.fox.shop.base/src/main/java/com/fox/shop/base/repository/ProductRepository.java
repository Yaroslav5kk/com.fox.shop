package com.fox.shop.base.repository;

import com.fox.shop.base.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
  List<ProductEntity> findAllByCategory(CategoryEntity category);

  List<ProductEntity> findByNameContaining(String name);
}
