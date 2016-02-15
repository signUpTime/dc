package com.qq.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qq.business.service.IFoodsService;
import com.qq.common.data.mapper.GoodsMapper;
import com.qq.common.domain.Goods;
import com.qq.common.domain.ResultDO;
import com.qq.common.domain.goodsVO.GoodsVO;
import com.qq.common.exception.BusinessException;
import com.qq.common.param.foods.FoodsParam;
import com.qq.common.util.PageUtil;

@Service
public class FoodsService implements IFoodsService{
	
	@Resource
	private GoodsMapper goodsMapper;
	
	@Override
	public ResultDO<List<GoodsVO>> queryFoodsList(FoodsParam param) {
		ResultDO<List<GoodsVO>> resultDO = new ResultDO<List<GoodsVO>>();
		PageUtil.getPageContition(param);
		int totalCount = goodsMapper.selectGoodsCount(param);
		int totalPage = PageUtil.getTotalPage(totalCount);
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		if(totalCount > 0) {
			list = goodsMapper.queryFoodList(param);
		}
		resultDO.setModel(list);
		resultDO.setTotalPage(totalPage);
		resultDO.setSize(param.getSize());
		return resultDO;
	}

	@Override
	public ResultDO<GoodsVO> selectFoodInfo(int id) {
		ResultDO<GoodsVO> result = new ResultDO<GoodsVO>();
		GoodsVO goodsVO = goodsMapper.selectFoodById(id);
		if(goodsVO != null) {
			result.setModel(goodsVO);
		} else {
			throw new BusinessException("餐品不存在");
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResultDO addFoods(Goods goods) {
		ResultDO resultDO = new ResultDO();
		goodsMapper.insertGoods(goods);
		resultDO.setResult(true);
		return resultDO;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResultDO deleteFoods(String goodsIds) {
		ResultDO resultDO = new ResultDO();
		String[] ids = goodsIds.split(",");
		if(ids.length > 0) {
			goodsMapper.deleteFoods(ids);
			resultDO.setResult(true);
		} else {
			throw new BusinessException("请选择正确的餐品！");
		}
		return resultDO;
	}

}
