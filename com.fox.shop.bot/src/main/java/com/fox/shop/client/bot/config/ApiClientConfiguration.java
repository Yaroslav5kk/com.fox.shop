package com.fox.shop.client.bot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.client.bot.api.client.BaseApiClientImpl;
import com.fox.shop.client.bot.api.client.PaginationDecoratorBaseApiClientImpl;
import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.api.factory.i.BaseRequestFactory;
import com.fox.shop.client.bot.context.i.PaginationDataContext;
import com.fox.shop.client.bot.ui.generate.i.PaginationMessageGenerator;
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

  @Bean
  public BaseApiClient baseApiClient(
          final PaginationDataContext paginationDataContext,
          final BaseRequestFactory baseRequestFactory,
          final TelegramApiClient telegramApiClient,
          final PaginationMessageGenerator paginationMessageGenerator,
          final ObjectMapper objectMapper
  ) {
    return new PaginationDecoratorBaseApiClientImpl(
            new BaseApiClientImpl(
                    baseRequestFactory,
                    objectMapper
            ),
            paginationDataContext,
            telegramApiClient,
            paginationMessageGenerator
    );
  }

}
