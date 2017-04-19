package com.musicplayer.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author followwwind
 *
 */
public class LookAndFeelTest {
	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	private List<JRadioButton> sexs;
	private List<JCheckBox> skills;
	private JComboBox<String> country;
	public void init(){
		frame = new JFrame("swing");
		JPanel panel = new JPanel();
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.add(panel);
		Border panelBorder = BorderFactory.createLineBorder(Color.RED);
		panel.setBorder(BorderFactory.createTitledBorder(panelBorder, "注册", TitledBorder.CENTER, TitledBorder.CENTER));
		GridBagLayout gb = new GridBagLayout();  
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(gb);
		//所有组件都可以在横向、纵向扩大    
        gbc.fill = GridBagConstraints.BOTH;   
        //该GridBagConstraints控制的GUI组件左对齐  
        gbc.anchor = GridBagConstraints.WEST;  
        //该GridBagConstraints控制的GUI组件纵向、横向扩大的权重是1   
        gbc.weighty = 1;   
        gbc.weightx = 1;  
        //该GridBagConstraints控制的GUI组件将横向跨一个网格，纵向跨两个网格    
        //gbc.gridwidth = 1; 
        //gbc.gridheight = 2;   
        
        JLabel userLabel = new JLabel("用户名:", SwingConstants.RIGHT);
        username = new JTextField(15);
        gbc.gridx = 0; 
        gbc.gridy = 0;//第一行，第一列 
        gb.setConstraints(userLabel, gbc);
        gbc.gridx = 1; 
        gbc.gridy = 0;//第一行，第二列 
        gb.setConstraints(username, gbc);
		panel.add(userLabel);
		panel.add(username);
		
		JLabel pwdLabel = new JLabel("密码:", SwingConstants.RIGHT);
		password = new JPasswordField(15);
		gbc.gridx = 0; 
        gbc.gridy = 1;//第二行，第一列 
		gb.setConstraints(pwdLabel, gbc);
		gbc.gridx = 1; 
        gbc.gridy = 1;//第二行，第二列 
        gb.setConstraints(password, gbc);
		panel.add(pwdLabel);
		panel.add(password);
		//frame.setBounds(100, 30, 300, 300);
		
		JLabel sexLabel = new JLabel("性别:", SwingConstants.RIGHT);
		JPanel sexPanel = new JPanel();
		sexPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
		sexs = new ArrayList<JRadioButton>();
		JRadioButton man = new JRadioButton("男");
		man.setSelected(true);
		JRadioButton women = new JRadioButton("女");
		sexs.add(man);
		sexs.add(women);
		ButtonGroup group = new ButtonGroup();// 创建单选按钮组
		group.add(man);
		group.add(women);
		sexPanel.add(man);
		sexPanel.add(women);
		gbc.gridx = 0; 
        gbc.gridy = 2;//第三行，第一列 
		gb.setConstraints(sexLabel, gbc);
		panel.add(sexLabel);
		gbc.gridx = 1; 
        gbc.gridy = 2;//第三行，第二列 
		gb.setConstraints(sexPanel, gbc);
		panel.add(sexPanel);
		
		JLabel skillLabel = new JLabel("技能:", SwingConstants.RIGHT);
		JPanel skillPanel = new JPanel();
		skillPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
		skills = new ArrayList<JCheckBox>();
		JCheckBox jcb1 = new JCheckBox("Java");// 定义一个复选框
		JCheckBox jcb2 = new JCheckBox("C");// 定义一个复选框
		JCheckBox jcb3 = new JCheckBox("C++");// 定义一个复选框
		skills.add(jcb1);
		skills.add(jcb2);
		skills.add(jcb3);
	    skillPanel.add(jcb1);
	    skillPanel.add(jcb2);
	    skillPanel.add(jcb3);
	    gbc.gridx = 0; 
        gbc.gridy = 3;//第四行，第一列 
		gb.setConstraints(skillLabel, gbc);
		panel.add(skillLabel);
		gbc.gridx = 1; 
        gbc.gridy = 3;//第四行，第二列 
		gb.setConstraints(skillPanel, gbc);
		panel.add(skillPanel);
	    
		JLabel contryLabel = new JLabel("国家:", SwingConstants.RIGHT);
		String []countrys = new String[]{"China", "USA", "Korea"};
		country = new JComboBox<String>(countrys);
		gbc.gridx = 0; 
        gbc.gridy = 4;//第五行，第一列 
        gb.setConstraints(contryLabel, gbc);
        gbc.gridx = 1; 
        gbc.gridy = 4;//第五行，第二列 
        gb.setConstraints(country, gbc);
		panel.add(contryLabel);
		panel.add(country);
		
		JPanel submitPanel = new JPanel();
		JButton submit = new JButton("提交");
		JButton reset = new JButton("清空");
		submitPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		submitPanel.add(submit);
		submitPanel.add(reset);
		gbc.gridx = 0; 
        gbc.gridy = 6;//第六行，第一列
        gbc.gridwidth = 2; 
        gb.setConstraints(submitPanel, gbc);
        panel.add(submitPanel);
        
		frame.pack(); 
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = username.getText();
				String pwd = new String(password.getPassword());
				String sex = sexs.get(0).isSelected()? sexs.get(0).getText() : sexs.get(1).getText();
				List<String> skillsStr = new ArrayList<String>();
				for(JCheckBox button : skills){
					boolean selected = button.isSelected();
					if(selected){
						String text = button.getText();
						skillsStr.add(text);
					}
				}
				String c = country.getSelectedItem().toString();
				
				System.out.println("-----用户名:" + name + "------");
				System.out.println("-----密码:" + pwd + "------");
				System.out.println("-----性别:" + sex + "------");
				System.out.println("-----技能:" + skillsStr + "------");
				System.out.println("-----国家:" + c + "------");
				
			}
		});
		
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
	}
	
	public LookAndFeelTest() {
		init();
	}
	
	public void clear(){
		username.setText("");
		password.setText("");
		sexs.get(0).setSelected(true);
		sexs.get(1).setSelected(false);
		for(JCheckBox checkBox : skills){
			checkBox.setSelected(false);
		}
		
		country.setSelectedItem("China");
		
	}
	
	public void setSkins(){
		//String lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
		String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		//String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
		//String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
			SwingUtilities.updateComponentTreeUI(frame);
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
	public static void main(String[] args) {
		new LookAndFeelTest();
	}
}
