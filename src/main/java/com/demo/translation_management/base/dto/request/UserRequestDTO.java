package com.demo.translation_management.base.dto.request;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String username;
    private String password;
    private String status;
}
