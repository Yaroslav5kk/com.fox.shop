package com.fox.menu.bot.merchant.repository;

import com.fox.menu.bot.merchant.entity.WaiterInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaiterInfoRepository extends MongoRepository<WaiterInfoEntity, Long> {
}
