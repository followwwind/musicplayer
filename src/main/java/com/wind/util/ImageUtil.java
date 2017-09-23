package com.wind.util;

import java.awt.Graphics;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtil {
	/**  
     * 截取一个图像的中央区域  
     * @param image 图像File  
     * @param w 需要截图的宽度  
     * @param h 需要截图的高度  
     * @param x 起始x坐标位置
     * @param y 起始y坐标位置
     * @return 返回一个  
     * @throws IOException  
     */  
    public static BufferedImage cutImage(File image, int w, int h, int x, int y) throws IOException {   
           
        // 判断参数是否合法   
        if (null == image || 0 == w || 0 == h) {   
            new Exception ("哎呀，截图出错！！！");   
        }   
        InputStream inputStream = new FileInputStream(image);   
        // 用ImageIO读取字节流   
        BufferedImage bufferedImage = ImageIO.read(inputStream);   
        BufferedImage distin = null;   
        // 返回源图片的宽度。   
        //int srcW = bufferedImage.getWidth();   
        // 返回源图片的高度。   
        //int srcH = bufferedImage.getHeight();   
        // 使截图区域居中   
        /*
        x = srcW / 2 - w / 2;   
        y = srcH / 2 - h / 2;   
        srcW = srcW / 2 + w / 2;   
        srcH = srcH / 2 + h / 2;*/   
        // 生成图片   
        distin = new BufferedImage(w, h, Transparency.TRANSLUCENT);   
        Graphics g = distin.getGraphics();   
        g.drawImage(bufferedImage, 0, 0, w, h, x, y, x + w, h + y, null);
        //ImageIO.write(distin, "png", new File("D:\\333.png"))
        inputStream.close();
        return distin;
    }

  
    public static void main(String[] args) throws Exception {   
        File file = new File("img/collection.png");   
        ImageIO.write(cutImage(file, 27, 36, 0, 37), "png", new File("D:\\1.png"));
        ImageIO.write(cutImage(file, 37, 37, 27, 37), "png", new File("D:\\2.png"));
        ImageIO.write(cutImage(file, 37, 37, 63, 37), "png", new File("D:\\3.png"));
        ImageIO.write(cutImage(file, 27, 36, 99, 37), "png", new File("D:\\4.png"));
    }   
}
