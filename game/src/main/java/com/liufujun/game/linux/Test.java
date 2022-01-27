package com.liufujun.game.linux;

import com.liufujun.game.util.PlanUtil;
import com.liufujun.game.util.服务器使用路径;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
// long lon=1638435569895l;
//        System.out.println("employee.xml文件的最后修改时间 = "+new Date(lon));

        ConnectLinux.Connect();
        ConnectLinux.execComm("cd "+服务器使用路径.MTK368_Linux+"\n"
        +"git branch");
        String sh="source /media/workspace0/liufujun/9255/MTK368P/vendor/toptech/build.sh   --boot \n";
        System.out.println(sh);
        ConnectLinux.execComm(sh);

    }
    public static void 编译脚本(String 脚本){
        switch (PlanUtil.PlanType(脚本)){
            case "368":
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
