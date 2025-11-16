package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostRequest {

    @NotBlank( message = "Title field is mandatory." )
    @Size(max = 100, message = "The title can't be longer than 100 characters") 
    private String title;

    @NotBlank( message = "Body field is mandatory." )
    @Size(max = 1000, message = "The body can't be longer than 1000 characters")
    private String body;

    
    private Long userId;

    
    public PostRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
