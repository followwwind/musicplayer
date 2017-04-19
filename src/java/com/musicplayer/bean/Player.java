package com.musicplayer.bean;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player extends Thread{

	/**
	 * 播放标志
	 */
	private boolean flag = false;
	private boolean pause = false; //停止标志
	private boolean threadflag = false; //该线程是否启动
	private AudioInputStream audioInputStream;// 文件流
    private AudioFormat audioFormat;// 文件格式
    private SourceDataLine sourceDataLine;// 输出设备
    Object lock = new Object();//一个空的对象,没什么意义
    private File musicfile;
    
    public Player() {
		
	}
    
	public Player(File musicfile) {
		this.musicfile = musicfile;
	}
	
	public boolean existFile(){
		return musicfile != null ? true : false;
	}
	
	public void loadMusicFile(){
		// 取得文件输入流
        try {
			audioInputStream = AudioSystem.getAudioInputStream(musicfile);
			audioFormat = audioInputStream.getFormat();
	        // 转换mp3文件编码
	        if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
	            audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
	                    audioFormat.getSampleRate(), 16, audioFormat
	                            .getChannels(), audioFormat.getChannels() * 2,
	                    audioFormat.getSampleRate(), false);
	            audioInputStream = AudioSystem.getAudioInputStream(audioFormat,
	                    audioInputStream);
	        }
	        // 打开输出设备
	        DataLine.Info dataLineInfo = new DataLine.Info(
	                SourceDataLine.class, audioFormat,
	                AudioSystem.NOT_SPECIFIED);
	        sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
	        sourceDataLine.open(audioFormat);
	        
        } catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
	}
	
	public void startplay(){
		if(pause){
			synchronized(lock) {
                pause = false;
                lock.notifyAll();
            }
		}
		flag = true;
	}
	
	public void pauseplay(){
		pause = true;
	}
	
	public void stopplay(){
		flag = false;
	}
	
	@Override
	public void run() {
		loadMusicFile();
		threadflag = true;
		byte[] data = new byte[4096];
        int nBytesRead;
        if(sourceDataLine != null){
        	synchronized (lock) {
                try {
					while ((nBytesRead = audioInputStream.read(data, 0, data.length)) != -1) {
						while(pause){
							if(sourceDataLine.isRunning()) {
								sourceDataLine.stop();
	                            //System.out.println("暂停");
	                        }
	                        try {
	                            lock.wait();
	                            //System.out.println("等待");
	                        }catch(InterruptedException e) {
	                        	
	                        }
						}
						
						if(flag){
							sourceDataLine.start();
						}else{
							sourceDataLine.drain();
							sourceDataLine.stop();
							sourceDataLine.close();
						}
						sourceDataLine.write(data, 0, nBytesRead);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
	}
	
	public boolean isStart(){
		return threadflag;
	}

	public File getMusicfile() {
		return musicfile;
	}

	public void setMusicfile(File musicfile) {
		this.musicfile = musicfile;
	}

}
