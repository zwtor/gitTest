package cn.kxy.authentication.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;

public class AppPostMapBusiness {
	public static String enterpriseUrl=  EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId=EnterpriseData.getEnterpriseId();
	public static String userId = UserBusiness.getUserId();
	
	public static String not_started_count_url =enterpriseUrl +"v2/"+enterpriseId+"/users/"+userId+"/post_maps/not_started_count";
	
	public static String queryListUrl = enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/post_maps";
	
	public static String queryListInfoUrl (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/post_maps/"+id;
	} 
	
	public static String pop_window_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/post_maps/"+id+"/pop_window";
	}
	
	public static String get_awards_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/post_maps/"+id+"/get_awards";
	}
	public static String introduction_url(String id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/post_maps/"+id+"/introduction";
	}
	
	public static String queryIntroduction(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, introduction_url(id)); 
	}
	public static String queryAppPostItemsUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/qualifications/"+id+"/items";
	}
	
	public static String save_progress_url(String studyId,String courseId,String resourcesId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/studies/"+studyId+"/courses/"+courseId+"/resources/"+resourcesId+"/save_progress";
	}
	/**   
	 * @Title: queryAppPostItems   
	 * @Description: TODO(查看APP端岗位认证详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppPostItems(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "user_id",userId,queryAppPostItemsUrl(id));
	}
	
	/**   
	 * @Title: getPostMapNotStartedCount   
	 * @Description: TODO(查看岗位地图未认证的个数)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getPostMapNotStartedCount() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, not_started_count_url); 
	}
	
	/**   
	 * @Title: getAward   
	 * @Description: TODO(获取额外奖励)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getAward(String id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}", token, get_awards_url(id));
	}
	
	/**   
	 * @Title: popWindow   
	 * @Description: TODO(通关后弹窗)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String popWindow(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, pop_window_url(id));
	}
	
	/**   
	 * @Title: queryListInfo   
	 * @Description: TODO(查看岗位地图概要信息)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryListInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryListInfoUrl(id));
	}
	
	/**   
	 * @Title: queryList   
	 * @Description: TODO(查询岗位地图列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryList() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"page_number","1",
				"page_size","20", queryListUrl);
	}
	
	
}
