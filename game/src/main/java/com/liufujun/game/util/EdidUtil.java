package com.liufujun.game.util;

import com.liufujun.game.me.dao.EDID;

public class EdidUtil {
    public static void main(String[] args) {
        String path="C:\\Users\\Administrator\\Desktop\\EDID\\32.bin";
        EDID edid=new EDID(path);
        System.out.println(edid.toString());
//          String path="E:\\edid.bin";
//        ej校验合法(path);
    }

    public static void eEdid修改(String path,EDID edid){

    }

    public static int e获取128校验未值(String path){
        byte bytes[]= Fileprocessing.readFileByBytes(path);
        byte Block0[]=new byte[127];
        for (int i = 0; i <Block0.length ; i++) {
            Block0[i]=bytes[i];
        }
        System.out.println(sumCheck(Block0,127));
        return (int)sumCheck(Block0,127);
    }


    public static boolean ej校验合法(String path){
        byte bytes[]= Fileprocessing.readFileByBytes(path);
        byte Block0[]=new byte[128];
        for (int i = 0; i <Block0.length ; i++) {
            Block0[i]=bytes[i];
        }
        System.out.println(sumCheck(Block0,128));
        if (sumCheck(Block0,128)==0){
            return true;
        }
        return false;
    }


    private static byte sumCheck(byte[] b, int len){
        int sum = 0;
        for(int i = 0; i < len; i++){
            sum = sum + b[i];
        }
        if(sum > 0xff){ //超过了255，使用补码（补码 = 原码取反 + 1）
            sum = ~sum;
            sum = sum + 1;
        }
        return (byte) (sum & 0xff);
    }
}
