package com.fox.shop.protocol;

public class ImageByteModel {
    private long id;
    private String name;
    private byte[] value;

    public ImageByteModel() {
    }

    public ImageByteModel(long id, String name, byte[] value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
