package com.lin.utils;

import java.io.UnsupportedEncodingException;

public class TranscodeUtil {
	
	public static String transcode(String beforeTran) {
		String afterTran = null;
		try {
			afterTran = new String(beforeTran.getBytes(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return afterTran;
	}
	
	
	public static String transcode(String beforeTran, String beforeCode, String afterCode) {
		String afterTran = null;
		try {
			afterTran = new String(beforeTran.getBytes(beforeCode), afterCode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return afterTran;
	}

}
