package com.fox.shop.client.bot.ui.view;

import com.fox.shop.client.bot.entity.BannerEntity;

import static com.fox.shop.client.bot.ui.view.HtmlElementGenerator.bold;

public class BannerViewer {

  public static String startPage() {
    return "U+1F609 Привіт дружок, пирожок U+1F609";
  }


  public static String general(
      final BannerEntity banner
  ) {
    return bold(banner.getTextToView()).toString();
  }


}
