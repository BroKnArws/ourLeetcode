package com.neu.entity;

import com.neu.common.Utils;
import com.neu.dto.request.CreateDraftRequest;
import com.neu.dto.request.EditArticleRequest;
import com.neu.dto.request.EditDraftRequest;

import java.util.Date;

public class Draft {
    private Integer id;
    private Integer topicId;
    private Integer authorId;
    private String title;
    private Date editTime;
    private String content;
    private String thumbnail;


    public Draft() {
    }

    public Draft(CreateDraftRequest request) {
        this.topicId = request.getTopicId();
        this.title = request.getTitle();
        this.editTime = Utils.currentTime();
        this.content = request.getContent();
        this.thumbnail = request.getThumbnail();
    }
    public Draft(EditDraftRequest request) {
        this.id = request.getId();
        this.topicId = request.getTopicId();
        this.title = request.getTitle();
        this.editTime = Utils.currentTime();
        this.content = request.getContent();
        this.thumbnail = request.getThumbnail();
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
