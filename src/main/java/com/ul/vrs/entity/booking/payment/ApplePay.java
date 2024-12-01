package com.ul.vrs.entity.booking.payment;

public class ApplePay extends Payment{
    private String userId;
    private String auth;

    public ApplePay(String userId, String auth) {
        this.userId = userId;
        this.auth = auth;
    }

    public String getUserId() {
        return userId;
    }

    public String geAuth() {
        return auth;
    }

}
