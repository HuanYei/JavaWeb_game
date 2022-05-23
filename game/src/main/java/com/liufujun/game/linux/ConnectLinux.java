//package com.liufujun.game.linux;
//
//import ch.ethz.ssh2.Connection;
//import ch.ethz.ssh2.Session;
//import ch.ethz.ssh2.StreamGobbler;
//import com.liufujun.game.util.服务器使用路径;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//public class ConnectLinux {
//
//    private static int port = 22;
//    public static void main(String[] args) {
//
//    }
//    static  Connection conn;
//    static InputStream stdout;
//    static BufferedReader br;
//    public static String endString;
//    public static void Connect(){
//        if (服务器使用路径.user.equals(""))return;
//        System.out.println("ip:"+服务器使用路径.ip);
//        System.out.println("user:"+服务器使用路径.user);
//        System.out.println("password:"+服务器使用路径.password);
//        try {
//            //先创建一个连接，传入IP地址和端口
//            conn = new Connection(服务器使用路径.ip, port);
//            conn.connect();
//            //然后传入用户名密码
//            boolean b = conn.authenticateWithPassword(服务器使用路径.user, 服务器使用路径.password);
//            if (b == false) {
//                throw new IOException("连接失败！");
//            }
//        }catch (Exception e){
//
//        }
//    }
//    public static void close(){
//        //关闭会话和连接
//
//        conn.close();
//    }
//
//    public static void execComm(String cmd) {
//        try {
//            //需要连接 首先先创建一个通话
//            Session session = conn.openSession();
//            //然后输入需要执行的命令 这里的命令是去触发我们写的脚本的
//            session.execCommand(cmd);
//            //然后将返回的结果转化为输入流对象
//             stdout = new StreamGobbler(session.getStdout());
//            //然后将流对象读取出来
//             br = new BufferedReader(new InputStreamReader(stdout));
//
//            while (true){
//                String line  = br.readLine();
//                if(line == null){
//                    break;
//                }
//                //打印到控制台
//                System.out.println(line);
//                endString=line;
//            }
//            session.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
