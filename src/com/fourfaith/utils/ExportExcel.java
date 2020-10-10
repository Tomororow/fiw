package com.fourfaith.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @ClassName: ExportExcel
 * @Description: 导出Excel公共方法 easypoi
 * @Author: zhaojx
 * @Date: 2018年4月12日 下午3:07:54
 */
public class ExportExcel { 

    public static Logger logger = LoggerFactory.getLogger(ExportExcel.class);

    /**
     * 导出EXCEL
     * @param list 导出的数据集合
     * @param title 标题
     * @param sheetName 表名称
     * @param pojoClass 实体类型
     * @param fileName 文件名称
     * @param isCreateHeader
     * @param response
     * @author liuyun
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response){
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);
    }
    
    /**
     * 导出EXCEL
     * @param list 导出的数据集合
     * @param title 标题
     * @param sheetName 表名称
     * @param pojoClass 实体类型
     * @param fileName 文件名称
     * @param response
     * @author liuyun
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, HttpServletResponse response){
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }
    
    /**
     * 导出EXCEL
     * @param list 导出的数据集
     * @param fileName 文件名称
     * @param response
     * @author liuyun
     */
    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response){
        defaultExport(list, fileName, response);
    }

    /**
     * @Title: defaultExport
     * @Description: 导出工作表
     * @param: @param list
     * @param: @param pojoClass
     * @param: @param fileName
     * @param: @param response
     * @param: @param exportParams
     * @return: void
     */
    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null){
        	downLoadExcel(fileName, response, workbook);
        }
    }
    
    /**
     * 下载EXCEL
     * @param fileName 文件名称
     * @param response
     * @author liuyun
     */
    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
    
    /**
     * @Title: defaultExport
     * @Description: 导出工作表
     * @param: @param list
     * @param: @param fileName
     * @param: @param response
     * @return: void
     * @throws
     */
    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null);
        downLoadExcel(fileName, response, workbook);
    }
    
    /**
     * 导入EXCEL
     * @param filePath 文件路径
     * @param titleRows 标题行数
     * @param headerRows 页头行数
     * @param pojoClass 生成的实体对象类型
     * @author liuyun
     */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        params.setNeedSave(true);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
            logger.error("模板不能为空{}",e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return list;
    }
    
    /**
     * @Title: importExcel
     * @Description: 导入EXCEL
     * @param: @param file
     * @param: @param titleRows
     * @param: @param headerRows
     * @param: @param pojoClass
     * @param: @return
     * @return: List<T>
     * @throws
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (file == null){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
            logger.error("excel文件不能为空{}",e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return list;
    }
    
}