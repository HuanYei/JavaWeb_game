package com.liufujun.game.util;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.stream.ImageInputStream;

import com.github.jaiimageio.stream.RawImageInputStream;

public class RAWUtils {
	public static String jpg2Raw(String jpgPath) throws IOException {
		int index = jpgPath.lastIndexOf(".");
		String rawPath = jpgPath.substring(0, index) + ".raw";
		
		BufferedImage im = ImageIO.read(new File(jpgPath));
		im=toRGB(im);
		ImageIO.write(im, "raw", new File(rawPath));
		
		return rawPath;
	}

	
	private static Dimension guess(long length) {
		long wh = length / 3;
		int w = (int)Math.sqrt(wh);
		int h = (int)(wh / w);
		while(w <= wh) {
			if(wh - w * h == 0) {
				break;
			}
			h = (int)(wh / ++ w);
		}
		System.out.println("w=" + w + ", h=" + h);
		return new Dimension(w, h);
	}


	public static byte[] rawtshow(String rawPath,int width, int height) throws IOException {

		byte[] bytes = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			String jpgPath="res/img/"+rawPath.substring(rawPath.lastIndexOf("/")).replace(".raw",".jpg");
			File jpgFile = new File(jpgPath);
			System.out.println("jpgPath = [" + jpgPath + "], width = [" );
			if (!jpgFile.exists()) {
				File rawFile = new File(rawPath);
				Dimension dimension = new Dimension(width, height);
				BufferedImage bi = rawdate(rawFile, dimension);
				bi=toRGB(bi);
				ImageIO.write(bi, "png", baos);
				ImageIO.write(bi, "jpg", jpgFile);
			}else {
				BufferedImage bi = ImageIO.read(jpgFile);
				ImageIO.write(bi, "png", baos);
			}

			bytes = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			baos.close();
		}
		return bytes;
	}

	private static BufferedImage rawdate(File rawFile, Dimension dimension) throws IOException {
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("raw");
		ImageInputStream iis = null;
		RawImageInputStream ris = null;
		try {
			ImageReader reader = readers.next();

			iis = ImageIO.createImageInputStream(rawFile);
			ImageTypeSpecifier type =  ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_3BYTE_BGR);
			long[] index = {0};
			if(dimension == null) {
				dimension = guess(iis.length());
			}
			Dimension[] dimensions = {dimension};
			ris = new RawImageInputStream(iis, type, index, dimensions);
			reader.setInput(ris, true);
			BufferedImage image=reader.read(0);
			return image;
		} finally {
			if(ris != null) {
				try {
					ris.close();
				} catch (IOException e) {
				}
			}
			if(iis != null) {
				try {
					iis.close();
				} catch (IOException e) {
				}
			}
		}
	}
	private static BufferedImage toRGB(BufferedImage bufferedImage){
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		// 获取图片中每一个rgb像素的int类型表示
		int[] rgbPixels = bufferedImage.getRGB(0, 0, width, height, null, 0, width);
		int[] bgrPixels = new int[rgbPixels.length];
		for (int i = 0; i < rgbPixels.length; i++) {
			int color = rgbPixels[i];
			int red = ((color & 0x00FF0000) >> 16);
			int green = ((color & 0x0000FF00) >> 8);
			int blue = color & 0x000000FF;
			// 将rgb三个通道的数值合并为一个int数值，同时调换b通道和r通道
			bgrPixels[i] = (red & 0x000000FF) | (green << 8 & 0x0000FF00) | (blue << 16 & 0x00FF0000);
		}
		bufferedImage.setRGB(0, 0, width, height, bgrPixels, 0, width);

		return bufferedImage;
	}
}
