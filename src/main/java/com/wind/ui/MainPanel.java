package com.wind.ui;

import com.wind.ui.component.CenterPanel;
import com.wind.ui.component.NorthPanel;
import com.wind.ui.component.SouthPanel;

import java.awt.BorderLayout;

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

	public void initComponent(){

		setLayout(new BorderLayout());
		southPanel = new SouthPanel();
		northPanel = new NorthPanel();
		centerPanel = new CenterPanel(southPanel);
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}
	
	public MainPanel(JFrame frame) {
		initComponent();
	}
}
