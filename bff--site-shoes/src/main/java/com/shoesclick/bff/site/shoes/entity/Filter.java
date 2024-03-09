package com.shoesclick.bff.site.shoes.entity;

public class Filter {

    private Integer page;

    private String filter;

    public Integer getPage() {
        return page;
    }

    public Filter setPage(Integer page) {
        this.page = page;
        return this;
    }

    public String getFilter() {
        return filter;
    }

    public Filter setFilter(String filter) {
        this.filter = filter;
        return this;
    }
}