package com.qq.common.util;

import java.util.Map;

import com.qq.common.constants.CommonConstants;
import com.qq.common.domain.PageCondition;

public class PageUtil {
	
	public static void getPageContition(PageCondition pageCondition){
		int pageNum=pageCondition.getPageNum();
		int pageSize=CommonConstants.PAGING_SIZE;
		int startNum=(pageNum-1)*pageSize;
		pageCondition.setStartNum(startNum);
		pageCondition.setSize(pageSize);
	}
	
	public static void getPageContition(PageCondition pageCondition,int pageSize){
		int pageNum=pageCondition.getPageNum();
		int startNum=(pageNum-1)*pageSize;
		pageCondition.setStartNum(startNum);
		pageCondition.setSize(pageSize);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void getPageContition(Map pageCondition){
		int pageNum=(int) pageCondition.get("pageNum");
		int pageSize=CommonConstants.PAGING_SIZE;
		int startNum=(pageNum-1)*pageSize;
		pageCondition.put("startNum", startNum);
		pageCondition.put("size", pageSize);
	}
	
	public static int getTotalPage(int count){
		if(count%CommonConstants.PAGING_SIZE==0){
			return count/CommonConstants.PAGING_SIZE;
		}else{
			return count/CommonConstants.PAGING_SIZE+1;
		}
	}
	
	public static int getTotalPage(int count,int pageSize){
		if(count%pageSize==0){
			return count/pageSize;
		}else{
			return count/pageSize+1;
		}
	}
}
