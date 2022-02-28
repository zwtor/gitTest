package cn.kxy.community.group;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;
import okhttp3.*;

import java.io.IOException;

public class CreationBusiness {
	public static String group_url=  EnterpriseDataUrl.getGroupUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id=EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	
	public static String creation_url = group_url + "my/creation";
	
	public static String search_tag_url = group_url + "topic/tag/search";
	
	public static String disable_tag_url = group_url + "topic/disable";
	
	public static String allTopic_url = group_url + "allTopic";
	
	public static String read_message_url =group_url + "mention/"+user_id+"/read";
	
	public static String creationUrl (String type) {
		return group_url + user_id +"/creation/"+type;
	}
	
	public static String creatUrl(String circle_id,String type) {
		return group_url + circle_id+"/"+type;
	}
	
	public static String queryMyCreationUrl(String type) {
		return group_url+user_id+"/creation/"+type;
	}
	
	public static String queryMyCreation(String type) {
		return HttpRequest.get(queryMyCreationUrl(type)).header("x-access-token", token).query("page", "0").query("size","20")
				.query("access-token", token).send().body();
	}
	
	public static String readMessage() {
		return HttpRequest.post(read_message_url).header("x-access-token", token).data("{}").send().body();
	}
	
	/**   
	 * @Title: creatQuestion   
	 * @Description: TODO  创建提问 ：question    文章：article
	 * @param: @param circle_id
	 * @param: @param title
	 * @param: @param topic_name01
	 * @param: @param topic_name02
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String creatQuestionArctile(String circle_id,String type,String title,String topic_name01 ,String topic_name02) {
		
		OkHttpClient client = new OkHttpClient();
		final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		@SuppressWarnings("deprecation")
		RequestBody body = RequestBody.create(JSON, "{\r\n" + 
				"  \"title\":\""+title+"\",\r\n" + 
				"  \"circle\":\""+circle_id+"\",\r\n" + 
				"  \"topic\":[\r\n" + 
				"    \""+topic_name01+"\",\r\n" + 
				"    \""+topic_name02+"\"],\r\n" + 
				"  \"content\":\"{\\\"type\\\":\\\"pc\\\",\\\"content\\\":\\\"{\\\\\\\"blocks\\\\\\\":[{\\\\\\\"key\\\\\\\":\\\\\\\"89qb9\\\\\\\",\\\\\\\"text\\\\\\\":\\\\\\\"fafasf fasfaf\\\\\\\",\\\\\\\"type\\\\\\\":\\\\\\\"unstyled\\\\\\\",\\\\\\\"depth\\\\\\\":0,\\\\\\\"inlineStyleRanges\\\\\\\":[],\\\\\\\"entityRanges\\\\\\\":[],\\\\\\\"data\\\\\\\":{}}],\\\\\\\"entityMap\\\\\\\":{}}\\\"}\",\r\n" + 
				"  \"userId\":\""+user_id+"\",\r\n" + 
				"  \"userName\":\""+UserBusiness.getUsername()+"\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"");
		Request request = new Request.Builder().url(creatUrl(circle_id,type)).addHeader("x-access-token", token).post(body).build();
		String result="";
		try {
			Response response = client.newCall(request).execute();
			result = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result ;
	}
	
	/**   
	 * @Title: queryAllTopic   
	 * @Description: TODO 查看所有的话题
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryAllTopic() {
		return HttpRequest.get(allTopic_url).header("x-access-token", token).query("access-token", token).send().body();
	}
	
	/**   
	 * @Title: disableTag   
	 * @Description: TODO  判断tag是否被禁用
	 * @param: @param tagName
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String disableTag(String tagName) {
		return HttpRequest.get(disable_tag_url ).header("x-access-token", token).query("access-token", token).query("tagName",tagName).send().body();
	}
	
	/**   
	 * @Title: searchTag   
	 * @Description: TODO  搜索tags
	 * @param: @param tagName
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String searchTag(String tagName) {
		return HttpRequest.get(search_tag_url).header("x-access-token", token).query("tagName",tagName).query("access-token", token).send().body();
	}
	
	/**   
	 * @Title: myQuestions   
	 * @Description: TODO  创作中心我的提问:questions   回答：answers   文章：articles
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String myQuestions(String type) {
		return HttpRequest.get(creationUrl(type)).header("x-access-token", token).query("page", "0").query("size","20")
				.query("access-token", token).send().body();
	}
	
	/**   
	 * @Title: queryCreationData   
	 * @Description: TODO  查看创作中心的数据
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryCreationData() {
		return HttpRequest.get(creation_url).header("x-access-token", token).query("access-token", token).send().body();
	}
	
	/**   
	 * @Title: queryAppMessageList   
	 * @Description: TODO  查看移动端消息列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryAppMessageList () {
		return HttpRequest.get(creation_url).header("x-access-token", token).query("page", "0").query("size", "10").query("access-token", token).send().body();
	}
	
}
