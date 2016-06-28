package com.qq.common.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qq.common.constants.CommonConstants;

public class HttpUtil {
	private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);
	
	public static final String postHttp(String url,String params,String encoding,Header... headers) throws UnsupportedEncodingException{
		String responseMsg="";
		//构造HttpClient的实例
		HttpClient httpClient=new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CommonConstants.connectionTimeout);
		//设置编码格式
		httpClient.getParams().setContentCharset(encoding);
		//构造PostMethod的实例
		PostMethod postMethod=new PostMethod(url);
		for (Header header : headers) {
			postMethod.setRequestHeader(header);
		}
		InputStream requestIO=new ByteArrayInputStream(params.getBytes("UTF-8"));   
		InputStreamRequestEntity entity=new InputStreamRequestEntity(requestIO);
		postMethod.setRequestEntity(entity);
		try {
			//执行postMethod,调用http接口
			httpClient.executeMethod(postMethod);
			log.info("send http request, url is "+url+", status code is"+postMethod.getStatusCode());
			if(200==postMethod.getStatusCode()){
				//读取内容
				responseMsg = postMethod.getResponseBodyAsString();
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//释放连接
			postMethod.releaseConnection();
		}
		return responseMsg;
	}
	

	/**
	 * @author 李鹏飞
	 * @Description 发送http请求
	 * @Time 2013-11-27
	 * @param url
	 * @param paramsList
	 * @param encoding
	 * @return
	 */
	public static String postHttp(String url, Map<String, String> params,String encoding,Header... headers) {
		String responseMsg = "";
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CommonConstants.connectionTimeout);
		// 设置编码格式
		httpClient.getParams().setContentCharset(encoding);
		// 构造PostMethod的实例
		PostMethod postMethod = new PostMethod(url);
		for (Header header : headers) {
			postMethod.setRequestHeader(header);
		}
		// 把参数值放入到PostMethod对象中
		postMethod.setRequestBody(assembleRequestParams(params));
		try {
			// 执行postMethod,调用http接口
			httpClient.executeMethod(postMethod);
			log.info("send http request, url is "+url+", status code is"+postMethod.getStatusCode());
			if(200==postMethod.getStatusCode()){
				//读取内容
				responseMsg = postMethod.getResponseBodyAsString();
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
		return responseMsg;
	}

	/**
	 * @author 李鹏飞
	 * @Description 组装参数
	 * @Time 2013-11-27
	 * @param params
	 * @return
	 */
	public static NameValuePair[] assembleRequestParams(
			Map<String, String> params) {
		Set<String> paramsSet = params.keySet();
		final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();
		for (Iterator<String> it = paramsSet.iterator(); it.hasNext();) {
			String key = it.next();
			nameValueList.add(new NameValuePair(key, params.get(key)));
		}
		return nameValueList.toArray(new NameValuePair[nameValueList.size()]);
	}
	
	public static final String postHttp(String url,InputStream inputStream) throws UnsupportedEncodingException{
		String responseMsg="";
		//构造HttpClient的实例
		HttpClient httpClient=new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CommonConstants.connectionTimeout);
		//构造PostMethod的实例
		PostMethod postMethod=new PostMethod(url);
		InputStreamRequestEntity entity=new InputStreamRequestEntity(inputStream);
		postMethod.setRequestEntity(entity);
		try {
			//执行postMethod,调用http接口
			httpClient.executeMethod(postMethod);
			log.info("send http request, url is "+url+", status code is"+postMethod.getStatusCode());
			if(200==postMethod.getStatusCode()){
				//读取内容
				responseMsg = postMethod.getResponseBodyAsString();
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//释放连接
			postMethod.releaseConnection();
		}
		return responseMsg;
		
	}
	public static final String getHttp(String url,String params,String encoding,Header... headers ){
		String responseMsg="";
		//构造HttpClient的实例
		HttpClient httpClient=new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CommonConstants.connectionTimeout);
		//组装get请求url
		if(params!=null){
			url=url+"?"+params;
		}
		
		//构造getMethod的实例
        GetMethod getMethod = new GetMethod(url);
        for (Header header : headers) {
        	getMethod.setRequestHeader(header);
		}
      //设置编码格式
        getMethod.getParams().setContentCharset(encoding);
        try {
        	//执行getMethod,调用http接口
			httpClient.executeMethod(getMethod);
			log.info("send http request, url is "+url+", status code is"+getMethod.getStatusCode());
			if(200==getMethod.getStatusCode()){
				//读取内容
				byte[] responseBody = getMethod.getResponseBody();
				//处理返回的内容
				responseMsg = new String(responseBody);
			}

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//释放连接
			getMethod.releaseConnection();
		}
		return responseMsg;
	}
	
	
	public static final String postImageHttp(String url, String encoding, String imageFile) throws FileNotFoundException{
		String responseMsg="";
		//构造HttpClient的实例
		HttpClient httpClient=new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CommonConstants.connectionTimeout);
		//设置编码格式
		httpClient.getParams().setContentCharset(encoding);
		//构造PostMethod的实例
		PostMethod postMethod=new PostMethod(url);
		
		byte[] srtbyte = imageFile.getBytes();
		ByteArrayPartSource  source=new ByteArrayPartSource(imageFile, srtbyte);
	            //FilePart：用来上传文件的类  
	        FilePart fp = new FilePart("filedata", source);  
	         Part[] parts = { fp };  
	          //对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装  
	         MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());  
	         postMethod.setRequestEntity(mre);  
		try {
			//执行postMethod,调用http接口 
			log.info("send http request, url is "+url+", status code is"+postMethod.getStatusCode());
			httpClient.executeMethod(postMethod);
			if(200==postMethod.getStatusCode()){
				//读取内容
				responseMsg = postMethod.getResponseBodyAsString();
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//释放连接
			postMethod.releaseConnection();
		}
		return responseMsg;
		
	}
	
	/**
	* @Title: postImageHttp
	* @Description: TODO 图片上传
	* @param @param url
	* @param @param encoding
	* @param @param filename
	* @param @param bytes
	* @param @return
	* @param @throws FileNotFoundException    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static final String postImageHttp(String url, String encoding, String filename, byte[] bytes) throws FileNotFoundException{
		String responseMsg="";
		//构造HttpClient的实例
		HttpClient httpClient=new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CommonConstants.connectionTimeout);
		//设置编码格式
		httpClient.getParams().setContentCharset(encoding);
		//构造PostMethod的实例
		PostMethod postMethod=new PostMethod(url);
		
		
		try {			
			ByteArrayPartSource  source=new ByteArrayPartSource(filename, bytes);
		            //FilePart：用来上传文件的类  
		        FilePart fp = new FilePart("filedata", source);  
		         Part[] parts = { fp };  
		          //对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装  
		         MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());  
		         postMethod.setRequestEntity(mre);  
			
			//执行postMethod,调用http接口 
			httpClient.executeMethod(postMethod);
			log.info("send http request, url is "+url+", status code is"+postMethod.getStatusCode());
			if(200==postMethod.getStatusCode()){
				//读取内容
				responseMsg = postMethod.getResponseBodyAsString();
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//释放连接
			postMethod.releaseConnection();
			
		}
		return responseMsg;
		
	}
	
	public static final String postImageHttp(String url, String encoding, File file) throws FileNotFoundException{
		String responseMsg="";
		//构造HttpClient的实例
		HttpClient httpClient=new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CommonConstants.connectionTimeout);
		//设置编码格式
		httpClient.getParams().setContentCharset(encoding);
		//构造PostMethod的实例
		PostMethod postMethod=new PostMethod(url);
		
		
		try {			
		            //FilePart：用来上传文件的类  
		        FilePart fp = new FilePart("filedata", file);  
		         Part[] parts = { fp };  
		          //对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装  
		         MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());  
		         postMethod.setRequestEntity(mre);  
			
			//执行postMethod,调用http接口 
		    log.info("send http request, url is "+url+", status code is"+postMethod.getStatusCode());
			httpClient.executeMethod(postMethod);
			if(200==postMethod.getStatusCode()){
				//读取内容
				responseMsg = postMethod.getResponseBodyAsString();
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//释放连接
			postMethod.releaseConnection();
		}
		return responseMsg;
		
	}
}
