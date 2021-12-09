package com.fox.shop.client.bot.repository;

import com.fox.shop.client.bot.entity.CommandConfigurationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandConfigurationRepository extends MongoRepository<CommandConfigurationEntity, String> {

}
