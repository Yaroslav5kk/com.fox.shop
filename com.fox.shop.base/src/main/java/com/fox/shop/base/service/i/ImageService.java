package com.fox.shop.base.service.i;

import com.fox.shop.protocol.ImageByteModel;
import com.fox.shop.protocol.ImageModel;
import com.fox.shop.protocol.request.ImageOnCreateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    /*------------------------------------product--------------------------------------------*/
    ImageModel uploadMainToProduct(
            long productId,
            MultipartFile file
    );

    ImageByteModel downloadMainByteByProduct(
            long productId
    );

    /*------------------------------------kitchen--------------------------------------------*/
    ImageModel uploadMainToCategory(
            long kitchenId,
            MultipartFile file
    );

    ImageByteModel downloadMainByteByCategory(
            long kitchenId
    );


    ImageByteModel downloadById(
            long imageId
    );

    byte[] downloadByName(
            String imageName
    );

    ImageModel upload(MultipartFile file);

    ImageModel save(ImageOnCreateRequest image);

    ImageModel save(ImageModel image);

    ImageModel findById(long id);
}
