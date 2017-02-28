package com.contour.weather.service;

import com.contour.weather.exception.DataExportException;

import java.io.PrintWriter;

/**
 * Interface to define method for export data
 */
public interface DataExportService<T> {

    void exportData(PrintWriter writer) throws DataExportException;
}
