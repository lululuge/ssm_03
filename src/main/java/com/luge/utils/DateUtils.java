package com.luge.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换工具类
 */
public class DateUtils {
    /**
     * 日期转换为字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String date2String(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;
    }

    /**
     * 字符串转换为日期
     * @param str
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String str, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date parse = sdf.parse(str);
        return parse;
    }

}
