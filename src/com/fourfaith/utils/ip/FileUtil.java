package com.fourfaith.utils.ip;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName: FileUtil
 * @Description: 文件工具类
 * @Author: zhaojx
 */
public class FileUtil {
	
	public static Path classpath(String name) {
        try {
            //final URL url = Class.class.getResource(name);
            final URL url = FileUtil.class.getResource(name);
            return (url != null) ? Paths.get(url.toURI()) : null;
        } catch (URISyntaxException e) {
            return null;
        }
    }
 
    private FileUtil() {
    }
    
}