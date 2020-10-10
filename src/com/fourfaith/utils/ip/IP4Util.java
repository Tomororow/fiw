package com.fourfaith.utils.ip;

/**
 * @ClassName: IP4Util
 * @Description: IP4工具类
 */
public class IP4Util {
	
	public static byte[] toBytes(int address) {
		return new byte[] {
				(byte) ((address >>> 24) & 0xFF),
				(byte) ((address >>> 16) & 0xFF),
				(byte) ((address >>>  8) & 0xFF),
				(byte) ((address       ) & 0xFF)
		};
	}

	private IP4Util() {
	}
	
}