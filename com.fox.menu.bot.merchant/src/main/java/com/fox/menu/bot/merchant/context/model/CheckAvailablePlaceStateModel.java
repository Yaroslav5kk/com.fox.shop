package com.fox.menu.bot.merchant.context.model;

public class CheckAvailablePlaceStateModel {
    private long customerId;
    private long waiterId;
    private long verifiedPlaceId;

    public CheckAvailablePlaceStateModel() {
    }

    public CheckAvailablePlaceStateModel(long customerId, long waiterId) {
        this.customerId = customerId;
        this.waiterId = waiterId;
    }

    public CheckAvailablePlaceStateModel userId(final long userId) {
        this.customerId = userId;
        return this;
    }

    public CheckAvailablePlaceStateModel waiterId(final long waiterId) {
        this.waiterId = waiterId;
        return this;
    }

    public CheckAvailablePlaceStateModel confirmedPlaceId(final long verifiedPlaceId) {
        this.verifiedPlaceId = verifiedPlaceId;
        return this;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(long waiterId) {
        this.waiterId = waiterId;
    }

    public long getVerifiedPlaceId() {
        return verifiedPlaceId;
    }

    public void setVerifiedPlaceId(long verifiedPlaceId) {
        this.verifiedPlaceId = verifiedPlaceId;
    }
}
