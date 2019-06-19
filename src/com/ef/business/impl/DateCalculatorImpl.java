package com.ef.business.impl;

import com.ef.business.api.DateCalculator;
import com.ef.utils.exception.BusinessException;

import java.time.LocalDateTime;

public class DateCalculatorImpl implements DateCalculator {
    @Override
    public LocalDateTime calculateEndDateTime(LocalDateTime localDateTime, String option) {
        switch (option.toLowerCase()) {
            case "hourly":
                return localDateTime.plusHours(1);
            case "daily":
                return localDateTime.plusDays(1);
            default:
                final String exceptionNarrative = "Duration "+option+"  is not defined";
                System.err.println("calculateEndDateTime -->"+exceptionNarrative);
                throw new BusinessException(exceptionNarrative);
        }
    }
}
