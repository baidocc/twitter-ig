package com.example.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "The Username field is mandatory")
    @Size(max = 20, message = "The username can't be longer than 20 characters")
    private String username;

    @NotBlank(message = "The Email field is mandatory")
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "The Password field is mandatory")
    @Size(min = 6, message = "The Password needs to be at least 6 characters long")
    private String password;

    public RegisterRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
