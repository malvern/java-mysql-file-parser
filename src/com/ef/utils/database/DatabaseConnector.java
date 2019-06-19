package com.ef.utils.database;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String username = "root";
    private static final String password = "developer@@";
    private static final String dataSouceUrl = "jdbc:mysql://localhost:3306/analytics?&createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false";
    private static final String dataSourceDriver = "com.mysql.jdbc.Driver";
    private static HikariDataSource hikariDataSource;

    static {
        try {
            hikariDataSource = new HikariDataSource();
            hikariDataSource.setDriverClassName(dataSourceDriver);
            hikariDataSource.setJdbcUrl(dataSouceUrl);
            hikariDataSource.setUsername(username);
            hikariDataSource.setPassword(password);
            hikariDataSource.setMinimumIdle(100);
            hikariDataSource.setMaximumPoolSize(2000);
            hikariDataSource.setAutoCommit(false);
            hikariDataSource.setLoginTimeout(3);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static DataSource getDatabaseSource(){
        return hikariDataSource;
    }
}
