package com.fox.menu.bot.merchant.service;

import com.fox.menu.bot.merchant.entity.CommandConfigurationEntity;
import com.fox.menu.bot.merchant.repository.CommandConfigurationRepository;
import com.fox.menu.bot.merchant.service.i.CommandConfigurationService;
import org.springframework.stereotype.Service;

@Service
public class CommandConfigurationServiceImpl implements CommandConfigurationService {

    private final CommandConfigurationRepository commandConfigurationRepository;

    public CommandConfigurationServiceImpl(
            final CommandConfigurationRepository commandConfigurationRepository
    ) {
        this.commandConfigurationRepository = commandConfigurationRepository;
    }

    @Override
    public CommandConfigurationEntity save(final CommandConfigurationEntity commandConfigurationEntity) {
        return commandConfigurationRepository.save(commandConfigurationEntity);
    }

    @Override
    public CommandConfigurationEntity get(final String command) {
        return commandConfigurationRepository.findById(command).get();
    }

    @Override
    public boolean existsByCommand(final String command) {
        return commandConfigurationRepository.existsById(command);
    }
}













