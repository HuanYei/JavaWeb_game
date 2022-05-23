package com.liufujun.game.me.dao;

import com.liufujun.game.me.pojo.PQ;
import com.liufujun.game.me.pojo.SW;
import com.liufujun.game.me.pojo.SwEnglish;
import com.liufujun.game.pdf.Main;
import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.pdf.util.StringUtil;
import com.liufujun.game.util.PlanUtil;
import com.liufujun.game.util.RAWUtils;
import com.liufujun.game.util.服务器使用路径;

import java.io.File;

public class SwDao {
    static  String Stringshu[];
    public static SW 读取软件所有属性(String swpath,SW sw){
        String e脚本内容= Fileprocessing.readTxtFile(swpath);
        Stringshu=e脚本内容.split("\n");

        if (sw.get方案().equals("2851")||sw.get方案().equals("2842")||sw.get方案().equals("2853")||sw.get方案().equals("2843")){
            //RTK
            sw.getSWinfo().setIRname(e脚本宏查值("config_remote_name"));
            sw.getSWinfo().setKEYboardName(e脚本宏查值("config_keypad_name"));
            sw.set软件logo名(e脚本宏查值("config_bootlogo_name"));
            sw.set屏名(e脚本宏查值("config_panel_name").replace("\"",""));
            sw.set按键数量(e脚本宏查值("config_keypad_name"));
            sw.set客户名缩写(e脚本宏查值("config_customer_name").replace("\"","").substring(0,4));
            sw.set软件客制化名称(e脚本宏查值("config_customer_folder_name").replace("\"",""));

            sw.setIsrtk(1);
            PlanType(sw);
            PanelDao.PanelRTK赋值(sw);

            sw.getSWinfo().setKEYboardType(SWinfoDao.按键板类型(sw,0));
            sw.setPQ数据(RtkpqDao.PQDate(sw.get软件色温文件路径(),new PQ()));
            sw.set电子屏贴路径(sw.get软件客制化路径全称()+"overlay/com.toptech.tvmenu/res/drawable-nodpi/sticker.png");
        }else {
            sw.setIsrtk(0);
            //MTK
            sw.getSWinfo().setKEYboardName(e脚本宏查值("keypad_file"));
            sw.getSWinfo().setIRname(e脚本宏查值("ir_file"));

            sw.set软件logo名(e脚本宏查值("bootlogo_file"));
            sw.set屏名(e脚本宏查值("panelname"));
            sw.set按键数量(e脚本宏查值("keypad_file"));
            sw.set客户名缩写(e脚本宏查值("cus_id").replace("\"","").substring(0,4));
            sw.set智像DP(e脚本宏查值("zeasn_devicetype")+"(country="+e脚本宏查值("zeasn_country")+")");
            if (sw.get方案().equals("368")){
                sw.set软件客制化名称(e脚本宏查值("customer_folder=$toptech_path/customer/$cus_id/"));
            }else if (sw.get方案().equals("9632")){
                sw.set软件客制化名称(e脚本宏查值("export customer_folder=$toptech_path/customer/9632/$cus_id/"));
                if (sw.get软件客制化名称().equals("未识别到这个宏")){
                    sw.set软件客制化名称(e脚本宏查值("customer_folder=$toptech_path/customer/9632/$cus_id/"));
                };
            }else if (sw.get方案().equals("6681")){
                sw.set软件客制化名称(e脚本宏查值("export customer_folder=$toptech_path/customer/6681/$cus_id/"));
                if (sw.get软件客制化名称().equals("未识别到这个宏")){
                    sw.set软件客制化名称(e脚本宏查值("customer_folder=$toptech_path/customer/6681/$cus_id/"));
                };
            }

            PlanType(sw);
            Pq赋值(sw);
            PanelDao.PanelMTK赋值(sw);


            sw.getSWinfo().setKEYboardType(SWinfoDao.按键板类型(sw,1));
            sw.setIs电子屏贴(e脚本宏查值("config_sticker_visible"));
            sw.set电子屏贴路径(sw.get软件客制化路径全称()+"overlay/com.toptech.tvmenu/res/drawable-nodpi/sticker.png");
        }
        SWinfoDao.SWinfohandle(sw);
        return sw;
    }



