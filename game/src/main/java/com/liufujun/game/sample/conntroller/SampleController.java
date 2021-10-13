package com.liufujun.game.sample.conntroller;

import com.liufujun.game.pdf.Main;
import com.liufujun.game.util.服务器使用路径;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class SampleController {

    @PostMapping("/RTKJBupload")
    public String RTKJBupload(@RequestParam("file") MultipartFile file,Model model){
        if (file.isEmpty()){
            model.addAttribute("message", "The file is empty!");
            return "/404";
        }
        try{
            String pdfpath= 服务器使用路径.RTK脚本 + file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            Path path = Paths.get(pdfpath);
            Files.write(path, bytes);
            model.addAttribute("message", "SSS");
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/pdfJG";
    }

}
