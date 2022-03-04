package com.example.ratra.model.form;

import com.example.ratra.model.Location;
import com.example.ratra.model.SubmitType;
import lombok.Data;

@Data
public class SubmitFormWithType {
    private String title;
    private String description;
    private SubmitType type;
    private Location location;
}
