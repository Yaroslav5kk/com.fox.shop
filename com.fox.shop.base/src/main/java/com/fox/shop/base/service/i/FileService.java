package com.fox.shop.base.service.i;

public interface FileService {
    void write(
            byte []file,
            String inputPath
    );

    byte[] read(
            String inputPath
    );
}
