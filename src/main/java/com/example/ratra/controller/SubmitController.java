package com.example.ratra.controller;

import com.example.ratra.model.Submit;
import com.example.ratra.model.form.DeleteImageForm;
import com.example.ratra.model.form.SubmitFormWithType;
import com.example.ratra.service.impl.ImageServiceImpl;
import com.example.ratra.service.impl.SubmitServiceImpl;
import com.example.ratra.util.ApiUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(ApiUrl.API_PATH)
@CrossOrigin(origins = "*", maxAge = 3600)
public class SubmitController {

    @Autowired
    SubmitServiceImpl submitService;

    @Autowired
    ImageServiceImpl imageService;

    @RequestMapping("/submits")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getSubmitsByUser(@RequestParam(defaultValue = "0") Integer pageNumber,
                                            @RequestParam(defaultValue = "1") Integer pageSize,
                                            @RequestParam(defaultValue = "id") String sortBy,
                                            @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(submitService.getSubmitsByUser(pageNumber, pageSize, sortBy, direction));
    }

    @RequestMapping("/submitsByUser")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity getSubmitsByUser(@RequestParam String name,
                                           @RequestParam(defaultValue = "0") Integer pageNumber,
                                           @RequestParam(defaultValue = "1") Integer pageSize,
                                           @RequestParam(defaultValue = "id") String sortBy,
                                           @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(submitService.getSubmitsForUser(name, pageNumber, pageSize, sortBy, direction));
    }

    @GetMapping("/submit/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Submit> getSubmit(@PathVariable Long id) {
        return ResponseEntity.ok(submitService.getById(id));
    }

    @GetMapping("/submits/count")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Integer> getSubmitsCount() {
        return ResponseEntity.ok(submitService.getSubmitCount());
    }

    @PostMapping(value = "/submit/new",
                consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void create(
            @RequestPart("submit") SubmitFormWithType submitForm,
            @RequestPart(value = "files", required = false) MultipartFile[] files) {
        submitService.saveSubmit(submitForm, files);
    }

    @PutMapping("/submit/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Submit> update(@PathVariable Long id,
                                         @RequestPart("submit") SubmitFormWithType submitForm,
                                         @RequestPart(value = "files", required = false) MultipartFile[] files) {
        return ResponseEntity.ok(submitService.updateSubmitForm(id, submitForm, files));
    }

    @DeleteMapping("/submit/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        submitService.deleteSubmit(id);
    }

    @DeleteMapping(value= "/image", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity deleteImage(@RequestBody DeleteImageForm image){
        return ResponseEntity.ok(imageService.deleteImage(image.getId()));
    }
}
