package com.example.backend.controller;

import java.util.List;

import com.example.backend.dto.PostRequest;
import com.example.backend.model.Post;
import com.example.backend.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.repository.UserRepository;
import com.example.backend.model.User;

@RestController
@RequestMapping("/api/post")
@CrossOrigin(origins = "http://localhost:3000")

public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService=postService;
    }

     // Creeaza un post nou
    @PostMapping("/create")
    public ResponseEntity<Void> createPost(@Valid @RequestBody PostRequest request) {
    postService.createPost(request);
    return ResponseEntity.status(201).build(); // 201 Created
}


    // Toate posturile unui user
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Post>> getAllPostsByUser(@PathVariable String username) {
        
        List<Post> posts = postService.getAllPostsByUsername(username);
        return ResponseEntity.ok(posts);
    }

    // Toate posturile (de exemplu pentru feed global)
    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // Stergere post
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
    
    
}
