package com.musicplayer.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

import com.musicplayer.bean.Constant;

public class MyScrollBar extends BasicScrollBarUI {
	
	private int width;
	private int height;
	private int barheight;
	
	
	public MyScrollBar(int width, int height, int barheight) {
		this.width = width;
		this.height = height;
		this.barheight = barheight;
	}
	
	@Override
	public Dimension getPreferredSize(JComponent c) {
		
		return new Dimension(width, barheight);
	}
	
	// 重绘滚动条的滑块
	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		// super.paintThumb(g, c, thumbBounds);

		int tw = thumbBounds.width;
		int th = thumbBounds.height;
		// 重定图形上下文的原点，这句一定要写，不然会出现拖动滑块时滑块不动的现象
		g.translate(thumbBounds.x, thumbBounds.y);

		Graphics2D g2 = (Graphics2D) g;
		int orientation = this.scrollbar.getOrientation();
		if (orientation == JScrollBar.VERTICAL) {
			g2.drawImage(Constant.scrollBar, 0, 0, 3, 450, null);
		}else if(orientation == JScrollBar.HORIZONTAL) {
			
		}
	}

	//
	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		
	}
	
	@Override
	protected void paintDecreaseHighlight(Graphics g) {
		
	}
	
	@Override
	protected void paintIncreaseHighlight(Graphics g) {
		
	}
	
	@Override
	protected JButton createIncreaseButton(int orientation) {
		//super.createIncreaseButton(orientation);
		return new BasicArrowButton(orientation){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void paintTriangle(Graphics g, int x, int y, int size, int direction, boolean isEnabled) {
				
			}
			
		};
	}
	
	@Override
	protected JButton createDecreaseButton(int orientation) {
		//super.createDecreaseButton(orientation);
		return new BasicArrowButton(orientation){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void paintTriangle(Graphics g, int x, int y, int size, int direction, boolean isEnabled) {
				
			}
			
		};
	}
}
