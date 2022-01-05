package com.fox.shop.base.repository;

import com.fox.shop.base.entity.ProductEntity;
import com.fox.shop.base.entity.ProductGroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

  Page<ProductEntity> findByNameContaining(String name, Pageable pageable);

  Page<ProductEntity> findAllByGroupsIsIn(ProductGroupEntity group, Pageable pageable);

  Page<ProductEntity> findAllByIdIsInAndProductBalanceGreaterThan(List<Long> ids, Pageable pageable);

  Page<ProductEntity> findAllByIdIsIn(List<Long> ids, Pageable pageable);
}
