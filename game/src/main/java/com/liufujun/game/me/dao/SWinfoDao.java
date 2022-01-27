package com.liufujun.game.me.dao;

import com.liufujun.game.me.pojo.SW;
import com.liufujun.game.pdf.util.Country;
import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.pdf.util.StringUtil;
import com.liufujun.game.sample.pojo.RTKmacro;
import com.liufujun.game.util.MyUtil;
import com.liufujun.game.util.服务器使用路径;


public class SWinfoDao {
    public static String 按键板类型(SW sw,int is){

        if (is==0){
            String C0017key=SwDao.e脚本宏查值("config_c001_onekeypad");
            if (C0017key.equals("1")){
                return "一键七键";
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
                return "一键七键";
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
        //MTK
        if (sw.getIsrtk()==0){
            sw.getSWinfo().setBoard(SwDao.e脚本宏查值("pcbname").replace("\"",""));
            sw.getSWinfo().setIsCI(SwDao.e脚本宏查值("ciEnable").equals("1")?"带":"不带");
            String EMMC=SwDao.e脚本宏查值("use_flash_size_type").equals("1")?"8G":"4G";
            String ddr=SwDao.e脚本宏查值("ddr_type");
            if (ddr.equals("1")){
                ddr="1G";
            }else if (ddr.equals("0")){
                ddr="512M";
            }else {
                ddr="1.5G";
            }
            sw.getSWinfo().setStore(ddr+EMMC);
            sw.getSWinfo().setTV_system("全ATV "+DTV());
            String ATV画面=SwDao.e脚本宏查值("color_system").toUpperCase();
            if (ATV画面.equals("未识别到这个宏"))ATV画面="PAL";
            String ATV声音=SwDao.e脚本宏查值("sound_system");
            if (ATV声音.equals("未识别到这个宏"))ATV声音="BG";
            String isdzpt=sw.getIs电子屏贴().equals("true")?"带":"不带";
            sw.getSWinfo().setIsDzpt(isdzpt);
            sw.getSWinfo().setATVManual(ATV画面+" "+ATV声音);
            sw.getSWinfo().setTTX_language(TTX());
            sw.getSWinfo().setECO_MODE(ECO());
            sw.getSWinfo().setCountry_and_language(Country.to国家值(
                    SwDao.e脚本宏查值("default_country"),0
            )+" "+Country.单个翻译语言(StringUtil.截取到第一次出现(SwDao.e脚本宏查值("language_list")," ")
            .replace("\"",""))+"语");
            sw.getSWinfo().setIsdolby( 杜比());
            sw.getSWinfo().setIsBluetooth(SwDao.e脚本宏查值("BluetoothEnable").equals("1")?"带":"不带");
            sw.getSWinfo().setLanguageAll(Country.to中文语言(SwDao.e脚本宏查值("language_list")));
            sw.getSWinfo().setSound_power(Sound());
            sw.getSWinfo().setCurrent(SwDao.e脚本宏查值("pwm_max_current"));
            String applist=SwDao.e脚本宏查值("preinstall_apk").replace("\"","");
            sw.getSWinfo().setAPPS(applist);
            sw.getSWinfo().setResolution(sw.getPanel().getM_wPanelWidth()+"*"+sw.getPanel().getM_wPanelHeight());
            sw.getSWinfo().setIsZeasn(applist.indexOf("Zeasn")==-1?"不带":"带");
            sw.getSWinfo().setIsEshare(applist.indexOf("EShare")==-1?"不带":"带");
            sw.getSWinfo().setUpdateName("MTK"+sw.get方案()+"P.bin");
            sw.getSWinfo().setModel_name(SwDao.e脚本宏查值("cus_pro_id").replace("\"",""));
            sw.getSWinfo().setBacklight(Backlight(sw));
            sw.getSWinfo().setStandby_mode(STR(sw));
            sw.getSWinfo().setEquipment_name(Equipment(sw.getSWinfo().getPath()));
        }else {
            //RTK
            sw.getSWinfo().setBoard(SwDao.e脚本宏查值("config_pcb_varient").replace("\"",""));
            sw.getSWinfo().setIsCI(SwDao.e脚本宏查值("config_default_enable_ci").equals("1")?"带":"不带");
            sw.getSWinfo().setStore(SwDao.e脚本宏查值("config_ddr_type_1G").equals("true")?"1G8G":"1.5G8G");
            sw.getSWinfo().setTV_system(RTKTV());
            String isdzpt=sw.getIs电子屏贴().equals("true")?"带":"不带";
            sw.getSWinfo().setIsDzpt(isdzpt);
            String ATV画面=SwDao.e脚本宏查值("config_color_system").toUpperCase();
            if (ATV画面.equals("未识别到这个宏"))ATV画面="PAL";
            String ATV声音=SwDao.e脚本宏查值("config_sound_system");
            if (ATV声音.equals("未识别到这个宏"))ATV声音="BG";
            sw.getSWinfo().setATVManual(ATV画面+" "+ATV声音);
            sw.getSWinfo().setCountry_and_language(Country.to国家值(
                    SwDao.e脚本宏查值("config_default_country").replace("\"",""),0
            )+" "+Country.单个翻译语言(StringUtil.截取到第一次出现(SwDao.e脚本宏查值("config_product_locales")," ")
                    .replace("\"",""))+"语");
            sw.getSWinfo().setIsdolby(SwDao.e脚本宏查值("config_dolby_able").equals("1")?"带":"不带");
            sw.getSWinfo().setIsBluetooth(SwDao.e脚本宏查值("config_bluetooth_enable").equals("1")?"带":"不带");
            sw.getSWinfo().setLanguageAll(Country.to中文语言(SwDao.e脚本宏查值("config_product_locales")));
            sw.getSWinfo().setSound_power(SwDao.e脚本宏查值("config_amp_power"));
            sw.getSWinfo().setCurrent(SwDao.e脚本宏查值("config_pwm_max_current"));
            String applist=SwDao.e脚本宏查值("config_prebuild_modules").replace("\"","");
            sw.getSWinfo().setAPPS(applist);
            sw.getSWinfo().setResolution(sw.getPanel().getM_wPanelWidth()+"*"+sw.getPanel().getM_wPanelHeight());
            sw.getSWinfo().setIsZeasn(applist.indexOf("zhixiangUI")==-1?"不带":"带");
            sw.getSWinfo().setIsEshare(SwDao.e脚本宏查值("toptech_eshare_enable").equals("1")?"带":"不带");
            sw.getSWinfo().setUpdateName("RTK"+sw.get方案()+"P.img");
            sw.getSWinfo().setModel_name(SwDao.e脚本宏查值("config_product_name").replace("\"",""));
            sw.getSWinfo().setBacklight(SwDao.e脚本宏查值("config_default_backlight").replace("\"",""));
            String mode=SwDao.e脚本宏查值("config_power_on_mode");
            sw.getSWinfo().setIsPower_memory(mode);
            sw.getSWinfo().setEquipment_name(RTKEquipment(sw.getSWinfo().getPath()));
            String aStr=SwDao.e脚本宏查值("config_str_disable");
            if (aStr.equals("未识别到这个宏")){
                aStr="STR开机";
            }else if (aStr.equals("0")){
                aStr="STR开机";
            }else {
                aStr="正常开机";
            }
            sw.getSWinfo().setStandby_mode(aStr);
            sw.getSWinfo().setECO_MODE(RTKECO());
            sw.getSWinfo().setTTX_language(RTKTTX());
        }
    }

    private static String RTKTV() {
        String TV="";
        if (SwDao.e脚本宏查值("config_no_atv").equals("true")){
            TV+="无ATV";
        }else {
            TV+=SwDao.e脚本宏查值("config_default_atv_system").toUpperCase();
        }
        if (SwDao.e脚本宏查值("config_no_dtv").equals("true")){
            TV+=" 无DTV";
        }else {
            TV+=" "+SwDao.e脚本宏查值("config_default_dtv_system").toUpperCase();
            TV+=" "+(SwDao.e脚本宏查值("config_default_enable_dvbc").equals("1")?"DVBC":"");
            TV+=" "+(SwDao.e脚本宏查值("config_default_enable_dvbs").equals("1")?"DVBS":"");
        }

        return TV;
    }

    private static String RTKEquipment(String path) {
        String aEquipme=SwDao.e脚本宏查值("config_device_name");

        if (aEquipme.equals("未识别到这个宏")||aEquipme.equals("")) {
            path += "customer/scripts/init.sh";
            String COnntt[] = Fileprocessing.readTxtFile(path).split("\n");
            String name = "";
            for (int i = 0; i < COnntt.length; i++) {
                if (COnntt[i].indexOf("config_device_name") != -1) {
                    name = COnntt[i].replace("config_device_name", "").replace("=", "").trim();
                    break;
                }
            }
            System.out.println(666+name+path);
            return name;

        }
        return aEquipme.replace("\"","");
    }
    private static String Equipment(String path) {
        String aEquipme=SwDao.e脚本宏查值("customer_device_name");
        if (aEquipme.equals("未识别到这个宏")||aEquipme.equals("")) {
            path += "vendor/toptech/product.mk";
            String COnntt[] = Fileprocessing.readTxtFile(path).split("\n");
            String name = "";
            for (int i = 0; i < COnntt.length; i++) {
                if (COnntt[i].indexOf("TOPTECH_PRODUCT_DEVICE") != -1) {
                    if (COnntt[i - 1].indexOf("else") != -1) {
                        name = COnntt[i].replace("export", "").replace("TOPTECH_PRODUCT_DEVICE", "").replace(":=", "").trim();
                        break;
                    }
                }
            }
            return name;
        }
        return aEquipme.replace("\"","");
    }

    private static String STR(SW sw) {
        String COnntt[]= Fileprocessing.readTxtFile(sw.get软件客制化路径全称()+"set_config").
                split("\n");
        String sttt="";
        String IsPower_memory="";
        for (int i = 0; i <COnntt.length ; i++) {
            if (COnntt[i].indexOf("str_crc")!=-1){
                sttt=COnntt[i].charAt(COnntt[i].length()-1)+"";
            }
            if (COnntt[i].indexOf("factory_poweron_mode")!=-1){
                IsPower_memory=COnntt[i].replace("factory_poweron_mode","").replace("setenv","").replace("_def","").trim();
                break;
            }
        }
        sw.getSWinfo().setIsPower_memory(IsPower_memory);
        if (sttt.equals("1")){
            return "正常待机";
        }else{
            return "STR待机";
        }

    }

    private static String Backlight(SW sw) {
        String jbBK=SwDao.e脚本宏查值("config_default_backlight");
        if (jbBK.equals("未识别到这个宏"))jbBK="100";
        System.out.println(sw.get软件客制化路径全称()+"default_ini/ConfigDefaultValue.ini");
        String COnntt= Fileprocessing.readTxtFile(sw.get软件客制化路径全称()+"default_ini/ConfigDefaultValue.ini").
                        split("\n")[65];
        COnntt=StringUtil.截取到第一次出现(COnntt,",");
        System.out.println(jbBK+" sssss file:"+COnntt);
        if (jbBK.equals(COnntt)||SwDao.e脚本宏查值("config_shopmode_enabled").equals("true"))
        return jbBK;
        else {
            return "书写错误";
        }
    }

    private static String 杜比() {
      String aa = SwDao.e脚本宏查值("dolby_enable");
      if (aa.equals("1")||aa.equals("true")){
          return "带";
      }else {
          return "不带";
      }
    }

    private static String Sound() {
        int aa=Integer.parseInt(SwDao.e脚本宏查值("VolumeCurve")) ;

        switch (aa){
            case 0:
                return "8O8W";
            case 1:
                return "8O10W";
            case 2:
                return "4O3W";
            case 3:
                return "8O5W";
            case 4:
                return "8O6W";
                default:
                    return "未识别";
        }
    }

    private static String RTKECO() {
        boolean showECO=SwDao.e脚本宏查值("config_ecomode_visible").equals("true");
        boolean showSHOP=SwDao.e脚本宏查值("config_shopmode_visible").equals("true");
        if (showECO){
            String isECO ="ECO Mode";
            String ECOenabled=SwDao.e脚本宏查值("config_ecomode_enabled").equals("true")?"开":"关";
            String ECOdate=SwDao.e脚本宏查值("config_ecomode_backlight");
            return "显示为"+isECO+",默认为"+ECOenabled+",开启时为"+ECOdate;
        }else if (showSHOP){
            String isECO ="Shop Mode";
            String ECOenabled=SwDao.e脚本宏查值("config_shopmode_enabled").equals("true")?"开":"关";
            String ECOdate=SwDao.e脚本宏查值("config_shopmode_backlight");
            return "显示为"+isECO+",默认为"+ECOenabled+",开启时为"+ECOdate;
        }else {
            return "无节能模式";
        }
    }

    private static String ECO() {
        boolean showECO=SwDao.e脚本宏查值("config_shopmode_visible").equals("true");
        if (showECO){
            String isECO =SwDao.e脚本宏查值("config_shopmode_change_to_ecomode").equals("true")?"ECO Mode":"SHOW Mode";
            String ECOenabled=SwDao.e脚本宏查值("config_shopmode_enabled").equals("true")?"开":"关";
            String ECOdate=SwDao.e脚本宏查值("config_shopmode_backlight");
            return "显示为"+isECO+",默认为"+ECOenabled+",开启时为"+ECOdate;
        }else {
            return "隐藏ECO";
        }
    }
    private static String TTX() {
        if (SwDao.e脚本宏查值("teletextEnable").equals("1")){
            if (SwDao.e脚本宏查值("config_ttx_language_visible").equals("true")){
                return "带图文,有显示图文语言选项，图文语言为"+TTX_L();
            }else {
                return "带图文,不显示图文语言选项，图文语言为"+TTX_L();
            }

        }else {
            return "不带图文";
        }
    }
    private static String RTKTTX() {
        if (SwDao.e脚本宏查值("config_default_enable_tt").equals("1")){
            if (SwDao.e脚本宏查值("config_ttx_language_visible").equals("true")){
                return "带图文,有显示图文语言选项，图文语言为"+RTKTTX_L();
            }else {
                return "带图文,不显示图文语言选项，图文语言为"+RTKTTX_L();
            }

        }else {
            return "不带图文";
        }
    }
    private static String TTX_L() {
        String e宏值=SwDao.e脚本宏查值("config_ttx_language_default");
        if (e宏值.equals("57")){
            return "arabic";
        }else if (e宏值.equals("0")){
            return "west";
        }else if (e宏值.equals("1")){
            return "east";
        }else if (e宏值.equals("2")){
            return "russian";
        }else if (e宏值.equals("7")){
            return "farsi";
        }else if (e宏值.equals("8")){
            return "arabic";
        }else {
            return "west";
        }
    }
    private static String RTKTTX_L() {
        String e宏值=SwDao.e脚本宏查值("config_ttx_language_default");
        if (e宏值.equals("0")){
            return "west";
        }else if (e宏值.equals("1")){
            return "east";
        }else if (e宏值.equals("2")){
            return "Russian";
        }else if (e宏值.equals("3")){
            return "Arabic";
        }else if (e宏值.equals("4")){
            return "Farsi";
        }else {
            return "west";
        }
    }
    private static String DTV() {
        String DTV=SwDao.e脚本宏查值("dtv_type_support_list").replace("0x","");
        char DTVarr[]= MyUtil.hexStringToByte(DTV).toCharArray();
        DTV="";
        int j=0;
        for (int i = DTVarr.length-1; i >=0 ; i--) {
            if (DTVarr[i]=='1'){
                DTV+=j;
            }
            j++;
        }
        DTVarr=DTV.toCharArray();
        DTV="";
        for (int i = 0; i <DTVarr.length ; i++) {
            switch (DTVarr[i]){
                case '0':
                    DTV+="DVBT ";
                    break;
                case '1':
                    DTV+="DVBC ";
                    break;
                case '2':
                    DTV+="DVBS ";
                    break;
                case '3':
                    DTV+="ATSC ";
                    break;
                case '4':
                    DTV+="ISDB ";
                    break;
                case '5':
//                        DTV+="DVBT2 ";
                    break;
                case '6':
//                        DTV+="DVBS2 ";
                    break;
                case '7':
                    DTV+="DTMB ";
                    break;
            }
        }
        DTV=DTV.trim();
        if (DTV.equals("")){
            DTV="无DTV";
        }
        return DTV;
    }

    private static void 通道处理(SW sw) {
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
                String board368[]= Fileprocessing.readTxtFile(服务器使用路径.MTK368PATH+"vendor/toptech/board.json").split("\n");
                System.out.println(board368.length);
                for (int i = 0; i <board368.length ; i++) {
                    if (board368[i].indexOf(board)!=-1){
                        sw.getSWinfo().setSOURCE(e取值(board368,i));
                    }
                }
            }else {
                board=SwDao.e脚本宏查值("config_pcb_varient").trim();
                System.out.println(board);
                String board9632[]= Fileprocessing.readTxtFile(服务器使用路径.MTK9632PATH+"vendor/toptech/board.json").split("\n");
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
            String board2851[]= Fileprocessing.readTxtFile(服务器使用路径.RTK2851PATH+"kernel/system/board.json").split("\n");
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
