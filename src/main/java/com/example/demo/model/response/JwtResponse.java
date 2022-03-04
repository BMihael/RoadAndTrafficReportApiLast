package com.example.demo.model.response;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String bearerString = "Bearer ";

    public JwtResponse(String token) {
        this.token = token;
    }

}
