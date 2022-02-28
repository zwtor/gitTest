package cn.kxy.localstorage.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.httpclient.utils.HttpRequest;
import okhttp3.*;

import java.io.IOException;

public class StorageBusiness {
	public static String enterpriseId=EnterpriseData.getEnterpriseId();
	
	public static String resourcemanager_url = EnterpriseDataUrl.getResourcemanagerUrl();
	
	public static String token = TokenData.getMangerToken();
	
	public static String resourcemanagerUrl = resourcemanager_url + "localstorage";
	
	public static String changeStorageStatusUrl (String id,String en_able) {
		return resourcemanagerUrl+ "/" +id+"/status/"+en_able;
	}
	
	public static String queryStoarageLog(String storage_id) {
		return HttpRequest.get(resourcemanager_url+ "resourcelog").header("x-access-token", token).query("access_token", token).query("page","0")
				.query("size","20").query("storage_id",storage_id).send().body();
	}
	
	public static String changeStorageStatus (String id,String en_able) {
		return HttpRequest.put(changeStorageStatusUrl(id,en_able)).header("x-access-token", token).data("{\"access_token\":\""+token+"\"}").send().body();
	}
	
	public static String queryResoureList() {
		return HttpRequest.get(resourcemanagerUrl).header("x-access-token", token).query("access_token",token).send().body();
	}
	
	public static String deleteStorage (String id) {
		return HttpRequest.delete(resourcemanagerUrl+"/"+id).header("x-access-token", token).send().body();
	}
	
	public static String addLocalstorage (String name ,String host,String storage_type) {
		
		OkHttpClient client = new OkHttpClient();
		final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		@SuppressWarnings("deprecation")
		RequestBody body = RequestBody.create(JSON, "{\r\n" + 
				"	\"name\": \""+name+"\",\r\n" + 
				"	\"host\": \""+host+"\",\r\n" + 
				"	\"storage_type\": \""+storage_type+"\",\r\n" + 
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}");
		Request request = new Request.Builder().url(resourcemanagerUrl).addHeader("x-access-token", token).post(body).build();
		String result="";
		try {
			Response response = client.newCall(request).execute();
			result = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result ;
	}
	
	public static String editLocalstorage (String id,String name ,String host,String storage_type) {
		
		OkHttpClient client = new OkHttpClient();
		final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		@SuppressWarnings("deprecation")
		RequestBody body = RequestBody.create(JSON, "{\r\n" + 
				"	\"name\": \""+name+"\",\r\n" + 
				"	\"host\": \""+host+"\",\r\n" + 
				"	\"storage_type\": \""+storage_type+"\",\r\n" + 
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}");
		Request request = new Request.Builder().url(resourcemanagerUrl+"/"+id).addHeader("x-access-token", token).post(body).build();
		String result="";
		try {
			Response response = client.newCall(request).execute();
			result = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result ;
	}
	
}
