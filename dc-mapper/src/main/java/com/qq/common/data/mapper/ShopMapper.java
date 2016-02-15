package com.qq.common.data.mapper;

import java.util.List;
import java.util.Map;

import com.qq.common.domain.Shop;

public interface ShopMapper {

	List<Shop> selectAllShops();

	List<Shop> selectShopList(Map<String, Object> param);

	void insertShop(Shop shop);

}
