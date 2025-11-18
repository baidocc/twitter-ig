package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProfileRequest {

    
    @Size(max = 100, message = "The description can't be longer than 100 characters")
    private String description;
 
    public ProfileRequest() {
    }

    public ProfileRequest(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
