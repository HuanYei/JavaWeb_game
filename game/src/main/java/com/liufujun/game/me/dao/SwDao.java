package com.liufujun.game.me.dao;

import com.liufujun.game.me.pojo.SW;
import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.util.PlanUtil;

public class SwDao {
    static  String Stringshu[];
    public static SW 读取软件所有属性(String swpath,SW sw){
        String e脚本内容= Fileprocessing.readTxtFile(swpath);
        Stringshu=e脚本内容.split("\n");

        //368
        sw.set软件logo名(e脚本宏查值("bootlogo_file"));
        sw.set软件客制化名称(e脚本宏查值("customer_folder=$toptech_path/customer/$cus_id/"));
        sw.set屏名(e脚本宏查值("panelname"));
        sw.set按键数量(e脚本宏查值("keypad_file"));
        sw=PlanType(sw);
        sw=Pq赋值(sw);

        return sw;
    }

    private static SW Pq赋值(SW sw) {
        String 色温String=Fileprocessing.readTxtFile(sw.get软件色温文件路径());
        String[] e色温数组=色温String.split("\n");


        for (int i = 0; i <e色温数组.length ; i++) {
            if (e色温数组[i].indexOf("mode: Standard")!=-1){
                sw.getPQ数据().setStandardR(e色温数组[i+3].substring(0,4).replace(",",""));
                sw.getPQ数据().setStandardG(e色温数组[i+10].substring(0,4).replace(",",""));
                sw.getPQ数据().setStandardB(e色温数组[i+14].substring(0,4).replace(",",""));
            }else if (e色温数组[i].indexOf("mode: Cool")!=-1){
                sw.getPQ数据().setCoolR(e色温数组[i+3].substring(0,4).replace(",",""));
                sw.getPQ数据().setCoolG(e色温数组[i+10].substring(0,4).replace(",",""));
                sw.getPQ数据().setCoolB(e色温数组[i+14].substring(0,4).replace(",",""));
            }else if (e色温数组[i].indexOf("mode: Warm")!=-1){
                sw.getPQ数据().setWarmR(e色温数组[i+3].substring(0,4).replace(",",""));
                sw.getPQ数据().setWarmG(e色温数组[i+10].substring(0,4).replace(",",""));
                sw.getPQ数据().setWarmB(e色温数组[i+14].substring(0,4).replace(",",""));
            }
        }
        return sw;
    }

    private static String e脚本宏查值( String e宏) {
        for (String a:Stringshu){
            if (a.indexOf(e宏)!=-1){
                return a.replace(e宏,"").replace("=","");
            }
        }
        return "未识别到这个宏";
    }

    public static SW PlanType(SW sw){
        String swname=sw.get软件名称();
        if (swname.indexOf("368")!=-1){
            return 赋值368(sw);
        }else if (swname.indexOf("9255")!=-1){
            return 赋值368(sw);
        }else if (swname.indexOf("2851")!=-1){

        }else if (swname.indexOf("9632")!=-1){

        }
        return sw;
    }

    private static SW 赋值368(SW sw) {
        sw.set软件logo路径全称(LOGO路径368+sw.get软件logo名()+".jpg");
        sw.set软件屏参名路径全称(屏参路径368+sw.get屏名());
        sw.set软件客制化路径全称(客制化文件夹路径368+sw.get软件客制化名称()+"/");
        sw.set软件色温文件路径(sw.get软件客制化路径全称()+"default_ini/OsdMapping.ini");
        sw.set软件logo前端(sw.get软件logo路径全称().substring(3));
        return sw;
    }
    //368方案相关路径
    static String  脚本路径368="Z:/9255/New9255/MTK368P/vendor/toptech/buildsettings/";
    static String  LOGO路径368="Z:/9255/New9255/MTK368P/vendor/toptech/customer/common/bootlogo/";
    static String  屏参路径368="Z:/9255/New9255/MTK368P/vendor/mediatek/proprietary_tv/apollo/linux_core/misdk/mi/mi/platform/common/ini/main/panel/";
    static String  客制化文件夹路径368="Z:/9255/New9255/MTK368P/vendor/toptech/customer/C001/";
}
