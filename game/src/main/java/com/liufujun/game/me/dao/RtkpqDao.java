package com.liufujun.game.me.dao;

import com.liufujun.game.me.pojo.PQ;
import com.liufujun.game.util.Fileprocessing;
import com.liufujun.game.util.StringUtil;

public class RtkpqDao {

    public static PQ displayDate(String path, PQ pq) {
        String content = Fileprocessing.readTxtFile(path);

        String ATV = StringUtil.v(content, "m_AtvColorFacMode =  {", "};");
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

        String DTV = StringUtil.v(content, "m_DtvColorFacMode =  {", "};");
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


        String AV = StringUtil.v(content, "m_AvColorFacMode =  {", "};");
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


        String YPBPR = StringUtil.v(content, "m_YppColorFacMode =  {", "};");
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


        String HDMI = StringUtil.v(content, "m_HdmiColorFacMode =  {", "};");
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


        String VGA = StringUtil.v(content, "m_VgaColorFacMode =  {", "};");
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


        String USB = StringUtil.v(content, "m_UsbColorFacMode =  {", "};");
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
        return pq;
    }

    public static String Color赋值(String date, int i) {
        String datearr[] = date.split(",");
        return StringUtil.删除字符(datearr[i], "\t", " ", "{");
    }



}
