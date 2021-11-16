package com.liufujun.game.pdf.util;

public class Country {
    public static String 单个翻译语言(String yu){
        switch (yu){
            case "英":
                return "en_US";
            case "法":
                return "fr_FR";
            case "俄":
                return "ru_RU";
            case "阿拉伯":
                return "ar_EG";
            case "波斯":
                return "fa_IR";
            case "土耳其":
                return "tr_TR";
            case "葡萄牙":
                return "pt_PT";
            case "西班牙":
                return "es_ES";
            case "意大利":
                return "it_IT";
            case "德":
                return "de_DE";
            case "塞尔维亚":
                return "sr_Latn_RS";
            case "希伯来":
                return "iw_IL";
            default:
                return yu;
        }

    }

    public static String 时区(String gu) {
        switch (gu) {
            case "NP":
                return "Asia/Kathmandu";
            case "IQ":
                return "Asia/Baghdad";
            case "EG":
                return "Africa/Cairo";
            case "TR":
                return "Europe/Istanbul";
            case "SA":
                return "Asia/Riyadh";
            case "AE":
                return "Asia/Dubai";
            case "IL":
                return "Asia/Jerusalem";
            default:
                return gu;
        }
    }

    public static String 国家368(String gu){
        switch (gu){
            case "尼泊尔":
                return "NP";
            case "伊拉克":
                return "IQ";
            case "埃及":
                return "EG";
            case "土耳其":
                return "TR";
            case "沙特阿拉伯":
                return "SA";
            case "阿联酋":
                return "AE";
            case "塞尔维亚":
                return "RS";
            case "以色列":
                return "IL";
            default:
                return gu;
        }
    }

    public static String 国家(String gu){
        switch (gu){
            case "NP":
                return "NEPAL";
            case "IQ":
                return "IRAQ";
            case "EG":
                return "EGYPT";
            case "TR":
                return "TURKEY";
            case "SA":
                return "SAUDI_ARABIA";
            case "AE":
                return "ARE";
            case "RS":
                return "SERBIA";
            case "IL":
                return "ISRAEL";
            default:
                return gu;
        }
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

    public static String 语言(String yu,String defaultyu){
        yu=StringUtil.删除字符(yu,"，","、","/","\\");
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
