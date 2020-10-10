package com.fourfaith.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



/**
 * 获取properties配置文件属性值
 * @author administrator
 */
public class PropertiesUtils {

	public static String PATH = "/web.properties";

	public static String SAFETYPATH = "/safety.properties";

	private static Properties properties;
	static {
		try {
			InputStream is = PropertiesUtils.class.getResourceAsStream(PATH);
			InputStream safety = PropertiesUtils.class
					.getResourceAsStream(SAFETYPATH);
			properties = new Properties();
			properties.load(is);
			properties.load(safety);
			if (is != null)
				is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * getPara 获取properties文件里值
	 * @param propertiesPath
	 * @param key
	 */
	public static String getPara(String propertiesPath, String key) {
		return properties.getProperty(key);
	}

	public static String getPara(String key) {
		return properties.getProperty(key);
	}
	/**
	 * getPara 获取properties文件里值
	 * @param propertiesPath
	 * @param key
	 * @throws IOException 
	 */
	public String setPara(String key, String value) throws IOException {
		String message = "success";
		properties.setProperty(key,value);
		File file = new File(this.getClass().getResource("/").getPath());
        FileOutputStream outputFile = new FileOutputStream(file.getPath()+PATH);
        try {
			properties.save(outputFile,"");
		} catch (Exception e) {
			message = "error";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				outputFile.close();
				   outputFile.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return message;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		//PropertiesUtils.setPara("sumWater","2");
	}
	
}