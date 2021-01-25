package com.training.monitor.mappers;

import com.training.monitor.dto.InfoDTO;
import com.training.monitor.models.Info;
import org.springframework.stereotype.Component;

@Component
public class GetAllInfoMapper {

    public InfoDTO  getAllInfo(Info info){

        InfoDTO infoDTO= new InfoDTO();

        infoDTO.setReferrer(info.getReferrer());
        infoDTO.setEnv(info.getEnv());
        infoDTO.setxApiKey(info.getxApiKey());
        infoDTO.setxClientId(info.getxClientId());
        infoDTO.setRequest(info.getRequest());
        infoDTO.setResponse(info.getResponse());
        infoDTO.setResponseCode(info.getResponseCode());
        infoDTO.setTimestamp(info.getTimestamp());
        infoDTO.setLatencyInMs(info.getLatencyInMs());
        infoDTO.setRequestSize(info.getRequestSize());
        infoDTO.setResponseSize(info.getResponseSize());
        infoDTO.setApiVersion(info.getApiVersion());

        return infoDTO;
    }
}
