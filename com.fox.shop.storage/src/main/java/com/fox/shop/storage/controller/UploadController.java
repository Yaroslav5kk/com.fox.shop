package com.fox.shop.storage.controller;

import com.fox.shop.storage.protocol.response.FileInfoResponse;
import com.fox.shop.storage.protocol.response.GeneralResponse;
import com.fox.shop.storage.protocol.types.FileType;
import com.fox.shop.storage.protocol.types.TelegramHolderType;
import com.fox.shop.storage.service.i.UploadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/upload")
public class UploadController {

  private final UploadService uploadService;

  public UploadController(
      final UploadService uploadService
  ) {
    this.uploadService = uploadService;
  }

  @PostMapping(value = "image-to-telegram", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Mono<ResponseEntity<GeneralResponse<FileInfoResponse>>> imageUploadToTelegram(
      @RequestParam final TelegramHolderType holderType,
      @RequestPart final FilePart file
  ) throws IOException {
    return uploadService.uploadToTelegram(holderType, FileType.PHOTO, file).map(ResponseEntity::ok);
  }

  @PostMapping(value = "animation-to-telegram", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Mono<ResponseEntity<GeneralResponse<FileInfoResponse>>> animationUploadToTelegram(
      @RequestParam final TelegramHolderType holderType,
      @RequestPart final FilePart file
  ) throws IOException {
    return uploadService.uploadToTelegram(holderType, FileType.ANIMATION, file).map(ResponseEntity::ok);
  }

  @PostMapping(value = "video-to-telegram", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Mono<ResponseEntity<GeneralResponse<FileInfoResponse>>> videoUploadToTelegram(
      @RequestParam final TelegramHolderType holderType,
      @RequestPart final FilePart file
  ) throws IOException {
    return uploadService.uploadToTelegram(holderType, FileType.VIDEO, file).map(ResponseEntity::ok);
  }
}
