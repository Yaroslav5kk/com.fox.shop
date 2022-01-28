package com.fox.shop.client.bot.context.i;

import com.fox.shop.protocol.response.PageResponse;

public interface PaginationDataContext {
  PageResponse setCurrentPage(
      long userId,
          PageResponse currentPage
  );

  Integer getNextPageNumber(long userId);

  PageResponse getCurrentPage(long userId);

  void incrementPage(long userId);

  void clear(long userId);

  int getPaginationSize();
}
