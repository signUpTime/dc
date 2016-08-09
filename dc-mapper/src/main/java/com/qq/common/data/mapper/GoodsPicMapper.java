package com.qq.common.data.mapper;

import com.qq.common.domain.GoodsPic;

public interface GoodsPicMapper {
	public void addTmpFoodPic(GoodsPic goodsPic);
	
	public GoodsPic selectPicBytesById(int id);
	
	public int selectPicCountByFoodId(int id);
	
	public void bindPicWithFood(GoodsPic goodsPic);
	
	public void unbundPicWithFood(int id);
	
	public int selectPicIdByFoodId(int id);
	
}
