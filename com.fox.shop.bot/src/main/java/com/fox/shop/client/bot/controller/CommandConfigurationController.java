package com.fox.shop.client.bot.controller;

import com.fox.shop.client.bot.entity.CommandConfigurationEntity;
import com.fox.shop.client.bot.service.i.CommandConfigurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/command/configuration")
public class CommandConfigurationController {

    private final CommandConfigurationService commandConfigurationService;

    public CommandConfigurationController(
            final CommandConfigurationService commandConfigurationService
    ) {
        this.commandConfigurationService = commandConfigurationService;
    }

    @PostMapping
    public ResponseEntity<CommandConfigurationEntity> save(@RequestBody final CommandConfigurationEntity request) {
        return ResponseEntity.ok(commandConfigurationService.save(request));
    }

    @GetMapping("/{command}")
    public ResponseEntity<CommandConfigurationEntity> get(@PathVariable final String command) {
        return ResponseEntity.ok(commandConfigurationService.get(command));
    }
}
