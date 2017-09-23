package com.wind.ui.component;

import com.wind.bean.Const;
import com.wind.bean.Player;
import com.wind.bean.Song;
import com.wind.util.DateUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class SouthPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel musicname;
	private JLabel player;
	private JLabel pre;
	private JLabel next;
	private JPanel btnPanel;
	private JPanel musicBar;
	private JLabel starttime;
	private JLabel endtime;
	private JProgressBar progressBar;
	private Player musicplayer;
	private List<Player> players;
	private Song song;
	private List<Song> songs = new ArrayList<Song>();
	private int index;
	private int currenttime = 0;
	private Timer timer;
	/** 歌曲播放状态 */
	private boolean status = false;

	public void initPanel() {
		musicname = new JLabel();
		musicname.setForeground(Color.WHITE);

		player = new JLabel(new ImageIcon(Const.player), SwingConstants.CENTER);
		pre = new JLabel(new ImageIcon(Const.pre), SwingConstants.CENTER);
		next = new JLabel(new ImageIcon(Const.next), SwingConstants.CENTER);
		// player.setForeground(Color.RED);
		progressBar = new JProgressBar(0, 100);
		progressBar.setPreferredSize(new Dimension(300, 5));
		progressBar.setBackground(Color.BLACK);
		progressBar.setForeground(Color.RED);
		//progressBar.setValue(70);
		starttime = new JLabel("00:00");
		starttime.setForeground(Color.WHITE);
		endtime = new JLabel("00:00");
		endtime.setForeground(Color.WHITE);

		setLayout(new BorderLayout());

		btnPanel = new JPanel();
		musicBar = new JPanel();
		btnPanel.setOpaque(false);
		musicBar.setOpaque(false);

		btnPanel.add(musicname);
		btnPanel.add(pre);
		btnPanel.add(player);
		btnPanel.add(next);
		btnPanel.add(starttime);
		btnPanel.add(progressBar);
		btnPanel.add(endtime);

		add(btnPanel, BorderLayout.CENTER);
		add(musicBar, BorderLayout.EAST);
		setOpaque(false);

		players = new ArrayList<Player>();

		player.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(musicplayer != null){
					if (status) {
						status = false;
						player.setIcon(new ImageIcon(Const.player2));
						musicplayer.pauseplay();
						timer.stop();
					} else {
						status = true;
						player.setIcon(new ImageIcon(Const.pause2));
						musicplayer.startplay();
						if (!musicplayer.isStart() && musicplayer.existFile()) {
							musicplayer.start();
						}
						timer.start();
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (status) {
					player.setIcon(new ImageIcon(Const.pause2));
				} else {
					player.setIcon(new ImageIcon(Const.player2));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (status) {
					player.setIcon(new ImageIcon(Const.pause));
				} else {
					player.setIcon(new ImageIcon(Const.player));
				}
			}

		});

		pre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pre.setIcon(new ImageIcon(Const.pre2));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pre.setIcon(new ImageIcon(Const.pre));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				index--;
				if(index < 0){
					index = songs.size() - 1;
				}
				
				if(index < songs.size() && songs.size() > 0){
					initSong(songs.get(index));
				}
			}
		});

		next.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				next.setIcon(new ImageIcon(Const.next2));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				next.setIcon(new ImageIcon(Const.next));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				index++;
				if(index >= songs.size()){
					index = 0;
				}
				
				if(index < songs.size() && songs.size() > 0){
					initSong(songs.get(index));
				}
			}
		});

	}

	public SouthPanel() {
		initPanel();
	}

	@Override
	protected void paintComponent(Graphics g) {
//		g.drawImage(Const.footer, 0, 0, null);
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song, int index) {
		this.song = song;
		this.index = index;
		initSong(song);
	}
	
	public void initSong(Song song){
		player.setIcon(new ImageIcon(Const.player));
		if(musicplayer != null && musicplayer.isStart()){
			musicplayer.stopplay();
			if(timer != null){
				timer.stop();
			}
		}
		status = false;
		musicplayer = new Player(song.getFile());
		musicname.setText(song.getTitle());
		starttime.setText("00:00");
		endtime.setText(DateUtil.msToMinute(song.getTime()));
		progressBar.setValue(0);
		progressBar.setMaximum(song.getTime()/1000);
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currenttime++;
				if (currenttime * 1000 <= song.getTime()) {
					starttime.setText(DateUtil.msToMinute(currenttime*1000));
					progressBar.setValue(currenttime);
				}else{
					timer.stop();
					currenttime = 0;
					starttime.setText("00:00");
					player.setIcon(new ImageIcon(Const.player));
					status = false;
					musicplayer = new Player(song.getFile());
					progressBar.setValue(0);
				}
			}
		});
		currenttime = 0;
		// musicplayer.setMusicfile(song.getFile());
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

}
