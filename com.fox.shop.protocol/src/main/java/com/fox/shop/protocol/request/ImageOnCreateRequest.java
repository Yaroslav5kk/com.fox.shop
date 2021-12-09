package com.fox.shop.protocol.request;

import com.fox.shop.protocol.type.ImageProviderType;

public class ImageOnCreateRequest {

    private String url;
    private String provider;
    private String name;
    private ImageProviderType providerType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageProviderType getProviderType() {
        return providerType;
    }

    public void setProviderType(ImageProviderType providerType) {
        this.providerType = providerType;
    }
}
