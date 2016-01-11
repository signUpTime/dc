package com.qq.admin.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.authentication.AttributePrincipal;

public class MainServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		AttributePrincipal principal = (AttributePrincipal) req.getUserPrincipal();
   	 	Map<String, Object> attributes = principal.getAttributes();
   	 	for(String key : attributes.keySet()) {
   	 		System.out.println(key + ":" + attributes.get(key));
   	 	}
	}
}
