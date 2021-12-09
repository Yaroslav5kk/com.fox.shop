package com.fox.shop.base.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.base.service.i.FileService;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileServiceImpl implements FileService {

    private final ObjectMapper objectMapper;

    public FileServiceImpl(
            final ObjectMapper objectMapper
    ) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void write(
            final byte[] file,
            final String inputPath
    ) {
        try (final FileOutputStream outputStream = new FileOutputStream(inputPath)) {
            outputStream.write(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] read(
            final String inputPath
    ) {
        try (final InputStream inputStream = new FileInputStream(inputPath)) {
            //return new ByteArrayResource(inputStream.readAllBytes());
            return inputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
