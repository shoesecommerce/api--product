package com.shoesclick.bff.site.shoes.entity;

import java.util.List;

public interface ListPage<T> {

    List<T> getContent();
}
