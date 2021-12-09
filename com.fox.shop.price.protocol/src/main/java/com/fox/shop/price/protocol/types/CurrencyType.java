package com.fox.shop.price.protocol.types;

public enum CurrencyType {
    UA("\u20B4"),
    USD("$"),
    EUR("євро");
    private final String name;

    CurrencyType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}