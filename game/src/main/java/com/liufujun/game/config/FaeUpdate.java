package com.liufujun.game.config;

import com.liufujun.game.pdf.util.Fileprocessing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FaeUpdate {

    public static void autoUpdate() {
        File directory = new File("");//参数为空
        String courseFile="";
        try {
             courseFile = directory.getCanonicalPath() ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        downloadByNIO2("http://172.168.1.230:8888/Versiondownload", courseFile, "fae_tool_update.log");
        String Ver=courseFile+"\\fae_tool_update.log";
        int fwqvercode=Integer.parseInt(Fileprocessing.findJBFile(Ver,"VersionCode="));
        System.out.println(fwqvercode);
        if (fwqvercode>Versionconfig.VerCode){
            downloadByNIO2("http://172.168.1.230:8888/autodownload", courseFile, "jar.jar");
            System.out.println("done...");
            try {
                String cmd="\""+courseFile+ "\\update.bat \"";
                System.out.println(cmd);
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static void downloadByNIO2(String url, String saveDir, String fileName) {
        try (InputStream ins = new URL(url).openStream()) {
            Path target = Paths.get(saveDir, fileName);
            Files.createDirectories(target.getParent());
            Files.copy(ins, target, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
