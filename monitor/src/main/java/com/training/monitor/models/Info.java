package com.training.monitor.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;

@Document(collection = "info")
public class Info {

    @Id
    private String id;
    private String referrer;
    private String env;
    private String xApiKey;
    private String xClientId;
    private Map<String, Object> request;
    private Map<String, Object> response;
    private int responseCode;
    private long timestamp;
    private int latencyInMs;
    private int requestSize;
    private int responseSize;
    private String apiVersion;

    public Info() {
        this.id = UUID.randomUUID().toString();
    }

    public Info(String referrer, String env, String xApiKey, String xClientId, Map<String, Object> request, Map<String, Object> response, int responseCode, long timestamp, int latencyInMs, int requestsize, int responseSize, String apiVersion) {
        this.id = UUID.randomUUID().toString();
        this.referrer = referrer;
        this.env = env;
        this.xApiKey = xApiKey;
        this.xClientId = xClientId;
        this.request = request;
        this.response = response;
        this.responseCode = responseCode;
        this.timestamp = timestamp;
        this.latencyInMs = latencyInMs;
        this.requestSize = requestsize;
        this.responseSize = responseSize;
        this.apiVersion = apiVersion;
    }

    public String getId() {
        return id;
    }

    public void setId(){
        this.id = UUID.randomUUID().toString();
    }
    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getxApiKey() {
        return xApiKey;
    }

    public void setxApiKey(String xApiKey) {
        this.xApiKey = xApiKey;
    }

    public String getxClientId() {
        return xClientId;
    }

    public void setxClientId(String xClientId) {
        this.xClientId = xClientId;
    }

    public Map<String, Object> getRequest() {
        return request;
    }

    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    public Map<String, Object> getResponse() {
        return response;
    }

    public void setResponse(Map<String, Object> response) {
        this.response = response;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getLatencyInMs() {
        return latencyInMs;
    }

    public void setLatencyInMs(int latencyInMs) {
        this.latencyInMs = latencyInMs;
    }

    public int getRequestSize() {
        return requestSize;
    }

    public void setRequestSize(int requestSize) {
        this.requestSize = requestSize;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(int responseSize) {
        this.responseSize = responseSize;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id='" + id + '\'' +
                ", referrer='" + referrer + '\'' +
                ", env='" + env + '\'' +
                ", xApiKey='" + xApiKey + '\'' +
                ", xClientId='" + xClientId + '\'' +
                ", request=" + request +
                ", response=" + response +
                ", responseCode=" + responseCode +
                ", timestamp=" + timestamp +
                ", latency=" + latencyInMs +
                ", requestSize=" + requestSize +
                ", responseSize=" + responseSize +
                ", apiVersion='" + apiVersion + '\'' +
                '}';
    }
}
