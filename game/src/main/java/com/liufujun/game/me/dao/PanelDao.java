package com.liufujun.game.me.dao;

import com.liufujun.game.me.pojo.SW;
import com.liufujun.game.me.pojo.SwEnglish;
import com.liufujun.game.util.Fileprocessing;
import com.liufujun.game.util.StringUtil;

public class PanelDao {

    public static SW PanelMTK赋值(SW sw) {
        String PanelString= Fileprocessing.readTxtFile(sw.get软件屏参名路径全称());
        String[] e屏数组=PanelString.split("\n");
        for (int i = 0; i <e屏数组.length ; i++) {
            if (e屏数组[i].indexOf("m_pPanelName")!=-1){
                String Name=StringUtil.replaceBlank(e屏数组[i]);
                Name=Name.replace("m_pPanelName=","").replace(";","");
                sw.getPanel().setM_PNAME(Name);
            }else if (e屏数组[i].indexOf("m_wPanelWidth")!=-1){
                String SWAP=StringUtil.replaceBlank(e屏数组[i]);
                SWAP=SWAP.replace("m_wPanelWidth=","").replace(";","");
                sw.getPanel().setM_wPanelWidth(SWAP);
            }else if (e屏数组[i].indexOf("m_wPanelHeight")!=-1){
                String SWAP= StringUtil.replaceBlank(e屏数组[i]);
                SWAP=SWAP.replace("m_wPanelHeight=","").replace(";","");
                sw.getPanel().setM_wPanelHeight(SWAP);
            }
            if (sw.get方案().equals("9632")){
                if (e屏数组[i].indexOf("m_bPanelDualPort")!=-1){
                    String Dual=StringUtil.replaceBlank(e屏数组[i]);
                    Dual=Dual.replace("m_bPanelDualPort=","").substring(0,1);
                    int a=Integer.parseInt(Dual)+1;
                    sw.getPanel().setDual_MODE(a+"");
                }
            }else {
                if (e屏数组[i].indexOf("m_bPanelLVDS_TI_MODE")!=-1){
                    String Ti= StringUtil.replaceBlank(e屏数组[i]);
                    Ti=Ti.replace("m_bPanelLVDS_TI_MODE=","").substring(0,1);
                    sw.getPanel().setTi_MODE(Ti);
                }else if (e屏数组[i].indexOf("m_bPanelSwapPort")!=-1){
                    String SWAP=StringUtil.replaceBlank(e屏数组[i]);
                    SWAP=SWAP.replace("m_bPanelSwapPort=","").substring(0,1);
                    sw.getPanel().setSwap_MODE(SWAP);
                }
            }

            sw.getPanel().setMirror_MODE(SwDao.e脚本宏查值("mirror_on="));

        }
        return sw;
    }

