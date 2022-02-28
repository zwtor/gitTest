package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;
import com.lazy.httpclient.utils.HttpResponse;

import java.util.ArrayList;

public class IntegralBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String user_id =UserBusiness.getUserId();
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	
	public static String integral_list_url  = enterprise_url + "v2/"+enterprise_id+"/integral/integral_list";
	
	public static String user_integral_list_url  = enterprise_url + "v2/"+enterprise_id+"/user/"+user_id+"/integral_list";
	
	public static String my_integral_url  = enterprise_url  + "v2/"+enterprise_id+"/user/"+user_id+"/my_integral";
	
	public static String integral_details_url = enterprise_url + "v2/"+enterprise_id+"/integral/"+user_id+"/details";
	
	public static String rank_export_url = enterprise_url + "v2/"+enterprise_id+"/integral/rank_export";
	
	public static String integral_export_url  = enterprise_url + "v2/"+enterprise_id+"/user/"+user_id+"/integral_export";
	

	public static String save_reward_rules_url = enterprise_url + "v2/"+enterprise_id+"/integral/save_reward_rules";
	
	public static String add_study_task_url = enterprise_url + "plan/study/add";
	
	public static String sign_rule_list_url =enterprise_url + "v2/"+enterprise_id+"/sign/rule_list";
	
	public static String rule_save_url = enterprise_url + "v2/"+enterprise_id+"/sign/rule_save";
	
	public static String lecturer_rule_list_url = enterprise_url + "v2/"+enterprise_id+"/lecturer/rule_list";
	
	public static String rule_save_lecturer_url = enterprise_url + "v2/"+enterprise_id+"/lecturer/rule_save";
	
	public static String add_integral_lecturers_url = enterprise_url + "v2/"+enterprise_id+"/integral/add";
	
	public static String integral_level_list_url = enterprise_url + "v2/"+enterprise_id+"/integral_level_list";
	
	public static String save_integral_level_url = enterprise_url + "v2/"+enterprise_id+"/save_update/integral_level";
	
	public static String delete_integral_level_url = enterprise_url + "v2/"+enterprise_id+"/delete/integral_level";
	
	public static String deleteIntegralLevel(String id) {
		return HttpRequest.post(delete_integral_level_url).query("access_token", token).data("{\"id\":\""+id+"\",\"access_token\":\""+token+"\"}")
				.send().body();
	}
	
	public static String editIntegralLevel(String id,String name) {
		return HttpRequest.post(save_integral_level_url).query("access_token", token).data("{\n" + 
				"  \"level_icon\":\"https://oss.coolcollege.cn/1798854662116478976.jpg\",\n" + 
				"  \"level_name\":\""+name+"\",\n" + 
				"  \"id\":\""+id+"\","+
				"  \"integral\":6,\n" + 
				"  \"level_number\":\"1\",\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}\n" + 
				"").send().body();
	}
	
	public static String saveIntegralLevel(String name) {
		return HttpRequest.post(save_integral_level_url).query("access_token", token).data("{\n" + 
				"  \"level_icon\":\"https://oss.coolcollege.cn/1798854662116478976.jpg\",\n" + 
				"  \"level_name\":\""+name+"\",\n" + 
				"  \"integral\":6,\n" + 
				"  \"level_number\":\"1\",\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}\n" + 
				"").send().body();
	}
	
	public static String queryIntegralLevelList() {
		return HttpRequest.get(integral_level_list_url).query("access_token", token).query("page_number","1").query("page_size","200").send().body();
	}
	
	public static String addIntegral(String points,String behavior) {
		return HttpRequest.post(add_integral_lecturers_url).query("access_token", token).data("{\n" + 
				"  \"group_ids\":\"\",\n" + 
				"  \"department_ids\":\"\",\n" + 
				"  \"user_ids\":\""+user_id+"\",\n" + 
				"  \"post_ids\":\"\",\n" + 
				"  \"time\":"+DateUtil.getTimeStamp(0, "")+",\n" + 
				"  \"behavior\":\""+behavior+"\",\n" + 
				"  \"points\":"+points+",\n" + 
				"  \"teacherIds\":[\n" + 
				"  ],\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}").send().body();
	}
	
	public static String saveLecturerRule(ArrayList<String> list) {
		return HttpRequest.post(rule_save_lecturer_url).query("access_token", token).data("{\n" + 
				"  \"list\":"+list+",\n" + 
				"  \"daily_status\":1,\n" + 
				"  \"daily_num\":120,\n" + 
				"  \"modify\":[\n" + 
				"  ],\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}").send().body();
	}
	
	public static String saveLecturerRule(String rule_group_id_01,String rule_group_id_02,String rule_group_id_03) {
		return HttpRequest.post(rule_save_lecturer_url).query("access_token", token).data("{\n" + 
				"  \"list\":[\n" + 
				"    {\n" + 
				"      \"effective_time\":"+DateUtil.getTimeStamp(0, "")+",\n" + 
				"      \"operation_name\":\"学习项目讲师积分\",\n" + 
				"      \"number\":2,\n" + 
				"      \"event_code\":\"100801\",\n" + 
				"      \"limit\":0,\n" + 
				"      \"rule_group_id\":\""+rule_group_id_01+"\",\n" + 
				"      \"type\":\" 成功发布的学习项目关联的讲师\",\n" + 
				"      \"status\":1\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"effective_time\":"+DateUtil.getTimeStamp(0, "")+",\n" + 
				"      \"operation_name\":\"线上课程讲师积分\",\n" + 
				"      \"number\":3,\n" + 
				"      \"event_code\":\"100802\",\n" + 
				"      \"limit\":0,\n" + 
				"      \"rule_group_id\":\""+rule_group_id_02+"\",\n" + 
				"      \"type\":\"成功发布的自建课程关联的讲师\",\n" + 
				"      \"status\":1\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"effective_time\":"+DateUtil.getTimeStamp(0, "")+",\n" + 
				"      \"operation_name\":\"线下培训讲师积分\",\n" + 
				"      \"number\":4,\n" + 
				"      \"event_code\":\"100803\",\n" + 
				"      \"limit\":0,\n" + 
				"      \"rule_group_id\":\""+rule_group_id_03+"\",\n" + 
				"      \"type\":\"成功发布的线下培训关联的讲师\",\n" + 
				"      \"status\":1\n" + 
				"    }],\n" + 
				"  \"daily_status\":1,\n" + 
				"  \"daily_num\":0,\n" + 
				"  \"modify\":[\n" + 
				"  ],\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}\n" + 
				"").send().body();
	}
	
	/**   
	 * @Title: queryLecturerRuleList   
	 * @Description: TODO  查看讲师积分列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryLecturerRuleList() {
		return HttpRequest.get(lecturer_rule_list_url).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: saveSignRule   
	 * @Description: TODO 保存连续签到规则
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String saveSignRule() {
		return HttpRequest.post(rule_save_url).query("access_token", token).data("[]").send().body();
	}
	/**   
	 * @Title: querySignRuleList   
	 * @Description: TODO 查询签到规则列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String querySignRuleList() {
		return HttpRequest.get(sign_rule_list_url).query("access_token", token).query("page_number","1").query("page_size","100").send().body();
	}
	
	public static String addStudyTask(String title,String course_id_01,String course_id_02,String course_id_03,String course_id_04) {
		
	return	HttpRequest.post(add_study_task_url).query("access_token", token).data("{\r\n" + 
				"  \"title\":\""+title+" \",\r\n" + 
				"  \"beginTime\":"+DateUtil.getTimeStamp(0, "")+",\r\n" + 
				"  \"endTime\":"+DateUtil.getTimeStamp(7, "")+",\r\n" + 
				" \"summary\":\"\",\n" + 
				"  \"dingImgUrl\":\"\",\n" + 
				"  \"departmentIds\":\"\",\n" + 
				"  \"supervisor_department_ids\":[\n" + 
				"  ],\n" + 
				"  \"groupIds\":\"\",\n" + 
				"  \"isGetScore\":true,\n" + 
				"  \"isNoticeAll\":0,\n" + 
				"  \"planCertificateId\":\"\",\n" + 
				"  \"postIds\":\"\",\n" + 
				"  \"progress\":100,\n" + 
				"  \"studyScore\":{\n" + 
				"    \"finishScore\":0,\n" + 
				"    \"unFinishScore\":0\n" + 
				"  },\n" + 
				"  \"studyTimeLimit\":0,\n" + 
				"  \"faceRecognition\":\"off\",\n" + 
				"  \"supervisorId\":\""+user_id+"\",\n" + 
				"  \"supervisorPaper\":true,\n" + 
				"  \"authorityRange\":false,\n" + 
				"  \"times\":\"\",\n" + 
				"  \"operationTimes\":\"\",\n" + 
				"  \"userIds\":\""+user_id+"\",\n" + 
				"  \"dingGroupIds\":\"\","+
				"\"stageJson\":[\n" + 
				"    {\n" + 
				"      \"content\":\"\",\n" + 
				"      \"stageName\":\"step one\",\n" + 
				"      \"isOrder\":false,\n" + 
				"      \"stageSort\":1,\n" + 
				"      \"course\":[\n" + 
				"        {\n" + 
				"          \"courseId\":\""+course_id_01+"\",\n" + 
				"          \"courseSort\":0,\n" + 
				"          \"courseType\":3,\n" + 
				"          \"flag\":1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"courseId\":\""+course_id_02+"\",\n" + 
				"          \"courseSort\":1,\n" + 
				"          \"courseType\":3,\n" + 
				"          \"flag\":1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"courseId\":\""+course_id_03+"\",\n" + 
				"          \"courseSort\":2,\n" + 
				"          \"courseType\":3,\n" + 
				"          \"flag\":1\n" + 
				"        },\n" + 
				"        {\n" + 
				"          \"courseId\":\""+course_id_04+"\",\n" + 
				"          \"courseSort\":3,\n" + 
				"          \"courseType\":3,\n" + 
				"          \"flag\":1\n" + 
				"        }]\n" + 
				"    }],"+
				"  \"stagePass\":false,\r\n" + 
				"  \"finishEvaluation\":false,\r\n" + 
				"  \"openEvaluationResult\":false,\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}").send().body();
	}
	
	public static String saveRewardRules(String credit_exchange_group,String effective_time,String rule_group_id_01,String rule_group_id_02,String rule_group_id_03
			,String rule_group_id_04,String rule_group_id_05,String rule_group_id_06,String rule_group_id_07) {
		return HttpRequest.post(save_reward_rules_url).query("access_token", token).data("{\n" + 
				"  \"list\":[\n" + 
				"    {\n" + 
				"      \"effective_time\":"+effective_time+",\n" + 
				"      \"operation_name\":\"每日签到\",\n" + 
				"      \"event_code\":\"100101\",\n" + 
				"      \"daily_integral_num\":10000,\n" + 
				"      \"effective_period\":\"每日\",\n" + 
				"      \"rule_group_id\":\""+rule_group_id_01+"\",\n" + 
				"      \"type\":\"用户\",\n" + 
				"      \"single_reward_points\":3,\n" + 
				"      \"status\":1,\n" + 
				"      \"key\":\""+rule_group_id_01+"\"\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"effective_time\":"+effective_time+",\n" + 
				"      \"operation_name\":\"完成视频课件\",\n" + 
				"      \"event_code\":\"100102\",\n" + 
				"      \"daily_integral_num\":10000,\n" + 
				"      \"effective_period\":\"一次性\",\n" + 
				"      \"rule_group_id\":\""+rule_group_id_02+"\",\n" + 
				"      \"type\":\"在线学习\",\n" + 
				"      \"single_reward_points\":3,\n" + 
				"      \"status\":1,\n" + 
				"      \"key\":\""+rule_group_id_02+"\"\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"effective_time\":"+effective_time+",\n" + 
				"      \"operation_name\":\"完成文档课件\",\n" + 
				"      \"event_code\":\"100103\",\n" + 
				"      \"daily_integral_num\":10000,\n" + 
				"      \"effective_period\":\"一次性\",\n" + 
				"      \"rule_group_id\":\""+rule_group_id_03+"\",\n" + 
				"      \"type\":\"在线学习\",\n" + 
				"      \"single_reward_points\":3,\n" + 
				"      \"status\":1,\n" + 
				"      \"key\":\""+rule_group_id_03+"\"\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"effective_time\":"+effective_time+",\n" + 
				"      \"operation_name\":\"完成文章课件\",\n" + 
				"      \"event_code\":\"100104\",\n" + 
				"      \"daily_integral_num\":10000,\n" + 
				"      \"effective_period\":\"一次性\",\n" + 
				"      \"rule_group_id\":\""+rule_group_id_04+"\",\n" + 
				"      \"type\":\"在线学习\",\n" + 
				"      \"single_reward_points\":3,\n" + 
				"      \"status\":1,\n" + 
				"      \"key\":\""+rule_group_id_04+"\"\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"effective_time\":"+effective_time+",\n" + 
				"      \"operation_name\":\"完成音频课件\",\n" + 
				"      \"event_code\":\"100105\",\n" + 
				"      \"daily_integral_num\":10000,\n" + 
				"      \"effective_period\":\"一次性\",\n" + 
				"      \"rule_group_id\":\""+rule_group_id_05+"\",\n" + 
				"      \"type\":\"在线学习\",\n" + 
				"      \"single_reward_points\":3,\n" + 
				"      \"status\":1,\n" + 
				"      \"key\":\""+rule_group_id_05+"\"\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"effective_time\":"+effective_time+",\n" + 
				"      \"operation_name\":\"制作微课\",\n" + 
				"      \"event_code\":\"100106\",\n" + 
				"      \"daily_integral_num\":10000,\n" + 
				"      \"effective_period\":\"一次性\",\n" + 
				"      \"rule_group_id\":\""+rule_group_id_06+"\",\n" + 
				"      \"type\":\"贡献\",\n" + 
				"      \"single_reward_points\":3,\n" + 
				"      \"status\":1,\n" + 
				"      \"key\":\""+rule_group_id_06+"\"\n" + 
				"    },\n" + 
				"    {\n" + 
				"      \"effective_time\":"+effective_time+",\n" + 
				"      \"operation_name\":\"上传一门课程\",\n" + 
				"      \"event_code\":\"100107\",\n" + 
				"      \"daily_integral_num\":10000,\n" + 
				"      \"effective_period\":\"一次性\",\n" + 
				"      \"rule_group_id\":\""+rule_group_id_07+"\",\n" + 
				"      \"type\":\"贡献\",\n" + 
				"      \"single_reward_points\":3,\n" + 
				"      \"status\":1,\n" + 
				"      \"key\":\""+rule_group_id_07+"\"\n" + 
				"    }],\n" + 
				"  \"daily_integral_status\":0,\n" + 
				"  \"daily_integral_num\":\"10000\",\n" + 
				"  \"credit_exchange_status\":1,\n" + 
				"  \"credit_exchange_num\":\"1\",\n" + 
				"  \"scene_code\":1001,\n" + 
				" \"modify\":[\n" + 
				"    \"100101\",\n" + 
				"    \"100102\",\n" + 
				"    \"100103\",\n" + 
				"    \"100104\",\n" + 
				"    \"100105\",\n" + 
				"    \"100107\",\n" + 
				"    \"100106\"],"+
				"  \"credit_exchange_event\":\"100401\",\n" + 
				"  \"credit_exchange_group\":\""+credit_exchange_group+"\",\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}\n" + 
				"").send().body();
	}
	
	
	public static String queryIntegralDetailExport(String start_time,String end_time) {
		return HttpRequest.post(integral_export_url).query("access_token", token).data("{\r\n" + 
				"  \"start_time\":\""+start_time+"\",\r\n" + 
				"  \"end_time\":\""+end_time+"\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"").send().body();
	}
	
	public static int getRankExportCode() {
		return HttpResponse.getstatusCode(rank_export_url, token, "access_token", token,"page_number","1","department_id","1",
				"name",UserBusiness.getUsername()); 
	}
	
	public static String queryIntegralDetails(String begin_time,String end_time) {
		return HttpRequest.get(integral_details_url).query("access_token",token).query("page_number","1").query("page_size","10")
				.query("begin_time",begin_time).query("end_time",end_time).send().body();
	}
	
	public static String queryIntegralDetails(String begin_time,String end_time,String page_size) {
		return HttpRequest.get(integral_details_url).query("access_token",token).query("page_number","1").query("page_size",page_size)
				.query("begin_time",begin_time).query("end_time",end_time).send().body();
	}
	
	public static String queryMyIntegralList(String begin_time,String end_time) {
		return HttpRequest.get(user_integral_list_url).query("access_token",token).query("page_number","1").query("page_size","10")
				.query("begin_time",begin_time).query("end_time",end_time).query("type","0").send().body();
	}
	
	public static String queryMyIntegral() {
		return HttpRequest.get(my_integral_url).query("access_token",token).send().body();
	}
	
	/**   
	 * @Title: queryMyIntegral   
	 * @Description: TODO  查看我的讲师积分
	 * @param: @param type   8
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryMyIntegral(String type) {
		return HttpRequest.get(my_integral_url).query("access_token",token).query("type",type).send().body();
	}
	
	public static String queryIntegralRank(String begin_time,String end_time,String name) {
		return HttpRequest.get(integral_list_url).query("access_token",token).query("page_number","1").query("page_size","20")
				.query("begin_time",begin_time).query("end_time",end_time).query("name",name).query("department_id","1").send().body();
	}
	
}
