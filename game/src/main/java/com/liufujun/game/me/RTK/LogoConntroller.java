package com.liufujun.game.me.RTK;

import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.pdf.util.StringUtil;
import com.liufujun.game.util.ImagesUtils;
import com.liufujun.game.util.RAWUtils;
import com.liufujun.game.util.服务器使用路径;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class LogoConntroller {
    @RequestMapping("/toRAWLOGO")
    public String toMeworkbench() {
        return "tool/RawLogo";
    }

    @RequestMapping("/tokeyKeypad")
    public String tokeyKeypad() {
        return "tool/keyKeypad";
    }

    @RequestMapping("/to2851path")
    public String to2851path(Model model) {
        model.addAttribute("path",服务器使用路径.RTK2851PATH);
        return "tool/2851PATH";
    }

    @PostMapping("/LOGOupload")
    public String pdfupload(@RequestParam("file") MultipartFile file,
                            @RequestParam("logoname") String logoname,
                            @RequestParam("plan1") String plan1,
                            @RequestParam("up") String up,
                            Model model) {
        if (file.isEmpty()) {
            model.addAttribute("msg", "请选择文件!");
            return "tool/RawLogo";
        }
        if (logoname.equals("")) logoname="未命名";
        if (plan1.equals(""))plan1="2842";

        System.out.println("file = [" + file + "], logoname = [" + logoname + "], plan1 = [" + plan1 + "], model = [" + model + "]");
        try {
            Files.write(Paths.get("img/" + "test" + ".jpg"), file.getBytes());
            InputStream is = new FileInputStream(new File("img/" + "test" + ".jpg"));
            OutputStream os = new FileOutputStream(new File("img/" + logoname + ".jpg"));
            if (plan1.equals("2842")) {
                ImagesUtils.meImage(is, os, 1280, 720, "jpg");
            }else if (plan1.equals("2851")){
                ImagesUtils.meImage(is, os, 1920, 1080, "jpg");
            }
            RAWUtils.jpg2Raw("img/" + logoname + ".jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (up.equals("0")) {
            path = "img/" + logoname + ".raw";
            return "forward:/downloadlogo";
        }
        else {
            Fileprocessing.newFile("img/" + logoname + ".raw",服务器使用路径.LOGO路径2842+logoname + ".raw");
        }
        model.addAttribute("msg","成功");
        return "tool/RawLogo";
    }

    String path="";

    @RequestMapping("/downloadlogo")
    public void jbdownload(HttpServletResponse response)throws Exception{

        // 1.去指定目录读取文件
        File newfile = new File(path);
        // 2.将文件读取为文件输入流
        FileInputStream is = new FileInputStream(newfile);
        // 2.1 获取响应流之前  一定要设置以附件形式下载
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(StringUtil.提取文件名(path), "UTF-8"));
        // 3.获取响应输出流
        ServletOutputStream os = response.getOutputStream();
        // 4.输入流复制给输出流
        FileCopyUtils.copy(is,os);

    }
}
