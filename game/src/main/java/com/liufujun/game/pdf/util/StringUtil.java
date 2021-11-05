package com.liufujun.game.pdf.util;

public class StringUtil {

    public static String 删除字符(String 源字符,String 需删除字符1){

        return 源字符.replace(需删除字符1,"");
    }
    public static String 删除字符(String 源字符,String 需删除字符1,String 需删除字符2,String 需删除字符3,String 需删除字符4){
        return 源字符.replace(需删除字符1,"").replace(需删除字符2,"").replace(需删除字符3,"").replace(需删除字符4,"");
    }
    public static String 删除字符(String 源字符,String 需删除字符1,String 需删除字符2){
        return 源字符.replace(需删除字符1,"").replace(需删除字符2,"");
    }
    public static String 删除字符(String 源字符,String 需删除字符1,String 需删除字符2,String 需删除字符3){
        return 源字符.replace(需删除字符1,"").replace(需删除字符2,"").replace(需删除字符3,"");
    }

    public static String 提取文件路径(String 源路径全称){
        int a= 源路径全称.lastIndexOf("/")+1;
        return 源路径全称.substring(0,a);
    }
    public static String 提取文件名(String 源路径全称){
        int a= 源路径全称.lastIndexOf("/")+1;
        return 源路径全称.substring(a);
    }

    public static int 统计字符出现次数(String 字符串,char 字符){
        char[] chars=字符串.toCharArray();
        int ci=0;
        for (int i = 0; i <chars.length ; i++) {
            if (chars[i]==字符){
                ci++;
            }
        }
        return ci;
    };

    public static String 截取到第一次出现(String 字符串,String 字符){
        return 删除字符(字符串.substring(0,字符串.indexOf(字符))," ","\t");
    };
    public static void main(String[] args) {
      System.out.println(截取到第一次出现("104,//_BRIGHTNESS_25,",","))  ;
    }
}
