package com.fox.shop.client.bot.repository;

import com.fox.shop.client.bot.entity.BannerEntity;
import com.fox.shop.client.bot.model.types.CommandData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends MongoRepository<BannerEntity, CommandData> {
}
