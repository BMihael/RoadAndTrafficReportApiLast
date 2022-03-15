package com.example.ratra.repository;

import com.example.ratra.model.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {

    @Query(name = "SELECT * FROM users_settings us " +
            "LEFT JOIN USER u ON u.USER_SETTINGS_ID  = us.ID " +
            "WHERE u.user_settings_id = :id",
            nativeQuery = true)
    UserSettings findUserSettingsById(Long id);
}
