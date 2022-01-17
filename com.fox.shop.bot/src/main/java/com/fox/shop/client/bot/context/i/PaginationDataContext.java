package com.fox.shop.client.bot.context.i;

import com.fox.shop.protocol.response.PageResponse;

public interface PaginationDataContext {
  PageResponse setCurrentPage(
          int userId,
          PageResponse currentPage
  );

  Integer getNextPageNumber(int userId);

  PageResponse getCurrentPage(int userId);

  void incrementPage(int userId);

  void clear(int userId);

  int getPaginationSize();
}
