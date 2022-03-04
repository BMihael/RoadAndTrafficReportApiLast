package com.example.demo.model.form;

import com.example.demo.model.Location;
import com.example.demo.model.SubmitType;
import lombok.Data;

@Data
public class SubmitFormWithType {
    private String title;
    private String description;
    private SubmitType type;
    private Location location;
}
