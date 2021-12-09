package com.fox.shop.ordering.protocol.response;

public class GeneralResponse {
    private String error;
    private boolean success;

    public static GeneralResponse ok() {
        return new GeneralResponse("ok", true);
    }

    public static GeneralResponse fail() {
        return new GeneralResponse("error", false);
    }

    public GeneralResponse() {
    }

    public GeneralResponse(String error, boolean success) {
        this.error = error;
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
