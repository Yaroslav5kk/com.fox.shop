package com.fox.shop.storage.controller;

import com.fox.shop.storage.protocol.response.GeneralResponse;
import com.fox.shop.storage.service.i.FileInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

  @GetMapping("telegram-id")
  public Mono<ResponseEntity<GeneralResponse<String>>> getTelegramId(
      @RequestParam(required = false) final String id,
      @RequestParam(required = false) final long baseProductId,
      @RequestParam(required = false) final long baseProductGroupId
  ) {
    return fileInfoService.getTelegramId(
        id,
        baseProductId,
        baseProductGroupId
    ).map(ResponseEntity::ok);
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
