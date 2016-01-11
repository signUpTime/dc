package com.qq.common.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qq.common.domain.Order;
import com.qq.common.domain.goodsVO.OrderFoodVO;
import com.qq.common.domain.goodsVO.OrderUserVO;
import com.qq.common.param.order.OrderParam;

public interface OrderMapper {

	Order selectOrderByUserAndTime(OrderParam orderParam);

	void insertOrder(OrderParam orderParam);

	List<OrderFoodVO> queryTodayOrderFoodList(OrderParam orderParam);

	int updateTodayOrderBookStatus(OrderParam orderParam);

	List<OrderUserVO> queryTodayOrderUserList(OrderParam setLegalOrderTime);

	void updateOrderTakeFoodStatus(OrderParam orderParam);

	Order queryMyTodayOrder(OrderParam setLegalOrderTime);

	int updateOrderFood(OrderParam orderParam);

	int deleteOrder(@Param("orderId") long orderId);

}
