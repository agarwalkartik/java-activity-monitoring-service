package com.example.restservice;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Activity {
    @JsonProperty(access = Access.WRITE_ONLY)
    private int value;
    @JsonProperty(access = Access.WRITE_ONLY)
    private String key;
    @JsonProperty(access = Access.WRITE_ONLY)
    private long timestamp;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public Activity() {
        Date date = new Date();
        this.timestamp = date.getTime();
    }

}
