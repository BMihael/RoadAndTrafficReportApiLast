package com.example.ratra.model.form;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginForm {

    @NotBlank(message = "Username is mandatory")
    @NotNull(message = "Username cannot be null")
    @Size(min = 3, max = 255)
    private String username;

    @NotBlank(message = "Password is mandatory")
    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 255)
    private String password;
}
