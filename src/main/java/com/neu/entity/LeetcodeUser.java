package com.neu.entity;

public class LeetcodeUser {


    private Integer id;
    private String name;
    private String avatar;//头像url
    private Integer likes;
    private Integer collections;
    private Integer follows;
    private Integer browses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getCollections() {
        return collections;
    }

    public void setCollections(Integer collections) {
        this.collections = collections;
    }

    public Integer getFollows() {
        return follows;
    }

    public void setFollows(Integer follows) {
        this.follows = follows;
    }

    public Integer getBrowses() {
        return browses;
    }

    public void setBrowses(Integer browses) {
        this.browses = browses;
    }
}
