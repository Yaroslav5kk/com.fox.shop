package com.fox.shop.client.bot.model.types;

public enum FileType {
    PHOTO(),
    ANIMATION(),
    VIDEO();

    public static FileType fromName(final String name) {
        for (FileType fileType : values()) {
            if (fileType.name().equals(name)) {
                return fileType;
            }
        }
        return FileType.PHOTO;
    }
}
