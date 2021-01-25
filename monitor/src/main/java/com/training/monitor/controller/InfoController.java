package com.training.monitor.controller;

import com.training.monitor.commons.ReportTypeEnum;
import com.training.monitor.dto.FilterDTO;
import com.training.monitor.dto.InfoDTO;
import com.training.monitor.dto.ResponseDTO;
import com.training.monitor.service.InfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Validated
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @PostMapping(value = "/api/ingest")
    public ResponseEntity<ResponseDTO> saveInfo(@RequestBody InfoDTO infoDTO) {
        return ResponseEntity.ok(new ResponseDTO(true, infoService.create(infoDTO)));
    }

    @GetMapping(value = "/api/view")
    public ResponseEntity<ResponseDTO> viewInfo(){
        return ResponseEntity.ok(new ResponseDTO(true,infoService.viewInfo()));
    }

    @PostMapping(value = "/api/total")
    public ResponseEntity<ResponseDTO> searchInfo(@RequestBody FilterDTO filterDTO) {
        return ResponseEntity.ok(new ResponseDTO(true,infoService.searchInfo(filterDTO)));
    }

    @PostMapping(value = "/api/report")
    public void exportCSV(HttpServletResponse response, @RequestBody FilterDTO filterDTO, @RequestParam(name="reportTypeEnum") ReportTypeEnum reportTypeEnum) throws IOException {
        infoService.giveReport(response, filterDTO, reportTypeEnum);
    }

}
