package com.liufujun.game.pdf;

import com.liufujun.game.pdf.pojo.User;
import com.liufujun.game.pdf.util.Country;
import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.pdf.util.StringUtil;
import com.liufujun.game.util.服务器使用路径;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static String pdfpath = "", jbpath = "";
    public static User user = new User();
    static Scanner scanner=new Scanner(System.in);
    public static String 脚本名;
//    public static void main(String[] args) {
////        Country.语言("英语法语俄语阿拉伯语波斯语土耳其语","狗语");
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入pdf路径");
//        pdfpath = scanner.nextLine();
//        System.out.println("请输入参考脚本路径");
//        jbpath = scanner.nextLine();
//        pdf(pdfpath, jbpath);
//
//    }
    static String  打印="";
    public static String pdf(String pdfsrc, String jbsrc) {/*创建PdfDocument实例*/
        打印="";
        pdfpath=pdfsrc;jbpath=jbsrc;
        System.out.println(pdfsrc+"\n"+jbsrc);
        String jbname=jbpath.substring(jbpath.indexOf("C001_"));
        PdfDocument doc = new PdfDocument();/*加载PDF文件*/
        doc.loadFromFile(pdfsrc);/*创建StringBuilder实例*/
        StringBuilder sb = new StringBuilder();
        String sc = "";
        PdfPageBase page;/*遍历PDF页面，获取每个页面的文本并添加到StringBuilder对象*/
        for (int i = 0; i < doc.getPages().getCount(); i++) {
            page = doc.getPages().get(i);
            sb.append(page.extractText(true));
        }
        FileWriter writer;
        sc = sb.toString();
        sc = replaceBlank(sc);

        Stringpick(sc);
        isUser();
        sc =Fileprocessing.readTxtFile(jbsrc);
        StringUpdete(sc);
        sc=jbString;

        jbname=jbNameString(jbname);
        Fileprocessing.updateFile(服务器使用路径.彩讯订单脚本生成路径+jbname,sc);
        脚本名=jbname;
        return 打印;
    }

    private static String jbNameString(String jbname) {
        旧版型= StringUtil.删除字符(旧版型,"\"");
        String 旧Mirror="MirrorOn_";
        if (jbString.indexOf("mirror_on=0")!=-1){
            旧Mirror="MirrorOFF_";
        }
        String 订单机型板卡=jbname.substring(jbname.indexOf("-")-6,jbname.indexOf(旧版型)+旧版型.length());
        String 旧客户名=StringUtil.删除字符(jbname.substring(0,jbname.indexOf(订单机型板卡)),"C001",旧国家,"_");
        String 电流=v(jbname,"SW","MA");
        if (电流.equals("")){
            电流=v(jbname,"sw","ma");
        }
        String 屏名logo遥控器智像=v(jbname,"_8G_",".");

        String new订单机型板卡=user.get客户订单号()+"_"+user.get机型()+"_"+user.get版型();

        String new屏名logo遥控器智像=user.get屏名()+"_"+user.get开机logo()+"_C001_"+user.get遥控器名()+"_Zeasn";
        jbname=jbname.replace(订单机型板卡,new订单机型板卡);
        jbname=jbname.replace(电流,user.get屏背光电流());
        jbname=jbname.replace(屏名logo遥控器智像,new屏名logo遥控器智像);
        jbname=jbname.replace(旧国家,Country.国家(user.get国家()));
        System.out.println(旧国家+"::"+user.get国家());
        jbname=jbname.replace(旧客户名,user.get客户缩写());
        if (jbname.indexOf("Mirror")==-1){
            jbname=jbname.replace(new屏名logo遥控器智像,旧Mirror+new屏名logo遥控器智像);
        }
        System.out.println(jbname);
        if (isPDF){
            打印+="\n"+"脚本名：\n<span style=\"color: green\">"+jbname+"</span>";
        }else {
            打印+="\n"+jbname;
        }

        return jbname;
    }
    static boolean isPDF=true;
    private static void isUser() {
        isPDF=true;
        if (user.get屏名().equals("")){
            isPDF=false;
            打印+="<span style=\"color: red\">未识别到屏名，请输入屏名</span>\n";
        }
        if (user.get开机logo()==null||user.get开机logo().equals("")||user.get开机logo().equals("/")){
            isPDF=false;
            打印+="<span style=\"color: red\">未识别到开机logo，请输入logo名</span>\n";
        }
        if (user.get屏贴().equals("□")){
            user.set屏贴("false");
        }else {
            user.set屏贴("true");
        }
        if (isPDF)
        打印+="<span style='color:green;'>成功！！！</span>\n"+user.toString();
        else  打印+="<span style='color:red;'>失败！！！</span>\n"+user.toString();
        System.out.println(user.toString());
    }

    static String jbString;
    static String 旧国家;
    static String 旧版型;
    private static void StringUpdete(String sc) {
        jbString=sc;

        String pcb_board_type="Toptech_PCB_BOARD_"+user.get版型();
        Stringmacro("pcb_board_type",pcb_board_type);

        String config_pcb_varient="Toptech_PCB_BOARD_"+user.get版型();
        Stringmacro("config_pcb_varient",config_pcb_varient);

        String cus_pro_id="\""+user.get机型()+"_"+user.get版型()+"_"+user.get客户订单号()+"\"";
        Stringmacro("cus_pro_id",cus_pro_id);

        String pcbname="\""+user.get版型()+"\"";
        Stringmacro("pcbname",pcbname);

        String customer_folder="";
        if(user.get版型().indexOf("9632")!=-1){
            customer_folder ="$toptech_path/customer/9632/$cus_id/"+user.get客户订单号()+"_"+Country.国家(user.get国家())+"_"+user.get机型()+"_"+user.get版型()+"_"+user.get屏名()+"_CX"+user.get遥控器名()+"_"+user.get开机logo();
        }else {
            customer_folder ="$toptech_path/customer/$cus_id/"+user.get客户订单号()+"_"+Country.国家(user.get国家())+"_"+user.get机型()+"_"+user.get版型()+"_"+user.get屏名()+"_CX"+user.get遥控器名()+"_"+user.get开机logo();

        }
        Stringmacro("customer_folder",customer_folder);

        String panelname=user.get屏名()+".ini";
        Stringmacro("panelname",panelname);

        String pwm_max_current=user.get屏背光电流()+"ma";
        Stringmacro("pwm_max_current",pwm_max_current);

        String ir_file="C001_"+user.get遥控器名();
        Stringmacro("ir_file",ir_file);

        String keypad_file=Country.按键板(user.get按键数量());
        Stringmacro("keypad_file",keypad_file);

        String language_list="\""+user.get语言()+"\"";
        Stringmacro("language_list",language_list);

        String timezone="\""+Country.时区(user.get国家())+"\"";
        Stringmacro("timezone",timezone);

        String default_country=user.get国家();
        Stringmacro("default_country",default_country);

        String zeasn_country=Country.国家(user.get国家());
        Stringmacro("zeasn_country",zeasn_country);

        String bootlogo_file="bootlogo_C001_"+user.get开机logo();
        Stringmacro("bootlogo_file",bootlogo_file);

        String config_sticker_visible=user.get屏贴();
        Stringmacro("config_sticker_visible",config_sticker_visible);
    }

    private static void Stringmacro(String pcb_board_type, String pcb_board_type1) {
        String Stringshu[]=jbString.split("\n");
        for (int i=0;i<Stringshu.length;i++) {
            String yuan = v(Stringshu[i],pcb_board_type+"=","");
            if (!yuan.equals("")){
                if (pcb_board_type.equals("zeasn_country")){
                    旧国家=yuan;
                }else if (pcb_board_type.equals("pcbname")){
                    旧版型=yuan;
                }
                if (yuan.indexOf(pcb_board_type1)==-1){
                    Stringshu[i] = Stringshu[i].replace(yuan, pcb_board_type1);
                }

            }
        }
        jbString="";
        for (int i=0;i<Stringshu.length;i++) {
            jbString+=Stringshu[i]+"\n";
        }
    }


    private static void Stringpick(String sc) {
        user.set客户订单号(sc.substring(sc.indexOf("生产批号") + 4, sc.indexOf("生产批号") + 14));/*        System.out.println(sc.indexOf(")",sc.indexOf("共2页")));*/
        user.set机型(sc.substring(sc.indexOf(")", sc.indexOf("共2页")) + 1, sc.indexOf(")", sc.indexOf("共2页")) + 8).replace("-", ""));
        user.set版型(Country.returnBoard(v(sc,"DK","8G")));
        user.set国家(Country.国家368(v(sc, "销售地区", "制式")));
        if (user.get开机logo()==null||user.get开机logo().equals("")||user.get开机logo().equals("/")) {
            if (sc.indexOf("■丝印位置：") != -1)
                user.set开机logo(v(sc, "商标1", "丝印").substring(0, v(sc, "商标1", "丝印").length() - 1).toUpperCase());
            else if (sc.indexOf("商标1/■无") != -1) {
                user.set开机logo("/");
            }
        }
        user.set语言(Country.语言(v(sc,"OSD","语言默认语言").replace("/",""),v(sc,"默认语言","电视制式")));
        user.set电视制式(v(sc,"电视制式■","电伴音及输出").replace("■"," "));
        if (user.get屏名()==null||user.get屏名().equals("")||user.get屏名().equals("/")){
            user.set屏名(v(sc,"玻璃型号","数量").replace("-","_"));
        }
        user.set屏分辨率(v(sc,"寸分辨率","背光"));
        user.set屏背光电流(v(sc,"背光","玻璃品牌").toLowerCase().substring(v(sc,"背光","玻璃品牌").toLowerCase().indexOf("ma")-3,v(sc,"背光","玻璃品牌").toLowerCase().indexOf("ma")));
        user.set按键数量(v(sc,"按键数量","键"));
        user.set遥控器名(v(sc,"遥控器型号1:","数量").replace("CX-",""));
        user.set遥控器码值(sc.substring(sc.indexOf("件型号2")-6,sc.indexOf("件型号2")));
        user.set屏贴(sc.substring(sc.indexOf("电子屏贴")-1,sc.indexOf("电子屏贴")));
        user.set伴音及输出(v(sc,"伴音及输出","2021"));
        user.set附加功能(v(sc,"附加功能","能主板功能"));
        user.set客户缩写(sc.substring(sc.indexOf("客户名称")+4,sc.indexOf("客户名称")+7));
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

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n|");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;

    }

}