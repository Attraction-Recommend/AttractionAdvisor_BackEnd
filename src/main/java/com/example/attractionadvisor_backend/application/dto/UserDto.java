package com.example.attractionadvisor_backend.application.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    // Constructor, getters, and setters
}