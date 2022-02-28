package cn.kxy.community.group;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.DeleteRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class CircleBusiness {
	public static String group_url=  EnterpriseDataUrl.getGroupUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id=EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	
	public static String search_url  = group_url + "search";
	
	public static String add_url  = group_url + "space" ;
	
	public static String  statUrl(String id) {
		return group_url + "space/"+id+"/stat";
	}
	
	public static String  editUrl(String id) {
		return group_url + "space/"+id;
	}
	
	/**   
	 * @Title: queryStuCircle    
	 * @Description: TODO  查看学员端的圈子列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryStuCircle() {
		return HttpRequest.get(add_url + "/0").header("x-access-token", token).query("access-token", token).send().body();
	}
	
	public static String addCircle(String title) {
		return HttpRequest.post(add_url).header("x-access-token", token).data("{\r\n" + 
				"  \"name\":\""+title+"\",\r\n" + 
				"  \"awatar\":\"https://oss.coolcollege.cn/1799212826393448448.jpg\",\r\n" + 
				"  \"description\":\"this is des\",\r\n" + 
				"  \"topic\":[\r\n" + 
				"  ],\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"").send().body();
	}
	
	public static String editCircle(String id,String title) {
		return HttpRequest.post(editUrl(id)).header("x-access-token", token).data("{\r\n" + 
				"  \"name\":\""+title+"\",\r\n" + 
				"  \"awatar\":\"https://oss.coolcollege.cn/1799139000800382976.jpg\",\r\n" + 
				"  \"description\":\"this is des\",\r\n" + 
				"  \"spaceId\":\""+id+"\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"").send().body();
	}
	public static String stat(String id,String enable) {
		return DeleteRequestTools.RequestBodyByput(statUrl(id), "{\"enable\":"+enable+",\"access_token\":\""+token+"\"}",token);
	}
	
	public static String queryCircle(String keyword) {
		return HttpRequest.get(search_url).header("x-access-token", token).query("page", "0").query("size","20").query("t","SPACE")
			.query("text",keyword).send().body();
	}
	
 }
