package com.liufujun.game.me.RTK.remote;

import com.liufujun.game.pdf.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
public class RemoteConntroller {

    @RequestMapping("/toRTKRemote")
    public String toRTKRemote() {
        return "tool/RTKRemote";
    }


    @PostMapping("/Remoteupload")
    public String pdfupload(@RequestParam("file") MultipartFile file,
                            @RequestParam("remotename") String remotename,
                            @RequestParam("Customercode") String Customercode,
                            @RequestParam("keycode") String keycode,
                            @RequestParam("do") String up,
                            Model model) {

        System.out.println("remotename = [" + remotename + "], Customercode = [" + Customercode + "], keycode = [" + keycode + "], up = [" + up + "], model = [");

        String key=RemoteUtil.toRemoteUtil(Customercode,1,keycode);
        System.out.println(key);
        RemoteDao.UPConfig(key,file,remotename);
        if (up.equals("0")) {
            path = "res/IR/" + remotename + ".config";
            return "forward:/downloadconfig";
        }else {
            String msg=RemoteDao.C(remotename);
            System.out.println(msg);
            model.addAttribute("msg", msg);
        }
            return "tool/RTKRemote";
    }

    @RequestMapping("/downloadconfig")
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

private static String path="";

}
