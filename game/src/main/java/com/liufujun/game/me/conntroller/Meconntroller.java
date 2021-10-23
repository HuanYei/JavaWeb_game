package com.liufujun.game.me.conntroller;

import com.liufujun.game.me.dao.SwDao;
import com.liufujun.game.me.pojo.SW;
import com.liufujun.game.me.pojo.SwEnglish;
import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.util.Colortemperature;
import com.liufujun.game.util.EditorstyleUtil;
import com.liufujun.game.util.PlanUtil;
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

    @RequestMapping("/toMeworkbench")
    public String  toMeworkbench(){
        return "me/meindex";
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
        }
        if (Filepath.indexOf(".sh")!=-1){
            Fileprocessing.openFile(Filepath);
            return "forward:/topostScript?swSCpath="+Filepath;
        }else {
            Fileprocessing.openFile(path);
            return "forward:/subswname?swname="+swname;
        }

    }

    @PostMapping("/Color")
    public String COLOR(@ModelAttribute SwEnglish sw,Model model){
        Colortemperature.Colortupdate(sw);
        System.out.println(sw.getSoftware_name());
        model.addAttribute("msg","色温修改成功");
        return "forward:/subswname?swname="+sw.getSoftware_name();
    }
    @PostMapping("/Panel")
    public String Panel(@ModelAttribute SwEnglish sw,Model model){
        SwDao.Panel修改(sw);
        model.addAttribute("msg","屏参修改成功");
        return "forward:/subswname?swname="+sw.getSoftware_name();
    }
    @PostMapping("/subswname")
    public String pdfupload(@RequestParam("swname") String swname, Model model) {
        SW sw=new SW();
        sw.set软件名称(swname);
        sw.set软件路径全称(PlanUtil.SW脚本路径(swname));
        sw=SwDao.读取软件所有属性(sw.get软件路径全称(),sw);
        System.out.println(sw.toString());

        String SwType= PlanUtil.PlanType(swname);
        model.addAttribute("SwType",SwType);
        SwEnglish swE=new SwEnglish(sw);
        potopath=sw.get软件logo路径全称();
        model.addAttribute("SW",swE);
        model.addAttribute("msg","");
        return "me/SWmodification";
    }

    String potopath="";
    @RequestMapping(value = "/image/{image_name}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("image_name") String image_name) throws Exception{

        byte[] imageContent ;
        String path = potopath;
        imageContent = Fileprocessing.fileToByte(new File(path));

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
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

}
