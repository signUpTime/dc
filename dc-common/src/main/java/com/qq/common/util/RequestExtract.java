package com.qq.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.qq.common.constants.CommonConstants;
import com.qq.common.domain.AdminInfo;
import com.qq.common.exception.BusinessException;

public class RequestExtract {

	public final static AdminInfo getAdminInfo(HttpServletRequest httpRequest){
		HttpSession session= httpRequest.getSession();
		Object loginUserAttr = session.getAttribute(CommonConstants.LOGINED_USER);
		if(loginUserAttr != null){
			return (AdminInfo)loginUserAttr;
		}else{
			throw new BusinessException("session不存在");
		}
	}
}
