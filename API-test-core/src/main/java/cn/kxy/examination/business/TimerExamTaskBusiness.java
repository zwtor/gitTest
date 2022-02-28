package cn.kxy.examination.business;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpResponse;

public class TimerExamTaskBusiness {

	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String addUrl() {
		return exam_url + "v2/" + enterpriseId + "/template_exam";
	}
	
	public static String querylistUrl() {
		return exam_url + "v2/" + enterpriseId + "/templates/exam_list";
	}
	
	public static String closeStatusUrl(String id) {
		return exam_url + "v2/" + enterpriseId + "/exam_templates/"+id+"/update_status";
	}
	
	public static String deleteUrl () {
		return exam_url + "v2/" + enterpriseId + "/templates/"+getFirstId()+"/delete_exam";
	}
	public static String deleteUrl (String id) {
		return exam_url + "v2/" + enterpriseId + "/templates/"+id+"/delete_exam";
	}
	
	public static String queryInfoUrl(String id) {
		
		return exam_url + "v2/" + enterpriseId + "/templates/"+id+"/exam";
	}
	
	public static String openStatusUrl (String schedulerId) {
		return exam_url + "v2/" + enterpriseId + "/schedulers/"+schedulerId+"/update";
	}
	
	public static String editUrl(String id) {
		return exam_url + "v2/" + enterpriseId + "/templates/"+id+"/edit_exam";
	}
	
