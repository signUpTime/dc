package com.qq.common.service.impl;

import java.io.FileNotFoundException;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.qq.common.constants.CommonConstants;
import com.qq.common.service.ICommonService;
import com.qq.common.service.IConfigService;
import com.qq.common.util.HttpUtil;


@Service
public class CommonServiceImpl implements ICommonService{
	
	@Resource
	private IConfigService configServiceImpl;
	
	@Override
	public String getImageUrl(String imageFile, String channel, byte[] bytes) {
		String pictureUrl = "";
		try {
			pictureUrl = HttpUtil.postImageHttp(configServiceImpl.get("imgUploadUrl") + channel, CommonConstants.UTF8,
					imageFile, bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return pictureUrl;
	}


}
