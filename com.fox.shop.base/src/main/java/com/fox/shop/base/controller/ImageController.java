package com.fox.shop.base.controller;

import com.fox.shop.base.service.i.ImageService;
import com.fox.shop.protocol.ImageByteModel;
import com.fox.shop.protocol.ImageModel;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "api/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(
            final ImageService imageService
    ) {
        this.imageService = imageService;
    }

    /*------------------------------------category--------------------------------------------*/
    @PostMapping("/upload/main/to/category/{categoryId}")
    public ResponseEntity<ImageModel> uploadMainToCategory(
            @PathVariable final long categoryId,
            @RequestParam final MultipartFile file
    ) {
        return ResponseEntity.ok(imageService.uploadMainToCategory(categoryId, file));
    }

    @GetMapping("/main/download/byte/by/kitchen/{categoryId}")
    public ResponseEntity<ImageByteModel> downloadMainByteByCategory(
            @PathVariable final long categoryId
    ) {
        return ResponseEntity.ok(imageService.downloadMainByteByCategory(categoryId));
    }

    /*------------------------------------product--------------------------------------------*/
    @PostMapping("/upload/main/to/product/{productId}")
    public ResponseEntity<ImageModel> uploadMainToProduct(
            @PathVariable final long productId,
            @RequestParam final MultipartFile file
    ) {
        return ResponseEntity.ok(imageService.uploadMainToProduct(productId, file));
    }

    @GetMapping("/main/download/byte/by/product/{productId}")
    public ResponseEntity<ImageByteModel> downloadMainByteByProduct(
            @PathVariable final long productId
    ) {
        return ResponseEntity.ok(imageService.downloadMainByteByProduct(productId));
    }


    /*------------------------------------image--------------------------------------------*/
    @GetMapping(value = "/download/byte/{imageId}")
    public ResponseEntity<ImageByteModel> download(
            @PathVariable final String imageId
    ) {
        return ResponseEntity.ok(imageService.downloadById(Long.parseLong(imageId)));
    }

    @GetMapping(value = "/download/by/name/{imageName}")
    public ResponseEntity<byte[]> downloadByName(
            @PathVariable final String imageName
    ) {
        final Pair<String, byte[]> nameFIle = Pair.of(imageName, imageService.downloadByName(imageName));
        return ResponseEntity.ok().
                contentType(MediaType.APPLICATION_OCTET_STREAM).
                header("Content-Disposition", "inline; filename=" + nameFIle.getFirst()).
                header("Accept", buildMediaTypeFromFileName(nameFIle.getFirst()).getType()).
                body(nameFIle.getSecond());
    }

    @PostMapping("/upload")
    public ResponseEntity<ImageModel> upload(
            @RequestParam MultipartFile file
    ) {
        return ResponseEntity.ok(imageService.upload(file));
    }

    private MediaType buildMediaTypeFromFileName(final String fileName) {
        return "png".equals(fileName.split("\\.")[1])
                ? MediaType.IMAGE_PNG
                : MediaType.IMAGE_JPEG;
    }
}












