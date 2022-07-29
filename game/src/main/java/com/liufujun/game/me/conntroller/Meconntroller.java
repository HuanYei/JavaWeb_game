package com.liufujun.game.me.conntroller;

import com.liufujun.game.config.FaeUpdate;
import com.liufujun.game.me.dao.PanelDao;
import com.liufujun.game.me.dao.SwDao;
import com.liufujun.game.me.pojo.SW;
import com.liufujun.game.me.pojo.SwEnglish;
import com.liufujun.game.util.*;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Controller
public class Meconntroller {

    @RequestMapping("/")
    public String  toMeworkbench(Model model){

        if (服务器使用路径.MTK368PALL.size()>0){
            model.addAttribute("isSelt368",服务器使用路径.MTK368PALL.get(0));
            model.addAttribute("equManager368",服务器使用路径.MTK368PALL);
            for (int i = 0; i <服务器使用路径.MTK368PALL.size() ; i++) {
                if (服务器使用路径.MTK368PALL.get(i).indexOf(服务器使用路径.MTK368PATH)!=-1){
                    model.addAttribute("isSelt368",服务器使用路径.MTK368PALL.get(i));
                }
            }
        }

        if (服务器使用路径.MTK9632PALL.size()>0){
            model.addAttribute("isSelt9632",服务器使用路径.MTK9632PALL.get(0));
            model.addAttribute("equManager9632",服务器使用路径.MTK9632PALL);
            for (int i = 0; i <服务器使用路径.MTK9632PALL.size() ; i++) {
                if (服务器使用路径.MTK9632PALL.get(i).indexOf(服务器使用路径.MTK9632PATH)!=-1){
                    model.addAttribute("isSelt9632",服务器使用路径.MTK9632PALL.get(i));
                }
            }
        }

        if (服务器使用路径.RTK2851PALL.size()>0){
            model.addAttribute("isSelt2851",服务器使用路径.RTK2851PALL.get(0));
            model.addAttribute("equManager2851",服务器使用路径.RTK2851PALL);
            for (int i = 0; i <服务器使用路径.RTK2851PALL.size() ; i++) {
                if (服务器使用路径.RTK2851PALL.get(i).indexOf(服务器使用路径.RTK2851PATH)!=-1){
                    model.addAttribute("isSelt2851",服务器使用路径.RTK2851PALL.get(i));
                }
            }
        }

        if (服务器使用路径.RTK2853PALL.size()>0){
            model.addAttribute("isSelt2853",服务器使用路径.RTK2853PALL.get(0));
            model.addAttribute("equManager2853",服务器使用路径.RTK2853PALL);
            for (int i = 0; i <服务器使用路径.RTK2853PALL.size() ; i++) {
                if (服务器使用路径.RTK2853PALL.get(i).indexOf(服务器使用路径.RTK2853PATH)!=-1){
                    model.addAttribute("isSelt2853",服务器使用路径.RTK2853PALL.get(i));
                }
            }
        }
        if (!服务器使用路径.comparison_tool.equals("")){
            model.addAttribute("Iscomparison","true");
        }
        model.addAttribute("ischeckbox368",Fileprocessing.findJBboolean("user.config","368configure="));
        model.addAttribute("ischeckbox9632",Fileprocessing.findJBboolean("user.config","9632configure="));
        model.addAttribute("ischeckbox2851",Fileprocessing.findJBboolean("user.config","2851configure="));
        model.addAttribute("ischeckbox2853",Fileprocessing.findJBboolean("user.config","2853configure="));


        model.addAttribute("datetime",MyUtil.time());
        return "me/meindex";
    }
    @RequestMapping("/dzpt")
    public String  todzpt(){
        return "me/dzpt";
    }

