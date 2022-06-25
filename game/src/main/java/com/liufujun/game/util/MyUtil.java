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
    //现在时间
    public static String totime(){
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }
public static void main(String[] args) {
    String cent[]=Fileprocessing.readTxtFile("res/test.txt").replace(")","").replace("(","").split("\n");
    for (int i = 0; i <cent.length ; i++) {
        String aa[]=cent[i].split("=");

        System.out.println(i);
        cent[i]=aa[2]+"=a="+aa[1]+"="+aa[0]+"=a=a=a=a=a";
    }

    for (String a:
         cent) {
        System.out.println(a);
    }
}
}
