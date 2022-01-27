package com.liufujun.game.util;

import com.liufujun.game.pdf.util.Fileprocessing;

import java.util.ArrayList;
import java.util.List;

public class 服务器使用路径 {

    public static String ip="172.168.1.230";
    public static int port = 22;
    public static String user = "liufujun";
    public static String password = "123422343HY";
    public static String rtk2851_pq_Linux="/media/workspace0/liufujun/2851/rtk2851_pq/rtk2851/libvip/LibvipOSD";
    public static String rtk2851_pq_Windows="Z:/2851/rtk2851_pq/rtk2851/libvip/LibvipOSD/";


    public static String 彩讯订单PDF文件存储路径="res/pdf/订单pdf文件/";
    public static String 彩讯订单参考脚本文件存储路径="res/pdf/参考脚本文件/";
    public static String 彩讯订单脚本生成路径="res/pdf/生成脚本文件/";
    public static String RTK脚本="res/pdf/RTK脚本文件/";

    //368
    public static List<String> MTK368PALL=new ArrayList<String>();
    public static String  MTK368PATH="Z:/9255/MTK368P/";
    public static String  脚本路径368="";
    public static String  LOGO路径368="";
    public static String  屏参路径368="";
    public static String  客制化文件夹路径368="";
    public static String  MTK368_Linux="/media/workspace0/liufujun/9255/New9255/MTK368P";

    //9632
    public static List<String> MTK9632PALL=new ArrayList<String>();
    public static String  MTK9632PATH="Z:/9632/MTK9632P/";
    public static String  脚本路径9632="";
    public static String  LOGO路径9632="";
    public static String  屏参路径9632="";
    public static String  客制化文件夹路径9632="";

    public static String  脚本路径6681="";
    public static String  LOGO路径6681="";
    public static String  屏参路径6681="";
    public static String  客制化文件夹路径6681="";

    //2851
    public static List<String> RTK2851PALL=new ArrayList<String>();
    public static String  RTK2851PATH="Z:/2851/2851_all/";
    public static String  脚本路径2851="";
    public static String  LOGO路径2851="";
    public static String  屏参路径2851="";
    public static String  客制化文件夹路径2851="";
    public static String rtk2851_Linux="/media/workspace0/liufujun/2851/2851_all";

    //2842
    public static String  脚本路径2842="";
    public static String  LOGO路径2842="";
    public static String  屏参路径2842="";
    public static String  客制化文件夹路径2842="";

