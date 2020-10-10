package com.fourfaith.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: FileUtils
 * @Description: springmvc文件上传工具类
 * @Author: zhaojx
 */
public class FileUtils {

	public static void fileUpload(String filePath, MultipartFile fileInfo, String deviceAreaCode) {
		// 源文件名称
		String oriName = fileInfo.getOriginalFilename();
		// 获取图片后缀
		String extName = oriName.substring(oriName.lastIndexOf("."));
    	File file = new File(filePath, deviceAreaCode+extName);
		if(!file.exists()) {
			file.mkdirs(); 
		}
		try {
			fileInfo.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
    }
	
}