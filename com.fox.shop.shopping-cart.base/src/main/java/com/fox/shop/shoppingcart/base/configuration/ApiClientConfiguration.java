package com.fox.shop.shoppingcart.base.configuration;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiClientConfiguration {

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClients.createDefault();
    }

}
