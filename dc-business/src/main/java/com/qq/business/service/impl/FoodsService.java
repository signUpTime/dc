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
import com.qq.common.service.ICommonService;
import com.qq.common.service.IConfigService;
import com.qq.common.util.PageUtil;

@Service
public class FoodsService implements IFoodsService{
	
	@Resource
	private GoodsMapper goodsMapper;
	
	@Resource
	private IConfigService configService;
	
	@Resource
	private ICommonService commonService;
	
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
		for(GoodsVO goodsVO : list) {
				goodsVO.setPic(configService.get("getImgUrl")+goodsVO.getPic().replace("{0}", ""));
		}
		resultDO.setModel(list);
		resultDO.setTotalPage(totalPage);
		resultDO.setSize(param.getSize());
		return resultDO;
	}
	
	@Override
	public ResultDO<List<GoodsVO>> queryAdminFoodsList(FoodsParam param) {
		ResultDO<List<GoodsVO>> resultDO = new ResultDO<List<GoodsVO>>();
		PageUtil.getPageContition(param);
		int totalCount = goodsMapper.selectGoodsCount(param);
		int totalPage = PageUtil.getTotalPage(totalCount);
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		if(totalCount > 0) {
			list = goodsMapper.queryAdminFoodList(param);
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
		goodsVO.setPicName(goodsVO.getPic());
		goodsVO.setPic(configService.get("getImgUrl")+goodsVO.getPic().replace("{0}", ""));
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
	public ResultDO editFood(Goods goods) {
		ResultDO resultDO = new ResultDO();
		goodsMapper.editGood(goods);
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
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResultDO enableFoods(String goodsIds) {
		ResultDO resultDO = new ResultDO();
		String[] ids = goodsIds.split(",");
		if(ids.length > 0) {
			goodsMapper.enableFoods(ids);
			resultDO.setResult(true);
		} else {
			throw new BusinessException("请选择正确的餐品！");
		}
		return resultDO;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResultDO disableFoods(String goodsIds) {
		ResultDO resultDO = new ResultDO();
		String[] ids = goodsIds.split(",");
		if(ids.length > 0) {
			goodsMapper.disableFoods(ids);
			resultDO.setResult(true);
		} else {
			throw new BusinessException("请选择正确的餐品！");
		}
		return resultDO;
	}

}