    public static SW PanelRTK赋值(SW sw) {
        String PanelString= Fileprocessing.readTxtFile(sw.get软件屏参名路径全称());
        String[] e屏数组=PanelString.split("\n");
        for (int i = 0; i <e屏数组.length ; i++) {
             if (e屏数组[i].indexOf(".sPanelName")!=-1){
                String Name=StringUtil.replaceBlank(e屏数组[i]);
                Name=Name.replace(".sPanelName=","").replace(";","").replace("\"","");
                sw.getPanel().setM_PNAME(Name);
            }else if (e屏数组[i].indexOf(".iVFLIP")!=-1){
                String iVFLIP=StringUtil.replaceBlank(e屏数组[i]);
                iVFLIP=iVFLIP.replace(".iVFLIP=","").substring(0,1);
                sw.getPanel().setMirror_MODE(iVFLIP);
            }else if (e屏数组[i].indexOf(".iCONFIG_DISP_ACT_END_HPOS")!=-1){
                 String SWAP=StringUtil.replaceBlank(e屏数组[i]);
                 SWAP=SWAP.replace(".iCONFIG_DISP_ACT_END_HPOS=","").replace(",","");
                 sw.getPanel().setM_wPanelWidth(SWAP);
             }else if (e屏数组[i].indexOf(".iCONFIG_DISP_ACT_END_VPOS")!=-1){
                 String SWAP=StringUtil.replaceBlank(e屏数组[i]);
                 SWAP=SWAP.replace(".iCONFIG_DISP_ACT_END_VPOS=","").replace(",","");
                 sw.getPanel().setM_wPanelHeight(SWAP);
             }
             //2853
            if (sw.get方案().equals("2853")||sw.get方案().equals("2843")){
                if (e屏数组[i].indexOf("DISP_PANEL_NAME")!=-1){
                    String Name=StringUtil.replaceBlank(e屏数组[i]);
                    Name=Name.replace("DISP_PANEL_NAME","").replace(" ","").replace("=","");
                    sw.getPanel().setM_PNAME(Name);
                }else if (e屏数组[i].indexOf("VFLIP")!=-1){
                    String iVFLIP=StringUtil.replaceBlank(e屏数组[i]);
                    iVFLIP=iVFLIP.replace("VFLIP","").replace(" ","").replace("=","");
                    sw.getPanel().setMirror_MODE(iVFLIP);
                }else if (e屏数组[i].indexOf("DISP_ACT_END_HPOS")!=-1){
                    String SWAP=StringUtil.replaceBlank(e屏数组[i]);
                    SWAP=SWAP.replace("DISP_ACT_END_HPOS=","").replace(" ","").replace("=","");
                    sw.getPanel().setM_wPanelWidth(SWAP);
                }else if (e屏数组[i].indexOf("DISP_ACT_END_VPOS")!=-1){
                    String SWAP=StringUtil.replaceBlank(e屏数组[i]);
                    SWAP=SWAP.replace("DISP_ACT_END_VPOS","").replace(" ","").replace("=","");
                    sw.getPanel().setM_wPanelHeight(SWAP);
                }
                if (sw.get方案().equals("2843")){
                    if (e屏数组[i].indexOf("DISPLAY_COLOR_BITS")!=-1){
                        String Bits=StringUtil.replaceBlank(e屏数组[i]);
                        Bits=Bits.replace("DISPLAY_COLOR_BITS","").replace(" ","").replace("=","");
                        sw.getPanel().setBit_MODE(Bits);
                    }else if (e屏数组[i].indexOf("DISPLAY_BITMAPPING_TABLE")!=-1){
                        String TI=StringUtil.replaceBlank(e屏数组[i]);
                        TI=TI.replace("DISPLAY_BITMAPPING_TABLE","").replace(" ","").replace("=","");
                        sw.getPanel().setTi_MODE(TI);
                    }else if (e屏数组[i].indexOf("DISPLAY_PORTAB_SWAP")!=-1){
                        String Swap=StringUtil.replaceBlank(e屏数组[i]);
                        Swap=Swap.replace("DISPLAY_PORTAB_SWAP=","").replace(" ","").replace("=","");
                        sw.getPanel().setSwap_MODE(Swap);
                    }
                }else {
                    if (e屏数组[i].indexOf("SFG_SEG_NUM")!=-1){
                        String NUM= StringUtil.replaceBlank(e屏数组[i]);
                        NUM=NUM.replace("SFG_SEG_NUM","").replace(" ","").replace("=","");
                        sw.getPanel().setDual_MODE(NUM);
                    }
                }
            }


            if (sw.get方案().equals("2842")){
                if (e屏数组[i].indexOf(".iCONFIG_DISPLAY_COLOR_BITS")!=-1){
                    String Bits=StringUtil.replaceBlank(e屏数组[i]);
                    Bits=Bits.replace(".iCONFIG_DISPLAY_COLOR_BITS=","").substring(0,1);
                    sw.getPanel().setBit_MODE(Bits);
                }else if (e屏数组[i].indexOf(".iCONFIG_DISPLAY_BITMAPPING_TABLE")!=-1){
                    String TI=StringUtil.replaceBlank(e屏数组[i]);
                    TI=TI.replace(".iCONFIG_DISPLAY_BITMAPPING_TABLE=","").substring(0,1);
                    sw.getPanel().setTi_MODE(TI);
                }else if (e屏数组[i].indexOf(".iCONFIG_DISPLAY_PORTAB_SWAP")!=-1){
                    String Swap=StringUtil.replaceBlank(e屏数组[i]);
                    Swap=Swap.replace(".iCONFIG_DISPLAY_PORTAB_SWAP=","").substring(0,1);
                    sw.getPanel().setSwap_MODE(Swap);
                }
            }else {
                if (e屏数组[i].indexOf(".iCONFIG_SFG_SEG_NUM")!=-1){
                    String NUM= StringUtil.replaceBlank(e屏数组[i]);
                    NUM=NUM.replace(".iCONFIG_SFG_SEG_NUM=","").substring(0,1);
                    sw.getPanel().setDual_MODE(NUM);
                }
            }
        }
        return sw;
    }

