package com.liufujun.game.me.dao;

import com.liufujun.game.linux.ConnectLinux;
import com.liufujun.game.me.pojo.PQ;
import com.liufujun.game.pdf.Main;
import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.pdf.util.StringUtil;
import com.liufujun.game.util.inter.ICallback;
import com.liufujun.game.util.服务器使用路径;

import java.io.File;

public class RtkpqDao {

//    public static void main(String[] args) {
//        PQ pq = new PQ();
//        String def = "Z:/2851/2851_all/customer/pq/vip_default_osd.cpp";
//        String path = "Z:/2851/2851_all/customer/customers/C070/CHILE_P50_2842V62_HV320WHB_N7K_Bluetooth/pq_RTK2842P/VIP_Panel_DK2842_C070_HV320WHB-N7X_20210318_Osd.cpp";
//        String path2 = "Z:/2851/2851_all/customer/customers/X117/AUSTRALIA_ST4251D01_4_P150_2851V60_DVBT/pq/VIP_Panel_DK2851_LS-ST4251D01-4_20200824_Osd.cpp";
//
//        pq = PQDate(path2, pq);
//        pq.setATVHue("555");
//        pq.setDTVBrightness("666");
//        pq.setHDMIHue("8888");
//        pq.setAVBrightness("44");
//        PQ_OSDUpdate(path2, pq);
//    }

