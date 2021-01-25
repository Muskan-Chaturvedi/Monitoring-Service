package com.training.monitor.dto;

import com.training.monitor.commons.EnvironmentEnum;
import com.training.monitor.commons.SortBy;

import java.util.Optional;

public class FilterDTO extends SortDTO<SortBy> {
    private String referrer;
    private String xApiKey;
    private String xClientId;
    private EnvironmentEnum env;
    private String apiVersion;
    private Integer responseCode;
    private OperationDTO<Long, Long> timestamp;
    private OperationDTO<Integer, Integer> latencyInMs;
    private OperationDTO<Integer, Integer> requestSize;
    private OperationDTO<Integer, Integer> responseSize;

    public FilterDTO() {
        super(SortBy.TIMESTAMP);
        env = EnvironmentEnum.PROD;
        timestamp = new OperationDTO<>(946665000000L, 2208969000000L);
        latencyInMs = new OperationDTO<>(0, 10000);
        requestSize = new OperationDTO<>(0, 50000);
        responseSize = new OperationDTO<>(0, 50000);
    }

    public Optional<String> getReferrer() {
        return getOptionalString(referrer);
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public Optional<String> getxApiKey() {
        return getOptionalString(xApiKey);
    }

    public void setxApiKey(String xApiKey) {
        this.xApiKey = xApiKey;
    }

    public Optional<String> getxClientId() {
        return getOptionalString(xClientId);
    }

    public void setxClientId(String xClientId) {
        this.xClientId = xClientId;
    }

    public EnvironmentEnum getEnv() {
        return env;
    }

    public void setEnv(EnvironmentEnum env) {
        this.env = env;
    }

    public Optional<String> getApiVersion() {
        return getOptionalString(apiVersion);
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public Optional<Integer> getResponseCode() {
        if (responseCode == null)
            return Optional.empty();
        return Optional.of(responseCode);
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public OperationDTO<Long, Long> getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OperationDTO<Long, Long> timestamp) {
        this.timestamp = timestamp;
    }

    public OperationDTO<Integer, Integer> getLatencyInMs() {
        return latencyInMs;
    }

    public void setLatencyInMs(OperationDTO<Integer, Integer> latencyInMs) {
        this.latencyInMs = latencyInMs;
    }

    public OperationDTO<Integer, Integer> getRequestSize() {
        return requestSize;
    }

    public void setRequestSize(OperationDTO<Integer, Integer> requestSize) {
        this.requestSize = requestSize;
    }

    public OperationDTO<Integer, Integer> getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(OperationDTO<Integer, Integer> responseSize) {
        this.responseSize = responseSize;
    }

    private Optional<String> getOptionalString(String string) {
        if (string == null)
            return Optional.empty();
        return Optional.of(string);
    }
}
