package com.ef.utils.dates;

import com.ef.utils.exception.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@FunctionalInterface
public interface DateUtils {
    LocalDateTime convertStringToDateTime(String date,String format);

    static LocalDateTime convertStringDateToLocalDateTime(String strDate) {
       return LocalDateTime.parse(strDate);

    }

}
