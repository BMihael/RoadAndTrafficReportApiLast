package com.example.demo.controller;

import com.example.demo.exception.EmailIsAlreadyTakenException;
import com.example.demo.exception.RoleNotFoundException;
import com.example.demo.exception.UsernameIsAlreadyTakenException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserSettings;
import com.example.demo.model.form.LoginForm;
import com.example.demo.model.form.RegisterForm;
import com.example.demo.model.response.JwtResponse;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.ApiUrl;
import com.example.demo.util.JwtProvider;
import com.example.demo.util.RoleName;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApiUrl.API_PATH)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @RequestMapping("/auth/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @RequestMapping("/auth/signup")
    public ResponseEntity registerUser(@Valid @RequestBody RegisterForm registerForm) {
        if (userRepository.existsByUsername(registerForm.getUsername())) {
            throw new UsernameIsAlreadyTakenException("Username already taken!");
        }
        if (userRepository.existsByEmail(registerForm.getEmail())) {
            throw new EmailIsAlreadyTakenException("Email already taken!");
        }

        User user = new User();
        user.setName(registerForm.getName());
        user.setUsername(registerForm.getUsername());
        user.setPassword(encoder.encode(registerForm.getPassword()));
        user.setEmail(registerForm.getEmail());

        List<Role> roles = new ArrayList<Role>();
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
        return ResponseEntity.ok().body("User registered successfully!");
    }
}
