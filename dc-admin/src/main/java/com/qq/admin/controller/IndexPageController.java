package com.qq.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qq.common.domain.User;
import com.qq.common.util.RequestExtract;


@Controller
@RequestMapping("/page")
public class IndexPageController {
	
	@RequestMapping("/main.do")
	public ModelAndView getMain(HttpServletRequest request,ModelMap modelMap) {
		return new ModelAndView("/WEB-INF/main.jsp");
	}
	
	@RequestMapping("/index.do")
	public ModelAndView getIndex(HttpServletRequest request){
		ModelAndView model=new ModelAndView();
		model.setViewName("/WEB-INF/index.jsp");
		return model;
	}
	
	/** 
	* @Title: getHeader 
	* @Description: 获取后台header
	* @param @param request
	* @param @return
	* @return ModelAndView
	* @throws 
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/getHeader.do")
	public ModelAndView getHeader(HttpServletRequest request){
		User loginUser=(User) RequestExtract.getAdminInfo(request);
		Map result=new HashMap();
		Date now = new Date();
		result.put("nowDate", now.getTime());
		result.put("user", loginUser);
		ModelAndView model=new ModelAndView("result", result);
		model.setViewName("/WEB-INF/common/head.jsp");
		return model;
	}
}
