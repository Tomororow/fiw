package com.fourfaith.factorManage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableFont.FontName;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.factorManage.model.IraFAllDetails;
import com.fourfaith.factorManage.service.IraFAllDetailsService;
import com.fourfaith.utils.PersonDateUtils;
import com.fourfaith.utils.MapKeyComparator;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.PropertiesUtils;

/**
 * @Description: 通用报表控制器
 * @author administrator
 * @date 2016年6月12日
 */
@Controller
@RequestMapping(value ="/generalReport")
public class GeneralReportController {
	
	protected static final String indexJsp="/page/statistic/generalReport/index";
	protected static final String listJsp="/page/statistic/generalReport/list";
	protected static final String detailListJsp="/page/statistic/generalReport/detailList";
	
	@Autowired
	IraFAllDetailsService iraFAllDetailsService;
	
	/**
	 * 通用报表首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mav = new ModelAndView(indexJsp);
		mav.addObject("defaultTime", new Date());
		return mav;
	}
	
	/**
	 * 列表数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ModelAndView list(HttpServletRequest request, String nodeIds, String searchType){
		ModelAndView mav = new ModelAndView(listJsp);
		
		Date beginTime = null;
		Date endTime = null;
		if("1".equals(searchType)){
			//时段
			String query_beginTime = request.getParameter("query_beginTime");
			String query_endTime = request.getParameter("query_endTime");
			beginTime = PersonDateUtils.StringToDate(query_beginTime, "yyyy-MM-dd HH:mm");
			endTime = PersonDateUtils.StringToDate(query_endTime, "yyyy-MM-dd HH:mm");
		}else{
			String query_Time = request.getParameter("query_Time");
			if("2".equals(searchType)){
				//日
				String pattern = "yyyy-MM-dd";
				Date date = PersonDateUtils.StringToDate(query_Time, pattern);
				beginTime = PersonDateUtils.setMinTimeForDate(date);
				endTime = PersonDateUtils.setMaxTimeForDate(date);
			}else if("3".equals(searchType)){
				//月
				String pattern = "yyyy-MM";
				Date date = PersonDateUtils.StringToDate(query_Time, pattern);
				beginTime = PersonDateUtils.setMinTimeForMonthDate(date);
				endTime = PersonDateUtils.setMaxTimeForMonthDate(date);
			}else if("4".equals(searchType)){
				//年
				String pattern = "yyyy";
				Date date = PersonDateUtils.StringToDate(query_Time, pattern);
				beginTime = PersonDateUtils.setMinTimeForYearDate(date);
				endTime = PersonDateUtils.setMaxTimeForYearDate(date);
			}
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		
		List<String> stcdList = null;
		if(StringUtils.isNotBlank(nodeIds)){
			stcdList = new ArrayList<>(Arrays.asList(nodeIds.split(",")));
		}else{
			//无id，则置空
			stcdList = new ArrayList<>(Arrays.asList(""));
		}
		params.put("stcdList", stcdList);
		
		// 分页
	    String pageNo = request.getParameter("pageNo");
	    int count = this.iraFAllDetailsService.getStatisCount(params);
	    PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("start", pagingBean.getStart());
		params.put("limit", pagingBean.getLimit());
		
		//获取统计值
		List<IraFAllDetails> detList = this.iraFAllDetailsService.getStatisList(params);
		if(detList != null && detList.size()>0){
			for (IraFAllDetails model : detList) {
				Map<String, Object> dParams = new HashMap<String, Object>();
				dParams.put("beginTime", beginTime);
				dParams.put("endTime", endTime);
				dParams.put("stcd", model.getStcd());
				dParams.put("orderByCon", "TM ASC");
				dParams.put("specialCon", "FlowrateAdd IS NOT NULL");
				IraFAllDetails min = this.iraFAllDetailsService.getLastest(dParams);
				model.setFlowrateaddmin(min!=null?min.getFlowrateadd():null);
				dParams.put("orderByCon", "TM DESC");
				IraFAllDetails max = this.iraFAllDetailsService.getLastest(dParams);
				model.setFlowrateaddmax(max!=null?max.getFlowrateadd():null);
			}
		}
		mav.addObject("detList", detList);
		mav.addObject("detListJson", JSONObject.toJSONString(detList));
		mav.addObject("pagingBean", pagingBean);
		mav.addObject("query_beginTimeStr", PersonDateUtils.DateToString(beginTime, "yyyy-MM-dd HH:mm:ss"));
		mav.addObject("query_endTimeStr", PersonDateUtils.DateToString(endTime, "yyyy-MM-dd HH:mm:ss"));
		return mav;
	}
	
	/**
	 * 列表数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/detailList")
	public ModelAndView detailList(HttpServletRequest request, String stcd, String query_beginTime, String query_endTime){
		ModelAndView mav = new ModelAndView(detailListJsp);
		
		String pattern = "yyyy-MM-dd HH:mm:ss";
		Date beginTime = PersonDateUtils.StringToDate(query_beginTime, pattern);
		Date endTime = PersonDateUtils.StringToDate(query_endTime, pattern);;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("stcd", stcd);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		
		// 分页
	    String pageNo = request.getParameter("pageNo");
	    int count = this.iraFAllDetailsService.getCount(params);
	    PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", count, PagingBean.DEFAULT_PAGE_SIZE);
		params.put("start", pagingBean.getStart());
		params.put("limit", pagingBean.getLimit());
		
		//获取详细值
		List<IraFAllDetails> detList = this.iraFAllDetailsService.getList(params);
		mav.addObject("detList", detList);
		mav.addObject("stcd", stcd);
		mav.addObject("pagingBean", pagingBean);
		return mav;
	}
	
	/**
	 * 导出excel数据
	 * @param response
	 * @param groupId
	 */
	@RequestMapping(value = "/exportExcelData", method = RequestMethod.POST)
	@ResponseBody
	public String exportExcelData(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> hm = new HashMap<String, Object>();
		String beginTimeStr = request.getParameter("beginTime");
		String endTimeStr = request.getParameter("endTime");

		// 文件名
		StringBuilder str = new StringBuilder();
		String fileName = null;
		
		String pattern = "yyyy-MM-dd HH:mm";
		Date beginTime = PersonDateUtils.StringToDate(beginTimeStr, pattern);
		Date endTime = PersonDateUtils.StringToDate(endTimeStr, pattern);
		
		//重新生成文件名 (xx设备_xx要素_xx时间_xx时间_5位随机数)
		str.setLength(0);
		str.append("通用报表");
		str.append("(");
		str.append(PersonDateUtils.DateToString(beginTime, "yyyyMMddHHmm"));
		str.append("-");
		str.append(PersonDateUtils.DateToString(endTime, "yyyyMMddHHmm"));
		str.append(")");
		str.append(".xls");

		try {
			fileName = dataWrite(request, str.toString());
			hm.put("success", true);
			hm.put("fileName", fileName);
		} catch (Exception e) {
			hm.put("success", false);
			e.printStackTrace();
		}
		return JSONObject.toJSONString(hm);
	}

