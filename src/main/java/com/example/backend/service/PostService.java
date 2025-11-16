package com.example.backend.service;



import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.example.backend.model.User;
import com.example.backend.dto.PostRequest;
import com.example.backend.model.Post;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.PostRepository;

@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository,
                       UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void createPost( PostRequest request){

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post(
                request.getTitle(),
                request.getBody(),
                user
        );

        postRepository.save(post);
    }

    public void deletePost( Long postId){

        Post post = postRepository.findById(postId)
        .orElseThrow(() -> new RuntimeException("Post not found"));

        
        // verificam daca postarea apartine userului---trebuie facut
        
        ///delete comments
        postRepository.deleteById(postId);

    }

    public List<Post> getAllPosts(){
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Post> getAllPostsByUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return postRepository.findByUserOrderByCreatedAtDesc(user);
    }
    

}
