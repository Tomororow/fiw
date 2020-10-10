package com.fourfaith.RemoteTopUp.service;

import com.fourfaith.RemoteTopUp.model.Orders;

public interface OrdersService {

	int deleteByPrimaryKey(String id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
    
   /**
    * @Title: updateOrderStatus
    * @方法描述: 修改订单状态，改为 支付成功，已付款; 同时新增支付流水
    * @param @param orderId
    * @param @param alpayFlowNum
    * @param @param paidAmount   
    * @return void  
    * @throws
    * @author 刘海年
    * @date 2018年8月14日-上午9:43:09
    */
	public void updateOrderStatus(String orderId, String alpayFlowNum, String paidAmount);
}
