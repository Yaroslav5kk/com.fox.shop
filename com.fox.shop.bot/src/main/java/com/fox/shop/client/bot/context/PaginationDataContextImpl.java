package com.fox.shop.client.bot.context;

import com.fox.shop.client.bot.context.i.PaginationDataContext;
import com.fox.shop.protocol.response.PageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaginationDataContextImpl implements PaginationDataContext {

  @Value("${content.product.pagination-size}")
  private int paginationSize;

  private final Map<Long, PageResponse> userIdCurrentPage;

  public PaginationDataContextImpl() {
    userIdCurrentPage = new HashMap<>();
  }

  @Override
  public PageResponse setCurrentPage(
          final long userId,
          final PageResponse currentPage
  ) {
    return userIdCurrentPage.put(userId, currentPage);
  }

  @Override
  public Integer getNextPageNumber(final long userId) {
    PageResponse currentPage = userIdCurrentPage.get(userId);
    if (currentPage == null)
      return userIdCurrentPage.put(userId, PageResponse.empty()).getCurrentPage();
    return userIdCurrentPage.get(userId).getCurrentPage() + 1;
  }

  @Override
  public PageResponse getCurrentPage(final long userId) {
    PageResponse currentPage = userIdCurrentPage.get(userId);
    if (currentPage == null)
      userIdCurrentPage.put(userId, PageResponse.empty());
    return userIdCurrentPage.get(userId);
  }

  @Override
  public void incrementPage(final long userId) {
    userIdCurrentPage.get(userId).incrementNumber();
  }

  @Override
  public void clear(final long userId) {
    userIdCurrentPage.remove(userId);
  }


  @Override
  public int getPaginationSize() {
    return paginationSize;
  }
}
