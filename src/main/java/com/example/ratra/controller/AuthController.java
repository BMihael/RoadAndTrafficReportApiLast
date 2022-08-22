package com.example.ratra.controller;

import com.example.ratra.model.form.LoginForm;
import com.example.ratra.model.form.RegisterForm;
import com.example.ratra.service.impl.AuthServiceImpl;
import com.example.ratra.util.ApiUrl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Data
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiUrl.API_PATH)
public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    @RequestMapping(ApiUrl.AUTH + "/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginForm loginForm) {
        return ResponseEntity.ok(authService.autenticate(loginForm));
    }

    @RequestMapping(ApiUrl.AUTH + "/signup")
    public ResponseEntity registerUser(@Valid @RequestBody RegisterForm registerForm) {
        return ResponseEntity.ok(authService.register(registerForm));
    }
}
