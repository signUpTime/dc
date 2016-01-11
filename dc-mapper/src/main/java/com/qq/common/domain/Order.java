package com.qq.common.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


public class Order {
	private long id;
	private int userId;
	private int goodsId;
	private BigDecimal totalPrice;
	private int status; //员工取餐状态 0：下单成功员工未取餐 1：员工已取餐
	private int bookStatus;//管理员订餐状态 0：未订餐 1：管理员已订餐 2：管理员已收到订餐
	private int userReceiveStatus;
	private int isDeleted;
	private Date createTime;
	private Timestamp lastUpdateTime;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getUserReceiveStatus() {
		return userReceiveStatus;
	}
	public void setUserReceiveStatus(int userReceiveStatus) {
		this.userReceiveStatus = userReceiveStatus;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(int bookStatus) {
		this.bookStatus = bookStatus;
	}
	
}
