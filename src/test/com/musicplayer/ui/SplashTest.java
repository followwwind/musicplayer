package com.musicplayer.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.SplashScreen;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class SplashTest {
	//在这儿你能获得通过-splash:设置给程序的splash screen的实例  
    private final SplashScreen splash = SplashScreen.getSplashScreen(); 
    private Rectangle splashBounds;  
    private Graphics2D splashGraphics;  
  
    /** 
     * 初始化splash 
     */  
    protected void initSplash() {  
        if (splash != null) {  
        	try {
        		URL fileUrl = new URL("file:///E:/JavaDevelopment/JavaCode/JavaGUI/MusicPlayer/img/splash.jpg");
				splash.setImageURL(fileUrl);
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
            //下面给splash screen画一个边框  
            splashBounds = splash.getBounds();  
            //在这儿初始化图形对象，该图形对象取自splash  
            splashGraphics = (Graphics2D) splash.createGraphics();  
            if (splashGraphics != null) {  
                splashGraphics.setColor(Color.BLUE);  
                splashGraphics.drawRect(0, 0, splashBounds.width - 1, splashBounds.height - 1);  
            }  
        }  
    }  
  
    /** 
     * 更新splash，这个方法 
     */  
    protected void updateSplash(String status, int progress) {  
        if (splash == null || splashGraphics == null) {  
            return;  
        }  
        //重画splash上面的进度并通知splash更新界面  
        drawSplash(splashGraphics, status, progress);  
        splash.update();  
    }  
  
    /** 
     * 这个方法画一个进度条，不再详述。 
     */  
    protected void drawSplash(Graphics2D splashGraphics, String status, int progress) {  
        int barWidth = splashBounds.width;//进度条长度  
        splashGraphics.setComposite(AlphaComposite.Clear);  
        splashGraphics.fillRect(1, 10, splashBounds.width - 2, 20);//闪屏边框  
        splashGraphics.setPaintMode();//模型  
        splashGraphics.setColor(Color.ORANGE);//字符串颜色  
        splashGraphics.drawString(status, 10, 20);//画字符串  
        splashGraphics.setColor(Color.red);//进 度条填充颜色  
        int width = progress * barWidth / 100;//进度条当前值  
        splashGraphics.fillRect(0, splashBounds.height - 20, width, 6);  
    }  
  
    /** 
     * 这儿是加载程序的过程 
     */  
    protected void loadApplication() {  
        //初始化splash  
        initSplash();  
        //模拟的加载进度提示信息  
        final String[] stages = {"正在启动", "正在读取数据", "正在加载相关文档", "启动完成"};  
        int stage = 0;  
        //在下面你初始化程序的过程中调用updateSplash来更新splash  
        for (int i = 0; i <= 100; i += 1) {  
            String status = stages[stage];  
            if (splash != null) {  
                //更新闪屏图像  
                updateSplash(status, i);  
            }  
            if (i == 30) {  
                stage = 1;  
            } else if (i == 70) {  
                stage = 2;  
            } else if (i == 90) {  
                stage = 3;  
            }  
            try {  
                //故意等待  
                Thread.sleep(20);  
            } catch (Exception e) {  
                //异常不做处理  
            }  
        }  
        /*try {  
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
        } catch (Exception e) { 
        	
        }  */
        JFrame window = new JFrame();  
        //在这儿你可以主动关闭splash  
        if (splash != null) {  
            splash.close();  
        }  
        window.setSize(300, 500);
        //真正开始的你的登录界面或者主窗口         
        window.setVisible(true); 
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }  
	public static void main(String[] args) {
		//System.out.println(SplashTest.class.getResource("/"));
		new SplashTest().loadApplication();
	}
}
