package com.fox.shop.client.bot.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class StringParserImpl implements StringParser {

    @Override
    public List<String> list(final String text) {
        if (StringUtils.isEmpty(text) && !text.contains(";"))
            return Collections.emptyList();
        final List<String> result = new ArrayList<>();
        for (final var it : text.split(";"))
            result.add(it.replace(" ", ""));
        return result;
    }
}