    public static void MTKPQ写入(SwEnglish sw){
        String 线性String=Fileprocessing.readTxtFile(StringUtil.提取文件路径(sw.getSoftware_color_temperature_file_path())+"NLA.ini");
        String e线性数组[]=线性String.split("\n");
        for (int i = 0; i <e线性数组.length ; i++) {
            if (e线性数组[i].indexOf("#CVBS")!=-1){
                e线性数组[i+4]=e写入(e线性数组[i+4],2,sw.getPQ_data().getAVBrightness());
                e线性数组[i+5]=e写入(e线性数组[i+5],2,sw.getPQ_data().getAVContrast());
                e线性数组[i+6]=e写入(e线性数组[i+6],2,sw.getPQ_data().getAVSaturation());
                e线性数组[i+7]=e写入(e线性数组[i+7],2,sw.getPQ_data().getAVSharpness());
                e线性数组[i+8]=e写入(e线性数组[i+8],2,sw.getPQ_data().getAVHue());
            }else if (e线性数组[i].indexOf("#YPbPr")!=-1){
                e线性数组[i+4]=e写入(e线性数组[i+4],2,sw.getPQ_data().getYPBPRBrightness());
                e线性数组[i+5]=e写入(e线性数组[i+5],2,sw.getPQ_data().getYPBPRContrast());
                e线性数组[i+6]=e写入(e线性数组[i+6],2,sw.getPQ_data().getYPBPRSaturation());
                e线性数组[i+7]=e写入(e线性数组[i+7],2,sw.getPQ_data().getYPBPRSharpness());
                e线性数组[i+8]=e写入(e线性数组[i+8],2,sw.getPQ_data().getYPBPRHue());
            }else if (e线性数组[i].indexOf("#VGA")!=-1){
                e线性数组[i+4]=e写入(e线性数组[i+4],2,sw.getPQ_data().getVGABrightness());
                e线性数组[i+5]=e写入(e线性数组[i+5],2,sw.getPQ_data().getVGAContrast());
                e线性数组[i+6]=e写入(e线性数组[i+6],2,sw.getPQ_data().getVGASaturation());
                e线性数组[i+7]=e写入(e线性数组[i+7],2,sw.getPQ_data().getVGASharpness());
                e线性数组[i+8]=e写入(e线性数组[i+8],2,sw.getPQ_data().getVGAHue());
            }else if (e线性数组[i].indexOf("#HDMI")!=-1){
                e线性数组[i+4]=e写入(e线性数组[i+4],2,sw.getPQ_data().getHDMIBrightness());
                e线性数组[i+5]=e写入(e线性数组[i+5],2,sw.getPQ_data().getHDMIContrast());
                e线性数组[i+6]=e写入(e线性数组[i+6],2,sw.getPQ_data().getHDMISaturation());
                e线性数组[i+7]=e写入(e线性数组[i+7],2,sw.getPQ_data().getHDMISharpness());
                e线性数组[i+8]=e写入(e线性数组[i+8],2,sw.getPQ_data().getHDMIHue());
            }else if (e线性数组[i].indexOf("#DTV")!=-1){
                e线性数组[i+4]=e写入(e线性数组[i+4],2,sw.getPQ_data().getDTVBrightness());
                e线性数组[i+5]=e写入(e线性数组[i+5],2,sw.getPQ_data().getDTVContrast());
                e线性数组[i+6]=e写入(e线性数组[i+6],2,sw.getPQ_data().getDTVSaturation());
                e线性数组[i+7]=e写入(e线性数组[i+7],2,sw.getPQ_data().getDTVSharpness());
                e线性数组[i+8]=e写入(e线性数组[i+8],2,sw.getPQ_data().getDTVHue());
            }else if (e线性数组[i].indexOf("#ATV")!=-1){
                e线性数组[i+4]=e写入(e线性数组[i+4],2,sw.getPQ_data().getATVBrightness());
                e线性数组[i+5]=e写入(e线性数组[i+5],2,sw.getPQ_data().getATVContrast());
                e线性数组[i+6]=e写入(e线性数组[i+6],2,sw.getPQ_data().getATVSaturation());
                e线性数组[i+7]=e写入(e线性数组[i+7],2,sw.getPQ_data().getATVSharpness());
                e线性数组[i+8]=e写入(e线性数组[i+8],2,sw.getPQ_data().getATVHue());
            }else if (e线性数组[i].indexOf("#Others")!=-1){
                e线性数组[i+4]=e写入(e线性数组[i+4],2,sw.getPQ_data().getUSBBrightness());
                e线性数组[i+5]=e写入(e线性数组[i+5],2,sw.getPQ_data().getUSBContrast());
                e线性数组[i+6]=e写入(e线性数组[i+6],2,sw.getPQ_data().getUSBSaturation());
                e线性数组[i+7]=e写入(e线性数组[i+7],2,sw.getPQ_data().getUSBSharpness());
                e线性数组[i+8]=e写入(e线性数组[i+8],2,sw.getPQ_data().getUSBHue());
            }
        }
        线性String="";
        for (int i = 0; i <e线性数组.length ; i++) {
            线性String+=e线性数组[i]+"\n";
        }
        Fileprocessing.updateFile(StringUtil.提取文件路径(sw.getSoftware_color_temperature_file_path())+"NLA.ini",线性String);
    }

