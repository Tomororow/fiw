package com.fourfaith.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证java的一些正则表达式
 * @author administrator
 */
public class ValidateRegular {

	/**
	 * 验证10为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("^\\d{10}$");
		Matcher match = pattern.matcher(str);
		return match.matches();
	}

	/**
	 * 验证手机号
	 */
	public static boolean isMobile(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher match = p.matcher(mobiles);
		return match.matches();
	}

	/**
	 * 最多输入的数字
	 */
	public static boolean isMaxNum(String num) {
		Pattern p = Pattern.compile("^[0-9]*$");
		Matcher match = p.matcher(num);
		if (match.matches()) {
			if (num.length() > 9) {
				return false;
			} else {
				return true;
			}
		}
		return match.matches();
	}

	public static boolean centerVal(String num) {
		Pattern p = Pattern.compile("^\\+?[1-9][0-9]*$");
		Matcher match = p.matcher(num);
		if (match.matches()) {
			if (Integer.parseInt(num) > 255) {
				return false;
			} else {
				return true;
			}
		}
		return match.matches();
	}

	public static void main(String[] args) {
		System.out.print(centerVal("-1"));
	}
	
}