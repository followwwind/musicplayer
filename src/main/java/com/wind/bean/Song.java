package com.wind.bean;

import com.wind.util.DateUtil;

import java.io.File;


public class Song {
	//歌曲名称
	private String title;
	//时长 毫秒
	private int time;
	//大小
	private String size;
	//歌手
	private String artist;
	//专辑
	private String album;
	
	private File file;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Object[] getTableRow(){
		return new Object[]{title, artist, album, DateUtil.msToMinute(time), size};
	}
}