    public static void Panel修改(SwEnglish sw) {
        if (sw.getIsRTK()==1){
            RTK屏修改(sw);
        }else {
            MTK屏修改(sw);
        }

    }

    private static void RTK屏修改(SwEnglish sw) {
        System.out.println(sw.getFull_name_of_software_screen_parameter_name_path()+"SSSSSSSSS");
        String PanelString=Fileprocessing.readTxtFile(sw.getFull_name_of_software_screen_parameter_name_path());
        String[] e屏数组=PanelString.split("\n");
        System.out.println(sw.getPlan()+"SSSSSSSS");
        if (sw.getPlan().equals("2853")||sw.getPlan().equals("2843")){
            System.out.println(2853);
            for (int i = 0; i <e屏数组.length ; i++) {
                if (e屏数组[i].indexOf("SFG_SEG_NUM")!=-1){
                    if (sw.getPanel().getDual_MODE()!=null&&!sw.getPanel().getDual_MODE().equals(""))
                        e屏数组[i]="SFG_SEG_NUM = "+sw.getPanel().getDual_MODE();
                }else if (e屏数组[i].indexOf("DISP_PANEL_NAME")!=-1){
                    if (sw.getPanel().getM_PNAME()!=null&&!sw.getPanel().getM_PNAME().equals(""))
                        e屏数组[i]="DISP_PANEL_NAME = "+sw.getPanel().getM_PNAME();
                }else if (e屏数组[i].indexOf("VFLIP")!=-1){
                    if (sw.getPanel().getMirror_MODE()!=null&&!sw.getPanel().getMirror_MODE().equals(""))
                        e屏数组[i]="VFLIP = "+sw.getPanel().getMirror_MODE();
                }else if (e屏数组[i].indexOf("DISPLAY_COLOR_BITS")!=-1){
                    if (sw.getPanel().getBit_MODE()!=null&&!sw.getPanel().getBit_MODE().equals(""))
                        e屏数组[i]="DISPLAY_COLOR_BITS = "+sw.getPanel().getBit_MODE();
                }else if (e屏数组[i].indexOf("DISPLAY_BITMAPPING_TABLE")!=-1){
                    if (sw.getPanel().getTi_MODE()!=null&&!sw.getPanel().getTi_MODE().equals(""))
                        e屏数组[i]="DISPLAY_BITMAPPING_TABLE = "+sw.getPanel().getTi_MODE();
                }else if (e屏数组[i].indexOf("DISPLAY_PORTAB_SWAP")!=-1){
                    if (sw.getPanel().getSwap_MODE()!=null&&!sw.getPanel().getSwap_MODE().equals(""))
                        e屏数组[i]="DISPLAY_PORTAB_SWAP = "+sw.getPanel().getSwap_MODE();
                }
            }
            PanelString="";
            if (sw.getPlan().equals("2843")){
                for (int i = 0; i < e屏数组.length; i++) {
                    PanelString+=e屏数组[i]+"\n";
                }
            }
            if (sw.getPlan().equals("2853")){
                for (int i = 0; i < e屏数组.length; i++) {
                    PanelString+=e屏数组[i]+"\r\n";
                }
            }
        }else {
        for (int i = 0; i <e屏数组.length ; i++) {
            if (e屏数组[i].indexOf(".iCONFIG_SFG_SEG_NUM")!=-1){
                if (sw.getPanel().getDual_MODE()!=null&&!sw.getPanel().getDual_MODE().equals(""))
                e屏数组[i]=".iCONFIG_SFG_SEG_NUM="+sw.getPanel().getDual_MODE()+",";
            }else if (e屏数组[i].indexOf(".sPanelName")!=-1){
                if (sw.getPanel().getM_PNAME()!=null&&!sw.getPanel().getM_PNAME().equals(""))
                e屏数组[i]=".sPanelName=\""+sw.getPanel().getM_PNAME()+"\"";
            }else if (e屏数组[i].indexOf(".iVFLIP")!=-1){
                if (sw.getPanel().getMirror_MODE()!=null&&!sw.getPanel().getMirror_MODE().equals(""))
                e屏数组[i]=".iVFLIP="+sw.getPanel().getMirror_MODE()+",";
            }else if (e屏数组[i].indexOf(".iCONFIG_DISPLAY_COLOR_BITS")!=-1){
                if (sw.getPanel().getBit_MODE()!=null&&!sw.getPanel().getBit_MODE().equals(""))
                    e屏数组[i]=".iCONFIG_DISPLAY_COLOR_BITS="+sw.getPanel().getBit_MODE()+",";
            }else if (e屏数组[i].indexOf(".iCONFIG_DISPLAY_BITMAPPING_TABLE")!=-1){
                if (sw.getPanel().getTi_MODE()!=null&&!sw.getPanel().getTi_MODE().equals(""))
                    e屏数组[i]=".iCONFIG_DISPLAY_BITMAPPING_TABLE="+sw.getPanel().getTi_MODE()+",";
            }else if (e屏数组[i].indexOf(".iCONFIG_DISPLAY_PORTAB_SWAP")!=-1){
                if (sw.getPanel().getSwap_MODE()!=null&&!sw.getPanel().getSwap_MODE().equals(""))
                    e屏数组[i]=".iCONFIG_DISPLAY_PORTAB_SWAP="+sw.getPanel().getSwap_MODE()+",";
            }
        }
            PanelString="";
            for (int i = 0; i < e屏数组.length; i++) {
                PanelString+=e屏数组[i]+"\r\n";
            }
        }

        Fileprocessing.updateFile(sw.getFull_name_of_software_screen_parameter_name_path(),PanelString);
    }

