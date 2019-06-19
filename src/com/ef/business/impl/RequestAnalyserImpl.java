package com.ef.business.impl;

import com.ef.business.api.DateCalculator;
import com.ef.business.api.RequestAnalyser;
import com.ef.repository.api.LogRepository;
import com.ef.utils.database.DatabaseConnector;
import com.ef.utils.dates.DateUtils;
import com.ef.utils.exception.BusinessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class RequestAnalyserImpl implements RequestAnalyser {
    private static String dateFormat = "yyyy-MM-dd.HH:mm:ss";
    private final DateCalculator dateCalculator;
    private final LogRepository logRepository;
    private final DateUtils formatDate;

    public RequestAnalyserImpl(DateCalculator dateCalculator, LogRepository logRepository) {
        this.dateCalculator = dateCalculator;
        this.logRepository = logRepository;
        formatDate = (date, format) -> LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));

    }

    @Override
    public void requestAnalytics(String startDate, String duration, String threshold) {
        final LocalDateTime startingDate = formatDate.convertStringToDateTime(startDate, dateFormat);
        final LocalDateTime endDateTime = dateCalculator.calculateEndDateTime(startingDate, duration);
        try (Connection connection = DatabaseConnector.getDatabaseSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(logRepository.
                    selectRequestWithThresholdGreaterOrEqualAndBetweenStartDateAndEndDate());
            connection.setAutoCommit(true);
            logRepository.createQueryWithMultipleParams(preparedStatement, startingDate.toString(),
                    endDateTime.toString(), threshold);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                displayResult(resultSet);
                saveRequestAnalysis(startingDate, threshold, connection, resultSet);
            }
        } catch (Exception e) {
            System.err.println("requestAnalytics --> " + e.getMessage());
            throw new BusinessException(e.getMessage());
        }

    }

    private void displayResult(ResultSet resultSet) {
        try {
            System.out.println("\t" + resultSet.getString(2) + "\t|\t" + resultSet.getString(4)
                    + "\t|\t" + resultSet.getString(3) + "\t|\t" + resultSet.getString(5)+"\t|\tblocked IP");
        } catch (SQLException e) {
            System.err.println("displayResult --->" + e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

    private void saveRequestAnalysis(LocalDateTime startDate, String threshold, Connection connection, ResultSet resultSet) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(logRepository.saveLogAnalysisResults());
            logRepository.createQueryWithMultipleParams(preparedStatement, resultSet.getString(1),
                    threshold, resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4),
                    resultSet.getString(5), "blocked IP",
                    startDate.toString()).execute();
        } catch (SQLException e) {
            System.err.println("saveRequestAnalysis --> " + e.getMessage());
            throw new BusinessException(e.getMessage());
        }
    }

}
