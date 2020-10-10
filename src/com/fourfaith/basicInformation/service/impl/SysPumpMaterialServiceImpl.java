package com.fourfaith.basicInformation.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.basicInformation.dao.SysPumpMaterialMapper;
import com.fourfaith.basicInformation.model.SysPumpMaterial;
import com.fourfaith.basicInformation.service.SysPumpMaterialService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysPumpMaterialServiceImpl
 * @Description: 泵管材质service实现类
 * @Author: zhaojx
 */
@Service("sysPumpMaterialService")
public class SysPumpMaterialServiceImpl implements SysPumpMaterialService {
	
	protected Logger logger = Logger.getLogger(SysPumpMaterialServiceImpl.class);
	
	@Autowired
	private SysPumpMaterialMapper sysPumpMaterialMapper;
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysPumpMaterialMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysPumpMaterial> getList(Map<String, Object> params) {
		return sysPumpMaterialMapper.getList(params);
	}

	public int deleteByPrimaryKey(String id) {
		int result = sysPumpMaterialMapper.deleteByPrimaryKey(id);
		return result;
	}

	public int insert(SysPumpMaterial record) {
		int result = sysPumpMaterialMapper.insert(record);
		return result;
	}
	
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysPumpMaterialMapper.deleteByPrimaryKey(id);
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
	
	public int insertSelective(SysPumpMaterial record) {
		int result = sysPumpMaterialMapper.insertSelective(record);
		return result;
	}

	public SysPumpMaterial selectByPrimaryKey(String id) {
		SysPumpMaterial entity = sysPumpMaterialMapper.selectByPrimaryKey(id);
		return entity;
	}

	public SysPumpMaterial findById(String Id) {
		return sysPumpMaterialMapper.selectByPrimaryKey(Id);
	}

	public String add(SysPumpMaterial model) {
		String msg = null;
		// 增加泵管名唯一性校验（新增），只需要根据输入的泵管名称来检验是否存在即可
		List<SysPumpMaterial> spm = sysPumpMaterialMapper.uniqueAddSelective(model);
		/**
		 *  1、如果查询到的泵管名存在，则提示该泵管名已存在；
		 *  2、如果查询到的泵管名不存在，则执行新增操作
		 *  	A、如果新增成功（无异常），则提示添加成功
		 *  	B、如果新增失败（异常），则提示添加失败
		 */
		if(null==spm || spm.size()==0) {
			int result = sysPumpMaterialMapper.insertSelective(model);
			if(result>0){
				msg = "添加成功";
			}else{
			    msg = "添加失败";
			}
		} else {
			msg = "该泵管名已存在";
		}
		logger.info(msg);
		return msg;
	}

	@Transactional(rollbackFor=Exception.class)
	public AjaxJson deleteDis(String items) {
		AjaxJson  ajaxJson = new AjaxJson();
		String logContent = "";
		try{
			if(items!=null){
				String [] itemArray=items.split(",");
	    		for(String item:itemArray)
	    		{
	    			String id = item;
	    			//直接删除
	    			SysPumpMaterial plan = findById(id);
	    			logContent = logContent+"删除【"+plan.getPumpMaterial()+"】的数据"+",";
	    			delete(id);
	    		}
	    		ajaxJson.setMsg("删除成功");
				ajaxJson.setSuccess(true);
				ajaxJson.setObj(logContent);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			ajaxJson.setMsg("操作失败，异常信息："+ex.getMessage());
			ajaxJson.setSuccess(false);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}
	
	public String update(SysPumpMaterial sysPumpMaterial) {
		String msg = null;
		try {
			// 增加泵管名唯一性校验（修改），只需要根据输入的泵管名称来检验是否存在即可
			List<SysPumpMaterial> spm = sysPumpMaterialMapper.uniqueUpdateSelective(sysPumpMaterial);
			/**
			 *  1、如果查询到的泵管名存在，则提示该泵管名已存在；
			 *  2、如果查询到的泵管名不存在，则执行新增操作
			 *  	A、如果新增成功（无异常），则提示添加成功
			 *  	B、如果新增失败（异常），则提示添加失败
			 */
			if(null==spm || spm.size()==0) {
				int result = sysPumpMaterialMapper.updateByPrimaryKeySelective(sysPumpMaterial);
				if(result>0){
				   msg = "更新成功";
				}else{
				  msg = "更新失败";
				}
			} else {
				msg = "该泵管名已存在";
			}
			logger.info(msg);			
		}catch (Exception e) {
			e.printStackTrace();
			msg = "更新失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}
	
}