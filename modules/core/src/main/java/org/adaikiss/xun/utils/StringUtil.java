package org.adaikiss.xun.utils;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;


public class StringUtil {

	/**
	 * 截断字符串，并且使用ellipsis替换
	 * 
	 * @param str
	 * @param limit
	 *            截取的长度
	 * @param ellipsis
	 *            省略号
	 * @param ellipsisLength
	 *            省略号长度
	 * @return
	 */
	public static String limit(String str, int limit, String ellipsis, int ellipsisLength) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		char[] cs = str.toCharArray();
		int length = 0;
		int ellipsisLimit = limit - ellipsisLength;
		Integer shortPosition = null;
		for (int i = 0; i < cs.length; i++) {
			if (length > ellipsisLimit && null == shortPosition) {
				shortPosition = Math.max(i - 1, 1);
			}
			if (length > limit) {
				return new String(ArrayUtils.addAll(Arrays.copyOfRange(cs, 0, shortPosition), ellipsis.toCharArray()));
			}
			length += length(cs[i]);
		}
		return str;
	}

	/**
	 * 截断字符串，并且使用ellipsis替换
	 * 
	 * @param str
	 * @param limit
	 * @param ellipsis
	 * @return
	 */
	public static String limit(String str, int limit, String ellipsis) {
		return limit(str, limit, ellipsis, length(ellipsis));
	}

	/**
	 * 获取字符串长度, ascII为1，其他为2
	 * 
	 * @param str
	 * @return
	 */
	public static int length(String str) {
		int length = 0;
		for (char c : str.toCharArray()) {
			length += length(c);
		}
		return length;
	}

	/**
	 * 获取字符长度, ascII为1，其他为2
	 * 
	 * @param c
	 * @return
	 */
	private static int length(char c) {
		if (isAscII(c)) {
			return 1;
		}
		return 2;
	}

	/**
	 * 判断一个字符是Ascii字符还是其它字符（如汉，日，韩文字符）
	 * 
	 * @param c
	 *            需要判断的字符
	 * @return 返回true,Ascii字符
	 */
	public static boolean isAscII(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(length("..."));
		System.out.println(limit("你好吗？hellworld!", 3, ".."));
		System.out.println(limit("你好吗？hellworld!", 5, "..."));
		System.out.println(limit("你好吗？hellworld!", 11, "..."));
		System.out.println(limit("你好吗？hellworld!", 15, "..."));
	}

}
