package com.qq.admin.inteceptor;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qq.common.constants.CommonConstants;
import com.qq.common.data.mapper.UserMapper;
import com.qq.common.domain.User;
import com.qq.common.util.PageRedirectUtil;

public class LoginInteceptor implements HandlerInterceptor {
	

	@Resource
	private UserMapper userMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(CommonConstants.LOGINED_USER);
		if(user != null) {
			if(user.getId() > 0) {
				return true;
			} else {
				User existedUser = userMapper.selectUserByMail(user);
				if(existedUser == null) {
					userMapper.insertUser(user);
					session.setAttribute(CommonConstants.LOGINED_USER, user);
				} else {
					session.setAttribute(CommonConstants.LOGINED_USER, existedUser);
				}
				return true;
			}
		} else {
			return redierct(request, response);
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	private boolean redierct(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String url = request.getRequestURI();
		// 判断获取的路径不为空且不是访问登录页面或执行登录操作时跳转
		if (url != null && !url.equals("")) {
			PageRedirectUtil.redierct(request,response, request.getContextPath()+"/index.jsp");
			return false;
		}
		return true;
	}

}
