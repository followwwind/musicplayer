package com.wind.ui;

import com.wind.bean.Const;

import javax.swing.*;


/**
 * 程序入口
 * @author followwwind
 *
 */
public class UIWindow2 extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainPanel mainPanel;
	private BgPanel bgPanel;
	private JLayeredPane layeredPane;
	public UIWindow2() {
		layeredPane=new JLayeredPane();
		mainPanel = new MainPanel(this);
		bgPanel = new BgPanel(Const.background, Const.WIDTH, Const.HEIGHT);
		mainPanel.setBounds(0, 0, Const.WIDTH, Const.HEIGHT);
		bgPanel.setBounds(0, 0, Const.WIDTH, Const.HEIGHT);
//		layeredPane.add(bgPanel, new Integer(1));
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
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel("com.bulenkov.darcula.DarculaLaf");
					new UIWindow2();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
