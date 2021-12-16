package com.fox.shop.storage.controller;

import com.fox.shop.storage.service.i.DownloadService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

@RestController
@RequestMapping("api/v1/download")
public class DownloadController {

  private final DownloadService downloadService;

  public DownloadController(
      final DownloadService downloadService
  ) {
    this.downloadService = downloadService;
  }

  @GetMapping(value = "{fileId}", produces = APPLICATION_OCTET_STREAM_VALUE)
  public Mono<ResponseEntity<Resource>> download(
      @PathVariable final String fileId
  ) {
    return downloadService.downloadById(fileId).map(ResponseEntity::ok);
  }
}
