package com.contour.weather.controller;

import com.contour.weather.exception.DataExportException;
import com.contour.weather.service.DataExportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Rest controller to export data
 */
@RestController
public class DataExportController {

    private DataExportFactory dataExportFactory;

    private Logger logger = LoggerFactory.getLogger(DataExportController.class);

    @Autowired
    public DataExportController(DataExportFactory dataExportFactory){
        this.dataExportFactory = dataExportFactory;
    }

    @RequestMapping(value = "weather/export/csv")
    public void exportCSV(HttpServletResponse response) throws IOException {
        String csvFileName = "weather.csv";
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                csvFileName);
        response.setHeader(headerKey, headerValue);
        try {
            dataExportFactory.getDataExportService("CSV").exportData(response.getWriter());
        } catch(DataExportException e){
            //todo : redirect to the error page
        }
    }

    @RequestMapping(value = "weather/export/xml")
    public void exportXML(HttpServletResponse response) throws IOException {
        logger.info("Weather data export for xml is not supported.");
//        try {
//            dataExportFactory.getDataExportService("XML").exportData(response.getWriter());
//        } catch(DataExportException e){
//            //todo : redirect to the error page
//        }
    }

}
