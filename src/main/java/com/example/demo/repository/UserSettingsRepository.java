package com.example.demo.repository;

import com.example.demo.model.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {

    @Query(name = "SELECT * FROM user_settings us " +
            "LEFT JOIN USER u ON u.USER_SETTINGS_ID  = us.ID " +
            "WHERE u.user_settings_id = :id",
            nativeQuery = true)
    UserSettings findUserSettingsById(Long id);
}
