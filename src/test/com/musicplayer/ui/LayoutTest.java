package com.musicplayer.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LayoutTest {
	
	public void init(){
		JFrame frame = new JFrame();
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		JPanel cwest = new JPanel();
		JPanel ccenter = new JPanel();
		center.setLayout(new BorderLayout());
		center.add(cwest, BorderLayout.WEST);
		center.add(ccenter, BorderLayout.CENTER);
		
		frame.setLayout(new BorderLayout());
		frame.add(north, BorderLayout.NORTH);
		frame.add(center, BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
		int width = screensize.width - 340;
		int height = screensize.height - 110;
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new LayoutTest().init();
	}
}
