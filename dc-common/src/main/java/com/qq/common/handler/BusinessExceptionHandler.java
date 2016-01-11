package com.qq.common.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qq.common.domain.ResultDO;
import com.qq.common.exception.BusinessException;

@ControllerAdvice
public class BusinessExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object exceptionResponse(Exception ex, HttpServletRequest request, HttpServletResponse response){
		String accept=request.getHeader("Accept");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		ResultDO resultDO=new ResultDO();
		resultDO.setResult(false);
		if(accept.contains("application/json")){
			if(ex instanceof BusinessException){
				BusinessException e=(BusinessException)ex;
				resultDO.setResultMsg(e.getMessage());
			}else{
				logger.error("", ex);
				resultDO.setResultMsg("系统异常:"+ex.getMessage());
			}
			return resultDO;
		}else{
			PrintWriter out=null;
			try {
				out = response.getWriter();
				if(ex instanceof BusinessException){
					BusinessException e=(BusinessException)ex;
					out.println(e.getMessage());
				}else{
					logger.error("", ex);
					out.println(ex.getMessage());
				}
			} catch (IOException e1) {
			}finally{
				out.flush();
				out.close();
			}
			return "";
		}
	}
}
