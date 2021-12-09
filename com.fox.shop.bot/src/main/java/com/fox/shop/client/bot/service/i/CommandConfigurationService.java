package com.fox.shop.client.bot.service.i;

import com.fox.shop.client.bot.entity.CommandConfigurationEntity;

public interface CommandConfigurationService {
  CommandConfigurationEntity save(CommandConfigurationEntity commandConfigurationEntity);

  CommandConfigurationEntity get(String command);

    boolean existsByCommand(String command);
}
