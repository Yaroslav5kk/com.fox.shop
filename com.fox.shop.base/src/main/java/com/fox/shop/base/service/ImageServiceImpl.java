package com.fox.shop.base.service;

import com.fox.shop.base.converter.ImageConverter;
import com.fox.shop.base.entity.CategoryEntity;
import com.fox.shop.base.entity.ImageEntity;
import com.fox.shop.base.entity.ProductEntity;
import com.fox.shop.base.repository.CategoryRepository;
import com.fox.shop.base.repository.ImageRepository;
import com.fox.shop.base.repository.ProductRepository;
import com.fox.shop.base.service.i.FileService;
import com.fox.shop.base.service.i.ImageService;
import com.fox.shop.protocol.ImageByteModel;
import com.fox.shop.protocol.ImageModel;
import com.fox.shop.protocol.request.ImageOnCreateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${store.path.image.merchant}")
    private String merchantPath;
    @Value("${store.path.image.base}")
    private String basePath;

    private final ImageRepository imageRepository;
    private final FileService fileService;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ImageServiceImpl(
            final ImageRepository imageRepository,
            final FileService fileService,
            final CategoryRepository categoryRepository,
            final ProductRepository productRepository
    ) {
        this.imageRepository = imageRepository;
        this.fileService = fileService;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    /*------------------------------------category--------------------------------------------*/
    @Override
    public ImageModel uploadMainToCategory(
            final long kitchenId,
            final MultipartFile file
    ) {
        final ImageEntity uploadedImage = uploadEntity(file);
        final CategoryEntity kitchen = categoryRepository.getOne(kitchenId);
        kitchen.setImage(uploadedImage);
        categoryRepository.save(kitchen);
        return ImageConverter.fromEntity(uploadedImage);
    }

    @Override
    public ImageByteModel downloadMainByteByCategory(
            final long kitchenId
    ) {
        final CategoryEntity kitchen = categoryRepository.getOne(kitchenId);
        final ImageEntity image = kitchen.getImage();
        return new ImageByteModel(image.getId(), image.getName(), fileService.read(image.getUrl()));
    }

    /*------------------------------------product--------------------------------------------*/
    @Override
    public ImageModel uploadMainToProduct(
            final long productId,
            final MultipartFile file
    ) {
        final ImageEntity uploadedImage = uploadEntity(file);
        final ProductEntity product = productRepository.getOne(productId);
        product.setMainImage(uploadedImage);
        productRepository.save(product);
        return ImageConverter.fromEntity(uploadedImage);
    }

    @Override
    public ImageByteModel downloadMainByteByProduct(
            final long productId
    ) {
        final ProductEntity product = productRepository.getOne(productId);
        final ImageEntity image = product.getMainImage();
        return new ImageByteModel(image.getId(), image.getName(), fileService.read(image.getUrl()));
    }

    /*------------------------------------image--------------------------------------------*/
    @Override
    public ImageByteModel downloadById(
            final long imageId
    ) {
        final ImageEntity image = imageRepository.getOne(imageId);
        return new ImageByteModel(
                imageId,
                image.getName(),
                fileService.read(image.getUrl())
        );
    }

    @Override
    public byte[] downloadByName(
            final String imageName
    ) {
        final ImageEntity image = imageRepository.getByName(imageName);
        return fileService.read(image.getUrl());
    }

    @Override
    public ImageModel upload(final MultipartFile file) {
        return ImageConverter.fromEntity(uploadEntity(file));
    }

    @Override
    public ImageModel save(final ImageOnCreateRequest image) {
        return ImageConverter.fromEntity(imageRepository.save(ImageConverter.fromRequestOnCreateToEntity(image)));
    }

    @Override
    public ImageModel save(final ImageModel image) {
        return ImageConverter.fromEntity(imageRepository.save(ImageConverter.toEntity(image)));
    }

    @Override
    public ImageModel findById(final long id) {
        return ImageConverter.fromEntity(imageRepository.getOne(id));
    }

    private ImageEntity uploadEntity(final MultipartFile file) {
        try {
            final String fullPath = basePath + file.getOriginalFilename();
            fileService.write(file.getResource().getInputStream().readAllBytes(), fullPath);
            final ImageEntity result = new ImageEntity();
            result.setUrl(fullPath);
            result.setName(file.getName());
            result.setId(imageRepository.save(result).getId());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return new ImageEntity();
        }
    }

}
