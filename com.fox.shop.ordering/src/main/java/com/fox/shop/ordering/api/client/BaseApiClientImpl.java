package com.fox.shop.ordering.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fox.protocol.user.UserModel;
import com.fox.shop.ordering.api.client.i.BaseApiClient;
import com.fox.shop.ordering.api.client.i.FatherApiClient;
import com.fox.shop.ordering.api.factory.i.BaseRequestFactory;
import com.fox.shop.protocol.response.GeneralResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BaseApiClientImpl implements FatherApiClient, BaseApiClient {

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

  /*--------------------------------------------- users ----------------------------------------------------*/
  @Override
  public UserModel getUserById(final long id) {
    final Optional<UserModel> response = executeRequestAndExtractResponse(
        baseRequestFactory.getUserById(id),
        SimpleType.constructUnsafe(UserModel.class)
    );
    return response.isPresent()
        ? response.get()
        : new UserModel();
  }

  /*--------------------------------------------- users ----------------------------------------------------*/
  @Override
  public GeneralResponse<Long> getMerchantIdByProductId(final long productId) {
    final Optional<GeneralResponse> response = executeRequestAndExtractResponse(
        baseRequestFactory.getUserById(productId),
        SimpleType.constructUnsafe(GeneralResponse.class)
    );
    return response.isPresent()
        ? response.get()
        : new GeneralResponse();
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
