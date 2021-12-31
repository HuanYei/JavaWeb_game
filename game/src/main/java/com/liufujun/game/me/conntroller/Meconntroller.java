package com.liufujun.game.me.conntroller;

import com.liufujun.game.me.dao.PanelDao;
import com.liufujun.game.me.dao.RtkpqDao;
import com.liufujun.game.me.dao.SwDao;
import com.liufujun.game.me.pojo.SW;
import com.liufujun.game.me.pojo.SwEnglish;
import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.util.Colortemperature;
import com.liufujun.game.util.EditorstyleUtil;
import com.liufujun.game.util.PlanUtil;
import com.liufujun.game.util.RAWUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@Controller
public class Meconntroller {

    @RequestMapping("/")
    public String  toMeworkbench(){
        return "me/meindex";
    }
    @RequestMapping("/dzpt")
    public String  todzpt(){
        return "me/dzpt";
    }


    @PostMapping("/RmScname")
    public String  RmScname(@RequestParam("oldScname") String oldScname,@RequestParam("newScname") String newScname,Model model){
        String a=newScname;
        newScname=oldScname.replace(oldScname.substring(oldScname.lastIndexOf("/")+1),"")+newScname+".sh";
        Fileprocessing.ReNameFile(oldScname,newScname);
        model.addAttribute("msg","重命名成功");
        return  "forward:/subswname?swname="+a;
    }

    @PostMapping("/rmDir")
    public String  rmDir(@RequestParam("oldScname") String oldScname,@RequestParam("newScname") String newScname,@RequestParam("SwName") String SwName,Model model){
        SwDao.SW宏修改(SwName,newScname);
        oldScname=oldScname.substring(0,oldScname.length()-1);
        newScname=oldScname.replace(oldScname.substring(oldScname.substring(0,oldScname.length()-2).lastIndexOf("/")+1),"")+newScname;
        Fileprocessing.ReNameFile(oldScname,newScname);

        model.addAttribute("msg","重命名成功");
        return  "forward:/subswname?swname="+SwName;
    }
    @PostMapping("/newDir")
    public String  newDir(@RequestParam("oldScname") String oldScname,@RequestParam("newScname") String newScname,@RequestParam("SwName") String SwName,Model model){
        SwDao.SW宏修改(SwName,newScname);
        oldScname=oldScname.substring(0,oldScname.length()-1);
        newScname=oldScname.replace(oldScname.substring(oldScname.substring(0,oldScname.length()-2).lastIndexOf("/")+1),"")+newScname;
        Fileprocessing.copy(oldScname+"/",newScname+"/");

        model.addAttribute("msg","重命名成功");
        return  "forward:/subswname?swname="+SwName;
    }

    @PostMapping("/newSc")
    public String  newSc(@RequestParam("oldScname") String oldScname,@RequestParam("newScname") String newScname,Model model){

        String a=newScname;
        newScname=oldScname.replace(oldScname.substring(oldScname.lastIndexOf("/")+1),"")+newScname+".sh";
        Fileprocessing.newFile(oldScname,newScname);
        model.addAttribute("msg","创建成功");
        return  "forward:/subswname?swname="+a;
    }

    @PostMapping("/UPlogo")
    public String  UPlogo(@RequestParam("oldScname") String oldScname,@RequestParam("newScname") String newScname,@RequestParam("SwName") String SwName,Model model){
        SwDao.SW宏修改(SwName,newScname);
        model.addAttribute("msg","更改LOGO成功");
        if (newScname.indexOf(".ini")!=-1){
            model.addAttribute("msg","更改屏参成功");
        }
        return  "forward:/subswname?swname="+SwName;
    }

    @PostMapping("/topostScript")
    public String  topostScript(@RequestParam("swSCpath") String swSCpath,Model model){
        model=Tosc(model,swSCpath);
        return "me/script_Update";
    }

    @GetMapping("/toScript")
    public String  togetScript(@RequestParam("swSCpath") String swSCpath,Model model){
        model=Tosc(model,swSCpath);
        return "me/script_Update";
    }

    @PostMapping("/textUpdate")
    public String textUpdate(@RequestParam("textcontent") String textcontent,@RequestParam("textpath") String textpath,Model model){
        System.out.println(textcontent);
        textcontent=EditorstyleUtil.DeleteStyle(textcontent);
        Fileprocessing.updateFile(textpath,textcontent);
        model.addAttribute("msg","成功保存");
        return "forward:/topostScript?swSCpath="+textpath;
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
        model.addAttribute("msg","色温修改成功");
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
        sw=SwDao.读取软件所有属性(sw.get软件路径全称(),sw);
        System.out.println(sw.toString());

        String SwType= PlanUtil.PlanType(swname);
        model.addAttribute("SwType",SwType);
        SwEnglish swE=new SwEnglish(sw);
        变量赋值(sw);
        model.addAttribute("SW",swE);
        model.addAttribute("msg","");
        return "me/SWmodification";
    }

    private void 变量赋值(SW sw) {
        potopath=sw.get软件logo路径全称();
        电子屏贴path=sw.get电子屏贴路径();
        Plan=sw.get方案();
        遥控器丝印图Path=sw.getIRimgPath();
    }

    String potopath="";
    String 电子屏贴path="";
    String Plan="";
    String 遥控器丝印图Path="";
    @RequestMapping(value = "/image/{image_name}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("image_name") String image_name) throws Exception{
        if (image_name.equals("dzpt")){
            return poto(电子屏贴path);
        }else if (image_name.equals("ykq")){
            return poto(遥控器丝印图Path);
        } else {
            byte[] imageContent ;
            String path = potopath;
            if (Plan.equals("2851")){
                imageContent =  RAWUtils.rawtshow(path,1920,1080);
            }else if (Plan.equals("2842")){
                imageContent =  RAWUtils.rawtshow(path,1280,720);
            }else {
                imageContent = Fileprocessing.fileToByte(new File(path));
            }
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
        }
    }

    private ResponseEntity poto(String Path) throws Exception{
        byte[] imageContent ;
        File potoFile=new File(Path);
        if (potoFile.exists()){
            imageContent = Fileprocessing.fileToByte(potoFile);
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
        }
        return null;
    }


    public Model Tosc(Model model,String swSCpath){
        System.out.println(swSCpath);
        String content=Fileprocessing.readTxtFile(swSCpath);
        content= EditorstyleUtil.Editorstyle(content);
        model.addAttribute("content",content);
        model.addAttribute("scriptpath",swSCpath);
        model.addAttribute("row",EditorstyleUtil.row);
        return model;
    }




    private void PQ(SwEnglish sw){
        if (sw.getIsRTK()==1){
            if (sw.getSoftware_color_temperature_file_path().indexOf("vip_default_osd.cpp")!=-1){
                String name="";
                if (sw.getPlan().equals("2851"))
                    name="pq";
                else
                    name="pq_RTK2842P";
                File file=new File(sw.getFull_name_of_software_customization_path()+name+"/");
                file.mkdir();
                Fileprocessing.newFile(sw.getSoftware_color_temperature_file_path(),sw.getFull_name_of_software_customization_path()+name+"/VIP_Panel_TEST_default_Osd.cpp");
                RtkpqDao.PQ_OSDUpdate(sw.getFull_name_of_software_customization_path()+name+"/VIP_Panel_TEST_default_Osd.cpp",sw.getPQ_data());
            }else {
                RtkpqDao.PQ_OSDUpdate(sw.getSoftware_color_temperature_file_path(),sw.getPQ_data());
            }
        }else {
            Colortemperature.Colortupdate(sw);
        }
    }
}
