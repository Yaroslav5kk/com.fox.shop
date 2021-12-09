package com.fox.shop.client.bot.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fox.protocol.user.UserModel;
import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.FatherApiClient;
import com.fox.shop.client.bot.api.factory.i.BaseRequestFactory;
import com.fox.shop.client.bot.service.i.UploadImageToTelegramService;
import com.fox.shop.protocol.*;
import com.fox.shop.protocol.type.ProductGroupType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BaseApiClientImpl implements BaseApiClient, FatherApiClient {

    private final BaseRequestFactory baseRequestFactory;
    private final CloseableHttpClient client;
    private final ObjectMapper objectMapper;
    private final UploadImageToTelegramService uploadImageToTelegramService;

    public BaseApiClientImpl(
            final BaseRequestFactory baseRequestFactory,
            final ObjectMapper objectMapper,
            final UploadImageToTelegramService uploadImageToTelegramService
    ) {
        this.baseRequestFactory = baseRequestFactory;
        this.client = HttpClients.createDefault();
        this.objectMapper = objectMapper;
        this.uploadImageToTelegramService = uploadImageToTelegramService;
    }

    /*--------------------------------------------- category ----------------------------------------------------*/

    @Override
    public List<CategoryModel> categoryByMenu(final long menuId) {
        final Optional<List<CategoryModel>> response = executeRequestAndExtractResponse(
                baseRequestFactory.categoryByMenu(menuId),
                objectMapper.getTypeFactory().constructCollectionType(List.class, CategoryModel.class)
        );
        return response.isPresent()
                ? response.get()
                : Collections.emptyList();
    }

    @Override
    public String mainImageByteByCategory(final long categoryId) {
        final Optional<ImageByteModel> response = executeRequestAndExtractResponse(
                baseRequestFactory.mainImageByteByCategory(categoryId),
                SimpleType.constructUnsafe(ImageByteModel.class)
        );
        return response.isPresent()
                ? uploadImageToTelegramService.existOrUploadAndGetTelegramFileId(response.get())
                : uploadImageToTelegramService.getErrorTelegramFileId();
    }


    /*--------------------------------------------- product ----------------------------------------------------*/

    @Override
    public String mainImageByteByProduct(final long productId) {
        final Optional<ImageByteModel> response = executeRequestAndExtractResponse(
                baseRequestFactory.mainImageByteByProduct(productId),
                SimpleType.constructUnsafe(ImageByteModel.class)
        );
        return response.isPresent()
                ? uploadImageToTelegramService.existOrUploadAndGetTelegramFileId(response.get())
                : uploadImageToTelegramService.getErrorTelegramFileId();
    }

    @Override
    public List<ProductModel> productsByGroup(final long groupId) {
        final Optional<List<ProductModel>> response = executeRequestAndExtractResponse(
                baseRequestFactory.productsByGroup(groupId),
                objectMapper.getTypeFactory().constructCollectionType(List.class, ProductModel.class)
        );
        return response.isPresent()
                ? response.get()
                : Collections.emptyList();
    }

    @Override
    public List<ProductModel> searchProductsByName(final String name) {
        final Optional<List<ProductModel>> response = executeRequestAndExtractResponse(
                baseRequestFactory.searchProductsByName(name),
                objectMapper.getTypeFactory().constructCollectionType(List.class, ProductModel.class)
        );
        return response.isPresent()
                ? response.get()
                : Collections.emptyList();
    }

    @Override
    public List<ProductModel> productByCategory(final long categoryId) {
        final Optional<List<ProductModel>> response = executeRequestAndExtractResponse(
                baseRequestFactory.productByCategory(categoryId),
                objectMapper.getTypeFactory().constructCollectionType(List.class, ProductModel.class)
        );
        return response.isPresent()
                ? response.get()
                : Collections.emptyList();
    }

    @Override
    public List<ProductModel> productByIds(final List<Long> ids) {
        final Optional<List<ProductModel>> response = executeRequestAndExtractResponse(
                baseRequestFactory.productByIds(ids),
                objectMapper.getTypeFactory().constructCollectionType(List.class, ProductModel.class)
        );
        return response.isPresent()
                ? response.get()
                : Collections.emptyList();
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


    /*--------------------------------------------- image ----------------------------------------------------*/
    @Override
    public String downloadImageByteById(final long imageId) {
        final Optional<ImageByteModel> response = executeRequestAndExtractResponse(
                baseRequestFactory.downloadImageByteById(imageId),
                SimpleType.constructUnsafe(ImageByteModel.class)
        );
        return response.isPresent()
                ? uploadImageToTelegramService.existOrUploadAndGetTelegramFileId(response.get())
                : uploadImageToTelegramService.getErrorTelegramFileId();
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
