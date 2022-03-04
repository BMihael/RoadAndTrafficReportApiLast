package com.example.demo.model.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class RegisterForm {

    @NotBlank
    private String name;

    @NotBlank(message = "Username is mandatory")
    @NotNull(message = "Username cannot be null")
    @Size(min = 3, max = 255)
    private String username;

    @NotBlank(message = "Password is mandatory")
    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank
    private List<String> roles;
}
