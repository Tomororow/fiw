package com.fourfaith.chargeManage.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.chargeManage.dao.BaseWaterChargeMapper;
import com.fourfaith.chargeManage.model.BaseWaterCharge;
import com.fourfaith.chargeManage.model.BaseWaterChargeVO;
import com.fourfaith.chargeManage.service.BaseWaterChargeService;
import com.fourfaith.paramterManage.model.ParamBaseWaterPrice;
import com.fourfaith.paramterManage.service.ParamBaseWaterPriceService;
import com.fourfaith.sysManage.model.BaseDeviceExpandInfo;
import com.fourfaith.sysManage.model.BaseDeviceInfo;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.service.BaseDeviceExpandInfoService;
import com.fourfaith.sysManage.service.BaseDeviceInfoService;
import com.fourfaith.sysManage.service.SysWaterAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.WaterAreaRecursionUtil;

/**
 * @ClassName: BaseWaterChargeServiceImpl
 * @Description: 基本水费收缴service实现类
 * @Author: zhaojx
 * @Date: 2018年1月13日 下午3:42:28
 */
@Service("BaseWaterChargeService")
public class BaseWaterChargeServiceImpl implements BaseWaterChargeService{

	protected Logger logger = Logger.getLogger(BaseWaterChargeServiceImpl.class);
	
	@Autowired
	private BaseWaterChargeMapper baseWaterChargeMapper;
	@Autowired
	private BaseDeviceExpandInfoService baseDeviceExpandInfoService;
	@Autowired
	private SysWaterAreaService sysWaterAreaService;
	@Autowired
	private BaseDeviceInfoService baseDeviceInfoService;
	@Autowired
	private ParamBaseWaterPriceService paramBaseWaterPriceService;

	@Override
	public Integer getCount(Map<String, Object> params) {
		return baseWaterChargeMapper.getCount(params);
	}

	@Override
	public List<BaseWaterCharge> getList(Map<String, Object> params) {
		return baseWaterChargeMapper.getList(params);
	}

