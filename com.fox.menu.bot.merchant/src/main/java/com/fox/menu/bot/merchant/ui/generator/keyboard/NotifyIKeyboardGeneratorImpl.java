package com.fox.menu.bot.merchant.ui.generator.keyboard;

import com.fox.menu.bot.merchant.ui.generator.i.InlineKeyboardGenerator;
import org.springframework.stereotype.Component;

@Component
public class NotifyIKeyboardGeneratorImpl {

    private final InlineKeyboardGenerator inlineKeyboardGenerator;

    public NotifyIKeyboardGeneratorImpl(
            final InlineKeyboardGenerator inlineKeyboardGenerator
    ) {
        this.inlineKeyboardGenerator = inlineKeyboardGenerator;
    }
}
