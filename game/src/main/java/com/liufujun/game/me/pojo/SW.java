package com.liufujun.game.me.pojo;

public class SW {
    String a="1";
    String 软件名称;
    String 软件路径全称;
    String 软件logo名;
    String 软件logo路径全称;
    String 软件logo前端;
    String 软件开机视频路径全称;
    String 软件屏参名路径全称;
    String 软件客制化路径全称;
    String 软件客制化名称;
    String 软件色温文件路径;
    PQ PQ数据=new PQ();
    Panel panel=new Panel();
    String 屏名;
    String 按键数量;
    String 客户名缩写;
    String 方案;
    String 智像DP;
    String is电子屏贴;
    String 电子屏贴路径;
    SWINFO SWinfo=new SWINFO();
    String IRimgPath;
    int isrtk;

    @Override
    public String toString() {
        return "SW{" +
                "\n方案='" + 方案 + '\'' +
                "\n客户名缩写='" + 客户名缩写 + '\'' +
                "\n软件名称='" + 软件名称 + '\'' +
                ",\n 软件路径全称='" + 软件路径全称 + '\'' +
                ",\n 软件logo名='" + 软件logo名 + '\'' +
                ",\n 软件logo路径全称='" + 软件logo路径全称 + '\'' +
                ",\n 软件开机视频路径全称='" + 软件开机视频路径全称 + '\'' +
                ",\n 软件屏参名路径全称='" + 软件屏参名路径全称 + '\'' +
                ",\n 软件客制化路径全称='" + 软件客制化路径全称 + '\'' +
                ",\n 软件色温文件路径='" + 软件色温文件路径 + '\'' +
                ",\n 屏名='" + 屏名 + '\'' +
                ",\n 按键数量='" + 按键数量 + '\'' +
                ",\n 软件客制化名称='" + 软件客制化名称 + '\'' +
                ",\n logo前端='" + 软件logo前端 + '\'' +
                ",\n 是否包含电子屏贴='" + is电子屏贴 + '\'' +
                ",\n 电子屏贴路径='" + 电子屏贴路径 + '\'' +
                '}'+PQ数据.toString()+"\n"+panel.toString()+"\n"+SWinfo.toString();
    }

    public PQ getPQ数据() {
        return PQ数据;
    }

    public void setPQ数据(PQ PQ数据) {
        this.PQ数据 = PQ数据;
    }

    public String get软件名称() {
        return 软件名称;
    }

    public void set软件名称(String 软件名称) {
        this.软件名称 = 软件名称;
    }

    public String get软件路径全称() {
        return 软件路径全称;
    }

    public void set软件路径全称(String 软件路径全称) {
        this.软件路径全称 = 软件路径全称;
    }

    public String get软件logo路径全称() {
        return 软件logo路径全称;
    }

    public void set软件logo路径全称(String 软件logo路径全称) {
        this.软件logo路径全称 = 软件logo路径全称;
    }

    public String get软件开机视频路径全称() {
        return 软件开机视频路径全称;
    }

    public void set软件开机视频路径全称(String 软件开机视频路径全称) {
        this.软件开机视频路径全称 = 软件开机视频路径全称;
    }

    public String get软件屏参名路径全称() {
        return 软件屏参名路径全称;
    }

    public void set软件屏参名路径全称(String 软件屏参名路径全称) {
        this.软件屏参名路径全称 = 软件屏参名路径全称;
    }

    public String get软件客制化路径全称() {
        return 软件客制化路径全称;
    }

    public String get智像DP() {
        return 智像DP;
    }

    public void set智像DP(String 智像DP) {
        this.智像DP = 智像DP;
    }

    public void set软件客制化路径全称(String 软件客制化路径全称) {
        this.软件客制化路径全称 = 软件客制化路径全称;
    }

    public String get软件logo名() {
        return 软件logo名;
    }

    public void set软件logo名(String 软件logo名) {
        this.软件logo名 = 软件logo名;
    }

    public String get软件色温文件路径() {
        return 软件色温文件路径;
    }

    public void set软件色温文件路径(String 软件色温文件路径) {
        this.软件色温文件路径 = 软件色温文件路径;
    }

    public String get屏名() {
        return 屏名;
    }

    public void set屏名(String 屏名) {
        this.屏名 = 屏名;
    }

    public String get按键数量() {
        return 按键数量;
    }

    public void set按键数量(String 按键数量) {
        this.按键数量 = 按键数量;
    }
    public String get软件客制化名称() {
        return 软件客制化名称;
    }

    public void set软件客制化名称(String 软件客制化名称) {
        this.软件客制化名称 = 软件客制化名称;
    }
    public String get软件logo前端() {
        return 软件logo前端;
    }

    public void set软件logo前端(String 软件logo前端) {
        this.软件logo前端 = 软件logo前端;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public String get客户名缩写() {
        return 客户名缩写;
    }

    public void set客户名缩写(String 客户名缩写) {
        this.客户名缩写 = 客户名缩写;
    }

    public String get方案() {
        return 方案;
    }

    public void set方案(String 方案) {
        this.方案 = 方案;
    }

    public int getIsrtk() {
        return isrtk;
    }

    public void setIsrtk(int isrtk) {
        this.isrtk = isrtk;
    }

    public String getIs电子屏贴() {
        return is电子屏贴;
    }

    public void setIs电子屏贴(String is电子屏贴) {
        this.is电子屏贴 = is电子屏贴;
    }

    public String get电子屏贴路径() {
        return 电子屏贴路径;
    }

    public void set电子屏贴路径(String 电子屏贴路径) {
        this.电子屏贴路径 = 电子屏贴路径;
    }

    public String getIRimgPath() {
        return IRimgPath;
    }

    public void setIRimgPath(String IRimgPath) {
        this.IRimgPath = IRimgPath;
    }

    public SWINFO getSWinfo() {
        return SWinfo;
    }

    public void setSWinfo(SWINFO SWinfo) {
        this.SWinfo = SWinfo;
    }
}
