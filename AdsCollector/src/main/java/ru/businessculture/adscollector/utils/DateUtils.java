package ru.businessculture.adscollector.utils;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Setter
@Getter
public class DateUtils {
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

    public static SimpleDateFormat getSimpleDateFormat()
    {
        return simpleDateFormat;
    }
}
