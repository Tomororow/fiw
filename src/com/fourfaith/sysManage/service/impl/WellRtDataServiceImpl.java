package com.fourfaith.sysManage.service.impl;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.sysManage.dao.WellRtDataMapper;
import com.fourfaith.sysManage.model.WellRtData;
import com.fourfaith.sysManage.service.WellRtDataService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: WellRtDataServiceImpl
 * @Description: 恒泽动态
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:45:42
 */
@Service("WellRtDataService")
public class WellRtDataServiceImpl implements WellRtDataService {
	protected Logger logger = Logger.getLogger(WellRtDataServiceImpl.class);
	
	@Autowired
	private WellRtDataMapper wellRtDataMapper;
	
	@Override
	public String add(WellRtData wellRtData) {
		String msg = null;
		try {
			int result = wellRtDataMapper.insertWellRtData(wellRtData);
			if(result>0){
				msg = "添加成功";
			}else{
			    msg = "添加失败";
			}
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "添加失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public AjaxJson delWellRtData(String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		String logContent = "";
		if(ids!=null){
			String[] idArray = ids.split(",");
			for (int i = 0; i < idArray.length; i++) {
				logContent = logContent+"删除机井编码【"+idArray[i]+"】的实时数据表数据"+",";
				delete(idArray[i]);
			}
			ajaxJson.setMsg("删除成功");
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}
	
	@Override
	public String delete(String id) {
		String msg = null;
		try {
			int result = wellRtDataMapper.deleteByPrimaryKey(id);
			if(result>0){
				msg = "删除成功";
			}else{
			    msg = "删除失败";
			}
			logger.info(msg);			
		}catch (Exception e) {
			e.printStackTrace();
			msg = "删除失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}

	/**
     * 获取到所有的有数据的青岛恒泽数据
     * @return
     * 2017年4月18日
     */
	public List<WellRtData> selectRtInfo() {
		return wellRtDataMapper.selectRtInfo();
	}

	@Override
	public void editWellPumpCloseTime(WellRtData wellRtData) {
		wellRtDataMapper.updateWellPumpCloseTime(wellRtData);
	}
	
}