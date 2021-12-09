package com.fox.protocol.user;

import java.util.Objects;

public enum Role {
    USER("User"),
    ADMIN("Admin"),
    MERCHANT("Merchant"),
    WAITER("Waiter");

    private final String value;

    Role(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Role byValue(final String value) {
        for (Role itValue :
                values()) {
            if (Objects.equals(itValue.getValue(), value)) {
                return itValue;
            }
        }
        return Role.USER;
    }
}
