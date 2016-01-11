package com.qq.admin.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qq.common.constants.CommonConstants;
import com.qq.common.data.mapper.UserMapper;
import com.qq.common.domain.AdminInfo;
import com.qq.common.domain.User;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Resource
	private UserMapper userMapper;
	
	@RequestMapping("/login.do")
	public ModelAndView login(HttpServletRequest request,ModelMap modelMap) {
		User user = (User) request.getSession().getAttribute(CommonConstants.LOGINED_USER);
		if(user != null) {
			modelMap.addAttribute("user", user);
		} else {
			return new ModelAndView("/index.jsp");
		}
		return new ModelAndView("/WEB-INF/main.jsp");
	}

	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		AdminInfo adminInfo = (AdminInfo)request.getSession().getAttribute("loginUser");
		if(adminInfo != null) {
			//TODO 删除session中登录用户信息
		}
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("/WEB-INF/common/login.jsp").forward(request, response);
		return null;
	}
}