    public static String e写入(String date, int i,String str) {
        String datearr[] = date.split(",");
        datearr[i]=str;
        str="";
        for (String a:datearr) {
            str+=a+",";
        }
        return str.substring(0,str.length()-1);
    }

    private static SW Pq赋值(SW sw) {
        String 线性String=Fileprocessing.readTxtFile(StringUtil.提取文件路径(sw.get软件色温文件路径())+"NLA.ini");
        String e线性数组[]=线性String.split("\n");

        for (int i = 0; i <e线性数组.length ; i++) {
            if (e线性数组[i].indexOf("#CVBS")!=-1){
                sw.getPQ数据().setAVBrightness(RtkpqDao.Color赋值(e线性数组[i+4],2));
                sw.getPQ数据().setAVContrast(RtkpqDao.Color赋值(e线性数组[i+5],2));
                sw.getPQ数据().setAVHue(RtkpqDao.Color赋值(e线性数组[i+8],2));
                sw.getPQ数据().setAVSaturation(RtkpqDao.Color赋值(e线性数组[i+6],2));
                sw.getPQ数据().setAVSharpness(RtkpqDao.Color赋值(e线性数组[i+7],2));
            }else if (e线性数组[i].indexOf("#YPbPr")!=-1){
                sw.getPQ数据().setYPBPRBrightness(RtkpqDao.Color赋值(e线性数组[i+4],2));
                sw.getPQ数据().setYPBPRContrast(RtkpqDao.Color赋值(e线性数组[i+5],2));
                sw.getPQ数据().setYPBPRHue(RtkpqDao.Color赋值(e线性数组[i+8],2));
                sw.getPQ数据().setYPBPRSaturation(RtkpqDao.Color赋值(e线性数组[i+6],2));
                sw.getPQ数据().setYPBPRSharpness(RtkpqDao.Color赋值(e线性数组[i+7],2));
            }else if (e线性数组[i].indexOf("#VGA")!=-1){
                sw.getPQ数据().setVGABrightness(RtkpqDao.Color赋值(e线性数组[i+4],2));
                sw.getPQ数据().setVGAContrast(RtkpqDao.Color赋值(e线性数组[i+5],2));
                sw.getPQ数据().setVGAHue(RtkpqDao.Color赋值(e线性数组[i+8],2));
                sw.getPQ数据().setVGASaturation(RtkpqDao.Color赋值(e线性数组[i+6],2));
                sw.getPQ数据().setVGASharpness(RtkpqDao.Color赋值(e线性数组[i+7],2));
            }else if (e线性数组[i].indexOf("#HDMI")!=-1){
                sw.getPQ数据().setHDMIBrightness(RtkpqDao.Color赋值(e线性数组[i+4],2));
                sw.getPQ数据().setHDMIContrast(RtkpqDao.Color赋值(e线性数组[i+5],2));
                sw.getPQ数据().setHDMIHue(RtkpqDao.Color赋值(e线性数组[i+8],2));
                sw.getPQ数据().setHDMISaturation(RtkpqDao.Color赋值(e线性数组[i+6],2));
                sw.getPQ数据().setHDMISharpness(RtkpqDao.Color赋值(e线性数组[i+7],2));
            }else if (e线性数组[i].indexOf("#DTV")!=-1){
                sw.getPQ数据().setDTVBrightness(RtkpqDao.Color赋值(e线性数组[i+4],2));
                sw.getPQ数据().setDTVContrast(RtkpqDao.Color赋值(e线性数组[i+5],2));
                sw.getPQ数据().setDTVHue(RtkpqDao.Color赋值(e线性数组[i+8],2));
                sw.getPQ数据().setDTVSaturation(RtkpqDao.Color赋值(e线性数组[i+6],2));
                sw.getPQ数据().setDTVSharpness(RtkpqDao.Color赋值(e线性数组[i+7],2));
            }else if (e线性数组[i].indexOf("#ATV")!=-1){
                sw.getPQ数据().setATVBrightness(RtkpqDao.Color赋值(e线性数组[i+4],2));
                sw.getPQ数据().setATVContrast(RtkpqDao.Color赋值(e线性数组[i+5],2));
                sw.getPQ数据().setATVHue(RtkpqDao.Color赋值(e线性数组[i+8],2));
                sw.getPQ数据().setATVSaturation(RtkpqDao.Color赋值(e线性数组[i+6],2));
                sw.getPQ数据().setATVSharpness(RtkpqDao.Color赋值(e线性数组[i+7],2));
            }else if (e线性数组[i].indexOf("#Others")!=-1){
                sw.getPQ数据().setUSBBrightness(RtkpqDao.Color赋值(e线性数组[i+4],2));
                sw.getPQ数据().setUSBContrast(RtkpqDao.Color赋值(e线性数组[i+5],2));
                sw.getPQ数据().setUSBHue(RtkpqDao.Color赋值(e线性数组[i+8],2));
                sw.getPQ数据().setUSBSaturation(RtkpqDao.Color赋值(e线性数组[i+6],2));
                sw.getPQ数据().setUSBSharpness(RtkpqDao.Color赋值(e线性数组[i+7],2));
            }
        }
        
        String 色温String=Fileprocessing.readTxtFile(sw.get软件色温文件路径());
        String[] e色温数组=色温String.split("\n");


        for (int i = 0; i <e色温数组.length ; i++) {
            if (e色温数组[i].indexOf("mode: Standard")!=-1){
                sw.getPQ数据().setStandardR(e色温数组[i+3].substring(0,4).replace(",",""));
                sw.getPQ数据().setStandardG(e色温数组[i+10].substring(0,4).replace(",",""));
                sw.getPQ数据().setStandardB(e色温数组[i+14].substring(0,4).replace(",",""));
                for (int j = 0; j <34 ; j++) {
                    if (e色温数组[i+j].indexOf("COLOR_TEMP_MODE3_RED_OFFSET")!=-1){
                        sw.getPQ数据().setStandardROFF(e色温数组[i+j+1].substring(0,4).replace(",",""));
                    }else if (e色温数组[i+j].indexOf("COLOR_TEMP_MODE3_GREEN_OFFSET")!=-1){
                        sw.getPQ数据().setStandardGOFF(e色温数组[i+j+1].substring(0,4).replace(",",""));
                    }else if (e色温数组[i+j].indexOf("COLOR_TEMP_MODE3_BLUE_OFFSET")!=-1){
                        sw.getPQ数据().setStandardBOFF(e色温数组[i+j+1].substring(0,4).replace(",",""));
                    }
                }
            }else if (e色温数组[i].indexOf("mode: Cool")!=-1){
                sw.getPQ数据().setCoolR(e色温数组[i+3].substring(0,4).replace(",",""));
                sw.getPQ数据().setCoolG(e色温数组[i+10].substring(0,4).replace(",",""));
                sw.getPQ数据().setCoolB(e色温数组[i+14].substring(0,4).replace(",",""));
                for (int j = 0; j <34 ; j++) {
                    if (e色温数组[i+j].indexOf("COLOR_TEMP_MODE2_RED_OFFSET")!=-1){
                        sw.getPQ数据().setCoolROFF(e色温数组[i+j+1].substring(0,4).replace(",",""));
                    }else if (e色温数组[i+j].indexOf("COLOR_TEMP_MODE2_GREEN_OFFSET")!=-1){
                        sw.getPQ数据().setCoolGOFF(e色温数组[i+j+1].substring(0,4).replace(",",""));
                    }else if (e色温数组[i+j].indexOf("COLOR_TEMP_MODE2_BLUE_OFFSET")!=-1){
                        sw.getPQ数据().setCoolBOFF(e色温数组[i+j+1].substring(0,4).replace(",",""));
                    }
                }
            }else if (e色温数组[i].indexOf("mode: Warm")!=-1){
                sw.getPQ数据().setWarmR(e色温数组[i+3].substring(0,4).replace(",",""));
                sw.getPQ数据().setWarmG(e色温数组[i+10].substring(0,4).replace(",",""));
                sw.getPQ数据().setWarmB(e色温数组[i+14].substring(0,4).replace(",",""));
                for (int j = 0; j <34 ; j++) {
                    if (e色温数组[i+j].indexOf("COLOR_TEMP_MODE1_RED_OFFSET")!=-1){
                        sw.getPQ数据().setWarmROFF(e色温数组[i+j+1].substring(0,4).replace(",",""));
                    }else if (e色温数组[i+j].indexOf("COLOR_TEMP_MODE1_GREEN_OFFSET")!=-1){
                        sw.getPQ数据().setWarmGOFF(e色温数组[i+j+1].substring(0,4).replace(",",""));
                    }else if (e色温数组[i+j].indexOf("COLOR_TEMP_MODE1_BLUE_OFFSET")!=-1){
                        sw.getPQ数据().setWarmBOFF(e色温数组[i+j+1].substring(0,4).replace(",",""));
                    }
                }
            }else if (e色温数组[i].indexOf("mode: User")!=-1){
                sw.getPQ数据().setUserR(e色温数组[i+3].substring(0,4).replace(",",""));
                sw.getPQ数据().setUserG(e色温数组[i+10].substring(0,4).replace(",",""));
                sw.getPQ数据().setUserB(e色温数组[i+14].substring(0,4).replace(",",""));
                for (int j = 0; j <34 ; j++) {
                    if (e色温数组[i+j].indexOf("COLOR_TEMP_MODE0_RED_OFFSET")!=-1){
                        sw.getPQ数据().setUserROFF(e色温数组[i+j+1].substring(0,4).replace(",",""));
                    }else if (e色温数组[i+j].indexOf("COLOR_TEMP_MODE0_GREEN_OFFSET")!=-1){
                        sw.getPQ数据().setUserGOFF(e色温数组[i+j+1].substring(0,4).replace(",",""));
                    }else if (e色温数组[i+j].indexOf("COLOR_TEMP_MODE0_BLUE_OFFSET")!=-1){
                        sw.getPQ数据().setUserBOFF(e色温数组[i+j+1].substring(0,4).replace(",",""));
                    }
                }
            }
        }
        return sw;
    }

