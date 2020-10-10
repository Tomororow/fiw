package com.fourfaith.factorManage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fourfaith.factorManage.service.IraFAllDetailsService;

/**   
 * @Title: Controller
 * @Description: 详细数据
 * @author administrator
 * @date 2016-06-12 10:30:31
 * @version V1.0   
 */
@Controller
@RequestMapping(value ="/iraFAllDetails")
public class IraFAllDetailsController {

	@Autowired
	private IraFAllDetailsService iraFAllDetailsService;

}