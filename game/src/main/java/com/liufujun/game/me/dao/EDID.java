package com.liufujun.game.me.dao;

import com.liufujun.game.util.Fileprocessing;
import com.liufujun.game.util.MyUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class EDID {

    private int year,week,maxhsize,maxvsiez,hsize1,vsize1,hfbl1,vfbl1,hblan,vblan;
    private double maxsize,size,PixelClock,fps;
    private String fbl="",manufacturer="",Equipment_name="";

    private int hsize2,vsize2,hfbl2,vfbl2,hblan2,vblan2;
    private double size2,PixelClock2,fps2;

    public EDID(){
    }
    public EDID(String path){
        byte bytes[]= Fileprocessing.readFileByBytes(path);
        year=1990+MyUtil.byteToInteger(bytes[17]);
        week=MyUtil.byteToInteger(bytes[16]);
        maxhsize=MyUtil.byteToInteger(bytes[21]);
        maxvsiez=MyUtil.byteToInteger(bytes[22]);
        maxsize=Math.sqrt((maxhsize*maxhsize)+(maxvsiez*maxvsiez))/2.54;
        BigDecimal bgmax = new BigDecimal(maxsize);
        maxsize=bgmax.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

        hsize1=e计算h(bytes[66],bytes[68],0);
        vsize1=e计算h(bytes[67],bytes[68],1);
        hsize2=e计算h(bytes[84],bytes[86],0);
        vsize2=e计算h(bytes[85],bytes[86],1);

        hfbl1=e计算h(bytes[56],bytes[58],0);
        vfbl1=e计算h(bytes[59],bytes[61],0);
        hfbl2=e计算h(bytes[74],bytes[76],0);
        vfbl2=e计算h(bytes[77],bytes[79],0);

        hblan=e计算h(bytes[57],bytes[58],1);
        vblan=e计算h(bytes[60],bytes[61],1);
        hblan2=e计算h(bytes[75],bytes[76],1);
        vblan2=e计算h(bytes[78],bytes[79],1);


        size=Math.sqrt((hsize1*hsize1)+(vsize1*vsize1))/2.54/10;
        BigDecimal bg = new BigDecimal(size);
        size=bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

        size2=Math.sqrt((hsize2*hsize2)+(vsize2*vsize2))/2.54/10;
        BigDecimal bgtow = new BigDecimal(size2);
        size2=bgtow.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();


        PixelClock=PixelClock(bytes[54],bytes[55]);
        PixelClock2=PixelClock(bytes[72],bytes[73]);

        fps=PixelClock*10000/(hfbl1+hblan)/(vfbl1+vblan);
        fps2=PixelClock2*10000/(hfbl2+hblan2)/(vfbl2+vblan2);

        PixelClock/=100;
        BigDecimal bg2 = new BigDecimal(PixelClock);
        PixelClock=bg2.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

        PixelClock2/=100;
        BigDecimal bg2tow = new BigDecimal(PixelClock2);
        PixelClock2=bg2tow.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

        BigDecimal bg3 = new BigDecimal(fps);
        fps=bg3.setScale(1, BigDecimal.ROUND_DOWN).doubleValue();

        BigDecimal bg3two = new BigDecimal(fps2);
        fps2=bg3two.setScale(1, BigDecimal.ROUND_DOWN).doubleValue();

        fbl=hfbl1+"x"+vfbl1;

        manufacturer=manufacturer(bytes[8],bytes[9]);

        for (int i = 0; i <13 ; i++) {
            Equipment_name+=MyUtil.StringToAsciiString(bytes[113+i]);
        }
    }

    private String manufacturer(byte aByte, byte aByte1) {
        int a1=MyUtil.byteToInteger(aByte);
        int a2=MyUtil.byteToInteger(aByte1);
        String e1=MyUtil.iNTToByte(a1);
        String e2=MyUtil.iNTToByte(a2);
        System.out.println(e1);
        System.out.println(e2);
        String z1=e1.substring(1,6);
        String z2=e1.substring(6,8);
        z2+=e2.substring(0,3);
        String z3=e2.substring(3,8);
        System.out.println(z1);
        System.out.println(z2);
        System.out.println(z3);

        return MyUtil.erToAsciiString(z1)+MyUtil.erToAsciiString(z2)+MyUtil.erToAsciiString(z3);
    }

    private int PixelClock(byte aByte, byte aByte1) {
//        System.out.println("aByte = [" + aByte + "], aByte1 = [" + aByte1 + "]");
        int lower=MyUtil.byteToInteger(aByte);
        int up=MyUtil.byteToInteger(aByte1);
        String e16lower=Integer.toHexString(lower);
        String e16u=Integer.toHexString(up);
        if (e16lower.length()<2){
            e16lower=0+e16lower;
        }
        if (e16u.length()<2){
            e16u=0+e16u;
        }
        String e16=e16u+e16lower;
        int e10=Integer.parseInt(e16,16);

        return e10;
    }

    private static int e计算h(byte chus,byte gaowei,int i){
        if (chus==0&&gaowei==0)return 0;
        int gaoweiint=MyUtil.byteToInteger(gaowei);
//        System.out.println("chus = [" + chus + "], gaowei = [" + gaoweiint + "], i = [" + i + "]");
        String lower8= MyUtil.byteTo二进制8位(chus);
//        System.out.println(lower8);
        String e16=Integer.toHexString(gaoweiint);
        String b=e16.charAt(i)+"";
        String up4=MyUtil.hexStringToByte(b);
//        System.out.println(up4);
//        System.out.println(e16);
        return  Integer.parseInt(up4+lower8,2);
    }


    public double getFps() {
        return fps;
    }

    public void setFps(double fps) {
        this.fps = fps;
    }

    public int getYear() {
        return year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getEquipment_name() {
        return Equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        Equipment_name = equipment_name;
    }

    @Override
    public String toString() {
        return "EDID{" +
                "year=" + year +
                ", week=" + week +
                ", maxhsize=" + maxhsize +
                ", maxvsiez=" + maxvsiez +
                ", hsize1=" + hsize1 +
                ", vsize1=" + vsize1 +
                ", hfbl1=" + hfbl1 +
                ", vfbl1=" + vfbl1 +
                ", hblan=" + hblan +
                ", vblan=" + vblan +
                ", maxsize=" + maxsize +
                ", size=" + size +
                ", PixelClock=" + PixelClock +
                ", fps=" + fps +
                ", fbl='" + fbl + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", Equipment_name='" + Equipment_name + '\'' +
                ", hsize2=" + hsize2 +
                ", vsize2=" + vsize2 +
                ", hfbl2=" + hfbl2 +
                ", vfbl2=" + vfbl2 +
                ", hblan2=" + hblan2 +
                ", vblan2=" + vblan2 +
                ", size2=" + size2 +
                ", PixelClock2=" + PixelClock2 +
                ", fps2=" + fps2 +
                '}';
    }

    public String getFbl() {
        return fbl;
    }

    public void setFbl(String fbl) {
        this.fbl = fbl;
    }

    public int getHsize1() {
        return hsize1;
    }

    public void setHsize1(int hsize1) {
        this.hsize1 = hsize1;
    }

    public int getVsize1() {
        return vsize1;
    }

    public void setVsize1(int vsize1) {
        this.vsize1 = vsize1;
    }

    public int getHsize2() {
        return hsize2;
    }

    public void setHsize2(int hsize2) {
        this.hsize2 = hsize2;
    }

    public int getVsize2() {
        return vsize2;
    }

    public void setVsize2(int vsize2) {
        this.vsize2 = vsize2;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public double getMaxsize() {
        return maxsize;
    }

    public void setMaxsize(double maxsize) {
        this.maxsize = maxsize;
    }

    public int getMaxhsize() {
        return maxhsize;
    }

    public void setMaxhsize(int maxhsize) {
        this.maxhsize = maxhsize;
    }

    public int getMaxvsiez() {
        return maxvsiez;
    }

    public void setMaxvsiez(int maxvsiez) {
        this.maxvsiez = maxvsiez;
    }

    public int getHfbl1() {
        return hfbl1;
    }

    public void setHfbl1(int hfbl1) {
        this.hfbl1 = hfbl1;
    }

    public int getVfbl1() {
        return vfbl1;
    }

    public void setVfbl1(int vfbl1) {
        this.vfbl1 = vfbl1;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPixelClock() {
        return PixelClock;
    }

    public void setPixelClock(double pixelClock) {
        PixelClock = pixelClock;
    }

    public int getHblan() {
        return hblan;
    }

    public void setHblan(int hblan) {
        this.hblan = hblan;
    }

    public int getVblan() {
        return vblan;
    }

    public void setVblan(int vblan) {
        this.vblan = vblan;
    }

    public int getHfbl2() {
        return hfbl2;
    }

    public void setHfbl2(int hfbl2) {
        this.hfbl2 = hfbl2;
    }

    public int getVfbl2() {
        return vfbl2;
    }

    public void setVfbl2(int vfbl2) {
        this.vfbl2 = vfbl2;
    }

    public int getHblan2() {
        return hblan2;
    }

    public void setHblan2(int hblan2) {
        this.hblan2 = hblan2;
    }

    public int getVblan2() {
        return vblan2;
    }

    public void setVblan2(int vblan2) {
        this.vblan2 = vblan2;
    }

    public double getSize2() {
        return size2;
    }

    public void setSize2(double size2) {
        this.size2 = size2;
    }

    public double getPixelClock2() {
        return PixelClock2;
    }

    public void setPixelClock2(double pixelClock2) {
        PixelClock2 = pixelClock2;
    }

    public double getFps2() {
        return fps2;
    }

    public void setFps2(double fps2) {
        this.fps2 = fps2;
    }


    public String toString2() {
        return "EDID{" +
                "year=" + year +
                ", week=" + week +
                ", maxhsize=" + maxhsize +
                ", maxvsiez=" + maxvsiez +
                ", hsize1=" + hsize1 +
                ", vsize1=" + vsize1 +
                ", hfbl1=" + hfbl1 +
                ", vfbl1=" + vfbl1 +
                ", hblan=" + hblan +
                ", vblan=" + vblan +
                ", maxsize=" + maxsize +
                ", size=" + size +
                '}';
    }
}
