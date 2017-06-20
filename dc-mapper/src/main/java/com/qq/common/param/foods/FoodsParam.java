package com.qq.common.param.foods;

import com.qq.common.domain.PageCondition;

public class FoodsParam extends PageCondition{
	private String foodsName;
	private String shopName;
	private int destinationId; //送餐地址
	
	public String getFoodsName() {
		return foodsName;
	}
	public void setFoodsName(String foodsName) {
		this.foodsName = foodsName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public int getDestinationId(){
		return destinationId;
	}
	public void setDestinationId(int destinationId){
		this.destinationId = destinationId;
	}
}
