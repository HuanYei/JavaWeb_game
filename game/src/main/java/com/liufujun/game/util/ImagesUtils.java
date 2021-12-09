package com.liufujun.game.util;

import net.coobird.thumbnailator.Thumbnails;

public class ImagesUtils {
    public static void reduceImg(String imgsrc, String imgdist, int widthdist, int heightdist) {
        try {
            Thumbnails.of(imgsrc).size(widthdist, heightdist).keepAspectRatio(false).outputQuality(1f).toFile(imgdist);
        }catch (Exception e){
        }
    }

}