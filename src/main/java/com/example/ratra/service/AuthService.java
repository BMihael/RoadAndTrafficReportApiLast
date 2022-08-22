package com.example.ratra.service;

import com.example.ratra.model.form.LoginForm;
import com.example.ratra.model.form.RegisterForm;
import com.example.ratra.model.response.JwtResponse;

public interface AuthService {
    JwtResponse autenticate(LoginForm loginForm);
    String register(RegisterForm registerForm);
}
