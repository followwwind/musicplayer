package com.musicplayer.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JProgressBar;

public class MyProgress extends JProgressBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage bg;

	public MyProgress() {
		
	}
	
	public MyProgress(BufferedImage bg, BufferedImage point) {
		this.bg = bg;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setClip(new RoundRectangle2D.Double(0, 0, 300, 5, 5, 5));
		g2.drawImage(bg, 0, 0, 300, 5, null);
	}
}
