package com.fox.menu.bot.merchant.model.type;

import org.springframework.util.StringUtils;

public enum CommandData {
    START("/start", "begin"),
    MAIN("/main", "main board"),
    FINISH("/finish", "finish"),
    RESET("/reset", "reset"),
    BACK("/back", "back"),
    SET_VALUE("/set_value", "set value"),
    SET_URL("/set_url", "set url"),
    ENTER_OTHER_VALUE("/enter_other", "enter other"),
    NEXT_PAGE("/next_page", "next"),
    PREVIOUS_PAGE("/previous_page", "previous"),
    CONFIRM_AVAILABLE_PLACE("/confirm_available_place", "confirm available place"),
    EMPTY_AVAILABLE_PLACE("/empty_available_place", "empty available place");

    private final String value;
    private final String description;

    CommandData(final String value, final String description) {
        this.value = value;
        this.description = description;
    }

    public static CommandData fromValue(final String value) {
        if (!StringUtils.hasText(value))
            return CommandData.START;
        for (final var itCommand : values())
            if (value.contains(itCommand.getValue()))
                return itCommand;
        return CommandData.START;
    }

    public final String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static boolean contains(final String data) {
        for (var el : values()) {
            if (el.getValue().equals(data)) {
                return true;
            }
        }
        return false;
    }

}
