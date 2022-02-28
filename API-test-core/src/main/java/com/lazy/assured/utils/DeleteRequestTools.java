package com.lazy.assured.utils;

import okhttp3.*;

import java.io.IOException;

public class DeleteRequestTools {
	@SuppressWarnings("deprecation")
	public static String  RequestBodyByDelete (String url,String json) {
		OkHttpClient client = new OkHttpClient();
		final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).delete(body).build();
		String result="";
		try {
			Response response = client.newCall(request).execute();
			result = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String  RequestBodyByput (String url,String json,String token) {
		OkHttpClient client = new OkHttpClient();
		final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).addHeader("x-access-token", token).put(body).build();
		String result="";
		try {
			Response response = client.newCall(request).execute();
			result = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
