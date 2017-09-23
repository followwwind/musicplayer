package com.wind.ui.component;

import com.wind.bean.Const;
import com.wind.bean.Song;
import com.wind.ui.MyScrollBar;
import com.wind.ui.UIWindow;
import com.wind.util.Mp3Util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;


public class CenterPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel menu;
	private JScrollPane content;
	private FileDialog fload;
	private List<Song> songs = new ArrayList<Song>();
	private SouthPanel southPanel;
	private String keyword = "搜索歌曲...";

	public CenterPanel(SouthPanel southPanel) {
		this.southPanel = southPanel;
		menu = new JPanel();
		content = new JScrollPane();
		menu.setPreferredSize(new Dimension(Const.WIDTH/4, this.getHeight()));
		menu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
		menu.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 5));

		initLeftMenu();
		initContent();

		menu.setOpaque(false);
		content.setOpaque(false);
		content.setBorder(BorderFactory.createEmptyBorder());
		content.getViewport().setOpaque(false);
		setLayout(new BorderLayout());
		add(menu, BorderLayout.WEST);
		add(content, BorderLayout.CENTER);
		setOpaque(false);
		fload = new FileDialog(UIWindow.frame, "打开文件", FileDialog.LOAD);
	}

	public void initLeftMenu() {
		// 创建根节点和子节点
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("");

		DefaultMutableTreeNode music = new DefaultMutableTreeNode("我的音乐");
		DefaultMutableTreeNode nativemusic = new DefaultMutableTreeNode("本地音乐");
		DefaultMutableTreeNode download = new DefaultMutableTreeNode("下载管理");

		DefaultMutableTreeNode musiclist = new DefaultMutableTreeNode("歌单");
		DefaultMutableTreeNode albummusic = new DefaultMutableTreeNode("Music");
		DefaultMutableTreeNode albumfav = new DefaultMutableTreeNode("Favorite");
		// 利用根节点创建treemodel
		DefaultTreeModel treemodel = new DefaultTreeModel(root);
		// 插入子节点
		treemodel.insertNodeInto(music, root, root.getChildCount());
		treemodel.insertNodeInto(nativemusic, music, music.getChildCount());
		treemodel.insertNodeInto(download, music, music.getChildCount());

		treemodel.insertNodeInto(musiclist, root, root.getChildCount());
		treemodel.insertNodeInto(albummusic, musiclist, musiclist.getChildCount());
		treemodel.insertNodeInto(albumfav, musiclist, musiclist.getChildCount());
		JTree leftmenu = new JTree(treemodel);
		leftmenu.setOpaque(false);
		// cellRender.setLeafIcon(new ImageIcon(Const.pre2));

		// 去掉连线
		leftmenu.putClientProperty("JTree.lineStyle", "None");
		leftmenu.setRowHeight(30);
		leftmenu.setRootVisible(false);
		expandAll(leftmenu, new TreePath(root), true);

		// leftmenu.setForeground(Color.WHITE);
		// leftmenu.expandPath(new TreePath(root));
		// leftmenu.setShowsRootHandles(true);
		leftmenu.setCellRenderer(new DefaultTreeCellRenderer() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
					boolean leaf, int row, boolean hasFocus) {
				super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
				String nodename = value.toString();
				ImageIcon icon = new ImageIcon();
				/*
				 * if("本地音乐".equals(nodename)){ icon.setImage(Const.pre2);
				 * }else if("下载管理".equals(nodename)){
				 * icon.setImage(Const.next2); }else
				 * if("我的音乐".equals(nodename)){ icon = new ImageIcon(); }else
				 * if("歌单".equals(nodename)){ icon = new ImageIcon(); }else{
				 * icon = new ImageIcon(); }
				 */
				setIcon(icon);
				setForeground(Color.WHITE);
				setOpenIcon(new ImageIcon());
				setClosedIcon(new ImageIcon());
				setBackgroundNonSelectionColor(new Color(0, 0, 0, 0));
				setBackgroundSelectionColor(Color.gray);
				return this;
			}

		});
		menu.add(leftmenu);

	}

	/**
	 * 展开或者关闭
	 * 
	 * @param tree
	 * @param parent
	 * @param expand
	 */
	private void expandAll(JTree tree, TreePath parent, boolean expand) {
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() > 0) {
			for (Enumeration<?> e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAll(tree, path, expand);

			}
		}
		if (expand) {
			tree.expandPath(parent);
		} else {
			tree.collapsePath(parent);
		}
	}

	public void initContent() {
		String[] columnNames = { "音乐标题", "歌手", "专辑", "时长", "大小" };
		Object[][] cellData = {};
		DefaultTableModel tableModel = new DefaultTableModel(cellData, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable table = new JTable(tableModel);
		table.setOpaque(false);
		table.setShowGrid(false);
		table.getTableHeader().setReorderingAllowed(false);

		table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				// setOpaque(false);
				setHorizontalAlignment(SwingConstants.CENTER);
				setForeground(Color.WHITE);
				setBackground(new Color(22, 24, 28));
				setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
				return this;
			}

		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				System.out.println(row);
				Song s = songs.get(row);
				southPanel.setSong(s, row);
				// setRowBackgroundColor(table, row, Color.RED);
			}
		});

		// table.setShowGrid(false);
		// table.setShowHorizontalLines(false);
		// table.setShowVerticalLines(false);
		table.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		// table.setEnabled(false);

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			/**
			 * 
			 */

			public final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				setHorizontalAlignment(SwingConstants.CENTER);
				setForeground(Color.WHITE);
				// setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0,
				// 0)));
				setOpaque(false);
				// DEFAULT_RENDERER.setBorder(BorderFactory.createEmptyBorder());
				return this;
			}

		});
		JPanel panel = new JPanel();
		JPanel center = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				//g.drawImage(Const.splash, 0, 0, null);
			}
		};
		JScrollPane south = new JScrollPane();
		JPanel north = new JPanel();
		north.setPreferredSize(new Dimension(content.getWidth(), 50));
		panel.setOpaque(false);
		center.setOpaque(false);
		south.setOpaque(false);
		north.setOpaque(false);
		/*
		 * MyScrollBar scrollBar = new MyScrollBar(3, 30, content.getHeight());
		 * center.getVerticalScrollBar().setUI(scrollBar);
		 */
		// center.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// center.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		south.getViewport().setOpaque(false);
		south.setViewportView(table);
		south.setBorder(BorderFactory.createEmptyBorder());

		//center.setPreferredSize(new Dimension(content.getWidth(), 10));
		center.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		center.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		
		JButton add = new JButton("添加歌曲");
		JButton delete = new JButton("删除歌曲");
		JTextField queryText = new JTextField();
		/*add.setForeground(Color.BLUE);
		add.setBackground(new Color(35, 35, 38));
		
		delete.setForeground(Color.BLUE);
		delete.setBackground(new Color(35, 35, 38));*/
		
		queryText.setText(keyword);
		queryText.setPreferredSize(new Dimension(150, 24));
		/*queryText.setForeground(Color.BLUE);
		queryText.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		queryText.setBackground(new Color(35, 35, 38));*/
		
		JPanel centerleft = new JPanel();
		JPanel centerright = new JPanel();
		centerleft.setOpaque(false);
		centerright.setOpaque(false);
		centerleft.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		centerleft.add(add);
		centerleft.add(delete);
		centerright.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		centerright.add(queryText);
		center.setLayout(new BorderLayout());
		center.add(centerleft, BorderLayout.WEST);
		center.add(centerright, BorderLayout.CENTER);
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fload.setVisible(true);
				String d = fload.getDirectory();
				String f = fload.getFile();
				if ((d != null) && (f != null)) {
					Song song = Mp3Util.getMp3Info(new File(d + f));
					if (song != null) {
						songs.add(song);
						tableModel.addRow(song.getTableRow());
						southPanel.setSongs(songs);
						// table.setRowSelectionInterval(0, 0);
					}
				}
				// table.setModel(tableModel);
			}
		});
		
		queryText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				queryText.setText("");
				keyword = "";
			}
		});
		
		queryText.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				//System.out.println(queryText.getText());	
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				System.out.println(queryText.getText());
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				//System.out.println(queryText.getText());	
			}
		});
		
		/*queryText.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				keyword += c;
				//queryText.setText(keyword);
				System.out.println(keyword);
			}
		});*/
		
		JLabel musictable = new JLabel("歌曲");
		JLabel lyric = new JLabel("歌词");
		JLabel musicalbum = new JLabel("歌单");
		JLabel singer = new JLabel("歌手");
