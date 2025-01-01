package com.news.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String ChangeToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
