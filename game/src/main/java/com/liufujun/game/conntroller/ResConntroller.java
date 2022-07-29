package com.liufujun.game.conntroller;


import com.liufujun.game.config.FaeUpdate;
import com.liufujun.game.util.Fileprocessing;
import com.liufujun.game.util.MyUtil;
import com.liufujun.game.util.StringUtil;
import com.liufujun.game.util.服务器使用路径;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
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
//            Rtklist.addAll(服务器使用路径.RTK2851PALL);
            Rtklist.addAll(服务器使用路径.RTK2853PALL);
            model.addAttribute("list",Rtklist);
        }else {
            List<String> Mtklist=new ArrayList<>();
            Mtklist.addAll(服务器使用路径.MTK368PALL);Mtklist.addAll(服务器使用路径.MTK9632PALL);
            model.addAttribute("list",Mtklist);
        }
        model.addAttribute("path",swname);
        return "selectwarehouse";
    }

    @RequestMapping("/jg")
    public String JG(@RequestParam("jg") String jg, Model model){
        model.addAttribute("jg",jg);
        return "jg";
    }
    public static String RTKPQpath="";
    @RequestMapping("/resDownloadPQ")
    public ModelAndView resDownloadPQ(@RequestParam("path") String path, Model model){
        System.out.println("swname = [" + path );
        String paths[]=path.split("\\?");
        String e生成文件夹路径=StringUtil.提取文件路径(paths[0]);
        String e源文件名=StringUtil.提取文件名(paths[0]);
        if (e生成文件夹路径.indexOf("customers")==-1){
            return new ModelAndView(new RedirectView("http://127.0.0.1:9090/jg?jg="+"<b style='color:red;'>error:</b>There is no PQ file in the customized folder"));
        }
        Fileprocessing.deletefile(paths[0]);
        String cppfwqpath=paths[1].split("=")[0];
        String cppfwqname=paths[1].split("=")[1];
        FaeUpdate.downloadByNIO2("http://172.168.1.230:8888/downloadres?path="+cppfwqpath, e生成文件夹路径, e源文件名);

        String binfwqpath=paths[2].split("=")[0];
        String binfwqname=paths[2].split("=")[1];
        FaeUpdate.downloadByNIO2("http://172.168.1.230:8888/downloadres?path="+binfwqpath, e生成文件夹路径, binfwqname);

        String name="PQ"+ MyUtil.totime()+".cpp";
        Fileprocessing.e单个文件上传(e生成文件夹路径+e源文件名,"res/pq/",name);
        String plan="2851";
        if (e生成文件夹路径.indexOf("/PQ_OverScan")!=-1){
            plan="2853";
        }
        System.out.println(plan);
        return new ModelAndView(new RedirectView("http://172.168.1.230:8888/pdfuploadpath?path=res/pq/"+name+"&plan="+plan+"&despath="+e生成文件夹路径+cppfwqname));
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
                if (e每个路径[1].indexOf(".config")!=-1){
                    Fileprocessing.e删除红外开机键(c仓库路径,e每个路径[1]);
                    Fileprocessing.eRTK遥控器头码添加(c仓库路径,e每个路径[1]);
                }
            }else if (paths[i].indexOf("IR")!=-1&&path.indexOf(".ini")!=-1){
                c仓库路径=drepositoryname.split("：")[1]+"vendor/toptech/customer/common/ir/";
                String[] e每个路径=paths[i].split("=");
                FaeUpdate.downloadByNIO2("http://172.168.1.230:8888/downloadres?path="+e每个路径[0], c仓库路径, e每个路径[1]);
                System.out.println(e每个路径[0]);
                System.out.println( c仓库路径);
                System.out.println( e每个路径[1]);
            }else if (paths[i].indexOf("<key actual=\"")!=-1){
                c仓库路径=drepositoryname.split("：")[1]+"customer/IR/_tv_keys.xml";
                Fileprocessing.e删除按键复用(c仓库路径,paths[i].split("\n")[0].trim());
                Fileprocessing.e在源文件下方换行加入(c仓库路径,paths[i],"</KeyConstants>");
            }else if (paths[i].indexOf("<key name=\"")!=-1){
                //判断是否为368
                if (drepositoryname.indexOf("/MTK368P/")!=-1){
                    c仓库路径=drepositoryname.split("：")[1]+"vendor/toptech/customer/common/ir/key_extras/com.toptech.tvmenu/keys_extra.xml";
                }else {
                    c仓库路径=drepositoryname.split("：")[1]+"vendor/toptech/customer/common/ir/keys_extra.xml";
                }
                Fileprocessing.e删除按键复用(c仓库路径,paths[i].split("\n")[0].trim());
                Fileprocessing.e在源文件下方换行加入(c仓库路径,paths[i],"</key-map>");
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
