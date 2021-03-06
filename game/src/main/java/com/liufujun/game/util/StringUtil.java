package com.liufujun.game.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String 删除字符(String 源字符,String 需删除字符1,String 需删除字符2,String 需删除字符3,String 需删除字符串4,String 需删除字符串5){
        return 源字符.replace(需删除字符1,"").replace(需删除字符2,"").replace(需删除字符3,"").replace(需删除字符串4,"").replace(需删除字符串5,"");
    }
    public static String 删除字符(String 源字符,String 需删除字符1,String 需删除字符2,String 需删除字符3,String 需删除字符串4,String 需删除字符串5,String 需删除字符串6,String 需删除字符串7){
        return 源字符.replace(需删除字符1,"").replace(需删除字符2,"").replace(需删除字符3,"").replace(需删除字符串4,"").replace(需删除字符串6,"").replace(需删除字符串7,"").replace(需删除字符串5,"");
    }
    public static String 删除前后字符(String e源字符,char e字符){
        if (e源字符.charAt(0)==e字符){
            e源字符=e源字符.substring(1,e源字符.length());
            return 删除前后字符(e源字符,e字符);
        }
        if (e源字符.charAt(e源字符.length()-1)==e字符){
            e源字符=e源字符.substring(0,e源字符.length()-1);
            return 删除前后字符(e源字符,e字符);
        }
        return e源字符;
    }
    public static String 提取文件路径(String 源路径全称){
        int a= 源路径全称.lastIndexOf("/")+1;
        return 源路径全称.substring(0,a);
    }
    public static String 提取文件名(String 源路径全称){
        int a= 源路径全称.lastIndexOf("/")+1;
        return 源路径全称.substring(a);
    }
    public static String 提取文件后缀(String 源路径全称){
        String a= 提取文件名(源路径全称).split("\\.")[1];
        return "."+a;
    }
    public static String e去除文件后缀(String 文件名){
        String a= 提取文件名(文件名).split("\\.")[0];
        return a;
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
    public static String CDDD(String e目录){
        if (e目录.charAt(e目录.length()-1)=='/'){
            return 提取文件路径(e目录.substring(0,e目录.length()-1));
        }else if (e目录.charAt(e目录.length()-1)=='\\'){
            return 提取文件路径(e目录.substring(0,e目录.length()-1));
        }else {
            return 提取文件路径(e目录);
        }
    }

    public static String 提取config文件的值(String path,String 宏){
        String textarr[]= Fileprocessing.readTxtFile(path).split("\n");

        for (String a:textarr){
            if (a.indexOf(宏)!=-1){
                return 删除字符(a,"=",宏);
            }
        }
        return "未识别到字符";
    }
    public static String 截取到第一次出现(String 字符串,String 字符){
        if (字符串.indexOf(字符)==-1){
            return 字符串;
        }
        return 删除字符(字符串.substring(0,字符串.indexOf(字符))," ","\t");
    };
    public static void main(String[] args) {
      System.out.println(截取到第一次出现("104,//_BRIGHTNESS_25,",","))  ;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n|");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;

    }
    public static String v(String sc, String 初始, String 结束) {
        if (sc.indexOf(初始)==-1||sc.indexOf(结束)==-1){
            return "";
        }
        int 初始长度 = 初始.length();
        if (结束.equals("")){
            return sc.substring(sc.indexOf(初始) + 初始长度);
        }
        return sc.substring(sc.indexOf(初始) + 初始长度, sc.indexOf(结束, sc.indexOf(初始) + 初始长度));
    }

    public static String RePath(String 完整路径,String 文件名){
        if (文件名.split("\\.").length>1){
            return 提取文件路径(完整路径)+文件名;
        }
        return 提取文件路径(完整路径)+文件名+提取文件后缀(完整路径);

    }
}
