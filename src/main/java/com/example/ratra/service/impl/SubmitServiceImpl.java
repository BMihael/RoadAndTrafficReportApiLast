package com.example.ratra.service.impl;

import com.example.ratra.exception.SubmitNotFoundException;
import com.example.ratra.mapper.MapStructMapper;
import com.example.ratra.model.ImageFile;
import com.example.ratra.model.Location;
import com.example.ratra.model.Submit;
import com.example.ratra.model.User;
import com.example.ratra.model.form.SubmitFormWithType;
import com.example.ratra.repository.SubmitRepository;
import com.example.ratra.service.SubmitService;
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

    @Autowired
    LocationServiceImpl locationService;

    @Override
    public Submit saveSubmit(SubmitFormWithType submitFormWithType, MultipartFile[] files) {
        User user = userServiceImpl.getUser();
        Submit submit = mapStructMapper.submitFormWithTypeToSubmit(submitFormWithType);
        submit.setDate(LocalDate.now());
        submit.setUser(user);

        if (files != null) {
            List<ImageFile> images = new ArrayList<>();
            Arrays.stream(files).forEach(file -> {
                if(!file.getOriginalFilename().equals("")) {
                    ImageFile image = new ImageFile();
                    image.setSubmit(submit);
                    try {
                        image.setType(file.getContentType());
                        image.setData(file.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    images.add(image);
                }
            });
            if (!(images.isEmpty())) {
                submit.setImages(images);
            } else {
                submit.setImages(null);
            }
        }

        log.info("Saving submit to database");
        return submitRepository.save(submit);
    }

    @Override
    public Page<Submit> getSubmitsByUser(Integer pageNumber, Integer pageSize, String sortBy, String direction) {
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

        Location location = locationService.getLocationBySubmitId(submit.getId());

        location.setLatitude(submitFormWithType.getLocation().getLatitude());
        location.setLongitude(submitFormWithType.getLocation().getLongitude());

        submit.setLocation(location);

        if (files != null) {
            List<ImageFile> images = new ArrayList<>();
            Arrays.stream(files).forEach(file -> {
                if(!file.getOriginalFilename().equals("")){
                    ImageFile image = new ImageFile();
                    image.setSubmit(submit);
                    try {
                        image.setType(file.getContentType());
                        image.setData(file.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    images.add(image);
                }
            });

            if (!(images.isEmpty())) {
                submit.setImages(images);
            } else {
                submit.setImages(null);
            }
        }
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

    @Override
    public Page<Submit> getSubmitsForUser(String username, Integer pageNumber, Integer pageSize, String sortBy, String direction) {
        User user = userServiceImpl.getUserByUsername(username);
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(getDirection(direction), sortBy));
        Page<Submit> pagedResult = submitRepository.findSubmitsByUserId(user.getId(), page);
        return pagedResult;
    }

}
