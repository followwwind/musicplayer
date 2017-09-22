package com.wind.ui;

import com.wind.bean.Const;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;



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
		bgPanel = new BgPanel(Const.background, Const.WIDTH, Const.HEIGHT);
		mainPanel.setBounds(0, 0, Const.WIDTH, Const.HEIGHT);
		bgPanel.setBounds(0, 0, Const.WIDTH, Const.HEIGHT);
		layeredPane.add(bgPanel, new Integer(1));
		layeredPane.add(mainPanel, new Integer(2));
		//this.setLayeredPane(layeredPane);
		this.setIconImage(Const.app);
		this.getContentPane().add(layeredPane);
		this.setSize(Const.WIDTH + Const.WIDTH/55, Const.HEIGHT + Const.HEIGHT/15);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new UIMain();
	}
}