    public static String e脚本宏查值( String e宏) {
        for (String a:Stringshu){
            if (a.indexOf(e宏)==0){
                return a.replace(e宏,"").replace("=","").replace("export ","").trim();
            }
        }
        return "未识别到这个宏";
    }

    public static SW PlanType(SW sw){
        String swname=sw.get软件名称();
        sw.setIRimgPath("res/IR/"+sw.get方案()+"/"+sw.getSWinfo().getIRname()+".jpg");
        if (swname.indexOf("368")!=-1){
            return 赋值368(sw);
        }else if (swname.indexOf("9255")!=-1){
            return 赋值368(sw);
        }else if (swname.indexOf("2851")!=-1){
            return 赋值2851(sw);
        }else if (swname.indexOf("2842")!=-1){
            return 赋值2842(sw);
        } else if (swname.indexOf("9632")!=-1){
            return 赋值9632(sw);
        }else if (swname.indexOf("6681")!=-1){
            return 赋值6681(sw);
        }else if (swname.indexOf("2853")!=-1){
            return 赋值2853(sw);
        }else if (swname.indexOf("2843")!=-1){
            return 赋值2843(sw);
        }


        return sw;
    }

    private static SW 赋值2843(SW sw) {
        RTK2851(sw);


        sw.getSWinfo().setIRpath(服务器使用路径.RTK2853PATH+"customer/IR/"+sw.getSWinfo().getIRname()+".config");

        sw.set软件logo路径全称(服务器使用路径.LOGO路径2843+sw.get软件logo名());
        sw.set软件屏参名路径全称(服务器使用路径.屏参路径2843+sw.get屏名()+".ini");
        sw.set软件客制化路径全称(服务器使用路径.客制化文件夹路径2843+sw.get客户名缩写()+"/"+sw.get软件客制化名称()+"/");
        String 软件色温文件夹=sw.get软件客制化路径全称()+"PQ_OverScan/";
        File file=new File(软件色温文件夹);
        if (file.exists()) {
            sw.set软件色温文件路径(Fileprocessing.lookupwai(软件色温文件夹, "Osd").get(0));
        }else {
            sw.set软件色温文件路径(服务器使用路径.RTK2853PATH+"customer/default/PQ_OverScan/2843/VIP_Panel_Default_Osd.cpp");
        }
        return sw;
    }

