package com.example.ratra.service.impl;

import com.example.ratra.exception.EmailIsAlreadyTakenException;
import com.example.ratra.exception.RoleNotFoundException;
import com.example.ratra.exception.UsernameIsAlreadyTakenException;
import com.example.ratra.model.Role;
import com.example.ratra.model.User;
import com.example.ratra.model.UserSettings;
import com.example.ratra.model.form.LoginForm;
import com.example.ratra.model.form.RegisterForm;
import com.example.ratra.model.response.GenericResponse;
import com.example.ratra.model.response.JwtResponse;
import com.example.ratra.model.response.ResponseMessages;
import com.example.ratra.repository.RoleRepository;
import com.example.ratra.repository.UserRepository;
import com.example.ratra.service.AuthService;
import com.example.ratra.util.JwtProvider;
import com.example.ratra.util.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public JwtResponse authenticate(LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getUsername(),
                        loginForm.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);

        return new JwtResponse(jwt);
    }

    @Override
    public GenericResponse register(RegisterForm registerForm) {
        if (userRepository.existsByUsername(registerForm.getUsername())) {
            throw new UsernameIsAlreadyTakenException(ResponseMessages.USERNAME_TAKEN);
        }
        if (userRepository.existsByEmail(registerForm.getEmail())) {
            throw new EmailIsAlreadyTakenException(ResponseMessages.EMAIL_TAKEN);
        }

        User user = new User();
        user.setName(registerForm.getName());
        user.setUsername(registerForm.getUsername());
        user.setPassword(encoder.encode(registerForm.getPassword()));
        user.setEmail(registerForm.getEmail());

        List<Role> roles = new ArrayList<>();
        registerForm.getRoles().forEach(role -> {
            switch (role) {
                case "ROLE_ADMIN":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RoleNotFoundException("Role " + role + " not found!"));
                    roles.add(adminRole);
                    break;
                case "ROLE_USER":
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RoleNotFoundException("Role " + role + " not found!"));
                    roles.add(userRole);
                    break;
                default:
                    throw new RuntimeException("Fail! -> Cause: User " + role + "not find.");
            }
        });
        user.setRoles(roles);

        UserSettings userSettings = new UserSettings();
        userSettings.setEmail(registerForm.getEmail());
        user.setUserSettings(userSettings);

        userRepository.save(user);

        return new GenericResponse(ResponseMessages.USER_REGISTER_SUCCESSFULLY);
    }
}