package com.example.game.conntroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class AnswerController {
    @Autowired
    private JdbcTemplate jdbcTemplate;



    @RequestMapping("/test")
    public String  Test(){
        System.out.println(select("sw").get(0).get("content"));
        return "Answerquestions";
    }
    @RequestMapping("/toAddBUG")
    public String  toAddBUG(){
        System.out.println(select("sw").get(0).get("content"));
        return "Addquestion";
    }
    @RequestMapping("/addBUG")
    public String addBUG(Model model,@RequestParam("name1") String name){
        model.addAttribute("cg","cg");
        name =replaceBlank(name);
        name=deleteString2(name,'\"');
        name=deleteString2(name,'\'');
        insert(name);

        System.out.println(name);
        return "Addquestion";
    }

    @RequestMapping("/reply")
    public String reply(Model model,@RequestParam("name1") String name){
        model.addAttribute("cg","cg");
        String name2=new String();
        name2=name;
        List<String> list2=new ArrayList<String>(),list=new ArrayList<String>();
        for (int i=1;i<1000;i++){
            if (name2.indexOf(i+".")==0){
                if (name2.indexOf((i+1)+".")==-1){
                    list2.add(name2.substring(name2.indexOf(".")+1,name2.length()-1));
                }else {
                    list2.add(name2.substring(name2.indexOf(".")+1,name2.indexOf((i+1)+".")));
                }
            }
            if (name2.indexOf((i+1)+".")==-1)break;
            name2=name2.substring(name2.indexOf((i+1)+"."));
        }

        name =replaceBlank(name);
        name=deleteString2(name,'\"');
        name=deleteString2(name,'\'');

        for (int i=1;i<1000;i++){
            if (name.indexOf(i+".")==0){
                if (name.indexOf((i+1)+".")==-1){
                    list.add(name.substring(name.indexOf(".")+1,name.length()-1));
                }else {
                    list.add(name.substring(name.indexOf(".")+1,name.indexOf((i+1)+".")));
                }
            }
            if (name.indexOf((i+1)+".")==-1)break;
            name=name.substring(name.indexOf((i+1)+"."));
        }

        System.out.println(list.size()+"dd"+list2.size());

        String jg="";
        for (int i=0;i<list.size();i++){
            if (select(list.get(i)).size()!=0){
                jg+=(i+1)+"."+list2.get(i)+"<br/>"+"<span style=\"background: chartreuse\">DK"+Timeing()+"回复"+select(list.get(i)).get(0).get("reply")+"</span><br/><br/>";
            }else {
                jg+=(i+1)+"."+list2.get(i)+"<br/><br/>";
            }
        }
        model.addAttribute("jg",jg);
        return "jg";
    }

    public List<Map<String, Object>> select(String conent){
        String sql="select * from customer_reply where content='"+conent+"'";
        System.out.println(sql);
        return jdbcTemplate.queryForList(sql);
    }

    public  void insert(String name){
        String reply="",conect="";
        String sql = "";
        System.out.println(sql);
        for (int i=1;i<1000;i++){
            //定义
            int DK=name.indexOf("：",name.indexOf("DK2021")+7);
            if (DK>name.indexOf("DK2021")+20||DK==-1){
                DK=name.indexOf(":",name.indexOf("DK2021")+7);
            }
            if (DK==-1){
                DK=name.indexOf("DK2021");
            }
            System.out.println(DK);
            System.out.println(name.indexOf("DK2021")+ "sssss");
            //判断是否是第一个
            if (name.indexOf(1+".")==0){
                sql+="('"+name.substring(2,name.indexOf("DK2021"))+"','"+name.substring(DK,name.indexOf("2."))+"')";
            }else if (name.indexOf(i+".")==0){
//                System.out.println(i);
                reply=name.substring(name.indexOf(".")+1,name.indexOf("DK2021"));

                //判断是否是最后一个
                if (name.indexOf((i+1)+".")==-1){
                    sql+=",('"+reply+"','"+name.substring(DK)+"')";
                }
                else {
                    System.out.println("i="+i);
                    sql+=",('"+reply+"','"+name.substring(DK,name.indexOf((i+1)+".",name.indexOf("DK2021")+8))+"')";
                }
            };
            if (name.indexOf((i+1)+".")==-1)break;
            name=name.substring(name.indexOf((i+1)+".",name.indexOf("DK2021")+8));
        }
        //调用他的update方法-
        sql="insert into customer_reply(content,reply) values"+sql;
        System.out.println(sql);
        jdbcTemplate.execute(sql);
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n|");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 删除方法一
     * @param str
     * @param delChar
     * @return
     */
    public static String deleteString2(String str, char delChar) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != delChar) {
                stringBuffer.append(str.charAt(i));
            }
        }
        return stringBuffer.toString();
    }

    //当前时间
    public String Timeing(){
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy.MM.dd");// a为am/pm的标记

        Date date = new Date();// 获取当前时间

        return  sdf.format(date); // 输出已经格式化的现在时间(24小时制)
    }

    }
