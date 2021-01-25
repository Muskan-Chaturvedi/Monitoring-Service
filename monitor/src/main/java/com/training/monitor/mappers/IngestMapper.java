package com.training.monitor.mappers;

import com.training.monitor.dto.InfoDTO;
import com.training.monitor.models.Info;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class IngestMapper {

    public Info InfoDTOtoInfo(InfoDTO infoDTO) throws ResourceNotFoundException {

        Info info= new Info();

        info.setReferrer(infoDTO.getReferrer());
        info.setEnv(infoDTO.getEnv());
        info.setxApiKey(infoDTO.getxApiKey());
        info.setxClientId(infoDTO.getxClientId());
        info.setRequest(infoDTO.getRequest());
        info.setResponse(infoDTO.getResponse());
        info.setResponseCode(infoDTO.getResponseCode());
        info.setTimestamp(infoDTO.getTimestamp());
        info.setLatencyInMs(infoDTO.getLatencyInMs());
        info.setRequestSize(infoDTO.getRequestSize());
        info.setResponseSize(infoDTO.getResponseSize());
        info.setApiVersion(infoDTO.getApiVersion());

        return info;
    }
}
