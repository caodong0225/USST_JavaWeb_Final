package com.news.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String ChangeToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    public static int CalAge(Date birthday)
    {
        var nowTime = Calendar.getInstance();
        var birthTime = Calendar.getInstance();
        birthTime.setTime(birthday);
        if (nowTime.before(birthTime)) {
            return 1;
        }
        var yearNum=nowTime.get(Calendar.YEAR)-birthTime.get(Calendar.YEAR);
        if (nowTime.get(Calendar.MONTH)<birthTime.get(Calendar.MONTH)) {
            yearNum--;
        } else if (nowTime.get(Calendar.MONTH)==birthTime.get(Calendar.MONTH) && nowTime.get(Calendar.DAY_OF_MONTH)<birthTime.get(Calendar.DAY_OF_MONTH)) {
            yearNum--;
        }
        return yearNum;
    }
}
