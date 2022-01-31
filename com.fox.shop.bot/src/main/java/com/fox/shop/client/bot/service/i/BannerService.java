package com.fox.shop.client.bot.service.i;

import com.fox.shop.client.bot.entity.BannerEntity;
import com.fox.shop.client.bot.model.types.CommandData;

import java.util.Optional;

public interface BannerService {
  BannerEntity save(BannerEntity banner);

  Optional<BannerEntity> getByCommand(CommandData commandData);
}
