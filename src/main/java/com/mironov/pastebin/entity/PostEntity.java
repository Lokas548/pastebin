package com.mironov.pastebin.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;


@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Content")

public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String text;
    private String hash;

    @Column(name = "creation_time")
    private Long creationTime;

    @Column(name = "duration")
    private int linkDurationTime;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getLinkDurationTime() {
        return linkDurationTime;
    }

    public void setLinkDurationTime(int linkDurationTime) {
        this.linkDurationTime = linkDurationTime;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }
}
