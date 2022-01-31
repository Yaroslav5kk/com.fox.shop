package com.fox.shop.client.bot.service;

import com.fox.shop.client.bot.entity.BannerEntity;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.repository.BannerRepository;
import com.fox.shop.client.bot.service.i.BannerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BannerServiceImpl implements BannerService {

  private final BannerRepository bannerRepository;

  public BannerServiceImpl(
      final BannerRepository bannerRepository
  ) {
    this.bannerRepository = bannerRepository;
  }


  @Override
  public BannerEntity save(final BannerEntity banner) {
    return bannerRepository.save(banner);
  }

  @Override
  public Optional<BannerEntity> getByCommand(final CommandData commandData) {
    return bannerRepository.findById(commandData);
  }

}






















