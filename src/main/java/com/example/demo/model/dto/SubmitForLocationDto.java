package com.example.demo.model.dto;

import com.example.demo.model.SubmitType;
import lombok.Data;

@Data
public class SubmitForLocationDto {
    private String title;
    private SubmitType type;
    private UserUsernameOnlyDto user;
}
