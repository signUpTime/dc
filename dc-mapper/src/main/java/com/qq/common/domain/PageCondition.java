package com.qq.common.domain;

/** 
* @ClassName: PageCondition 
* @Description: 分页类，所有需要分页显示的  request对象需要继承该对象
* @author pengfei li 
* @date 2015年1月21日 上午11:22:04 
*  
*/
public class PageCondition {
	/**
	 * request对象中表示当前的页码数(从1开始算起)
	 */
	private int pageNum; 
	
	/**
	 * request对象中表示当前每个分页的默认记录条数
	 */
	private int size; 
	
	/**
	 * 注：该属性是根据pageNum, size计算出来的，不是前端提交的，
	 * 由PageUtil.getPageContition(PageCondition pc)方法计算得来
	 */
	private int startNum; 
	
	/**
	 * 当前分页的页码
	 */
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * 当前分页的起始记录序号
	 */
	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	/**
	 * 每个分页中的记录数
	 */
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
