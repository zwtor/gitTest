package cn.kxy.app.homepage;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class AppHomePageBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String user_id =UserBusiness.getUserId();
	
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String platform_url  = EnterpriseDataUrl.getPlatformUrl();
	public static String cmdb_url  = EnterpriseDataUrl.getCmdbUrl();
	public static String feed_url  = EnterpriseDataUrl.getFeedUrl();
	
	public static String corp_id = "dingef2502a50df74ccc35c2f4657eb6378f";
	public static String query_mobile_setting_url = enterprise_url + "v2/mobile/startup/pages/get";
	
	public static String top_url = enterprise_url + "v2/"+enterprise_id+"/users/"+user_id+"/tasks/top";
	
	public static String not_started_count_url = enterprise_url + "v2/"+enterprise_id+"/users/"+user_id+"/post_maps/not_started_count";
	
	public static String language_url =enterprise_url + "v2/"+enterprise_id+"/users/"+user_id+"/language";
	
	public static String base_score_url = enterprise_url + "v2/"+enterprise_id+"/users/"+user_id+"/base_score";
	
	public static String show_feed_url = enterprise_url + "v2/"+enterprise_id+"/settings/show_feed";
	
	public static String courses_study_url = enterprise_url + "v2/"+enterprise_id+"/courses/study";
	
	public static String settings_url = enterprise_url + "v2/"+enterprise_id+"/settings";
	
	public static String scores_rank_url =enterprise_url + "v2/"+enterprise_id+"/scores";
	
	public static String lecturers_url =enterprise_url + "v2/"+enterprise_id+"/lecturers";
	
	public static String weatherHasGuideViewUrl = enterprise_url + "guideView/weatherHasGuideView";
	
	public static String discoveries_url = platform_url + "v2/"+enterprise_id+"/users/"+user_id+"/discoveries/query";
	
	public static String phone_pop_window_url = platform_url + "v2/"+enterprise_id+"/users/"+user_id+"/phone_pop_window";
	
	public static String isvlogin_url = platform_url + "v2/isvLogin";

	public static String menus_url = cmdb_url + "v2/enterprises/"+enterprise_id+"/users/"+user_id+"/menus";
	
	public static String module_setting_url = cmdb_url + "v2/"+enterprise_id+"/home_page/module_setting";
	
	public static String feeds_url = feed_url + "v2/"+enterprise_id+"/feeds";
	
	public static String customer_url = enterprise_url + "v2/"+enterprise_id+"/customer";
	
	public static String mobile_url =enterprise_url + "v2/mobile/list";
	
	public static String score_depart_url = enterprise_url + "v2/"+enterprise_id+"/departments/score/mobile";
	
	public static String today_scores_rank_url =enterprise_url + "v2/"+enterprise_id+"/users/"+user_id+"/scores";
	
	public static String get_home_menus_url = cmdb_url + "v2/"+enterprise_id+"/get_home_menus";

	public static String is_need_url = enterprise_url + "v2/boot/is_need";

	public static String face_query_url = enterprise_url + "v2/"+enterprise_id+"/users/"+user_id+"/face/query";
	
	public static int getHomeMenus() {
		return HttpRequest.get(get_home_menus_url).query("keys", "notice,scoreList,lecturerDemeanor,versionUpdate").query("access_token", token).send().statusCode();
	}

	public static String judgeIsNeed() {
		return HttpRequest.get(is_need_url).query("boot_name","create_project").query("access_token", token).
				query("enterprise_id",enterprise_id).query("user_id",user_id).send().body();
	}
	
	/**   
	 * @Title: queryTodayScoresRank   
	 * @Description: TODO  查看今日学分
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryTodayScoresRank() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"page_number","1","page_size","10",
				"enterprise_id", enterprise_id, "user_id",user_id, today_scores_rank_url);
	}
	
	/**   
	 * @Title: queryDepart   
	 * @Description: TODO  查询用户所属部门
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryDepart() {
		return HttpRequest.get(score_depart_url).query("enterprise_id", enterprise_id).query("access_token", token)
				.query("user_id", user_id).send().body();
	}
	
	/**   
	 * @Title: queryCustomer   
	 * @Description: TODO  查看客服接口
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryCustomer() {
		return HttpRequest.get(customer_url).query("access_token", token).query("enterprise_id", enterprise_id)
				.query("type", "login").query("user_id", user_id).send().body();
	}
	
	
	public static String thumbsUpUrl(String id) {
		return feed_url + "v2/"+enterprise_id+"/feeds/"+id+"/thumbsup";
	}
	
	/**   
	 * @Title: thumbsUp   
	 * @Description: TODO(对学习动态进行点赞)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String thumbsUp (String id,String status) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"thumbs_up\": \""+status+"\", \r\n" + 
				"  \"my_thumb_id\": \""+id+"\"\r\n" + 
				"}", token, "enterprise_id", enterprise_id, "user_id", user_id, thumbsUpUrl(id));
	}
	
	/**   
	 * @Title: queryFeed   
	 * @Description: TODO(学习动态)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryFeed() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"page_size","10","enterprise_id", enterprise_id, "user_id",user_id, feeds_url);
	}
	/**   
	 * @Title: queryModuleSetting   
	 * @Description: TODO(查询移动端设置)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryModuleSetting() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id", enterprise_id, "user_id",user_id, module_setting_url);
	}
	
	/**   
	 * @Title: menus   
	 * @Description: TODO(查看菜单)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryMenus() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id", enterprise_id, "user_id",user_id, menus_url);
	}

	public static String queryFace () {
		return HttpRequest.get(face_query_url).query("access_token", token).query("enterprise_id",enterprise_id).query("user_id",user_id)
				.send().body();
	}
	
	/**   
	 * @Title: isvlogin   
	 * @Description: TODO(判断是否登录成功)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String isvlogin () {
		return GetRequestTools.RequestQueryParamsByGet(isvlogin_url);
	}
	
	/**   
	 * @Title: queryPhonePopWindow   
	 * @Description: TODO(查询是否弹窗)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryPhonePopWindow() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id", enterprise_id, "user_id",user_id, phone_pop_window_url);
	}
	
	/**   
	 * @Title: queryDiscoveries   
	 * @Description: TODO(发现)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryDiscoveries () {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id", enterprise_id, "user_id",user_id, discoveries_url);
	}
	
	/**   
	 * @Title: queryWeatherHasGuideView   
	 * @Description: TODO(查询指南)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryWeatherHasGuideView () {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"userId",user_id,"type","11",
				"enterprise_id", enterprise_id, "user_id",user_id, weatherHasGuideViewUrl);
	}
	
	/**   
	 * @Title: queryLecturer   
	 * @Description: TODO(查看APP端首页讲师列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryLecturer() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"page_number","1","page_size","3",
				"enterprise_id", enterprise_id, "user_id",user_id,"status","1", lecturers_url);
	}
	
	/**   
	 * @Title: queryScoresRank   
	 * @Description: TODO(查看学分排行)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryScoresRank(String dept_id,String page_size) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"page_number","1","page_size",page_size,"dept_id",dept_id,
				"enterprise_id", enterprise_id, "user_id",user_id, scores_rank_url);
	}
	
	/**   
	 * @Title: querySetting   
	 * @Description: TODO(查看设置)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String querySetting() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id", enterprise_id, "user_id",user_id, settings_url);
	}
	
	/**   
	 * @Title: queryCoursesStudy   
	 * @Description: TODO(查询动态学习)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryDynamicStudy() {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"resource_ids\": [{\r\n" + 
				"		\"type\": \"resource.finish\",\r\n" + 
				"		\"id\": \"1791022943879434240\"\r\n" + 
				"	}, {\r\n" + 
				"		\"type\": \"certificate.award\",\r\n" + 
				"		\"id\": \"1791826118496620544.1791826116936339456\"\r\n" + 
				"	}, {\r\n" + 
				"		\"type\": \"exam.fullmark\",\r\n" + 
				"		\"id\": \"1791826118496620544\"\r\n" + 
				"	}, {\r\n" + 
				"		\"type\": \"exam.fullmark\",\r\n" + 
				"		\"id\": \"1791826109608890368\"\r\n" + 
				"	}, {\r\n" + 
				"		\"type\": \"exam.fullmark\",\r\n" + 
				"		\"id\": \"1791826109499838464\"\r\n" + 
				"	}, {\r\n" + 
				"		\"type\": \"exam.fullmark\",\r\n" + 
				"		\"id\": \"1791826108962967552\"\r\n" + 
				"	}, {\r\n" + 
				"		\"type\": \"exam.fullmark\",\r\n" + 
				"		\"id\": \"1791826107721453568\"\r\n" + 
				"	}, {\r\n" + 
				"		\"type\": \"certificate.award\",\r\n" + 
				"		\"id\": \"1791826089698529280.1791826078235496448\"\r\n" + 
				"	}, {\r\n" + 
				"		\"type\": \"exam.fullmark\",\r\n" + 
				"		\"id\": \"1791826089698529280\"\r\n" + 
				"	}, {\r\n" + 
				"		\"type\": \"certificate.award\",\r\n" + 
				"		\"id\": \"1791826088356352000.1791826078235496448\"\r\n" + 
				"	}]\r\n" + 
				"}", token, "enterprise_id", enterprise_id, "user_id", user_id, courses_study_url);
	}
	/**   
	 * @Title: queryMobileStudy   
	 * @Description: TODO(查看动端学习动态)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryMobileStudySetting() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id", enterprise_id, "user_id",user_id, show_feed_url);
	}
	
	/**   
	 * @Title: queryBaseScoreInfo   
	 * @Description: TODO(查看APP端的个人的学分)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryBaseScoreInfo() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id", enterprise_id, "user_id",user_id, base_score_url);
	}
	
	/**   
	 * @Title: queryLanguageInfo   
	 * @Description: TODO(查看当前语言)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryLanguageInfo() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id", enterprise_id, "user_id",user_id, language_url);
	}
	
	/**   
	 * @Title: queryPostMapNotStartCount   
	 * @Description: TODO(查看岗位地图未完成的数量)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryPostMapNotStartCount() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id", enterprise_id, "user_id",user_id, not_started_count_url);
	}
	
	/**   
	 * @Title: queryTopTask   
	 * @Description: TODO(APP首页我的任务列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryTopTask() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id", enterprise_id, "user_id",user_id,top_url);
	}
	
	/**   
	 * @Title: queryMobileSetting   
	 * @Description: TODO(查看手机端设置)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryMobileSetting() {
		return GetRequestTools.RequestQueryParamsByGet("corp_id", corp_id, query_mobile_setting_url);
	}
	
}
