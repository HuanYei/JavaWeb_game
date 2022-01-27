package com.liufujun.game.me.conntroller;

import com.liufujun.game.util.服务器使用路径;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.*;

@Controller
public class SWconntroller {

    @RequestMapping("/toSWtool")
    public String toSWtool(Model model){
        List<String> e总仓库=new ArrayList<String>();
        e总仓库.addAll(服务器使用路径.MTK368PALL);
        e总仓库.addAll(服务器使用路径.MTK9632PALL);
        e总仓库.addAll(服务器使用路径.RTK2851PALL);
        model.addAttribute("total",e总仓库);
        List<String> e总软件存放地址=new ArrayList<>();
        for (String path:e总仓库) {
            if (path.indexOf("MTK368P")!=-1){
                String a=path.substring(path.indexOf("：")+1);
                String a2=a.substring(0,a.length()-1);
                a=a2.substring(0,a2.lastIndexOf("/"));
                e总软件存放地址.add(a+"/images/android_9/");
            }else if (path.indexOf("MTK9632P")!=-1){
                String a=path.substring(path.indexOf("：")+1);
                String a2=a.substring(0,a.length()-1);
                a=a2.substring(0,a2.lastIndexOf("/"));
                e总软件存放地址.add(a+"/images/android_9/");
            }else if (path.indexOf("2851_all")!=-1){
                e总软件存放地址.add(path.substring(path.indexOf("：")+1)+"image_file_creator/rarfiles/");
            }
        }

        List<File> list=getFileSort(e总软件存放地址);
        removeDuplicateWithOrder(list);
        model.addAttribute("swfile",list);
//        for (File f :
//                list) {
//
//            System.out.println(f.getName());
//        }

        return "me/SWtool";
    }
    // 删除ArrayList中重复元素，保持顺序
    public static void removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        System.out.println( " remove duplicate " + list);
    }
    // 获取目录下所有文件(按时间排序)
    public static List<File> getFileSort(List<String> paths) {

        List<File> list=new ArrayList<File>();
        for (String path:paths) {
            System.out.println(path);
            list.addAll(getFiles(path, new ArrayList<File>()));
        }
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<File>() {
                public int compare(File file, File newFile) {
                    if (file.lastModified() < newFile.lastModified()) {//降序<;升序>
                        return 1;
                    } else if (file.lastModified() == newFile.lastModified()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
        }
        return list;
    }

    public static List<File> getFiles(String realpath, List<File> files) {
        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    if (file.getName().length()>20)
                    files.add(file);
                }
            }
        }
        return files;
    }
}