    @RequestMapping("/IRUpdate")
    public String  IRUpdate(@RequestParam("path") String IRPath,Model model){

        String name =MyUtil.totime()+".ini";
        String fuyonname =MyUtil.totime()+".xml";
        String fuyonPath=StringUtil.提取文件路径(IRPath)+"keys_extra.xml";
        //判断是否为368
        if (IRPath.indexOf("/MTK368P/")!=-1){
             name =MyUtil.totime()+".ini";
             fuyonname =MyUtil.totime()+".xml";
             fuyonPath=StringUtil.提取文件路径(IRPath)+"key_extras/com.toptech.tvmenu/keys_extra.xml";
        }else if (IRPath.indexOf("/RTD2853R/")!=-1){
             name =MyUtil.totime()+".config";
             fuyonname =MyUtil.totime()+".xml";
             fuyonPath=StringUtil.提取文件路径(IRPath)+"_tv_keys.xml";
        }
        System.out.println("fuyonPath="+fuyonPath+"\nIRPath="+IRPath);
        Fileprocessing.e单个文件上传(IRPath,"res/IR/IRfile/",name);
        Fileprocessing.e单个文件上传(fuyonPath,"res/IR/IRfile/",fuyonname);
        model.addAttribute("path","res/IR/IRfile/"+name+"="+StringUtil.e去除文件后缀(StringUtil.提取文件名(IRPath))+"?res/IR/IRfile/"+fuyonname);
        model.addAttribute("http","http://172.168.1.230:8888/FAEtoIRselect");
//        model.addAttribute("http","http://127.0.0.1:8888/FAEtoIRselect");
        System.out.println("IRPath = [" + IRPath + "], model = [" + model + "]");
        return "totofwq";
    }

    @RequestMapping(value = "/RmScname", method = RequestMethod.POST)
    @ResponseBody
    public String RmScname (@RequestBody(required=false) String path) {

        String newScname=path.split(" ")[0];
        String oldScname=path.split(" ")[1];
        String neworrm=path.split(" ")[2];
        String SwName=path.split(" ")[3];
        boolean is2851=PlanUtil.PlanIs2851(SwName);
        String newScnameNAME=newScname;
        System.out.println(path);

        //判断是否是文件夹
        if (oldScname.charAt(oldScname.length()-1)=='/'){
            oldScname=oldScname.substring(0,oldScname.length()-1);
            newScname=oldScname.replace(oldScname.substring(oldScname.substring(0,oldScname.length()-2).lastIndexOf("/")+1),"")+newScname;
        }else {
            newScname=StringUtil.RePath(oldScname,newScname);
        }
        System.out.println(newScname);
        if (Fileprocessing.isFile(newScname)){
            return "失败，该文件名已存在";
        }
        if (neworrm.equals("0")) Fileprocessing.ReNameFile(oldScname,newScname);
        else if (neworrm.equals("1")) Fileprocessing.newFile(oldScname,newScname);
        else if (neworrm.equals("2")){
            System.out.println(newScnameNAME);
            SwDao.SW宏修改(SwName,newScnameNAME);
            Fileprocessing.ReNameFile(oldScname,newScname);
        } else if (neworrm.equals("3")){
            System.out.println(newScnameNAME);
            SwDao.SW宏修改(SwName,newScnameNAME);
            Fileprocessing.copy(oldScname+"/",newScname+"/");
        } else if (neworrm.equals("4")){
            System.out.println(newScnameNAME);
            SwDao.SW宏修改(SwName,newScnameNAME);
            Fileprocessing.newFile(oldScname,newScname);
            if (is2851){
                String panelset= StringUtil.CDDD( StringUtil.CDDD(oldScname))+"panel_setting.h";
                String text=Fileprocessing.readTxtFile(panelset);
                String panel相对路径=newScname.replace(StringUtil.CDDD( StringUtil.CDDD(oldScname)),"");
                String update="\t#elif defined(TOPTECH_PANEL_NAME_"+newScnameNAME+")\n" +
                        "          #include \""+panel相对路径+"\"\n" +
                        "\t#else";
                System.out.println("panelset:"+panelset+"panel相对路径:"+panel相对路径+"update:"+update);

                if (text.indexOf("\t#else")==-1){
                    text=text.replace("#else",update);
                }else {
                    text=  text.replace("\t#else",update);
                }
                Fileprocessing.updateFile(panelset,text);
            }
        }


       return "成功";
    }





    @PostMapping("/UPlogo")
    public String  UPlogo(@RequestParam("newScname") String newScname,@RequestParam("SwName") String SwName,Model model){
        SwDao.SW宏修改(SwName,newScname);
        return  "forward:/subswname?swname="+SwName;
    }

    @PostMapping("/OpenFile")
    public String OpenFile(@RequestParam("Filepath") String Filepath){
        String path="",swname="";
        if (Filepath.indexOf("|")!=-1){
            int fjx=Filepath.indexOf("|");
            path=Filepath.substring(0,fjx);
            swname=Filepath.substring(fjx+1,Filepath.length());
            Fileprocessing.openFile(path);
            return "forward:/subswname?swname="+swname;
        }else {
            Fileprocessing.openFile(Filepath);
            return "forward:/topostScript?swSCpath="+Filepath;
        }

    }

