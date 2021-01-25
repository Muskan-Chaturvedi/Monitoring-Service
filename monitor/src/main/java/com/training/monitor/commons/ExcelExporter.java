package com.training.monitor.commons;

import com.training.monitor.dto.InfoDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<InfoDTO> reportInfo;

    public ExcelExporter(List<InfoDTO> reportInfo) {
        this.reportInfo = reportInfo;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Report");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Referrer", style);
        createCell(row, 1, "Environment", style);
        createCell(row, 2, "XApiKey", style);
        createCell(row, 3, "XClientId", style);
        createCell(row, 4, "Request", style);
        createCell(row, 5, "Response", style);
        createCell(row, 6, "ResponseCode", style);
        createCell(row, 7, "Timestamp", style);
        createCell(row, 8, "LatencyInMs", style);
        createCell(row, 9, "RequestSize", style);
        createCell(row, 10, "ResponseSize", style);
        createCell(row, 11, "ApiVersion", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {

        sheet.autoSizeColumn(columnCount);

        Cell cell = row.createCell(columnCount);

        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else {
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (InfoDTO infoDTO : reportInfo) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            String requestAsString = infoDTO.getRequest().keySet().stream()
                    .map(key -> key + "=" + infoDTO.getRequest().get(key))
                    .collect(Collectors.joining(", ", "{", "}"));

            String responseAsString = infoDTO.getResponse().keySet().stream()
                    .map(key -> key + "=" + infoDTO.getResponse().get(key))
                    .collect(Collectors.joining(", ", "{", "}"));

            createCell(row, columnCount++, infoDTO.getReferrer(), style);
            createCell(row, columnCount++, infoDTO.getEnv(), style);
            createCell(row, columnCount++, infoDTO.getxApiKey(), style);
            createCell(row, columnCount++, infoDTO.getxClientId(), style);
            createCell(row, columnCount++, requestAsString, style);
            createCell(row, columnCount++, responseAsString, style);
            createCell(row, columnCount++, infoDTO.getResponseCode(), style);
            createCell(row, columnCount++, infoDTO.getTimestamp(), style);
            createCell(row, columnCount++, infoDTO.getLatencyInMs(), style);
            createCell(row, columnCount++, infoDTO.getRequestSize(), style);
            createCell(row, columnCount++, infoDTO.getResponseSize(), style);
            createCell(row, columnCount++, infoDTO.getApiVersion(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
