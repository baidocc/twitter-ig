package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.security.CustomUserDetails;
import com.example.backend.security.CustomUserDetailsService;
import com.example.backend.service.FollowingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.UserRepository;
import java.util.List;

@RestController
@RequestMapping("/api/follow")
public class FollowingController {

    private final FollowingService followingService;
    private final UserRepository userRepository;

    public FollowingController(FollowingService followingService, UserRepository userRepository) {
        this.followingService = followingService;
        this.userRepository = userRepository;
}

    // =======================
    // FOLLOW
    // =======================
    @PostMapping("/{followedUsername}")

    public ResponseEntity<Void> follow(
            @PathVariable String followedUsername)
    {
        System.out.println("P1");
        User followed = userRepository.findByUsername(followedUsername)
            .orElseThrow(() -> new IllegalArgumentException("User urmarit inexistent"));

        followingService.follow(followed.getId());
        System.out.println("P2")
;        return ResponseEntity.ok().build();
    }

    // =======================
    // UNFOLLOW
    // =======================
    @DeleteMapping("/unfollow/{followedUsername}")
    public ResponseEntity<Void> unfollow(
            @PathVariable String followedUsername
    ) {

        User followed = userRepository.findByUsername(followedUsername)
            .orElseThrow(() -> new IllegalArgumentException("User urmarit inexistent"));
        
            followingService.unfollow(followed.getId());
        return ResponseEntity.noContent().build();
    }

//     // =======================
//     // CHECK FOLLOW
//     // =======================
//     @GetMapping("/check")
//     public ResponseEntity<Boolean> isFollowing(
//             @RequestParam Long followerId,
//             @RequestParam Long followedId
//     ) {
//         boolean result = followingService.isFollowing(followerId, followedId);
//         return ResponseEntity.ok(result);
//     }

    // // =======================
    // // LIST FOLLOWING
    // // =======================
    // @GetMapping("/{userId}/following")
    // public ResponseEntity<List<Long>> getFollowing(@PathVariable Long userId) {
    //     List<Long> followingIds = followingService.getFollowing()
    //             .stream()
    //             .map(user -> user.getId())
    //             .toList();

    //     return ResponseEntity.ok(followingIds);
    // }

    // // =======================
    // // LIST FOLLOWERS
    // // =======================
    // @GetMapping("/{userId}/followers")
    // public ResponseEntity<List<Long>> getFollowers(@PathVariable Long userId) {
    //     List<Long> followerIds = followingService.getFollowers()
    //             .stream()
    //             .map(user -> user.getId())
    //             .toList();

    //     return ResponseEntity.ok(followerIds);
    // }

//     // =======================
//     // COUNTS
//     // =======================
//     @GetMapping("/{userId}/counts")
//     public ResponseEntity<FollowCountsResponse> getCounts(@PathVariable Long userId) {

//         long followers = followingService.countFollowers(userId);
//         long following = followingService.countFollowing(userId);

//         return ResponseEntity.ok(new FollowCountsResponse(followers, following));
//     }

    // =======================
    // DTO INTERN
    // =======================
    // public record FollowCountsResponse(long followers, long following) {}
}