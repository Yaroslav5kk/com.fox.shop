package com.fox.shop.storage.controller;

import com.fox.shop.storage.response.GeneralResponse;
import com.fox.shop.storage.service.i.FileInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/file-info")
public class FileInfoController {

  private final FileInfoService fileInfoService;

  public FileInfoController(
          final FileInfoService fileInfoService
  ) {
    this.fileInfoService = fileInfoService;
  }

  @GetMapping("telegram-id-by-main-id/{baseId}")
  public Mono<ResponseEntity<GeneralResponse<String>>> getTelegramIdByBaseId(
          @PathVariable final int baseId
  ) {
    return fileInfoService.getTelegramIdByBaseId(baseId).map(ResponseEntity::ok);
  }

  @GetMapping("telegram-id-by-id/{id}")
  public Mono<ResponseEntity<GeneralResponse<String>>> getTelegramIdByBaseId(
          @PathVariable final String id
  ) {
    return fileInfoService.getTelegramIdById(id).map(ResponseEntity::ok);
  }
}
