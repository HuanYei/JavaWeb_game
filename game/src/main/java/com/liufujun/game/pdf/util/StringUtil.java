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


}
