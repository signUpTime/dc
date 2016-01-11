package com.qq.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.common.domain.ResultDO;

public class PageRedirectUtil {
	@SuppressWarnings("rawtypes")
	public static void redierct(HttpServletRequest request,HttpServletResponse response,String url) throws IOException{
		String accept=request.getHeader("Accept");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			if (accept.contains("application/json")) {
				ResultDO resultDO = new ResultDO();
				resultDO.setResult(false);
				resultDO.setResultMsg("登陆超时，请重新登陆");
				//暂时写在这，以后统一定义
				resultDO.setErrorCode(412);
				out.println(FastJsonUtil.toJSONString(resultDO));
			} else {
				out.println("<html>");
				out.println("<script>");
				out.println("parent.parent.location.href ='" + url + "'");
				out.println("</script>");
				out.println("</html>");
			}
		} catch (Exception e) {

		}finally{
			out.flush();
			out.close();
		}
	}
}