    private static void MTK屏修改(SwEnglish sw) {
        System.out.println(sw.getFull_name_of_software_screen_parameter_name_path()+"SSSSSSSSS");
        String PanelString=Fileprocessing.readTxtFile(sw.getFull_name_of_software_screen_parameter_name_path());
        String[] e屏数组=PanelString.split("\n");
        for (int i = 0; i <e屏数组.length ; i++) {
            if (e屏数组[i].indexOf("m_bPanelLVDS_TI_MODE")!=-1){
                if (sw.getPanel().getTi_MODE()!=null&&!sw.getPanel().getTi_MODE().equals("")) {
                    String a = e屏数组[i].substring(0, e屏数组[i].indexOf(";"));
                    e屏数组[i] = e屏数组[i].replace(a, a.substring(0, a.length() - 2) + " " + sw.getPanel().getTi_MODE());
                }
            }else if (e屏数组[i].indexOf("m_pPanelName")!=-1){
                if (sw.getPanel().getM_PNAME()!=null&&!sw.getPanel().getM_PNAME().equals("")){
                    String a=e屏数组[i].substring(0,e屏数组[i].indexOf("=")+1);
                    e屏数组[i]=a+"   "+sw.getPanel().getM_PNAME()+";";
                }
            }else if (e屏数组[i].indexOf("m_bPanelSwapPort")!=-1){
                if (sw.getPanel().getSwap_MODE()!=null&&!sw.getPanel().getSwap_MODE().equals("")) {
                    String a = e屏数组[i].substring(0, e屏数组[i].indexOf(";"));
                    e屏数组[i] = e屏数组[i].replace(a, a.substring(0, a.length() - 2) + " " + sw.getPanel().getSwap_MODE());
                }
            }else if (e屏数组[i].indexOf("m_bPanelDualPort")!=-1){
                if (sw.getPanel().getDual_MODE()!=null&&!sw.getPanel().getDual_MODE().equals("")) {
                    int ac=Integer.parseInt(sw.getPanel().getDual_MODE())-1;
                    String a = e屏数组[i].substring(0, e屏数组[i].indexOf(";"));
                    e屏数组[i] = e屏数组[i].replace(a, a.substring(0, a.length() - 2) + " " + ac);
                }
            }
        }
        if (sw.getPanel().getMirror_MODE()!=null&&!sw.getPanel().getMirror_MODE().equals("")) {
            Fileprocessing.updateJBFile(sw.getFull_name_of_software_path(),"mirror_on=",sw.getPanel().getMirror_MODE());
        }
        PanelString="";
        for (int i = 0; i < e屏数组.length; i++) {
            PanelString+=e屏数组[i]+"\n";
        }
        Fileprocessing.updateFile(sw.getFull_name_of_software_screen_parameter_name_path(),PanelString);
    }
}
