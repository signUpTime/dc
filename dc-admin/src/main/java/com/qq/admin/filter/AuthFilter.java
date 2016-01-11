package com.qq.admin.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.authentication.AttributePrincipal;

import com.qq.common.constants.CommonConstants;
import com.qq.common.domain.User;

public class AuthFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getSession().getAttribute(CommonConstants.LOGINED_USER) == null) {
			AttributePrincipal principal = (AttributePrincipal) (req).getUserPrincipal();
			Map<String, Object> map = principal.getAttributes();
			User user = new User();
			for(String key:map.keySet()) {
				System.out.println(key+":"+map.get(key));
				switch (key) {
				case CommonConstants.USER_NAME:
					user.setName((String) map.get(key));
					break;
				case CommonConstants.USER_ACCOUNT_NAME:
					user.setAccountName((String) map.get(key));
					break;
				case CommonConstants.USER_MAIL:
					user.setMail((String) map.get(key));
					break;
				case CommonConstants.USER_DEPARTMENT:
					user.setDepartment((String) map.get(key));
					break;
				default:
					break;
				}
			}
			req.getSession().setAttribute(CommonConstants.LOGINED_USER, user);
		}
		
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
