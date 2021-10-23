package com.liufujun.game.pdf.util;

import java.awt.*;
import java.io.*;

public class Fileprocessing {
    public static String readTxtFile(String filePath) {
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
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void openFile(String path) {
        path = path.replace("/", "\\");
        System.out.println(path);

        try {
            Runtime.getRuntime().exec("explorer /select," + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void newFile(String srcPathStr, String desPathStr) {
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
            fis.close();//释放资源
        } catch (Exception e) {
            e.printStackTrace();
        }
        String a="k";

    }

    public static void copy(String path, String copyPath) {
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
                read = new DataInputStream(
                        new BufferedInputStream(new FileInputStream(path)));
                write = new DataOutputStream(
                        new BufferedOutputStream(new FileOutputStream(copyPath)));
                byte[] buf = new byte[1024 * 512];
                while (read.read(buf) != -1) {
                    write.write(buf);
                }
                read.close();
                write.close();
            } else {
                System.out.println("请输入正确的文件名或路径名");
            }
        } catch (Exception e) {
            System.out.println("请输入正确的文件名或路径名");
        }
    }

}
