package com.fox.menu.bot.merchant.service.i;

import com.fox.menu.bot.merchant.entity.CommandConfigurationEntity;

public interface CommandConfigurationService {
    CommandConfigurationEntity save(CommandConfigurationEntity commandConfigurationEntity);

    CommandConfigurationEntity get(String command);

    boolean existsByCommand(String command);
}
