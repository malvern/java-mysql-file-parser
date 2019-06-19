package com.ef;

import com.ef.business.api.DatabaseFileLoader;
import com.ef.business.api.RequestAnalyser;
import com.ef.business.impl.DatabaseFileLoaderImpl;
import com.ef.business.impl.DateCalculatorImpl;
import com.ef.business.impl.RequestAnalyserImpl;
import com.ef.repository.impl.LogRepositoryImpl;

import java.util.HashMap;
import java.util.Optional;

public class Parser {
    public static void main(String[] args) {
        String path = requestMapper(args).get("accesslog");
        DatabaseFileLoader databaseFileLoader = new DatabaseFileLoaderImpl();
        databaseFileLoader.loadFileToDatabase(Optional.ofNullable(path));
        RequestAnalyser requestAnalyser = new RequestAnalyserImpl(new DateCalculatorImpl(), new LogRepositoryImpl());
        requestAnalyser.requestAnalytics(requestMapper(args).get("startDate"),
                requestMapper(args).get("duration"),requestMapper(args).get("threshold"));

    }
    private static HashMap<String, String> requestMapper(String... args) {
        HashMap<String, String> params = new HashMap<>();
        for (String toSplit : args) {
            String[] splitted = toSplit.split("=");
            String key = splitted[0].replaceAll("--", "");
            String value = splitted[1];
            params.put(key, value);
        }
        return params;
    }
}
