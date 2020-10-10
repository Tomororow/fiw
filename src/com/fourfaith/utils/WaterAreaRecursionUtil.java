package com.fourfaith.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fourfaith.sysManage.model.SysWaterArea;

/**
 * @ClassName: WaterAreaRecursionUtil
 * @Description: 水管区域工具类
 * @Author: zhaojx
 */
public class WaterAreaRecursionUtil {

	// 定义递归获取到的水管区域子节点信息集合
	static List<SysWaterArea> sysWaterAreaCurrAndChildList = new ArrayList<SysWaterArea>();
	
	// 定义递归获取到的水管区域父节点信息集合
	static List<SysWaterArea> sysWaterAreaCurrAndParentList = new ArrayList<SysWaterArea>();

	public static List<SysWaterArea> getCurrAndChildWaterAreaList(List<SysWaterArea> sysAllWaterAreaList, String waterAreaId) {
		sysWaterAreaCurrAndChildList = new ArrayList<SysWaterArea>();
		for (Iterator<SysWaterArea> iterator = sysAllWaterAreaList.iterator(); iterator.hasNext();) {
			SysWaterArea sysWaterArea = (SysWaterArea) iterator.next();
			// 根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (waterAreaId.equals(sysWaterArea.getId())) {
				// 循环递归获取当前区域下的子区域
				recursionChildFn(sysAllWaterAreaList, sysWaterArea);
			}
		}
		return sysWaterAreaCurrAndChildList;
	}

	/**
	 * TODO:循环递归获取当前区域下的子区域 
	 * @param list
	 * @param node
	 * 2016年10月23日
	 * Administrator: xiaogaoxiang
	 */
	private static void recursionChildFn(List<SysWaterArea> list, SysWaterArea sysWaterArea) {
		// 得到子节点列表
		List<SysWaterArea> childList = getChildList(list, sysWaterArea);
		// 判断是否有子节点
		if (hasChild(list, sysWaterArea)) {
			// 有子节点，则将子节点信息添加到集合中
			sysWaterAreaCurrAndChildList.add(sysWaterArea);
			// 迭代循环子节点信息
			Iterator<SysWaterArea> it = childList.iterator();
			while (it.hasNext()) {
				SysWaterArea n = (SysWaterArea) it.next();
				// 递归循环添加子节点信息
				recursionChildFn(list, n);
			}
		} else {
			// 如果没有子节点信息，则添加当前的信息，返回
			sysWaterAreaCurrAndChildList.add(sysWaterArea);
		}
	}

	/**
	 * TODO: 得到子节点列表
	 * @param list
	 * @param sysArea
	 * @return 2016年10月23日
	 */
	private static List<SysWaterArea> getChildList(List<SysWaterArea> list,
			SysWaterArea sysWaterArea) {
		List<SysWaterArea> sysWaterAreaList = new ArrayList<SysWaterArea>();
		Iterator<SysWaterArea> it = list.iterator();
		// 递归循环添加子节点信息
		while (it.hasNext()) {
			SysWaterArea s = (SysWaterArea) it.next();
			// 如果子节点的父Id 等于 子节点Id，则添加
			if (s.getParentWaterAreaId().equals(sysWaterArea.getId())) {
				sysWaterAreaList.add(s);
			}
		}
		return sysWaterAreaList;
	}

	/**
	 * TODO: 判断是否有子节点 
	 * @param list
	 * @param sysArea
	 * @return 2016年10月23日
	 */
	private static boolean hasChild(List<SysWaterArea> list,
			SysWaterArea sysWaterArea) {
		return getChildList(list, sysWaterArea).size() > 0 ? true : false;
	}

	public static List<SysWaterArea> getCurrAndParentWaterAreaList(
			List<SysWaterArea> sysAllWaterAreaList, String parentWaterAreaId) {
		sysWaterAreaCurrAndParentList = new ArrayList<SysWaterArea>();
		for (Iterator<SysWaterArea> iterator = sysAllWaterAreaList.iterator(); iterator
				.hasNext();) {
			SysWaterArea sysWaterArea = (SysWaterArea) iterator.next();
			// 根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (parentWaterAreaId.equals(sysWaterArea.getId())) {
				// 循环递归获取当前区域下的子区域
				recursionParentFn(sysAllWaterAreaList, sysWaterArea);
			}
		}
		return sysWaterAreaCurrAndParentList;
	}

	/**
	 * TODO: 循环递归获取当前区域下的子区域 
	 * @param list
	 * @param node
	 * 2016年10月23日
	 * Administrator: xiaogaoxiang
	 */
	private static void recursionParentFn(List<SysWaterArea> list,
			SysWaterArea sysWaterArea) {
		// 得到子节点列表
		List<SysWaterArea> childList = getParentList(list, sysWaterArea);
		// 判断是否有子节点
		if (hasParent(list, sysWaterArea)) {
			// 有子节点，则将子节点信息添加到集合中
			sysWaterAreaCurrAndParentList.add(sysWaterArea);
			// 迭代循环子节点信息
			Iterator<SysWaterArea> it = childList.iterator();
			while (it.hasNext()) {
				SysWaterArea n = (SysWaterArea) it.next();
				// 递归循环添加子节点信息
				recursionParentFn(list, n);
			}
		} else {
			// 如果没有子节点信息，则添加当前的信息，返回
			sysWaterAreaCurrAndParentList.add(sysWaterArea);
		}
	}

	/**
	 * TODO: 得到子节点列表
	 * @param list
	 * @param sysArea
	 * @return 2016年10月23日
	 * Administrator: xiaogaoxiang
	 */
	private static List<SysWaterArea> getParentList(List<SysWaterArea> list,
			SysWaterArea sysWaterArea) {
		List<SysWaterArea> sysWaterAreaList = new ArrayList<SysWaterArea>();
		Iterator<SysWaterArea> it = list.iterator();
		// 递归循环添加子节点信息
		while (it.hasNext()) {
			SysWaterArea s = (SysWaterArea) it.next();
			// 如果子节点的父Id 等于 子节点Id，则添加
			if (s.getId().equals(sysWaterArea.getParentWaterAreaId())) {
				sysWaterAreaList.add(s);
			}
		}
		return sysWaterAreaList;
	}

	/**
	 * TODO: 判断是否有子节点
	 * @param list
	 * @param sysArea
	 * @return 2016年10月23日 
	 * Administrator: xiaogaoxiang
	 */
	private static boolean hasParent(List<SysWaterArea> list,
			SysWaterArea sysWaterArea) {
		return getParentList(list, sysWaterArea).size() > 0 ? true : false;
	}
	
}