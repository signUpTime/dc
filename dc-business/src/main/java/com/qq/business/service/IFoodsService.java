package com.qq.business.service;

import java.util.List;

import com.qq.common.domain.Goods;
import com.qq.common.domain.GoodsPic;
import com.qq.common.domain.ResultDO;
import com.qq.common.domain.goodsVO.GoodsVO;
import com.qq.common.param.foods.FoodsParam;

public interface IFoodsService {

	ResultDO<List<GoodsVO>> queryFoodsList(FoodsParam param);
	
	ResultDO<List<GoodsVO>> queryAdminFoodsList(FoodsParam param);
	
	ResultDO<GoodsVO> selectFoodInfo(int id);
	
	@SuppressWarnings("rawtypes")
	ResultDO addFoods(Goods goods);

	@SuppressWarnings("rawtypes")
	ResultDO addFoods(Goods goods, int picId);
	
	@SuppressWarnings("rawtypes")
	ResultDO editFood(Goods goods);

	@SuppressWarnings("rawtypes")
	ResultDO editFood(Goods goods, int picId);
	
	@SuppressWarnings("rawtypes")
	ResultDO deleteFoods(String goodsIds);
	
	@SuppressWarnings("rawtypes")
	ResultDO enableFoods(String goodsIds);
	
	@SuppressWarnings("rawtypes")
	ResultDO disableFoods(String goodsIds);
	
	ResultDO<String> addTmpFoodPic(GoodsPic goodsPic);
	
	ResultDO<byte[]> selectPicBytesById(int id);
	


}
