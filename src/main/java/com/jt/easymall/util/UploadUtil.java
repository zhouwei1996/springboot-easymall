package com.jt.easymall.util;



public class UploadUtil {
	
	public static String getUploadPath(String fileName,String upload){
		
		//分目录存�?,计算存储路径
		String hash = Integer.toHexString(fileName.hashCode());
		while(hash.length()<8){
			hash += "0";
		}
		for (int i = 0; i < hash.length(); i++) {
			upload += "/"+hash.charAt(i);
		}
		
		
		return upload;
	}
}
