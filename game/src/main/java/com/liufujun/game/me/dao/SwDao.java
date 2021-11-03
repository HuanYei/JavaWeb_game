package com.liufujun.game.me.dao;

import com.liufujun.game.me.pojo.PQ;
import com.liufujun.game.me.pojo.SW;
import com.liufujun.game.me.pojo.SwEnglish;
import com.liufujun.game.pdf.Main;
import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.util.PlanUtil;
import com.liufujun.game.util.RAWUtils;
import com.liufujun.game.util.服务器使用路径;

import java.io.File;

public class SwDao {
    static  String Stringshu[];
    public static SW 读取软件所有属性(String swpath,SW sw){
        String e脚本内容= Fileprocessing.readTxtFile(swpath);
        Stringshu=e脚本内容.split("\n");

        if (sw.get方案().equals("2851")||sw.get方案().equals("2842")){
            //RTK
            sw.set软件logo名(e脚本宏查值("config_bootlogo_name"));
            sw.set屏名(e脚本宏查值("config_panel_name").replace("\"",""));
            sw.set按键数量(e脚本宏查值("config_keypad_name"));
            sw.set客户名缩写(e脚本宏查值("config_customer_name").replace("\"","").substring(0,4));
            sw.set软件客制化名称(e脚本宏查值("config_customer_folder_name").replace("\"",""));
            sw.setIsrtk(1);
            sw=PlanType(sw);
            sw=PanelDao.PanelRTK赋值(sw);
            sw.setPQ数据(RtkpqDao.PQDate(sw.get软件色温文件路径(),new PQ()));
        }else {
            sw.setIsrtk(0);
            //MTK
            sw.set软件logo名(e脚本宏查值("bootlogo_file"));
            sw.set屏名(e脚本宏查值("panelname"));
            sw.set按键数量(e脚本宏查值("keypad_file"));
            sw.set客户名缩写(e脚本宏查值("cus_id").replace("\"","").substring(0,4));
            if (sw.get方案().equals("368")){
                sw.set软件客制化名称(e脚本宏查值("customer_folder=$toptech_path/customer/$cus_id/"));
            }else if (sw.get方案().equals("9632")){
                sw.set软件客制化名称(e脚本宏查值("customer_folder=$toptech_path/customer/9632/$cus_id/"));
            }else if (sw.get方案().equals("6681")){
                sw.set软件客制化名称(e脚本宏查值("customer_folder=$toptech_path/customer/6681/$cus_id/"));
            }
            sw=PlanType(sw);
            sw=Pq赋值(sw);
            sw=PanelDao.PanelMTK赋值(sw);
        }

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
                return a.replace(e宏,"").replace("=","").replace("export ","");
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
            return 赋值2851(sw);
        }else if (swname.indexOf("2842")!=-1){
            return 赋值2842(sw);
        } else if (swname.indexOf("9632")!=-1){
            return 赋值9632(sw);
        }else if (swname.indexOf("6681")!=-1){
            return 赋值6681(sw);
        }
        return sw;
    }

    private static SW 赋值368(SW sw) {
        sw.set软件logo路径全称(服务器使用路径.LOGO路径368+sw.get软件logo名()+".jpg");
        sw.set软件屏参名路径全称(服务器使用路径.屏参路径368+sw.get屏名());
        sw.set软件客制化路径全称(服务器使用路径.客制化文件夹路径368+sw.get客户名缩写()+"/"+sw.get软件客制化名称()+"/");
        sw.set软件色温文件路径(sw.get软件客制化路径全称()+"default_ini/OsdMapping.ini");
        sw.set软件logo前端(sw.get软件logo路径全称().substring(3));
        return sw;
    }

    private static SW 赋值9632(SW sw) {
        sw.set软件logo路径全称(服务器使用路径.LOGO路径9632+sw.get软件logo名()+".jpg");
        sw.set软件屏参名路径全称(服务器使用路径.屏参路径9632+sw.get屏名());
        sw.set软件客制化路径全称(服务器使用路径.客制化文件夹路径9632+sw.get客户名缩写()+"/"+sw.get软件客制化名称()+"/");
        sw.set软件色温文件路径(sw.get软件客制化路径全称()+"default_ini/OsdMapping.ini");
        sw.set软件logo前端(sw.get软件logo路径全称().substring(3));
        return sw;
    }
    private static SW 赋值6681(SW sw) {
        sw.set软件logo路径全称(服务器使用路径.LOGO路径6681+sw.get软件logo名()+".jpg");
        sw.set软件屏参名路径全称(服务器使用路径.屏参路径6681+sw.get屏名());
        sw.set软件客制化路径全称(服务器使用路径.客制化文件夹路径6681+sw.get客户名缩写()+"/"+sw.get软件客制化名称()+"/");
        sw.set软件色温文件路径(sw.get软件客制化路径全称()+"default_ini/OsdMapping.ini");
        sw.set软件logo前端(sw.get软件logo路径全称().substring(3));
        return sw;
    }

    private static SW 赋值2851(SW sw) {

        sw.set软件logo路径全称(服务器使用路径.LOGO路径2851+sw.get软件logo名());
        sw.set软件屏参名路径全称(服务器使用路径.屏参路径2851+sw.get屏名()+".h");
        sw.set软件客制化路径全称(服务器使用路径.客制化文件夹路径2851+sw.get客户名缩写()+"/"+sw.get软件客制化名称()+"/");
        String 软件色温文件夹=sw.get软件客制化路径全称()+"pq/";
        File file=new File(软件色温文件夹);
        if (file.exists()) {
            sw.set软件色温文件路径(Fileprocessing.lookupwai(软件色温文件夹, "Osd").get(0));
        }else {
            sw.set软件色温文件路径(服务器使用路径.RTK2851PATH+"customer/pq/vip_default_osd.cpp");
        }
        return sw;
    }
    private static SW 赋值2842(SW sw) {

        sw.set软件logo路径全称(服务器使用路径.LOGO路径2842+sw.get软件logo名());
        sw.set软件屏参名路径全称(服务器使用路径.屏参路径2842+sw.get屏名()+".h");
        sw.set软件客制化路径全称(服务器使用路径.客制化文件夹路径2842+sw.get客户名缩写()+"/"+sw.get软件客制化名称()+"/");
        return sw;
    }
    public static void SW宏修改(String swname,String 客制化){
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
