package com.wind.ui;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class BgPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private int width;
	private int height;
	
	public BgPanel() {
		
	}
	
	public BgPanel(BufferedImage image, int width, int height) {
		this.image = image;
		this.width = width;
		this.height = height;
		//this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, width, height, null);
	}
}
