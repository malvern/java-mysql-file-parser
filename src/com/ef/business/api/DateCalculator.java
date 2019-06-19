package com.ef.business.api;

import java.time.LocalDateTime;

public interface DateCalculator {
    LocalDateTime calculateEndDateTime(LocalDateTime localDateTime,String option);
}
