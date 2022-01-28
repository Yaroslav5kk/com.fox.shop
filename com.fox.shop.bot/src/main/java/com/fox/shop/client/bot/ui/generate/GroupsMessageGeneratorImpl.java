package com.fox.shop.client.bot.ui.generate;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.ui.generate.i.GroupsMessageGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.GroupsInlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.GroupsViewer;
import com.fox.shop.protocol.ProductGroupModel;
import com.fox.shop.protocol.type.ProductGroupType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupsMessageGeneratorImpl implements GroupsMessageGenerator {

  private final BaseApiClient baseApiClient;
  private final GroupsInlineKeyboardGenerator groupsInlineKeyboardGenerator;
  private final StorageApiClient storageApiClient;

  public GroupsMessageGeneratorImpl(
      final BaseApiClient baseApiClient,
      final GroupsInlineKeyboardGenerator groupsInlineKeyboardGenerator,
      final StorageApiClient storageApiClient
  ) {
    this.baseApiClient = baseApiClient;
    this.groupsInlineKeyboardGenerator = groupsInlineKeyboardGenerator;
    this.storageApiClient = storageApiClient;
  }

  @Override
  public List<SendPhotoFileIdRequest> allSearchProductGroups(
      final long chatId,
      final long userId
  ) {
    final List<ProductGroupModel> productGroups = baseApiClient.allProductGroups(ProductGroupType.SEARCH);
    final List<SendPhotoFileIdRequest> result = new ArrayList<>();
    for (var itGroup : productGroups) {
      final String fileId = storageApiClient.getTelegramIdById(itGroup.getMainImageStorageId());
      final SendPhotoFileIdRequest sendPhoto = new SendPhotoFileIdRequest();
      sendPhoto.setChatId(chatId);
      sendPhoto.setParseMode("HTML");
      sendPhoto.setCaption(GroupsViewer.viewProductGroup(itGroup));
      sendPhoto.setPhoto(fileId);
      sendPhoto.setReplyMarkup(groupsInlineKeyboardGenerator.productGroup(userId, itGroup.getId()));
      result.add(sendPhoto);
    }
    return result;
  }

  @Override
  public List<SendPhotoFileIdRequest> allMainProductGroups(
      final long chatId,
      final long userId
  ) {
    final List<ProductGroupModel> productGroups = baseApiClient.allProductGroups(ProductGroupType.MAIN);
    final List<SendPhotoFileIdRequest> result = new ArrayList<>();
    for (var itGroup : productGroups) {
      final String fileId = storageApiClient.getTelegramIdById(itGroup.getMainImageStorageId());
      final SendPhotoFileIdRequest sendPhoto = new SendPhotoFileIdRequest();
      sendPhoto.setChatId(chatId);
      sendPhoto.setParseMode("HTML");
      sendPhoto.setCaption(GroupsViewer.viewProductGroup(itGroup));
      sendPhoto.setPhoto(fileId);
      sendPhoto.setReplyMarkup(groupsInlineKeyboardGenerator.productGroup(userId, itGroup.getId()));
      result.add(sendPhoto);
    }
    return result;
  }

}




















