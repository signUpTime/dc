package com.qq.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qq.business.service.IShopService;
import com.qq.common.data.mapper.ShopMapper;
import com.qq.common.domain.ResultDO;
import com.qq.common.domain.Shop;

@Service
public class ShopService implements IShopService{
	@Resource
	private ShopMapper shopMapper;
	
	@Override
	public ResultDO<List<Shop>> queryShopList(String name) {
		ResultDO<List<Shop>> resultDO = new ResultDO<List<Shop>>();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name);
		List<Shop> list = shopMapper.selectShopList(param);
		resultDO.setModel(list);
		resultDO.setResult(true);
		return resultDO;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResultDO addShop(Shop shop) {
		ResultDO resultDO = new ResultDO();
		shopMapper.insertShop(shop);
		resultDO.setResult(true);
		return resultDO;
	}
}
