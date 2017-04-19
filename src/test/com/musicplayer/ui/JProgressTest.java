package com.musicplayer.ui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import com.musicplayer.bean.Constant;

public class JProgressTest extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JProgressTest() {
		this.setLayout(null);
		MyProgress myProgress = new MyProgress(Constant.musicProgress, Constant.point);
		myProgress.setBounds(10, 30, 300, 5);
		myProgress.setMinimum(0);
		myProgress.setMaximum(100);
		myProgress.setForeground(Color.RED);
		myProgress.setValue(70);
		myProgress.setOrientation(JProgressBar.HORIZONTAL);
		myProgress.setBorderPainted(false);
		this.add(myProgress);
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new JProgressTest();
	}
}
