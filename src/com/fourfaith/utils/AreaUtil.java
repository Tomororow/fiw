package com.fourfaith.utils;

import java.util.ArrayList;
import java.util.List;

import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.sysManage.model.SysWaterArea;

/**
 * @ClassName: AreaUtil
 * @Description: 区域工具类
 */
public class AreaUtil {

	static List<SysArea> sysAreaList = new ArrayList<SysArea>();
	static List<SysWaterArea> sysWaterAreaList = new ArrayList<SysWaterArea>();

	public static List<SysArea> getChildArea(List<SysArea> childSysAreaList,
			String areaId) {
		// 遍历出父id等于参数的id，add进子节点集合
		for (SysArea sysArea : childSysAreaList) {
			if (areaId.equals(sysArea.getParentAreaId())) {
				sysAreaList.add(sysArea);
				// 递归遍历下一级
				getChildArea(childSysAreaList, sysArea.getId());
			}
		}
		return sysAreaList;
	}

	public static void emptyAreaList() {
		sysAreaList.clear();
	}

	public static List<SysWaterArea> getWaterChildArea(
		List<SysWaterArea> childSysWaterAreaList, String id) {
		// 遍历出父id等于参数的id，add进子节点集合
		for (SysWaterArea sysWaterArea : childSysWaterAreaList) {
			if (id.equals(sysWaterArea.getParentWaterAreaId())) {
				sysWaterAreaList.add(sysWaterArea);
				// 递归遍历下一级
				getWaterChildArea(childSysWaterAreaList, sysWaterArea.getId());
			}
		}
		return sysWaterAreaList;
	}

	public static void emptyWaterAreaList() {
		sysWaterAreaList.clear();
	}
	
}