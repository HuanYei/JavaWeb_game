package com.liufujun.game.me.dao;

import com.liufujun.game.me.pojo.SW;
import com.liufujun.game.me.pojo.SwEnglish;
import com.liufujun.game.pdf.Main;
import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.util.PlanUtil;
import com.liufujun.game.util.服务器使用路径;

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
        sw=Panel赋值(sw);
        return sw;
    }

    private static SW Panel赋值(SW sw) {
        String PanelString=Fileprocessing.readTxtFile(sw.get软件屏参名路径全称());
        String[] e屏数组=PanelString.split("\n");
        for (int i = 0; i <e屏数组.length ; i++) {
            if (e屏数组[i].indexOf("m_bPanelLVDS_TI_MODE")!=-1){
                String Ti= Main.replaceBlank(e屏数组[i]);
                Ti=Ti.replace("m_bPanelLVDS_TI_MODE=","").substring(0,1);
                sw.getPanel().setTi_MODE(Integer.parseInt(Ti));
            }else if (e屏数组[i].indexOf("m_pPanelName")!=-1){
                String Name=Main.replaceBlank(e屏数组[i]);
                Name=Name.replace("m_pPanelName=","").replace(";","");
                sw.getPanel().setM_PNAME(Name);
            }else if (e屏数组[i].indexOf("m_bPanelSwapPort")!=-1){
                String SWAP=Main.replaceBlank(e屏数组[i]);
                SWAP=SWAP.replace("m_bPanelSwapPort=","").substring(0,1);
                sw.getPanel().setSwap_MODE(SWAP);
            }
        }

        return sw;
    }


    public static void Panel修改(SwEnglish sw) {
        System.out.println(sw.getFull_name_of_software_screen_parameter_name_path()+"SSSSSSSSS");
        String PanelString=Fileprocessing.readTxtFile(sw.getFull_name_of_software_screen_parameter_name_path());
        String[] e屏数组=PanelString.split("\n");
        for (int i = 0; i <e屏数组.length ; i++) {
            if (e屏数组[i].indexOf("m_bPanelLVDS_TI_MODE")!=-1){
                String a=e屏数组[i].substring(0,e屏数组[i].indexOf(";"));
                e屏数组[i]=e屏数组[i].replace(a,a.substring(0,a.length()-2)+" "+sw.getPanel().getTi_MODE());
            }else if (e屏数组[i].indexOf("m_pPanelName")!=-1){
                String a=e屏数组[i].substring(0,e屏数组[i].indexOf("=")+1);
                e屏数组[i]=a+"   "+sw.getPanel().getM_PNAME()+";";
            }else if (e屏数组[i].indexOf("m_bPanelSwapPort")!=-1){
                String a=e屏数组[i].substring(0,e屏数组[i].indexOf(";"));
                e屏数组[i]=e屏数组[i].replace(a,a.substring(0,a.length()-2)+" "+sw.getPanel().getSwap_MODE());
            }
        }
        PanelString="";
        for (int i = 0; i < e屏数组.length; i++) {
            PanelString+=e屏数组[i]+"\n";
        }
        Fileprocessing.updateFile(sw.getFull_name_of_software_screen_parameter_name_path(),PanelString);
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
        sw.set软件logo路径全称(服务器使用路径.LOGO路径368+sw.get软件logo名()+".jpg");
        sw.set软件屏参名路径全称(服务器使用路径.屏参路径368+sw.get屏名());
        sw.set软件客制化路径全称(服务器使用路径.客制化文件夹路径368+sw.get软件客制化名称()+"/");
        sw.set软件色温文件路径(sw.get软件客制化路径全称()+"default_ini/OsdMapping.ini");
        sw.set软件logo前端(sw.get软件logo路径全称().substring(3));

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
