package com.fourfaith.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

/**
 * httpUtils tools
 * @author wull
 */
public class httpUtils {
	
	protected static Logger logger = Logger.getLogger(httpUtils.class);
	
	/**
	 * 利用HttpClient发起POST请求，并接收返回的响应内容
	 * @param url 请交易或响应编号
	 * @param message 请求内容
	 * @return 响应内容
	 */
	public static String post(String url,NameValuePair... data) 
	{
		// 响应内容
		String result = "";
		// 定义http客户端对象--httpClient
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);//30秒socket建立链接的超时时间，Httpclient包中通过一个异步线程去创建socket链接，对应的超时控制。
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);///数据传输时间60s秒 连接池中取出连接的超时时间 
		// 定义并实例化客户端链接对象-postMethod
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		try {
			// 设置http的头
			postMethod.setRequestHeader("ContentType",
					"application/x-www-form-urlencoded;charset=UTF-8");
			// 填入各个表单域的值
//			NameValuePair[] data = { new NameValuePair("type", type),
//					new NameValuePair("message", message) };
			// 将表单的值放入postMethod中
			postMethod.setRequestBody(data);
			// 定义访问地址的链接状态
			int statusCode = 0;
			try {
				// 客户端请求url数据
				statusCode = httpClient.executeMethod(postMethod);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 请求成功状态-200
			if (statusCode == HttpStatus.SC_OK) {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader( postMethod.getResponseBodyAsStream(),"utf-8"));
				    String msg = null;
					while((msg = reader.readLine())!= null)  
				    {  
				    	result += msg;
				    }  
					return result;
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			} else {
				logger.error("请求返回状态：" + statusCode);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			// 释放链接
			postMethod.releaseConnection();
			httpClient.getHttpConnectionManager().closeIdleConnections(0);
		}
		return result;
	}
	
	/**
	 * 利用HttpClient发起POST请求，并接收返回的响应内容
	 * @param url 请求链接
	 * @param type 交易或响应编号
	 * @param message 请求内容
	 * @return 响应内容
	 */
	public static String post(String url,List<NameValuePair> list){
		// 响应内容
		String result ="";

		// 定义http客户端对象--httpClient
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);//30秒socket建立链接的超时时间，Httpclient包中通过一个异步线程去创建socket链接，对应的超时控制。
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);///数据传输时间60s秒 连接池中取出连接的超时时间 

		// 定义并实例化客户端链接对象-postMethod
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		try {
			// 设置http的头
			postMethod.setRequestHeader("ContentType",
					"application/x-www-form-urlencoded;charset=UTF-8");

			// 填入各个表单域的值
			NameValuePair[] data = new NameValuePair[list.size()];
			// 将表单的值放入postMethod中
			postMethod.setRequestBody(list.toArray(data));

			// 定义访问地址的链接状态
			int statusCode = 0;
			try {
				// 客户端请求url数据
				statusCode = httpClient.executeMethod(postMethod);
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 请求成功状态-200
			if (statusCode == HttpStatus.SC_OK) {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader( postMethod.getResponseBodyAsStream(),"utf-8"));
				    String msg = null;
					while((msg = reader.readLine())!= null)  
				    {  
				    	result += msg;
				    }  
					return result;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				logger.error("请求返回状态：" + statusCode);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			// 释放链接
			postMethod.releaseConnection();
			httpClient.getHttpConnectionManager().closeIdleConnections(0);
		}
		return result;
	}
	
	public static String get(String url) {  
		// 响应内容
		String result ="";

		// 定义http客户端对象--httpClient
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);//30秒socket建立链接的超时时间，Httpclient包中通过一个异步线程去创建socket链接，对应的超时控制。
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);///数据传输时间60s秒 连接池中取出连接的超时时间 
		// 定义并实例化客户端链接对象-postMethod
		GetMethod postMethod = new GetMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		// 设javascript:void(0);置http的头
		postMethod.setRequestHeader("ContentType",
				"application/x-www-form-urlencoded;charset=UTF-8");
		try {
			// 定义访问地址的链接状态
			int statusCode = 0;
			try {
				// 客户端请求url数据
				statusCode = httpClient.executeMethod(postMethod);
			}catch(java.net.ConnectException ex){
				logger.error("远程请求出错：=="+ex.getMessage());
			}
			// 请求成功状态-200
			if (statusCode == HttpStatus.SC_OK) {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader( postMethod.getResponseBodyAsStream(),"utf-8"));
				    String msg = null;
					while((msg = reader.readLine())!= null)  
				    {  
				    	result += msg;
				    }  	
					return result;
				} catch (IOException e) {
					//e.printStackTrace();
					logger.error("远程请求出错：=="+e.getMessage());
				}
			} else {
				logger.error("请求返回状态：" + statusCode);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			// 释放链接
			postMethod.releaseConnection();
			httpClient.getHttpConnectionManager().closeIdleConnections(0);
		}
		return result;
	}

}