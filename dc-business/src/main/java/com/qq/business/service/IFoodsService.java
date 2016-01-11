package com.qq.business.service;

import java.util.List;

import com.qq.common.domain.ResultDO;
import com.qq.common.domain.goodsVO.GoodsVO;
import com.qq.common.param.foods.FoodsParam;

public interface IFoodsService {

	ResultDO<List<GoodsVO>> queryFoodsList(FoodsParam param);

}
