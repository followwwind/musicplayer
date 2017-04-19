package com.musicplayer.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class JFrameBgTest extends JFrame {

	// 创建一个JLayeredPane用于分层的。
	JLayeredPane layeredPane;
	// 创建一个Panel和一个Label用于存放图片，作为背景。
	JPanel jp;
	JLabel jl;
	ImageIcon image;

	// 创建一个按钮用于测试的。
	JButton jb;

	public static void main(String[] args) {
		new JFrameBgTest();
	}

	public JFrameBgTest() {
		layeredPane = new JLayeredPane();
		image = new ImageIcon("img/splash.png");// 随便找一张图就可以看到效果。
		// 创建背景的那些东西
		jp = new JPanel();
		//System.out.println(image.getIconWidth());
		jp.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
		jl = new JLabel(image);
		// jl.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
		jp.add(jl);

		// 创建一个测试按钮
		jb = new JButton("测试按钮");
		jb.setBounds(100, 100, 100, 100);

		// 将jp放到最底层。
		layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
		// 将jb放到高一层的地方
		layeredPane.add(jb, JLayeredPane.MODAL_LAYER);

		this.setLayeredPane(layeredPane);
		this.setSize(image.getIconWidth(), image.getIconHeight());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
