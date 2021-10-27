package com.liufujun.game.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;


public class ImagesUtils {
    private Image img;
    private final static int WIDTH = 147;
    private final static int HEIGHT = 136;

    /**
     * 改变图片的大小到宽为size，然后高随着宽等比例变化
     * @param is 上传的图片的输入流
     * @param os 改变了图片的大小后，把图片的流输出到目标OutputStream
     * @param size 新图片的宽
     * @param format 新图片的格式
     * @throws IOException
     */
    public static OutputStream resizeImage(InputStream is, OutputStream os, int size, String format) throws IOException {
        BufferedImage prevImage = ImageIO.read(is);
        double width = prevImage.getWidth();
        double height = prevImage.getHeight();
        double percent = size/width;
        int newWidth = (int)(width * percent);
        int newHeight = (int)(height * percent);
        System.out.println("newWidth = [" + newWidth + "], newHeight = [" + newHeight + "], size = [");
        BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
        ImageIO.write(image, format, os);
        os.flush();
        is.close();
        os.close();

        return os;
    }
    public static OutputStream meImage(InputStream is, OutputStream os, int newWidth,int newHeight, String format) throws IOException {
        BufferedImage prevImage = ImageIO.read(is);
        System.out.println("newWidth = [" + newWidth + "], newHeight = [" + newHeight + "], size = [");
        BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
        ImageIO.write(image, format, os);
        os.flush();
        is.close();
        os.close();
        return os;
    }
    public static void main(String[] args) {
        try {
            InputStream is = new FileInputStream(new File("img/C001_VEGA_1920x1080_bootfile.jpg"));
            OutputStream os = new FileOutputStream(new File("img/C001_VEGA_1920x1080_bootfile2.jpg"));
            resizeImage(is, os, 1280, "jpg");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}