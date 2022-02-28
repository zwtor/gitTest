package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;
import com.lazy.httpclient.utils.HttpResponse;

public class AdvanceSettingBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String user_id =UserBusiness.getUserId();
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String setting_url = enterprise_url +"v2/"+enterprise_id+"/settings";
	
	public static String common_settings_url =enterprise_url + "v2/"+enterprise_id+"/switches/033af7874c8a11e997717cd3055008b6/setting";
	
	public static String message_receivers_url = enterprise_url + "v2/"+enterprise_id+"/quota/message_receivers/status";
	
	public static String set_message_receivers_url = enterprise_url +"v2/"+enterprise_id+"/quota/message_receivers";
	
	public static String dynamic_watermark_url = enterprise_url+"v2/"+enterprise_id+"/settings/dynamic_watermark";
	
	public static String synchronization_url = enterprise_url + "system/synchro/begin";
	
	public static String reward_rules_url = enterprise_url + "v2/"+enterprise_id+"/integral/reward_rules";
	
	public static String queryReewardRules(String scene_code) {
		return HttpRequest.get(reward_rules_url).query("scene_code", scene_code).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: synchronizationInfo   
	 * @Description: TODO  同步钉钉人员信息
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String synchronizationInfo () {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "enterpriseId",enterprise_id, synchronization_url);
	}
	
	/**   
	 * @Title: queryDynamicWatermark   
	 * @Description: TODO(查看水印设置)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryDynamicWatermark() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, dynamic_watermark_url);
	}
	
	/**   
	 * @Title: openMessageReceivers   
	 * @Description: TODO(开启消息接收人)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String openMessageReceivers() {
		return HttpResponse.postJson(set_message_receivers_url, "{\r\n" + 
				"  \"user_ids\": [\r\n" + 
				"    \""+user_id+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", "access_token", token);
	}
	
	/**   
	 * @Title: closeMessageReceivers   
	 * @Description: TODO(关闭消息接收人)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String closeMessageReceivers() {
		return HttpResponse.postJson(message_receivers_url, "{\"status\":\"off\",\"access_token\":\""+token+"\"}", "access_token", token);
	}
	
	/**   
	 * @Title: setShowFeed   
	 * @Description: TODO(设置移动端学习动态)   
	 * @param: @param switch_status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String setShowFeed(String switch_status) {
		return HttpResponse.postJson(common_settings_url, "{\r\n" + 
				"  \"type\": \"show_feed\", \r\n" + 
				"  \"switch_status\": \""+switch_status+"\", \r\n" + 
				"  \"approvers\": [ ], \r\n" + 
				"  \"actions\": \"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", "access_token", token);
	}
	
	/**   
	 * @Title: setCourseApproval   
	 * @Description: TODO(设置课程审核)   
	 * @param: @param switch_status （CLOSED--关闭 ,关闭时，不需要传userid , OPEN--开启，开启时需要传userid）
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String setCourseApproval(String switch_status,String user_id) {
		return HttpResponse.postJson(common_settings_url, "{\r\n" + 
				"  \"type\": \"course_approval\", \r\n" + 
				"  \"switch_status\": \""+switch_status+"\", \r\n" + 
				"  \"approvers\": [\r\n" + 
				"    \""+user_id+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"actions\": \"\", \r\n" + 
				"  \"access_token\": \"aa89c32426e273c84c4b0af4f588ef17\"\r\n" + 
				"}", "access_token", token);
	}
	
	/**   
	 * @Title: setCoursSharing   
	 * @Description: TODO(设置企业课程分享)   
	 * @param: @param switch_status  （CLOSED--关闭   OPEN--开启）
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String setCoursSharing(String switch_status) {
		return HttpResponse.postJson(common_settings_url, "{\r\n" + 
				"  \"type\": \"course_sharing\", \r\n" + 
				"  \"switch_status\": \""+switch_status+"\", \r\n" + 
				"  \"approvers\": [ ], \r\n" + 
				"  \"actions\": \"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", "access_token", token);
	}
	
	/**   
	 * @Title: setDocumentPlayer   
	 * @Description: TODO()   
	 * @param: @param status true--兼容模式    false--放映模式
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String setDocumentPlayer(String status) {
		return HttpResponse.postJson(common_settings_url, "{\r\n" + 
				"  \"type\":\"document_player\",\r\n" + 
				"  \"switch_status\":\"OPEN\",\r\n" + 
				"  \"approvers\":[\r\n" + 
				"  ],\r\n" + 
				"  \"actions\":\"[{\\\"param\\\":{\\\"checked\\\":\\\""+status+"\\\",\\\"comment\\\":[\\\"compat\\\"]},\\\"type\\\":\\\"compat\\\",\\\"dynamic_name\\\":\\\"兼容模式\\\"},{\\\"param\\\":{\\\"checked\\\":\\\"true\\\",\\\"comment\\\":[\\\"projection\\\"]},\\\"type\\\":\\\"projection\\\",\\\"dynamic_name\\\":\\\"放映模式\\\"}]\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"", "access_token", token);
	}
	
	/**   
	 * @Title: setScoreRank   
	 * @Description: TODO(设置积分排行榜)   
	 * @param: @param switch_status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String setScoreRank(String switch_status) {
		return HttpResponse.postJson(common_settings_url, "{\r\n" + 
				"  \"type\": \"mobile_credit_ranking\", \r\n" + 
				"  \"switch_status\": \""+switch_status+"\", \r\n" + 
				"  \"approvers\": [ ], \r\n" + 
				"  \"actions\": \"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", "access_token", token);
	}
	
	/**   
	 * @Title: setAudioVideoDrag   
	 * @Description: TODO(设置视频拖曳)   
	 * @param: @param switch_status （CLOSED--关闭   OPEN--开启）
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String setAudioVideoDrag (String switch_status) {
		return PostRequestTools.RequestBodyByPost( "{\r\n" + 
				"  \"type\": \"audio_video_drag\", \r\n" + 
				"  \"switch_status\": \""+switch_status+"\", \r\n" + 
				"  \"approvers\": [ ], \r\n" + 
				"  \"actions\": \"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, common_settings_url);
		
	}
	
	/**   
	 * @Title: switchTaskStatus   
	 * @Description: TODO(钉钉任务提醒)   
	 * @param: @param switch_status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String switchTaskStatus(String switch_status) {
		return HttpResponse.postJson(common_settings_url, "{\r\n" + 
				"  \"type\": \"task_reminder\", \r\n" + 
				"  \"switch_status\": \""+switch_status+"\", \r\n" + 
				"  \"approvers\": [ ], \r\n" + 
				"  \"actions\": \"[{\"param\":{\"checked\":\"true\",\"comment\":[\"task_assign_message\"]},\"type\":\"task_assign_message\",\"dynamic_name\":\"任务指派及变更\"},{\"param\":{\"checked\":\"false\",\"comment\":[\"timed_reminder\"]},\"type\":\"timed_reminder\",\"dynamic_name\":\"每日定时提醒\"},{\"param\":{\"checked\":\"false\",\"comment\":[\"task_ding_workflow\"]},\"type\":\"task_ding_workflow\",\"dynamic_name\":\"钉钉待办\"}]\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", "access_token", token);
	}
	
	/**   
	 * @Title: setPreventCutScreen   
	 * @Description: TODO(设置防切屏)   
	 * @param: @param type（ CLOSED--关闭   OPEN--开启）
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String setPreventCutScreen(String type) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"type\": \"prevent_cut_screen\", \r\n" + 
				"  \"switch_status\": \""+type+"\", \r\n" + 
				"  \"approvers\": [ ], \r\n" + 
				"  \"actions\": \"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, common_settings_url);
	}
	
	/**   
	 * @Title: setDynamicWatermark   
	 * @Description: TODO(设置动态水印)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String setDynamicWatermark() {
		return	HttpResponse.postJson(common_settings_url, "{\r\n" + 
				"  \"type\": \"dynamic_watermark\", \r\n" + 
				"  \"switch_status\": \"OPEN\", \r\n" + 
				"  \"approvers\": [ ], \r\n" + 
				"  \"actions\": \"[{\"param\":{\"checked\":\"true\",\"comment\":[\"name\",\"position\","
				+ "\"jobnumber\"]},\"type\":\"exam\",\"dynamic_name\":\"考试水印\"},{\"param\":{\"checked\":"
				+ "\"true\",\"comment\":[\"name\",\"position\",\"jobnumber\"]},\"type\":\"video\",\"dynamic_name\":"
				+ "\"视频水印\"},{\"param\":{\"checked\":\"true\",\"comment\":[\"name\",\"position\",\"jobnumber\"]},\"type\":\"document\",\"dynamic_name\":\"文档水印\"}]\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", "access_token", token);
	}
	
	/**   
	 * @Title: getAdvanceSetting   
	 * @Description: TODO(获取高级设置)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getAdvanceSetting() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "page_size", "20","page_number","1", setting_url);
	}
	
}
