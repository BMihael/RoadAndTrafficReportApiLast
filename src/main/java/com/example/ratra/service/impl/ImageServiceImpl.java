package com.example.ratra.service.impl;

import com.example.ratra.exception.ImageNotFoundException;
import com.example.ratra.model.response.GenericResponse;
import com.example.ratra.model.response.ResponseMessages;
import com.example.ratra.repository.ImageRepository;
import com.example.ratra.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public GenericResponse deleteImage(Long id) {
        imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException(ResponseMessages.IMAGE_NOT_FOUND));
        imageRepository.deleteById(id);
        return new GenericResponse(ResponseMessages.IMAGE_DELETED);
    }
}
