package com.fourfaith.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 字符串工具类
 */
public class StringUtils {

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		boolean flag = true;
		if (str != null && !str.trim().equals("")) {
			flag = false;
		}
		return flag;
	}

	/**
	 * md5加密
	 * @param str
	 * @return
	 */
	public static String encryptMd5(String str) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuilder build = new StringBuilder("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					build.append("0");
				build.append(Integer.toHexString(i));
			}

			re_md5 = build.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;
	}

	/**
	 * 生成随机码
	 * @param length 长度
	 * @param onlyDigital 是否为纯数字,false 则生成a-z A-Z 0-9随机码
	 * @return
	 */
	public static String getRandomStr(int length, boolean onlyDigital) {
		char mapStr[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
				'8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', '0' };
		char mapint[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		String str = "";

		if (onlyDigital) {
			for (int i = 0; i < length; i++) {
				int count = (int) (mapint.length * Math.random());
				if (count >= mapint.length) {
					str += mapint[mapint.length - 1];
				} else {
					str += mapint[count];
				}
			}
		} else {
			for (int i = 0; i < length; i++) {
				int count = (int) (mapStr.length * Math.random());
				if (count >= mapStr.length) {
					str += mapStr[mapStr.length - 1];
				} else {
					str += mapStr[count];
				}

			}
		}
		return str;
	}

	/**
	 * list元素集用split分隔开
	 * @param split
	 * @param elementList
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String join(String split, List elementList) {
		StringBuffer result = new StringBuffer();
		if (elementList != null && elementList.size() > 0) {
			for (int i = 0; i < elementList.size(); i++) {
				result.append(elementList.get(i));
				if (i != elementList.size() - 1) {
					result.append(split);
				}
			}
		}
		return result.toString();
	}

	/**
	 * 判断container是否有包含数组中的元素
	 * @param container url
	 * @param regex 过滤的数组
	 * @return
	 */
	public static boolean contains(String container, String[] regex) {
		for (String str : regex) {
			if (container.contains(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断数组中元素是否包含regex字符串
	 * @param container
	 * @param regex
	 * @return
	 */
	public static boolean contains(String[] container, String regex) {
		for (String str : container) {
			if (str.equals(regex)) {
				return true;
			}
		}
		return false;
	}

}