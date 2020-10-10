package com.fourfaith.sysManage.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourfaith.sysManage.dao.SysShortMsgMapper;
import com.fourfaith.sysManage.dao.SysUserMapper;
import com.fourfaith.sysManage.dao.SysUserWaterMapper;
import com.fourfaith.sysManage.model.SysShortMsg;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserWater;
import com.fourfaith.sysManage.service.SysUserRoleService;
import com.fourfaith.sysManage.service.SysUserService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.Constant;
import com.fourfaith.utils.Huaxin_webjzjs;
import com.fourfaith.utils.StringUtils;

/**
 * @ClassName: SysUserServiceImpl
 * @Description: 用户service实现
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:43:44
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	
	protected Logger logger = Logger.getLogger(SysUserServiceImpl.class);
	
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysUserWaterMapper sysUserWaterMapper;
	@Autowired
	private SysShortMsgMapper sysShortMsgMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		int result = sysUserMapper.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public int insert(SysUser record) {
		int result = sysUserMapper.insert(record);
		return result;
	}

	@Override
	public int insertSelective(SysUser record) {
		int result = sysUserMapper.insertSelective(record);
		return result;
	}

	@Override
	public SysUser selectByPrimaryKey(String id) {
		SysUser entity = sysUserMapper.selectByPrimaryKey(id);
		return entity;
	}

	@Override
	public int updateByPrimaryKeySelective(SysUser record) {
		int result = sysUserMapper.updateByPrimaryKeySelective(record);
		return result;
	}

	@Override
	public int updateByPrimaryKey(SysUser record) {
		int result = sysUserMapper.updateByPrimaryKey(record);
		return result;
	}

	@Override
	public Integer getCount(Map<String, Object> params) {
		int result = sysUserMapper.getCount(params);
		return result;
	}

	@Override
	public List<SysUser> getList(Map<String, Object> params) {
		return sysUserMapper.getList(params);
	}

	@Override
	public SysUser findById(String Id) {
		return sysUserMapper.selectByPrimaryKey(Id);
	}

	@Override
	public SysUser findByUserName(String username) {
		return sysUserMapper.findByUserName(username);
	}

	@Override
	public String add(SysUser model) {
		String msg = null;
		// 保存用户信息
		model.setId(CommonUtil.getRandomUUID());
		model.setUserPasswordming(model.getUserPassword());
		model.setAreaId(model.getAreaId().split(",")[model.getAreaId().split(",").length-1]);
		model.setWaterAreaId(model.getWaterAreaId().split(",")[model.getWaterAreaId().split(",").length-1]);
		model.setUserPassword(StringUtils.encryptMd5(model.getUserPassword()));
		model.setCreateTime(new Date());
		model.setEditTime(new Date());
		model.setRole(model.getRole());
		model.setIsCardUpdate(model.getIsCardUpdate());
		int result = sysUserMapper.insertSelective(model);
		// 保存用户角色信息
		if(result>0){
			msg = "添加成功";
		}else{
			msg = "添加失败";
		}
		logger.info(msg);
		return msg;
	}

	@Override
	public String update(SysUser model) {
		String msg = null;
		try {
			model.setAreaId(model.getAreaId().split(",")[model.getAreaId().split(",").length-1]);
			model.setWaterAreaId(model.getWaterAreaId().split(",")[model.getWaterAreaId().split(",").length-1]);
			int result = sysUserMapper.updateByPrimaryKey(model);
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
	@Transactional(rollbackFor=Exception.class)
	public AjaxJson deleteUser(String items) {
		AjaxJson  ajaxJson = new AjaxJson();
		String logContent = "";
		try{
			if(items!=null){
				String [] itemArray=items.split(",");
				for(String item:itemArray)
				{
					String id = item;
					delete(id);
					//删除已配置的用户角色关系表
					List<String> userRoleList = this.sysUserRoleService.getUserRoleId(id);
					if(userRoleList != null && userRoleList.size()>0)
					{
						for (int i = 0; i < userRoleList.size(); i++) {
							String userRoleId = userRoleList.get(i);
							this.sysUserRoleService.delete(userRoleId);
						}
					}
					ajaxJson.setMsg("删除成功");
					ajaxJson.setSuccess(true);
					ajaxJson.setObj(logContent);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			ajaxJson.setMsg("操作失败，异常信息："+ex.getMessage());
			ajaxJson.setSuccess(false);
			ajaxJson.setObj(logContent);
		}
		return ajaxJson;
	}

	@Override
	public String delete(String id) {
		String msg = null;
		try {
			int result = sysUserMapper.deleteByPrimaryKey(id);
			
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
	public String moveUser(SysUser model) {
		String msg = null;
		try{
			int result = sysUserMapper.updateByPrimaryKey(model);
			if(result>0){
				msg = "移动用户成功";
			}else{
				msg = "移动用户失败";
			}
			logger.info(msg);
		}catch(Exception ex){
			ex.printStackTrace();
			msg = "移动用户失败"; 
			logger.error(msg + ex.getMessage());
		}
		return msg;
	}

	@Override
	public String findUserCodeByID(String id) {
		return sysUserMapper.getUserCodeByID(id);
	}

	@Override
	public String findUserWaterByCode(String userCode) {
		SysUserWater sysUserWaters =  sysUserWaterMapper.getUserWaterByCode(userCode);
		if(null != sysUserWaters){
			return "failed";
		}else{
			return "success";
		}
	}

	@Override
	public String saveUserWater(SysUserWater sysUserWater) {
		int result = sysUserWaterMapper.addUserWater(sysUserWater);
		if(result > 0){
			return "添加成功";
		}else{
			return "添加失败";
		}
	}

	@Override
	public String editUserWater(SysUserWater sysUserWater) {
		int result = sysUserWaterMapper.updateUserWater(sysUserWater);
		if(result > 0){
			return "修改成功";
		}else{
			return "修改失败";
		}
	}

	@Override
	public SysUserWater findUserWaterByUserCode(String userCode) {
		return sysUserWaterMapper.getUserWaterByCode(userCode);
	}

	@Override
	public String getV4IP() {
		// 获取本机外网IP 发Http请求从站长之家获取
		String internetIP = "";
		String chinaz = "http://ip.chinaz.com";

		StringBuilder inputLine = new StringBuilder();
		String read = "";
		URL url = null;
		HttpURLConnection urlConnection = null;
		BufferedReader in = null;

		try {
			url = new URL(chinaz);
			urlConnection = (HttpURLConnection) url.openConnection();
			in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream(), "UTF-8"));
			while ((read = in.readLine()) != null) {
				inputLine.append(read + "\r\n");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
		Matcher m = p.matcher(inputLine.toString());
		if (m.find()) {
			String ipstr = m.group(1);
			internetIP = ipstr;
		}
		return internetIP;
	}

	@Override
	public String getValidateCode(String phone) {
		// 随机生成四位验证码
		int randNum = (int) Math.round(Math.random() * (9999 - 1000) + 1000);
		String code = String.valueOf(randNum);
		// 根据短信编码获取短信模板
		SysShortMsg sysShortMsg = sysShortMsgMapper.getMsgByCode("1");
		String msgContent = sysShortMsg.getMsgContent();
		// 获取短信内容替换验证码
		msgContent = msgContent.replace("@", code);
		// 调用短信接口  发送验证码
		Huaxin_webjzjs huaXinMsg = new Huaxin_webjzjs();
		huaXinMsg.SendMessage("", Constant.ACCOUNT, Constant.ACCOUNT_PWD, phone, msgContent, "");
		return code;
	}

	@Override
	public int updateUserAuthority(SysUser userModel) {
		return sysUserMapper.updateUserAuthority(userModel);
	}

	@Override
	public String uniqueUserCode(String userCode) {
		List<SysUser> userList = sysUserMapper.uniqueUserCode(userCode);
		if(null != userList && userList.size() > 0){
			return "failed";
		}else{
			return "success";
		}
	}

	@Override
	public String appAudit(SysUser userModel) {
		int result = sysUserMapper.appAudit(userModel);
		if(result > 0){
			return "success";
		}else{
			return "failed";
		}
	}

	@Override
	public Integer getAppUserCount(Map<String, Object> params) {
		int result = sysUserMapper.getAppUserCount(params);
		return result;
	}

	@Override
	public List<SysUser> getAppUserList(Map<String, Object> params) {
		return sysUserMapper.getAppUserList(params);
	}

	@Override
	public String initPwd(SysUser userModel) {
		String msg = "";
		int result = sysUserMapper.updateByPrimaryKeySelective(userModel);
		if(result > 0){
			msg = "success";
		}else{
			msg = "failed";
		}
		return msg;
	}
	
}