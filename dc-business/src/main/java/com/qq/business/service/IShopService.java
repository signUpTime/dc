package com.qq.business.service;

import java.util.List;

import com.qq.common.domain.ResultDO;
import com.qq.common.domain.Shop;

public interface IShopService {

	ResultDO<List<Shop>> queryShopList(String name,int destinationId);

	@SuppressWarnings("rawtypes")
	ResultDO addShop(Shop shop);

}
