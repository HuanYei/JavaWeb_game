package com.liufujun.game.util;

import com.liufujun.game.pdf.util.StringUtil;

public class EditorstyleUtil {
    public static int row=0;
    public static String Editorstyle(String text){
        String textarray[]=text.split("\n");
        for (int i = 0; i <textarray.length ; i++) {
            if (!textarray[i].equals("\n")&&!textarray[i].equals("")) {
                if (textarray[i].charAt(0) == '#') {
                    textarray[i] = "<span style=\"color: #a2a2a2\">" + textarray[i] + "</span>";
                } else {
                    textarray[i] = textarray[i].replace("=", "<span style=\"color: #a21616\">=</span><span style=\"color: #d3af62\">") + "</span>";
                }
            }
        }
        String newtext="";
        row=0;
        for (String a:textarray
             ) {
            newtext+=a+"<br/>";
            row++;
        }
        return newtext;
    }
    public static String DeleteStyle(String text){
       String newtext= StringUtil.删除字符(text,"</span>","<span style=\"color: #a2a2a2\">","<span style=\"color: #a21616\">","<span style=\"color: #d3af62\">")
               .replace("<span style=\"color: #a21616\">=<span style=\"color: #d3af62\">","=")
               .replace("<br>","\n")
               .replace("&lt;","<")
               .replace("<font color=\"#d3af62\">","")
               .replace("</font>","")
               .replace("&gt;",">");
        return newtext;
    }
}
