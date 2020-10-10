package com.fourfaith.baseManager.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fourfaith.factorManage.model.IraFAllDetails;
import com.fourfaith.factorManage.service.IraFAllDetailsService;
import com.fourfaith.siteManage.model.StAlarmInfo;
import com.fourfaith.siteManage.service.StAlarmInfoService;
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PersonDateUtils;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.PropertiesUtils;

/**
 * 告警查询Controller
 * @author administrator
 */
@Controller
@RequestMapping(value ="/history")
public class HistoryController {

	protected static final String indexJsp="/page/history/index";
	protected static final String historyJsp="/page/history/history";
	protected static final String alarmJsp="/page/history/alarm";
	protected static final String historyListJsp = "/page/history/historyList";
	protected static final String alarmListJsp = "/page/history/alarmList";

	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private IraFAllDetailsService iraFAllDetailsService;
	@Autowired
	private StAlarmInfoService stAlarmInfoService;

	/**
	 * 首页
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,String menuId, String stcd,String tag){
		ModelAndView mav = new ModelAndView(indexJsp);
		SysUser login_user = (SysUser) CommonUtil.getLoginUserInfo(request);
		List<SysMenu> sysMenuList = new ArrayList<SysMenu>();
		//根据权限动态获取菜单列表
		sysMenuList = this.sysMenuService.getListByRoleidAndMenuid(menuId, login_user.getSysrole().getId());
		mav.addObject("sysMenuList", sysMenuList);
		mav.addObject("stcd", stcd);
		mav.addObject("tag", tag);
		return mav;
	}
	

	/**
	 * 历史查询
	 */
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ModelAndView history(HttpServletRequest request, String stcd){
		ModelAndView mav = new ModelAndView(historyJsp);
		mav.addObject("defaultTime", new Date());
		mav.addObject("pStcd", stcd);
		return mav;
	}

	/**
	 * 报警查询
	 */
	@RequestMapping(value = "/alarm", method = RequestMethod.GET)
	public ModelAndView alarm(HttpServletRequest request, String stcd){
		ModelAndView mav = new ModelAndView(alarmJsp);
		mav.addObject("defaultTime", new Date());
		mav.addObject("pStcd", stcd);
		return mav;
	}

