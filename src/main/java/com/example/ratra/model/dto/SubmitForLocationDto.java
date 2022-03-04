package com.example.ratra.model.dto;

import com.example.ratra.model.SubmitType;
import lombok.Data;

@Data
public class SubmitForLocationDto {
    private String title;
    private SubmitType type;
    private UserUsernameOnlyDto user;
}
