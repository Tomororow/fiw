package com.fourfaith.basicInformation.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.basicInformation.dao.SysOperatorMapper;
import com.fourfaith.basicInformation.model.SysOperator;
import com.fourfaith.basicInformation.service.SysOperatorService;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: SysOperatorServiceImpl
 * @Description: 运营商service实现类
 * @Author: zhaojx
 */
@Service("sysOperatorService")
public class SysOperatorServiceImpl implements SysOperatorService {
	
	protected Logger logger = Logger.getLogger(SysOperatorServiceImpl.class);
	
	@Autowired
	private SysOperatorMapper sysOperatorMapper;
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysOperatorMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysOperator> getList(Map<String, Object> params) {
		return sysOperatorMapper.getList(params);
	}

	public int deleteByPrimaryKey(String id) {
		int result = sysOperatorMapper.deleteByPrimaryKey(id);
		return result;
	}

	public int insert(SysOperator record) {
		int result = sysOperatorMapper.insert(record);
		return result;
	}
	
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysOperatorMapper.deleteByPrimaryKey(id);
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
	
	public int insertSelective(SysOperator record) {
		int result = sysOperatorMapper.insertSelective(record);
		return result;
	}

	public SysOperator selectByPrimaryKey(String id) {
		SysOperator entity = sysOperatorMapper.selectByPrimaryKey(id);
		return entity;
	}

	public SysOperator findById(String Id) {
		return sysOperatorMapper.selectByPrimaryKey(Id);
	}

	public String add(SysOperator model) {
		String msg = null;
		// 增加运营商名唯一性校验（新增），只需要根据输入的运营商名称来检验是否存在即可
		List<SysOperator> m = sysOperatorMapper.uniqueAddSelective(model);
		/**
		 *  1、如果查询到的运营商存在，则提示该运营商名已存在；
		 *  2、如果查询到的运营商名不存在，则执行新增操作
		 *  	A、如果新增成功（无异常），则提示添加成功
		 *  	B、如果新增失败（异常），则提示添加失败
		 */
		if(null==m || m.size()==0) {
			int result = sysOperatorMapper.insertSelective(model);
			if(result>0) {
				msg = "添加成功";
			}else {
				 msg = "添加失败";
			}
		} else {
			 msg = "该运营商名已存在";
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
	    			SysOperator plan = findById(id);
	    			logContent = logContent+"删除【"+plan.getOperator()+"】的数据"+",";
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
	
	public String update(SysOperator sysOperator) {
		String msg = null;
		try {
			// 增加运营商名唯一性校验（修改），只需要根据输入的运营商名称来检验是否存在即可
			List<SysOperator> so = sysOperatorMapper.uniqueUpdateSelective(sysOperator);
			/**
			 *  1、如果查询到的运营商存在，则提示该运营商名已存在；
			 *  2、如果查询到的运营商名不存在，则执行新增操作
			 *  	A、如果新增成功（无异常），则提示添加成功
			 *  	B、如果新增失败（异常），则提示添加失败
			 */
			if(null==so || so.size()==0) {
				int result = sysOperatorMapper.updateByPrimaryKeySelective(sysOperator);
				if(result>0) {
					msg = "更新成功";
				}else {
					 msg = "更新失败";
				}
			} else {
				 msg = "该运营商名已存在";
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