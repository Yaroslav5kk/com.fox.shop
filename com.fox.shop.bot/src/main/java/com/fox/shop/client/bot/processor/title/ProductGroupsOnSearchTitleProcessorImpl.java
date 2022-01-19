package com.fox.shop.client.bot.processor.title;

import com.fox.shop.client.bot.annotation.CommandProcessorComponent;
import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.processor.title.i.TgCommandTitleProcessorFather;
import com.fox.shop.client.bot.ui.generate.keyboard.i.GroupsInlineKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.GroupsViewer;
import com.fox.shop.protocol.ProductGroupModel;
import com.fox.shop.protocol.type.ProductGroupType;

import java.util.ArrayList;
import java.util.List;

@CommandProcessorComponent
public class ProductGroupsOnSearchTitleProcessorImpl implements TgCommandTitleProcessorFather {
  private final TelegramApiMediator telegramApiMediator;
  private final StorageApiClient storageApiClient;
  private final GroupsInlineKeyboardGenerator groupsInlineKeyboardGenerator;
  private final BaseApiClient baseApiClient;

  public ProductGroupsOnSearchTitleProcessorImpl(
          final TelegramApiMediator telegramApiMediator,
          final StorageApiClient storageApiClient,
          final GroupsInlineKeyboardGenerator groupsInlineKeyboardGenerator,
          final BaseApiClient baseApiClient
  ) {
    this.telegramApiMediator = telegramApiMediator;
    this.storageApiClient = storageApiClient;
    this.groupsInlineKeyboardGenerator = groupsInlineKeyboardGenerator;
    this.baseApiClient = baseApiClient;
  }

  @Override
  public void process(final TgIncomingCommandModel incomingCommand) {
    telegramApiMediator.addMessages(incomingCommand.getUserId(), allSearchProductGroups(incomingCommand));
  }

  private List<SendPhotoFileIdRequest> allSearchProductGroups(
          final TgIncomingCommandModel incomingCommand
  ) {
    final List<ProductGroupModel> productGroups = baseApiClient.allProductGroups(ProductGroupType.SEARCH);
    final List<SendPhotoFileIdRequest> result = new ArrayList<>();
    for (var itGroup : productGroups) {
      final String fileId = storageApiClient.getTelegramIdById(itGroup.getMainImageStorageId());
      final SendPhotoFileIdRequest sendPhoto = new SendPhotoFileIdRequest();
      sendPhoto.setChatId(incomingCommand.getChatId());
      sendPhoto.setParseMode("HTML");
      sendPhoto.setCaption(GroupsViewer.viewProductGroup(itGroup));
      sendPhoto.setPhoto(fileId);
      sendPhoto.setReplyMarkup(groupsInlineKeyboardGenerator.productGroup(incomingCommand.getUserId(), itGroup.getId()));
      result.add(sendPhoto);
    }
    return result;
  }

  @Override
  public CommandData getResponsibleCommand() {
    return CommandData.ALL_SEARCH_PRODUCT_GROUPS;
  }
}
































