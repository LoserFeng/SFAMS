package com.a02.sfams.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    public static String createDateString(){
        LocalDate now = LocalDate.now();
        String str="";
        str+=now.getYear()+""+now.getMonth().getValue()+""+now.getDayOfMonth();
        return str;
    }

    //获取时间的时分秒
    public static String getHMS(Date date){
        if(date==null){
            return "";
        }
        String[] strNow3 = new SimpleDateFormat("HH:mm:ss").format(date).toString().split(":");
        int hour = Integer.parseInt(strNow3[0]);//获取时（24小时制）
        int minute=Integer.parseInt(strNow3[1]);			//获取分
        int second=Integer.parseInt(strNow3[2]);			//获取秒
        return hour+":"+minute+":"+second;
    }

    public static long getDifferenceInDays(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        long diffInMilliseconds = calendar2.getTimeInMillis() - calendar1.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);
    }

}
