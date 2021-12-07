package com.fox.shop.price.repository;

import com.fox.shop.price.entity.DiscountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends MongoRepository<DiscountEntity, Long> {
}
