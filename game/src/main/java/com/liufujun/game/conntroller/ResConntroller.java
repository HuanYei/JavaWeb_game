package com.liufujun.game.conntroller;


import com.liufujun.game.config.FaeUpdate;
import com.liufujun.game.util.StringUtil;
import com.liufujun.game.util.服务器使用路径;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ResConntroller {

    @RequestMapping("/resDownloadrepository")
    public String resDownloadrepository(@RequestParam("path") String swname, Model model){
        System.out.println(swname);

        if(swname.indexOf(".config")!=-1){
            List<String> Rtklist=new ArrayList<>();
            Rtklist.addAll(服务器使用路径.RTK2851PALL); Rtklist.addAll(服务器使用路径.RTK2853PALL);
            model.addAttribute("list",Rtklist);
        }else {
            List<String> Mtklist=new ArrayList<>();
            Mtklist.addAll(服务器使用路径.MTK368PALL);Mtklist.addAll(服务器使用路径.MTK9632PALL);
            model.addAttribute("list",Mtklist);
        }
        model.addAttribute("path",swname);
        return "selectwarehouse";
    }

    @RequestMapping("/resdeal")
    public ModelAndView resdeal(@RequestParam("drepositoryname") String drepositoryname, @RequestParam("path") String path, Model model){
        System.out.println("drepositoryname = [" + drepositoryname + "], path = [" + path + "], model = [" + model + "]");
        String paths[]=path.split("\\?");
        String c仓库路径=drepositoryname.split("：")[1];
        for (int i = 0; i <paths.length ; i++) {
            System.out.println(i);
            if (paths[i].indexOf("IR")!=-1&&path.indexOf(".config")!=-1){
                c仓库路径=drepositoryname.split("：")[1]+"customer/IR/";
                String[] e每个路径=paths[i].split("=");
                FaeUpdate.downloadByNIO2("http://172.168.1.230:8888/downloadres?path="+e每个路径[0], c仓库路径, e每个路径[1]);
                System.out.println(e每个路径[0]);
                System.out.println( c仓库路径);
                System.out.println( e每个路径[1]);
            }else if (paths[i].indexOf("IR")!=-1&&path.indexOf(".ini")!=-1){
                c仓库路径=drepositoryname.split("：")[1]+"vendor/toptech/customer/common/ir/";
                String[] e每个路径=paths[i].split("=");
                FaeUpdate.downloadByNIO2("http://172.168.1.230:8888/downloadres?path="+e每个路径[0], c仓库路径, e每个路径[1]);
                System.out.println(e每个路径[0]);
                System.out.println( c仓库路径);
                System.out.println( e每个路径[1]);
            }
        }
        return new ModelAndView(new RedirectView("http://172.168.1.230:8888/toRTKRemote"));
    }

    @ResponseBody
    @RequestMapping("/uploadres")
    public void upload(@RequestParam("file") MultipartFile file,
                       @RequestParam("path") String path,
                       @RequestParam("name") String name) {
        if (file.isEmpty()) {
            return;
        }
        System.out.println("file = [" + file + "], path = [" + path + "], name = [" + name + "]");
        path= StringUtil.提取文件路径(path);
        File newfile=new File(path);
        if (!newfile.exists())newfile.mkdirs();
        try {
            Files.write(Paths.get(path+name), file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
