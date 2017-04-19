package com.musicplayer.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Splash2Test {
	JDialog splashDialog;
	SplashPanel SplashPanel;
	protected void initSplash(){
		splashDialog = new JDialog();
		try {
			SplashPanel = new SplashPanel(ImageIO.read(new File("img/splash.jpg")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		splashDialog.add(SplashPanel);
		splashDialog.setVisible(true);
		splashDialog.setSize(500, 300);
		splashDialog.setLocationRelativeTo(null);
		//SplashPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final String[] stages = {"正在启动", "正在读取数据", "正在加载相关文档", "启动完成"};  
        int stage = 0;  
        //在下面你初始化程序的过程中调用updateSplash来更新splash  
        for (int i = 0; i <= 100; i += 1) {  
            String status = stages[stage];  
            SplashPanel.setStatus(status, i); 
            SplashPanel.repaint();
            if (i == 30) {  
                stage = 1;  
            } else if (i == 70) {  
                stage = 2;  
            } else if (i == 90) {  
                stage = 3;  
            }  
            try {  
                //故意等待  
                Thread.sleep(100);  
            } catch (Exception e) {  
                //异常不做处理  
            }  
        }  
        
        if(splashDialog != null){
        	splashDialog.dispose();
        	splashDialog = null;
        }
        
        //System.out.println(SplashPanel);
	}
	
	public static void main(String[] args) {
		new Splash2Test().initSplash();
	}
}

class SplashPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rectangle splashBounds;
	private String status;
	private int progress;
	private BufferedImage image;
	
	public SplashPanel() {
		splashBounds = new Rectangle(400, 10);
	}
	
	public SplashPanel(BufferedImage image) {
		this();
		this.image = image;
	}
	
	
	public void setStatus(String status, int progress){
		this.status = status;
		this.progress = progress;
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, null);// 画背景
		drawSplash((Graphics2D)g);
	}
	
	/** 
     * 这个方法画一个进度条，不再详述。 
     */  
    protected void drawSplash(Graphics2D splashGraphics) {  
        int barWidth = splashBounds.width ;//进度条长度  
        splashGraphics.setComposite(AlphaComposite.Clear);  
        //splashGraphics.fillRect(1, 10, splashBounds.width - 2, 20);//闪屏边框  
        splashGraphics.setPaintMode();//模型  
        splashGraphics.setColor(Color.ORANGE);//字符串颜色  
        splashGraphics.drawString(status, 10, 50);//画字符串  
        splashGraphics.setColor(Color.red);//进 度条填充颜色  
        int width = progress * barWidth / 100;//进度条当前值  
        splashGraphics.fillRect(45, 200, width, 6);  
        
        //splashGraphics.drawImage(image, 0, 0, 500, 300, null);
    }
	
}
