package com.example.ratra.controller;

import com.example.ratra.model.User;
import com.example.ratra.model.dto.UserSettingsDto;
import com.example.ratra.model.form.UserSettingsForm;
import com.example.ratra.service.impl.UserServiceImpl;
import com.example.ratra.util.ApiUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping(ApiUrl.API_PATH)
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @RequestMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(userServiceImpl.getUser());
    }

    @RequestMapping("/user/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userServiceImpl.getUserByUsername(username));
    }

    @RequestMapping("/user/delete")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteUser() {
        userServiceImpl.deleteUser();
    }

    @RequestMapping("/user/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(@PathVariable Long id) {
        userServiceImpl.deleteUserById(id);
    }

    @RequestMapping("/user/settings")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserSettingsDto> getUserSettings() {
        return ResponseEntity.ok(userServiceImpl.getUserSettings());
    }

    @PutMapping("/user/settings")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserSettingsDto> updateUserSettings(
            @RequestBody UserSettingsForm userSettingsForm) {
        return ResponseEntity.ok(userServiceImpl.updateUserSettings(userSettingsForm));
    }

    @PostMapping(value = "/user/settings/profilepicture/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void updateUserProfilePicture(@RequestParam("file") MultipartFile file) throws IOException {
        userServiceImpl.saveUserProfilePicture(file);
    }
}
