package com.training.monitor.commons;

public enum SortBy {
    TIMESTAMP("timestamp"),
    LATENCY_IN_MS("latencyInMs"),
    REQUEST_SIZE("requestSize"),
    RESPONSE_SIZE("responseSize");


    private final String value;

    SortBy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
