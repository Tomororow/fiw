package com.fourfaith.utils;

import java.util.Comparator;

/**
 * @ClassName: MapKeyComparator
 * @Description: 比较器类  
 */
public class MapKeyComparator implements Comparator<String>{  
    
	public int compare(String str1, String str2) {  
        return str1.compareTo(str2);  
    }  
	
}