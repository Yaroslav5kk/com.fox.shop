package com.fox.shop.storage.entity.listener;

import com.fox.shop.storage.entity.FileInfoEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

@Component
public class FileInfoListener extends AbstractMongoEventListener<FileInfoEntity> {
  @Override
  public void onBeforeConvert(BeforeConvertEvent<FileInfoEntity> event) {
    final FileInfoEntity source = event.getSource();
    source.setId(source.getId() + source.getFormat().getFormat());
  }
}
