package com.example.rate_limiting_demo.dto;

import com.example.rate_limiting_demo.model.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {

    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "email cannot be blank")
    @Email(message = "email format is invalid")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;

    @NotNull(message = "role is required")
    private Role role;
}
