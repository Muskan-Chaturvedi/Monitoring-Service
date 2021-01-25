package com.training.monitor.service;

import com.training.monitor.commons.ExcelExporter;
import com.training.monitor.commons.ReportTypeEnum;
import com.training.monitor.dto.FilterDTO;
import com.training.monitor.dto.InfoDTO;
import com.training.monitor.mappers.GetAllInfoMapper;
import com.training.monitor.mappers.IngestMapper;
import com.training.monitor.models.Info;
import com.training.monitor.repository.InfoRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfoServiceImpl implements InfoService {


    private final IngestMapper ingestMapper;
    private final GetAllInfoMapper getAllInfoMapper;
    private final MongoTemplate mongoTemplate;
    private final InfoRepository infoRepository;

    public InfoServiceImpl(InfoRepository infoRepository, IngestMapper ingestMapper,
                           GetAllInfoMapper getAllInfoMapper, MongoTemplate mongoTemplate) {
        this.infoRepository = infoRepository;
        this.ingestMapper = ingestMapper;
        this.getAllInfoMapper = getAllInfoMapper;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public InfoDTO create(InfoDTO infoDTO) {

        Info info = null;

        try {
            info = ingestMapper.InfoDTOtoInfo(infoDTO);
            infoRepository.save(info);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }

        return infoDTO;
    }

    @Override
    public List<InfoDTO> viewInfo() {

        List<Info> allInfo = infoRepository.findAll();

        return allInfo.stream().map(info -> getAllInfoMapper.getAllInfo(info)).collect(Collectors.toList());

    }

    @Override
    public List<InfoDTO> searchInfo(FilterDTO filterDTO) {

        Query query = new Query();

        if (filterDTO.getReferrer().isPresent()) {
            query.addCriteria(Criteria.where("referrer").is(filterDTO.getReferrer().get()));
        }
        if (filterDTO.getxApiKey().isPresent()) {
            query.addCriteria(Criteria.where("xApiKey").is(filterDTO.getxApiKey().get()));
        }
        if (filterDTO.getxClientId().isPresent()) {
            query.addCriteria(Criteria.where("xClientId").is(filterDTO.getxClientId().get()));
        }
        if (filterDTO.getEnv().getValue().isEmpty()) {
            query.addCriteria(Criteria.where("env").is(filterDTO.getEnv()));
        }
        query.addCriteria(Criteria.where("timestamp").gte(filterDTO.getTimestamp().getGte())
                .lte(filterDTO.getTimestamp().getLte()));

        query.addCriteria(Criteria.where("latencyInMs").gte(filterDTO.getLatencyInMs().getGte())
                .lte(filterDTO.getLatencyInMs().getLte()));

        query.addCriteria(Criteria.where("requestSize").gte(filterDTO.getRequestSize().getGte())
                .lte(filterDTO.getRequestSize().getLte()));

        query.addCriteria(Criteria.where("responseSize").gte(filterDTO.getResponseSize().getGte())
                .lte(filterDTO.getResponseSize().getLte()));

        if (filterDTO.getApiVersion().isPresent()) {
            query.addCriteria(Criteria.where("apiVersion").is(filterDTO.getApiVersion().get()));
        }
        if (filterDTO.getResponseCode().isPresent()) {
            query.addCriteria(Criteria.where("responseCode").is(filterDTO.getResponseCode().get()));
        }

        query.with(Sort.by(Sort.Direction.fromString(filterDTO.getSortOrder().toString()), filterDTO.getSortBy().toString()));

        final Pageable pageableRequest = PageRequest.of((filterDTO.getPageNumber()- 1), filterDTO.getPageSize());
        query.with(pageableRequest);

        List<InfoDTO> allFilterData = mongoTemplate.find(query, InfoDTO.class);

        return allFilterData.stream().map(info -> getAllInfoMapper.getAllInfo(info)).collect(Collectors.toList());
    }

    @Override
    public void giveReport(HttpServletResponse response, FilterDTO filterDTO, ReportTypeEnum reportTypeEnum) throws IOException {

        if(reportTypeEnum.equals(ReportTypeEnum.CSV))
        {
            List<InfoDTO> reportInfo = searchInfo(filterDTO);

            response.setContentType("text/csv");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=report_" + currentDateTime + ".csv";
            response.setHeader(headerKey, headerValue);

            ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
            String[] csvHeader = {"Referrer", "Environment", "XApiKey", "XClientId", "Request", "Response", "ResponseCode", "Timestamp", "LatencyInMs", "RequestSize", "ResponseSize", "ApiVersion"};
            String[] nameMapping = {"referrer", "env", "xApiKey", "xClientId", "request", "response",  "responseCode", "timestamp", "latencyInMs", "requestSize", "responseSize", "apiVersion"};

            csvWriter.writeHeader(csvHeader);

            for (InfoDTO infoDTO : reportInfo) {
                csvWriter.write(infoDTO, nameMapping);
            }

            csvWriter.close();
        }

        if(reportTypeEnum.equals(ReportTypeEnum.XLSX))
        {
            response.setContentType("application/vnd.ms-excel");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=report_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            List<InfoDTO> reportInfo = searchInfo(filterDTO);

            ExcelExporter excelExporter = new ExcelExporter(reportInfo);

            excelExporter.export(response);
        }
    }
}
