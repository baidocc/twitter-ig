package com.example.backend.service;

import com.example.backend.dto.ProfileRequest;
import com.example.backend.model.Profile;
import com.example.backend.model.User;
import com.example.backend.repository.ProfileRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class ProfileService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository,
                    UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public Profile getProfileByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        return profileRepository.findByUser(user)
            .orElseGet(() -> new Profile(user, ""));
    }

    public Profile getMyProfile() {
        // Logica e similară tehnic, dar semantic e diferită.
        // Aici știm sigur că userul există (fiindcă e logat cu token valid)

         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // username-ul la tine e emailul
        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Eroare critică: Userul din token nu există în DB."));

        return profileRepository.findByUser(user)
                .orElseGet(() -> new Profile(user, ""));
    }

    public void editMyProfile(ProfileRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // username-ul la tine e emailul
        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = profileRepository.findByUser(user)
                .orElse(null);

        if (profile == null) {
            // Dacă nu avea profil creat (descriere), îi facem unul acum
            profile = new Profile(user, request.getDescription());
        } else {
            // Dacă avea, doar actualizăm textul
            profile.setDescription(request.getDescription());
        }

        profileRepository.save(profile);
    }
    

}
