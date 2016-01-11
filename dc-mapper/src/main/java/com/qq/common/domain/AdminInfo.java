package com.qq.common.domain;

import java.io.Serializable;

public class AdminInfo implements Serializable{
	private static final long serialVersionUID = 3394476025239595461L;
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
