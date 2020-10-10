package com.fourfaith.sysManage.dao;

import java.util.List;

import com.fourfaith.sysManage.model.WellRtData;

/**
 * @ClassName: WellRtDataMapper
 * @Description: 恒泽动态信息dao接口
 * @Author: zhaojx
 * @Date: 2017年5月15日 下午7:15:12
 */
public interface WellRtDataMapper {
	
	int insertWellRtData(WellRtData wellRtData);
	
	int deleteByPrimaryKey(String id);

	/**
	 * 获取到所有的有数据的青岛恒泽数据
	 * 2017年4月18日
	 */
	List<WellRtData> selectRtInfo();
	
	/**
	 * @Title: editWellPumpCloseTime
	 * @Description: 定时修改恒泽关泵时间
	 * @param wellRtData
	 * @return: void
	 * @Author: zhaojx
	 * @Date: 2017年7月25日 上午9:42:03
	 */
	void updateWellPumpCloseTime(WellRtData wellRtData);
	
}