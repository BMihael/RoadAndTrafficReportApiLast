package com.example.ratra.mapper;

import com.example.ratra.model.Location;
import com.example.ratra.model.Submit;
import com.example.ratra.model.User;
import com.example.ratra.model.UserSettings;
import com.example.ratra.model.dto.LocationDto;
import com.example.ratra.model.dto.SubmitForLocationDto;
import com.example.ratra.model.dto.UserSettingsDto;
import com.example.ratra.model.dto.UserUsernameOnlyDto;
import com.example.ratra.model.form.SubmitFormWithType;
import com.example.ratra.model.form.UserSettingsForm;
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
