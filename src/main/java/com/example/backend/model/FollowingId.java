package com.example.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable // Îi spune JPA că această clasă este folosită în interiorul altei entități
public class FollowingId implements Serializable {

    @Column(name = "follower_id") // ID-ul celui care urmărește
    private Long followerId;

    @Column(name = "followed_id") // ID-ul celui care este urmărit
    private Long followedId;

    // Constructor gol necesar pentru JPA
    public FollowingId() {}

    // Constructor util
    public FollowingId(Long followerId, Long followedId) {
        this.followerId = followerId;
        this.followedId = followedId;
    }

    // Getters și Setters
    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }

    public Long getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Long followedId) {
        this.followedId = followedId;
    }

    // Metodele equals() și hashCode() sunt CRITICE pentru cheile compuse!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowingId that = (FollowingId) o;
        return Objects.equals(followerId, that.followerId) &&
               Objects.equals(followedId, that.followedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followerId, followedId);
    }
}