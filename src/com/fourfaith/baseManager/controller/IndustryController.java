package com.fourfaith.baseManager.controller;

import com.fourfaith.statisticAnalysis.model.IndustrialDevice;
import com.fourfaith.statisticAnalysis.model.IndustrialDeviceExvoit;
import com.fourfaith.statisticAnalysis.service.IndustrialdeviceService;
import com.fourfaith.utils.ExportExcel;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.aspectj.weaver.patterns.ExactTypePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping(value ="/industry")
public class IndustryController {

    protected static final String industryJSP="/page/Industry/Industry";
    protected static final String industryListJSP="/page/Industry/IndustryList";

    @Autowired
    private IndustrialdeviceService industrialdeviceService;

    @RequestMapping(value = "/industryinfo", method = RequestMethod.GET)
    public ModelAndView Industryinfo(HttpServletRequest request){
        ModelAndView mav = new ModelAndView(industryJSP);
        // 历史数据默认加载一个月
        Date date = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.DATE,-90);
        date=c.getTime();
        mav.addObject("sTime", date);
        mav.addObject("eTime", new Date());
        return mav;
    }

    @RequestMapping(value = "/industryinfoList", method = RequestMethod.POST)
    public ModelAndView IndustryinfoList(HttpServletRequest request){
        ModelAndView mav = new ModelAndView(industryListJSP);
        Map<String,Object> pageInfo = new HashMap<>();
        String deviceName = request.getParameter("deviceName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String pageNo = request.getParameter("pageNo");
        List<IndustrialDevice> list = new ArrayList<>();
        Page pageHelper = new Page();
        long count = 0;
        try {
            BigDecimal Indty = new BigDecimal("0");
            pageInfo.put("deviceName",deviceName);
            pageInfo.put("startTime",startTime);
            pageInfo.put("endTime",endTime);
            IndustrialDevice ind = industrialdeviceService.findWaterTotal(pageInfo);
            Indty = ind.getPositry()==null?new BigDecimal("0"):ind.getPositry();
            pageHelper = PageHelper.startPage(Integer.parseInt(StringUtils.isNotBlank(pageNo)?pageNo:"1"), 10);
            list = industrialdeviceService.selectHistoryList(pageInfo);
            count = pageHelper.getTotal();
            PagingBean pagingBean = PageUtil.page(pageNo, PagingBean.DEFAULT_PAGE_SIZE+"", (int) count, PagingBean.DEFAULT_PAGE_SIZE);
            mav.addObject("Indty", Indty);
            mav.addObject("Inderlist", list);
            mav.addObject("pagingBean", pagingBean);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/voltageReport")
    public void exportTt(String deviceName, String startTime, String endTime, HttpServletResponse response){
        Map<String,Object> pageInfo = new HashMap<>();
        pageInfo.put("deviceName",deviceName);
        pageInfo.put("startTime",startTime);
        pageInfo.put("endTime",endTime);
        try {
            List<IndustrialDeviceExvoit> list = industrialdeviceService.selectHistoryListExcel(pageInfo);
            for(IndustrialDeviceExvoit drt:list){
                drt.setTime(startTime+"-"+endTime);
            }
            ExportExcel.exportExcel(list,"工业机井监测数据导出","工业机井监测数据导出",IndustrialDeviceExvoit.class,"工业机井监测数据导出.xls",response);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
