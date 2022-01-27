package com.liufujun.game.ftp;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * <p>
 * ftp连接管理(使用apache commons-net-1.4.1 lib)
 * </p>
 * <p>Description:从FTP上下载对账单</p>
 * @author 张军,www.zuidaima.com
 * @version 1.0   20120912
 */
public class FtpClientUtil {
    private FTPClient ftpClient = null;
    private String server;
    private int port;
    private String userName;
    private String userPassword;

    public static void main(String[] args) {
        FtpClientUtil f = new FtpClientUtil("172.168.2.84", 21, "liufujun", "123456");
        try {
            if (f.open()) {
//                f.get("/opt/IBM/WebSphere/AppServer/profiles/AppSrv01/qunarlog.txt", "E:/zhangjun.txt");// 远程路径为相对路径

                String name="/研发生产软件-android/";
                name=new String(name.getBytes("UTF-8"),"iso-8859-1");// 转换后的目录名或文件名。
                f.getFileNameList(name);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public FtpClientUtil(String server, int port, String userName,
                         String userPassword) {
        this.server = server;
        this.port = port;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    /**
     * 链接到服务器
     *
     * @return
     * @throws Exception
     */
    public boolean open() {
        if (ftpClient != null && ftpClient.isConnected()) {
            return true;
        }
        try {
            ftpClient = new FTPClient();
            // 连接
            ftpClient.connect(this.server, this.port);
            ftpClient.login(this.userName, this.userPassword);
            // 检测连接是否成功
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                this.close();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }
            System.out.println("open FTP success:" + this.server+";port:"+this.port + ";name:"
                    + this.userName + ";pwd:" + this.userPassword);
            ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE); // 设置上传模式.binally
            // or ascii
            return true;
        } catch (Exception ex) {
            // 关闭
            this.close();
            ex.printStackTrace();
            return false;
        }

    }

    private boolean cd(String dir) throws IOException {
        if (ftpClient.changeWorkingDirectory(dir)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取目录下所有的文件名称
     *
     * @param filePath
     * @return
     * @throws IOException
     */

    private FTPFile[] getFileList(String filePath) throws IOException {
        FTPFile[] list = ftpClient.listFiles();
        for (FTPFile name:
             list) {
            System.out.println(name.getName());
        }
        return list;

    }

    /**
     * 循环将设置工作目录
     */
    public boolean changeDir(String ftpPath) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        try {

            // 将路径中的斜杠统一
            char[] chars = ftpPath.toCharArray();
            StringBuffer sbStr = new StringBuffer(256);
            for (int i = 0; i < chars.length; i++) {

                if ('\\' == chars[i]) {
                    sbStr.append('/');
                } else {
                    sbStr.append(chars[i]);
                }
            }
            ftpPath = sbStr.toString();
            // System.out.println("ftpPath"+ftpPath);

            if (ftpPath.indexOf('/') == -1) {
                // 只有一层目录
                // System.out.println("change"+ftpPath);
                ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(),
                        "iso-8859-1"));
            } else {
                // 多层目录循环创建
                String[] paths = ftpPath.split("/");
                // String pathTemp = "";
                for (int i = 0; i < paths.length; i++) {
                    // System.out.println("change "+paths[i]);
                    ftpClient.changeWorkingDirectory(new String(paths[i]
                            .getBytes(), "iso-8859-1"));
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 循环创建目录，并且创建完目录后，设置工作目录为当前创建的目录下
     */
    public boolean mkDir(String ftpPath) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        try {

            // 将路径中的斜杠统一
            char[] chars = ftpPath.toCharArray();
            StringBuffer sbStr = new StringBuffer(256);
            for (int i = 0; i < chars.length; i++) {

                if ('\\' == chars[i]) {
                    sbStr.append('/');
                } else {
                    sbStr.append(chars[i]);
                }
            }
            ftpPath = sbStr.toString();
            System.out.println("ftpPath" + ftpPath);

            if (ftpPath.indexOf('/') == -1) {
                // 只有一层目录

                ftpClient.makeDirectory(new String(ftpPath.getBytes(),
                        "iso-8859-1"));
                ftpClient.changeWorkingDirectory(new String(ftpPath.getBytes(),
                        "iso-8859-1"));
            } else {
                // 多层目录循环创建
                String[] paths = ftpPath.split("/");
                // String pathTemp = "";
                for (int i = 0; i < paths.length; i++) {

                    ftpClient.makeDirectory(new String(paths[i].getBytes(),
                            "iso-8859-1"));
                    ftpClient.changeWorkingDirectory(new String(paths[i]
                            .getBytes(), "iso-8859-1"));
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 上传文件到FTP服务器
     *
     *localDirectoryAndFileName
     *            本地文件目录和文件名
     * @param ftpFileName
     *            上传后的文件名
     * @param ftpDirectory
     *            FTP目录如:/path1/pathb2/,如果目录不存在回自动创建目录
     * @throws Exception
     */
    public boolean put(String localDirectoryAndFileName, String ftpFileName,
                       String ftpDirectory) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        boolean flag = false;
        if (ftpClient != null) {
            File srcFile = new File(localDirectoryAndFileName);
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(srcFile);

                // 创建目录

                this.mkDir(ftpDirectory);

                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");

                // 设置文件类型（二进制）
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                // 上传
                flag = ftpClient.storeFile(new String(ftpFileName.getBytes(),
                        "iso-8859-1"), fis);
            } catch (Exception e) {
                this.close();
                e.printStackTrace();
                return false;
            } finally {
                IOUtils.closeQuietly(fis);
            }
        }

        System.out.println("success put file " + localDirectoryAndFileName
                + " to " + ftpDirectory + ftpFileName);
        return flag;
    }

    /**
     * 从FTP服务器上下载文件并返回下载文件长度
     *
     * @param ftpDirectoryAndFileName
     * @param localDirectoryAndFileName
     * @return
     * @throws Exception
     */
    public long get(String ftpDirectoryAndFileName,String localDirectoryAndFileName) {

        long result = 0;
        if (!ftpClient.isConnected()) {
            return 0;
        }
        ftpClient.enterLocalPassiveMode();
        try {
            // 将路径中的斜杠统一
            char[] chars = ftpDirectoryAndFileName.toCharArray();
            StringBuffer sbStr = new StringBuffer(256);
            for (int i = 0; i < chars.length; i++) {

                if ('\\' == chars[i]) {
                    sbStr.append('/');
                } else {
                    sbStr.append(chars[i]);
                }
            }
            ftpDirectoryAndFileName = sbStr.toString();
            String filePath = ftpDirectoryAndFileName.substring(0,ftpDirectoryAndFileName.lastIndexOf("/"));
            String fileName = ftpDirectoryAndFileName.substring(ftpDirectoryAndFileName.lastIndexOf("/") + 1);
            this.changeDir(filePath);
            ftpClient.retrieveFile( new String(fileName.getBytes(),"iso-8859-1"),
                    new FileOutputStream(localDirectoryAndFileName)); // download
            System.out.print(ftpClient.getReplyString()); // check result

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Success get file" + ftpDirectoryAndFileName + " from " + localDirectoryAndFileName);
        return result;
    }

    /**
     * 返回FTP目录下的文件列表
     *
     * @param ftpDirectory
     * @return
     */
    public List getFileNameList(String ftpDirectory) {
        List list = new ArrayList();
         if (!open())
         return list;
         try {
             FTPFile[] list2 = ftpClient.listFiles(ftpDirectory);
             for (FTPFile name:
                     list2) {
                 String a=name.getName();
                 if (name.isDirectory()&&a.length()>2){
                     getFileNameList(ftpDirectory+a+"/");
                 }
                 a=new String(a.getBytes("iso-8859-1"),"UTF-8");
                 System.out.println(a);
             }
         } catch (Exception e) {
         e.printStackTrace();
         }
        return list;
    }

    /**
     * 删除FTP上的文件
     *
     * @param ftpDirAndFileName
     */
    public boolean deleteFile(String ftpDirAndFileName) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        //Todo
        return true;
    }

    /**
     * 删除FTP目录
     *
     * @param ftpDirectory
     */
    public boolean deleteDirectory(String ftpDirectory) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        //ToDo
        return true;
    }

    /**
     * 关闭链接
     */
    public void close() {
        try {
            if (ftpClient != null && ftpClient.isConnected())
                ftpClient.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Close Server Success :"+this.server+";port:"+this.port);
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {

        this.ftpClient = ftpClient;
    }
}
