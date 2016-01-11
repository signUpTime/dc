package com.qq.business.service;

import java.util.List;

import com.qq.common.domain.Order;
import com.qq.common.domain.ResultDO;
import com.qq.common.domain.goodsVO.OrderFoodVO;
import com.qq.common.domain.goodsVO.OrderUserVO;
import com.qq.common.param.order.OrderParam;

public interface IOrderService {

	@SuppressWarnings("rawtypes")
	ResultDO bookFood(OrderParam orderParam);

	ResultDO<List<OrderFoodVO>> queryTodayOrderFoodList(OrderParam orderParam);

	@SuppressWarnings("rawtypes")
	ResultDO updateTodayOrderBookStatus(OrderParam orderParam);

	ResultDO<List<OrderUserVO>> queryTodayOrderUserList(OrderParam orderParam);

	@SuppressWarnings("rawtypes")
	ResultDO updateOrderTakeFoodStatus(OrderParam orderParam);

	ResultDO<Order> queryMyTodayOrder(OrderParam orderParam);

	@SuppressWarnings("rawtypes")
	ResultDO cancelOrder(long orderId);

}
