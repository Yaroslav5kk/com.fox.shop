package com.fox.shop.notify.bot.repository;

import com.fox.shop.notify.bot.entity.UserInfoEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends ReactiveMongoRepository<UserInfoEntity, Long> {
}
