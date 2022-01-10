package com.fox.shop.client.bot.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fox.protocol.user.UserModel;
import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.FatherApiClient;
import com.fox.shop.client.bot.api.factory.i.BaseRequestFactory;
import com.fox.shop.protocol.DeliveryModel;
import com.fox.shop.protocol.ProductGroupModel;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.type.ProductGroupType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BaseApiClientImpl implements BaseApiClient, FatherApiClient {

  private final BaseRequestFactory baseRequestFactory;
  private final CloseableHttpClient client;
  private final ObjectMapper objectMapper;

  public BaseApiClientImpl(
          final BaseRequestFactory baseRequestFactory,
          final ObjectMapper objectMapper
  ) {
    this.baseRequestFactory = baseRequestFactory;
    this.client = HttpClients.createDefault();
    this.objectMapper = objectMapper;
  }

  /*--------------------------------------------- product ----------------------------------------------------*/
  @Override
  public List<ProductModel> productsByGroup(
          final long groupId,
          final Pageable pageable
  ) {
    final Optional<List<ProductModel>> response = executeRequestAndExtractResponse(
            baseRequestFactory.productsByGroup(groupId, pageable),
            objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, ProductModel.class)
    );
    return response.isPresent()
            ? response.get()
            : Collections.emptyList();
  }

  @Override
  public List<ProductModel> searchProductsByName(final String name) {
    final Optional<List<ProductModel>> response = executeRequestAndExtractResponse(
            baseRequestFactory.searchProductsByName(name),
            objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, ProductModel.class)
    );
    return response.isPresent()
            ? response.get()
            : Collections.emptyList();
  }

  @Override
  public List<ProductModel> productsByIds(final List<Long> ids) {
    final Optional<List<ProductModel>> response = executeRequestAndExtractResponse(
            baseRequestFactory.productsByIds(ids),
            objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, ProductModel.class)
    );
    return response.isPresent()
            ? response.get()
            : Collections.emptyList();
  }

  @Override
  public ProductModel productById(final long id) {
    final Optional<ProductModel> response = executeRequestAndExtractResponse(
            baseRequestFactory.productById(id),
            SimpleType.constructUnsafe(ProductModel.class)
    );
    return response.isPresent()
            ? response.get()
            : new ProductModel();
  }


  @Override
  public List<ProductGroupModel> allProductGroups(final ProductGroupType type) {
    final Optional<List<ProductGroupModel>> response = executeRequestAndExtractResponse(
            baseRequestFactory.allProductGroups(type),
            objectMapper.getTypeFactory().constructCollectionType(List.class, ProductGroupModel.class)
    );
    return response.isPresent()
            ? response.get()
            : Collections.emptyList();
  }

  /*--------------------------------------------- users ----------------------------------------------------*/
  @Override
  public UserModel saveUser(final UserModel userModel) {
    final Optional<UserModel> response = executeRequestAndExtractResponse(
            baseRequestFactory.saveUser(userModel),
            SimpleType.constructUnsafe(UserModel.class)
    );
    return response.isPresent()
            ? response.get()
            : new UserModel();
  }

  /*--------------------------------------------- delivery ----------------------------------------------------*/
  @Override
  public List<DeliveryModel> getALlDelivery() {
    final Optional<List<DeliveryModel>> response = executeRequestAndExtractResponse(
            baseRequestFactory.getAllDelivery(),
            objectMapper.getTypeFactory().constructCollectionType(List.class, DeliveryModel.class)
    );
    return response.isPresent()
            ? response.get()
            : Collections.EMPTY_LIST;
  }

  @Override
  public CloseableHttpClient getClient() {
    return client;
  }


  @Override
  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }
}
