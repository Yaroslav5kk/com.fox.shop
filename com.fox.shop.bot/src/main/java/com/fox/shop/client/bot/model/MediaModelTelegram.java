package com.fox.shop.client.bot.model;

public class MediaModelTelegram {
    public final static String PHOTO_TYPE = "photo";
    public final static String ATTACH_MEDIA_PREFIX = "attach://";

    private String media;
    private String type;

    public MediaModelTelegram() {
    }

    public MediaModelTelegram(String media, String type) {
        this.media = media;
        this.type = type;
    }

    public static MediaModelTelegram buildPhotoAttach(final String mediaName) {
        return new MediaModelTelegram(ATTACH_MEDIA_PREFIX + mediaName, PHOTO_TYPE);
    }

    public static MediaModelTelegram buildPhotoFileIdOnTelegram(final String fileIdOnTelegram) {
        return new MediaModelTelegram(fileIdOnTelegram, PHOTO_TYPE);
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
