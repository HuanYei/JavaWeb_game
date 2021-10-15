package com.liufujun.game.me.pojo;

public class SwEnglish {
 public String software_name;
 public String full_name_of_software_path;
 public String software_logo_name;
 public String full_name_of_software_logo_path;
 public String full_name_of_software_startup_video_path;
 public String full_name_of_software_screen_parameter_name_path;
 public String full_name_of_software_customization_path;
 public String software_customization_name;
 public String software_color_temperature_file_path;
 public PQ PQ_data;
 public String screen_name;
 public String number_of_keys;
 public String logoweb;

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
 }
}
