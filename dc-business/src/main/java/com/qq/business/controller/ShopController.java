package com.qq.business.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qq.business.service.IShopService;
import com.qq.common.domain.ResultDO;
import com.qq.common.domain.Shop;

@Controller
@RequestMapping("/shop")
public class ShopController {
	@Resource
	private IShopService shopService;
	
	@RequestMapping("/toShopManage.do")
	public ModelAndView toShopManage() {
		return new ModelAndView("/WEB-INF/shop/shopManage.jsp");
	}
	
	@RequestMapping("/toAddShop.do")
	public ModelAndView toAddShop() {
		return new ModelAndView("/WEB-INF/shop/addShop.jsp");
	}
	
	@RequestMapping("/queryShopList.do")
	public ModelAndView queryShopList(ModelMap modelMap,@RequestParam(required=false)String name) {
		ResultDO<List<Shop>> resultDO = new ResultDO<List<Shop>>();
		resultDO = shopService.queryShopList(name);
		modelMap.addAttribute("list", resultDO.getModel());
		return new ModelAndView("/WEB-INF/shop/shopList.jsp");
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/addShop.do")
	@ResponseBody
	public ResultDO addShop(@RequestBody Shop shop) {
		ResultDO resultDO = new ResultDO();
		resultDO = shopService.addShop(shop);
		return resultDO;
	}
}
