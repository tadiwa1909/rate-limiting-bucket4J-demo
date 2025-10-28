package com.example.rate_limiting_demo.dto;

import com.example.rate_limiting_demo.model.Role;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPatchDto {

    private Role role;
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;
}
