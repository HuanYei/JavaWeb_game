package com.liufujun.game.me.dao;

import com.liufujun.game.me.pojo.SW;
import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.pdf.util.StringUtil;
import com.liufujun.game.util.服务器使用路径;


public class SWinfoDao {
    public static String 按键板类型(SW sw,int is){

        if (is==0){
            String C0017key=SwDao.e脚本宏查值("config_c001_onekeypad");
            if (C0017key.equals("1")){
                return "虚拟七键";
            }
            String smart5key=SwDao.e脚本宏查值("config_use_smart_keyboard");
            if (smart5key.equals("1")){
                return "虚拟五键";
            }
            if (sw.getSWinfo().getKEYboardName().equals("DEMO_7KEY")){
                return "公版七键";
            }
            return 按键数量判断(sw);
        }else {
            if (sw.getSWinfo().getKEYboardName().equals("keypad_ToptechPublic_oneKey")){
                return "虚拟七键";
            }
            if (sw.getSWinfo().getKEYboardName().equals("keypad_ToptechPublic_7Key")){
                return "公版七键";
            }
            return 按键数量判断(sw);
        }
    }
    public static String 按键数量判断(SW sw){
        if (sw.getSWinfo().getKEYboardName().indexOf("7")!=-1){
            return "客户七键";
        }
        if (sw.getSWinfo().getKEYboardName().indexOf("七")!=-1){
            return "客户七键";
        }
        if (sw.getSWinfo().getKEYboardName().indexOf("5")!=-1){
            return "客户五键";
        }
        if (sw.getSWinfo().getKEYboardName().indexOf("五")!=-1){
            return "客户五键";
        }
        return "未识别";
    }
    public static void SWinfohandle(SW sw){
        通道处理(sw);  
    }

    private static void 通道处理(SW sw) {
        String board368[]= Fileprocessing.readTxtFile(服务器使用路径.MTK368PATH+"vendor/toptech/board.json").split("\n");
        String board9632[]= Fileprocessing.readTxtFile(服务器使用路径.MTK9632PATH+"vendor/toptech/board.json").split("\n");
        String board2851[]= Fileprocessing.readTxtFile(服务器使用路径.RTK2851PATH+"kernel/system/board.json").split("\n");
        String board;
        if (sw.getIsrtk()==0){
            System.out.println("rtk=0");
            board=SwDao.e脚本宏查值("pcb_board_type").trim();
            if (board.equals("Toptech_PCB_BOARD_EL_MT9255_FA48")||board.equals("Toptech_PCB_BOARD_EL_MT9255_FA75")){
                sw.getSWinfo().setSOURCE("一路RCA AV、两路HDMI");
                return;
            }
            System.out.println(sw.getPanel());
            if (sw.get方案().equals("368")){
                System.out.println(board368.length);
                for (int i = 0; i <board368.length ; i++) {
                    System.out.println(board);
                    if (board368[i].indexOf(board)!=-1){
                        System.out.println(board368[i]);
                        sw.getSWinfo().setSOURCE(e取值(board368,i));
                    }
                }
            }else {
                board=SwDao.e脚本宏查值("config_pcb_varient").trim();
                System.out.println(board);
                for (int i = 0; i <board9632.length ; i++) {
                    if (board9632[i].indexOf(board)!=-1){
                        System.out.println(board9632[i]);
                        sw.getSWinfo().setSOURCE(e取值(board9632,i));
                    }
                }
            }
        }else {
            board=SwDao.e脚本宏查值("config_pcb_varient");
            System.out.println(board);
            for (int i = 0; i <board2851.length ; i++) {
                if (board2851[i].indexOf(board)!=-1){
                    sw.getSWinfo().setSOURCE(e取值(board2851,i));
                }
            }
        }
    }

    private static String e取值(String[] e数组, int i) {
        String zhi="";
        boolean pd=false;
        for (int j = i; j <e数组.length ; j++) {
            if (pd){
                zhi+=e数组[j]+"、";
            }
            if (e数组[j].indexOf("\"desc\": [")!=-1){
                pd=true;
            }
            if (pd&&e数组[j].indexOf("]")!=-1){
                break;
            }

        }
        zhi=StringUtil.删除字符(zhi," ","\"","]","DTV","ATV",",","，");
        return StringUtil.删除前后字符(zhi,'、');
    }
}
