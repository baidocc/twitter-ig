package com.example.backend.controller;

import com.example.backend.dto.ProfileRequest;
import com.example.backend.model.Profile;
import com.example.backend.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.example.backend.repository.UserRepository;
import com.example.backend.model.User;
@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // 1. GET OTHER PROFILE
  
    @GetMapping("/{username}")
    public ResponseEntity<Profile> getUserProfile(@PathVariable String username) {
        
        Profile profile = profileService.getProfileByUsername(username);
        return ResponseEntity.ok(profile);
    }

    // 2. GET MY PROFILE
    
    @GetMapping("/me")
    public ResponseEntity<Profile> getMyProfile() {
        
        
        Profile myProfile = profileService.getMyProfile();
        return ResponseEntity.ok(myProfile);
    }

    // 3. EDIT MY PROFILE
    
    @PutMapping("/edit")
    public ResponseEntity<Void> editProfile(@RequestBody @Valid ProfileRequest request) {
        // Extragem cine e logat din JWT
       

        profileService.editMyProfile(request);
        return ResponseEntity.ok().build();
    }
}