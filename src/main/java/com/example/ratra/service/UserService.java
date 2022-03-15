package com.example.ratra.service;

import com.example.ratra.model.User;
import com.example.ratra.model.dto.UserSettingsDto;
import com.example.ratra.model.form.UserSettingsForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    User getUser();

    void deleteUser();

    UserSettingsDto getUserSettingsDto();

    UserSettingsDto updateUserSettings(UserSettingsForm userSettingsForm);

    void saveUserProfilePicture(MultipartFile file) throws IOException;

    User getUserByUsername(String name);
}
