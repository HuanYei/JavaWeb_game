package com.liufujun.game.me.RTK.remote;

import com.liufujun.game.pdf.Main;

import java.util.ArrayList;
import java.util.Scanner;

public class RemoteUtil {
//    public static void main(String[] args) {
//        toRemoteUtil("20df",1,"0b KEY_POWER\n" +
//                "14 KEY_MUTE\n" +
//                "42 KEY_1");
//    }
//    public static String toRemoteUtil(String user_code,int _agree,String cont) {
//        Scanner scanner=new Scanner(System.in);
//        System.out.println("请输入客户码：");
//        String usercode=user_code;
//        usercode=usercode.substring(2,4)+usercode.substring(0,2);
//        System.out.println("客户码是:"+usercode);
//        System.out.println("请输入协议/格式（数字）");
//        int agree=_agree;
//        cont=cont.trim();//去除头尾空白字符
//        boolean pd=true;
//        String a = "";
//        ArrayList<Ykq> list=new ArrayList<Ykq>();
//        if (cont.indexOf("KEY")!=-1) {
//            String conarr[] = cont.split("\n");
//
//            for (int i = 0; i < conarr.length; i++) {
//                String arr[] = conarr[i].split(" ");
//                String stringne = arr[0];
//                if (stringne.equals("0")) {
//                    break;
//                }
//                String stringC = arr[1];
//                if (stringne.equals("0") || stringC.equals("0")) {
//                    break;
//                }
//                int Z10 = Integer.parseInt(stringne, 16);
//                String F = Integer.toHexString(255 - Z10);
//                if (F.length() == 1) {
//                    F = "0" + F;
//                }
//                Ykq ykq = new Ykq();
//                ykq.Mzhi = "0x" + F + stringne + usercode;
//                pd = false;
//                ykq.Jzhi = stringC;
//                list.add(ykq);
//
//            }
//
//            for (int i = 0; i < list.size(); i++) {
//                a += agree + "," + list.get(i).Mzhi + "," + list.get(i).Jzhi + "\n";
////            System.out.println(agree+","+list.get(i).Mzhi+","+list.get(i).Jzhi);
//            }
//        }
//        return  a;
//    }
//    static class Ykq{
//        String Mzhi;
//        String Jzhi;
//    }
}
