package com.fourfaith.chargeManage.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fourfaith.chargeManage.model.BaseWaterCharge;
import com.fourfaith.chargeManage.model.BaseWaterChargeVO;

/**
 * @ClassName: BaseWaterChargeMapper
 * @Description: 基本水费收缴dao接口
 * @Author: zhaojx
 * @Date: 2018年2月13日 上午11:10:23
 */
public interface BaseWaterChargeMapper {

	/**
	 * @Title: getCount
	 * @Description: 统计信息数量
	 * @param: @param params
	 * @return: Integer
	 */
	Integer getCount(Map<String, Object> params);

	/**
	 * @Title: getList
	 * @Description: 获取水费收缴列表
	 * @param: @param params
	 * @return: List<BaseWaterCharge>
	 */
	List<BaseWaterCharge> getList(Map<String, Object> params);

	/**
	 * TODO: 保存方法
	 * @param baseWaterCharge
	 * 2016年11月28日
	 * Administrator: xiaogaoxiang
	 */
	int add(BaseWaterCharge baseWaterCharge);

	/**
	 * TODO: 根据id查询基本收费信息
	 * @param id
	 * 2016年11月28日
	 * Administrator: xiaogaoxiang
	 */
	BaseWaterCharge findById(String id);

	/**
	 * TODO: 新增收费方式设置
	 * @param baseWaterCharge
	 * 2016年11月28日
	 * Administrator: xiaogaoxiang
	 */
	int update(BaseWaterCharge baseWaterCharge);

	/**
	 * TODO: 删除操作
	 * @param id
	 * 2016年12月4日
	 * Administrator: xiaogaoxiang
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * TODO: 根据distType查询所有已缴费信息
	 * @param params
	 * 2016年12月28日
	 * Administrator: xiaogaoxiang
	 */
	List<BaseWaterCharge> selectWaterChargeList(Map<String, Object> params);
	
	/**
	 * @Title: getBaseWaterPriceSum
	 * @Description: 按条件获取基本水费总和
	 * @param: @param params
	 * @return: BigDecimal
	 * @Author: zhaojinxin
	 */
	BigDecimal getBaseWaterPriceSum(Map<String, Object> params);
	
	/**
	 * @Title: getBaseWaterExcelList
	 * @Description: 获取其他费用导出报表
	 * @return: List<BaseWaterChargeVO>
	 * @Author: zhaojx
	 * @Date: 2017年9月8日
	 */
	List<BaseWaterChargeVO> getBaseWaterExcelList(Map<String, Object> params);
	
	/**
	 * @Title: getDeviceCodeByMessage
	 * @Description:根据机井编号查询：村号、村名、户号、户名、充值次数、总购金额、用户剩余金额 
	 * @param code
	 * @return
	 * @return BaseWaterCharge
	 * @author 刘海年
	 * @date 2018年9月4日下午3:07:28
	 */
	BaseWaterCharge getDeviceCodeByMessage(String code);

}