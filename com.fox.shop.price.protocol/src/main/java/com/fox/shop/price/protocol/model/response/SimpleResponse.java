package com.fox.shop.price.protocol.model.response;

public class SimpleResponse implements FatherResponse {
    private String result;

    public SimpleResponse() {
    }

    private SimpleResponse(final String result) {
        this.result = result;
    }

    public static SimpleResponse ok() {
        return new SimpleResponse("OK");
    }

    public static SimpleResponse failed() {
        return new SimpleResponse("FAILED");
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}