package com.a02.sfams.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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
}
