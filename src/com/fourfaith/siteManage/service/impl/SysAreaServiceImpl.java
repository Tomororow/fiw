package com.fourfaith.siteManage.service.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.siteManage.dao.SysAreaMapper;
import com.fourfaith.siteManage.model.SysArea;
import com.fourfaith.siteManage.service.SysAreaService;
import com.fourfaith.sysManage.dao.SysWaterAreaMapper;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysWaterArea;
import com.fourfaith.sysManage.model.WaterTree;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.AreaRecursionUtil;
import com.fourfaith.utils.AreaUtil;
import com.google.gson.Gson;

/**
 * @ClassName: SysAreaServiceImpl
 * @Description: 行政区域service实现
 * @Author: zhaojx
 * @Date: 2017年5月14日 上午11:38:36
 */
@Service("SysAreaService")
public class SysAreaServiceImpl implements SysAreaService {
	
	protected Logger logger = Logger.getLogger(SysAreaServiceImpl.class);
	
	@Autowired
	private SysAreaMapper sysAreaMapper;

	@Autowired
	private SysWaterAreaMapper sysWaterAreaMapper;
	
	@Override
	public List<SysArea> getAllArea() {
		return sysAreaMapper.getAllArea();
	}
	
	@Override
	public List<SysArea> selectWaterId(String waterAreaId) {
		return sysAreaMapper.selectWaterId(waterAreaId);
	}
	
	@Override
	public List<SysArea> getChildArea(String areaId) {
		List<SysArea> sysAreaList;
		List<SysArea> allSysAreaList =sysAreaMapper.getAllArea();
	    List<SysArea> childAreaList = AreaUtil.getChildArea(allSysAreaList, areaId);
		sysAreaList = new ArrayList<SysArea>(childAreaList);
		AreaUtil.emptyAreaList();
		Collections.sort(sysAreaList,new Comparator<SysArea>(){
			@Override
			public int compare(SysArea a1, SysArea a2) {
				return a1.getId().compareTo(a2.getId());
			}
		});
		return sysAreaList;
	}

