package com.example.ratra.controller;

import com.example.ratra.model.form.LoginForm;
import com.example.ratra.model.form.RegisterForm;
import com.example.ratra.service.impl.AuthServiceImpl;
import com.example.ratra.util.ApiUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiUrl.API_PATH)
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    @RequestMapping(value = ApiUrl.AUTH + "/signin", method = RequestMethod.POST)
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginForm loginForm) {
        return ResponseEntity.ok(authService.authenticate(loginForm));
    }

    @RequestMapping(value = ApiUrl.AUTH + "/signup", method = RequestMethod.POST)
    public ResponseEntity registerUser(@Valid @RequestBody RegisterForm registerForm) {
        return ResponseEntity.ok(authService.register(registerForm));
    }
}
