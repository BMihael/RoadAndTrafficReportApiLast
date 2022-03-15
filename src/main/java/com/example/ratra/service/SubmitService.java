package com.example.ratra.service;

import com.example.ratra.model.Submit;
import com.example.ratra.model.form.SubmitFormWithType;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface SubmitService {
    Submit saveSubmit(SubmitFormWithType submitFormWithType, MultipartFile[] files);

    Page<Submit> getSubmitsByUser(Integer pageNumber, Integer pageSize, String sortBy, String direction);

    void deleteSubmit(Long id);

    Submit updateSubmitForm(Long id, SubmitFormWithType submitFormWithType, MultipartFile[] files);

    Integer getSubmitCount();

    Submit getById(Long id);

    Page<Submit> getSubmitsForUser(String user, Integer pageNumber, Integer pageSize, String sortBy, String direction);
}
