package com.liufujun.game.me.conntroller;

import com.liufujun.game.config.Versionconfig;
import com.liufujun.game.pdf.util.Fileprocessing;
import com.liufujun.game.pdf.util.StringUtil;
import com.liufujun.game.util.PlanUtil;
import com.liufujun.game.util.服务器使用路径;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class SHconntroller {
    private ArrayList<String> JBAlllist=new ArrayList<>();
    @RequestMapping(value = "/chaJB", method = RequestMethod.POST)
    @ResponseBody
    public String jiakey (@RequestBody(required=false) String jiaoben) {
        if (jiaoben==null||jiaoben.equals("")){
            return "";
        }
        System.out.println(jiaoben);
        jiaoben=jiaoben.trim();
        String e关键字arr[] =jiaoben.split(" ");
        System.out.println(JBAlllist.size());
        return 查找(e关键字arr);
    }

    @RequestMapping(value = "/Compared", method = RequestMethod.POST)
    @ResponseBody
    public void Compared(@RequestBody(required=false) String Compared) {
        System.out.println("Compared = [" + Compared + "]");
        if (Compared==null||Compared.equals("")){
            return;
        }
        Compared=Compared.trim();
        String e双脚本arr[] =Compared.split(" ");

        Fileprocessing.ComparedJBOrDir(PlanUtil.SW脚本路径(e双脚本arr[0].replace(".sh","")),PlanUtil.SW脚本路径(e双脚本arr[1].replace(".sh","")));
    }


    @RequestMapping(value = "/Changku368", method = RequestMethod.POST)
    @ResponseBody
    public void Changku368 (@RequestBody(required=false) String path) {
        服务器使用路径.Info368(path.substring(path.indexOf("：")+1));
        System.out.println(服务器使用路径.MTK368PATH+"  当前路径");
    }

    @RequestMapping(value = "/openSWfile", method = RequestMethod.POST)
    @ResponseBody
    public void openSWfile (@RequestBody(required=false) String path) {
        System.out.println(path);
        Fileprocessing.openFile(path);
    }

    @RequestMapping(value = "/Changku9632", method = RequestMethod.POST)
    @ResponseBody
    public void Changku9632 (@RequestBody(required=false) String path) {
        System.out.println(path);
        服务器使用路径.Info9632(path.substring(path.indexOf("：")+1));
        System.out.println(服务器使用路径.MTK9632PATH+"  当前路径");
    }

    @RequestMapping(value = "/Changku2851", method = RequestMethod.POST)
    @ResponseBody
    public void Changku2851 (@RequestBody(required=false) String path) {
        服务器使用路径.Info2851(path.substring(path.indexOf("：")+1));
        System.out.println(服务器使用路径.RTK2851PATH+"  当前路径");
    }

    @RequestMapping(value = "/Changku2853", method = RequestMethod.POST)
    @ResponseBody
    public void Changku2853 (@RequestBody(required=false) String path) {
        服务器使用路径.Info2853(path.substring(path.indexOf("：")+1));
        System.out.println(服务器使用路径.RTK2853PATH+"  当前路径");
    }
    @RequestMapping(value = "/JBall", method = RequestMethod.POST)
    @ResponseBody
    public void JBall () {
        JBAlllist.clear();
        if (!服务器使用路径.MTK368PATH.equals("")) {
            File file368=new File(服务器使用路径.脚本路径368);
            JBAlllist.addAll(Arrays.asList(提取file名称(file368)));
        }
        if (!服务器使用路径.RTK2851PATH.equals("")) {
            File file2851=new File(服务器使用路径.脚本路径2851);
            File file2842=new File(服务器使用路径.脚本路径2842);
            JBAlllist.addAll(Arrays.asList(提取file名称(file2842)));
            JBAlllist.addAll(Arrays.asList(提取file名称(file2851)));
        }
        if (!服务器使用路径.RTK2853PATH.equals("")) {
            File file2853=new File(服务器使用路径.脚本路径2853);
            File file2843=new File(服务器使用路径.脚本路径2843);
            JBAlllist.addAll(Arrays.asList(提取file名称(file2843)));
            JBAlllist.addAll(Arrays.asList(提取file名称(file2853)));
        }
        if (!服务器使用路径.MTK9632PATH.equals("")) {
            File file9632=new File(服务器使用路径.脚本路径9632);
            File file6681=new File(服务器使用路径.脚本路径6681);
            JBAlllist.addAll(Arrays.asList(提取file名称(file6681)));
            JBAlllist.addAll(Arrays.asList(提取file名称(file9632)));
        }
    }

    private RestTemplate restTemplate=new RestTemplate();

    @RequestMapping(value = "/upfile", method = RequestMethod.POST)
    @ResponseBody
    public String upfile (@RequestBody(required=false) String path) {
        System.out.println("path = [" + path + "]");
        String name=path.split("::::")[1];
        path=path.split("::::")[0];
        System.out.println("path = [" + path + "]");
        File file=new File(path);
        MultipartFile cMultiFile=null;
        try {
             cMultiFile = new MockMultipartFile("file", file.getName(), null, new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = "http://172.168.1.230:8888/"+"uploadres";
        // 请求头设置,x-www-form-urlencoded格式的数据
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        FileSystemResource resource = new FileSystemResource(file);
        //提交参数设置
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", resource);
        map.add("path", "res/pq/");
        map.add("name", name);
        // 组装请求体
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(map);
        // 发送post请求，并打印结果，以String类型接收响应结果JSON字符串
        restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

        return "成功";
    }


    public String[] 提取file名称(File path) {
        File file[]=path.listFiles();
        String s[]=new String[file.length];
        for (int i = 0; i <s.length ; i++) {
            s[i]=file[i].getName();
        }
        return s;
    }

    private String 查找(String[] e关键字arr) {
        ArrayList<String> list=new ArrayList<String>();

        for (int i = 0; i <JBAlllist.size() ; i++) {
            boolean 判断符合=true;
            for (int j = 0; j <e关键字arr.length ; j++) {
                if (JBAlllist.get(i).indexOf(e关键字arr[j])==-1){
                    判断符合=false;
                }
            }
            if (判断符合){
                list.add(JBAlllist.get(i));
            }
        }
        System.out.println("查询结果"+list.size());
        String e结果="";
        for (String a:list) {
            e结果+=a+"|";
        }
        return e结果;
    }
}
