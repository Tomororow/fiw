package com.fourfaith.sysManage.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum TriplList {
	
	TRIPS_TYPE_NOFLOW(0,"无水检测跳闸"),    //无水检测跳闸
	 TRIPS_TYPE_NOELE(1,"无电检测跳闸"),    //无电检测跳闸
	 TRIPS_TYPE_WATER_ERR(2,"水表通讯异常跳闸"),   //水表通讯异常跳闸
	 TRIPS_TYPE_ELECT_ERR(3,"电表通讯异常跳闸"),   //电表通讯异常跳闸
	 TRIPS_TYPE_FLOW_OVER(4,"无剩余水量跳闸"),   //无剩余水量跳闸
	 TRIPS_TYPE_CRAD_DOWN(5,"下卡跳闸"),   //下卡跳闸
	 TRIPS_TYPE_OPEN_DOOR(6,"开箱门跳闸"),   //开箱门跳闸
	 TRIPS_TYPE_START_AU(7,"启动保护跳闸-A项电压"),   //启动保护跳闸-A项电压
	 TRIPS_TYPE_START_BU(8,"启动保护跳闸-B项电压"),   //启动保护跳闸-B项电压
	 TRIPS_TYPE_START_CU(9,"启动保护跳闸-C项电压"),   //启动保护跳闸-C项电压
	 TRIPS_TYPE_ELECT_PROTECT_A(10,"电流保护跳闸-A项"),  //电流保护跳闸-A项
	 TRIPS_TYPE_ELECT_PROTECT_B(11,"电流保护跳闸-B项"),  //电流保护跳闸-B项
	 TRIPS_TYPE_ELECT_PROTECT_C(12,"电流保护跳闸-C项"),  //电流保护跳闸-C项
	 TRIPS_TYPE_VOLTAGE_PROTECT_A(13,"电压保护跳闸-A项"), //电压保护跳闸-A项
	 TRIPS_TYPE_VOLTAGE_PROTECT_B(14,"电压保护跳闸-B项"), //电压保护跳闸-B项
	 TRIPS_TYPE_VOLTAGE_PROTECT_C(15,"电压保护跳闸-C项"), //电压保护跳闸-C项
	 TRIPS_TYPE_VOLTAGE_BALANCE_A(16,"电压平衡跳闸-A项"), //电压平衡跳闸-A项
	 TRIPS_TYPE_VOLTAGE_BALANCE_B(17,"电压平衡跳闸-A项"), //电压平衡跳闸-A项
	 TRIPS_TYPE_VOLTAGE_BALANCE_C(18,"电压平衡跳闸-A项"), //电压平衡跳闸-A项
	 TRIPS_TYPE_ELECT_BALANCE_A(19,"电流平衡跳闸-A项"),  //电流平衡跳闸-A项
	 TRIPS_TYPE_ELECT_BALANCE_B(20,"电流平衡跳闸-B项"),  //电流平衡跳闸-B项
	 TRIPS_TYPE_ELECT_BALANCE_C(21,"电流平衡跳闸-C项"),  //电流平衡跳闸-C项
	 TRIPS_TYPE_BLCKCHNNA_NOELE(22,"备用通道无电跳闸"),  //备用通道无电跳闸
	 TRIPS_TYPE_WATERMETER_ZERO(23,"表底被清零"),  //表底被清零
	 TRIPS_TYPE_WATERMETER_MORE(24,"表底增量超过100方"),  //表底增量超过100方
	 TRIPS_TYPE_WATERMETER_LESS(25,"表底流量小"),  //表底流量小
	 TRIPS_TYPE_ELECT_ZERO(26,"电表被清零"),   //电表被清零
	 TRIPS_TYPE_ELECT_MORE(27,"电表增量超过阀值 （100度)"),   //电表增量超过阀值 （100度)
	 TRIPS_TYPE_ELECT_LESS(28,"电表电量小于存储电量"),   //电表电量小于存储电量
	 TRIPS_TYPE_MAX(29,"超过界限");//超过界限
    
    private int key;
    private String value;
    
    private TriplList(int key, String value) {
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
        for (TriplList un:TriplList.values()) {
        	if(un.getKey()==sign){
        		name = un.getValue();
        	}
        }
        return name;
    }
}
