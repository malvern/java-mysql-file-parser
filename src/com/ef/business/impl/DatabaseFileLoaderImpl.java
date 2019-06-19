package com.ef.business.impl;

import com.ef.business.api.DatabaseFileLoader;
import com.ef.utils.database.DatabaseConnector;
import com.ef.utils.exception.BusinessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class DatabaseFileLoaderImpl implements DatabaseFileLoader {

    @Override
    public int loadFileToDatabase(Optional<String> filePath) {
        final String file = filePath.orElseThrow(() -> new BusinessException("Please specify file to load into database"));
        String loadDataSqlStatement ="LOAD DATA LOCAL INFILE"+"'"+file+"'"+
                " INTO TABLE  access_logs FIELDS TERMINATED BY '\\|' ENCLOSED BY '\"' LINES TERMINATED BY '\r\n'" +
                " (start_date,ip,request,request_status,user_agent,id) SET id =@dummy ";
        try (Connection connection = DatabaseConnector.getDatabaseSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(loadDataSqlStatement);) {
            connection.setAutoCommit(true);
            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("loadFileToDatabase -->"+e.getMessage());
           throw new BusinessException(e.getMessage());
        }
    }
}