    @PostMapping("/Color")
    public String COLOR(@ModelAttribute SwEnglish sw,Model model){
        PQ(sw);
        System.out.println(sw.getSoftware_name());
        return "forward:/subswname?swname="+sw.getSoftware_name();
    }
    @PostMapping("/Panel")
    public String Panel(@ModelAttribute SwEnglish sw,Model model){
        PanelDao.Panel修改(sw);
        model.addAttribute("msg","屏参修改成功");
        return "forward:/subswname?swname="+sw.getSoftware_name();
    }
    @PostMapping("/subswname")
    public String pdfupload(@RequestParam("swname") String swname, Model model) {
        if (swname.indexOf(".sh")!=-1){
            swname=swname.replace(".sh","");
        }
        SW sw=new SW();
        sw.set软件名称(swname);
        sw.set方案(PlanUtil.PlanType(swname));
        sw.set软件路径全称(PlanUtil.SW脚本路径(swname));
        sw=SwDao.读取软件所有属性(sw.get软件路径全称(),sw,model);
        System.out.println(sw.toString());

        String SwType= PlanUtil.PlanType(swname);
        model.addAttribute("SwType",SwType);

        SwEnglish swE=new SwEnglish(sw);
        变量赋值(sw);
        model.addAttribute("SW",swE);
        model.addAttribute("msg","");
        sw=null;
        swE=null;
        return "me/SWmodification";
    }

    private void 变量赋值(SW sw) {
        potopath=sw.get软件logo路径全称();
        电子屏贴path=sw.get电子屏贴路径();
        Plan=sw.get方案();
        遥控器丝印图Path=sw.getIRimgPath();
        e开机视频路径=sw.get软件开机视频路径全称();
        e是否有开机视频=sw.getSWinfo().getIsbootvideo();
        System.out.println("swpotopath = [" + potopath + "]");
    }
    String e是否有开机视频;
    String e开机视频路径;
    String potopath="";
    String 电子屏贴path="";
    String Plan="";
    String 遥控器丝印图Path="";
    @RequestMapping(value = "/image/{image_name}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("image_name") String image_name) throws Exception{
        if (image_name.equals("dzpt")){
            return poto(电子屏贴path,1);
        }else if (image_name.equals("ykq")){
            if (遥控器丝印图Path.indexOf(":")==-1)
            FaeUpdate.downloadByNIO2("http://172.168.1.230:8888/downloadres?path=res/IR/"+Plan+"/"+ StringUtil.提取文件名(遥控器丝印图Path),StringUtil.提取文件路径(遥控器丝印图Path), StringUtil.提取文件名(遥控器丝印图Path));
            System.out.println(666+遥控器丝印图Path);
            return poto(遥控器丝印图Path,2);
        } else {
            byte[] imageContent ;
            String path = potopath;
            if (Plan.equals("2851")||Plan.equals("2853")){
                imageContent =  RAWUtils.rawtshow(path,1920,1080);
            }else if (Plan.equals("2842")||Plan.equals("2843")){
                imageContent =  RAWUtils.rawtshow(path,1280,720);
            }else {
                imageContent = Fileprocessing.fileToByte(new File(path));
            }
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/preview2", method = RequestMethod.GET)
  @ResponseBody
   public void getPreview2( HttpServletResponse response) {
      try {
          if (e是否有开机视频.equals("false"))return;

            File file = new File(e开机视频路径);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition", "attachment; filename="+file.getName().replace(" ", "_"));
            InputStream iStream = new FileInputStream(file);
            IOUtils.copy(iStream, response.getOutputStream());
            response.flushBuffer();
          } catch (java.nio.file.NoSuchFileException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
          } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
       }
    }




    private ResponseEntity poto(String Path,int type) throws Exception{
        byte[] imageContent ;
        File potoFile=new File(Path);
        if (potoFile.exists()){

            imageContent = Fileprocessing.fileToByte(potoFile);
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
        }else if (type==1){
            return poto("res/config/drawable/sticker.png",2);
        }
        return null;
    }


    private void PQ(SwEnglish sw){
        SwDao.MTKPQ写入(sw);
        Colortemperature.Colortupdate(sw);

    }
}
