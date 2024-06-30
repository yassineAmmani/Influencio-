package com.influencio.server.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "influencers")
public class Influencer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastPostUrl() {
        return lastPostUrl;
    }

    public void setLastPostUrl(String lastPostUrl) {
        this.lastPostUrl = lastPostUrl;
    }

    public String getLastPostCaption() {
        return lastPostCaption;
    }

    public void setLastPostCaption(String lastPostCaption) {
        this.lastPostCaption = lastPostCaption;
    }

    public Integer getLastPostLikes() {
        return lastPostLikes;
    }

    public void setLastPostLikes(Integer lastPostLikes) {
        this.lastPostLikes = lastPostLikes;
    }

    public Integer getLastPostComments() {
        return lastPostComments;
    }

    public void setLastPostComments(Integer lastPostComments) {
        this.lastPostComments = lastPostComments;
    }

    public Instant getLastPostTimestamp() {
        return lastPostTimestamp;
    }

    public void setLastPostTimestamp(String lastPostTimestamp) {
        this.lastPostTimestamp = Instant.parse(lastPostTimestamp);
    }

    private String fullName;
    private String lastPostUrl;

    @Column(length = 1000)
    private String lastPostCaption;

    private Integer lastPostLikes;
    private Integer lastPostComments;
    private Instant lastPostTimestamp;

    // Getters and setters

    @Column(name = "path_last_post_image")
    private String pathLastPostImage;

    // Getters and setters

    public String getPathLastPostImage() {
        return pathLastPostImage;
    }

    public void setPathLastPostImage(String pathLastPostImage) {
        this.pathLastPostImage = pathLastPostImage;
    }
    // ...
}