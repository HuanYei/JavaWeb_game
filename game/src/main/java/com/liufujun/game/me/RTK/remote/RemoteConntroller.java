package com.liufujun.game.me.RTK.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;
import java.util.Map;

@Controller
public class RemoteConntroller {

    @RequestMapping("/toRTKRemote")
    public String toRTKRemote() {
        return "tool/RTKRemote";
    }

    @RequestMapping(value = "/chakey", method = RequestMethod.POST)
    @ResponseBody
    public String chakey (@RequestBody(required=false) String Silk) {
        List<Map<String, Object>> list=select(Silk);
        String key="";
        for (int i = 0; i <list.size() ; i++) {
            key+=list.get(i).get("remotekey")+"\n";
        }
        return key;
    }


    @RequestMapping(value = "/jiakey", method = RequestMethod.POST)
    @ResponseBody
    public String jiakey (@RequestBody(required=false) String Silk) {
            String arr[]=Silk.split("\n");
            insert(arr[0],arr[1]);
        return "插入成功";
    }



    @Autowired
    private  JdbcTemplate jdbcTemplate;


    public  List<Map<String, Object>> select(String Silk){
        String sql="select * from remote where Silk like '%"+Silk+"%'";
        System.out.println(sql);
        return jdbcTemplate.queryForList(sql);
    }


    public void insert(String Silk,String KEY){
        String sql="insert into remote (Silk,remotekey) values('"+Silk+"','"+KEY+"')";
        System.out.println(sql);
        jdbcTemplate.execute(sql);
    }
}
