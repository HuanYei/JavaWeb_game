package com.liufujun.game.util;

import com.liufujun.game.pdf.util.Fileprocessing;

public class 服务器使用路径 {
    public static String 彩讯订单PDF文件存储路径="D:\\work\\Customer documents\\file210716\\服务器路径\\订单pdf文件\\";
    public static String 彩讯订单参考脚本文件存储路径="D:\\work\\Customer documents\\file210716\\服务器路径\\参考脚本文件\\";
    public static String 彩讯订单脚本生成路径="D:\\work\\Customer documents\\file210716\\服务器路径\\生成脚本文件\\";
    public static String RTK脚本="D:\\work\\Customer documents\\file210716\\服务器路径\\RTK脚本文件\\";

    public static String  MTK368PATH="Z:/9255/New9255/MTK368P/";
    public static String  脚本路径368=MTK368PATH+"vendor/toptech/buildsettings/";
    public static String  LOGO路径368=MTK368PATH+"vendor/toptech/customer/common/bootlogo/";
    public static String  屏参路径368=MTK368PATH+"vendor/mediatek/proprietary_tv/apollo/linux_core/misdk/mi/mi/platform/common/ini/main/panel/";
    public static String  客制化文件夹路径368=MTK368PATH+"vendor/toptech/customer/C001/";

    static {
        String PathContent= Fileprocessing.readTxtFile("路径配置.config");
        String PathContentarray[]=PathContent.split("\n");
        for (int i = 0; i <PathContentarray.length ; i++) {
            if (PathContentarray[i].indexOf("368")!=-1){
                MTK368PATH=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1);
                System.out.println(MTK368PATH);
            }
        }


        脚本路径368=MTK368PATH+"vendor/toptech/buildsettings/";
        LOGO路径368=MTK368PATH+"vendor/toptech/customer/common/bootlogo/";
        屏参路径368=MTK368PATH+"vendor/mediatek/proprietary_tv/apollo/linux_core/misdk/mi/mi/platform/common/ini/main/panel/";
        客制化文件夹路径368=MTK368PATH+"vendor/toptech/customer/C001/";
    }
}
