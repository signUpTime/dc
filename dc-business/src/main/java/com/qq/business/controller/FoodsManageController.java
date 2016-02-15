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

import com.qq.business.service.IFoodsService;
import com.qq.business.service.IOrderService;
import com.qq.common.domain.Goods;
import com.qq.common.domain.Order;
import com.qq.common.domain.ResultDO;
import com.qq.common.domain.User;
import com.qq.common.domain.goodsVO.GoodsVO;
import com.qq.common.param.foods.FoodsParam;
import com.qq.common.param.order.OrderParam;
import com.qq.common.util.RequestExtract;

@Controller
@RequestMapping("/foods")
public class FoodsManageController {
	
	@Resource
	private IFoodsService foodsService;
	@Resource
	private IOrderService orderService;
	
	@RequestMapping("/toFoodsManage.do")
	public ModelAndView toFoodsManage() {
		return new ModelAndView("/WEB-INF/foods/index.jsp");
	}
	
	@RequestMapping("/getFoodsMenu.do")
	public ModelAndView getFoodsMenu(HttpServletRequest request,ModelMap modelMap) {
		User user = (User) RequestExtract.getAdminInfo(request);
		modelMap.addAttribute("user", user);
		return new ModelAndView("/WEB-INF/foods/menu.jsp");
	}
	
	@RequestMapping("/foodsList.do")
	public ModelAndView toFoodsList() {
		return new ModelAndView("/WEB-INF/foods/foodsManage.jsp");
	}
	
	@RequestMapping("/queryFoodsList.do")
	public ModelAndView queryFoodsList(@RequestBody FoodsParam param,ModelMap modelMap,HttpServletRequest request) {
		ResultDO<List<GoodsVO>> result = new ResultDO<List<GoodsVO>>();
		ResultDO<Order> myTodayOrder = new ResultDO<Order>();
		result = foodsService.queryFoodsList(param);
		User user = (User) RequestExtract.getAdminInfo(request);
		OrderParam orderParam = new OrderParam();
		orderParam.setUserId(user.getId());
		myTodayOrder = orderService.queryMyTodayOrder(orderParam);
		modelMap.addAttribute("foodList", result.getModel());
		modelMap.addAttribute("myTodayOrder", myTodayOrder.getModel());
		modelMap.addAttribute("totalPageNum", result.getTotalPage());
		modelMap.addAttribute("currentPageNum", result.getCurrentPage());
		return new ModelAndView("/WEB-INF/foods/foodsList.jsp");
	}
	
	@RequestMapping("/toFoodsAdminManage.do")
	public ModelAndView toFoodsAdminManage() {
		return new ModelAndView("/WEB-INF/foods/foodsAdminManage.jsp");
	}
	
	@RequestMapping("/queryAdminFoodsList.do")
	public ModelAndView queryAdminFoodsList(ModelMap modelMap,@RequestBody FoodsParam param) {
		ResultDO<List<GoodsVO>> result = new ResultDO<List<GoodsVO>>();
		result = foodsService.queryFoodsList(param);
		modelMap.addAttribute("foodList", result.getModel());
		return new ModelAndView("/WEB-INF/foods/foodAdminList.jsp");
	}
	
	@RequestMapping("/toEditFoodInfo.do")
	public ModelAndView toEditFoodInfo(ModelMap modelMap,int id){
		ResultDO<GoodsVO> result = new ResultDO<GoodsVO>();
		result = foodsService.selectFoodInfo(id);
		modelMap.addAttribute("food", result.getModel());
		return new ModelAndView("/WEB-INF/foods/editFood.jsp");
	}
	
	@RequestMapping("/toAddFoodInfo.do")
	public ModelAndView toAddFoodInfo() {
		return new ModelAndView("/WEB-INF/foods/addFood.jsp");
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/addFood.do")
	@ResponseBody
	public ResultDO addFoodInfo(@RequestBody Goods goods) {
		ResultDO resultDO = new ResultDO();
		resultDO = foodsService.addFoods(goods);
		return resultDO;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/deleteFoods.do")
	@ResponseBody
	public ResultDO deleteFood(@RequestParam String goodsIds) {
		ResultDO resultDO = new ResultDO();
		resultDO = foodsService.deleteFoods(goodsIds);
		return resultDO;
	}
	
}
