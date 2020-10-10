package com.fourfaith.sysManage.service;

import com.fourfaith.sysManage.model.WellInfo;
import com.fourfaith.utils.AjaxJson;

/**
 * @ClassName: WellInfoService
 * @Description: 恒泽机井信息dao接口
 * @Author: zhaojx
 * @Date: 2018年5月15日 下午7:36:02
 */
public interface WellInfoService{

    String add(WellInfo wellInfo);
    
    String update(WellInfo wellInfo);
    
    AjaxJson delWellInfo(String ids);
    
    String delete(String id);
    
}