package com.qq.common.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qq.common.exception.BusinessException;
import com.qq.common.service.ICommonService;
import com.qq.common.service.IConfigService;


@Controller
@RequestMapping("/common")
public class CommonController {
	private static final String WEB_ROOT = "dcWebApp.root";
	private static final String FOOD_PIC_LOC = File.separator+"images"+File.separator+"foods"+File.separator;
	
	@RequestMapping("/uploadPicture.do")
	@ResponseBody
	public Map<String,String> uploadPicture(@RequestParam MultipartFile pic,HttpServletRequest request) throws IOException {
		Map<String,String> result = new HashMap<String, String>();
		String picName = UUID.randomUUID().toString()+".png";
		String web_root = System.getProperty(WEB_ROOT);
		if(StringUtils.isEmpty(web_root)) {
			throw new BusinessException("not set web root !");
		}
		File picFile = new File(web_root+FOOD_PIC_LOC+picName);
		if(!picFile.exists()) 
			picFile.createNewFile();
		FileCopyUtils.copy(pic.getBytes(),picFile);
		result.put("picName",picName);
		return result;
	}
	
	
	@Resource
	private ICommonService commonService;
	
	@Resource
	private IConfigService configService;
	/**
	 * @Title: uploadMultimediaPicture
	 * @Description: TODO 图片上传
	 * @param @param pic
	 * @param @param request
	 * @param @return 设定文件
	 * @return Map<String,String> 返回类型
	 * @throws
	 */
	@RequestMapping("/uploadMultimediaPicture.do")
	@ResponseBody
	public Map<String, String> uploadMultimediaPicture(@RequestParam MultipartFile pic,@RequestParam String imageChannel, HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			byte[] bytes = pic.getBytes();

			String json = commonService.getImageUrl(pic.getOriginalFilename(), imageChannel, bytes);

			try {
				if (json != null && json != "") {
					JSONObject jsonObject = new JSONObject(json);
					String image_id = jsonObject.getString("image_id");
					String image_path = jsonObject.getString("image_path");
					String imagePath = image_path.replace("{0}", "");
					map.put("imageId", image_id);
					map.put("imagePath", image_path);
					map.put("imgUrl", configService.get("getImgUrl") + imagePath);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
