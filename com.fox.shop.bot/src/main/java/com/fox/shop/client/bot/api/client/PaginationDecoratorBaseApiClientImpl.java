package com.fox.shop.client.bot.api.client;

import com.fox.protocol.user.UserModel;
import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.context.i.PaginationDataContext;
import com.fox.shop.client.bot.ui.generate.i.PaginationMessageGenerator;
import com.fox.shop.protocol.DeliveryModel;
import com.fox.shop.protocol.ProductGroupModel;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.response.PageResponse;
import com.fox.shop.protocol.type.ProductGroupType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PaginationDecoratorBaseApiClientImpl implements BaseApiClient {

  private final BaseApiClient baseApiClient;
  private final PaginationDataContext paginationDataContext;
  private final TelegramApiClient telegramApiClient;
  private final PaginationMessageGenerator paginationMessageGenerator;

  public PaginationDecoratorBaseApiClientImpl(
      final BaseApiClient baseApiClient,
      final PaginationDataContext paginationDataContext,
      final TelegramApiClient telegramApiClient,
      final PaginationMessageGenerator paginationMessageGenerator
  ) {
    this.baseApiClient = baseApiClient;
    this.paginationDataContext = paginationDataContext;
    this.telegramApiClient = telegramApiClient;
    this.paginationMessageGenerator = paginationMessageGenerator;
  }

  @Override
  public PageResponse<ProductModel> productsByGroup(final long userId, final long groupId, final Pageable pageable) {
    final PageResponse currentPage = paginationDataContext.getCurrentPage(userId);
    final PageResponse<ProductModel> baseResponse = baseApiClient.productsByGroup(
        userId,
        groupId,
        PageRequest.of(currentPage.getCurrentPage() + 1, paginationDataContext.getPaginationSize())
    );
    paginationDataContext.setCurrentPage(
        userId,
        baseResponse
    );
    return baseResponse;
  }

  @Override
  public PageResponse<ProductModel> searchProductsByName(final long userId, final String name, final Pageable pageable) {
    PageResponse currentPage = paginationDataContext.getCurrentPage(userId);
    final PageResponse<ProductModel> baseResponse = baseApiClient.searchProductsByName(
        userId,
        name,
        PageRequest.of(currentPage.getCurrentPage() + 1, paginationDataContext.getPaginationSize())
    );
    paginationDataContext.setCurrentPage(
        userId,
        baseResponse
    );
    return baseResponse;
  }

  @Override
  public List<ProductModel> productsByIds(List<Long> ids) {
    return baseApiClient.productsByIds(ids);
  }

  @Override
  public ProductModel productById(long id) {
    return baseApiClient.productById(id);
  }

  @Override
  public List<ProductGroupModel> allProductGroups(ProductGroupType type) {
    return baseApiClient.allProductGroups(type);
  }

  @Override
  public UserModel saveUser(UserModel userModel) {
    return baseApiClient.saveUser(userModel);
  }

  @Override
  public List<DeliveryModel> getALlDelivery() {
    return baseApiClient.getALlDelivery();
  }
}
