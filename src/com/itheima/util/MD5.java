package com.itheima.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5 {
	public static String encode(String value){
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte b[] = md.digest(value.getBytes());
			BASE64Encoder base64 = new BASE64Encoder();
			return base64.encode(b);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException();
		}
		
	}
}
