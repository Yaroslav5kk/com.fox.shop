package com.fox.menu.bot.merchant.api.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.menu.bot.merchant.api.factory.i.BaseRequestFactory;
import com.fox.menu.bot.merchant.api.factory.i.FatherRequestFactory;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaseRequestFactoryImpl implements FatherRequestFactory, BaseRequestFactory {

    @Value("${base.api.url}")
    private String url;
    @Value("${base.endpoint.places-by-ids}")
    private String placesByIds;
    @Value("${base.endpoint.merchant-places-by-waiter-id}")
    private String merchantPlacesByWaiterId;
    @Value("${base.endpoint.place-by-id-on-map}")
    private String placeByIdOnMap;

    private final ObjectMapper objectMapper;


    public BaseRequestFactoryImpl() {
        objectMapper = new ObjectMapper();
    }


    /*----------------------------------------------places-------------------------------------------------*/
    @Override
    public HttpUriRequest merchantPlacesByWaiterId(
            final long waiterId
    ) {
        final String fullUri = buildFullUri(
                url,
                merchantPlacesByWaiterId + "/" + waiterId,
                null
        );
        return new HttpGet(fullUri);
    }

    @Override
    public HttpUriRequest placeByIdOnMap(
            final String idOnMap,
            final long merchantId
    ) {
        final String fullUri = buildFullUri(
                url,
                placeByIdOnMap,
                Arrays.asList(
                        Pair.of("idOnMap", idOnMap),
                        Pair.of("merchantId", String.valueOf(merchantId))
                )
        );
        return new HttpGet(fullUri);
    }

    @Override
    public HttpUriRequest placesByIds(
            final List<Long> ids
    ) {
        final String fullUri = buildFullUri(
                url,
                placesByIds,
                Arrays.asList(
                        Pair.of("ids", ids.stream().map(String::valueOf).collect(Collectors.joining(",")))
                )
        );
        return new HttpGet(fullUri);
    }


}
