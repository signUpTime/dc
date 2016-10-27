package com.qq.common.domain.goodsVO;

import com.qq.common.domain.Goods;

public class GoodsVO extends Goods{
	private String shopName;
	
	private String picName;
	
	private int picId;
	
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public int getPicId() {
		return picId;
	}

	public void setPicId(int picId) {
		this.picId = picId;
	}
	
	
	
}