    static {
        String PathContent= Fileprocessing.readTxtFile("user.config").replace("\\","/");
        String PathContentarray[]=PathContent.split("\n");
        for (int i = 0; i <PathContentarray.length ; i++) {
            if (PathContentarray[i].indexOf("368")!=-1){
                MTK368PATH=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1,PathContentarray[i].indexOf(">"));
                System.out.println(MTK368PATH);
                String AA=PathContentarray[i].substring(PathContentarray[i].indexOf(">")+1);
                MTK368PALL.add(AA+"："+MTK368PATH);
            }if (PathContentarray[i].indexOf("9632&&6681")!=-1){
                MTK9632PATH=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1,PathContentarray[i].indexOf(">"));
                System.out.println(MTK9632PATH);
                String AA=PathContentarray[i].substring(PathContentarray[i].indexOf(">")+1);
                MTK9632PALL.add(AA+"："+MTK9632PATH);
            }if (PathContentarray[i].indexOf("2851&&2842")!=-1){
                RTK2851PATH=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1,PathContentarray[i].indexOf(">"));
                System.out.println(RTK2851PATH);
                String AA=PathContentarray[i].substring(PathContentarray[i].indexOf(">")+1);
                RTK2851PALL.add(AA+"："+RTK2851PATH);
            }if (PathContentarray[i].indexOf("user")!=-1){
                user=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1);
                System.out.println(user);
            }if (PathContentarray[i].indexOf("password")!=-1){
                password=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1);
                System.out.println(password);
            }if (PathContentarray[i].indexOf("2851PQ_LinuxPath")!=-1){
                rtk2851_pq_Linux=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1);
                System.out.println(rtk2851_pq_Linux);
            }if (PathContentarray[i].indexOf("2851PQ_Windows")!=-1){
                rtk2851_pq_Windows=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1);
                System.out.println(rtk2851_pq_Windows);
            }if (PathContentarray[i].indexOf("LinuxIP")!=-1){
                ip=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1);
                System.out.println(ip);
            }if (PathContentarray[i].indexOf("2851_Linux")!=-1){
                rtk2851_Linux=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1);
                System.out.println(rtk2851_Linux);
            }
        }

        MTK9632PATH=MTK9632PALL.get(0).substring(MTK9632PALL.get(0).indexOf("：")+1);
        MTK368PATH=MTK368PALL.get(0).substring(MTK368PALL.get(0).indexOf("：")+1);
        RTK2851PATH=RTK2851PALL.get(0).substring(RTK2851PALL.get(0).indexOf("：")+1);
        Info368(MTK368PATH);
        Info9632(MTK9632PATH);
        Info2851(RTK2851PATH);

    }

    public static void Info368(String mtk368){
        //368
        if (!mtk368.equals("null")) {
            MTK368PATH=mtk368;
            脚本路径368 = MTK368PATH + "vendor/toptech/buildsettings/";
            LOGO路径368 = MTK368PATH + "vendor/toptech/customer/common/bootlogo/";
            屏参路径368 = MTK368PATH + "vendor/mediatek/proprietary_tv/apollo/linux_core/misdk/mi/mi/platform/common/ini/main/panel/";
            客制化文件夹路径368 = MTK368PATH + "vendor/toptech/customer/";
        }
    }

    public static void Info9632(String mtk9632){
        MTK9632PATH=mtk9632;
        //9632
        if (!MTK9632PATH.equals("null")) {
            脚本路径9632 = MTK9632PATH + "vendor/toptech/buildsettings/9632/";
            LOGO路径9632 = MTK9632PATH + "vendor/toptech/customer/common/bootlogo/";
            屏参路径9632 = MTK9632PATH + "vendor/mediatek/proprietary_tv/apollo/linux_core/misdk/mi/mi/platform/common/ini/main/panel/";
            客制化文件夹路径9632 = MTK9632PATH + "vendor/toptech/customer/9632/";
        }
        //6681
        if (!MTK9632PATH.equals("null")) {
            脚本路径6681 = MTK9632PATH + "vendor/toptech/buildsettings/6681/";
            LOGO路径6681 = MTK9632PATH + "vendor/toptech/customer/common/bootlogo/";
            屏参路径6681 = MTK9632PATH + "vendor/mediatek/proprietary_tv/apollo/linux_core/misdk/mi/mi/platform/common/ini/main/panel/";
            客制化文件夹路径6681 = MTK9632PATH + "vendor/toptech/customer/6681/";
        }
    }

    public static void Info2851(String rtk2851){
        RTK2851PATH=rtk2851;
        //2851
        if (!RTK2851PATH.equals("null")) {
            脚本路径2851 = RTK2851PATH + "kernel/system/buildsettings/";
            LOGO路径2851 = RTK2851PATH + "customer/bootlogo/";
            屏参路径2851 = RTK2851PATH + "bootcode/uboot/arch/arm/include/asm/arch-rtd285o/panel/toptech/";
            客制化文件夹路径2851 = RTK2851PATH + "customer/customers/";
        }
        //2842
        if (!RTK2851PATH.equals("null")) {
            脚本路径2842 = RTK2851PATH + "kernel/system/buildsettings/2842/";
            LOGO路径2842 = RTK2851PATH + "customer/bootlogo/";
            屏参路径2842 = RTK2851PATH + "bootcode/uboot/arch/arm/include/asm/arch-rtd285o/panel/toptech_2842/";
            客制化文件夹路径2842 = RTK2851PATH + "customer/customers/";
        }
    }
}
