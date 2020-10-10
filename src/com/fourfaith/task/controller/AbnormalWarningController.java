package com.fourfaith.task.controller;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fourfaith.alarmManage.model.WarningRecordDetail;
import com.fourfaith.alarmManage.service.WarningRecordDetailService;
import com.fourfaith.paramterManage.model.ParamPumpingWell;
import com.fourfaith.paramterManage.service.ParamPumpingWellService;
import com.fourfaith.statisticAnalysis.service.UseWaterService;

public class AbnormalWarningController {
	
	Logger logger = Logger.getLogger(WarningRecordDetail.class);
	
	private Date preTime = new Date();
	
	@Autowired
	private static WarningRecordDetailService warningRecordDetailService;
	
	@Autowired
	private static UseWaterService useWaterService;
	
	@Autowired
	private static ParamPumpingWellService paramWellService;
	
	// 注入的时候，给类的 service 注入
    @Autowired
    public void setChatService(WarningRecordDetailService warningRecordDetailService,UseWaterService useWaterService,ParamPumpingWellService paramWellService) {
    	AbnormalWarningController.warningRecordDetailService = warningRecordDetailService;
    	AbnormalWarningController.useWaterService = useWaterService;
    	AbnormalWarningController.paramWellService = paramWellService;
    }
	/**
	 * 项目启动即开启一个新的线程(过滤异常数据并预警...)
	 */
	public void taskMainWay(){
		new Thread(){
			@Override
			public void run() {
				try {
					logger.info("---开始扫描异常数据信息【"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(preTime)+"】----------");
					taskRun();//执行水量预警的逻辑运算方法
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	/**
	 * 逻辑执行方法
	 */
	private void taskRun() {
		//第一步：首先查询（ParamPumpingWell）表设定的开始时间的最小值（如果当前时间小于设定时间）就不执行预警逻辑
		ParamPumpingWell ppw = paramWellService.selecTimeMin();
		if(ppw!=null){
			/*if(timeCompareTo(new Date(),timeCycle(ppw.getStartTime(),1))){
				
			}*/
		}
		/*List<ParamPumpingWell> ppwList = paramWellService.selectAll();
		if(ppwList.size()>0){
			
		}*/
	}
	
	/**
	 * 时间的转换（+-）
	 * @return
	 */
	public Date timeCycle(Date time,int num){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.DATE, num);
		Date date = cal.getTime();
		return date;
	}
	
	/**
	 * 时间比大小
	 * @param sTime
	 * @param eTime
	 * @return
	 */
	public boolean timeCompareTo(Date sTime,Date eTime){
		Calendar cal = Calendar.getInstance();
		cal.setTime(sTime);//当前时间
		long sTimel = cal.getTimeInMillis();
		cal.setTime(eTime);//结束时间
		long eTimel = cal.getTimeInMillis();
		if((sTimel-eTimel)>=0){
			return true;
		}else{
			return false;
		}
	}
}













