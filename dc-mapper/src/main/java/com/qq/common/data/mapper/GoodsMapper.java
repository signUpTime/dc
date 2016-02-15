package com.qq.common.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qq.common.domain.Goods;
import com.qq.common.domain.goodsVO.GoodsVO;
import com.qq.common.param.foods.FoodsParam;

public interface GoodsMapper {
	public List<Goods> selectGoodsList();

	public int selectGoodsCount(FoodsParam param);

	public List<GoodsVO> queryFoodList(FoodsParam param);

	public GoodsVO selectFoodById(int id);

	public void insertGoods(Goods goods);

	public void deleteFoods(@Param("ids")String[] ids);
}
