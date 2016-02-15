package com.qq.common.tag;

import java.util.List;

import org.springframework.util.StringUtils;

import com.qq.common.data.mapper.ShopMapper;
import com.qq.common.domain.Shop;
import com.qq.common.util.SpringContextHolder;

public class SelectShopTag extends AbstractSelectTagSupport{
	private static final long serialVersionUID = 12741960564485577L;
	
	@Override
	public String setOptions() {
		ShopMapper shopMapper = SpringContextHolder.getBean("shopMapper");
		List<Shop> shops = shopMapper.selectAllShops();
		StringBuffer sb = new StringBuffer();
		for (Shop shop : shops) {
			if(!StringUtils.isEmpty(selectedValue) && selectedValue.equals(String.valueOf(shop.getId()))) {
				sb.append("<option value='"+shop.getId()+"' selected>").append(shop.getName()).append("</option>");
			} else {
				sb.append("<option value='"+shop.getId()+"' >").append(shop.getName()).append("</option>");
			}
		}
		return sb.toString();
	}

}
