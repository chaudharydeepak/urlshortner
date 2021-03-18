package com.dccorp.urlshortner.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse {
    String shortURL;
    String URL;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Object respObj;

    public ServiceResponse() {
    }

    public ServiceResponse(String shortURL, String URL, LocalDateTime startDate, LocalDateTime endDate, Object respObj) {
        this.shortURL = shortURL;
        this.URL = URL;
        this.startDate = startDate;
        this.endDate = endDate;
        this.respObj = respObj;
    }

    public String getShortURL() {
        return this.shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public String getURL() {
        return this.URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Object getRespObj() {
        return this.respObj;
    }

    public void setRespObj(Object respObj) {
        this.respObj = respObj;
    }

    public String toString() {
        return "ServiceResponse(shortURL=" + this.getShortURL() + ", URL=" + this.getURL() + ", startDate=" + this.getStartDate() + ", endDate=" + this.getEndDate() + ", respObj=" + this.getRespObj() + ")";
    }
}
