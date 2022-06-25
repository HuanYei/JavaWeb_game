package com.liufujun.game.util;

public class Country {
    public static String 单个翻译语言(String yu){
        yu=yu.replace("■","");
        String nei= Fileprocessing.readTxtFile("res/config/语言.config");
        String list[]=nei.split("\n");
        for (String a:list) {
            if (a.indexOf(yu)!=-1){
                return a.replace("=","").replace(yu,"");
            }
        }
        return yu;
    }

    public static String 时区(String gu) {
        return to国家值(gu,1);
    }
    public static String 搜台制式(String gu) {
        return to国家值(gu,4)+"/"+to国家值(gu,5);
    }
    public static String 图文语言(String gu) {
        return to国家值(gu,6);
    }
    public static String 国家368(String gu){
        return to国家值(gu,2);
    }

    public static String 国家(String gu){
        return to国家值(gu,3);
    }
    public static String to国家值(String gu,int s){
        gu=gu.replace("■","").toLowerCase();
        String nei= Fileprocessing.readTxtFile("res/config/国家.config");
        String list[]=nei.split("\n");
        for (String a:list) {
            if (a.toLowerCase().indexOf(gu)!=-1){
                String sub[]=a.split("=");
                return sub[s];
            }
        }
        return gu;
    }
    public static String e软件名提取中文国家名(String SWname){
        SWname=SWname.toUpperCase();
        String nei= Fileprocessing.readTxtFile("res/config/国家.config");
        String list[]=nei.split("\n");
        System.out.println(SWname);
        for (String a:list) {
             String sub[]=a.split("=");
             if (sub.length<3){
                 continue;
             }
             if (sub[3].charAt(0)!='_'){
                 sub[3]="_"+sub[3];
             }

             if (SWname.indexOf(sub[3].toUpperCase())!=-1){
                 return sub[0];
             }
        }
        return "无";
    }

    public static String returnBoard(String Board){

        if (Board.indexOf("EL.MT9255-FA75")!=-1){
            return "EL_MT9255_FA75";
        }else if(Board.indexOf("EL.MT9255-FA48")!=-1){
            return "EL_MT9255_FA48";
        }else if(Board.indexOf("P150-2851V6.2")!=-1){
            return "P150_2851V62";
        }else if(Board.indexOf("P75-368V6.5")!=-1){
            return "P75_368_V65";
        }else if(Board.indexOf("P150-9632V6.2")!=-1){
            return "P150_9632V62";
        }else if(Board.indexOf("P50-368V5.0")!=-1){
            return "P50_368_V50";
        }
        return Board;
    }

    public static String 按键板(String an){
        switch (an){
            case "一":
                return "keypad_ToptechPublic_oneKey";
            case "1":
                return "keypad_ToptechPublic_oneKey";
            case "7":
                return "keypad_ToptechPublic_7Key";
            case "七":
                return "keypad_ToptechPublic_7Key";
            default:
                return an;
        }
    }

    public static String to中文语言(String yu){
        yu=yu.replace("\"","");
        String allarr[]=yu.split(" ");
        String 中文语言="";
        for (int i = 0; i <allarr.length ; i++) {
            中文语言+=单个翻译语言(allarr[i])+" ";
        }
        return 中文语言;
    }


    public static String 语言(String yu,String defaultyu){
        yu= StringUtil.删除字符(yu,"，","、","/","\\");
        yu=yu.replace(defaultyu,"");
        defaultyu=defaultyu.replace("语","");
        String[]  strs=yu.split("语");
//        System.out.println(yu);
        yu="";
        yu+=单个翻译语言(defaultyu)+" ";
        for (int i=0;i<strs.length;i++){
            if (i==strs.length-1){
                yu+=单个翻译语言(strs[i]);
                break;
            }
            yu+=单个翻译语言(strs[i])+" ";
//            System.out.println(strs[i]);
        }
//        System.out.println(yu);
        return yu;
    }
}
