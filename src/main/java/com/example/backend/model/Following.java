package com.example.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "following")
public class Following {

    @EmbeddedId // Folosește clasa de mai sus ca ID compus
    private FollowingId id;

    // Relația către User-ul care urmărește (FOLLOWER)
    @ManyToOne
    @MapsId("followerId") // Leagă câmpul 'followerId' din FollowingId
    @JoinColumn(name = "follower_id")
    private User follower;

    // Relația către User-ul care este urmărit (FOLLOWED)
    @ManyToOne
    @MapsId("followedId") // Leagă câmpul 'followedId' din FollowingId
    @JoinColumn(name = "followed_id")
    private User followed;

    // (Opțional, dar recomandat) Adaugă un timestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Constructor gol necesar pentru JPA
    public Following() {}

    // Constructor util
    public Following(User follower, User followed) {
        this.follower = follower;
        this.followed = followed;
        this.id = new FollowingId(follower.getId(), followed.getId());
    }

    // --- Getters și Setters ---

    public FollowingId getId() {
        return id;
    }

    public void setId(FollowingId id) {
        this.id = id;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowed() {
        return followed;
    }

    public void setFollowed(User followed) {
        this.followed = followed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}