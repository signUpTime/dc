package com.qq.common.exception;

/**
 * 系统业务异常，message用于向前端返回错误信息
 */
public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 3416547891266313424L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public BusinessException(String message){
		this.message=message;
	}
}
