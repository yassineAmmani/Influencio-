package com.influencio.server.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private int id;
    private String email;
    private String username;
    private String enterprise;
    private List<String> roles;
}