package com.example.game.conntroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("jym")
public class TestController {
    ArrayList<Integer> 校验码List =new ArrayList<Integer>();


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Map<String, Object>> select(){
        String sql="select * from blog ORDER BY achievement ASC";
        return jdbcTemplate.queryForList(sql);
    }

    @RequestMapping("/paly")
    public String paly(Model model, ModelMap modelMap){
        int 校验码=(int)(Math.random()*10000);
        校验码List.add(校验码);
        model.addAttribute("MSG",modelMap.get("MSG"));
        model.addAttribute("list",select());
        model.addAttribute("jym",校验码);
        return "templates";
    }


    @RequestMapping("/insertsql")
    public String insert(@RequestParam("name1") String name,
                       @RequestParam("achievement1") String achievement,
                       @RequestParam("content1") String content,
                       ModelMap model,
                         SessionStatus sessionStatus,
                       @RequestParam("city1") String city){
        String jym;
        try {
            jym=model.get("jym").toString();
        }catch (Exception e){
            sessionStatus.setComplete();
            System.out.println("没有这个校验码");
            return "404";
        }

        System.out.println("校验码"+jym);
        if (jym.equals("")||jym==null) return "404";
        int jymxb=校验码List.indexOf(Integer.parseInt(jym));
        System.out.println("校验码下标:"+jymxb);
        if (jymxb==-1){
            return "404";
        }else {
            校验码List.remove(jymxb);
        }
        if (!校验成绩(content,achievement)){
            return "404";
        }

        String sql = "insert into blog(name,achievement,content,city) values('"+name+"',"+achievement+",'"+content+"','"+city+"')";
        //调用他的update方法-
        System.out.println(sql);
        jdbcTemplate.execute(sql);
        return "redirect:/paly";
    }

    private boolean 校验成绩(String content,String achievement) {
        String s=content.substring(0,2);
        String ss=content.substring(3,5);
        String sss=content.substring(6,8);
        int ssss=Integer.parseInt(s+ss+sss)-100000;
        if (ssss<0){
            ssss=0-ssss;
        }
        ssss+=512;
        System.out.println("ssss:"+ssss+" achievement "+achievement);

        if (ssss==Integer.parseInt(achievement)){
            return true;
        }
        return false;
    }
}
