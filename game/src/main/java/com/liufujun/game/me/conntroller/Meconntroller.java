package com.liufujun.game.me.conntroller;

import com.liufujun.game.me.dao.SwDao;
import com.liufujun.game.me.pojo.SW;
import com.liufujun.game.me.pojo.SwEnglish;
import com.liufujun.game.util.PlanUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Meconntroller {

    @RequestMapping("/toMeworkbench")
    public String  toPDF(){
        return "me/meindex";
    }

    @PostMapping("/Color")
    public String  toCOLOR(){

        return "me/meindex";
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
        model.addAttribute("SW",swE);
        return "me/SWmodification";
    }
}
