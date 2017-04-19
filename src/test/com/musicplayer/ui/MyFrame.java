package com.musicplayer.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MyFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JButton button;

	public MyFrame() {
		setSize(new Dimension(400, 300));
		setLayout(new BorderLayout());
		panel = new JPanel();
		JScrollPane js = new JScrollPane(panel);
		panel.setBackground(Color.RED);
		// js.setPreferredSize(new Dimension(100,80));
		getContentPane().add(js, BorderLayout.CENTER);
		button = new JButton("Zoom in");
		add(button, BorderLayout.SOUTH);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = MyFrame.this.getPanel().getWidth();
				int y = MyFrame.this.getPanel().getHeight() + 20;
				getPanel().setPreferredSize(new Dimension(x, y));
				getPanel().setSize(getPanel().getPreferredSize());
				// MyFrame.this.repaint();
			}

		});
		getContentPane().add(button, BorderLayout.SOUTH);
		setVisible(true);
	}

	public JPanel getPanel() {
		return this.panel;
	}

	public static void main(String[] args) {
		new MyFrame();
	}
}
