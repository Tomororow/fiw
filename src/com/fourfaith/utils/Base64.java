package com.fourfaith.utils;

import java.io.UnsupportedEncodingException;
import sun.misc.*;  
  
public class Base64 {  
    /**
     * @Title: getBase64
     * @Description: 加密
     * @param str
     * @return
     * @return String
     * @author 刘海年
     * @date 2018年10月15日上午8:34:34
     */
    public static String getBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }  
  
    /**
     * @Title: getFromBase64
     * @Description:解密
     * @param s
     * @return
     * @return String
     * @author 刘海年
     * @date 2018年10月15日上午8:34:55
     */
    public static String getFromBase64(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8");
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }
        return result;  
    }  
    
    public static void main(String[] args) {
    	getBase64("http://61.178.178.92:9098/remote/remoteRequestOperate.do?deviceCode=6207250099&isType=cc&command=6201101000040101&operType=5031&strToACSII=6201101000040101");
    	getFromBase64("");
	}

}  
