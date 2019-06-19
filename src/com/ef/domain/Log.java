package com.ef.domain;

import java.time.LocalDateTime;

public class Log {
    private LocalDateTime dateAccessed;
    private String ip;
    private String request;
    private String requestStatus;
    private String userAgent;
    public String numberOfRequest;
    private Integer threshold;
    private String comment;

    public LocalDateTime getDateAccessed() {
        return dateAccessed;
    }

    public void setDateAccessed(LocalDateTime dateAccessed) {
        this.dateAccessed = dateAccessed;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getNumberOfRequest() {
        return numberOfRequest;
    }

    public void setNumberOfRequest(String numberOfRequest) {
        this.numberOfRequest = numberOfRequest;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
