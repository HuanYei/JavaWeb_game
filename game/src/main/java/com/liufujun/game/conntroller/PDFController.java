package com.liufujun.game.conntroller;

import com.liufujun.game.pdf.Main;
import com.liufujun.game.util.服务器使用路径;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class PDFController {
    @RequestMapping("/toPDF")
    public String  toPDF(){
        System.out.println(4);
        return "pdf";
    }

    @PostMapping("/pdfupload")
    public String pdfupload(@RequestParam("file") MultipartFile file,@RequestParam("file2") MultipartFile file2, @RequestParam("logo")
            String logo,@RequestParam("panel") String panel,Model model){
        if (file.isEmpty()){
            model.addAttribute("message", "The file is empty!");
            return "/uploadStatus";
        }
        try{
            String pdfpath=服务器使用路径.彩讯订单PDF文件存储路径 + file.getOriginalFilename();
            String 脚本path=服务器使用路径.彩讯订单参考脚本文件存储路径 + file2.getOriginalFilename();
            byte[] bytes = file.getBytes();
            Path path = Paths.get(pdfpath);
            Files.write(path, bytes);

            byte[] bytes2 = file2.getBytes();
            Path path2 = Paths.get(脚本path);
            Files.write(path2, bytes2);
            Main.user.set屏名(panel);
            Main.user.set开机logo(logo);
            String dy=Main.pdf(pdfpath, 脚本path);

            System.out.println(logo);
            dy=dy.replace("\n","<br/>");
            model.addAttribute("message", dy);
            if (dy.indexOf("脚本名")!=-1){
                model.addAttribute("xzjb", dy);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/pdfJG";
    }

    @RequestMapping("/jbdownload")
    public void jbdownload(HttpServletResponse response)throws Exception{
        String jbname=Main.脚本名;
        // 1.去指定目录读取文件
        File newfile = new File(服务器使用路径.彩讯订单脚本生成路径+jbname);
        System.out.println(服务器使用路径.彩讯订单脚本生成路径+jbname);
        // 2.将文件读取为文件输入流
        FileInputStream is = new FileInputStream(newfile);
        // 2.1 获取响应流之前  一定要设置以附件形式下载
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(jbname, "UTF-8"));
        // 3.获取响应输出流
        ServletOutputStream os = response.getOutputStream();
        // 4.输入流复制给输出流
        FileCopyUtils.copy(is,os);

    }
}
