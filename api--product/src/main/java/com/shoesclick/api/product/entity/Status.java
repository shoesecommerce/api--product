package com.shoesclick.api.product.entity;

public class Status {

    private final int code;
    private final String description;

    public Status(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