	public static String addRandomExam(String title,String question_bank_id,String authority_range) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"question_mode\": 2, \r\n" +
				"\"authority_range\":\""+authority_range+"\","+
				"  \"show_knowledge\": \"show\", \r\n" + 
				"  \"pass_line\": 40, \r\n" + 
				"  \"total_score\": \"\", \r\n" + 
				"  \"single_count\": 1, \r\n" + 
				"  \"single_unit_score\": 40, \r\n" + 
				"  \"multiple_count\": 0, \r\n" + 
				"  \"multiple_unit_score\": 0, \r\n" + 
				"  \"true_or_false_count\": 0, \r\n" + 
				"  \"true_or_false_unit_score\": 0, \r\n" + 
				"  \"fill_blank_count\": 0, \r\n" + 
				"  \"fill_blank_unit_score\": 0, \r\n" + 
				"  \"short_answer_count\": 0, \r\n" + 
				"  \"short_answer_unit_score\": 0, \r\n" + 
				"  \"paper_ids\": \"\", \r\n" + 
				"  \"question_bank_list\": \""+question_bank_id+"\", \r\n" + 
				"  \"answer_parsing\": 2, \r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"is_all_in\": false, \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"user_ids\": \""+user_id+"\", \r\n" + 
				"\"authority_range\":false, "+
				"  \"department_ids\": \"\", \r\n" + 
				"  \"postIds\": \"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"exam_duration\": 45, \r\n" + 
				"  \"cheat_flag\": 0, \r\n" + 
				"  \"s_repeat_exam\": false, \r\n" + 
				"  \"mark_type\": 1, \r\n" + 
				"  \"exam_certificate_id\": 0, \r\n" + 
				"  \"passing_score\": 40, \r\n" + 
				"  \"cut_screen_count\": -1, \r\n" + 
				"  \"re_exam_rule\": 0, \r\n" + 
				"  \"re_exam_number\": 0, \r\n" + 
				"  \"score_rule\": 0, \r\n" + 
				"  \"get_score_info\": { }, \r\n" + 
				"  \"get_score\": false, \r\n" + 
				"  \"supervisor_paper_str\": \"true\", \r\n" + 
				"  \"supervisor_id\": \""+user_id+"\", \r\n" + 
				"  \"status\": \"on\", \r\n" + 
				"  \"term_type\": \"D\", \r\n" + 
				"  \"plan_term\": 4, \r\n" + 
				"  \"scheduler_json\": {\r\n" + 
				"    \"interval\": 0, \r\n" + 
				"    \"next_time\": \""+DateUtil.getRegularDateForHHMM(1)+"\", \r\n" + 
				"    \"start_time\": \""+DateUtil.getRegularDateForHHMM(1)+"\", \r\n" + 
				"    \"times\": 0, \r\n" + 
				"    \"type\": \"once\", \r\n" + 
				"    \"scheduler_id\": \"\"\r\n" + 
				"  }, \r\n" + 
				"  \"summary\": \"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, addUrl());
	}
	
	//查看考试信息
	public static String quetyInfoTimerExam(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,  queryInfoUrl(id));
	}
	//查询定时考试模板
	public static String queryTimerExamTask (String keyword,String status) {
		return GetRequestTools.RequestQueryParamsByGet("keyword", keyword, "status", status,"page_number","1","page_size","20", "access_token",token,querylistUrl());
	}
	//停用状态
	public static String updateStatusTimerExam(String type ,String id) {
		return PostRequestTools.RequestBodyByPost("{\"type\":\""+type+"\",\"access_token\":\""+token+"\"}", token, closeStatusUrl(id));
	}
	
	//启用单次
	public static String openStatusTimerExam (String id,String scheduler_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"    \"interval\": 0, \r\n" + 
				"    \"scheduler_id\": \""+scheduler_id+"\", \r\n" + 
				"    \"next_time\": \""+DateUtil.getRegularDate(0)+"\", \r\n" + 
				"    \"start_time\": \""+DateUtil.getRegularDate(1)+"\", \r\n" + 
				"    \"status\": \"on\", \r\n" + 
				"    \"template_id\": \""+id+"\", \r\n" + 
				"    \"times\": 1, \r\n" + 
				"    \"type\": \"once\", \r\n" + 
				"    \"access_token\": \""+token+"\"\r\n" + 
				"}", token, "scope", "exam", openStatusUrl(scheduler_id));
		
	}
	
	//循环启用
	public static String openStatusLoopTimerExam (String interval,String times,String id,String scheduler_id) {
			return PostRequestTools.RequestBodyByPost("{\r\n" + 
					"  \"interval\": "+interval+", \r\n" + 
					"  \"scheduler_id\": \""+scheduler_id+"\", \r\n" + 
					"  \"next_time\": \""+DateUtil.getRegularDate(2)+"\", \r\n" + 
					"  \"start_time\": \""+DateUtil.getRegularDate(2)+"\", \r\n" + 
					"  \"status\": \"on\", \r\n" + 
					"  \"template_id\": \""+id+"\", \r\n" + 
					"  \"times\": "+times+", \r\n" + 
					"  \"type\": \"period\", \r\n" + 
					"  \"access_token\": \""+token+"\"\r\n" + 
					"}", token, "scope", "exam", openStatusUrl(scheduler_id));
			
		}
		
	
	
	//删除定时考试任务
	public static String deleteTimerExamTask() {
		return PostRequestTools.RequestFormDataByPost(token, deleteUrl());
	}
	public static String deleteTimerExamTask(String id) {
		return PostRequestTools.RequestFormDataByPost(token, deleteUrl(id));
	}
	
	public static String getScheduler_id(String name) {
		String res  = queryTimerExamTask(name,"all");
		String status = (String)JSONPath.read(res, "$.list[0].schedule_id");
		return status;
	}
	
	public static String getFirstStatus(String name) {
		String res  = queryTimerExamTask(name,"all");
		String status = (String)JSONPath.read(res, "$.list[0].status");
		return status;
	}
	
	public static int getTotal() {
		String res  = queryTimerExamTask("","all");
		int total = (int)JSONPath.read(res, "$.total");
		return total;
	}
	public static String getIdByKeyword(String keyword) {
		String res  = queryTimerExamTask(keyword,"all");
		String id = (String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	public static String getFirstId() {
		String res  = queryTimerExamTask("","all");
		String id = (String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	//新增pass定时考试任务
	public static String creatTimerOtherExamTask(String paperId,String answer_parsing,String title,String s_repeat_exam,String mark_type ,String re_exam_rule,String re_exam_number,String exam_certificate_name) {
		
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"question_mode\": 1, \r\n" + 
				"  \"show_knowledge\": \"show\", \r\n" + 
				"  \"pass_line\": 60, \r\n" + 
				"  \"total_score\": 100, \r\n" + 
				"  \"single_count\": \"\", \r\n" + 
				"  \"single_unit_score\": \"\", \r\n" + 
				"  \"multiple_count\": \"\", \r\n" + 
				"  \"multiple_unit_score\": \"\", \r\n" + 
				"  \"classify_id\":\"999\","+
				"  \"true_or_false_count\": \"\", \r\n" + 
				"  \"true_or_false_unit_score\": \"\", \r\n" + 
				"  \"fill_blank_count\": \"\", \r\n" + 
				"  \"fill_blank_unit_score\": \"\", \r\n" + 
				"  \"short_answer_count\": \"\", \r\n" + 
				"  \"short_answer_unit_score\": \"\", \r\n" + 
				"  \"paper_ids\": \""+paperId+"\", \r\n" + 
				"  \"question_bank_list\": \"\", \r\n" + 
				"  \"answer_parsing\": "+answer_parsing+", \r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"is_all_in\": false, \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"user_ids\": \""+UserBusiness.getUserId()+"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"postIds\": \"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"exam_duration\": 45, \r\n" + 
				"  \"cheat_flag\": 0, \r\n" + 
				"  \"s_repeat_exam\": "+s_repeat_exam+", \r\n" + 
				"  \"mark_type\": "+mark_type+", \r\n" + 
				"  \"exam_certificate_id\": \""+CertificateBusiness.getIdByKeyword(exam_certificate_name)+"\", \r\n" + 
				"  \"passing_score\": 60, \r\n" + 
				"  \"cut_screen_count\": 0, \r\n" + 
				"  \"re_exam_rule\": "+re_exam_rule+", \r\n" + 
				"  \"re_exam_number\": "+re_exam_number+", \r\n" + 
				"  \"score_rule\": 0, \r\n" +
				"\"authority_range\":false,"+
				"  \"get_score_info\": {\r\n" + 
				"    \"missScore\": 2, \r\n" + 
				"    \"passScore\": 6, \r\n" + 
				"    \"unPassScore\": 2\r\n" + 
				"  }, \r\n" + 
				"  \"get_score\": true, \r\n" + 
				"  \"supervisor_paper_str\": \"false\", \r\n" + 
				"  \"supervisor_id\": \""+UserBusiness.getUserId()+"\", \r\n" + 
				"  \"status\": \"on\", \r\n" + 
				"  \"term_type\": \"D\", \r\n" + 
				"  \"plan_term\": 5, \r\n" + 
				"  \"scheduler_json\": {\r\n" + 
				"    \"interval\": 0, \r\n" + 
				"    \"next_time\": \""+DateUtil.getRegularDate(-2)+"\", \r\n" + 
				"    \"start_time\": \""+DateUtil.getRegularDate(-2)+"\", \r\n" + 
				"    \"times\": 0, \r\n" + 
				"    \"type\": \"once\", \r\n" + 
				"    \"scheduler_id\": \"\"\r\n" + 
				"  }, \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, addUrl());
		
	}
	
	public static String creatTimerExamTask(String  pass_line,String total_score,String paper_ids,String title,String userid,String exam_duration
			,String passing_score,String plan_term,String authority_range) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"    \"question_mode\": 1,\r\n" + 
				"    \"show_knowledge\": \"show\",\r\n" + 
				"    \"pass_line\": "+pass_line+",\r\n" + 
				"    \"total_score\": "+total_score+",\r\n" + 
				"    \"single_count\": \"\",\r\n" + 
				"    \"single_unit_score\": \"\",\r\n" + 
				"    \"multiple_count\": \"\",\r\n" + 
				"    \"multiple_unit_score\": \"\",\r\n" + 
				"    \"true_or_false_count\": \"\",\r\n" + 
				"    \"true_or_false_unit_score\": \"\",\r\n" + 
				"    \"fill_blank_count\": \"\",\r\n" + 
				"    \"fill_blank_unit_score\": \"\",\r\n" + 
				"    \"short_answer_count\": \"\",\r\n" + 
				"    \"short_answer_unit_score\": \"\",\r\n" + 
				"    \"paper_ids\": \""+paper_ids+"\",\r\n" + 
				"    \"question_bank_list\": \"\",\r\n" + 
				"    \"answer_parsing\": 2,\r\n" + 
				"    \"title\": \""+title+"\",\r\n" + 
				"    \"is_all_in\": false,\r\n" + 
				"    \"group_ids\": \"\",\r\n" + 
				"    \"user_ids\": \""+userid+"\",\r\n" + 
				"    \"department_ids\": \"\",\r\n" + 
				"    \"postIds\": \"\",\r\n" + 
				"    \"post_ids\": \"\",\r\n" + 
				"    \"exam_duration\": "+exam_duration+",\r\n" + 
				"    \"cheat_flag\": 0,\r\n" + 
				"    \"s_repeat_exam\": false,\r\n" + 
				"    \"mark_type\": 1,\r\n" + 
				"    \"exam_certificate_id\": 0,\r\n" + 
				"    \"passing_score\": "+passing_score+",\r\n" + 
				"    \"authority_range\":true,"+
				"    \"cut_screen_count\": 0,\r\n" + 
				"    \"re_exam_rule\": 0,\r\n" + 
				"    \"re_exam_number\": 0,\r\n" + 
				"    \"score_rule\": 0,\r\n" + 
				"    \"get_score_info\": {},\r\n" + 
				"    \"get_score\": false,\r\n" + 
				"    \"supervisor_paper_str\": \"false\",\r\n" + 
				"    \"supervisor_id\": \""+userid+"\",\r\n" + 
				"    \"status\": \"on\",\r\n" + 
				"    \"term_type\": \"D\",\r\n" + 
				"    \"authority_range\":"+authority_range+","+
				"    \"plan_term\": "+plan_term+",\r\n" + 
				"    \"scheduler_json\": {\r\n" + 
				"        \"interval\": 0,\r\n" + 
				"        \"next_time\": \""+DateUtil.getRegularDate(1)+"\",\r\n" + 
				"        \"start_time\": \""+DateUtil.getRegularDate(1)+"\",\r\n" + 
				"        \"times\": 0,\r\n" + 
				"        \"type\": \"once\",\r\n" + 
				"        \"scheduler_id\": \"\"\r\n" + 
				"    },\r\n" + 
				"    \"access_token\": \""+token+"\"\r\n" + 
				"}",
				token, addUrl());
	}
	
	public static String editTimeExamTask(String  pass_line,String total_score,String paper_ids,String title,String userid,String exam_duration
			,String passing_score,String plan_term,String id,String scheduler_id) {
		
		return 	HttpResponse.postJson(editUrl(id), "{\r\n" + 
				"    \"question_mode\": 1, \r\n" + 
				"    \"show_knowledge\": \"show\", \r\n" + 
				"    \"pass_line\": "+pass_line+", \r\n" + 
				"    \"total_score\": "+total_score+", \r\n" + 
				"    \"single_count\": \"\", \r\n" + 
				"    \"single_unit_score\": \"\", \r\n" + 
				"    \"multiple_count\": \"\", \r\n" + 
				"    \"multiple_unit_score\": \"\", \r\n" + 
				"    \"true_or_false_count\": \"\", \r\n" + 
				"    \"true_or_false_unit_score\": \"\", \r\n" + 
				"    \"fill_blank_count\": \"\", \r\n" + 
				"    \"fill_blank_unit_score\": \"\", \r\n" + 
				"    \"short_answer_count\": \"\", \r\n" + 
				"    \"short_answer_unit_score\": \"\", \r\n" + 
				"    \"paper_ids\": \""+paper_ids+"\", \r\n" + 
				"    \"question_bank_list\": \"\", \r\n" + 
				"    \"answer_parsing\": 2, \r\n" + 
				"    \"title\": \""+title+"\", \r\n" + 
				"    \"is_all_in\": false, \r\n" + 
				"    \"group_ids\": \"\", \r\n" + 
				"    \"user_ids\": \""+userid+"\", \r\n" + 
				"    \"department_ids\": \"\", \r\n" + 
				"    \"postIds\": \"\", \r\n" + 
				"    \"post_ids\": \"\", \r\n" + 
				"    \"exam_duration\": 60, \r\n" + 
				"    \"cheat_flag\": 0, \r\n" + 
				"    \"s_repeat_exam\": false, \r\n" + 
				"    \"mark_type\": 1, \r\n" + 
				"    \"exam_certificate_id\": 0, \r\n" + 
				"    \"passing_score\": "+passing_score+", \r\n" + 
				"    \"cut_screen_count\": 0, \r\n" + 
				"    \"re_exam_rule\": 0, \r\n" + 
				"    \"re_exam_number\": 0, \r\n" + 
				"    \"score_rule\": 0, \r\n" + 
				"    \"get_score_info\": { }, \r\n" + 
				"    \"get_score\": false, \r\n" + 
				"    \"supervisor_paper_str\": \"false\", \r\n" + 
				"    \"supervisor_id\": \""+userid+"\", \r\n" + 
				"    \"status\": \"on\", \r\n" + 
				"    \"term_type\": \"D\", \r\n" + 
				"    \"plan_term\": "+plan_term+", \r\n" + 
				"    \"scheduler_json\": {\r\n" + 
				"        \"interval\": 0, \r\n" + 
				"        \"next_time\": \""+DateUtil.getRegularDate(1)+"\", \r\n" + 
				"        \"start_time\": \""+DateUtil.getRegularDate(1)+"\", \r\n" + 
				"        \"times\": 1, \r\n" + 
				"        \"type\": \"once\", \r\n" + 
				"        \"scheduler_id\": \""+scheduler_id+"\"\r\n" + 
				"    }, \r\n" + 
				"    \"access_token\": \""+token+"\"\r\n" + 
				"}","access_token", token);
	}
}
