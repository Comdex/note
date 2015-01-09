package com.honeyhaw.note.mvc.utils;

import java.io.UnsupportedEncodingException;

public class StringUtils {

	/**
	 * 截取字符串
	 * 
	 * @param str
	 *            需要截取的字符串
	 * @param count
	 *            截取字符的长度
	 * @throws UnsupportedEncodingException
	 */
	public static String subStringCharacter(String str, int count)
			throws UnsupportedEncodingException {
		int num = 0;// 已经截取字符的长度
		int length = 0;// 每个字符的长度
		StringBuffer sb = new StringBuffer();
		char ch[] = str.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			length = String.valueOf(ch[i]).getBytes("utf-8").length;
			num += length;
			if (num > count) {
				break;
			}
			sb.append(ch[i]);
		}
		return sb.toString();
	}
}
