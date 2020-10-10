package com.fourfaith.sysManage.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourfaith.sysManage.dao.SysOrganizationMapper;
import com.fourfaith.sysManage.model.SysOrganization;
import com.fourfaith.sysManage.service.SysOrganizationService;

/**
 * @ClassName: SysOrganizationServiceImpl
 * @Description: service实现
 */
@Service("sysOrganizationService")
public class SysOrganizationServiceImpl implements SysOrganizationService {
	
	protected Logger logger = Logger.getLogger(SysOrganizationServiceImpl.class);
	
	@Autowired
	private SysOrganizationMapper sysOrganizationMapper;
  
    @Override
	public int deleteByPrimaryKey(String id) {
		int result = sysOrganizationMapper.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public int insert(SysOrganization record) {
		int result = sysOrganizationMapper.insert(record);
		return result;
	}
	
	@Override
	public int insertSelective(SysOrganization record) {
		int result = sysOrganizationMapper.insertSelective(record);
		return result;
	}

	@Override
	public SysOrganization selectByPrimaryKey(String id) {
		SysOrganization entity = sysOrganizationMapper.selectByPrimaryKey(id);
		return entity;
	}
	
	@Override
	public int updateByPrimaryKeySelective(SysOrganization record) {
		int result = sysOrganizationMapper.updateByPrimaryKeySelective(record);
		return result;
	}

	@Override
	public int updateByPrimaryKey(SysOrganization record) {
		int result = sysOrganizationMapper.updateByPrimaryKey(record);
		return result;
	}
	
	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysOrganizationMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysOrganization> getList(Map<String, Object> params) {
		return sysOrganizationMapper.getList(params);
	}

	@Override
	public SysOrganization findById(String Id) {
		return sysOrganizationMapper.selectByPrimaryKey(Id);
	}

	@Override
	public List<String> getAllChildIdList(String id) {
		return this.getChildIdList(id);
	}
	
	/**
	 * 查找机构下的子机构
	 * @param groupId
	 * @return
	 */
	public List<String> getChildIdList(String orgId){
        List<String> orgIdList = new ArrayList<String>();
    	//获取子机构
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("enabledstate", 1);
    	params.put("pid", orgId);
    	List<SysOrganization> orgList = getList(params);
		if(orgList!=null && orgList.size()>0){
			for(SysOrganization org:orgList){
				orgIdList.addAll(getChildIdList(org.getId()));
		   }
        }else{
        	//最子集
        	orgIdList.add(orgId);
        }
		return orgIdList;
   	}

	@Override
	public String add(SysOrganization model) {
		String msg = null;
		try {
			int result = sysOrganizationMapper.insertSelective(model);
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
	public String update(SysOrganization model) {
		String msg = null;
		try {
			int result = sysOrganizationMapper.updateByPrimaryKeySelective(model);
			if(result>0){
			   msg = "更新成功";
			}else{
			  msg = "更新失败";
			}
			logger.info(msg);			
		}catch (Exception e) {
			e.printStackTrace();
			msg = "更新失败";
			logger.error(msg + e.getMessage());
		}
		return msg;
	}

	@Override
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysOrganizationMapper.deleteByPrimaryKey(id);
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

	@Override
	public SysOrganization getByOrgan(Map<String, Object> params) {
		return sysOrganizationMapper.getByOrgan(params);
	}
	
}