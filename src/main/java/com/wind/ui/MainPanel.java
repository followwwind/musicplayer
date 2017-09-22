package com.wind.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NorthPanel northPanel;
	private CenterPanel centerPanel;
	private SouthPanel southPanel;
	private JFrame frame;
	public void initComponent(){
		setLayout(new BorderLayout());
		southPanel = new SouthPanel();
		northPanel = new NorthPanel();
		centerPanel = new CenterPanel(frame, southPanel);
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		//setBorder(BorderFactory.createLineBorder(Color.RED));
		setOpaque(false);
	}
	
	public MainPanel(JFrame frame) {
		this.frame = frame;
		initComponent();
	}
}
