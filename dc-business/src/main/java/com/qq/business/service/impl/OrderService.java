package com.qq.business.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qq.business.service.IOrderService;
import com.qq.common.constants.CommonConstants;
import com.qq.common.data.mapper.OrderMapper;
import com.qq.common.domain.Order;
import com.qq.common.domain.ResultDO;
import com.qq.common.domain.goodsVO.OrderFoodVO;
import com.qq.common.domain.goodsVO.OrderUserVO;
import com.qq.common.exception.BusinessException;
import com.qq.common.param.order.OrderParam;
import com.qq.common.service.IConfigService;

@Service
public class OrderService implements IOrderService{
	
	@Resource
	private IConfigService configServiceImpl;
	
	@Resource
	private OrderMapper orderMapper;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResultDO bookFood(OrderParam orderParam) {
		ResultDO resultDO = new ResultDO();
		//1.每天下午四点之前才能订餐
		//2.每人每天只能预订一次
		try {
			//判断订餐时间是否合法
			if(isLegalTime()){
				orderParam = setLegalOrderTime(orderParam);
				//查询当前用户今天是否订餐
				Order orderInfo = orderMapper.selectOrderByUserAndTime(orderParam);
				if(orderInfo == null) {
					orderMapper.insertOrder(orderParam);
				} else {
					//修改所订餐品
					orderParam.setId(orderInfo.getId());
					orderMapper.updateOrderFood(orderParam);
					/*throw new BusinessException("您今天已经订餐！");*/
				}
			} else {
				throw new BusinessException("订餐失败！必须在每天："+configServiceImpl.get(CommonConstants.END_ORDER_FOOD_TIME)+"之前订餐");
			}
		} catch (ParseException e) {
			e.printStackTrace();
			resultDO.setResult(false);
			resultDO.setResultMsg(e.getMessage());
		}
		resultDO.setResult(true);
		return resultDO;
	}
	

	@SuppressWarnings("rawtypes")
	@Override
	public ResultDO cancelOrder(long orderId) {
		ResultDO resultDO = new ResultDO();
		try {
			if(isLegalTime()) {
				int count = orderMapper.deleteOrder(orderId);
				if(count > 0) {
					resultDO.setResult(true);
				} else {
					resultDO.setResult(false);
					resultDO.setResultMsg("取消订单失败！");
				}
			} else {
				throw new BusinessException("取消失败！必须在每天："+configServiceImpl.get(CommonConstants.END_ORDER_FOOD_TIME)+"之前才能取消订餐");
			}
		} catch (ParseException e) {
			e.printStackTrace();
			resultDO.setResult(false);
			resultDO.setResultMsg(e.getMessage());
		}
		
		return resultDO;
	}
	
	@Override
	public ResultDO<List<OrderFoodVO>> queryTodayOrderFoodList(	OrderParam orderParam) {
		ResultDO<List<OrderFoodVO>> resultDO = new ResultDO<List<OrderFoodVO>>();
		List<OrderFoodVO> list = orderMapper.queryTodayOrderFoodList(setLegalOrderTime(orderParam));
		resultDO.setModel(list);
		resultDO.setResult(true);
		return resultDO;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResultDO updateTodayOrderBookStatus(OrderParam orderParam) {
		ResultDO resultDO = new ResultDO();
		setLegalOrderTime(orderParam);
		orderMapper.updateTodayOrderBookStatus(orderParam);
		resultDO.setResult(true);
		return resultDO;
	}
	
	@Override
	public ResultDO<List<OrderUserVO>> queryTodayOrderUserList(OrderParam orderParam) {
		ResultDO<List<OrderUserVO>> resultDO = new ResultDO<List<OrderUserVO>>();
		List<OrderUserVO> list = orderMapper.queryTodayOrderUserList(setLegalOrderTime(orderParam));
		resultDO.setModel(list);
		resultDO.setResult(true);
		return resultDO;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResultDO updateOrderTakeFoodStatus(OrderParam orderParam) {
		ResultDO resultDO = new ResultDO();
		orderMapper.updateOrderTakeFoodStatus(orderParam);
		resultDO.setResult(true);
		return resultDO;
	}
	
	@Override
	public ResultDO<Order> queryMyTodayOrder(OrderParam orderParam) {
		ResultDO<Order> resultDO = new ResultDO<Order>();
		Order myTodayOrder = orderMapper.queryMyTodayOrder(setLegalOrderTime(orderParam));
		resultDO.setModel(myTodayOrder);
		resultDO.setResult(true);
		return resultDO;
	}

	private OrderParam setLegalOrderTime(OrderParam orderParam) {
		OrderParam legalParam = orderParam;
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat(CommonConstants.DATE_FORMART_DATE_SPACES);
		String startTime = df.format(d);
		String endTime = df.format(d);
		startTime += CommonConstants.START_DAY_TIME;
		endTime += CommonConstants.END_DAY_TIME;
		legalParam.setStartTime(startTime);
		legalParam.setEndTime(endTime);
		return legalParam;
	}

	/**
	 * 判断当前时间是否大于下午四点
	 * @return
	 * @throws ParseException 
	 */
	private boolean isLegalTime() throws ParseException {
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat(CommonConstants.DATE_FORMART_DATE_SPACES);
		String legalTimeStr = df.format(d);
		legalTimeStr += " " + configServiceImpl.get(CommonConstants.END_ORDER_FOOD_TIME);
		SimpleDateFormat legalDf = new SimpleDateFormat(CommonConstants.DATE_FORMART_TOTAL);
		Date legalTime = legalDf.parse(legalTimeStr);
		if(d.getTime() > legalTime.getTime()) {
			return false;
		} else {
			return true;
		}
	}
	
}
