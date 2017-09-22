package com.wind.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;

public class IOUtils {
	
	public static void close(OutputStream out){
		if(out != null){
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String formetFileSize(long fileS) {//转换文件大小
       DecimalFormat df = new DecimalFormat("#.00");
       String fileSizeString = "";
       if (fileS < 1024) {
           fileSizeString = df.format((double) fileS) + "B";
       } else if (fileS < 1048576) {
           fileSizeString = df.format((double) fileS / 1024) + "K";
       } else if (fileS < 1073741824) {
           fileSizeString = df.format((double) fileS / 1048576) + "M";
       } else {
           fileSizeString = df.format((double) fileS / 1073741824) +"G";
       }
       return fileSizeString;
    }
}
