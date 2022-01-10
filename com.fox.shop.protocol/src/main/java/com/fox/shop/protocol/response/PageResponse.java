package com.fox.shop.protocol.response;

import java.util.Collections;
import java.util.List;

public class PageResponse<T> {
  private List<T> content;
  private int currentPage;
  private long total;
  private boolean isLast;

  public PageResponse() {
  }

  public PageResponse(List<T> content, int currentPage, long total, boolean isLast) {
    this.content = content;
    this.currentPage = currentPage;
    this.total = total;
    this.isLast = isLast;
  }

  public static PageResponse empty() {
    return new PageResponse(
            Collections.emptyList(),
            0,
            3,
            false
    );
  }

  public void incrementNumber() {
    currentPage++;
  }

  public PageResponse<T> content(final List<T> content) {
    this.content = content;
    return this;
  }

  public PageResponse currentPage(final int currentPage) {
    this.currentPage = currentPage;
    return this;
  }

  public PageResponse total(final long total) {
    this.total = total;
    return this;
  }

  public PageResponse isLast(final boolean isLast) {
    this.isLast = isLast;
    return this;
  }

  public List<T> getContent() {
    return content;
  }

  public void setContent(List<T> content) {
    this.content = content;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public boolean isLast() {
    return isLast;
  }

  public void setLast(boolean last) {
    isLast = last;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }
}
