package com.qq.common.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

public final class FastJsonUtil {
	
	  public static final String toPrettyJSONString(Object object) {
		  return JSON.toJSONString(object,SerializerFeature.PrettyFormat);
	  }
	
	
	  public static final String toJSONString(Object object, SerializerFeature... features) {
		  return JSON.toJSONString(object,features);
	  }
	  
	  public static final String toJSONStringWithDateFormat(Object object, String dateFormat,
              SerializerFeature... features) {
		  return JSON.toJSONStringWithDateFormat(object, dateFormat, features);
	  }
	  
	  public static final <T> T parseObject(String text, Class<T> clazz, Feature... features){
		 return  JSON.parseObject(text,clazz,features);
	  }
	  
	  public static final <T> List<T>  parseList(String text, Class<T> clazz){
		 return  JSON.parseArray(text, clazz);
	  }
	  
	  private FastJsonUtil(){}
}