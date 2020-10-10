package com.fourfaith.alarmManage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.alarmManage.dao.IntelligentDealMapper;
import com.fourfaith.alarmManage.model.IntelligentDeal;
import com.fourfaith.alarmManage.service.IntelligentDealService;
import com.fourfaith.utils.CommonUtil;

import common.Logger;

/**
 * @ClassName: IntelligentDealServiceImpl
 * @Description: 异常机井处理结果service实现
 * 2017年1月12日
 * Administrator: xiaogaoxiang
 */
@Service("IntelligentDealService")
public class IntelligentDealServiceImpl implements IntelligentDealService{

	protected Logger logger = Logger.getLogger(IntelligentDealServiceImpl.class);
	
	@Autowired
	private IntelligentDealMapper intelligentDealMapper;

	/**
	 * TODO: 新增异常机井处理结果信息
	 * @param intelligentDeal
	 */
	public String add(IntelligentDeal intelligentDeal) {
		String msg = null;
		try {
			intelligentDeal.setId(CommonUtil.getRandomUUID());
			int result = intelligentDealMapper.add(intelligentDeal);
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

	/**
	 * TODO: 根据异常机井表ID查询异常机井处理结果
	 * @param intelligentAnalysisId
	 */
	public IntelligentDeal selectByIntelligentAnalysisId(String intelligentAnalysisId) {
		return intelligentDealMapper.selectByIntelligentAnalysisId(intelligentAnalysisId);
	}

	/**
	 * TODO: 如果处理结果为未解决，则删除之前信息，重新添加
	 * @param ids
	 */
	public void delete(IntelligentDeal ids) {
		intelligentDealMapper.delete(ids);
	}

	/**
	 * TODO: 如果处理结果为待解决，则修改其时间
	 * @param intelligentDeal
	 */
	public String update(IntelligentDeal intelligentDeal) {
		String msg = null;
		try {
			int result = intelligentDealMapper.update(intelligentDeal);
			if(result>0){
				msg = "处理成功";
			}else{
			    msg = "处理失败";
			}
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "处理失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}

	/**
	 * 统计异常机井处理结果数量
	 */
	@Override
	public Integer getCount(Map<String, Object> params) {
		return intelligentDealMapper.getCount(params);
	}

	/**
	 * 获取异常机井处理结果列表
	 */
	@Override
	public List<IntelligentDeal> getList(Map<String, Object> params) {
		return intelligentDealMapper.getList(params);
	}
	
}