//		musictable.setForeground(Color.WHITE);
		musictable.setFont(new Font("BOLD", Font.BOLD, 16));
//		lyric.setForeground(Color.GRAY);
		lyric.setFont(new Font("BOLD", Font.BOLD, 16));
//		musicalbum.setForeground(Color.GRAY);
		musicalbum.setFont(new Font("BOLD", Font.BOLD, 16));
//		singer.setForeground(Color.GRAY);
		singer.setFont(new Font("BOLD", Font.BOLD, 16));

		JPanel lyricPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				// g.drawImage(Const.splash, 0, 0, null);
			}
		};
		JLabel lyricLabel = new JLabel();
		// lyricLabel.setSize(50, 0);//注意JLabel一定要设置宽度
		lyricLabel.setText("<html>" + "<head>" + "<style type='text/css'>" + "p:hover{color:red;font-size:14}"
				+ "</style>" + "</head>" + "<body style='text-align:center;font-size:12;color:green;'>"
				+ "<p onMouseOver='this.style.color=red;'>风沙漫延 扰乱晴天 丹心照明月</p>" + "<p>遥望城外 兵器相见 浮生又一劫</p>"
				+ "<p>君独守皇宫已非昨日威严 谁在此哽咽</p>" + "<p>故人一直就站在君的面前 不问也不怨</p>" +

				"<p>君本意欲寿与天齐 留万代功名</p>" + "<p>故人西辞不问情意 有何难说明</p>" + "<p>打乱了君一统天下的约定 谁可以同行</p>"
				+ "<p>原来不需要用战争去平定 要先得人心</p>" + "<p>故人发已衰白 风尘覆盖 不奢求重来</p>" + "<p>只盼君能收起战台 断头换不来</p>"
				+ "<p>最后的城墙破开 登高望海 一片烟火海</p>" + "<p>无能为力 尸遍满地 故人心已远</p>" +

				"<p>君本意欲寿与天齐 留万代功名</p>" + "<p>故人西辞不问情意 有何难说明</p>" + "<p>打乱了君一统天下的约定 谁可以同行</p>"
				+ "<p>原来不需要用战争去平定 要先得人心</p>" + "<p>故人发已衰白 风尘覆盖 不奢求重来</p>" + "<p>只盼君能收起战台 断头换不来</p>"
				+ "<p>最后的城墙破开 登高望海 一片烟火海</p>" + "<p>无能为力 尸遍满地 故人心已远</p>" +

				"<p>手一挥 膝一跪 拿玉杯赐天下无罪</p>" + "<p>没有人 喊万岁 只有故人看君落泪</p>" + "<p>君萧萧 拨剑鞘 还以为就此一了百了</p>"
				+ "<p>人在生 责在身 与谁同归都不可能</p>" + "<p>故人发已衰白 风尘覆盖 不奢求重来</p>" + "<p>只盼君能收起战台 断头换不来</p>"
				+ "<p>最后的城墙破开 登高望海 一片烟火海</p>" + "<p>无能为力 尸遍满地 故人心已远</p>" + "<p>发已衰白 风尘覆盖 不奢求重来</p>"
				+ "<p>只盼君能收起战台 断头换不来</p>" + "<p>最后的城墙破开 登高望海 一片烟火海</p>" + "<p>无能为力 尸遍满地 故人心已远</p>" + "</body></html>");
		lyricLabel.setForeground(Color.WHITE);
		lyricPane.add(lyricLabel);
		lyricPane.setOpaque(false);

		musictable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				panel.removeAll();
				panel.add(north, BorderLayout.NORTH);
				panel.add(center, BorderLayout.CENTER);
				panel.add(south, BorderLayout.SOUTH);
				panel.repaint();
				content.repaint();
				content.revalidate();
				content.updateUI();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				musictable.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				musictable.setForeground(Color.GRAY);
			}
		});

		lyric.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				panel.removeAll();
				panel.add(north, BorderLayout.NORTH);
				panel.add(lyricPane, BorderLayout.CENTER);
				panel.repaint();
				content.repaint();
				content.revalidate();
				content.updateUI();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lyric.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lyric.setForeground(Color.GRAY);
			}
		});

		musicalbum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				musicalbum.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				musicalbum.setForeground(Color.GRAY);
			}
		});

		singer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				singer.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				singer.setForeground(Color.GRAY);
			}
		});

		north.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
		north.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		north.add(musictable);
		north.add(lyric);
		north.add(musicalbum);
		north.add(singer);

		panel.setLayout(new BorderLayout());
		panel.add(north, BorderLayout.NORTH);
		panel.add(center, BorderLayout.CENTER);
		panel.add(south, BorderLayout.SOUTH);
		// panel.setBorder(null);
		/*
		 * UIManager.put("TabbedPane.contentOpaque", false); JTabbedPane tabpane
		 * = new JTabbedPane(JTabbedPane.TOP);
		 * tabpane.setTabLayoutPolicy(JTabbedPane.CENTER);
		 * tabpane.setBorder(null); tabpane.setUI(new MyTabbedPaneUI("#E0EEEE",
		 * "#E0EEEE")); tabpane.addTab("musiclist", panel); JPanel lyric = new
		 * JPanel(); lyric.setOpaque(false); tabpane.addTab("lyric", lyric);
		 * tabpane.setOpaque(false);
		 */
		MyScrollBar scrollBar = new MyScrollBar(3, 30, content.getHeight());
		content.getVerticalScrollBar().setUI(scrollBar);
		content.setViewportView(panel);
	}

	public void setRowBackgroundColor(JTable table, int rowIndex, Color color) {
		try {
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {

				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					setHorizontalAlignment(SwingConstants.CENTER);
					setForeground(Color.WHITE);
					setOpaque(false);
					/*
					 * if (row == rowIndex) { //setBackground(color); //
					 * setForeground(Color.WHITE); }else{
					 * setForeground(Color.WHITE); }
					 */
					return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				}
			};
			int columnCount = table.getColumnCount();
			for (int i = 0; i < columnCount; i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}

			table.repaint();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
