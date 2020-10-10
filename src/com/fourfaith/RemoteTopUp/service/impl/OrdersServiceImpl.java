package com.fourfaith.RemoteTopUp.service.impl;

import java.util.Date;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.fourfaith.RemoteTopUp.dao.FlowMapper;
import com.fourfaith.RemoteTopUp.dao.OrdersMapper;
import com.fourfaith.RemoteTopUp.model.Flow;
import com.fourfaith.RemoteTopUp.model.Orders;
import com.fourfaith.RemoteTopUp.service.OrdersService;
import com.fourfaith.RemoteTopUp.utils.OrderStatusEnum;

@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private FlowMapper flowMapper;
	
	@Autowired
	private Sid sid;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return ordersMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Orders record) {
		return ordersMapper.insert(record);
	}

	@Override
	public int insertSelective(Orders record) {
		return ordersMapper.insertSelective(record);
	}

	@Override
	public Orders selectByPrimaryKey(String id) {
		return ordersMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Orders record) {
		return ordersMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Orders record) {
		return ordersMapper.updateByPrimaryKey(record);
	}

	@Override
	public void updateOrderStatus(String orderId, String alpayFlowNum,
			String paidAmount) {
		Orders order = selectByPrimaryKey(orderId);
		if (order.getOrderStatus().equals(OrderStatusEnum.WAIT_PAY.key)) {
			order = new Orders();
			order.setId(orderId);
			order.setOrderStatus(OrderStatusEnum.PAID.key);
			order.setPaidTime(new Date());
			order.setPaidAmount(paidAmount);
			
			ordersMapper.updateByPrimaryKeySelective(order);
			
			order = selectByPrimaryKey(orderId);
			
			String flowId = sid.nextShort();
			Flow flow = new Flow();
			flow.setId(flowId);
			flow.setFlowNum(alpayFlowNum);
			flow.setBuyCounts(order.getBuyCounts());
			flow.setCreateTime(new Date());
			flow.setOrderNum(orderId);
			flow.setPaidAmount(paidAmount);
			flow.setPaidMethod(1);
			flow.setProductId(order.getProductId());
			
			flowMapper.insertSelective(flow);
		}
		
	}

}
