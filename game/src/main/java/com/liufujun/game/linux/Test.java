package com.liufujun.game.linux;

import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.util.PlanUtil;
import com.liufujun.game.util.服务器使用路径;

import java.io.File;
import java.util.Date;
import java.util.List;

public class Test {
//    public static void main(String[] args) {
//// long lon=1638435569895l;
////        System.out.println("employee.xml文件的最后修改时间 = "+new Date(lon));
//
////        ConnectLinux.Connect();
////        ConnectLinux.execComm("cd "+服务器使用路径.MTK368_Linux+"\n"
////        +"git branch");
////        String sh="source /media/workspace0/liufujun/9255/MTK368P/vendor/toptech/build.sh   --boot \n";
////        System.out.println(sh);
////        ConnectLinux.execComm(sh);
////        List<String> list= Fileprocessing.lookupwai("C:\\Users\\Administrator\\Desktop\\peers_TV\\lib\\armeabi-v7a\\","so");
////        for (int i = 0; i <list.size() ; i++) {
////            System.out.println("    "+list.get(i).replace("C:\\Users\\Administrator\\Desktop\\peers_TV\\","").replace("\\","/")+" \\");
////        }
//    }


//    public static void main(String[] args) {
//        String path="C:\\Users\\Administrator\\Desktop\\SO220225005 HDCP1.4\\";
//        String cu="HDCP_KEY_00";
//        int aa=0;
//        boolean is=true;
//        for (int i = 233199; i <= 238173; i++) {
//            File file=new File(path+cu+i+".bin");
//            System.out.println(i);
//            if (!file.exists()){
//                System.out.println(i+"他不存在");
//            }
//        }
//        if (is){
//            System.out.println("全都存在");
//        }
//
//    }
    public static void 编译脚本(String 脚本){
        switch (PlanUtil.PlanType(脚本)){
            case "368":
                break;
            case "2851":
                break;
            case "2842":
                break;
            case "9632":
                break;
            case "6681":
                break;
        }

    }
}