    public static PQ displayDate(String path, PQ pq) {
        String content = Fileprocessing.readTxtFile(path);

        String ATV = Main.v(content, "m_AtvColorFacMode =  {", "};");
        String ATVarr[] = ATV.split("\n");
        for (int i = 0; i < ATVarr.length; i++) {
            if (ATVarr[i].indexOf("BRIGHTNESS_50") != -1) {
                pq.setATVBrightness(StringUtil.截取到第一次出现(ATVarr[i], ","));
            } else if (ATVarr[i].indexOf("CONTRAST_50") != -1) {
                pq.setATVContrast(StringUtil.截取到第一次出现(ATVarr[i], ","));
            } else if (ATVarr[i].indexOf("SATURATION_50") != -1) {
                pq.setATVSaturation(StringUtil.截取到第一次出现(ATVarr[i], ","));
            } else if (ATVarr[i].indexOf("HUE_50") != -1) {
                pq.setATVHue(StringUtil.截取到第一次出现(ATVarr[i], ","));
            } else if (ATVarr[i].indexOf("SHARPNESS_50") != -1) {
                pq.setATVSharpness(StringUtil.截取到第一次出现(ATVarr[i], ","));
            }
        }

        String DTV = Main.v(content, "m_DtvColorFacMode =  {", "};");
        String DTVarr[] = DTV.split("\n");
        for (int i = 0; i < DTVarr.length; i++) {
            if (DTVarr[i].indexOf("BRIGHTNESS_50") != -1) {
                pq.setDTVBrightness(StringUtil.截取到第一次出现(DTVarr[i], ","));
            } else if (DTVarr[i].indexOf("CONTRAST_50") != -1) {
                pq.setDTVContrast(StringUtil.截取到第一次出现(DTVarr[i], ","));
            } else if (DTVarr[i].indexOf("SATURATION_50") != -1) {
                pq.setDTVSaturation(StringUtil.截取到第一次出现(DTVarr[i], ","));
            } else if (DTVarr[i].indexOf("HUE_50") != -1) {
                pq.setDTVHue(StringUtil.截取到第一次出现(DTVarr[i], ","));
            } else if (DTVarr[i].indexOf("SHARPNESS_50") != -1) {
                pq.setDTVSharpness(StringUtil.截取到第一次出现(DTVarr[i], ","));
            }
        }


        String AV = Main.v(content, "m_AvColorFacMode =  {", "};");
        String AVarr[] = AV.split("\n");
        for (int i = 0; i < AVarr.length; i++) {
            if (AVarr[i].indexOf("BRIGHTNESS_50") != -1) {
                pq.setAVBrightness(StringUtil.截取到第一次出现(AVarr[i], ","));
            } else if (AVarr[i].indexOf("CONTRAST_50") != -1) {
                pq.setAVContrast(StringUtil.截取到第一次出现(AVarr[i], ","));
            } else if (AVarr[i].indexOf("SATURATION_50") != -1) {
                pq.setAVSaturation(StringUtil.截取到第一次出现(AVarr[i], ","));
            } else if (AVarr[i].indexOf("HUE_50") != -1) {
                pq.setAVHue(StringUtil.截取到第一次出现(AVarr[i], ","));
            } else if (AVarr[i].indexOf("SHARPNESS_50") != -1) {
                pq.setAVSharpness(StringUtil.截取到第一次出现(AVarr[i], ","));
            }
        }


        String YPBPR = Main.v(content, "m_YppColorFacMode =  {", "};");
        String YPBPRarr[] = YPBPR.split("\n");
        for (int i = 0; i < YPBPRarr.length; i++) {
            if (YPBPRarr[i].indexOf("BRIGHTNESS_50") != -1) {
                pq.setYPBPRBrightness(StringUtil.截取到第一次出现(YPBPRarr[i], ","));
            } else if (YPBPRarr[i].indexOf("CONTRAST_50") != -1) {
                pq.setYPBPRContrast(StringUtil.截取到第一次出现(YPBPRarr[i], ","));
            } else if (YPBPRarr[i].indexOf("SATURATION_50") != -1) {
                pq.setYPBPRSaturation(StringUtil.截取到第一次出现(YPBPRarr[i], ","));
            } else if (YPBPRarr[i].indexOf("HUE_50") != -1) {
                pq.setYPBPRHue(StringUtil.截取到第一次出现(YPBPRarr[i], ","));
            } else if (YPBPRarr[i].indexOf("SHARPNESS_50") != -1) {
                pq.setYPBPRSharpness(StringUtil.截取到第一次出现(YPBPRarr[i], ","));
            }
        }


        String HDMI = Main.v(content, "m_HdmiColorFacMode =  {", "};");
        String HDMIarr[] = HDMI.split("\n");
        for (int i = 0; i < HDMIarr.length; i++) {
            if (HDMIarr[i].indexOf("BRIGHTNESS_50") != -1) {
                pq.setHDMIBrightness(StringUtil.截取到第一次出现(HDMIarr[i], ","));
            } else if (HDMIarr[i].indexOf("CONTRAST_50") != -1) {
                pq.setHDMIContrast(StringUtil.截取到第一次出现(HDMIarr[i], ","));
            } else if (HDMIarr[i].indexOf("SATURATION_50") != -1) {
                pq.setHDMISaturation(StringUtil.截取到第一次出现(HDMIarr[i], ","));
            } else if (HDMIarr[i].indexOf("HUE_50") != -1) {
                pq.setHDMIHue(StringUtil.截取到第一次出现(HDMIarr[i], ","));
            } else if (HDMIarr[i].indexOf("SHARPNESS_50") != -1) {
                pq.setHDMISharpness(StringUtil.截取到第一次出现(HDMIarr[i], ","));
            }
        }


        String VGA = Main.v(content, "m_VgaColorFacMode =  {", "};");
        String VGAarr[] = VGA.split("\n");
        for (int i = 0; i < VGAarr.length; i++) {
            if (VGAarr[i].indexOf("BRIGHTNESS_50") != -1) {
                pq.setVGABrightness(StringUtil.截取到第一次出现(VGAarr[i], ","));
            } else if (VGAarr[i].indexOf("CONTRAST_50") != -1) {
                pq.setVGAContrast(StringUtil.截取到第一次出现(VGAarr[i], ","));
            } else if (VGAarr[i].indexOf("SATURATION_50") != -1) {
                pq.setVGASaturation(StringUtil.截取到第一次出现(VGAarr[i], ","));
            } else if (VGAarr[i].indexOf("HUE_50") != -1) {
                pq.setVGAHue(StringUtil.截取到第一次出现(VGAarr[i], ","));
            } else if (VGAarr[i].indexOf("SHARPNESS_50") != -1) {
                pq.setVGASharpness(StringUtil.截取到第一次出现(VGAarr[i], ","));
            }
        }


        String USB = Main.v(content, "m_UsbColorFacMode =  {", "};");
        String USBarr[] = USB.split("\n");
        for (int i = 0; i < USBarr.length; i++) {
            if (USBarr[i].indexOf("BRIGHTNESS_50") != -1) {
                pq.setUSBBrightness(StringUtil.截取到第一次出现(USBarr[i], ","));
            } else if (USBarr[i].indexOf("CONTRAST_50") != -1) {
                pq.setUSBContrast(StringUtil.截取到第一次出现(USBarr[i], ","));
            } else if (USBarr[i].indexOf("SATURATION_50") != -1) {
                pq.setUSBSaturation(StringUtil.截取到第一次出现(USBarr[i], ","));
            } else if (USBarr[i].indexOf("HUE_50") != -1) {
                pq.setUSBHue(StringUtil.截取到第一次出现(USBarr[i], ","));
            } else if (USBarr[i].indexOf("SHARPNESS_50") != -1) {
                pq.setUSBSharpness(StringUtil.截取到第一次出现(USBarr[i], ","));
            }
        }


//        System.out.println(pq.toString());

        return pq;
    }

