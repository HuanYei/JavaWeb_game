package com.liufujun.game.linux;

import com.liufujun.game.util.PlanUtil;
import com.liufujun.game.util.服务器使用路径;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
 long lon=1638435569895l;
        System.out.println("employee.xml文件的最后修改时间 = "+new Date(lon));

//        ConnectLinux.Connect();
//        编译脚本("C001_EGYPT_EXE_TC2107-177_LE40T1_EL_MT9255_FA75_ShareOn_DobyOff_SW560MA_InverseOff_8G_MirrorOff_V400HJ6_PE1C3_C001_509_ELC");
//        编译脚本("C001_EGYPT_EXE_TC2107-179_LE43T1_EL_MT9255_FA75_ShareOn_DobyOff_SW560MA_InverseOff_8G_MirrorOff_C001_CC430LV2D_C001_509_ELC");
//        编译脚本("C001_EGYPT_EXE_TC2108-063_LE32T1_EL_MT9255_FA48_ShareOn_DobyOff_SW600MA_InverseOff_8G_MirrorOn_C001_LSC320AN09_H_C001_509_SS_ELC");

    }
    public static void 编译脚本(String 脚本){
        switch (PlanUtil.PlanType(脚本)){
            case "368":
                System.out.println(1);
                ConnectLinux.execComm("cd "+服务器使用路径.MTK368_Linux+"\n"
                +"./rmuntrackfile.sh");
                System.out.println(2);
                ConnectLinux.execComm("cd "+服务器使用路径.MTK368_Linux+"\n"
                        +"git checkout .");
                System.out.println(3);
                String sh="cd "+服务器使用路径.MTK368_Linux+"\n"
                        +"source vendor/toptech/build.sh  "+脚本+" --rar --ota&&"+"1\n";
                System.out.println(sh);
                ConnectLinux.execComm(sh);
                System.out.println(4);
                break;
            case "2851":
                break;
            case "2842":
                break;
            case "9632":
                break;
            case "6681":
                break;
        }

    }
}