	@RequestMapping("/download")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		// 获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载
		String fileNameStr = request.getParameter("fileName");
		String fileNameUtf = null;
		String fileName = null;
		try {
			fileNameUtf = java.net.URLDecoder.decode(fileNameStr, "UTF-8");
			fileName = java.net.URLEncoder.encode(fileNameUtf, "UTF-8");
			fileName = new String(fileNameUtf.getBytes("GB2312"), "ISO_8859_1");
		} catch (Exception e) {
			e.printStackTrace();
		}

		String basePath = PropertiesUtils.getPara("downFileDir");

		String savePath;
		savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
		// 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
		response.setContentType("multipart/form-data");
		// 2.设置文件头：最后一个参数是设置下载文件名
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		ServletOutputStream out;

		// 通过文件路径获得File对象
		File file = new File(savePath + fileNameUtf);

		try {
			FileInputStream inputStream = new FileInputStream(file);
			// 3.通过response获取ServletOutputStream对象(out)
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = inputStream.read(buffer)) != -1) {
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
	 * excel数据填充
	 */
	public String dataWrite(HttpServletRequest request, String fileName) {
		String listDataCache = request.getParameter("listDataCache");
		String beginTimeStr = request.getParameter("beginTime");
		String endTimeStr = request.getParameter("endTime");
		
		Date beginTime = PersonDateUtils.StringToDate(beginTimeStr, "yyyy-MM-dd HH:mm:ss");
		Date endTime = PersonDateUtils.StringToDate(endTimeStr, "yyyy-MM-dd HH:mm:ss");
		
		// 文件保存目录相对路径
		String basePath = PropertiesUtils.getPara("downFileDir");
		// 文件保存目录路径
		String savePath;
		savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
		// 文件不存在就创建
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		String fileFullPath = savePath + fileName;
		WritableWorkbook wwb = null;
		try {
			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
			wwb = Workbook.createWorkbook(new File(fileFullPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (wwb != null) {
			// 创建一个可写入的工作表
			List<IraFAllDetails> list = JSONObject.parseArray(listDataCache, IraFAllDetails.class);

			FontName defaultFont = WritableFont.createFont("宋体");
			WritableFont titleFont = new WritableFont(defaultFont, 14, WritableFont.BOLD);// 表头字体
			WritableFont titleFont2 = new WritableFont(defaultFont, 11, WritableFont.BOLD);// 表头字体
			WritableFont normalFont = new WritableFont(defaultFont, 10, WritableFont.NO_BOLD);// 普通字体
			WritableCellFormat headerFormat = new WritableCellFormat(titleFont);
			WritableCellFormat headerFormat2 = new WritableCellFormat(titleFont2);
			WritableCellFormat contentFormat = new WritableCellFormat(normalFont);

			try {
				// 水平居中
				headerFormat.setAlignment(Alignment.CENTRE);
				// 竖直方向居中对齐
				headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				// 水平居中
				headerFormat2.setAlignment(Alignment.CENTRE);
				// 竖直方向居中对齐
				headerFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
				contentFormat.setAlignment(Alignment.CENTRE);
				contentFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			} catch (WriteException e) {
				e.printStackTrace();
			}
			try {
				WritableSheet ws = wwb.createSheet("通用报表", 0);
				ws.mergeCells(0, 0, 13, 0);//设置第一列、第一行和 第13列、第一行合并
				String titleView = "通用报表";
				if(beginTime!=null){
					String bTime = PersonDateUtils.DateToString(beginTime, "yyyy/MM/dd HH:mm:ss");
					String eTime = PersonDateUtils.DateToString(endTime, "yyyy/MM/dd HH:mm:ss");
					titleView+="("+bTime+"至"+eTime+")";
				}
				Label headerTitle = new Label(0, 0, titleView, headerFormat);
				ws.addCell(headerTitle);
				
				//创建表头
				Label title1 = new Label(0, 1, "测站名称",headerFormat2);
				ws.addCell(title1);
				ws.setColumnView(0, 23);// 设置宽度
				
				ws.mergeCells(1, 1, 2, 1);
				Label title2 = new Label(1, 1, "电源电压(V)",headerFormat2);
				ws.addCell(title2);
				ws.setColumnView(1, 15);// 设置宽度
				
				ws.mergeCells(3, 1, 4, 1);
				Label title3 = new Label(3, 1, "水位(m)",headerFormat2);
				ws.addCell(title3);
				ws.setColumnView(2, 15);// 设置宽度
				
				ws.mergeCells(5, 1, 6, 1);
				Label title4 = new Label(5, 1, "每秒流量(m3/s)",headerFormat2);
				ws.addCell(title4);
				ws.setColumnView(3, 25);// 设置宽度
				
				ws.mergeCells(7, 1, 8, 1);
				Label title5 = new Label(7, 1, "每小时流量(m3/h)",headerFormat2);
				ws.addCell(title5);
				ws.setColumnView(4, 15);// 设置宽度
				
				ws.mergeCells(9, 1, 10, 1);
				Label title6 = new Label(9, 1, "累计流量(m3)",headerFormat2);
				ws.addCell(title6);
				ws.setColumnView(5, 15);// 设置宽度
				
				ws.mergeCells(11, 1, 12, 1);
				Label title7 = new Label(11, 1, "信号强度",headerFormat2);
				ws.addCell(title7);
				ws.setColumnView(6, 15);// 设置宽度
				ws.setColumnView(7, 15);// 设置宽度
				ws.setColumnView(8, 15);// 设置宽度
				ws.setColumnView(9, 15);// 设置宽度
				ws.setColumnView(10, 15);// 设置宽度
				ws.setColumnView(11, 15);// 设置宽度
				ws.setColumnView(12, 15);// 设置宽度
				ws.setColumnView(13, 15);// 设置宽度
				
				int row = 2;
				if(list != null){
					for (IraFAllDetails model : list) {
						ws.mergeCells(0, row, 0, row+2);
						Label labelC0 = new Label(0, row, model.getStnm(), contentFormat);// 第row行，第1列
						ws.addCell(labelC0);
						
						//电源电压
						ws.addCell(new Label(1, row, "最大值", contentFormat));
						ws.addCell(new Label(2, row, model.getVoltagemax().toString(), contentFormat));
						ws.addCell(new Label(1, row+1, "最小值", contentFormat));
						ws.addCell(new Label(2, row+1, model.getVoltagemin().toString(), contentFormat));
						ws.addCell(new Label(1, row+2, "平均值", contentFormat));
						ws.addCell(new Label(2, row+2, model.getVoltageavg().toString(), contentFormat));
						
						//水位
						ws.addCell(new Label(3, row, "最大值", contentFormat));
						ws.addCell(new Label(4, row, model.getWatermax().toString(), contentFormat));
						ws.addCell(new Label(3, row+1, "最小值", contentFormat));
						ws.addCell(new Label(4, row+1, model.getWatermin().toString(), contentFormat));
						ws.addCell(new Label(3, row+2, "平均值", contentFormat));
						ws.addCell(new Label(4, row+2, model.getWateravg().toString(), contentFormat));
						
						//每秒流量
						ws.addCell(new Label(5, row, "最大值", contentFormat));
						ws.addCell(new Label(6, row, model.getFlowratepersmax().toString(), contentFormat));
						ws.addCell(new Label(5, row+1, "最小值", contentFormat));
						ws.addCell(new Label(6, row+1, model.getFlowratepersmin().toString(), contentFormat));
						ws.addCell(new Label(5, row+2, "平均值", contentFormat));
						ws.addCell(new Label(6, row+2, model.getFlowratepersavg().toString(), contentFormat));
						
						//每小时流量
						ws.addCell(new Label(7, row, "最大值", contentFormat));
						ws.addCell(new Label(8, row, model.getFlowrateperhmax().toString(), contentFormat));
						ws.addCell(new Label(7, row+1, "最小值", contentFormat));
						ws.addCell(new Label(8, row+1, model.getFlowrateperhmin().toString(), contentFormat));
						ws.addCell(new Label(7, row+2, "平均值", contentFormat));
						ws.addCell(new Label(8, row+2, model.getFlowrateperhavg().toString(), contentFormat));
						
						//累计流量
						ws.addCell(new Label(9, row, "起始值", contentFormat));
						ws.addCell(new Label(10, row, model.getFlowrateaddmin().toString(), contentFormat));
						ws.addCell(new Label(9, row+1, "终止值", contentFormat));
						ws.addCell(new Label(10, row+1, model.getFlowrateaddmax().toString(), contentFormat));
						ws.addCell(new Label(9, row+2, "增量", contentFormat));
						ws.addCell(new Label(10, row+2, model.getFlowrateaddmax().subtract(model.getFlowrateaddmin()).toString(), contentFormat));
						
						//信号强度
						ws.addCell(new Label(11, row, "最大值", contentFormat));
						ws.addCell(new Label(12, row, model.getSignalintenmax().toString(), contentFormat));
						ws.addCell(new Label(11, row+1, "最小值", contentFormat));
						ws.addCell(new Label(12, row+1, model.getSignalintenmin().toString(), contentFormat));
						ws.addCell(new Label(11, row+2, "平均值", contentFormat));
						ws.addCell(new Label(12, row+2, model.getSignalintenavg().toString(), contentFormat));
						
						row += 3;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
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
	 * 使用 Map按key进行排序
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map sortMapByKey(Map map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map sortMap = new TreeMap<>(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}
	
}