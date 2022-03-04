package com.example.demo.mapper;

import com.example.demo.model.Location;
import com.example.demo.model.Submit;
import com.example.demo.model.User;
import com.example.demo.model.UserSettings;
import com.example.demo.model.dto.LocationDto;
import com.example.demo.model.dto.SubmitForLocationDto;
import com.example.demo.model.dto.UserSettingsDto;
import com.example.demo.model.dto.UserUsernameOnlyDto;
import com.example.demo.model.form.SubmitFormWithType;
import com.example.demo.model.form.UserSettingsForm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    UserUsernameOnlyDto userToUserUsernameOnlyDto(User user);

    Submit submitFormWithTypeToSubmit(SubmitFormWithType submitFormWithType);

    UserSettingsDto userSettingsToUserSettingsDto(UserSettings userSettings);

    UserSettings userSettingsFormToUserSettings(UserSettingsForm userSettingsForm);

    LocationDto locationToLocationDto(Location location);

    List<LocationDto> locationListToLocationDtoList(List<Location> locations);

    SubmitForLocationDto submitToSubmitForLocationDto(Submit submit);
}
