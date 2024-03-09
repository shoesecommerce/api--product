package com.shoesclick.bff.site.shoes.entity;

import java.util.List;

public class ListProduct implements ListPage<Product> {

  private List<Product> content;

  private Pageable pageable;

  private Boolean last;

  private Integer totalPages;

  private Integer totalElements;

  private Boolean first;

  private Integer size;

  private Integer number;

  private Sort sort;

  private Integer numberOfElements;

  private Boolean empty;

  public List<Product> getContent() {
    return content;
  }

  public ListProduct setContent(List<Product> content) {
    this.content = content;
    return this;
  }

  public Pageable getPageable() {
    return pageable;
  }

  public ListProduct setPageable(Pageable pageable) {
    this.pageable = pageable;
    return this;
  }

  public Boolean getLast() {
    return last;
  }

  public ListProduct setLast(Boolean last) {
    this.last = last;
    return this;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  public ListProduct setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  public Integer getTotalElements() {
    return totalElements;
  }

  public ListProduct setTotalElements(Integer totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  public Boolean getFirst() {
    return first;
  }

  public ListProduct setFirst(Boolean first) {
    this.first = first;
    return this;
  }

  public Integer getSize() {
    return size;
  }

  public ListProduct setSize(Integer size) {
    this.size = size;
    return this;
  }

  public Integer getNumber() {
    return number;
  }

  public ListProduct setNumber(Integer number) {
    this.number = number;
    return this;
  }

  public Sort getSort() {
    return sort;
  }

  public ListProduct setSort(Sort sort) {
    this.sort = sort;
    return this;
  }

  public Integer getNumberOfElements() {
    return numberOfElements;
  }

  public ListProduct setNumberOfElements(Integer numberOfElements) {
    this.numberOfElements = numberOfElements;
    return this;
  }

  public Boolean getEmpty() {
    return empty;
  }

  public ListProduct setEmpty(Boolean empty) {
    this.empty = empty;
    return this;
  }
}

