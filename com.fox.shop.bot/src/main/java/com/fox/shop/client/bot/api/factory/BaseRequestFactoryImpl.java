package com.fox.shop.client.bot.api.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.protocol.user.UserModel;
import com.fox.shop.client.bot.api.factory.i.BaseRequestFactory;
import com.fox.shop.client.bot.api.factory.i.FatherRequestFactory;
import com.fox.shop.protocol.type.ProductGroupType;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BaseRequestFactoryImpl implements BaseRequestFactory, FatherRequestFactory {

  @Value("${base.api.url}")
  private String url;
  @Value("${base.endpoint.product-by-ids}")
  private String productsByIds;
  @Value("${base.endpoint.product-by-id}")
  private String productById;
  @Value("${base.endpoint.all-product-group}")
  private String allProductGroups;
  @Value("${base.endpoint.products-by-group}")
  private String productsByGroup;
  @Value("${base.endpoint.search-products-by-name}")
  private String searchProductsByName;
  @Value("${base.endpoint.save-user}")
  private String saveUser;
  @Value("${base.endpoint.all-delivery}")
  private String allDelivery;

  private final ObjectMapper objectMapper;


  public BaseRequestFactoryImpl() {
    objectMapper = new ObjectMapper();
  }


  /*----------------------------------------------products-------------------------------------------------*/
  @Override
  public HttpUriRequest productsByIds(final List<Long> ids) {
    final StringBuilder queryListAsString = new StringBuilder();
    ids.forEach(it -> queryListAsString.append(it).append(","));
    queryListAsString.deleteCharAt(queryListAsString.length() - 1);
    final String fullUri = buildFullUri(
        url,
        productsByIds,
        Arrays.asList(Pair.of("ids", queryListAsString.toString()))
    );
    return new HttpGet(fullUri);
  }

  @Override
  public HttpUriRequest productById(final long id) {
    final String fullUri = buildFullUri(
        url,
        productById + "/" + id,
        Collections.emptyList()
    );
    return new HttpGet(fullUri);
  }

  @Override
  public HttpUriRequest productsByGroup(final long groupId) {
    final String fullUri = buildFullUri(
        url,
        productsByGroup + "/" + groupId,
        null
    );
    return new HttpGet(fullUri);
  }

  @Override
  public HttpUriRequest searchProductsByName(final String name) {
    final String fullUri = buildFullUri(
        url,
        searchProductsByName + "/" + name,
        null
    );
    return new HttpGet(fullUri);
  }

  /*----------------------------------------------groups-------------------------------------------------*/
  @Override
  public HttpUriRequest allProductGroups(final ProductGroupType type) {
    final String fullUri = buildFullUri(
        url,
        allProductGroups,
        Arrays.asList(Pair.of("type", type.name()))
    );
    return new HttpGet(fullUri);
  }

  /*----------------------------------------------users-------------------------------------------------*/
  @Override
  public HttpUriRequest saveUser(final UserModel request) {
    final String fullUri = buildFullUri(
        url,
        saveUser,
        null
    );
    final HttpPost result = new HttpPost(fullUri);
    result.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
    try {
      result.setEntity(new StringEntity(objectMapper.writeValueAsString(request)));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return result;
  }

  /*----------------------------------------------delivery-------------------------------------------------*/
  @Override
  public HttpUriRequest getAllDelivery() {
    final String fullUri = buildFullUri(
        url,
        allDelivery,
        null
    );
    return new HttpGet(fullUri);
  }
}
