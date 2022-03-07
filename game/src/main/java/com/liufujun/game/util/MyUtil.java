package com.liufujun.game.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {
    //进制转换
    public static String hexStringToByte(String hex) {
        int i = Integer.parseInt(hex, 16);
        String str2 = Integer.toBinaryString(i);
        return str2;
    }

    //今天时间
    public static String toDay(){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(date);
    }
}
