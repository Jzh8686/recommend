package com.gyj.gx.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    public static String getSimpleDateString(){
        return simpleDateFormat.format(new Date());
    }

    public static String getTheDayBeforeString(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,-1);
        return simpleDateFormat.format(calendar.getTime());
    }
}
