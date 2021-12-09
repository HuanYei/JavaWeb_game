package com.liufujun.game.me.conntroller;

import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.util.服务器使用路径;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class SHconntroller {
    private ArrayList<String> JBAlllist=new ArrayList<>();
    @RequestMapping(value = "/chaJB", method = RequestMethod.POST)
    @ResponseBody
    public String jiakey (@RequestBody(required=false) String jiaoben) {
        if (jiaoben==null||jiaoben.equals("")){
            return "";
        }
        System.out.println(jiaoben);
        jiaoben=jiaoben.trim();
        String e关键字arr[] =jiaoben.split(" ");
        System.out.println(JBAlllist.size());
        return 查找(e关键字arr);
    }


    @RequestMapping(value = "/JBall", method = RequestMethod.POST)
    @ResponseBody
    public void JBall ( ) {
        System.out.println("SSSS");
        JBAlllist.clear();
        if (!服务器使用路径.MTK368PATH.equals("null")) {
            File file368=new File(服务器使用路径.脚本路径368);
            JBAlllist.addAll(Arrays.asList(提取file名称(file368)));
        }
        if (!服务器使用路径.RTK2851PATH.equals("null")) {
            File file2851=new File(服务器使用路径.脚本路径2851);
            File file2842=new File(服务器使用路径.脚本路径2842);
            JBAlllist.addAll(Arrays.asList(提取file名称(file2842)));
            JBAlllist.addAll(Arrays.asList(提取file名称(file2851)));
        }
        if (!服务器使用路径.MTK9632PATH.equals("null")) {
            File file9632=new File(服务器使用路径.脚本路径9632);
            File file6681=new File(服务器使用路径.脚本路径6681);
            JBAlllist.addAll(Arrays.asList(提取file名称(file6681)));
            JBAlllist.addAll(Arrays.asList(提取file名称(file9632)));
        }
    }

    public String[] 提取file名称(File path) {
        File file[]=path.listFiles();
        String s[]=new String[file.length];
        for (int i = 0; i <s.length ; i++) {
            s[i]=file[i].getName();
        }
        return s;
    }

    private String 查找(String[] e关键字arr) {
        ArrayList<String> list=new ArrayList<String>();

        for (int i = 0; i <JBAlllist.size() ; i++) {
            boolean 判断符合=true;
            for (int j = 0; j <e关键字arr.length ; j++) {
                if (JBAlllist.get(i).indexOf(e关键字arr[j])==-1){
                    判断符合=false;
                }
            }
            if (判断符合){
                list.add(JBAlllist.get(i));
            }
        }
        System.out.println("查询结果"+list.size());
        String e结果="";
        for (String a:list) {
            e结果+=a+"|";
        }
        return e结果;
    }
}
