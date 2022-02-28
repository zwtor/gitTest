package cn.kxy.community.group;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

public class TopicBusiness {

	public static String group_url=  EnterpriseDataUrl.getGroupUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id=EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	
	public static String topic_list_url =group_url + "topic/page";
	
	public static String pc_list_url =group_url + "home/hot";
	
	public static String pc_creation_url =group_url + "my/creation";
	
	public static String pc_topic_recommend_url =group_url + "topic/recommend";
	
	public static String topic_url = group_url + "topic/search/";
	
	public static String topic_data_url = group_url +  "topic/data";
	
	public static String changeTopicStatusUrl(String name,String type) {
		return group_url + "topic/"+name+"/"+type;
	}
	
	public static String changeTopicStatus(String name,String type) {
		return HttpRequest.post(changeTopicStatusUrl(name,type)).header("x-access-token",token).query("access-token",token)
				.data("{\"access_token\":\""+token+"\"}").send().body();
	}
	
	/**   
	 * @Title: queryTopicData   
	 * @Description: TODO  查看圈子的数据
	 * @param: @param tagName
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryTopicData(String tagName) {
		return HttpRequest.get(topic_data_url).header("x-access-token",token).query("access-token",token).query("tagName-token",tagName).
				send().body();
	}
	
	/**   
	 * @Title: queryTopicList    
	 * @Description: TODO  学员端查看话题列表
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryTopicList(String tagName,String type) {
		return HttpRequest.get(topic_url+type).header("x-access-token",token).query("page", "0").query("size","20")
				.query("access-token",token).query("tagName",tagName).send().body();
	}
	
	public static String queryTopicRecommendList() {
		return HttpRequest.get(pc_list_url).header("x-access-token",token).query("access-token",token).send().body();
	}
	
	public static String queryCreationList() {
		return HttpRequest.get(pc_list_url).header("x-access-token",token).query("access-token",token).send().body();
	}
	
	public static String queryHotList(String type) {
		return HttpRequest.get(pc_list_url).header("x-access-token",token).query("page", "0").query("size","20")
				.query("access-token",token).query("type",type).send().body();
	}
	public static String queryList(String tagName) {
		return HttpRequest.get(topic_list_url).header("x-access-token",token).query("page", "0").query("size","20")
				.query("access-token",token).query("tagName",tagName).send().body();
	}
}
