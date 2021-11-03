package com.liufujun.game.me.dao;

import com.liufujun.game.linux.ConnectLinux;
import com.liufujun.game.me.pojo.PQ;
import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.pdf.util.StringUtil;
import com.liufujun.game.util.服务器使用路径;

public class RtkpqDao {

//    public static void main(String[] args) {
//        PQ pq=new PQ();
//        String path="Z:/2851/2851_all/customer/customers/C070/CHILE_P50_2842V62_HV320WHB_N7K_Bluetooth/pq_RTK2842P/VIP_Panel_DK2842_C070_HV320WHB-N7X_20210318_Osd.cpp";
//        String path2="Z:/2851/2851_all/customer/customers/X117/AUSTRALIA_ST4251D01_4_P150_2851V60_DVBT/pq/VIP_Panel_DK2851_LS-ST4251D01-4_20200824_Osd.cpp";
//        PQDate(path2,pq);
//    }

    public static PQ PQDate(String path,PQ pq) {
        String content = Fileprocessing.readTxtFile(path);
        String pqdate[] = content.split("\n");
        for (int i = 0; i <pqdate.length ; i++) {
            if (pqdate[i].indexOf("/*USER*/")!=-1){
                pq.setUserR(Color赋值(pqdate[i],0));
                pq.setUserG(Color赋值(pqdate[i],1));
                pq.setUserB(Color赋值(pqdate[i],2));
            }else if (pqdate[i].indexOf("/*NORMAL(7500K)*/")!=-1){
                pq.setStandardR(Color赋值(pqdate[i],0));
                pq.setStandardG(Color赋值(pqdate[i],1));
                pq.setStandardB(Color赋值(pqdate[i],2));
            }else if (pqdate[i].indexOf("/*WARMER (5500K)*/")!=-1){
                pq.setWarmR(Color赋值(pqdate[i],0));
                pq.setWarmG(Color赋值(pqdate[i],1));
                pq.setWarmB(Color赋值(pqdate[i],2));
            }else if (pqdate[i].indexOf("/*WARM (6500K)*/")!=-1){
            }else if (pqdate[i].indexOf("/*COOL (8500K)*/")!=-1){
                pq.setCoolR(Color赋值(pqdate[i],0));
                pq.setCoolG(Color赋值(pqdate[i],1));
                pq.setCoolB(Color赋值(pqdate[i],2));
            }else if (pqdate[i].indexOf("/*COOLER (9500K)*/")!=-1){
            }
        }
        System.out.println(pq.toString());
        return pq;
    }

    private static String Color赋值(String date,int i){
        String datearr[]=date.split(",");
        return StringUtil.删除字符(datearr[i],"\t"," ","{");
    }

    public static void PQ_OSDUpdate(String path,PQ pq){
        String content= Fileprocessing.readTxtFile(path);
        String pqdate[]=content.split("\n");

        for (int i = 0; i <pqdate.length ; i++) {
            if (pqdate[i].indexOf("/*USER*/")!=-1){
                pqdate[i]="\t{\t"+pq.getUserR()+",\t"+pq.getUserG()+",\t"+pq.getUserB()+",\t512,\t512,\t512, MAGIC_CT_ST_VAL, GAMMA_CURVE_RELATE_TO_GAMMA_MODE,},/*USER*/";
            }else if (pqdate[i].indexOf("/*NORMAL(7500K)*/")!=-1){
                pqdate[i]="\t{\t"+pq.getStandardR()+",\t"+pq.getStandardG()+",\t"+pq.getStandardB()+",\t512,\t512,\t512, MAGIC_CT_ST_VAL, GAMMA_CURVE_RELATE_TO_GAMMA_MODE,},/*NORMAL(7500K)*/";
            }else if (pqdate[i].indexOf("/*WARMER (5500K)*/")!=-1){
                pqdate[i]="\t{\t"+pq.getWarmR()+",\t"+pq.getWarmG()+",\t"+pq.getWarmB()+",\t512,\t512,\t512, MAGIC_CT_ST_VAL, GAMMA_CURVE_RELATE_TO_GAMMA_MODE,},/*WARMER (5500K)*/";
            }else if (pqdate[i].indexOf("/*WARM (6500K)*/")!=-1){
                pqdate[i]="\t{\t"+pq.getWarmR()+",\t"+pq.getWarmG()+",\t"+pq.getWarmB()+",\t512,\t512,\t512, MAGIC_CT_ST_VAL, GAMMA_CURVE_RELATE_TO_GAMMA_MODE,},/*WARM (6500K)*/";
            }else if (pqdate[i].indexOf("/*COOL (8500K)*/")!=-1){
                pqdate[i]="\t{\t"+pq.getCoolR()+",\t"+pq.getCoolG()+",\t"+pq.getCoolB()+",\t512,\t512,\t512, MAGIC_CT_ST_VAL, GAMMA_CURVE_RELATE_TO_GAMMA_MODE,},/*COOL (8500K)*/";
            }else if (pqdate[i].indexOf("/*COOLER (9500K)*/")!=-1){
                pqdate[i]="\t{\t"+pq.getCoolR()+",\t"+pq.getCoolG()+",\t"+pq.getCoolB()+",\t512,\t512,\t512, MAGIC_CT_ST_VAL, GAMMA_CURVE_RELATE_TO_GAMMA_MODE,},/*COOLER (9500K)*/";
            }
        }
        content="";
        for (int i = 0; i <pqdate.length ; i++) {
            content+=pqdate[i]+"\n";
        }

        Fileprocessing.updateFile(path,content);
        Fileprocessing.newFile(path, 服务器使用路径.rtk2851_pq_Windows+StringUtil.提取文件名(path));
        ConnectLinux.execComm("cd "+服务器使用路径.rtk2851_pq_Linux+"\n"+"./genPanelFactoryOSD.pl"+"\n");
        String old=StringUtil.提取文件名(path).replace("VIP_Panel","vip").replace("Osd","osd").replace("cpp","bin");
        old=服务器使用路径.rtk2851_pq_Windows+"PanelParam/"+old;
        String newbin=StringUtil.提取文件路径(path)+"vip_default_osd.bin";
        Fileprocessing.newFile(old,newbin);
    }
}
