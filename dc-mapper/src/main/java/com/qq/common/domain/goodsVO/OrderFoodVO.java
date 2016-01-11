package com.qq.common.domain.goodsVO;


public class OrderFoodVO {
	private int goodsId;
	private String goodsName; //餐名
	private String shopName; //商家名
	private String goodsSource;
	private String shopSource;
	private int count;
	private int status; //员工取餐状态
	private int bookStatus; //管理员订餐状态
	
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(int bookStatus) {
		this.bookStatus = bookStatus;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsSource() {
		return goodsSource;
	}
	public void setGoodsSource(String goodsSource) {
		this.goodsSource = goodsSource;
	}
	public String getShopSource() {
		return shopSource;
	}
	public void setShopSource(String shopSource) {
		this.shopSource = shopSource;
	}
}
