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
        }else if (swname.indexOf("6681")!=-1){
            return"6681";
        } else if (swname.indexOf("2842")!=-1){
            return"2842";
        } else if (swname.indexOf("2853")!=-1){
            return"2853";
        } else if (swname.indexOf("2843")!=-1){
            return"2843";
        }else {
            return "未识别到方案";
        }
    }

    public static String SW脚本路径(String swname){

        if (swname.indexOf("368")!=-1){
            return 服务器使用路径.脚本路径368+swname+".sh";
        }else if (swname.indexOf("9255")!=-1){
            return 服务器使用路径.脚本路径368+swname+".sh";
        }else if (swname.indexOf("2851")!=-1){
            return 服务器使用路径.脚本路径2851+swname+".sh";
        }else if (swname.indexOf("9632")!=-1){
            return 服务器使用路径.脚本路径9632+swname+".sh";
        }else if (swname.indexOf("6681")!=-1){
            return 服务器使用路径.脚本路径6681+swname+".sh";
        }else if (swname.indexOf("2842")!=-1){
            return 服务器使用路径.脚本路径2842+swname+".sh";
        } else if (swname.indexOf("2853")!=-1){
            return 服务器使用路径.脚本路径2853+swname+".sh";
        } else if (swname.indexOf("2843")!=-1){
            return 服务器使用路径.脚本路径2843+swname+".sh";
        }  else {
            return "未识别到方案";
        }
    }

}