	@Override
	public String add(SysArea sysArea) {
		String msg = null;
		try {
			int result = sysAreaMapper.insertArea(sysArea);
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
	public AjaxJson delSysArea(String areaIds) {
		AjaxJson ajaxJson = new AjaxJson();
		String logContent = "";
		if(areaIds!=null){
			String[] areaIdArray = areaIds.split(",");
			for (int i = 0; i < areaIdArray.length; i++) {
				SysArea sysArea = findById(areaIdArray[i]);
				logContent = logContent+"删除【"+sysArea.getAreaName()+"】的区域数据"+",";
				delete(areaIdArray[i]);
			}
			//删除报警记录
			//删除报表数据
			ajaxJson.setMsg("删除成功");
			ajaxJson.setSuccess(true);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}
	
	@Override
	public SysArea findById(String areaId) {
		return sysAreaMapper.selectByPrimaryKey(areaId);
	}
	
	@Override
	public String delete(String areaId) {
		String msg = null;
		try {
			int result = sysAreaMapper.deleteByPrimaryKey(areaId);
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
	public String update(SysArea sysArea) {
		String msg = null;
		try {
			int result = sysAreaMapper.updateByPrimaryKeySelective(sysArea);
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

	/**
	 * 获取第一级的行政区域
	 * @param object
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	public List<SysArea> getFirstAreaLevelList(Object object) {
		List<SysArea> sysAreaList = sysAreaMapper.getFirstAreaLevelList();
		return sysAreaList;
	}

	/**
	 * 获取行政区域子区域级联操作
	 * @param sysArea
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	public String getChildDeviceAreaInfo(SysArea sysArea) {
		// 获取行政区域的子区域集合
		List<SysArea> sysAreaList = sysAreaMapper.getChildDeviceAreaInfo(sysArea);
		Gson gson = new Gson();
		// 将子节点信息封装成json，并返回
		return gson.toJson(sysAreaList);
	}

	/**
	 * 根据已选择的行政区域，获取到所有的行政区域的areaCode值
	 * @param sysArea
	 * 2016年10月17日
	 * Administrator: xiaogaoxiang
	 */
	public String getAreaCode(SysArea sysArea) {
		SysArea swa = new SysArea();
		swa.setId(sysArea.getAreaIds().split(",")[sysArea.getAreaIds().split(",").length-1]);
		swa = sysAreaMapper.getAreaCode(swa);
		return swa.getAreaCode();
	}

	/**
	 * 根据用户所在的areaId获取当前及自权限区域
	 * @param areaId
	 * 2016年10月23日
	 * Administrator: xiaogaoxiang
	 */
	public List<SysArea> getCurrAndChildArea(String areaId) {
		// 根据登陆人员所属区域Id，获取到当前所属区域信息
		List<SysArea> sysAreaList = sysAreaMapper.getCurrAndChildArea(areaId);
		// 获取到所有地区信息
		List<SysArea> sysAllAreaList = getAllArea();
		// 当所有地区都为空时，则返回null
		if(sysAllAreaList == null && sysAreaList.get(0).getId() == null) return null;
		// 递归获取所属当前区域的子节点区域
		List<SysArea> sysAreaCurrAndChildList = AreaRecursionUtil.getCurrAndChildAreaList(sysAllAreaList, sysAreaList.get(0).getId());
		return sysAreaCurrAndChildList;
	}

	/**
	 * 获取登陆人员所在的区域做级联下拉框操作
	 * @param login_user
	 * 2016年10月25日
	 * Administrator: xiaogaoxiang
	 */
	public List<SysArea> getLoginerAreaList(SysUser login_user) {
		return sysAreaMapper.getLoginerAreaList(login_user);
	}

	/**
	 * 分页操作记录查询总条数
	 * @param params
	 * 2016年10月30日
	 * Administrator: xiaogaoxiang
	 */
	public int getCount(Map<String, Object> params) {
		int result = sysAreaMapper.getCount(params);
		return result;
	}

	/**
	 * 分页查询，查询当前节点的下一级子节点信息
	 * @param params
	 * 2016年10月30日
	 * Administrator: xiaogaoxiang
	 */
	public List<SysArea> getList(Map<String, Object> params) {
		return sysAreaMapper.getList(params);
	}

	/**
	 * 根据Id查询第一级行政编码信息
	 * @param areaId
	 * 2016年10月30日
	 * Administrator: xiaogaoxiang
	 */
	public SysArea findFirstAreaLevelById(String areaId) {
		return sysAreaMapper.findFirstAreaLevelById(areaId);
	}

	/**
	 * 根据用户所属最低一级行政区域查询本区域及同级所有区域
	 * @param sysArea
	 * 2016年11月5日
	 * Administrator: xiaogaoxiang
	 */
	public List<SysArea> getAreaByIdAndLevel(SysArea sysArea) {
		return sysAreaMapper.getAreaByIdAndLevel(sysArea);
	}

	/**
	 * 根据Id查询行政区域信息
	 * @param sysArea
	 * 2016年11月5日
	 * Administrator: xiaogaoxiang
	 */
	public SysArea getAreaById(SysArea sysArea) {
		return sysAreaMapper.getAreaById(sysArea);
	}

	/**
	 * 检查名称唯一性
	 * @param params
	 * 2016年11月15日
	 * Administrator: xiaogaoxiang
	 */
	public List<SysArea> uniqueArea(Map<String, Object> params) {
		return sysAreaMapper.uniqueArea(params);
	}

	@Override
	public SysArea selectMin() {
		return sysAreaMapper.selectMin();
	}

	@Override
	public List<SysArea> selectParentId(String parentAreaId) {
		return sysAreaMapper.selectParentId(parentAreaId);
	}

	@Override
	public List<SysArea> seelectWaterId(String waterAreaId) {
		return sysAreaMapper.seelectWaterId(waterAreaId);
	}

	
	//树结构递归，实例化
	@Override
	public List<WaterTree> recursion(String id,List<WaterTree> menus){
		List<WaterTree> menuList = new ArrayList<>();
			for(WaterTree wt:menus){
				if(id!=null){
					if(id.equals(wt.getParentId())){
						menuList.add(wt);
					}
				}
			}
		SysWaterArea swa = sysWaterAreaMapper.findFirstWaterAreaLevelById(id);
				//1级菜单
		if(menuList.size()>0){
			if(swa.getWaterAreaLevel()==0){
				for(WaterTree wt:menuList){
					wt.setChildren(recursionTwo(wt.getId(),treeList()));
				
				}
			}else if(swa.getWaterAreaLevel()==1){
				if(menuList!=null){
					for(WaterTree wt:menuList){
						List<WaterTree> list =waterIdTree(wt.getId(),1);
						 wt.setChildren(list);
						for(WaterTree wte:list){
							wte.setChildren(recursionThree(wte.getId(),waterTree()));
						}
					}
				}
			
			}
			
		}else{
			menuList =waterIdTree(swa.getId(),1);
			for(WaterTree li :menuList){
				li.setChildren(recursionThree(li.getId(),waterTree()));
			}
		}
		/*if(swa.getWaterAreaLevel()==0){
			for(WaterTree wt:menuList){
				wt.setChildren(recursionTwo(wt.getId(),treeList()));
			}
		}else if(swa.getWaterAreaLevel()==1){
			if(menuList!=null){
				for(WaterTree wt:menuList){
					List<WaterTree> list =waterIdTree(wt.getId(),1);
					 wt.setChildren(list);
					for(WaterTree wte:list){
						wte.setChildren(recursionThree(wte.getId(),waterTree()));
					}
				}
			}
			
		}else{
			menuList =waterIdTree(swa.getId(),1);
			for(WaterTree li :menuList){
				li.setChildren(recursionThree(li.getId(),waterTree()));
			}
				
		}*/
				
		return menuList;
	}
	
	public List<WaterTree> recursionTwo(String id,List<WaterTree> menus){
		List<WaterTree> menuList = new ArrayList<>();
		for(WaterTree wt:menus){
			if(id!=null){
				if(id.equals(wt.getParentId())){
					menuList.add(wt);
				}
			}
		}
		for(WaterTree wt:menuList){
			List<WaterTree> list =waterIdTree(wt.getId(),1);
			 wt.setChildren(list);
			for(WaterTree wte:list){
				wte.setChildren(recursionThree(wte.getId(),waterTree()));
			}
		}
		return menuList;
	}
	
	public List<WaterTree> recursionThree(String id,List<WaterTree> menus){
		List<WaterTree> menuList = new ArrayList<>();
			for(WaterTree wt:menus){
				if(id!=null){
					if(id.equals(wt.getParentId())){
						menuList.add(wt);
					}
				}
			}
			//1级菜单
			for(WaterTree wt:menuList){
			wt.setChildren(recursionThree(wt.getId(),treeList()));
		}
		return menuList;
	}
	
	//水管区域单独查询
	public List<WaterTree> recursionFour(String id,List<WaterTree> menus){
		List<WaterTree> menuList = new ArrayList<>();
			for(WaterTree wt:menus){
				if(id!=null){
					if(id.equals(wt.getParentId())){
						menuList.add(wt);
					}
				}
			}
			//1级菜单
			for(WaterTree wt:menuList){
			wt.setChildren(recursionFour(wt.getId(),treeList()));
		}
		return menuList;
	}
	
	//实例化sysarea
	public List<WaterTree> waterTree(){
		List<SysArea> saList = sysAreaMapper.getAllArea();//selectWaterId(waterId)
		List<WaterTree> wtList = new ArrayList<>();
		for(SysArea sa :saList){
			WaterTree wt =new WaterTree();
			wt.setId(sa.getId());
			wt.setDeviceCode(sa.getAreaCode());
			wt.setAreaLevel(Integer.parseInt(sa.getAreaLevel()));
			wt.setName(sa.getAreaName());
			wt.setParentId(sa.getParentAreaId());
			wtList.add(wt);
		}
		return  wtList;
	}
		
	public List<WaterTree> waterIdTree(String waterAreaId,int type){
		List<SysArea> saList = null;
		List<WaterTree> wtList = new ArrayList<>();
		if(type==1){
			saList = sysAreaMapper.selectWaterId(waterAreaId);
				for(SysArea sa :saList){
					WaterTree wt =new WaterTree();
					wt.setId(sa.getId());
					wt.setDeviceCode(sa.getAreaCode());
					wt.setAreaLevel(Integer.parseInt(sa.getAreaLevel()));
					wt.setName(sa.getAreaName());
					wt.setParentId(sa.getParentAreaId());
					wtList.add(wt);
			}
		}
		return  wtList;
	}
		
	@Override
	public List<WaterTree> treeList() {
		List<SysWaterArea> list = sysWaterAreaMapper.getAllWaterAreaList();
		List<WaterTree> menuList = new ArrayList<>();
		for(SysWaterArea swa:list){
			WaterTree tmc = new WaterTree();
				tmc.setName(swa.getWaterAreaName());
				tmc.setId(swa.getId());
				tmc.setDeviceCode(swa.getWaterAreaCode());
				tmc.setAreaLevel(swa.getWaterAreaLevel());
				tmc.setParentId(swa.getParentWaterAreaId());
				menuList.add(tmc);
		}
		return menuList;
	}
	
	//实例化sysarea
	@Override
	public List<WaterTree> waterTree(int type){
		List<WaterTree> wtList = new ArrayList<>();
		List<SysArea> saList = new ArrayList<>();
		if(type==1){
			saList = sysAreaMapper.seelectWaterOne();//selectWaterId(waterId)
		}else{
			saList =  sysAreaMapper.seelectWater();
		}
		for(SysArea sa :saList){
			WaterTree wt =new WaterTree();
			wt.setId(sa.getId());
			wt.setWaterAreaId(sa.getWaterAreaId());
			wt.setDeviceCode(sa.getAreaCode());
			wt.setAreaLevel(Integer.parseInt(sa.getAreaLevel()));
			wt.setName(sa.getAreaName());
			wt.setParentId(sa.getParentAreaId());
			wtList.add(wt);
		}
		return  wtList;
	}
	
	@Override
	public List<WaterTree> recursionArea(String id,List<WaterTree> menus){
		List<WaterTree> sysList = new ArrayList<>();
		for(WaterTree wt:menus){
			if(id!=null){
				if(id.equals(wt.getWaterAreaId())){
					sysList.add(wt);
				}
			}
		}
		for(WaterTree we:sysList){
			we.setChildren(recursionAreaTwo(we.getId(),waterTree(2)));
		}
		return sysList;
	}
	
	public List<WaterTree> recursionAreaTwo(String id,List<WaterTree> menus){
		List<WaterTree> sysList = new ArrayList<>();
		for(WaterTree wt :menus){
			if(id!=null){
				if(id.equals(wt.getParentId())){
					sysList.add(wt);
				}
			}
		}
		for(WaterTree we:sysList){
			we.setChildren(recursionAreaTwo(we.getId(),waterTree(2)));
		}
		return sysList;
	}
	
	@Override
	public SysArea getChildCode(String areaCode) {
		return sysAreaMapper.getChildCode(areaCode);
	}

	@Override
	public List<SysArea> getChildCodeInfo(String id) {
		return sysAreaMapper.getChildCodeInfo(id);
	}

	@Override
	public List<SysArea> findLevelSyaAreaCodeList(String level) {
		// TODO Auto-generated method stub
		return sysAreaMapper.findLevelSyaAreaCodeList(level);
	}

	@Override
	public List<SysArea> seelectWater() {
		// TODO Auto-generated method stub
		return sysAreaMapper.seelectWater();
	}
	
}