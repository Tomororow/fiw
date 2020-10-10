package com.fourfaith.sysManage.service;

import java.util.List;

import com.fourfaith.sysManage.model.WellRtData;
import com.fourfaith.utils.AjaxJson;

/**
 * WellRtData interface
 * @author Dan
 * TODO: 恒泽实时用水表service接口
 */
public interface WellRtDataService{

    String add(WellRtData wellRtData);
    
    AjaxJson delWellRtData(String ids);
    
    String delete(String id);

    /**
     * TODO: 获取到所有的有数据的青岛恒泽数据
     * 2017年4月18日
     * Administrator: xiaogaoxiang
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
	void editWellPumpCloseTime(WellRtData wellRtData);
	
}