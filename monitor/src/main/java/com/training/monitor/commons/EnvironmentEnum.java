package com.training.monitor.commons;

public enum EnvironmentEnum {
    DEV("dev"),
    STAGE("stage"),
    PROD("live");


    private final String value;

    EnvironmentEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
