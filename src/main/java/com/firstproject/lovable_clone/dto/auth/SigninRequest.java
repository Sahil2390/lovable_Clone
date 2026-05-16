package com.firstproject.lovable_clone.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SigninRequest(
        @Email @NotBlank String username,
         @Size(min=4 ,max=50) String password
) {
}
