package com.example.ratra.model.dto;

import lombok.Data;

@Data
public class UserSettingsDto {
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String postcode;
    private String email;
    private String country;
    private byte[] profilePictureData;
}