	/**
	 * TODO: 基本水费缴费信息
	 * @param baseWaterCharge
	 * 2018年09月15日
	 * Administrator: zhaojx
	 */
	public String add(BaseWaterCharge baseWaterCharge) {
		String msg = null;
		// 根据当前年份 获取费率信息  保存基本水费缴费信息
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		ParamBaseWaterPrice paramBaseWaterPrice = paramBaseWaterPriceService.findBaseWaterPriceByYear(currentYear);
		try {
			baseWaterCharge.setWaterAreaId(baseWaterCharge.getWaterAreaId().split(",")[baseWaterCharge.getWaterAreaId().split(",").length-1]);
			baseWaterCharge.setChargeMode(1);
			baseWaterCharge.setIsCharge(0);
			// 如果当没有选择到具体某个机井，则将对选择的水管区域下所有的机井信息做新增处理
			if(null==baseWaterCharge.getBaseDeviceId() || "".equals(baseWaterCharge.getBaseDeviceId())) {
				// 根据所选择的水管区域，获取该水管区域及子区域所有的机井实际灌溉面积信息
				// 获取所有水管区域信息
				List<SysWaterArea> sysAllWaterAreaList = sysWaterAreaService.getAllWaterAreaList();
				// 调用递归算法，获取当前所选择的水管区域及子区域信息
				List<SysWaterArea> sysWaterAreaList = WaterAreaRecursionUtil.getCurrAndChildWaterAreaList(sysAllWaterAreaList, baseWaterCharge.getWaterAreaId());
				List<String> waterAreaIds = new ArrayList<String>();
				for(SysWaterArea swa : sysWaterAreaList) {
					waterAreaIds.add(swa.getId());
				}
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("waterAreaIds", waterAreaIds);
				// 根据递归出来的水管区域信息，查询该区域下的所有机井信息
				List<BaseDeviceInfo> baseDeviceInfoList = baseDeviceInfoService.findByWaterAreaId(params);
				if(null==baseDeviceInfoList || baseDeviceInfoList.size()==0) {
					msg = "该水管区域下不存在机井信息";
				} else {
					List<String> deviceIds = new ArrayList<String>();
					for(BaseDeviceInfo bdi : baseDeviceInfoList) {
						deviceIds.add(bdi.getId());
					}
					params.put("deviceIds", deviceIds);
					// 根据设备Id查询出所有的机井实际灌溉面积
					List<BaseDeviceExpandInfo> baseDeviceExpandInfoList = baseDeviceExpandInfoService.findByDeviceIds(params);
					// 循环保存实际灌溉面积的收费信息
					for(BaseDeviceExpandInfo bdei : baseDeviceExpandInfoList) {
						baseWaterCharge.setChargeAmount(bdei.getsJArea().multiply(paramBaseWaterPrice.getStandardPrice()));
						baseWaterCharge.setBaseDeviceId(bdei.getDeviceId());
						try {
							String id = CommonUtil.getRandomUUID();
							baseWaterCharge.setId(id);
							baseWaterChargeMapper.add(baseWaterCharge);
							msg = "添加成功";
						} catch (Exception e) {
							msg = "添加失败";
							e.printStackTrace();
							return msg;
						}
					}
				}
			} else { // 如果选择到对应的机井信息，则将该机井地亩数乘以2即可
				String id = CommonUtil.getRandomUUID();
				baseWaterCharge.setId(id);
				BaseDeviceExpandInfo bdei = baseDeviceExpandInfoService.findById(baseWaterCharge.getBaseDeviceId());
				baseWaterCharge.setChargeAmount(bdei.getsJArea().multiply(paramBaseWaterPrice.getStandardPrice()));
				baseWaterCharge.setBaseDeviceId(bdei.getDeviceId());
				int result = baseWaterChargeMapper.add(baseWaterCharge);
				if(result>0) 
					msg = "添加成功";
				else 
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
	 * TODO: 根据id查询基本收费信息
	 * @param id
	 * 2016年11月28日
	 * Administrator: xiaogaoxiang
	 */
	public BaseWaterCharge findById(String id) {
		return baseWaterChargeMapper.findById(id);
	}

	/**
	 * TODO: 新增收费方式设置
	 * @param baseWaterCharge
	 * 2016年11月28日
	 * Administrator: xiaogaoxiang
	 */
	public String addChargeMode(BaseWaterCharge baseWaterCharge) {
		String msg = null;
		try {
			baseWaterCharge.setIsCharge(1);
			int result = baseWaterChargeMapper.update(baseWaterCharge);
			if(result>0) 
				msg = "支付成功";
			else 
				msg = "支付失败";
			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "支付失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}

	/**
	 * TODO: 删除机井设备收费信息
	 * @param ids
	 * 2016年12月4日
	 * Administrator: xiaogaoxiang
	 */
	public AjaxJson delBaseWaterCharge(String ids) {
		AjaxJson ajaxJson = new AjaxJson();
		String logContent = "";
		// 根据设备机井ID，先检查是否在水权管理中是否包含设备机井引用信息
		if(ids!=null){
			String[] idArray = ids.split(",");
			for (int i = 0; i < idArray.length; i++) {
				logContent = logContent+"删除【机井设备收费信息】数据"+",";
				delete(idArray[i]);
			}
			ajaxJson.setMsg("删除成功");
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}
	
	/**
	 * TODO: 删除操作
	 * @param id
	 * 2016年12月4日
	 * Administrator: xiaogaoxiang
	 */
	public String delete(String id) {
		String msg = null;
		try {
			int result = baseWaterChargeMapper.deleteByPrimaryKey(id);
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
	 * TODO: 根据distType查询所有已缴费信息
	 * @param params
	 * 2016年12月28日
	 * Administrator: xiaogaoxiang
	 */
	public List<BaseWaterCharge> selectWaterChargeList(Map<String, Object> params) {
		return baseWaterChargeMapper.selectWaterChargeList(params);
	}

	@Override
	public BigDecimal getBaseWaterPriceSum(Map<String, Object> params) {
		return baseWaterChargeMapper.getBaseWaterPriceSum(params);
	}

	@Override
	public List<BaseWaterChargeVO> getBaseWaterExcelList(
			Map<String, Object> params) {
		return baseWaterChargeMapper.getBaseWaterExcelList(params);
	}

	@Override
	public BaseWaterCharge getDeviceCodeByMessage(String code) {
		return baseWaterChargeMapper.getDeviceCodeByMessage(code);
	}
	
}