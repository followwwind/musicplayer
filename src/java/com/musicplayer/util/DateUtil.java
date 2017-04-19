package com.musicplayer.util;

/**
 * 日期转换工具
 * @author followwwind
 *
 */
public class DateUtil {
	
	public static String msToMinute(int ms){
		int minute = ms/1000/60;
		int second = ms/1000%60;
		return (minute < 10 ? "0" + minute : minute) + ":" + (second < 10 ? "0" + second : second);
	}
	
}
