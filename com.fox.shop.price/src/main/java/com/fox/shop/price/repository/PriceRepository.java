package com.fox.shop.price.repository;

import com.fox.shop.price.entity.PriceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends MongoRepository<PriceEntity, Long> {

    PriceEntity getByProductId(long productId);

    List<PriceEntity> getAllByProductIdIsIn(List<Long> productIds);
}
