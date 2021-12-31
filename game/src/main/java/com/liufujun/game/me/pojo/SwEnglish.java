package com.liufujun.game.me.pojo;

public class SwEnglish {
  String software_name;
  String full_name_of_software_path;
  String software_logo_name;
  String full_name_of_software_logo_path;
  String full_name_of_software_startup_video_path;
  String full_name_of_software_screen_parameter_name_path;
  String full_name_of_software_customization_path;
  String software_customization_name;
  String software_color_temperature_file_path;
  PQ PQ_data;
  String screen_name;
  String number_of_keys;
  String logoweb;
  Panel Panel;
  String Plan;
  String zeasn_devicetype;
  int isRTK;
  String isdzpt;
  String dzptPath;
  SWINFO swinfo;
 public SwEnglish(SW sw){

      software_name=sw.get软件名称();
      full_name_of_software_path=sw.get软件路径全称();
      software_logo_name=sw.get软件logo名();
      full_name_of_software_logo_path=sw.get软件logo路径全称();
      full_name_of_software_startup_video_path=sw.get软件开机视频路径全称();
      full_name_of_software_screen_parameter_name_path=sw.软件屏参名路径全称;
      full_name_of_software_customization_path=sw.软件客制化路径全称;
      software_customization_name=sw.get软件客制化名称();
      software_color_temperature_file_path=sw.get软件色温文件路径();
      PQ_data=sw.getPQ数据();
      screen_name=sw.get屏名();
      number_of_keys=sw.get按键数量();
      logoweb=sw.软件logo前端;
      Panel=sw.getPanel();
      Plan=sw.get方案();
      isRTK=sw.getIsrtk();
      zeasn_devicetype=sw.get智像DP();
      isdzpt=sw.getIs电子屏贴();
     dzptPath=sw.get电子屏贴路径();
     swinfo=sw.getSWinfo();
 }
    public SwEnglish() {

    }

    public Panel getPanel() {
        return Panel;
    }

    public void setPanel(Panel panel) {
        Panel = panel;
    }

    public String getSoftware_name() {
        return software_name;
    }

    public void setSoftware_name(String software_name) {
        this.software_name = software_name;
    }

    public String getFull_name_of_software_path() {
        return full_name_of_software_path;
    }

    public void setFull_name_of_software_path(String full_name_of_software_path) {
        this.full_name_of_software_path = full_name_of_software_path;
    }

    public String getZeasn_devicetype() {
        return zeasn_devicetype;
    }

    public void setZeasn_devicetype(String zeasn_devicetype) {
        this.zeasn_devicetype = zeasn_devicetype;
    }

    public String getSoftware_logo_name() {
        return software_logo_name;
    }

    public void setSoftware_logo_name(String software_logo_name) {
        this.software_logo_name = software_logo_name;
    }

    public String getFull_name_of_software_logo_path() {
        return full_name_of_software_logo_path;
    }

    public void setFull_name_of_software_logo_path(String full_name_of_software_logo_path) {
        this.full_name_of_software_logo_path = full_name_of_software_logo_path;
    }

    public String getFull_name_of_software_startup_video_path() {
        return full_name_of_software_startup_video_path;
    }

    public void setFull_name_of_software_startup_video_path(String full_name_of_software_startup_video_path) {
        this.full_name_of_software_startup_video_path = full_name_of_software_startup_video_path;
    }

    public String getFull_name_of_software_screen_parameter_name_path() {
        return full_name_of_software_screen_parameter_name_path;
    }

    public void setFull_name_of_software_screen_parameter_name_path(String full_name_of_software_screen_parameter_name_path) {
        this.full_name_of_software_screen_parameter_name_path = full_name_of_software_screen_parameter_name_path;
    }

    public String getFull_name_of_software_customization_path() {
        return full_name_of_software_customization_path;
    }

    public void setFull_name_of_software_customization_path(String full_name_of_software_customization_path) {
        this.full_name_of_software_customization_path = full_name_of_software_customization_path;
    }

    public String getSoftware_customization_name() {
        return software_customization_name;
    }

    public void setSoftware_customization_name(String software_customization_name) {
        this.software_customization_name = software_customization_name;
    }

    public String getSoftware_color_temperature_file_path() {
        return software_color_temperature_file_path;
    }

    public void setSoftware_color_temperature_file_path(String software_color_temperature_file_path) {
        this.software_color_temperature_file_path = software_color_temperature_file_path;
    }

    public PQ getPQ_data() {
        return PQ_data;
    }

    public void setPQ_data(PQ PQ_data) {
        this.PQ_data = PQ_data;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getNumber_of_keys() {
        return number_of_keys;
    }

    public void setNumber_of_keys(String number_of_keys) {
        this.number_of_keys = number_of_keys;
    }

    public String getLogoweb() {
        return logoweb;
    }

    public void setLogoweb(String logoweb) {
        this.logoweb = logoweb;
    }

    public int getIsRTK() {
        return isRTK;
    }

    public void setIsRTK(int isRTK) {
        this.isRTK = isRTK;
    }

    public String getPlan() {
        return Plan;
    }

    public void setPlan(String plan) {
        Plan = plan;
    }

    public String getIsdzpt() {
        return isdzpt;
    }

    public void setIsdzpt(String isdzpt) {
        this.isdzpt = isdzpt;
    }

    public String getDzptPath() {
        return dzptPath;
    }

    public void setDzptPath(String dzptPath) {
        this.dzptPath = dzptPath;
    }

    public SWINFO getSwinfo() {
        return swinfo;
    }

    public void setSwinfo(SWINFO swinfo) {
        this.swinfo = swinfo;
    }
}
