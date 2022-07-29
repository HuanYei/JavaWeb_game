package com.liufujun.game.util;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class MyUtil {
    //16to2
    public static String hexStringToByte(String hex) {
        int i = Integer.parseInt(hex, 16);
        String str2 = Integer.toBinaryString(i);
        return str2;
    }

    //10to2  8位
    public static String iNTToByte(int i) {
        String er=Integer.toBinaryString(i);
        while (er.length()<8){
            er="0"+er;
        }
        return er;
    }

    public static String byteTo二进制8位(byte b) {
        int a=b;
        if (a<0){
            a=byteToInteger(b);
        }
//        System.out.println(a);
        String str2 = Integer.toBinaryString(a);
        int length=str2.length();
            for (int i = 0; i <8-length ; i++) {
                str2="0"+str2;
            }
        return str2;
    }
    //二进制转压缩ASCII
    public static String erToAsciiString(String con) {
        int a=Integer.parseInt(con,2);
        int a1=a+64;
        return (char)a1+"";
    }

    //10进制转ASCII
    public static String StringToAsciiString(byte u) {
        int content=byteToInteger(u);
        System.out.println("ss:"+u);
        return (char)content+"";
    }

    /* *
     *  Description: 负数byte转正int
     *
     */
    public static Integer byteToInteger(Byte b) {
        return 0xff & b;
    }

    public static String time(){
        int year, month, day, week, hh;
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        week = calendar.get(Calendar.DAY_OF_WEEK);
        hh = calendar.get(Calendar.HOUR);
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        System.out.println("date=" +                      dateFormat.format(calendar.getTime()));
        System.out.println("year=" + year);
        System.out.println("month=" + month);
        System.out.println("day=" + day);
        System.out.println("week=" + week);
        System.out.println("hh=" + hh);

        return year+"年的第"+getWeek(new Date())+"周";
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
//public static void main(String[] args) {
//    String cent[]=Fileprocessing.readTxtFile("res/test.txt").replace(")","").replace("(","").split("\n");
//    for (int i = 0; i <cent.length ; i++) {
//        String aa[]=cent[i].split("=");
//
//        System.out.println(i);
//        cent[i]=aa[2]+"=a="+aa[1]+"="+aa[0]+"=a=a=a=a=a";
//    }
//
//    for (String a:
//         cent) {
//        System.out.println(a);
//    }
//}
    public static void main(String[] args) {
//        System.out.println(hexStringToByte("ff"));
    }

    public static byte[] hexStrToByteArray(String str)
    {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return new byte[0];
        }
        byte[] byteArray = new byte[str.length() / 2];
        for (int i = 0; i < byteArray.length; i++){
            String subStr = str.substring(2 * i, 2 * i + 2);
            byteArray[i] = ((byte)Integer.parseInt(subStr, 16));
        }
        return byteArray;
    }

    public static long getCRC32Checksum(byte[] bytes) {
        Checksum crc32 = new CRC32();
        crc32.update(bytes, 0, bytes.length);
        return crc32.getValue();
    }

    public static int getWeek(Date date) {
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(date);
        return g.get(Calendar.WEEK_OF_YEAR); //获得周数

    }
}
