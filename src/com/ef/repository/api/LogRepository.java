package com.ef.repository.api;


import java.sql.PreparedStatement;

public interface LogRepository {
    String selectRequestWithThresholdGreaterOrEqualAndBetweenStartDateAndEndDate();
    String saveLogAnalysisResults();
    PreparedStatement createQueryWithMultipleParams(PreparedStatement preparedStatement,String ...params);
}
