package com.liufujun.game.me.RTK.remote;

import java.util.ArrayList;
import java.util.Scanner;

public class RemoteUtil {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入客户码：");
        String usercode=scanner.next();
        usercode=usercode.substring(2,4)+usercode.substring(0,2);
        System.out.println("客户码是:"+usercode);
        System.out.println("请输入协议/格式（数字）");
        int agree=scanner.nextInt();
        System.out.println("请输入遥控器按键值和遥控器功能值，请用空格隔开。");
        System.out.println("输入 0 结束");
        boolean pd=true;
        ArrayList<Ykq> list=new ArrayList<Ykq>();

        for (int i = 0; i <1000; i++) {

            String stringne=scanner.next();
            if (stringne.equals("0")) {
                break;
            }
            String stringC=scanner.next();
            if (stringne.equals("0")||stringC.equals("0")) {
                break;
            }
            int Z10=Integer.parseInt(stringne,16);
            String F=Integer.toHexString(255-Z10);
            if (F.length()==1) {
                F="0"+F;
            }
            Ykq ykq=new Ykq();
            ykq.Mzhi="0x"+F+stringne+usercode;
            pd=false;
            ykq.Jzhi=stringC;
            list.add(ykq);

        }
        for (int i = 0; i <list.size(); i++) {
            System.out.println(agree+","+list.get(i).Mzhi+","+list.get(i).Jzhi);
        }
    }
    static class Ykq{
        String Mzhi;
        String Jzhi;
    }
}
