package com.example.backend.repository;

import com.example.backend.model.Following;
import com.example.backend.model.FollowingId;
import com.example.backend.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface FollowingRepository extends JpaRepository<Following, FollowingId>{

     boolean existsByFollowerAndFollowed(User follower, User followed);

    // Găsește relația de follow (util pentru unfollow)
    Optional<Following> findByFollowerAndFollowed(User follower, User followed);

    List<Following> findByFollowerOrderByCreatedAtDesc(User follower);
    List<Following> findByFollowedOrderByCreatedAtDesc(User followed);

    long countByFollowed(User followed);
    long countByFollower(User follower);
}