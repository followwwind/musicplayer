package com.musicplayer.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;
import org.jaudiotagger.tag.id3.ID3v23Frame;

import com.musicplayer.bean.Song;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;

/**
 * Mp3工具类
 * @author followwwind
 *
 */
public class Mp3Util {

	public static Song getMp3Info(File file) {
		Song song = null;
		FileInputStream fis;
		try {
			
			fis = new FileInputStream(file);
			int b = fis.available();
			Bitstream bt = new Bitstream(fis);
			Header h = bt.readFrame();
			int time = (int) h.total_ms(b);
			long filesize = file.length();
			
			MP3File mp3File = new MP3File(file);
			AbstractID3v2Tag id3v2tag=  mp3File.getID3v2Tag();
			HashMap frameMap = id3v2tag != null ? id3v2tag.frameMap : null;
			String songName = "";
			String artist = "";
			String album = "";
			if(frameMap != null){
				ID3v23Frame tit2 = (ID3v23Frame)frameMap.get("TIT2");
				ID3v23Frame tpe1 = (ID3v23Frame)frameMap.get("TPE1");
				ID3v23Frame talb = (ID3v23Frame)frameMap.get("TALB");
				//System.out.println(tit2.getBody().getObjectValue("Text"));
				//System.out.println(tit2.getBody().getLongDescription());
				AbstractTagFrameBody tit2Body = tit2.getBody();
				AbstractTagFrameBody tpe1Body = tpe1.getBody();
				AbstractTagFrameBody talbBody = talb.getBody();
				Object tit2Obj = tit2Body != null ? tit2Body.getObjectValue("Text") : null;
				Object tpe1Obj = tpe1Body != null ? tpe1Body.getObjectValue("Text") : null;
				Object talbObj = talbBody != null ? talbBody.getObjectValue("Text") : null;
				songName = tit2Obj != null ? tit2Obj.toString() : "";// 歌曲名称
				artist = tpe1Obj != null ? tpe1Obj.toString() : ""; // 歌手名字
				album = talbObj != null ? talbObj.toString() : ""; // 专辑名称
				//System.out.println(songName + "-" + artist + "-" + album);
				//System.out.println("All Info："+mp3File.displayStructureAsPlainText());
			}else{
				songName = file.getName().replace(".mp3", "");
				artist = "未知歌手";
				album = "未知专辑";
			}
			
			song = new Song();
			song.setTitle(songName);
			song.setArtist(artist);
			song.setAlbum(album);
			song.setTime(time);
			song.setFile(file);
			song.setSize(IOUtils.formetFileSize(filesize));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BitstreamException e) {
			e.printStackTrace();
		} catch (TagException e) {
			e.printStackTrace();
		} catch (ReadOnlyFileException e) {
			e.printStackTrace();
		} catch (InvalidAudioFrameException e) {
			e.printStackTrace();
		} 
		return song;
	}

	public static void getHead(String filename) {
		try {
			System.out.println("----------------Loading...Head-----------------");
			MP3File mp3File = new MP3File(filename);// 封装好的类
			MP3AudioHeader header = mp3File.getMP3AudioHeader();
			System.out.println("时长: " + header.getTrackLength()); // 获得时长
			System.out.println("比特率: " + header.getBitRate()); // 获得比特率
			System.out.println("音轨长度: " + header.getTrackLength()); // 音轨长度
			System.out.println("格式: " + header.getFormat()); // 格式，例 MPEG-1
			System.out.println("声道: " + header.getChannels()); // 声道
			System.out.println("采样率: " + header.getSampleRate()); // 采样率
			System.out.println("MPEG: " + header.getMpegLayer()); // MPEG
			System.out.println("MP3起始字节: " + header.getMp3StartByte()); // MP3起始字节
			System.out.println("精确的音轨长度: " + header.getPreciseTrackLength()); // 精确的音轨长度
		} catch (Exception e) {
			System.out.println("没有获取到任何信息");
		}
	}
	
	public static void main(String[] args) {
		getMp3Info(new File("music/爱乐团 - 死而无憾.mp3"));
	}
}
