package com.liufujun.game.util;


import com.liufujun.game.me.pojo.SW;
import com.liufujun.game.me.pojo.SwEnglish;
import com.liufujun.game.pdf.util.Fileprocessing;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Colortemperature {
    public static void Colortupdate(SwEnglish sw) {
        //输入
        System.out.println(sw.getSoftware_color_temperature_file_path());
        String 色温String= Fileprocessing.readTxtFile(sw.getSoftware_color_temperature_file_path());
        String[] e色温数组=色温String.split("\n");
        List<String>  list=new ArrayList<>(Arrays.asList(e色温数组));
        //输入

        boolean is368=false;
        for (int i = 0; i <list.size(); i++) {
            if (list.get(i).indexOf("default14")!=-1) {
                is368=true;
                break;
            }
        }

        System.out.println("是否368:"+is368);
        //输入User 3位
        String[] User=new String[6];
        User[0]=sw.getPQ_data().getUserR();
        User[1]=sw.getPQ_data().getUserG();
        User[2]=sw.getPQ_data().getUserB();
        User[3]=sw.getPQ_data().getUserROFF();
        User[4]=sw.getPQ_data().getUserGOFF();
        User[5]=sw.getPQ_data().getUserBOFF();
        //输入Normal 3位
        String[] normal=new String[6];
        normal[0]=sw.getPQ_data().getStandardR();
        normal[1]=sw.getPQ_data().getStandardG();
        normal[2]=sw.getPQ_data().getStandardB();
        normal[3]=sw.getPQ_data().getStandardROFF();
        normal[4]=sw.getPQ_data().getStandardGOFF();
        normal[5]=sw.getPQ_data().getStandardBOFF();
        //输入warm 3位
        String[] warm=new String[6];
        warm[0]=sw.getPQ_data().getWarmR();
        warm[1]=sw.getPQ_data().getWarmG();
        warm[2]=sw.getPQ_data().getWarmB();
        warm[3]=sw.getPQ_data().getWarmROFF();
        warm[4]=sw.getPQ_data().getWarmGOFF();
        warm[5]=sw.getPQ_data().getWarmBOFF();
        //输入cool 3位
        String[] cool=new String[6];
        cool[0]=sw.getPQ_data().getCoolR();
        cool[1]=sw.getPQ_data().getCoolG();
        cool[2]=sw.getPQ_data().getCoolB();
        cool[3]=sw.getPQ_data().getCoolROFF();
        cool[4]=sw.getPQ_data().getCoolGOFF();
        cool[5]=sw.getPQ_data().getCoolBOFF();
        //处理
        int sum=0;
        if (is368) {
            for (int i = 0; i <list.size(); i++) {
                if (list.get(i).indexOf("mode: Warm")!=-1) {
                    sum++;
                    System.out.println("Warm行数"+(i+1));
                        String red=warm[0]+","+warm[0]+","+warm[0]+",\\";
                        list.set(i+2, red);
                        list.set(i+3, red);
                        list.set(i+4, red);
                        list.set(i+5, red);
                        list.set(i+6, warm[0]+","+warm[0]+";");

                        String green=warm[1]+","+warm[1]+","+warm[1]+",\\";
                        list.set(i+8, green);
                        list.set(i+9, green);
                        list.set(i+10, green);
                        list.set(i+11, green);
                        list.set(i+12, warm[1]+","+warm[1]+";");

                        String blue=warm[2]+","+warm[2]+","+warm[2]+",\\";
                        list.set(i+14, blue);
                        list.set(i+15, blue);
                        list.set(i+16, blue);
                        list.set(i+17, blue);
                        list.set(i+18, warm[2]+","+warm[2]+";");

                        String redoff=warm[3]+","+warm[3]+","+warm[3]+",\\";
                        list.set(i+20, redoff);
                        list.set(i+21, redoff);
                        list.set(i+22, redoff);
                        list.set(i+23, redoff);
                        list.set(i+24, warm[3]+","+warm[3]+";");

                        String greenoff=warm[4]+","+warm[4]+","+warm[4]+",\\";
                        list.set(i+26, greenoff);
                        list.set(i+27, greenoff);
                        list.set(i+28, greenoff);
                        list.set(i+29, greenoff);
                        list.set(i+30, warm[4]+","+warm[4]+";");

                        String blueoff=warm[5]+","+warm[5]+","+warm[5]+",\\";
                        list.set(i+32, blueoff);
                        list.set(i+33, blueoff);
                        list.set(i+34, blueoff);
                        list.set(i+35, blueoff);
                        list.set(i+36, warm[5]+","+warm[5]+";");
                }else if (list.get(i).indexOf("mode: Cool")!=-1) {
                    System.out.println("Cool行数"+(i+1));
                        String red=cool[0]+","+cool[0]+","+cool[0]+",\\";
                        list.set(i+2, red);
                        list.set(i+3, red);
                        list.set(i+4, red);
                        list.set(i+5, red);
                        list.set(i+6, cool[0]+","+cool[0]+";");

                        String green=cool[1]+","+cool[1]+","+cool[1]+",\\";
                        list.set(i+8, green);
                        list.set(i+9, green);
                        list.set(i+10, green);
                        list.set(i+11, green);
                        list.set(i+12, cool[1]+","+cool[1]+";");

                        String blue=cool[2]+","+cool[2]+","+cool[2]+",\\";
                        list.set(i+14, blue);
                        list.set(i+15, blue);
                        list.set(i+16, blue);
                        list.set(i+17, blue);
                        list.set(i+18, cool[2]+","+cool[2]+";");

                        String redoff=cool[3]+","+cool[3]+","+cool[3]+",\\";
                        list.set(i+20, redoff);
                        list.set(i+21, redoff);
                        list.set(i+22, redoff);
                        list.set(i+23, redoff);
                        list.set(i+24, cool[3]+","+cool[3]+";");

                        String greenoff=cool[4]+","+cool[4]+","+cool[4]+",\\";
                        list.set(i+26, greenoff);
                        list.set(i+27, greenoff);
                        list.set(i+28, greenoff);
                        list.set(i+29, greenoff);
                        list.set(i+30, cool[4]+","+cool[4]+";");

                        String blueoff=cool[5]+","+cool[5]+","+cool[5]+",\\";
                        list.set(i+32, blueoff);
                        list.set(i+33, blueoff);
                        list.set(i+34, blueoff);
                        list.set(i+35, blueoff);
                        list.set(i+36, cool[5]+","+cool[5]+";");
                }else if (list.get(i).indexOf("mode: Standard")!=-1) {
                    System.out.println("Standard 行数"+(i+1));
                        String red=normal[0]+","+normal[0]+","+normal[0]+",\\";
                        list.set(i+2, red);
                        list.set(i+3, red);
                        list.set(i+4, red);
                        list.set(i+5, red);
                        list.set(i+6, normal[0]+","+normal[0]+";");

                        String green=normal[1]+","+normal[1]+","+normal[1]+",\\";
                        list.set(i+8, green);
                        list.set(i+9, green);
                        list.set(i+10, green);
                        list.set(i+11, green);
                        list.set(i+12, normal[1]+","+normal[1]+";");

                        String blue=normal[2]+","+normal[2]+","+normal[2]+",\\";
                        list.set(i+14, blue);
                        list.set(i+15, blue);
                        list.set(i+16, blue);
                        list.set(i+17, blue);
                        list.set(i+18, normal[2]+","+normal[2]+";");

                        String redoff=normal[3]+","+normal[3]+","+normal[3]+",\\";
                        list.set(i+20, redoff);
                        list.set(i+21, redoff);
                        list.set(i+22, redoff);
                        list.set(i+23, redoff);
                        list.set(i+24, normal[3]+","+normal[3]+";");

                        String greenoff=normal[4]+","+normal[4]+","+normal[4]+",\\";
                        list.set(i+26, greenoff);
                        list.set(i+27, greenoff);
                        list.set(i+28, greenoff);
                        list.set(i+29, greenoff);
                        list.set(i+30, normal[4]+","+normal[4]+";");

                        String blueoff=normal[5]+","+normal[5]+","+normal[5]+",\\";
                        list.set(i+32, blueoff);
                        list.set(i+33, blueoff);
                        list.set(i+34, blueoff);
                        list.set(i+35, blueoff);
                        list.set(i+36, normal[5]+","+normal[5]+";");
                }else if (list.get(i).indexOf("mode: User define")!=-1) {
                    System.out.println("Standard 行数"+(i+1));
                    String red=User[0]+","+User[0]+","+User[0]+",\\";
                    list.set(i+2, red);
                    list.set(i+3, red);
                    list.set(i+4, red);
                    list.set(i+5, red);
                    list.set(i+6, User[0]+","+User[0]+";");

                    String green=User[1]+","+User[1]+","+User[1]+",\\";
                    list.set(i+8, green);
                    list.set(i+9, green);
                    list.set(i+10, green);
                    list.set(i+11, green);
                    list.set(i+12, User[1]+","+User[1]+";");

                    String blue=User[2]+","+User[2]+","+User[2]+",\\";
                    list.set(i+14, blue);
                    list.set(i+15, blue);
                    list.set(i+16, blue);
                    list.set(i+17, blue);
                    list.set(i+18, User[2]+","+User[2]+";");

                    String redoff=User[3]+","+User[3]+","+User[3]+",\\";
                    list.set(i+20, redoff);
                    list.set(i+21, redoff);
                    list.set(i+22, redoff);
                    list.set(i+23, redoff);
                    list.set(i+24, User[3]+","+User[3]+";");

                    String greenoff=User[4]+","+User[4]+","+User[4]+",\\";
                    list.set(i+26, greenoff);
                    list.set(i+27, greenoff);
                    list.set(i+28, greenoff);
                    list.set(i+29, greenoff);
                    list.set(i+30, User[4]+","+User[4]+";");

                    String blueoff=User[5]+","+User[5]+","+User[5]+",\\";
                    list.set(i+32, blueoff);
                    list.set(i+33, blueoff);
                    list.set(i+34, blueoff);
                    list.set(i+35, blueoff);
                    list.set(i+36, User[5]+","+User[5]+";");
                }
            }
        }else {
            for (int i = 0; i <list.size(); i++) {
                if (list.get(i).indexOf("mode: Warm")!=-1) {
                    sum++;
                    System.out.println("Warm行数"+(i+1));

                        String red=warm[0]+","+warm[0]+","+warm[0]+",\\";
                        list.set(i+2, red);
                        list.set(i+3, red);
                        list.set(i+4, red);
                        list.set(i+5, warm[0]+","+warm[0]+","+warm[0]+";");

                        String green=warm[1]+","+warm[1]+","+warm[1]+",\\";
                        list.set(i+7, green);
                        list.set(i+8, green);
                        list.set(i+9, green);
                        list.set(i+10, warm[1]+","+warm[1]+","+warm[1]+";");

                        String blue=warm[2]+","+warm[2]+","+warm[2]+",\\";
                        list.set(i+12, blue);
                        list.set(i+13, blue);
                        list.set(i+14, blue);
                        list.set(i+15, warm[2]+","+warm[2]+","+warm[2]+";");

                        String redoff=warm[3]+","+warm[3]+","+warm[3]+",\\";
                        list.set(i+17, redoff);
                        list.set(i+18, redoff);
                        list.set(i+19, redoff);
                        list.set(i+20, warm[3]+","+warm[3]+","+warm[3]+";");

                        String greenoff=warm[4]+","+warm[4]+","+warm[4]+",\\";
                        list.set(i+22, greenoff);
                        list.set(i+23, greenoff);
                        list.set(i+24, greenoff);
                        list.set(i+25, warm[4]+","+warm[4]+","+warm[4]+";");

                        String blueoff=warm[5]+","+warm[5]+","+warm[5]+",\\";
                        list.set(i+27, blueoff);
                        list.set(i+28, blueoff);
                        list.set(i+29, blueoff);
                        list.set(i+30, warm[5]+","+warm[5]+","+warm[5]+";");
                }else if (list.get(i).indexOf("mode: Cool")!=-1) {
                    System.out.println("Cool行数"+(i+1));

                        String red=cool[0]+","+cool[0]+","+cool[0]+",\\";
                        list.set(i+2, red);
                        list.set(i+3, red);
                        list.set(i+4, red);
                        list.set(i+5, cool[0]+","+cool[0]+","+cool[0]+";");

                        String green=cool[1]+","+cool[1]+","+cool[1]+",\\";
                        list.set(i+7, green);
                        list.set(i+8, green);
                        list.set(i+9, green);
                        list.set(i+10, cool[1]+","+cool[1]+","+cool[1]+";");

                        String blue=cool[2]+","+cool[2]+","+cool[2]+",\\";
                        list.set(i+12, blue);
                        list.set(i+13, blue);
                        list.set(i+14, blue);
                        list.set(i+15, cool[2]+","+cool[2]+","+cool[2]+";");

                        String redoff=cool[3]+","+cool[3]+","+cool[3]+",\\";
                        list.set(i+17, redoff);
                        list.set(i+18, redoff);
                        list.set(i+19, redoff);
                        list.set(i+20, cool[3]+","+cool[3]+","+cool[3]+";");

                        String greenoff=cool[4]+","+cool[4]+","+cool[4]+",\\";
                        list.set(i+22, greenoff);
                        list.set(i+23, greenoff);
                        list.set(i+24, greenoff);
                        list.set(i+25, cool[4]+","+cool[4]+","+cool[4]+";");

                        String blueoff=cool[5]+","+cool[5]+","+cool[5]+",\\";
                        list.set(i+27, blueoff);
                        list.set(i+28, blueoff);
                        list.set(i+29, blueoff);
                        list.set(i+30, cool[5]+","+cool[5]+","+cool[5]+";");
                }else if (list.get(i).indexOf("mode: Standard")!=-1) {
                    System.out.println("Standard 行数"+(i+1));
                        String red=normal[0]+","+normal[0]+","+normal[0]+",\\";
                        list.set(i+2, red);
                        list.set(i+3, red);
                        list.set(i+4, red);
                        list.set(i+5, normal[0]+","+normal[0]+","+normal[0]+";");

                        String green=normal[1]+","+normal[1]+","+normal[1]+",\\";
                        list.set(i+7, green);
                        list.set(i+8, green);
                        list.set(i+9, green);
                        list.set(i+10, normal[1]+","+normal[1]+","+normal[1]+";");

                        String blue=normal[2]+","+normal[2]+","+normal[2]+",\\";
                        list.set(i+12, blue);
                        list.set(i+13, blue);
                        list.set(i+14, blue);
                        list.set(i+15, normal[2]+","+normal[2]+","+normal[2]+";");

                        String redoff=normal[3]+","+normal[3]+","+normal[3]+",\\";
                        list.set(i+17, redoff);
                        list.set(i+18, redoff);
                        list.set(i+19, redoff);
                        list.set(i+20, normal[3]+","+normal[3]+","+normal[3]+";");

                        String greenoff=normal[4]+","+normal[4]+","+normal[4]+",\\";
                        list.set(i+22, greenoff);
                        list.set(i+23, greenoff);
                        list.set(i+24, greenoff);
                        list.set(i+25, normal[4]+","+normal[4]+","+normal[4]+";");

                        String blueoff=normal[5]+","+normal[5]+","+normal[5]+",\\";
                        list.set(i+27, blueoff);
                        list.set(i+28, blueoff);
                        list.set(i+29, blueoff);
                        list.set(i+30, normal[5]+","+normal[5]+","+normal[5]+";");
                }else if (list.get(i).indexOf("mode: User define")!=-1) {
                    System.out.println("User 行数"+(i+1));
                    String red=User[0]+","+User[0]+","+User[0]+",\\";
                    list.set(i+2, red);
                    list.set(i+3, red);
                    list.set(i+4, red);
                    list.set(i+5, User[0]+","+User[0]+","+User[0]+";");

                    String green=User[1]+","+User[1]+","+User[1]+",\\";
                    list.set(i+7, green);
                    list.set(i+8, green);
                    list.set(i+9, green);
                    list.set(i+10, User[1]+","+User[1]+","+User[1]+";");

                    String blue=User[2]+","+User[2]+","+User[2]+",\\";
                    list.set(i+12, blue);
                    list.set(i+13, blue);
                    list.set(i+14, blue);
                    list.set(i+15, User[2]+","+User[2]+","+User[2]+";");

                    String redoff=User[3]+","+User[3]+","+User[3]+",\\";
                    list.set(i+17, redoff);
                    list.set(i+18, redoff);
                    list.set(i+19, redoff);
                    list.set(i+20, User[3]+","+User[3]+","+User[3]+";");

                    String greenoff=User[4]+","+User[4]+","+User[4]+",\\";
                    list.set(i+22, greenoff);
                    list.set(i+23, greenoff);
                    list.set(i+24, greenoff);
                    list.set(i+25, User[4]+","+User[4]+","+User[4]+";");

                    String blueoff=User[5]+","+User[5]+","+User[5]+",\\";
                    list.set(i+27, blueoff);
                    list.set(i+28, blueoff);
                    list.set(i+29, blueoff);
                    list.set(i+30, User[5]+","+User[5]+","+User[5]+";");
                }
            }
        }
        System.out.println("数量："+sum);
        System.out.println("\n\n");


        FileOutputStream fos;
        try {
            fos = new FileOutputStream(sw.getSoftware_color_temperature_file_path(),false);//为false
            String s  = "";
            for (int i = 0; i < list.size(); i++) {
                s+=list.get(i)+"\r\n";
            }
            byte[] bytes = s.getBytes("UTF-8");  // 将字符串按指定编码集编码--》将信息转成二进制数
            fos.write(bytes);  // 这样写入的数据，会将文件中的原数据覆盖
            fos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}

