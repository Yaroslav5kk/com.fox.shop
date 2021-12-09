package com.fox.shop.client.bot.model.response;

import com.fox.shop.client.bot.model.Result;

public class GetFileResponse {
    public static GetFileResponse BAD_FILE_RESPONSE = new GetFileResponse().ok(false);

    private boolean ok;
    private Result result;


    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public GetFileResponse ok(final boolean ok) {
        this.ok = ok;
        return this;
    }
}
