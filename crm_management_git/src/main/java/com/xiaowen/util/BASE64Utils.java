package com.xiaowen.util;

import java.util.Base64;

public class BASE64Utils {

	//BASE64 加密
	public static String encryptBASE64(String key) {
		return Base64.getEncoder().encodeToString(key.getBytes());
	}
	
	//BASE64 加密
	public static String encryptBASE64(Integer key) {
		return Base64.getEncoder().encodeToString(key.toString().getBytes());
	}
	
	//BASE64 解密
	public static String decryptBASE64(String key) {
		byte[] decode = Base64.getDecoder().decode(key);
		String decodeStr = new String(decode);
		return decodeStr;
	}
}
