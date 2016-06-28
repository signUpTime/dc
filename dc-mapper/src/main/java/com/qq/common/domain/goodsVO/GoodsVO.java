package com.qq.common.domain.goodsVO;

import com.qq.common.domain.Goods;

public class GoodsVO extends Goods{
	private String shopName;
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	private String picName;
	
	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}
	
	
}
