package com.fox.shop.client.bot.model.wrapper;


import com.fox.shop.protocol.ImageModel;

public class ImageWrapper {
    private final ImageModel model;

    public ImageWrapper() {
        model = new ImageModel();
    }

    public ImageWrapper(ImageModel model) {
        this.model = model;
    }

    public ImageModel getModel() {
        return model;
    }
}