    public static PQ PQDate(String path, PQ pq) {
        pq = displayDate(path, pq);
        String content = Fileprocessing.readTxtFile(path);
        String pqdate[] = content.split("\n");
        for (int i = 0; i < pqdate.length; i++) {
            if (pqdate[i].indexOf("/*USER*/") != -1) {
                pq.setUserR(Color赋值(pqdate[i], 0));
                pq.setUserG(Color赋值(pqdate[i], 1));
                pq.setUserB(Color赋值(pqdate[i], 2));
                pq.setUserROFF(Color赋值(pqdate[i], 3));
                pq.setUserGOFF(Color赋值(pqdate[i], 4));
                pq.setUserBOFF(Color赋值(pqdate[i], 5));
            } else if (pqdate[i].indexOf("/*NORMAL(7500K)*/") != -1) {
                pq.setStandardR(Color赋值(pqdate[i], 0));
                pq.setStandardG(Color赋值(pqdate[i], 1));
                pq.setStandardB(Color赋值(pqdate[i], 2));
                pq.setStandardROFF(Color赋值(pqdate[i], 3));
                pq.setStandardGOFF(Color赋值(pqdate[i], 4));
                pq.setStandardBOFF(Color赋值(pqdate[i], 5));
            } else if (pqdate[i].indexOf("/*WARMER (5500K)*/") != -1) {
                pq.setWarmR(Color赋值(pqdate[i], 0));
                pq.setWarmG(Color赋值(pqdate[i], 1));
                pq.setWarmB(Color赋值(pqdate[i], 2));
                pq.setWarmROFF(Color赋值(pqdate[i], 3));
                pq.setWarmGOFF(Color赋值(pqdate[i], 4));
                pq.setWarmBOFF(Color赋值(pqdate[i], 5));
            } else if (pqdate[i].indexOf("/*WARM (6500K)*/") != -1) {
            } else if (pqdate[i].indexOf("/*COOL (8500K)*/") != -1) {
                pq.setCoolR(Color赋值(pqdate[i], 0));
                pq.setCoolG(Color赋值(pqdate[i], 1));
                pq.setCoolB(Color赋值(pqdate[i], 2));
                pq.setCoolROFF(Color赋值(pqdate[i], 3));
                pq.setCoolGOFF(Color赋值(pqdate[i], 4));
                pq.setCoolBOFF(Color赋值(pqdate[i], 5));
            } else if (pqdate[i].indexOf("/*COOLER (9500K)*/") != -1) {
            }
        }
//        System.out.println(pq.toString());
        return pq;
    }

