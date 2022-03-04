package com.example.ratra.model.response;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String bearerString = "Bearer ";

    public JwtResponse(String token) {
        this.token = token;
    }

}
