package com.fox.shop.client.bot.entity;

import com.fox.shop.client.bot.model.types.CommandData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "banner")
public class BannerEntity {
  @Id
  private CommandData command;
  private List<String> storageImagesIds;
  private String textToView;

  public BannerEntity() {
  }

  public BannerEntity(CommandData command, List<String> storageImagesIds, String textToView) {
    this.command = command;
    this.storageImagesIds = storageImagesIds;
    this.textToView = textToView;
  }

  public CommandData getCommand() {
    return command;
  }

  public void setCommand(CommandData command) {
    this.command = command;
  }

  public List<String> getStorageImagesIds() {
    return storageImagesIds;
  }

  public void setStorageImagesIds(List<String> storageImagesIds) {
    this.storageImagesIds = storageImagesIds;
  }

  public String getTextToView() {
    return textToView;
  }

  public void setTextToView(String textToView) {
    this.textToView = textToView;
  }
}
