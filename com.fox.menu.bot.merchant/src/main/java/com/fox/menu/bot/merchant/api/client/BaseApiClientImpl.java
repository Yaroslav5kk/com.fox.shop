package com.fox.menu.bot.merchant.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fox.menu.bot.merchant.api.client.i.BaseApiClient;
import com.fox.menu.bot.merchant.api.client.i.FatherApiClient;
import com.fox.menu.bot.merchant.api.factory.i.BaseRequestFactory;
import com.fox.menu.bot.merchant.service.i.UploadImageToTelegramService;
import com.fox.menu.protocol.ImageByteModel;
import com.fox.menu.protocol.PlaceModel;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BaseApiClientImpl implements FatherApiClient, BaseApiClient {

    private final BaseRequestFactory baseRequestFactory;
    private final UploadImageToTelegramService uploadImageToTelegramService;
    private final CloseableHttpClient client;
    private final ObjectMapper objectMapper;

    public BaseApiClientImpl(
            final BaseRequestFactory baseRequestFactory,
            final ObjectMapper objectMapper,
            final UploadImageToTelegramService uploadImageToTelegramService
    ) {
        this.baseRequestFactory = baseRequestFactory;
        this.uploadImageToTelegramService = uploadImageToTelegramService;
        this.client = HttpClients.createDefault();
        this.objectMapper = objectMapper;
    }

    /*--------------------------------------------- place ----------------------------------------------------*/
    @Override
    public List<PlaceModel> placesByIds(final List<Long> ids) {
        final Optional<List<PlaceModel>> response = executeRequestAndExtractResponse(
                baseRequestFactory.placesByIds(ids),
                objectMapper.getTypeFactory().constructCollectionType(List.class, PlaceModel.class)
        );
        return response.isPresent()
                ? response.get()
                : Collections.emptyList();
    }

    @Override
    public List<String> merchantPlacesByWaiterId(final long waiterId) {
        final Optional<List<ImageByteModel>> response = executeRequestAndExtractResponse(
                baseRequestFactory.merchantPlacesByWaiterId(waiterId),
                objectMapper.getTypeFactory().constructCollectionType(List.class, ImageByteModel.class)
        );
        return response.isPresent()
                ? uploadImageToTelegramService.existOrUploadAndGetTelegramFileId(response.get())
                : Arrays.asList(uploadImageToTelegramService.getErrorTelegramFileId());
    }

    @Override
    public PlaceModel placeByIdOnMap(
            final String idOnMap,
            final long waiterId
    ) {
        final Optional<PlaceModel> response = executeRequestAndExtractResponse(
                baseRequestFactory.placeByIdOnMap(idOnMap, waiterId),
                SimpleType.constructUnsafe(PlaceModel.class)
        );
        return response.isPresent()
                ? response.get()
                : new PlaceModel();
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