    public static String Color赋值(String date, int i) {
        String datearr[] = date.split(",");
        return StringUtil.删除字符(datearr[i], "\t", " ", "{");
    }

    public static void PQ_OSDUpdate(String path, PQ pq) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String content = Fileprocessing.readTxtFile(path);
                content = UPNO(content, pq);
                String pqdate[] = content.split("\n");
                String Colorend;
                if (path.indexOf("PQ_OverScan")==-1){
                    Colorend="RELATE_TO_GAMMA_MODE";
                }else {
                    Colorend="0";
                }

                for (int i = 0; i < pqdate.length; i++) {
                    if (pqdate[i].indexOf("/*USER*/") != -1) {
                        pqdate[i] = "\t{\t" + pq.getUserR() + ",\t" + pq.getUserG() + ",\t" + pq.getUserB() + ",\t"+pq.getUserROFF() + ",\t" + pq.getUserGOFF() + ",\t" + pq.getUserBOFF()+", MAGIC_CT_ST_VAL, GAMMA_CURVE_"+Colorend+",},/*USER*/";
                    } else if (pqdate[i].indexOf("/*NORMAL(7500K)*/") != -1) {
                        pqdate[i] = "\t{\t" + pq.getStandardR() + ",\t" + pq.getStandardG() + ",\t" + pq.getStandardB() + ",\t"+pq.getStandardROFF() + ",\t" + pq.getStandardGOFF() + ",\t" + pq.getStandardBOFF()+", MAGIC_CT_ST_VAL, GAMMA_CURVE_"+Colorend+",},/*NORMAL(7500K)*/";
                    } else if (pqdate[i].indexOf("/*WARMER (5500K)*/") != -1) {
                        pqdate[i] = "\t{\t" + pq.getWarmR() + ",\t" + pq.getWarmG() + ",\t" + pq.getWarmB() + ",\t"+pq.getWarmROFF() + ",\t" + pq.getWarmGOFF() + ",\t" + pq.getWarmBOFF()+", MAGIC_CT_ST_VAL, GAMMA_CURVE_"+Colorend+",},/*WARMER (5500K)*/";
                    } else if (pqdate[i].indexOf("/*WARM (6500K)*/") != -1) {
                        pqdate[i] = "\t{\t" + pq.getWarmR() + ",\t" + pq.getWarmG() + ",\t" + pq.getWarmB() + ",\t"+pq.getWarmROFF() + ",\t" + pq.getWarmGOFF() + ",\t" + pq.getWarmBOFF()+", MAGIC_CT_ST_VAL, GAMMA_CURVE_"+Colorend+",},/*WARM (6500K)*/";
                    } else if (pqdate[i].indexOf("/*COOL (8500K)*/") != -1) {
                        pqdate[i] = "\t{\t" + pq.getCoolR() + ",\t" + pq.getCoolG() + ",\t" + pq.getCoolB() + ",\t"+pq.getCoolROFF() + ",\t" + pq.getCoolGOFF() + ",\t" + pq.getCoolBOFF()+", MAGIC_CT_ST_VAL, GAMMA_CURVE_"+Colorend+",},/*COOL (8500K)*/";
                    } else if (pqdate[i].indexOf("/*COOLER (9500K)*/") != -1) {
                        pqdate[i] = "\t{\t" + pq.getCoolR() + ",\t" + pq.getCoolG() + ",\t" + pq.getCoolB() + ",\t"+pq.getCoolROFF() + ",\t" + pq.getCoolGOFF() + ",\t" + pq.getCoolBOFF()+", MAGIC_CT_ST_VAL, GAMMA_CURVE_"+Colorend+",},/*COOLER (9500K)*/";
                    }
                }
                content = "";
                for (int i = 0; i < pqdate.length; i++) {
                    content += pqdate[i] + "\n";
                }
                Fileprocessing.updateFile(path, content);
                if (服务器使用路径.rtk2851_pq_Linux.equals(""))return;
                Fileprocessing.newFile(path, 服务器使用路径.rtk2851_pq_Windows+StringUtil.提取文件名(path));
                ConnectLinux.execComm("cd "+服务器使用路径.rtk2851_pq_Linux+"\n"+"./genPanelFactoryOSD.pl"+"\n");
                String old=StringUtil.提取文件名(path).replace("VIP_Panel","vip").replace("Osd","osd").replace("cpp","bin");
                old=服务器使用路径.rtk2851_pq_Windows+"PanelParam/"+old;
                if (new File(old).exists()){
                    String newbin=StringUtil.提取文件路径(path)+"vip_default_osd.bin";
                    Fileprocessing.newFile(old,newbin);
                }
                Fileprocessing.deleteDir(服务器使用路径.rtk2851_pq_Windows+"PanelParam/");
                new File(服务器使用路径.rtk2851_pq_Windows+StringUtil.提取文件名(path)).delete();
            }
        }).start();
    }


    private static String UPNO(String content, PQ pq) {
        String arr[] = content.split("\n");
        boolean DTV = false;
        boolean ATV = false;
        boolean AV = false;
        boolean YPBPR = false;
        boolean HDMI = false;
        boolean VGA = false;
        boolean USB = false;
        boolean DEF = false, SDHDMI = false;
        for (int i = 0; i < arr.length; i++) {

            if (arr[i].indexOf("m_AtvColorFacMode") != -1) {
                ATV = true;
            }
            if (ATV) {
                if (arr[i].indexOf("BRIGHTNESS_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getATVBrightness());
                } else if (arr[i].indexOf("CONTRAST_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getATVContrast());
                } else if (arr[i].indexOf("SATURATION_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getATVSaturation());
                } else if (arr[i].indexOf("HUE_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getATVHue());
                } else if (arr[i].indexOf("SHARPNESS_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getATVSharpness());
                } else if (arr[i].indexOf("};") != -1) {
                    ATV = false;
                }
            }


            if (arr[i].indexOf("m_DtvColorFacMode") != -1) {
                DTV = true;
            }
            if (DTV) {
                if (arr[i].indexOf("BRIGHTNESS_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getDTVBrightness());
                } else if (arr[i].indexOf("CONTRAST_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getDTVContrast());
                } else if (arr[i].indexOf("SATURATION_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getDTVSaturation());
                } else if (arr[i].indexOf("HUE_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getDTVHue());
                } else if (arr[i].indexOf("SHARPNESS_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getDTVSharpness());
                } else if (arr[i].indexOf("};") != -1) {
                    DTV = false;
                }
            }

            if (arr[i].indexOf("m_AvColorFacMode") != -1) {

                AV = true;
            }

            if (AV) {
                if (arr[i].indexOf("BRIGHTNESS_50") != -1) {

                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getAVBrightness());
                } else if (arr[i].indexOf("CONTRAST_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getAVContrast());
                } else if (arr[i].indexOf("SATURATION_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getAVSaturation());
                } else if (arr[i].indexOf("HUE_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getAVHue());
                } else if (arr[i].indexOf("SHARPNESS_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getAVSharpness());
                } else if (arr[i].indexOf("};") != -1) {
                    AV = false;
                }
            }


            if (arr[i].indexOf("m_YppColorFacMode") != -1) {

                YPBPR = true;
            }

            if (YPBPR) {
                if (arr[i].indexOf("BRIGHTNESS_50") != -1) {

                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getYPBPRBrightness());
                } else if (arr[i].indexOf("CONTRAST_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getYPBPRContrast());
                } else if (arr[i].indexOf("SATURATION_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getYPBPRSaturation());
                } else if (arr[i].indexOf("HUE_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getYPBPRHue());
                } else if (arr[i].indexOf("SHARPNESS_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getYPBPRSharpness());
                } else if (arr[i].indexOf("};") != -1) {
                    YPBPR = false;
                }
            }

            if (arr[i].indexOf("m_VgaColorFacMode") != -1) {

                VGA = true;
            }
            if (VGA) {
                if (arr[i].indexOf("BRIGHTNESS_50") != -1) {

                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getVGABrightness());
                } else if (arr[i].indexOf("CONTRAST_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getVGAContrast());
                } else if (arr[i].indexOf("SATURATION_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getVGASaturation());
                } else if (arr[i].indexOf("HUE_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getVGAHue());
                } else if (arr[i].indexOf("SHARPNESS_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getVGASharpness());
                } else if (arr[i].indexOf("};") != -1) {
                    VGA = false;
                }
            }


            if (arr[i].indexOf("m_UsbColorFacMode") != -1) {

                USB = true;
            }

            if (USB) {
                if (arr[i].indexOf("BRIGHTNESS_50") != -1) {

                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getUSBBrightness());
                } else if (arr[i].indexOf("CONTRAST_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getUSBContrast());
                } else if (arr[i].indexOf("SATURATION_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getUSBSaturation());
                } else if (arr[i].indexOf("HUE_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getUSBHue());
                } else if (arr[i].indexOf("SHARPNESS_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getUSBSharpness());
                } else if (arr[i].indexOf("};") != -1) {
                    USB = false;
                }
            }

            if (arr[i].indexOf("m_HdmiColorFacMode") != -1) {

                HDMI = true;
            }

            if (HDMI) {
                if (arr[i].indexOf("BRIGHTNESS_50") != -1) {

                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getHDMIBrightness());
                } else if (arr[i].indexOf("CONTRAST_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getHDMIContrast());
                } else if (arr[i].indexOf("SATURATION_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getHDMISaturation());
                } else if (arr[i].indexOf("HUE_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getHDMIHue());
                } else if (arr[i].indexOf("SHARPNESS_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getHDMISharpness());
                } else if (arr[i].indexOf("};") != -1) {
                    HDMI = false;
                }
            }

            if (arr[i].indexOf("m_HdmiSDColorFacMode") != -1) {

                SDHDMI = true;
            }

            if (SDHDMI) {
                if (arr[i].indexOf("BRIGHTNESS_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getHDMIBrightness());
                } else if (arr[i].indexOf("CONTRAST_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getHDMIContrast());
                } else if (arr[i].indexOf("SATURATION_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getHDMISaturation());
                } else if (arr[i].indexOf("HUE_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getHDMIHue());
                } else if (arr[i].indexOf("SHARPNESS_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getHDMISharpness());
                } else if (arr[i].indexOf("};") != -1) {
                    SDHDMI = false;
                }
            }

            if (arr[i].indexOf("m_defaultColorFacMode") != -1) {

                DEF = true;
            }

            if (DEF) {
                if (arr[i].indexOf("BRIGHTNESS_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getHDMIBrightness());
                } else if (arr[i].indexOf("CONTRAST_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getHDMIContrast());
                } else if (arr[i].indexOf("SATURATION_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getHDMISaturation());
                } else if (arr[i].indexOf("HUE_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getHDMIHue());
                } else if (arr[i].indexOf("SHARPNESS_50") != -1) {
                    arr[i] = arr[i].replace(StringUtil.截取到第一次出现(arr[i], ","), pq.getHDMISharpness());
                } else if (arr[i].indexOf("};") != -1) {
                    DEF = false;
                }
            }

            if (arr[i].indexOf("#else")!=-1){
                break;
            }
        }
        content = "";
        for (int i = 0; i < arr.length; i++) {
            content += arr[i] + "\n";
        }

        return content;
    }
}
