package com.shoesclick.bff.site.shoes.entity;

public class Status {

    private Integer code;
    private String description;

    public Status() {
    }

    public Status(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public Status setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Status setDescription(String description) {
        this.description = description;
        return this;
    }
}
