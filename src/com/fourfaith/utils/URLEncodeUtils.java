package com.fourfaith.utils;

import org.apache.log4j.Logger;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @ClassName: URLEncodeUtils
 * @Description: URL编码工具类
 * @Author: zhaojx
 * @Date: 2017年8月25日 上午9:45:55
 */
public class URLEncodeUtils {
    
	public static Logger logger = Logger.getLogger(URLEncodeUtils.class);
	
	/**
     * URLEncoded编码工具类
     * @param paramString
     */
    public static String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
        	logger.info("toURLEncoded error:"+paramString);
            return "";
        }

        try{
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        }
        catch (Exception localException){
        	logger.info("toURLEncoded error:"+paramString, localException);
        }
        return "";
    }

    /**
     * URLDecode解码工具类
     * @param paramString
     * @return
     */
    public static String toURLDecoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
        	logger.info("toURLDecoded error:"+paramString);
            return "";
        }
        try{
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLDecoder.decode(str, "UTF-8");
            return str;
        }
        catch (Exception localException){
        	logger.info("toURLDecoded error:"+paramString, localException);
        }
        return "";
    }
    
}