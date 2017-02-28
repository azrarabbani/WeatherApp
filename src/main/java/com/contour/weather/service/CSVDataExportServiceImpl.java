package com.contour.weather.service;

import com.contour.weather.data.model.WeatherUpdate;
import com.contour.weather.exception.DataExportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * CSV data export service uses super csv apis to write into csv format to the given writer.
 */
@Service
public class CSVDataExportServiceImpl extends AbstractDataExportServiceImpl {

    private Logger logger = LoggerFactory.getLogger(CSVDataExportServiceImpl.class);
    /**
     *
     * @param
     */
    @Autowired
    public CSVDataExportServiceImpl(WeatherService weatherService){
        super(weatherService);
    }

    @Override
    public void exportData(PrintWriter writer) throws DataExportException{
        logger.info("Exporting data to CSV format ....");
        try {
            List<WeatherUpdate> weatherInfoList = getWeatherInfo();
            // uses the Super CSV API to generate CSV data from the model data
            ICsvBeanWriter csvWriter = new CsvBeanWriter(writer,
                    CsvPreference.STANDARD_PREFERENCE);
            String[] header = {"City", "Temperature", "WeatherCondition", "WeatherTime"};
            csvWriter.writeHeader(header);
            for (WeatherUpdate w : weatherInfoList) {
                csvWriter.write(w, header);
            }
            csvWriter.close();
        } catch(IOException e) {
            logger.error("Exception occurred  : ", e);
            throw new DataExportException("Exception occurred while trying to export data into csv format.");
        }
    }
}