    private static SW 赋值2853(SW sw) {
        RTK2851(sw);
        sw.getSWinfo().setIRpath(服务器使用路径.RTK2853PATH+"customer/IR/"+sw.getSWinfo().getIRname()+".config");
        sw.set软件logo路径全称(服务器使用路径.LOGO路径2853+sw.get软件logo名());
        sw.set软件屏参名路径全称(服务器使用路径.屏参路径2853+sw.get屏名()+".ini");
        sw.set软件客制化路径全称(服务器使用路径.客制化文件夹路径2853+sw.get客户名缩写()+"/"+sw.get软件客制化名称()+"/");
        String 软件色温文件夹=sw.get软件客制化路径全称()+"PQ_OverScan/";
        if (!Fileprocessing.lookupwai(软件色温文件夹, "Osd").get(0).equals("无")) {
            sw.set软件色温文件路径(Fileprocessing.lookupwai(软件色温文件夹, "Osd").get(0));
        }else {
            sw.set软件色温文件路径(服务器使用路径.RTK2853PATH+"customer/default/PQ_OverScan/2853/VIP_Panel_Default_Osd.cpp");
        }
        return sw;
    }


    private static SW 赋值368(SW sw) {
        String bootvideoname=e脚本宏查值("config_bootvideo_name");
        if (bootvideoname.equals("")||bootvideoname.equals("未识别到这个宏")){
            sw.getSWinfo().setIsbootvideo("false");
        }else {
            sw.set软件开机视频路径全称(服务器使用路径.MTK368PATH+"vendor/toptech/customer/common/bootvideo/"+bootvideoname);
            sw.getSWinfo().setIsbootvideo("true");
        }
        sw.getSWinfo().setPath(服务器使用路径.MTK368PATH);
        sw.getSWinfo().setIRpath(服务器使用路径.MTK9632PATH+"vendor/toptech/customer/common/ir"+sw.getSWinfo().getIRname()+".ini");
        sw.set软件logo路径全称(服务器使用路径.LOGO路径368+sw.get软件logo名()+".jpg");
        sw.set软件屏参名路径全称(服务器使用路径.屏参路径368+sw.get屏名());
        sw.set软件客制化路径全称(服务器使用路径.客制化文件夹路径368+sw.get客户名缩写()+"/"+sw.get软件客制化名称()+"/");
        sw.set软件色温文件路径(sw.get软件客制化路径全称()+"default_ini/OsdMapping.ini");

        sw.set软件logo前端(sw.get软件logo路径全称().substring(3));

        return sw;
    }
    private static void MTK9632(SW sw){
        sw.getSWinfo().setPath(服务器使用路径.MTK9632PATH);
        String bootvideoname=e脚本宏查值("bootvideo_file");
        if (bootvideoname.equals("")||bootvideoname.equals("未识别到这个宏")){
            sw.getSWinfo().setIsbootvideo("false");
        }else {
            sw.set软件开机视频路径全称(服务器使用路径.MTK9632PATH+"vendor/toptech/customer/common/bootvideo/"+bootvideoname);
            sw.getSWinfo().setIsbootvideo("true");
        }
    }
    private static void RTK2851(SW sw){
        sw.getSWinfo().setPath(服务器使用路径.RTK2851PATH);
        String bootvideoname=e脚本宏查值("config_bootvideo_name");
        if (bootvideoname.equals("")||bootvideoname.equals("未识别到这个宏")){
            sw.getSWinfo().setIsbootvideo("false");
        }else {
            sw.set软件开机视频路径全称(服务器使用路径.RTK2851PATH+"customer/bootlogo/"+bootvideoname);
            sw.getSWinfo().setIsbootvideo("true");
        }
    }
    private static SW 赋值9632(SW sw) {
        MTK9632(sw);
        sw.getSWinfo().setIRpath(服务器使用路径.MTK9632PATH+"vendor/toptech/customer/common/ir/"+sw.getSWinfo().getIRname()+".ini");
        sw.set软件logo路径全称(服务器使用路径.LOGO路径9632+sw.get软件logo名()+".jpg");
        sw.set软件屏参名路径全称(服务器使用路径.屏参路径9632+sw.get屏名());
        sw.set软件客制化路径全称(服务器使用路径.客制化文件夹路径9632+sw.get客户名缩写()+"/"+sw.get软件客制化名称()+"/");
        sw.set软件色温文件路径(sw.get软件客制化路径全称()+"default_ini/OsdMapping.ini");
        sw.set软件logo前端(sw.get软件logo路径全称().substring(3));
        return sw;
    }
    private static SW 赋值6681(SW sw) {
        MTK9632(sw);
        sw.getSWinfo().setIRpath(服务器使用路径.MTK9632PATH+"vendor/toptech/customer/common/ir/"+sw.getSWinfo().getIRname()+".ini");
        sw.set软件logo路径全称(服务器使用路径.LOGO路径6681+sw.get软件logo名()+".jpg");
        sw.set软件屏参名路径全称(服务器使用路径.屏参路径6681+sw.get屏名());
        sw.set软件客制化路径全称(服务器使用路径.客制化文件夹路径6681+sw.get客户名缩写()+"/"+sw.get软件客制化名称()+"/");
        sw.set软件色温文件路径(sw.get软件客制化路径全称()+"default_ini/OsdMapping.ini");
        sw.set软件logo前端(sw.get软件logo路径全称().substring(3));
        return sw;
    }

