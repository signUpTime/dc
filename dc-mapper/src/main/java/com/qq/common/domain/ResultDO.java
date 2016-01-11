package com.qq.common.domain;

/** 
* @ClassName: ResultDO 
* @Description: 所有返回内容经过resultDO封装，包含是否成功，错误信息
* @author pengfei li 
* @date 2015年1月23日 下午2:26:49 
* 
* @param <T> 
*/
public class ResultDO<T> {
	private T model;
	private boolean result;//请求是否异常，当为false时,k可能model为空
	private String resultMsg;//错误信息
	private int currentPage;
	private int totalPage;
	private int size;
	private int errorCode;
	public ResultDO(){}
	public ResultDO(boolean result){
		this.result=result;
	}
	public ResultDO(T t){
		this.model = t;
		this.result = true;
	}
	
	public T getModel() {
		return model;
	}
	public void setModel(T model) {
		this.model = model;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
