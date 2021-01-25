package com.training.monitor.dto;

import com.training.monitor.models.Info;

import java.util.Map;

public class InfoDTO extends Info {

    public InfoDTO() {
    }

    public InfoDTO(String referrer, String env, String xApiKey, String xClientId, Map<String, Object> request, Map<String, Object> response, int responseCode, long timestamp, int latencyInMs, int requestsize, int responseSize, String apiVersion) {
        super(referrer, env, xApiKey, xClientId, request, response, responseCode, timestamp, latencyInMs, requestsize, responseSize, apiVersion);
    }

}
