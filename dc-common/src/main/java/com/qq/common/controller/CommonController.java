package com.qq.common.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qq.common.exception.BusinessException;


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
}
