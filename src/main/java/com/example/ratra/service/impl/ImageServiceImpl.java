package com.example.ratra.service.impl;

import com.example.ratra.exception.ImageNotFoundException;
import com.example.ratra.repository.ImageRepository;
import com.example.ratra.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public void deleteImage(Long id) {
        imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException("Image not found!"));
        imageRepository.deleteById(id);
    }
}
