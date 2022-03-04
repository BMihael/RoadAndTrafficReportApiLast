package com.example.demo.service.impl;

import com.example.demo.exception.SubmitNotFoundException;
import com.example.demo.mapper.MapStructMapper;
import com.example.demo.model.ImageFile;
import com.example.demo.model.Submit;
import com.example.demo.model.User;
import com.example.demo.model.form.SubmitFormWithType;
import com.example.demo.repository.SubmitRepository;
import com.example.demo.service.SubmitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class SubmitServiceImpl implements SubmitService {

    @Autowired
    SubmitRepository submitRepository;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    MapStructMapper mapStructMapper;

    @Override
    public Submit saveSubmit(SubmitFormWithType submitFormWithType, MultipartFile[] files) {
        User user = userServiceImpl.getUser();
        Submit submit = mapStructMapper.submitFormWithTypeToSubmit(submitFormWithType);
        submit.setDate(LocalDate.now());
        submit.setUser(user);

        if (files != null) {
            List<ImageFile> images = new ArrayList<>();
            Arrays.stream(files).forEach(file -> {
                ImageFile image = new ImageFile();
                image.setSubmit(submit);
                try {
                    image.setType(file.getContentType());
                    image.setData(file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                images.add(image);
            });
            submit.setImages(images);
        } else {
            submit.setImages(null);
        }

        log.info("Saving submit to database");
        return submitRepository.save(submit);
    }

    @Override
    public Page<Submit> getSubmitsForUser(Integer pageNumber, Integer pageSize, String sortBy, String direction) {
        User user = userServiceImpl.getUser();
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(getDirection(direction), sortBy));
        Page<Submit> pagedResult = submitRepository.findSubmitsByUserId(user.getId(), page);
        return pagedResult;
    }

    private Sort.Direction getDirection(String direction) {
        if (direction.equals("desc"))
            return Sort.Direction.DESC;
        return Sort.Direction.ASC;
    }

    @Override
    public void deleteSubmit(Long id) {
        Submit submit = submitRepository.getById(id);
        submitRepository.delete(submit);
    }

    @Override
    public Submit updateSubmitForm(Long id, SubmitFormWithType submitFormWithType, MultipartFile[] files) {
        User user = userServiceImpl.getUser();

        Submit submit = submitRepository.findById(id).orElseThrow(() -> new SubmitNotFoundException("Submit not found!"));

        submit.setTitle(submitFormWithType.getTitle());
        submit.setDescription(submitFormWithType.getDescription());
        submit.setType(submitFormWithType.getType());
        submit.setLocation(submitFormWithType.getLocation());

        if (files != null) {
            List<ImageFile> images = new ArrayList<>();
            Arrays.stream(files).forEach(file -> {
                ImageFile image = new ImageFile();
                image.setSubmit(submit);
                try {
                    image.setType(file.getContentType());
                    image.setData(file.getBytes());
                    ///image.setSubmit(submit); Ovo sam stavio kada sam napisao za donji todo
                } catch (IOException e) {
                    e.printStackTrace();
                }
                images.add(image);
            });
            submit.setImages(images);
        }

        //TODO: ovdje se baca null negdje, mislim da je zbog referenci submita i slika
        return submitRepository.save(submit);
    }

    @Override
    public Integer getSubmitCount() {
        User user = userServiceImpl.getUser();
        return submitRepository.countById(user.getId());
    }

    @Override
    public Submit getById(Long id) {
        return submitRepository.findById(id).orElseThrow(() -> new SubmitNotFoundException("Submit not found!"));
    }


}
