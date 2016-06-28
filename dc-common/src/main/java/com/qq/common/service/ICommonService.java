package com.qq.common.service;

public interface ICommonService {
	
	/**
	 * @Title: getImageUrl
	 * @Description: TODO 调用图片接口
	 * @param @param imageFile
	 * @param @param bytes
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String getImageUrl(String imageFile, String channel, byte[] bytes);

}
