package com.qq.business.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.qq.business.service.IFoodsService;
import com.qq.business.service.IOrderService;
import com.qq.common.domain.Goods;
import com.qq.common.domain.GoodsPic;
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
		result = foodsService.queryAdminFoodsList(param);
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
	
	@RequestMapping("/pictureSerialzeValidation.do")
	@ResponseBody
	public Map<String, String> pictureSerialzeValidation(@RequestParam MultipartFile pic, HttpServletRequest request) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		byte[] bytes = pic.getBytes();
		GoodsPic goodsPic = new GoodsPic();
		goodsPic.setPicBytes(bytes);
		ResultDO<String> result = foodsService.addTmpFoodPic(goodsPic);
		map.put("picId", result.getModel());
		return map;
	}
	
	@RequestMapping("/getFoodPic.do")
	public void getFoodPic(@RequestParam int id, HttpServletResponse response) throws IOException {
		ResultDO<byte[]> result= foodsService.selectPicBytesById(id);
		byte[] bytes = result.getModel();
		if(bytes == null)
			return;
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		ServletOutputStream output=null;
		ImageOutputStream imageOut=null;
		try {
			BufferedImage image = ImageIO.read(in);
			output=response.getOutputStream();
			imageOut = ImageIO.createImageOutputStream(output);
			ImageIO.write(image, "png", imageOut);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			imageOut.close();
		}
	}
	 
	@SuppressWarnings("rawtypes")
	@RequestMapping("/addFood.do")
	@ResponseBody
	public ResultDO addFoodInfo(@RequestBody Goods goods,@RequestParam int picId ) {
		ResultDO resultDO = new ResultDO();
		resultDO = foodsService.addFoods(goods, picId);
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
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/enableFoods.do")
	@ResponseBody
	public ResultDO enableFood(@RequestParam String goodsIds) {
		ResultDO resultDO = new ResultDO();
		resultDO = foodsService.enableFoods(goodsIds);
		return resultDO;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/editFood.do")
	@ResponseBody
	public ResultDO editFood(@RequestBody Goods goods,@RequestParam int picId) {
		ResultDO resultDO = new ResultDO();
		resultDO = foodsService.editFood(goods, picId);
		return resultDO;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/disableFoods.do")
	@ResponseBody
	public ResultDO disableFood(@RequestParam String goodsIds) {
		ResultDO resultDO = new ResultDO();
		resultDO = foodsService.disableFoods(goodsIds);
		return resultDO;
	}
	
}
