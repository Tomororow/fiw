package com.fourfaith.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @ClassName: ConfUtils
 * @Description: 自动远程操作  配置文件读取信息
 * @Author: liuyun & zhaojx
 * @Date: 2018年8月21日
 */
public class ConfUtils {
	private static Logger logger = Logger.getLogger(ConfUtils.class);
	
	private static ConfUtils m_instance = new ConfUtils();
    public static ConfUtils getInstance(){
        return m_instance;
    }
    
    // 自动升级配置
    /** 需升级水管区域 */
    private String WaterArea;
    /** 需升级行政区域 */
    private String AreaId;
    /** 需升级机井水泵状态 */
    private String PumpState;
    /** 需升级机井上卡状态 */
    private String CardState;
    /** 需要升级的版本 */
    private String VerUpgrade;
    /** 自动升级开关 */
    private String UpgradeSwitch;
    /** 要升级的目标版本号 升级信息AT指令 */
    private String UpgradeVersions;
    /** 远程升级地址 */
    private String REMOTE_URL;
    
    // 自动设参配置
    /** 需要自动设参的版本 */
    private String VerAutoParam;
    /** 自动设参AT(电流过滤值) */
    private String DBGLDLParam;
    
    // 肃南专用 备份通道
    private String BFTDParam;
    
    public static ConfUtils getM_instance() {
		return m_instance;
	}
	public static void setM_instance(ConfUtils m_instance) {
		ConfUtils.m_instance = m_instance;
	}

	public String getWaterArea() {
		return WaterArea;
	}
	public void setWaterArea(String waterArea) {
		WaterArea = waterArea;
	}
	public String getAreaId() {
		return AreaId;
	}
	public void setAreaId(String areaId) {
		AreaId = areaId;
	}
	public String getPumpState() {
		return PumpState;
	}
	public void setPumpState(String pumpState) {
		PumpState = pumpState;
	}
	public String getCardState() {
		return CardState;
	}
	public void setCardState(String cardState) {
		CardState = cardState;
	}
	public String getVerUpgrade() {
		return VerUpgrade;
	}
	public void setVerUpgrade(String verUpgrade) {
		VerUpgrade = verUpgrade;
	}
	public String getUpgradeSwitch() {
		return UpgradeSwitch;
	}
	public void setUpgradeSwitch(String upgradeSwitch) {
		UpgradeSwitch = upgradeSwitch;
	}
	public String getUpgradeVersions() {
		return UpgradeVersions;
	}
	public void setUpgradeVersions(String upgradeVersions) {
		UpgradeVersions = upgradeVersions;
	}
	public String getREMOTE_URL() {
		return REMOTE_URL;
	}
	public void setREMOTE_URL(String rEMOTE_URL) {
		REMOTE_URL = rEMOTE_URL;
	}
	public String getVerAutoParam() {
		return VerAutoParam;
	}
	public void setVerAutoParam(String verAutoParam) {
		VerAutoParam = verAutoParam;
	}
	public String getDBGLDLParam() {
		return DBGLDLParam;
	}
	public void setDBGLDLParam(String dBGLDLParam) {
		DBGLDLParam = dBGLDLParam;
	}
	// 肃南专用 备份通道
	public String getBFTDParam() {
		return BFTDParam;
	}
	public void setBFTDParam(String bFTDParam) {
		BFTDParam = bFTDParam;
	}
	
	public ConfUtils()
    {
    	WaterArea = "";
    	AreaId = "";
    	PumpState = "";
    	CardState = "";
    	VerUpgrade = "";
    	UpgradeVersions = "";
    	UpgradeSwitch = "";
    	REMOTE_URL = "";
    	VerAutoParam = "";
    	DBGLDLParam = "";
    	BFTDParam = "";
    	init();
    }

    /**
     * 初始化属性设置对象 读取配置文件
     * @param _properties
     */
    public boolean init()
    {
//        InputStream oInputStream = null;
        //        	String url1 = this.getClass().getClassLoader().getResource("").getPath();
//        	String url = url1+"web.properties";
//        	String url = "/FF_IRA_WEB/src/web.properties";
//        	File file = new File(url);
		//oInputStream = new FileInputStream(file);
    	InputStream oInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("web.properties");
        
        if (null == oInputStream){
            return false;
        }
        
        // 输入有效则开始读取对应配置
        Properties properties = new Properties();
        try{
            properties.load(oInputStream);
        }catch (IOException e){
        	logger.info("配置信息读取失败",e);
        }
        
        try{
        	/** 自动升级配置  */
        	// 需升级水管区域
            String waterArea = properties.getProperty("WaterArea");
            if (null != waterArea && !waterArea.isEmpty()){
            	WaterArea = waterArea;
            }
            
            // 需升级行政区域
            String areaId = properties.getProperty("AreaId");
            if (null != areaId && !areaId.isEmpty()){
            	AreaId = areaId;
            }
            
            // 需升级机井水泵状态
            String pumpState = properties.getProperty("PumpState");
            if (null != pumpState && !pumpState.isEmpty()){
            	PumpState = pumpState;
            }
            
            // 需升级机井上卡状态
            String cardState = properties.getProperty("CardState");
            if (null != cardState && !cardState.isEmpty()){
            	CardState = cardState;
            }
            
            // 需要升级的版本
            String verUpgrade = properties.getProperty("VerUpgrade");
            if (null != verUpgrade && !verUpgrade.isEmpty()){
            	VerUpgrade = verUpgrade;
            }
            
            // 自动升级开关
            String upgradeSwitch = properties.getProperty("UpgradeSwitch");
            if (null != upgradeSwitch && !upgradeSwitch.isEmpty()){
            	UpgradeSwitch = upgradeSwitch;
            }
            
            // 升级信息AT指令
            String upgradeVersions = properties.getProperty("UpgradeVersions");
            if (null != upgradeVersions && !upgradeVersions.isEmpty()){
            	UpgradeVersions = upgradeVersions;
            }
            
            // 远程升级地址
            String remote = properties.getProperty("REMOTE_URL");
            if (null != remote && !remote.isEmpty()){
            	REMOTE_URL = remote;
            }
            
            /** 自动设参配置 */
            // 需要自动设参的版本
            String verAutoParam = properties.getProperty("VerAutoParam");
            if (null != verAutoParam && !verAutoParam.isEmpty()){
            	VerAutoParam = verAutoParam;
            }
            
            // 自动设参AT(电流过滤值)
            String DBGLDL = properties.getProperty("DBGLDLParam");
            if (null != DBGLDL && !DBGLDL.isEmpty()){
            	DBGLDLParam = DBGLDL;
            }
            
            // 肃南专用 备份通道
            String BFTD = properties.getProperty("BFTDParam");
            if (null != BFTD && !BFTD.isEmpty()){
            	BFTDParam = BFTD;
            }
        }catch (Exception e){
        	logger.info("配置信息读取失败", e);
        }
        finally{
        	try{
				oInputStream.close();
			}catch (IOException e){
        		logger.info("配置信息读取失败", e);
			}
        }
        return true;
    }
    
}