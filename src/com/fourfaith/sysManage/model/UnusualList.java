package com.fourfaith.sysManage.model;

public enum UnusualList {
	YX_IO1_VALUE(0,"IO1值"),       // IO1值
	YX_IO2_VAlUE(1,"IO2值"),       // IO2值
	YX_IO3_VALUE(2,"IO3值"),       // IO3值
	YX_IO4_VALUE(3,"IO4值"),       // IO4值
	YX_PUMP_STATUS(4,"水泵状态"),       // 水泵状态
	YX_DOOR_STATUS(5,"开关门状态"),       // 开关门状态
	YX_CARD_STATUS(6,"上下卡状态"),       // 上下卡状态
	YX_MANCTRL_STATUS(7,"主控状态 "),      // 主控状态 
	YX_PMMP_ERR(8,"水泵异常"),       // 水泵异常
	YX_WATER_ERR(9,"水表异常"),       // 水表异常
	YX_ELECT_ERR(10,"电表异常"),       // 电表异常
	YX_TTS_ERR(11,"语音模块异常"),        // 语音模块异常
	YX_DOOR_OPEN(12,"开关门报警"),       // 开关门报警
	YX_VOLAGE_UNBALANCE(13,"电压不平衡"),     // 电压不平衡
	YX_ELECT_UNBALANCE(14,"电流不平衡"),      // 电流不平衡
	YX_NFC_ERR(15,"刷卡模块异常"),        // 刷卡模块异常
	YX_PHASE_ELEA_ERR(16,"A相电流异常"),      // A相电流异常
	YX_PHASE_ELEB_ERR(17,"B相电流异常"),      // B相电流异常
	YX_PHASE_ELEC_ERR(18,"C相电流异常"),      // C相电流异常
	YX_PHASE_VOLA_ERR(19,"A相电压异常"),      // A相电压异常
	YX_PHASE_VOLB_ERR(20,"B相电压异常"),      // B相电压异常
	YX_PHASE_VOLC_ERR(21,"C相电压异常"),      // C相电压异常
	YX_POWER_VOL_ERR(22,"电源电压不正常"),      // 电源电压不正常
	yx_DOWN_CARD(23,"下卡记录上报"),          // 下卡记录上报
	YX_TOTALFLOW_ERR(24,"水表表底异常 对应 TOTALFLOW_ERR_E"),      // 水表表底异常 对应 TOTALFLOW_ERR_E
	YX_TRIPS_TYPE(25,"跳闸记录类型"),       //跳闸记录类型
	YX_WATER_NOTFLOW(26,"无水检测状态"),      //无水检测状态
	YX_BCKCHNNA_STATE(27,"备用水量通道状态"),      //备用水量通道状态
	YX_WATER_NETELE(28,"无电检测状态"),      //无电检测状态
	YX_TOTALELECT_ERR(29,"电表表底异常 对应 TOTALELECT_ERR_E"),      //电表表底异常 对应 TOTALELECT_ERR_E
	YX_MAX(30,"超过界限");

   
   private int key;
   private String value;
   
   private UnusualList(int key, String value) {
		this.key = key;
		this.value = value;
	}
   
   public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static String toList(int sign) {
   	String name = "";
       for (UnusualList un:UnusualList.values()) {
       	if(un.getKey()==sign){
       		name = un.getValue();
       	}
       }
       return name;
   }
}
