package com.fourfaith.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fourfaith.siteManage.model.SysArea;

public class AreaRecursionUtil {

	// 定义递归获取到的行政区域子节点信息集合
	static List<SysArea> sysAreaCurrAndChildList = new ArrayList<SysArea>();
	// 定义递归获取到的行政区域父节点信息集合
	static List<SysArea> sysAreaCurrAndParentList = new ArrayList<SysArea>();
	
	public static List<SysArea> getCurrAndChildAreaList(List<SysArea> sysAllAreaList, String areaId) {
		sysAreaCurrAndChildList = new ArrayList<SysArea>();
		// 递归获取所属当前区域的子节点区域
		for (Iterator<SysArea> iterator = sysAllAreaList.iterator(); iterator.hasNext();) {
			SysArea sysArea = (SysArea) iterator.next();
			// 根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (areaId.equals(sysArea.getId())) {
				// 循环递归获取当前区域下的子区域
				recursionChildFn(sysAllAreaList, sysArea);
			}
		}
		return sysAreaCurrAndChildList;
	}
	
	/**
	 * 循环递归获取当前区域下的子区域
	 * @param list
	 * @param node
	 * 2016年10月23日
	 * Administrator: xiaogaoxiang
	 */
	private static void recursionChildFn(List<SysArea> list, SysArea sysArea) {
		// 得到子节点列表
		List<SysArea> childList = getChildList(list, sysArea);
		// 判断是否有子节点
		if (hasChild(list, sysArea)) {
			// 有子节点，则将子节点信息添加到集合中
			sysAreaCurrAndChildList.add(sysArea);
			// 迭代循环子节点信息
			Iterator<SysArea> it = childList.iterator();
			while (it.hasNext()) {
				SysArea n = (SysArea) it.next();
				// 递归循环添加子节点信息
				recursionChildFn(list, n);
	    	}
        } else {
        	// 如果没有子节点信息，则添加当前的信息，返回
        	sysAreaCurrAndChildList.add(sysArea);
		}
	}
	
	/**
	  * 得到子节点列表
	  * @param list
	  * @param sysArea
	  * 2016年10月23日
	  * Administrator: xiaogaoxiang
	  */
	private static List<SysArea> getChildList(List<SysArea> list, SysArea sysArea) {
		List<SysArea> sysAreaList = new ArrayList<SysArea>();
		Iterator<SysArea> it = list.iterator();
		// 递归循环添加子节点信息
		while (it.hasNext()) {
	     	SysArea s = (SysArea) it.next();
	     	// 如果子节点的父Id 等于 子节点Id，则添加
	    	if (s.getParentAreaId().equals(sysArea.getId())) {
	    		sysAreaList.add(s);
	    	}
		}
       return sysAreaList;
	}
	
	/**
	 * 判断是否有子节点
	 * @param list
	 * @param sysArea
	 * 2016年10月23日
	 * Administrator: xiaogaoxiang
	 */
	private static boolean hasChild(List<SysArea> list, SysArea sysArea) {
		return getChildList(list, sysArea).size() > 0 ? true : false;
	}
	
	public static List<SysArea> getCurrAndParentAreaList(List<SysArea> sysAllAreaList, String parentAreaId) {
		sysAreaCurrAndParentList = new ArrayList<SysArea>();
		// 递归获取所属当前区域的子节点区域
		for (Iterator<SysArea> iterator = sysAllAreaList.iterator(); iterator.hasNext();) {
			SysArea sysArea = (SysArea) iterator.next();
			// 根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (parentAreaId.equals(sysArea.getId())) {
				// 循环递归获取当前区域下的子区域
				recursionParentFn(sysAllAreaList, sysArea);
			}
		}
		return sysAreaCurrAndParentList;
	}
	
	/**
	 * 循环递归获取当前区域下的子区域
	 * @param list
	 * @param node
	 * 2016年10月23日
	 * Administrator: xiaogaoxiang
	 */
	private static void recursionParentFn(List<SysArea> list, SysArea sysArea) {
		// 得到子节点列表
		List<SysArea> childList = getParentList(list, sysArea);
		// 判断是否有子节点
		if (hasParent(list, sysArea)) {
			// 有子节点，则将子节点信息添加到集合中
			sysAreaCurrAndParentList.add(sysArea);
			// 迭代循环子节点信息
			Iterator<SysArea> it = childList.iterator();
			while (it.hasNext()) {
				SysArea n = (SysArea) it.next();
				// 递归循环添加子节点信息
				recursionParentFn(list, n);
	    	}
        } else {
        	// 如果没有父节点信息，则添加当前的信息，返回
        	sysAreaCurrAndParentList.add(sysArea);
		}
	}
	
	/**
	  * 得到父节点列表
	  * @param list
	  * @param sysArea
	  * 2016年10月23日
	  * Administrator: xiaogaoxiang
	  */
	private static List<SysArea> getParentList(List<SysArea> list, SysArea sysArea) {
		List<SysArea> sysAreaList = new ArrayList<SysArea>();
		Iterator<SysArea> it = list.iterator();
		// 递归循环添加父节点信息
		while (it.hasNext()) {
	     	SysArea s = (SysArea) it.next();
	     	// 如果子节点的父Id 等于 子节点Id，则添加
	    	if (s.getId().equals(sysArea.getParentAreaId())) {
	    		sysAreaList.add(s);
	    	}
		}
       return sysAreaList;
	}
	
	/**
	 * 判断是否有子节点
	 * @param list
	 * @param sysArea
	 * 2016年10月23日
	 * Administrator: xiaogaoxiang
	 */
	private static boolean hasParent(List<SysArea> list, SysArea sysArea) {
		return getParentList(list, sysArea).size() > 0 ? true : false;
	}
	
}