package com.example.ratra.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test/useroradmin")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        return ">>> User or Admin Contents!";
    }

    @GetMapping("/api/test/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return ">>> Admin only Contents!";
    }

    @GetMapping("/api/test/user")
    @PreAuthorize("hasRole('USER')")
    public String userOnlyAccess() {
        return ">>> User only Contents!";
    }
}
