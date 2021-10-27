package com.liufujun.game.util;

import com.liufujun.game.pdf.util.Fileprocessing;

public class 服务器使用路径 {
    public static String 彩讯订单PDF文件存储路径="D:/work/Customer documents/file210716/服务器路径/订单pdf文件/";
    public static String 彩讯订单参考脚本文件存储路径="D:/work/Customer documents/file210716/服务器路径/参考脚本文件/";
    public static String 彩讯订单脚本生成路径="D:/work/Customer documents/file210716/服务器路径/生成脚本文件/";
    public static String RTK脚本="D:/work/Customer documents/file210716/服务器路径/RTK脚本文件/";

    //368
    public static String  MTK368PATH="Z:/9255/New9255/MTK368P/";
    public static String  脚本路径368;
    public static String  LOGO路径368;
    public static String  屏参路径368;
    public static String  客制化文件夹路径368;


    //9632
    public static String  MTK9632PATH="Z:/6681/MTK9632P/";
    public static String  脚本路径9632;
    public static String  LOGO路径9632;
    public static String  屏参路径9632;
    public static String  客制化文件夹路径9632;

    public static String  脚本路径6681;
    public static String  LOGO路径6681;
    public static String  屏参路径6681;
    public static String  客制化文件夹路径6681;

    //2851
    public static String  RTK2851PATH="Z:/2851/2851_all/";
    public static String  脚本路径2851;
    public static String  LOGO路径2851;
    public static String  屏参路径2851;
    public static String  客制化文件夹路径2851;

    //2842
    public static String  脚本路径2842;
    public static String  LOGO路径2842;
    public static String  屏参路径2842;
    public static String  客制化文件夹路径2842;

    static {
        String PathContent= Fileprocessing.readTxtFile("路径配置.config").replace("\\","/");
        String PathContentarray[]=PathContent.split("\n");
        for (int i = 0; i <PathContentarray.length ; i++) {
            if (PathContentarray[i].indexOf("368")!=-1){
                MTK368PATH=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1);
                System.out.println(MTK368PATH);
            }if (PathContentarray[i].indexOf("9632")!=-1){
                MTK9632PATH=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1);
                System.out.println(MTK9632PATH);
            }if (PathContentarray[i].indexOf("2851")!=-1){
                RTK2851PATH=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1);
                System.out.println(RTK2851PATH);
            }
        }

        //368
        脚本路径368=MTK368PATH+"vendor/toptech/buildsettings/";
        LOGO路径368=MTK368PATH+"vendor/toptech/customer/common/bootlogo/";
        屏参路径368=MTK368PATH+"vendor/mediatek/proprietary_tv/apollo/linux_core/misdk/mi/mi/platform/common/ini/main/panel/";
        客制化文件夹路径368=MTK368PATH+"vendor/toptech/customer/";

        //9632
        脚本路径9632=MTK9632PATH+"vendor/toptech/buildsettings/9632/";
        LOGO路径9632=MTK9632PATH+"vendor/toptech/customer/common/bootlogo/";
        屏参路径9632=MTK9632PATH+"vendor/mediatek/proprietary_tv/apollo/linux_core/misdk/mi/mi/platform/common/ini/main/panel/";
        客制化文件夹路径9632=MTK9632PATH+"vendor/toptech/customer/9632/";

        //6681
        脚本路径6681=MTK9632PATH+"vendor/toptech/buildsettings/6681/";
        LOGO路径6681=MTK9632PATH+"vendor/toptech/customer/common/bootlogo/";
        屏参路径6681=MTK9632PATH+"vendor/mediatek/proprietary_tv/apollo/linux_core/misdk/mi/mi/platform/common/ini/main/panel/";
        客制化文件夹路径6681=MTK9632PATH+"vendor/toptech/customer/6681/";

        //2851
        脚本路径2851=RTK2851PATH+"kernel/system/buildsettings/";
        LOGO路径2851=RTK2851PATH+"customer/bootlogo/";
        屏参路径2851=RTK2851PATH+"bootcode/uboot/arch/arm/include/asm/arch-rtd285o/panel/toptech/";
        客制化文件夹路径2851=RTK2851PATH+"customer/customers/";

        //2842
        脚本路径2842=RTK2851PATH+"kernel/system/buildsettings/2842/";
        LOGO路径2842=RTK2851PATH+"customer/bootlogo/";
        屏参路径2842=RTK2851PATH+"bootcode/uboot/arch/arm/include/asm/arch-rtd285o/panel/toptech_2842/";
        客制化文件夹路径2842=RTK2851PATH+"customer/customers/";
    }
}
