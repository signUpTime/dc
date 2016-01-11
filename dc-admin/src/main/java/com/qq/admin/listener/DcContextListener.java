package com.qq.admin.listener;

import java.util.Date;
import java.util.Random;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.qq.common.util.TimeUtil;


public class DcContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//容器启动时设置版本号，版本号为月+日 主要在jsp中使用 上线后重新获取js、css
		Date date=new Date();
		String version=TimeUtil.dateToString(date, "MMdd")+new Random().nextInt(100);
		System.setProperty("version", "?version="+version);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
