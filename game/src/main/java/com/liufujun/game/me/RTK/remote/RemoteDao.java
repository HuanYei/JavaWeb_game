package com.liufujun.game.me.RTK.remote;

//import com.liufujun.game.linux.ConnectLinux;
import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.util.inter.ICallback;
import com.liufujun.game.util.服务器使用路径;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class RemoteDao {
//
//    public static void UPConfig(String con, MultipartFile file,String name){
//        try {
//            File file1=new File("res/IR/");
//            if (!file1.exists()){
//                file1.mkdir();
//            }
//            Files.write(Paths.get("res/IR/" + name+ ".config"), file.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String oldString= Fileprocessing.readTxtFile("res/IR/" + name+ ".config");
//        if (oldString.length()<10){
//            oldString=Fileprocessing.readTxtFile(服务器使用路径.RTK2851PATH+"customer/IR/AT073.config");
//        }
//        oldString=oldString+"\n"+con;
//        Fileprocessing.updateFile("res/IR/" + name+ ".config",oldString);
//    }
//
//    public static String C(String remotename) {
//        doStm((pppp)->{
//            System.out.println(pppp);
//        },remotename);
//        return "执行将在稍后完成，请检查。";
//    }
//    public static void doStm(final ICallback callback,String remotename) {
//        String filePath=服务器使用路径.RTK2851PATH+"customer/IR/"+remotename+".config";
//        String CFilePath=服务器使用路径.RTK2851PATH+"customer/IR/"+remotename+".c";
//        String packagePath=服务器使用路径.RTK2851PATH+"image_file_creator/components/packages/package7/customer/tv001/ir_table.config";
//
//        String outpath=服务器使用路径.RTK2851PATH+"kernel/linux/linux-4.14/drivers/rtk_kdriver/ir/ir_table/ir_table_default.c";
//        String jgg="";
//        if (服务器使用路径.rtk2851_Linux.equals(""))return;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Fileprocessing.newFile("res/IR/" + remotename+ ".config",packagePath);
//                String mi1="cd "+服务器使用路径.rtk2851_Linux+"/image_file_creator",
//                        mi2="make ir_table_bin PACKAGES=package7 CUSTOMER_ID=tv001",
//                        mi3="make ir_def_table PACKAGES=package7 CUSTOMER_ID=tv001";
//                ConnectLinux.execComm(mi1+"\n"+mi2);
//                for (int i = 0; i < 3; i++) {
//                    ConnectLinux.execComm(mi1+"\n"+mi3);
//                }
//                String jg="";
//                if (ConnectLinux.endString.indexOf("kernel/linux/linux-4.14/drivers/rtk_kdriver/ir/ir_table/ir_table_default.c")==-1){
//                    jg= "生成失败，请检查输入值是否合法";
//                }else {
//                    Fileprocessing.newFile("res/IR/" + remotename+ ".config", filePath);
//                    Fileprocessing.newFile(outpath,CFilePath);
//                    jg= "成功";
//                }
//                callback.callback(jg);
//            }
//        }).start();
//
//        }
    }
