package com.fox.menu.bot.merchant.controller;

import com.fox.menu.billing.protocol.model.OrderNotifyModel;
import com.fox.menu.bot.merchant.ui.scenarios.i.OrderScenarios;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notify")
public class NotifyController {

    private final OrderScenarios orderScenarios;

    public NotifyController(
            final OrderScenarios orderScenarios
    ) {
        this.orderScenarios = orderScenarios;
    }

    @PostMapping("order")
    public ResponseEntity<String> notifyOrder(
            @RequestBody final OrderNotifyModel model
    ) {
        orderScenarios.notifyOrder(model);
        return ResponseEntity.ok("OK");
    }
}
