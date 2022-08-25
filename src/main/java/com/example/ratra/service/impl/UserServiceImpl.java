package com.example.ratra.service.impl;

import com.example.ratra.exception.UsernameNotFoundException;
import com.example.ratra.mapper.MapStructMapper;
import com.example.ratra.model.User;
import com.example.ratra.model.UserSettings;
import com.example.ratra.model.dto.UserSettingsDto;
import com.example.ratra.model.form.UserSettingsForm;
import com.example.ratra.model.response.ResponseMessages;
import com.example.ratra.repository.UserRepository;
import com.example.ratra.repository.UserSettingsRepository;
import com.example.ratra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSettingsRepository userSettingsRepository;

    @Autowired
    MapStructMapper mapStructMapper;

    private User user() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(ResponseMessages.USERNAME_NOT_FOUND);
        }
        return user;
    }

    @Override
    public User getUser() {
        User user = user();
        return user;
    }

    @Override
    public void deleteUser() {
        User user = user();
        userRepository.delete(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }



    @Override
    public UserSettingsDto getUserSettings() {
        User user = user();
        UserSettingsDto userSettingsDto = mapStructMapper.
                userSettingsToUserSettingsDto(userSettingsRepository.findUserSettingsById(user.getId()));
        return userSettingsDto;
    }

    @Override
    public UserSettingsDto updateUserSettings(UserSettingsForm userSettingsForm) {
        User user = user();
        UserSettings userSettings = mapStructMapper.userSettingsFormToUserSettings(userSettingsForm);
        userSettings.setId(user.getUserSettings().getId());
        userSettings.setUser(user);
        userSettings.setProfilePictureData(user.getUserSettings().getProfilePictureData());
        user.setUserSettings(userSettings);
        userRepository.save(user);
        UserSettingsDto userSettingsDto = mapStructMapper.userSettingsToUserSettingsDto(user.getUserSettings());
        return userSettingsDto;
    }

    @Override
    public void saveUserProfilePicture(MultipartFile file) throws IOException {
        User user = user();
        UserSettings userSettings = user.getUserSettings();
        userSettings.setProfilePictureData(file.getBytes());
        userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User Not Found with username or email : " + username);
        }
       return user;
    }

}
