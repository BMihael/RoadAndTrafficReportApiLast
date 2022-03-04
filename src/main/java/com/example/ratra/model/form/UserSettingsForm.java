package com.example.ratra.model.form;

import lombok.Data;

@Data
public class UserSettingsForm {
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String postcode;
    private String email;
    private String country;
}
