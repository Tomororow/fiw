package com.fourfaith.utils;

/**
 * 公共的常量
 * @author administrator
 */
public class Constant {

	public static Integer delStatus = -1;// 删除状态为-1

	public static String xtglMenuCode = "4";// 系统设置4

	public static Integer organFlag = 1;// 机构状态--启用

	/******************** 和中心交互的标识符 **************************/
	public static String readFactory = "3A";// 中心站查询遥测站指定要素实时数据

	public static String readVersonStatus = "45";// 查询软件版本

	public static String rtuRestart = "E2";// RTU远程重启62

	public static String readDistanceConfig = "41";// 读取配置表

	public static String updateConfig = "40";// 修改配置表

	public static String rtuUpgrade = "E1";// RTU远程升级61

	/******************** 远程设置的标识符 **************************/
	public static String centerAddress = "01";// 中心站地址

	public static String pwd = "03";// 通信密码

	public static String number = "0F";// 通信号码

	public static String workModel = "0C";// 工作模式

	public static String main1TypeAddress = "04";// 中心站1主信道类型/地址

	public static String main2TypeAddress = "06";// 中心站2主信道类型/地址

	public static String main3TypeAddress = "08";// 中心站3主信道类型/地址

	public static String main4TypeAddress = "0A";// 中心站4主信道类型/地址

	public static String photo = "81";// 拍照

	public static String dropped = "82";// 撤销图像

	public static String readAlarmSetting = "43";// 报警设定读取

	public static String updateAlarmSetting = "42";// 报警设定配置

	/******************** 远程升级的标识符 **************************/
	public static String ip = "ip";// 服务器地址

	public static String port = "port";// 端口

	public static String var = "var";// 固件版本文件

	/******************** 短信接口发送验证名称和密码 **************************/
	public static final String ACCOUNT = "szzd00477"; // 验证名称

	public static final String ACCOUNT_PWD = "szzd00477ab"; // 验证密码
	
}