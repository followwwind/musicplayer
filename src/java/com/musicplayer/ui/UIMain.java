package com.musicplayer.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import com.musicplayer.bean.Constant;


/**
 * 程序入口
 * @author followwwind
 *
 */
public class UIMain extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainPanel mainPanel;
	private BgPanel bgPanel;
	private JLayeredPane layeredPane;
	public UIMain() {
		layeredPane=new JLayeredPane();
		mainPanel = new MainPanel(this);
		bgPanel = new BgPanel(Constant.background, Constant.WIDTH, Constant.HEIGHT);
		mainPanel.setBounds(0, 0, Constant.WIDTH, Constant.HEIGHT);
		bgPanel.setBounds(0, 0, Constant.WIDTH, Constant.HEIGHT);
		layeredPane.add(bgPanel, new Integer(1));
		layeredPane.add(mainPanel, new Integer(2));
		//this.setLayeredPane(layeredPane);
		this.setIconImage(Constant.app);
		this.getContentPane().add(layeredPane);
		this.setSize(Constant.WIDTH + Constant.WIDTH/55, Constant.HEIGHT + Constant.HEIGHT/15);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new UIMain();
	}
}