	/**
	 * 历史查询列表
	 */
	@RequestMapping(value = "/historyList", method = RequestMethod.POST)
	public ModelAndView detailList(HttpServletRequest request, String query_beginTime, String query_endTime){
		ModelAndView mav = new ModelAndView(historyListJsp);
		String nodeIds = request.getParameter("nodeIds");
		Map<String,Object> params = new HashMap<String,Object>();
		List<String> stcdList = null;
		if(StringUtils.isNotBlank(nodeIds)){
			stcdList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
		}else{
			//无id，则置空
			stcdList = new ArrayList<>(Arrays.asList(""));
		}
		params.put("stcdList", stcdList);
		String stcd_query = request.getParameter("stcd_query");
		try {
			if(StringUtils.isNotBlank(stcd_query)){
				params.put("stcd_query", stcd_query);
			}else{
				params.put("stcd_query", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String pattern = "yyyy-MM-dd HH:mm";
		Date beginTime = PersonDateUtils.StringToDate(query_beginTime, pattern);
		Date endTime = PersonDateUtils.StringToDate(query_endTime, pattern);

		params.put("beginTime", beginTime);
		params.put("endTime", endTime);

		// 分页
		String pageNo = request.getParameter("pageNo");
		int count = this.iraFAllDetailsService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("start", pagingBean.getStart());
		params.put("limit", pagingBean.getLimit());
		//获取历史数据
		List<IraFAllDetails> historyAllList = this.iraFAllDetailsService.getList(params);

		mav.addObject("IraFAllList", historyAllList);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("stcd", stcd_query);
		return mav;
	}

	/**
	 * 报警查询列表
	 */
	@RequestMapping(value = "/alarmList", method = RequestMethod.POST)
	public ModelAndView alarmList(HttpServletRequest request,String query_beginTime, String query_endTime,String type){
		ModelAndView mav = new ModelAndView(alarmListJsp);
		String nodeIds = request.getParameter("nodeIds");
		Map<String,Object> params = new HashMap<String,Object>();
		List<String> stcdList = null;
		if(StringUtils.isNotBlank(nodeIds)){
			stcdList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
		}else{
			//无id，则置空
			stcdList = new ArrayList<>(Arrays.asList(""));
		}
		params.put("stcdList", stcdList);
		String stcd_query = request.getParameter("stcd_query");
		try {
			if(StringUtils.isNotBlank(stcd_query)){
				params.put("stcd_query", stcd_query);
			}else{
				params.put("stcd_query", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(StringUtils.isEmpty(type)){
			type = null;
		}
		params.put("type",type);
		String pattern = "yyyy-MM-dd HH:mm";
		Date beginTime = PersonDateUtils.StringToDate(query_beginTime, pattern);
		Date endTime = PersonDateUtils.StringToDate(query_endTime, pattern);;

		params.put("beginTime", beginTime);
		params.put("endTime", endTime);

		// 分页
		String pageNo = request.getParameter("pageNo");
		int count = this.stAlarmInfoService.getCount(params);
		PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("start", pagingBean.getStart());
		params.put("limit", pagingBean.getLimit());
		//获取报警数据
		List<StAlarmInfo> stAlarmList = this.stAlarmInfoService.getList(params);

		mav.addObject("Alarmist", stAlarmList);
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("stcd", stcd_query);
		return mav;
	}
	
    /**
     * 历史查询导出
     */
	@RequestMapping(value="/historyExport", method = RequestMethod.POST)  
	public void historyExport(HttpServletRequest request,HttpServletResponse response){  
		commonExport(request,response,"history");
	}
	
	/**
     * 报警查询导出
     */
	@RequestMapping(value="/alarmExport", method = RequestMethod.POST)  
	public void alarmExport(HttpServletRequest request,HttpServletResponse response){  
		commonExport(request,response,"alarm");
	}

	/**
	 * 历史查询导出
	 * @param response
	 * @param groupId
	 */
	@RequestMapping(value="/commonExport")  
	public void commonExport(HttpServletRequest request,HttpServletResponse response,String tagType){  
		//文件名
		String nodeIds = request.getParameter("nodeIds");
		String stcd_query = request.getParameter("stcd_query");
		String query_beginTime = request.getParameter("query_beginTime");
		String query_endTime = request.getParameter("query_endTime");
		String type = request.getParameter("type");
		
		SimpleDateFormat simpleDateFormat;  
		simpleDateFormat = new SimpleDateFormat("yyyyMMdd");  
		Date date = new Date();
		String sdate = simpleDateFormat.format(date);  
		Random random = new Random();  
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数  
		String str = sdate+rannum;
		SimpleDateFormat sp = new SimpleDateFormat("yyyy");
		String year = sp.format(date);
        if(tagType.equals("alarm")){
        	str=str+year+PropertiesUtils.getPara("exportAlarmInfo");
		}else{
			str=str+year+PropertiesUtils.getPara("exportHistoryInfo");
		}
		
		String fileName = null;
		try {
			fileName = java.net.URLEncoder.encode(str, "UTF-8");
			fileName = new String(str.getBytes("GB2312"), "ISO_8859_1");  
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String pathName = "";
	    if(tagType.equals("alarm")){
	    	pathName = alarmWrite(request,nodeIds,stcd_query,query_beginTime,query_endTime,type);
		}else{
			pathName = historyWrite(request,nodeIds,stcd_query,query_beginTime,query_endTime);
		}
	
		//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
		response.setContentType("multipart/form-data");  
		//2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
		response.setHeader("Content-Disposition", "attachment;fileName="+fileName);  
		ServletOutputStream out;  
		//通过文件路径获得File对象 
		File file = new File(pathName);  
		try {  
			FileInputStream inputStream = new FileInputStream(file);  
			//3.通过response获取ServletOutputStream对象(out)  
			out = response.getOutputStream();  
			byte[] buffer = new byte[1024];  
			int length = -1;
			while ((length = inputStream.read(buffer)) != -1)
			{
				// 把缓冲区中输出到内存中
				out.write(buffer, 0, length);
			}
			inputStream.close();  
			out.close();  
			out.flush();  

		} catch (IOException e) {  
			e.printStackTrace();  
		}  
	}
	
	/**
	 * 历史记录写入
	 */
	public String historyWrite(HttpServletRequest  request,String nodeIds,String stcd_query,String query_beginTime,String query_endTime ){
		Map<String,Object> params = new HashMap<String,Object>();
		List<String> stcdList = null;
		if(StringUtils.isNotBlank(nodeIds)){
			stcdList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
		}else{
			//无id，则置空
			stcdList = new ArrayList<>(Arrays.asList(""));
		}
		params.put("stcdList", stcdList);

		try {
			if(StringUtils.isNotBlank(stcd_query)){
				params.put("stcd_query", stcd_query);
			}else{
				params.put("stcd_query", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String pattern = "yyyy-MM-dd HH:mm";
		Date beginTime = PersonDateUtils.StringToDate(query_beginTime, pattern);
		Date endTime = PersonDateUtils.StringToDate(query_endTime, pattern);;

		params.put("beginTime", beginTime);
		params.put("endTime", endTime);

		// 文件下载目录相对路径
		String basePath = PropertiesUtils.getPara("downFileDir");
		// 文件下载目录路径
		String savePath;
		savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
		//文件不存在就创建
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		//文件名
		SimpleDateFormat simpleDateFormat;  
		simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
		Date date = new Date();
		String sdate = simpleDateFormat.format(date);  
		Random random = new Random();  
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数  
		String str = sdate+rannum;
		SimpleDateFormat sp = new SimpleDateFormat("yyyy");
		String year = sp.format(date);
		str= str+year+PropertiesUtils.getPara("exportHistoryInfo");
		//			String fileName = savePath+str+".xls";
		String fileName = savePath+str;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		WritableWorkbook wwb = null;  
		try {  
			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象  
			wwb = Workbook.createWorkbook(new File(fileName));  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		if (wwb != null) {
			// 创建一个可写入的工作表  
			// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置  
			WritableSheet ws = wwb.createSheet("历史记录", 0);  
			try {
				ws.mergeCells(0, 0, 11, 0);//设置第一列、第一行和 第11列、第一行合并
				String titleView = "历史记录";
				if(beginTime!=null){
					String bTime = sdf.format(beginTime);
					String eTime = sdf.format(endTime);
					titleView+="("+bTime+"到"+eTime+")";
				}
				Label headerTitle = new Label(0, 0, titleView,getHeaderCellStyle());
				ws.addCell(headerTitle);
				ws.setColumnView(0, 10);// 设置宽度
				
				
				//创建表头
				Label title1 = new Label(0, 1, "区域",getHeaderCellStyle());
				ws.addCell(title1);
				ws.setColumnView(0, 10);// 设置宽度
				
				Label title2 = new Label(1, 1, "测站编码",getHeaderCellStyle());
				ws.addCell(title2);
				ws.setColumnView(1, 15);// 设置宽度
				Label title3 = new Label(2, 1, "测站名称",getHeaderCellStyle());
				ws.addCell(title3);
				ws.setColumnView(2, 15);// 设置宽度
				Label title4 = new Label(3, 1, "采集时间",getHeaderCellStyle());
				ws.addCell(title4);
				ws.setColumnView(3, 25);// 设置宽度
				Label title5 = new Label(4, 1, "水位",getHeaderCellStyle());
				ws.addCell(title5);
				ws.setColumnView(4, 15);// 设置宽度
				Label title6 = new Label(5, 1, "每秒流量",getHeaderCellStyle());
				ws.addCell(title6);
				ws.setColumnView(5, 15);// 设置宽度
				Label title7 = new Label(6, 1, "每小时流量",getHeaderCellStyle());
				ws.addCell(title7);
				ws.setColumnView(6, 15);// 设置宽度
				Label title8 = new Label(7, 1, "累计流量",getHeaderCellStyle());
				ws.addCell(title8);
				ws.setColumnView(7, 15);// 设置宽度
				Label title9 = new Label(8, 1, "电源电压",getHeaderCellStyle());
				ws.addCell(title9);
				ws.setColumnView(8, 15);// 设置宽度
				Label title10 = new Label(9, 1, "信号强度",getHeaderCellStyle());
				ws.addCell(title10);
				ws.setColumnView(9, 15);// 设置宽度
				Label title11 = new Label(10, 1, "流量计状态",getHeaderCellStyle());
				ws.addCell(title11);
				ws.setColumnView(10, 15);// 设置宽度
				Label title12 = new Label(11, 1, "电池状态",getHeaderCellStyle());
				ws.addCell(title12);
				ws.setColumnView(11, 15);// 设置宽度
			} catch (RowsExceededException e1) {
				e1.printStackTrace();
			} catch (WriteException e1) {
				e1.printStackTrace();
			}

			//获取历史数据
			List<IraFAllDetails> historyAllList = this.iraFAllDetailsService.getList(params);
			
			if(historyAllList!=null && historyAllList.size()>0){
				for(int i = 0;i<historyAllList.size();i++){
					for (int j = i; j <=i; j++) {  
						Label labelC0 = new Label(0, j+2,historyAllList.get(i).getAddvnm());//第一行，第一列  
						Label labelC1 = new Label(1, j+2,historyAllList.get(i).getStcd());//第一行，第二列  
						Label labelC2 = new Label(2, j+2,historyAllList.get(i).getStnm());//第一行，第三列  
						Label labelC3;
						if(historyAllList.get(i).getTm()!=null){
							String data = sdf.format(historyAllList.get(i).getTm());
							labelC3 = new Label(3, j+2,data);//第一行，第四列  
						}else{
							labelC3 = new Label(3, j+2,"--");//第一行，第四列
						}
						Label labelC4;
						if(historyAllList.get(i).getWater()!=null){
							labelC4 = new Label(4, j+2,historyAllList.get(i).getWater().toString());//第一行，第五列  
						}else{
							labelC4 = new Label(4, j+2,"--");//第一行，第五列  
						}
						Label labelC5;
						if(historyAllList.get(i).getFlowratepers()!=null){
							labelC5 = new Label(5, j+2,historyAllList.get(i).getFlowratepers().toString());//第一行，第六列  
						}else{
							labelC5 = new Label(5, j+2,"--");//第一行，第六列  
						}
						Label labelC6;
						if(historyAllList.get(i).getFlowrateperh()!=null){
							labelC6 = new Label(6, j+2,historyAllList.get(i).getFlowrateperh().toString());//第一行，第七列  
						}else{
							labelC6 = new Label(6, j+2,"--");//第一行，第七列  
						}
						Label labelC7;
						if(historyAllList.get(i).getFlowrateadd()!=null){
							labelC7 = new Label(7, j+2,historyAllList.get(i).getFlowrateadd().toString());//第一行，第八列  
						}else{
							labelC7 = new Label(7, j+2,"--");//第一行，第八列  
						}
						Label labelC8;
						if(historyAllList.get(i).getVoltage()!=null){
							labelC8 = new Label(8, j+2,historyAllList.get(i).getVoltage().toString());//第一行，第九列  
						}else{
							labelC8 = new Label(8, j+2,"--");//第一行，第九列  
						}

						Label labelC9;
						if(historyAllList.get(i).getSignalinten()!=null){
							labelC9 = new Label(9, j+2,historyAllList.get(i).getSignalinten().toString());//第一行，第10列  
						}else{
							labelC9 = new Label(9, j+2,"--");//第一行，第10列  
						}

						Label labelC10;
						if(historyAllList.get(i).getFlowmeterstatus()!=null){
							labelC10 = new Label(10, j+2,historyAllList.get(i).getFlowmeterstatus().toString());//第一行，第10列
						}else{
							labelC10 = new Label(10, j+2,"--");//第一行，第九列  
						}
						Label labelC11;
						if(historyAllList.get(i).getVoltage()!=null){
							BigDecimal a = BigDecimal.valueOf(10.8);
							BigDecimal b = BigDecimal.valueOf(36);
							if((historyAllList.get(i).getVoltage()).compareTo(a)==1 && (historyAllList.get(i).getVoltage()).compareTo(b)==-1 )
								labelC11 =new Label(11, j+2,"正常");//第一行，第10列
							else
								labelC11 =new Label(11, j+2,"异常");//第一行，第10列
						}else{
							    labelC11 = new Label(11, j+2,"--");//第一行，第九列  
						}
						try {
							ws.addCell(labelC0);
							ws.addCell(labelC1);  
							ws.addCell(labelC2); 
							ws.addCell(labelC3);
							ws.addCell(labelC4); 
							ws.addCell(labelC5);
							ws.addCell(labelC6); 
							ws.addCell(labelC7); 
							ws.addCell(labelC8); 
							ws.addCell(labelC9); 
							ws.addCell(labelC10);
							ws.addCell(labelC11);
						} catch (RowsExceededException e) {
							e.printStackTrace();
						} catch (WriteException e) {
							e.printStackTrace();
						} 
					}
				}
			}
		}

		try {  
			// 从内存中写入文件中  
			wwb.write();  
			// 关闭资源，释放内存  
			wwb.close();  
		} catch (IOException e) {  
			e.printStackTrace();  
		} catch (WriteException e) {  
			e.printStackTrace();  
		} 
		return fileName;
	}
	
	/**
	 * 报警记录写入
	 */
	public String alarmWrite(HttpServletRequest  request,String nodeIds,String stcd_query,String query_beginTime,String query_endTime,String type){
		Map<String,Object> params = new HashMap<String,Object>();
		List<String> stcdList = null;
		if(StringUtils.isNotBlank(nodeIds)){
			stcdList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
		}else{
			//无id，则置空
			stcdList = new ArrayList<>(Arrays.asList(""));
		}
		params.put("stcdList", stcdList);

		try {
			if(StringUtils.isNotBlank(stcd_query)){
				params.put("stcd_query", stcd_query);
			}else{
				params.put("stcd_query", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(StringUtils.isEmpty(type)){
			type = null;
		}
		params.put("type", type);
		String pattern = "yyyy-MM-dd HH:mm";
		Date beginTime = PersonDateUtils.StringToDate(query_beginTime, pattern);
		Date endTime = PersonDateUtils.StringToDate(query_endTime, pattern);;

		params.put("beginTime", beginTime);
		params.put("endTime", endTime);

		// 文件下载目录相对路径
		String basePath = PropertiesUtils.getPara("downFileDir");
		// 文件下载目录路径
		String savePath;
		savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
		//文件不存在就创建
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		//文件名
		SimpleDateFormat simpleDateFormat;  
		simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
		Date date = new Date();
		String sdate = simpleDateFormat.format(date);  
		Random random = new Random();  
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数  
		String str = sdate+rannum;
		SimpleDateFormat sp = new SimpleDateFormat("yyyy");
		String year = sp.format(date);
		str= str+year+PropertiesUtils.getPara("exportAlarmInfo");
		//			String fileName = savePath+str+".xls";
		String fileName = savePath+str;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		WritableWorkbook wwb = null;  
		try {  
			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象  
			wwb = Workbook.createWorkbook(new File(fileName));  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		if (wwb != null) {
			// 创建一个可写入的工作表  
			// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置  
			WritableSheet ws = wwb.createSheet("报警记录", 0);  
			try {
				ws.mergeCells(0, 0, 11, 0);//设置第一列、第一行和 第11列、第一行合并
				String titleView = "报警记录";
				if(beginTime!=null){
					String bTime = sdf.format(beginTime);
					String eTime = sdf.format(endTime);
					titleView+="("+bTime+"到"+eTime+")";
				}
				Label headerTitle = new Label(0, 0, titleView,getHeaderCellStyle());
				ws.addCell(headerTitle);
				ws.setColumnView(0, 10);// 设置宽度
				
				//创建表头
				Label title1 = new Label(0, 1, "区域",getHeaderCellStyle());
				ws.addCell(title1);
				ws.setColumnView(0, 10);// 设置宽度
				
				Label title2 = new Label(1, 1, "测站编码",getHeaderCellStyle());
				ws.addCell(title2);
				ws.setColumnView(1, 15);// 设置宽度
				Label title3 = new Label(2, 1, "测站名称",getHeaderCellStyle());
				ws.addCell(title3);
				ws.setColumnView(2, 15);// 设置宽度
				
				Label title4 = new Label(3, 1, "报警类型",getHeaderCellStyle());
				ws.addCell(title4);
				ws.setColumnView(3, 15);// 设置宽度
				
				Label title5 = new Label(4, 1, "相关量值",getHeaderCellStyle());
				ws.addCell(title5);
				ws.setColumnView(4, 15);// 设置宽度
				
				Label title6 = new Label(5, 1, "报警时间",getHeaderCellStyle());
				ws.addCell(title6);
				ws.setColumnView(5, 25);// 设置宽度
			} catch (RowsExceededException e1) {
				e1.printStackTrace();
			} catch (WriteException e1) {
				e1.printStackTrace();
			}

			//获取报警数据
			List<StAlarmInfo> stAlarmList = this.stAlarmInfoService.getList(params);
			if(stAlarmList!=null && stAlarmList.size()>0){
				for(int i = 0;i<stAlarmList.size();i++){
					for (int j = i; j <=i; j++) {  
						Label labelC0 = new Label(0, j+2,stAlarmList.get(i).getAddvnm());//第一行，第一列  
						Label labelC1 = new Label(1, j+2,stAlarmList.get(i).getStcd());//第一行，第二列  
						Label labelC2 = new Label(2, j+2,stAlarmList.get(i).getStnm());//第一行，第三列  
						Label labelC3 = null;
						if(stAlarmList.get(i).getType()==null){
							labelC3 = new Label(3, j+2,"--");//第一行，第六列  
						}else if(stAlarmList.get(i).getType()==0){
							labelC3 = new Label(3, j+2,"水位");//第一行，第六列  
						}else if(stAlarmList.get(i).getType()==1){
							labelC3 = new Label(3, j+2,"每秒流量");//第一行，第六列  
						}else if(stAlarmList.get(i).getType()==2){
							labelC3 = new Label(3, j+2,"每小时流量");//第一行，第六列  
						}else if(stAlarmList.get(i).getType()==3){
							labelC3 = new Label(3, j+2,"累计流量");//第一行，第六列  
						}else if(stAlarmList.get(i).getType()==4){
							labelC3 = new Label(3, j+2,"电压");//第一行，第六列  
						}else if(stAlarmList.get(i).getType()==5){
							labelC3 = new Label(3, j+2,"信号强度");//第一行，第六列  
						}
						Label labelC4 = new Label(4, j+2,stAlarmList.get(i).getFactorvalue());//第一行，第五列  
						
						Label labelC5;
						if(stAlarmList.get(i).getTm()!=null){
							String data = sdf.format(stAlarmList.get(i).getTm());
							labelC5 = new Label(5, j+2,data);//第一行，第四列  
						}else{
							labelC5 = new Label(5, j+2,"--");//第一行，第四列
						}
						
						try {
							ws.addCell(labelC0);
							ws.addCell(labelC1);  
							ws.addCell(labelC2); 
							ws.addCell(labelC3);
							ws.addCell(labelC4); 
							ws.addCell(labelC5);
						} catch (RowsExceededException e) {
							e.printStackTrace();
						} catch (WriteException e) {
							e.printStackTrace();
						} 
					}
				}
			}
		}

		try {  
			// 从内存中写入文件中  
			wwb.write();  
			// 关闭资源，释放内存  
			wwb.close();  
		} catch (IOException e) {  
			e.printStackTrace();  
		} catch (WriteException e) {  
			e.printStackTrace();  
		} 
		return fileName;
	}
	
	/** 
     * 表头单元格样式的设定 
     */  
    public WritableCellFormat getHeaderCellStyle(){  
        /* 
         * WritableFont.createFont("宋体")：设置字体为宋体 
         * 10：设置字体大小 
         * WritableFont.BOLD:设置字体加粗（BOLD：加粗     NO_BOLD：不加粗） 
         * false：设置非斜体 
         * UnderlineStyle.NO_UNDERLINE：没有下划线 
         */  
        WritableFont font = new WritableFont(WritableFont.createFont("宋体"),  
                                             10,   
                                             WritableFont.BOLD,   
                                             false,  
                                             UnderlineStyle.NO_UNDERLINE);  
          
        WritableCellFormat headerFormat = new WritableCellFormat(NumberFormats.TEXT);  
        try {  
            //添加字体设置  
            headerFormat.setFont(font);  
            /*//设置单元格背景色：表头为黄色  
            headerFormat.setBackground(Colour.YELLOW);  */
            //设置表头表格边框样式  
            //整个表格线为粗线、黑色  
            //headerFormat.setBorder(Border.ALL, BorderLineStyle.THICK, Colour.BLACK);  
            //表头内容水平居中显示  
            headerFormat.setAlignment(Alignment.CENTRE);      
        } catch (WriteException e) {  
        	e.printStackTrace();
        }  
        return headerFormat;  
    }

}