package com.training.monitor.service;

import com.training.monitor.commons.ReportTypeEnum;
import com.training.monitor.dto.FilterDTO;
import com.training.monitor.dto.InfoDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public interface InfoService {

    InfoDTO create(InfoDTO infoDTO);

    List<InfoDTO> viewInfo();

    List<InfoDTO> searchInfo(FilterDTO filterDTO);

    void giveReport(HttpServletResponse response, FilterDTO filterDTO, ReportTypeEnum reportTypeEnum) throws IOException;
}
