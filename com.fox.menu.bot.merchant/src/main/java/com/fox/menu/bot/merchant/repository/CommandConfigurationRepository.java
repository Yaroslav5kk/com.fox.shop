package com.fox.menu.bot.merchant.repository;

import com.fox.menu.bot.merchant.entity.CommandConfigurationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandConfigurationRepository extends MongoRepository<CommandConfigurationEntity, String> {

}
