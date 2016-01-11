package com.qq.common.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.qq.common.service.IConfigService;

@Service
public class ConfigServiceImpl implements IConfigService ,InitializingBean{
	private String path;
	private Properties properties;


	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String get(String key) {
		return properties.getProperty(key);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		InputStreamReader inputStreamReader = null;
		try {
			inputStreamReader = new InputStreamReader(ConfigServiceImpl.class
					.getClassLoader().getResourceAsStream(path), "UTF-8");
			properties = new Properties();
			properties.load(inputStreamReader);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(inputStreamReader != null) {
					inputStreamReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
