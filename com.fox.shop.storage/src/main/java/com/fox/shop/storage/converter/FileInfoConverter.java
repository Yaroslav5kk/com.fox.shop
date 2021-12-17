package com.fox.shop.storage.converter;

import com.fox.shop.storage.entity.FileInfoEntity;
import com.fox.shop.storage.response.FileInfoResponse;

public class FileInfoConverter {

  public static FileInfoResponse entityToResponse(final FileInfoEntity entity) {
    final FileInfoResponse result = new FileInfoResponse();
    result.setId(entity.getId());
    result.setFilePath(entity.getFilePath());
    result.setTelegramFileId(entity.getTelegramFileId());
    result.setFileType(entity.getFileType());
    result.setStorageProviderType(entity.getStorageProviderType());
    result.setTelegramHolderType(entity.getTelegramHolderType());
    return result;
  }
}
