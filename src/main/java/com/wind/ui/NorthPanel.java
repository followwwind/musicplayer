package com.wind.ui;

import com.wind.bean.Const;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class NorthPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel title;
	public NorthPanel() {
		title = new JLabel("Wind、Player");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("bold", Font.BOLD, 27));
		
		add(title);
		setOpaque(false);
		setPreferredSize(new Dimension(this.getWidth(), Const.HEIGHT/12));
		setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(168,31,31)));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(Const.footer, 0, 0, null);
	}
	
}
