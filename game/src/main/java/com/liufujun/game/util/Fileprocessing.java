package com.liufujun.game.util;

import com.liufujun.game.me.dao.SwDao;
import com.liufujun.game.me.pojo.SW;
import org.springframework.ui.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Fileprocessing {
    public static String readTxtFile(String filePath) {
        if (!filePath.equals("res/config/语言.config")) System.out.println("读取文件路径："+filePath);

        String map = "";
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    map += lineTxt + "\n";
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return map;
    }


    public static boolean updateFile(String path, String content) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(path, false);//为false
            byte[] bytes = content.getBytes("UTF-8");  // 将字符串按指定编码集编码--》将信息转成二进制数
            fos.write(bytes);  // 这样写入的数据，会将文件中的原数据覆盖
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void openFile(String path) {
        boolean isopenpath=true;
        boolean isopenpathrow=false;
        int row=1;
        if (path.indexOf("96322851368")!=-1){
            path=path.replace("96322851368","");
            isopenpath=false;
        }else if(path.indexOf("36896322851")!=-1){
            String aa[]=path.split("36896322851");
            row=e查找字符串所在行(aa[0],aa[1]);
            path=aa[0];
            isopenpathrow=true;
        }
        path = path.replace("/", "\\");
        System.out.println(path+"ssssssssssssssssssssssssssssss"+row);

        try {
            if (isopenpath&&!isopenpathrow) Runtime.getRuntime().exec(new String[]{"cmd","/C","explorer  /select,\""+ path+"\""});
            else if (isopenpathrow) Runtime.getRuntime().exec("\""+服务器使用路径.codeEditor +"\" -n"+row+" \""+ path+"\"");
            else Runtime.getRuntime().exec("\""+服务器使用路径.codeEditor +"\"  \""+ path+"\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static int e查找字符串所在行(String path,String Str) {
        System.out.println("path = [" + path + "], Str = [" + Str + "]");
        String center[]=readTxtFile(path).toLowerCase().split("\n");
        for (int i=0;i<center.length;i++) {
            if (center[i].indexOf(Str.toLowerCase())!=-1){
                return i+1;
            }
        }
        return -1;
    }

    public static void ReNameFile(String oldPath, String newPath) {
        // 旧的文件或目录
        File oldName = new File(oldPath);
        // 新的文件或目录
        File newName = new File(newPath);
        if (newName.exists()) {  //  确保新的文件名不存在
            System.out.printf("已存在新软件名");
            return;
        }

        if (oldName.renameTo(newName)) {
            System.out.println("已重命名");
        } else {
            System.out.println("Error");
        }
    }

    public static boolean isFile(String path){
        File file=new File(path);
        return file.exists();
    }

    public static void newFile(String srcPathStr, String desPathStr) {
        System.out.println("srcPathStr = [" + srcPathStr + "], desPathStr = [" + desPathStr + "]");
        try {
            FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
            FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
            byte datas[] = new byte[1024 * 8];//创建搬运工具
            int len = 0;//创建长度
            while ((len = fis.read(datas)) != -1)//循环读取数据
            {
                fos.write(datas, 0, len);
            }
            fis.close();//释放资源
            fos.close();//释放资源
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<String> lookupwai(String path,String 关键字){
        fileList=new ArrayList<String>();
        lookup(path,关键字);
        if (fileList.size()==0){
            fileList.add("无");
        }
        return fileList;
    }
    private static ArrayList fileList;
    private static void lookup(String path,String 关键字){

        try {
            File filePath = new File(path);
            if (filePath.isDirectory()) {
                File[] list = filePath.listFiles();
                for (int i = 0; i < list.length; i++) {
                    String newPath = path  + list[i].getName();
                    lookup(newPath, 关键字);
                }
            } else if (filePath.isFile()) {
                if (StringUtil.提取文件名(path).indexOf(关键字)!=-1){
                    fileList.add(path);
                }
            }
        } catch (Exception e) {
            System.out.println("请输入正确的文件名或路径名");
        }
    }
    //复制文件
    public static void copy(String path, String copyPath) {
        System.out.println("path = [" + path + "], copyPath = [" + copyPath + "]");
        try {
            File filePath = new File(path);
            DataInputStream read;
            DataOutputStream write;
            if (filePath.isDirectory()) {
                File[] list = filePath.listFiles();
                for (int i = 0; i < list.length; i++) {
                    String newPath = path + File.separator + list[i].getName();
                    String newCopyPath = copyPath + File.separator + list[i].getName();
                    File newFile = new File(copyPath);
                    if (!newFile.exists()) {
                        newFile.mkdir();
                    }
                    copy(newPath, newCopyPath);
                }
            } else if (filePath.isFile()) {
                newFile(path,copyPath);
            } else {
                System.out.println("请输入正确的文件名或路径名");
            }
        } catch (Exception e) {
            System.out.println("请输入正确的文件名或路径名");
        }
    }

    public static byte[] fileToByte(File img) throws Exception {
        byte[] bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "png", baos);
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baos.close();
        }
        return bytes;
    }

    //简单更新文件宏值
    public static boolean updateJBFile(String 脚本路径,String 宏,String 值){

       String con[]=  readTxtFile(脚本路径).split("\n");
       boolean kon=true;
        for (int i = 0; i <con.length ; i++) {
            if (con[i].indexOf(宏)!=-1){
                con[i]=宏+值;
                kon=false;
                break;
            }
        }
        StringBuffer str5 = new StringBuffer();
        for (String s : con) {
            str5.append(s+"\n");
        }
//        if (kon)str5.append(宏+值+"\n");
        updateFile(脚本路径,str5.toString());
        return kon;
    }

    //简单更新文件宏值不存在则创建
    public static void updateORnewJBFile(String 脚本路径,String 宏,String 值){
        if (updateJBFile(脚本路径,宏,值)){
            updateFile(脚本路径,readTxtFile(脚本路径)+"\n"+宏+值);
        }
    }

    public static void updateJBboolean(String 脚本路径,String 宏){
        if (findJBboolean(脚本路径,宏)){
            updateORnewJBFile(脚本路径,宏,"false");
        }else {
            updateORnewJBFile(脚本路径,宏,"true");
        }
    }
    //读取值为
    public static boolean findJBboolean(String 脚本路径,String 宏){
        System.out.println("脚本路径 = [" + 脚本路径 + "], 宏 = [" + 宏 + "]");
        boolean jg=true;
        String findjg=findJBFile(脚本路径,宏);
        if (findjg.equals("0")||findjg.equals("false")||findjg.equals("未识别到这个宏")){
            jg=false;
        }
        System.out.println(jg);
        return jg;
    }

    //简单读取文件宏值
    public static String findJBFile(String 脚本路径,String 宏){
        String Stringshu[]=  readTxtFile(脚本路径).split("\n");
        for (String a:Stringshu){
            if (a.indexOf(宏)==0){
                return a.replace(宏,"").replace("=","").replace("export ","").trim();
            }
        }
        return "未识别到这个宏";
    }

    //使用对比工具对比脚本和客制化文件夹
    public static void ComparedJBOrDir(String path1,String path2){
        ComparedFile(path1,path2);

        SW sw1=new SW(),sw2=new SW();
        sw1.set软件名称(StringUtil.提取文件名(path1));
        sw1.set方案(PlanUtil.PlanType(StringUtil.提取文件名(path1)));
        SwDao.读取软件所有属性(path1, sw1, null);
        String cusPath1=sw1.get软件客制化路径全称();
        System.out.println(cusPath1+"   SSSSSS  ");

        sw2.set软件名称(StringUtil.提取文件名(path2));
        sw2.set方案(PlanUtil.PlanType(StringUtil.提取文件名(path2)));
        SwDao.读取软件所有属性(path2,sw2,null);
        String cusPath2=sw2.get软件客制化路径全称();

        System.out.println(cusPath1+"   SSSSSS  "+cusPath2);
        ComparedFile(cusPath1,cusPath2);
    }
    //使用对比工具文件
    public static void ComparedFile(String path1,String path2){
        System.out.println("path1 = [" + path1 + "], path2 = [" + path2 + "]");
        try {
            Runtime.getRuntime().exec("\""+服务器使用路径.comparison_tool +"\"    \""+path1+"\"    \""+ path2+"\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDir(String path){
        File file=new File(path);
        //判断是否为文件夹
        if(file.isDirectory()){
            //获取该文件夹下的子文件夹
            File[] files = file.listFiles();
            //循环子文件夹重复调用delete方法
            for (int i = 0; i < files.length; i++) {
                deleteDir(files[i].getPath());
            }
        }
        //若为空文件夹或者文件删除，File类的删除方法
        file.delete();
    }

    public static void deletefile(String path){
        File file=new File(path);
        if (file.exists())file.delete();
    }
    public static void deletefiles(String[] paths){
        for (String path:
             paths) {
            System.out.println("已删除:"+path);
            deletefile(path);
        }
    }
    public static boolean isFile存在(String path){
        File file=new File(path);
        if (file.exists()){
            return true;
        }
        return false;
    }

    public static String[] e获取目录下所有文件名(String path,boolean 是否要后缀){
        File file总目录=new File(path);
        if(file总目录.isDirectory()){
            //获取该文件夹下的子文件夹
            File[] files = file总目录.listFiles();
            String[] filename=new String[files.length];
            //循环子文件夹重复调用delete方法
            for (int i = 0; i < files.length; i++) {
                if (是否要后缀) filename[i]=files[i].getName();
                else filename[i]=StringUtil.e去除文件后缀(files[i].getName());
            }
            return filename;
        }else {
            return null;
        }
    }
}
