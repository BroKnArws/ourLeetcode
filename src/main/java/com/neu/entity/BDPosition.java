package com.neu.entity;

/**
 * @author: treblez
 * @description:BDPosition实体类
 * @data: 2020-04-06
 **/
public class BDPosition {


    private long id;
    private long jobId;
    private String jobName;
    private String jobCategory;
    private String jobType;
    private String workPlace;
    private int pubTime;
    private String description;
    private String require;

    public BDPosition(long id, long jobId, String jobName, String jobCategory, String jobType, String workPlace, int pubTime, String description, String require) {
        this.id = id;
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobCategory = jobCategory;
        this.jobType = jobType;
        this.workPlace = workPlace;
        this.pubTime = pubTime;
        this.description = description;
        this.require = require;
    }
    public BDPosition(){

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getWorkplace() {
        return workPlace;
    }

    public void setWorkplace(String workPlace) {
        this.workPlace = workPlace;
    }

    public int getTime() {
        return pubTime;
    }

    public void setTime(int time) {
        this.pubTime = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequire() {
        return require;
    }

    public void setRequire(String require) {
        this.require = require;
    }
}