    private static SW 赋值2851(SW sw) {
        RTK2851(sw);
        sw.getSWinfo().setIRpath(服务器使用路径.RTK2851PATH+"customer/IR/"+sw.getSWinfo().getIRname()+".config");
        sw.set软件logo路径全称(服务器使用路径.LOGO路径2851+sw.get软件logo名());
        sw.set软件屏参名路径全称(服务器使用路径.屏参路径2851+sw.get屏名()+".h");
        sw.set软件客制化路径全称(服务器使用路径.客制化文件夹路径2851+sw.get客户名缩写()+"/"+sw.get软件客制化名称()+"/");
        String 软件色温文件夹=sw.get软件客制化路径全称()+"pq/";
        if (!Fileprocessing.lookupwai(软件色温文件夹, "Osd").get(0).equals("无")) {
            sw.set软件色温文件路径(Fileprocessing.lookupwai(软件色温文件夹, "Osd").get(0));
        }else {
            sw.set软件色温文件路径(服务器使用路径.RTK2851PATH+"customer/pq/vip_default_osd.cpp");
        }
        return sw;
    }
    private static SW 赋值2842(SW sw) {
        RTK2851(sw);
        sw.getSWinfo().setIRpath(服务器使用路径.RTK2851PATH+"customer/IR/IR_img/"+sw.getSWinfo().getIRname()+".config");
        sw.set软件logo路径全称(服务器使用路径.LOGO路径2842+sw.get软件logo名());
        sw.set软件屏参名路径全称(服务器使用路径.屏参路径2842+sw.get屏名()+".h");
        sw.set软件客制化路径全称(服务器使用路径.客制化文件夹路径2842+sw.get客户名缩写()+"/"+sw.get软件客制化名称()+"/");
        String 软件色温文件夹=sw.get软件客制化路径全称()+"pq_RTK2842P/";
        File file=new File(软件色温文件夹);
        if (file.exists()) {
            sw.set软件色温文件路径(Fileprocessing.lookupwai(软件色温文件夹, "Osd").get(0));
        }else {
            sw.set软件色温文件路径(服务器使用路径.RTK2851PATH+"customer/pq_RTK2842P/vip_default_osd.cpp");
        }
        return sw;
    }
    public static void SW宏修改(String swname,String 客制化){
        System.out.println("swname = [" + swname + "], 客制化 = [" + 客制化 + "]");
        if ( PlanUtil.PlanType(swname).equals("368")){
            if (客制化.indexOf("bootlogo")!=-1){
                String jb =Stringmacro("bootlogo_file",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径368+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径368+swname+".sh",jb);
                return;
            }else if (客制化.indexOf(".ini")!=-1){
                String jb =Stringmacro("panelname",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径368+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径368+swname+".sh",jb);
                return;
            }
            String customer_folder ="$toptech_path/customer/$cus_id/"+客制化;
            String jb =Stringmacro("customer_folder",customer_folder,Fileprocessing.readTxtFile(服务器使用路径.脚本路径368+swname+".sh"));
            Fileprocessing.updateFile(服务器使用路径.脚本路径368+swname+".sh",jb);
        }else if (PlanUtil.PlanType(swname).equals("9632")){
            if (客制化.indexOf("bootlogo")!=-1){
                String jb =Stringmacro("bootlogo_file",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径9632+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径9632+swname+".sh",jb);
                return;
            }else if (客制化.indexOf(".ini")!=-1){
                String jb =Stringmacro("panelname",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径9632+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径9632+swname+".sh",jb);
                return;
            }
            String customer_folder ="$toptech_path/customer/9632/$cus_id/"+客制化;
            String jb =Stringmacro("customer_folder",customer_folder,Fileprocessing.readTxtFile(服务器使用路径.脚本路径9632+swname+".sh"));
            Fileprocessing.updateFile(服务器使用路径.脚本路径9632+swname+".sh",jb);
        }else if (PlanUtil.PlanType(swname).equals("6681")){
            if (客制化.indexOf("bootlogo")!=-1){
                String jb =Stringmacro("bootlogo_file",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径6681+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径6681+swname+".sh",jb);
                return;
            }else if (客制化.indexOf(".ini")!=-1){
                String jb =Stringmacro("panelname",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径6681+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径6681+swname+".sh",jb);
                return;
            }
            String customer_folder ="$toptech_path/customer/6681/$cus_id/"+客制化;
            String jb =Stringmacro("customer_folder",customer_folder,Fileprocessing.readTxtFile(服务器使用路径.脚本路径6681+swname+".sh"));
            Fileprocessing.updateFile(服务器使用路径.脚本路径6681+swname+".sh",jb);
        }else if (PlanUtil.PlanType(swname).equals("6681")){
            if (客制化.indexOf("bootlogo")!=-1){
                String jb =Stringmacro("bootlogo_file",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径6681+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径6681+swname+".sh",jb);
                return;
            }else if (客制化.indexOf(".ini")!=-1){
                String jb =Stringmacro("panelname",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径6681+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径6681+swname+".sh",jb);
                return;
            }
            String customer_folder ="$toptech_path/customer/$cus_id/"+客制化;
            String jb =Stringmacro("customer_folder",customer_folder,Fileprocessing.readTxtFile(服务器使用路径.脚本路径6681+swname+".sh"));
            Fileprocessing.updateFile(服务器使用路径.脚本路径6681+swname+".sh",jb);
        }
        else if (PlanUtil.PlanType(swname).equals("2851")){
            if (客制化.indexOf(".raw")!=-1){
                String jb =Stringmacro("config_bootlogo_name",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径2851+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径2851+swname+".sh",jb);
                return;
            }else if (客制化.indexOf("2851")!=-1){
                客制化="\""+客制化+"\"";
                String jb =Stringmacro("config_customer_folder_name",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径2851+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径2851+swname+".sh",jb);
                return;
            }
            客制化="\""+客制化+"\"";
            String jb =Stringmacro("config_panel_name",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径2851+swname+".sh"));
            Fileprocessing.updateFile(服务器使用路径.脚本路径2851+swname+".sh",jb);
        }else if (PlanUtil.PlanType(swname).equals("2842")){
            if (客制化.indexOf(".raw")!=-1){
                String jb =Stringmacro("config_bootlogo_name",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径2842+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径2842+swname+".sh",jb);
                return;
            }else if (客制化.indexOf("2842")!=-1){
                客制化="\""+客制化+"\"";
                String jb =Stringmacro("config_customer_folder_name",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径2842+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径2842+swname+".sh",jb);
                return;
            }
            客制化="\""+客制化+"\"";
            String jb =Stringmacro("config_panel_name",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径2842+swname+".sh"));
            Fileprocessing.updateFile(服务器使用路径.脚本路径2842+swname+".sh",jb);
        }else if (PlanUtil.PlanType(swname).equals("2853")){
            if (客制化.indexOf(".raw")!=-1){
                String jb =Stringmacro("config_bootlogo_name",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径2853+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径2853+swname+".sh",jb);
                return;
            }else if (客制化.indexOf("2853")!=-1){
                客制化="\""+客制化+"\"";
                String jb =Stringmacro("config_customer_folder_name",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径2853+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径2853+swname+".sh",jb);
                return;
            }
            客制化="\""+客制化+"\"";
            String jb =Stringmacro("config_panel_name",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径2853+swname+".sh"));
            Fileprocessing.updateFile(服务器使用路径.脚本路径2853+swname+".sh",jb);
        }else if (PlanUtil.PlanType(swname).equals("2843")){
            if (客制化.indexOf(".raw")!=-1){
                String jb =Stringmacro("config_bootlogo_name",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径2843+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径2843+swname+".sh",jb);
                return;
            }else if (客制化.indexOf("2842")!=-1){
                客制化="\""+客制化+"\"";
                String jb =Stringmacro("config_customer_folder_name",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径2843+swname+".sh"));
                Fileprocessing.updateFile(服务器使用路径.脚本路径2843+swname+".sh",jb);
                return;
            }
            客制化="\""+客制化+"\"";
            String jb =Stringmacro("config_panel_name",客制化,Fileprocessing.readTxtFile(服务器使用路径.脚本路径2843+swname+".sh"));
            Fileprocessing.updateFile(服务器使用路径.脚本路径2843+swname+".sh",jb);
        }
    }


    private static String Stringmacro(String pcb_board_type, String pcb_board_type1,String jbString) {
        String Stringshu[]=jbString.split("\n");
        for (int i=0;i<Stringshu.length;i++) {
            String yuan = Main.v(Stringshu[i],pcb_board_type+"=","");
            if (!yuan.equals("")){
                Stringshu[i] = Stringshu[i].replace(yuan, pcb_board_type1);
            }
        }
        jbString="";
        for (int i=0;i<Stringshu.length;i++) {
            jbString+=Stringshu[i]+"\n";
        }
        return jbString;
    }

}
