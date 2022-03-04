package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserSettingsDto;
import com.example.demo.model.form.UserSettingsForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    User getUser();

    void deleteUser();

    UserSettingsDto getUserSettingsDto();

    UserSettingsDto updateUserSettings(UserSettingsForm userSettingsForm);

    void saveUserProfilePicture(MultipartFile file) throws IOException;
}
