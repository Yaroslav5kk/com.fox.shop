package com.fox.shop.base.entity;

import com.fox.shop.protocol.type.ImageProviderType;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue
    private long id;
    private String url;
    private String name;
    private String provider;
    @Column(name = "provider_type")
    @Enumerated(EnumType.STRING)
    private ImageProviderType providerType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
