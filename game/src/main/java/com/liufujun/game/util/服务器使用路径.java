package com.liufujun.game.util;

import java.util.ArrayList;
import java.util.List;

public class 服务器使用路径 {


    public static String codeEditor="";
    public static String comparison_tool="";

    public static String ip="";
    public static String user = "";
    public static String password = "";
    public static String rtk2851_pq_Linux="";
    public static String rtk2851_pq_Windows="";
    public static String FtpUser="";
    public static String FtpPassword="";



    //368
    public static List<String> MTK368PALL=new ArrayList<String>();
    public static String  MTK368PATH="";
    public static String  脚本路径368="";
    public static String  LOGO路径368="";
    public static String  屏参路径368="";
    public static String  客制化文件夹路径368="";

    //9632
    public static List<String> MTK9632PALL=new ArrayList<String>();
    public static String  MTK9632PATH="";
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
    public static String  RTK2851PATH="";
    public static String  脚本路径2851="";
    public static String  LOGO路径2851="";
    public static String  屏参路径2851="";
    public static String  客制化文件夹路径2851="";

    //2842
    public static String  脚本路径2842="";
    public static String  LOGO路径2842="";
    public static String  屏参路径2842="";
    public static String  客制化文件夹路径2842="";


    //2853
    public static List<String> RTK2853PALL=new ArrayList<String>();
    public static String  RTK2853PATH="";
    public static String  脚本路径2853="";
    public static String  LOGO路径2853="";
    public static String  屏参路径2853="";
    public static String  客制化文件夹路径2853="";

    //2843
    public static String  脚本路径2843="";
    public static String  LOGO路径2843="";
    public static String  屏参路径2843="";
    public static String  客制化文件夹路径2843="";

    static {
        String PathContent= Fileprocessing.readTxtFile("user.config").replace("\\","/");
        String PathContentarray[]=PathContent.split("\n");
        for (int i = 0; i <PathContentarray.length ; i++) {
            if (PathContentarray[i].indexOf("368仓库")!=-1){
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
            }if (PathContentarray[i].indexOf("2853&&2843")!=-1){
                RTK2853PATH=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1,PathContentarray[i].indexOf(">"));
                System.out.println(RTK2853PATH);
                String AA=PathContentarray[i].substring(PathContentarray[i].indexOf(">")+1);
                RTK2853PALL.add(AA+"："+RTK2853PATH);
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
            }if (PathContentarray[i].indexOf("FtpUser")!=-1){
                FtpUser=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1);
                System.out.println(FtpUser);
            }if (PathContentarray[i].indexOf("FtpPassword")!=-1){
                FtpPassword=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1);
                System.out.println(FtpPassword);
            }if (PathContentarray[i].indexOf("codeeditor")!=-1){
                codeEditor=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1);
                System.out.println(codeEditor);
            }if (PathContentarray[i].indexOf("ComparisonTool")!=-1){
                comparison_tool=PathContentarray[i].substring(PathContentarray[i].indexOf("=")+1);
                System.out.println(comparison_tool);
            }
        }
        if (MTK9632PALL.size()>0){
            MTK9632PATH=MTK9632PALL.get(0).substring(MTK9632PALL.get(0).indexOf("：")+1);
            Info9632(MTK9632PATH);
        }

        if (MTK368PALL.size()>0){
            MTK368PATH=MTK368PALL.get(0).substring(MTK368PALL.get(0).indexOf("：")+1);
            Info368(MTK368PATH);
        }

        if (RTK2851PALL.size()>0){
            RTK2851PATH=RTK2851PALL.get(0).substring(RTK2851PALL.get(0).indexOf("：")+1);
            Info2851(RTK2851PATH);
        }

        if (RTK2853PALL.size()>0){
            RTK2853PATH=RTK2853PALL.get(0).substring(RTK2853PALL.get(0).indexOf("：")+1);
            Info2853(RTK2853PATH);
        }



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

    public static void Info2853(String rtk2853){
        RTK2853PATH=rtk2853;
        //2853
        if (!RTK2853PATH.equals("null")) {
            脚本路径2853 = RTK2853PATH + "kernel/system/buildsettings/";
            LOGO路径2853 = RTK2853PATH + "customer/bootlogo/";
            屏参路径2853 = RTK2853PATH + "kernel/android/R/vendor/realtek/common/ATV/hardware/interfaces/rtkconfigs/1.0/default/RtkProjectConfigs/ProjectConfigs/panel/";
            客制化文件夹路径2853 = RTK2853PATH + "customer/customers/";
        }
        //2842
        if (!RTK2853PATH.equals("null")) {
            脚本路径2843 = RTK2853PATH + "kernel/system/buildsettings/2843/";
            LOGO路径2843 = RTK2853PATH + "customer/bootlogo/";
            屏参路径2843 = RTK2853PATH + "kernel/android/R/vendor/realtek/common/ATV/hardware/interfaces/rtkconfigs/1.0/default/RtkProjectConfigs/ProjectConfigs/panel/";
            客制化文件夹路径2843 = RTK2853PATH + "customer/customers/";
        }
    }
}
