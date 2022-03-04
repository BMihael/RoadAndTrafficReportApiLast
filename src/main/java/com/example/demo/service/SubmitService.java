package com.example.demo.service;

import com.example.demo.model.Submit;
import com.example.demo.model.form.SubmitFormWithType;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface SubmitService {
    Submit saveSubmit(SubmitFormWithType submitFormWithType, MultipartFile[] files);

    Page<Submit> getSubmitsForUser(Integer pageNumber, Integer pageSize, String sortBy, String direction);

    void deleteSubmit(Long id);

    Submit updateSubmitForm(Long id, SubmitFormWithType submitFormWithType, MultipartFile[] files);

    Integer getSubmitCount();

    Submit getById(Long id);
}
