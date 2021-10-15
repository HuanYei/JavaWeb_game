package com.liufujun.game.util;

import com.liufujun.game.me.pojo.SW;

public class PlanUtil {

    public static String PlanType(String swname){

        if (swname.indexOf("368")!=-1){
            return "368";
        }else if (swname.indexOf("9255")!=-1){
            return"368";
        }else if (swname.indexOf("2851")!=-1){
            return"2851";
        }else if (swname.indexOf("9632")!=-1){
            return"9632";
        }else {
            return "未识别到方案";
        }
    }
    public static String SW脚本路径(String swname){

        if (swname.indexOf("368")!=-1){
            return 脚本路径368+swname+".sh";
        }else if (swname.indexOf("9255")!=-1){
            return 脚本路径368+swname+".sh";
        }else if (swname.indexOf("2851")!=-1){
            return"2851";
        }else if (swname.indexOf("9632")!=-1){
            return"9632";
        }else {
            return "未识别到方案";
        }
    }
    public static SW SW属性(String swname){
        SW sw=new SW();
        if (PlanType(swname).equals("368")){

        }
            return sw;

    }
    //368方案相关路径
    static String  脚本路径368="Z:/9255/New9255/MTK368P/vendor/toptech/buildsettings/";
    static String  LOGO路径368="Z:/9255/New9255/MTK368P/vendor/toptech/customer/common/bootlogo/";
    static String  屏参路径368="Z:/9255/New9255/MTK368P/vendor/mediatek/proprietary_tv/apollo/linux_core/misdk/mi/mi/platform/common/ini/main/panel/";
    static String  客制化文件夹路径368="Z:/9255/New9255/MTK368P/vendor/toptech/customer/C001/";

}
