package com.qq.business.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qq.business.service.IOrderService;
import com.qq.common.domain.ResultDO;
import com.qq.common.domain.User;
import com.qq.common.domain.goodsVO.OrderFoodVO;
import com.qq.common.domain.goodsVO.OrderUserVO;
import com.qq.common.exception.BusinessException;
import com.qq.common.param.order.OrderParam;
import com.qq.common.util.RequestExtract;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Resource
	private IOrderService orderService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/bookFood.do")
	@ResponseBody
	public ResultDO orderFood(HttpServletRequest request,@RequestBody OrderParam orderParam) {
		User user = (User) RequestExtract.getAdminInfo(request);
		orderParam.setUserId(user.getId());
		ResultDO resultDO = new ResultDO();
		resultDO = orderService.bookFood(orderParam);
		return resultDO;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/cancelOrder.do")
	@ResponseBody
	public ResultDO cancelOrder(long orderId) {
		ResultDO resultDO = new ResultDO();
		resultDO = orderService.cancelOrder(orderId);
		return resultDO;
	}
	
	@RequestMapping("/todayOrderManage.do")
	public ModelAndView todayOrderManage() {
		return new ModelAndView("/WEB-INF/order/todayOrderManage.jsp");
	}
	
	@RequestMapping("/queryTodayOrderFoodList.do")
	public ModelAndView queryTodayOrderFoodList(@RequestBody OrderParam orderParam,ModelMap modelMap) {
		ResultDO<List<OrderFoodVO>> resutlDO = new ResultDO<List<OrderFoodVO>>();
		resutlDO = orderService.queryTodayOrderFoodList(orderParam);
		modelMap.addAttribute("orderFoodList", resutlDO.getModel());
		return new ModelAndView("/WEB-INF/order/todayOrderList.jsp");
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/updateTodayOrderBookStatus.do")
	@ResponseBody
	public ResultDO updateTodayOrderBookStatus(@RequestParam String goodsIds,@RequestParam int bookStatus) {
		ResultDO resultDO = new ResultDO();
		String[] ids = goodsIds.split(",");
		if(ids.length > 0) {
			OrderParam orderParam = new OrderParam();
			orderParam.setGoodsIds(ids);
			orderParam.setBookStatus(bookStatus);
			resultDO = orderService.updateTodayOrderBookStatus(orderParam);
		} else {
			throw new BusinessException("请选择正确的餐品！");
		}
		return resultDO;
	}
	
	@RequestMapping("/todayOrderUserManage.do")
	public ModelAndView todayOrderUserManage() {
		return new ModelAndView("/WEB-INF/order/todayOrderUserManage.jsp");
	}
	
	
	@RequestMapping("/queryTodayOrderUserList.do")
	public ModelAndView queryTodayOrderUserList(@RequestBody OrderParam orderParam,ModelMap modelMap) {
		ResultDO<List<OrderUserVO>> resutlDO = new ResultDO<List<OrderUserVO>>();
		resutlDO = orderService.queryTodayOrderUserList(orderParam);
		modelMap.addAttribute("orderUserList", resutlDO.getModel());
		return new ModelAndView("/WEB-INF/order/todayOrderUserList.jsp");
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/updateTodayOrderTakeFoodStatus.do")
	@ResponseBody
	public ResultDO updateTodayOrderTakeFoodStatus(@RequestParam String orderIds,@RequestParam int status) {
		ResultDO resultDO = new ResultDO();
		String[] ids = orderIds.split(",");
		if(ids.length > 0) {
			OrderParam orderParam = new OrderParam();
			orderParam.setOrderIds(ids);
			orderParam.setStatus(status);
			resultDO = orderService.updateOrderTakeFoodStatus(orderParam);
		} else {
			throw new BusinessException("请选择正确人员！");
		}
		return resultDO;
	}
	
	
}
