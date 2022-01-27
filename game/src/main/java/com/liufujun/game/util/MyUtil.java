package com.liufujun.game.util;

public class MyUtil {
    //进制转换
    public static String hexStringToByte(String hex) {
        int i = Integer.parseInt(hex, 16);
        String str2 = Integer.toBinaryString(i);
        return str2;
    }
}
