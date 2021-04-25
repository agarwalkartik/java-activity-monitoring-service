package com.example.restservice;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ActivityTotal {
    private int value;

    ActivityTotal(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
