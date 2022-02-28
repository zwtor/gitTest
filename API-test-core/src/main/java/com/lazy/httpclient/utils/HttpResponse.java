package com.lazy.httpclient.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 
 * @ClassName: HttpResponse
 * @Description:TODO(处理响应的结果)
 * @author: zhanglun
 * 
 */
public class HttpResponse {
	private org.apache.http.client.methods.CloseableHttpResponse res = null;

	public HttpResponse(org.apache.http.client.methods.CloseableHttpResponse res) {
		this.res = res;
	}

	public org.apache.http.HttpResponse response() {
		return res;
	}

	public String body() {
		return body(Charset.forName("utf-8"));
	}

	public String body(Charset charset) {
		try {
			return EntityUtils.toString(res.getEntity(), charset);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void close() {
		try {
			res.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String firstHeader(String name) {
		return res.getFirstHeader(name).getValue();
	}

	public String lastHeader(String name) {
		return res.getLastHeader(name).getValue();
	}

	public String statusLine() {
		return res.getStatusLine().toString();
	}

	public int statusCode() {
		return res.getStatusLine().getStatusCode();
	}

	/**
	 * 
	 * @Title: postJsonGetString @Description: TODO(将响应以string类型返回) @param: @param
	 * url @param: @param json @param: @return @return: String @throws
	 */
	public static String postJsonGetString(String url, String json) {
		String body = HttpRequest.post(url).contentType("application/json;charset=UTF-8").data(json).send().body();
		return body;
	}
	
	public static String postJson(String url, String json, String name, String value) {
		String body = HttpRequest.post(url).query(name, value).contentType("application/json;charset=UTF-8").data(json)
				.send().body();
		return body;
	}

	public static String deleteJson(String url, String json) {
		String body = HttpRequest.delete(url).contentType("application/json;charset=UTF-8").data(json).send().body();
		return body;
	}
	
	public static int  getstatusCode(String url,String token,String name ,String values) {
		 HttpResponse resp = HttpRequest.get(url).query(name, values).
		 query("access_token", token).contentType("application/json;charset=UTF-8")
			.send();
		return resp.statusCode();
	}
	public static int  getstatusCode(String url,String token,String name ,String values,String name1,String values1) {
		 HttpResponse resp = HttpRequest.get(url).query(name, values).query(name1, values1).
		 query("access_token", token).contentType("application/json;charset=UTF-8")
			.send();
		return resp.statusCode();
	}
	
	public static int  getstatusCode(String url,String token,String name ,String values,String name1,String values1
			,String name2,String value2) {
		 HttpResponse resp = HttpRequest.get(url).query(name, values).query(name1, values1).query(name2, value2).
		 query("access_token", token).contentType("application/json;charset=UTF-8")
			.send();
		return resp.statusCode();
	}
	
	public static int  getstatusCode(String url,String token,String name ,String values,String name1,String values1
			,String name2,String value2,String name3,String value3) {
		 HttpResponse resp = HttpRequest.get(url).query(name, values).query(name1, values1).query(name2, value2).query(name3, value3).
		 query("access_token", token).contentType("application/json;charset=UTF-8")
			.send();
		return resp.statusCode();
	}
	
	public static int  getstatusCode(String url,String token,String name ,String values,String name1,String values1
			,String name2,String value2,String name3,String value3,String name4,String value4) {
		 HttpResponse resp = HttpRequest.get(url).query(name, values).query(name1, values1).query(name2, value2).query(name3, value3).
		 query(name4,value4).query("access_token", token).contentType("application/json;charset=UTF-8")
			.send();
		return resp.statusCode();
	}
	
	/**
	 * 
	 * @Title: postJson @Description: TODO(将请求以json对象返回) @param: @param
	 * url @param: @param json @param: @return @return: JSONObject @throws
	 */
	public static JSONObject postJson(String url, String json) {
		return JSON.parseObject(postJsonGetString(url, json));
	}

	@Override
	public String toString() {
		return res.toString();
	}
}
