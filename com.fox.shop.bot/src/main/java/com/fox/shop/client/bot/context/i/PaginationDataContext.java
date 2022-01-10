package com.fox.shop.client.bot.context.i;

import com.fox.shop.protocol.response.PageResponse;

public interface PaginationDataContext {
  void currentPage(
          int userId,
          PageResponse currentPage
  );

  Integer getNextPage(int userId);

  void incrementPage(int userId);

  void clear(int userId);
}
