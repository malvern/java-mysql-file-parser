package com.ef.repository.impl;

import com.ef.domain.Log;
import com.ef.repository.api.LogRepository;
import com.ef.utils.exception.BusinessException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogRepositoryImpl implements LogRepository{

    public String selectRequestWithThresholdGreaterOrEqualAndBetweenStartDateAndEndDate() {
        return  "SELECT * FROM (SELECT COUNT(ip) AS threshold,ip,request,request_status,user_agent" +
                " FROM analytics.access_logs AS logs" +
                " WHERE  start_date BETWEEN ? AND ? GROUP BY ip,request,request_status,user_agent) AS analysis" +
                " WHERE   threshold >= ?";
    }


    public String saveLogAnalysisResults() {
        return "INSERT INTO access_analysis (number_of_request,threshold,ip,request,request_status, user_agent,comment,date_accessed)"
                + " VALUES (?, ?, ?, ?, ?,?,?,?)";
    }

    @Override
    public PreparedStatement createQueryWithMultipleParams(PreparedStatement preparedStatement, String... params) {
        int indexes = 1;
        for (String arg : params) {
            try {
                preparedStatement.setString(indexes++, arg);
            } catch (SQLException e) {
                System.err.println("createQueryWithMultipleParam --> "+e.getMessage());
                throw new BusinessException(e.getMessage());
            }
        }
        return preparedStatement;
    }
}
