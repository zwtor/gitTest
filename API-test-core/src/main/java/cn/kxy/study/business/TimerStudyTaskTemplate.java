package cn.kxy.study.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.common.utils.DateUtil;

public class TimerStudyTaskTemplate {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String userId = UserBusiness.getUserId();
	public static String query_list_url = enterpriseUrl + "v2/"+enterpriseId+"/templates/study_list";
	
	public static String queryInfoUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/templates/"+id+"/study";
	}
	
	public static  String updateStatusUrl(String id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/study_templates/"+id+"/update_status";
	}
	
	public static String openStatusUrl(String scheduler_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/schedulers/"+scheduler_id+"/update";
	}
	
	public static String querySchedulersUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/schedulers/"+id;
	}
	
	public static String addUrl = enterpriseUrl +"v2/"+enterpriseId+"/template_study";
	
	public static String deleteStudyUrl(String id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/templates/"+id+"/delete_study";
	}
	
	public static String  editUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/templates/"+id+"/edit_study";
	}
	
	/**   
	 * @Title: addStageTimerStudy   
	 * @Description: TODO(添加阶段闯关的定时学习模板)   
	 * @param: @param title
	 * @param: @param art_id
	 * @param: @param stage
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addStageTimerStudy(String title,String art_id,String stage) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"title\": \""+title+"\",\r\n" + 
				"	\"ding_img_url\": \"\",\r\n" + 
				"	\"get_score\": true,\r\n" + 
				"	\"plan_certificate_id\": \"\",\r\n" + 
				"	\"progress\": 100,\r\n" + 
				"	\"get_score_info\": {\r\n" + 
				"		\"finish_score\": 0,\r\n" + 
				"		\"unfinish_score\": 0\r\n" + 
				"	},\r\n" + 
				"	\"department_ids\": \"\",\r\n" + 
				"	\"group_ids\": \"\",\r\n" + 
				"	\"plan_term\": 2,\r\n" + 
				"	\"post_ids\": \"\",\r\n" + 
				"	\"scheduler_json\": {\r\n" + 
				"		\"interval\": 0,\r\n" + 
				"		\"next_time\": \""+DateUtil.getRegularDateForHHMM(1)+"\",\r\n" + 
				"		\"start_time\": \""+DateUtil.getRegularDateForHHMM(1)+"\",\r\n" + 
				"		\"times\": 0,\r\n" + 
				"		\"type\": \"once\",\r\n" + 
				"		\"scheduler_id\": \"\",\r\n" + 
				"		\"status\": \"on\"\r\n" + 
				"	},\r\n" + 
				"	\"is_all_in\": false,\r\n" + 
				"	\"study_time_limit\": 0,\r\n" + 
				"	\"supervisor_id\": \""+userId+"\",\r\n" + 
				"	\"supervisor_paper_read\": \"true\",\r\n" + 
				"	\"term_type\": \"D\",\r\n" + 
				"	\"times\": \"\",\r\n" + 
				"	\"user_ids\": \""+userId+"\",\r\n" + 
				"	\"stage_json\": [{\r\n" + 
				"		\"content\": \"\",\r\n" + 
				"		\"stage_name\": \"stage one\",\r\n" + 
				"		\"order\": \"true\",\r\n" + 
				"		\"sort\": 1,\r\n" + 
				"		\"course\": [{\r\n" + 
				"			\"course_id\": \""+art_id+"\",\r\n" + 
				"			\"course_sort\": 0,\r\n" + 
				"			\"course_type\": 3,\r\n" + 
				"			\"flag\": 1\r\n" + 
				"		}]\r\n" + 
				"	}],\r\n" + 
				"	\"stage_pass\": \""+stage+"\",\r\n" + 
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}", token, addUrl);
	}
	
	/**   
	 * @Title: addRandomExam   
	 * @Description: TODO  添加随机考试的定时学习任务
	 * @param: @param title
	 * @param: @param question_bank_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static  String addRandomExam(String title ,String question_bank_id ) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"ding_img_url\": \"\", \r\n" + 
				"  \"get_score\": true, \r\n" + 
				"  \"plan_certificate_id\": \"\", \r\n" + 
				
				"  \"progress\": 0, \r\n" + 
				"  \"get_score_info\": {\r\n" + 
				"    \"finish_score\": 0, \r\n" + 
				"    \"unfinish_score\": 0\r\n" + 
				"  }, \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"plan_term\": 5, \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"scheduler_json\": {\r\n" + 
				"    \"interval\": 0, \r\n" + 
				"    \"next_time\": \""+DateUtil.getRegularDateForHHMM(1)+"\", \r\n" + 
				"    \"start_time\": \""+DateUtil.getRegularDateForHHMM(1)+"\", \r\n" + 
				"    \"times\": 0, \r\n" + 
				"    \"type\": \"once\", \r\n" + 
				"    \"scheduler_id\": \"\", \r\n" + 
				"    \"status\": \"on\"\r\n" + 
				"  }, \r\n" + 
				"  \"is_all_in\": false, \r\n" + 
				"  \"study_time_limit\": 0, \r\n" + 
				"  \"supervisor_id\": \""+userId+"\", \r\n" + 
				"  \"supervisor_paper_read\": \"true\", \r\n" + 
				"  \"term_type\": \"D\", \r\n" + 
				"  \"times\": 1, \r\n" + 
				"  \"user_ids\": \""+userId+"\", \r\n" + 
				"  \"stage_json\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stage_name\": \"step one\", \r\n" + 
				"      \"order\": \"false\", \r\n" + 
				"      \"sort\": 1, \r\n" + 
				"      \"course\": [\r\n" + 
				"        {\r\n" + 
				"          \"flag\": 2, \r\n" + 
				"          \"type\": \"ex\", \r\n" + 
				"          \"course_sort\": 0, \r\n" + 
				"          \"course_id\": \"\", \r\n" + 
				"          \"title\": \"exam\", \r\n" + 
				"          \"cheat_flag\": 0, \r\n" + 
				"          \"exam_duration\": 150, \r\n" + 
				"          \"summary\": \"\", \r\n" + 
				"          \"mark_type\": 1, \r\n" + 
				"          \"paper_id\": \"\", \r\n" + 
				"          \"paper_title\": \"\", \r\n" + 
				"          \"pass_line\": 60, \r\n" + 
				"          \"question_bank_list\": \""+question_bank_id+"\", \r\n" + 
				"          \"question_mode\": 2, \r\n" + 
				"          \"show_knowledge\": \"show\", \r\n" + 
				"          \"s_repeat_exam\": \"false\", \r\n" + 
				"          \"multiple_count\": 0, \r\n" + 
				"          \"multiple_unit_score\": 0, \r\n" + 
				"          \"fill_blank_count\": 0, \r\n" + 
				"          \"fill_blank_unit_score\": 0, \r\n" + 
				"          \"short_answer_count\": 0, \r\n" + 
				"          \"short_answer_unit_score\": 0, \r\n" + 
				"          \"single_count\": 1, \r\n" + 
				"          \"single_unit_score\": 100, \r\n" + 
				"          \"true_or_false_count\": 0, \r\n" + 
				"          \"true_or_false_unit_score\": 0, \r\n" + 
				"          \"total_score\": \"\", \r\n" + 
				"          \"answer_parsing\": 2, \r\n" + 
				"          \"passing_score\": 60, \r\n" + 
				"          \"cut_screen_count\": -1, \r\n" + 
				"          \"re_exam_rule\": 0, \r\n" + 
				"          \"re_exam_number\": 0, \r\n" + 
				"          \"viewRule\": 1, \r\n" +
				"          \"score_rule\": 0\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, addUrl);
	}
	
	/**   
	 * @Title: editTimerStudy   
	 * @Description: TODO(编辑定时学习模板)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String editTimerStudy(String id,String editname,String time,String scheduler_id,String resourceId) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+editname+"\", \r\n" + 
				"  \"ding_img_url\": \"\", \r\n" + 
				"  \"get_score\": true, \r\n" + 
				"  \"plan_certificate_id\": \"\", \r\n" + 
				"  \"progress\": 100, \r\n" + 
				"  \"get_score_info\": {\r\n" + 
				"    \"finish_score\": 5, \r\n" + 
				"    \"unfinish_score\": 0\r\n" + 
				"  }, \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"plan_term\": 5, \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"scheduler_json\": {\r\n" + 
				"    \"interval\": 1, \r\n" + 
				"    \"next_time\": \""+time+"\", \r\n" + 
				"    \"start_time\": \""+time+"\", \r\n" + 
				"    \"times\": 3, \r\n" + 
				"    \"type\": \"period\", \r\n" + 
				"    \"scheduler_id\": \""+scheduler_id+"\", \r\n" + 
				"    \"status\": \"on\"\r\n" + 
				"  }, \r\n" + 
				"  \"is_all_in\": false, \r\n" + 
				"  \"study_time_limit\": 0, \r\n" + 
				"  \"supervisor_id\": \""+userId+"\", \r\n" + 
				"  \"supervisor_paper_read\": \"true\", \r\n" + 
				"  \"term_type\": \"D\", \r\n" + 
				"  \"times\": \"\", \r\n" + 
				"  \"user_ids\": \""+userId+"\", \r\n" + 
				"  \"stage_json\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stage_name\": \"step one\", \r\n" + 
				"      \"order\": \"false\", \r\n" + 
				"      \"sort\": 1, \r\n" + 
				"      \"course\": [\r\n" + 
				"        {\r\n" + 
				"          \"course_id\": \""+resourceId+"\", \r\n" + 
				"          \"course_sort\": 0, \r\n" + 
				"          \"course_type\": 3, \r\n" + 
				"          \"flag\": 1\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \"f634df04a041f1dc458bcfbaa1a31f6d\"\r\n" + 
				"}", token, editUrl(id));
	}
	/**   
	 * @Title: addschedulersTimerStudyTask   
	 * @Description: TODO(新增循环定时学习任务)   
	 * @param: @param title
	 * @param: @param time
	 * @param: @param rescourseId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addschedulersTimerStudyTask(String title,String time,String rescourseId,String authority_range) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"\"authority_range\":"+authority_range+","+
				"  \"ding_img_url\": \"\", \r\n" + 
				"  \"get_score\": true, \r\n" + 
				"  \"plan_certificate_id\": \"\", \r\n" + 
				"  \"progress\": 100, \r\n" + 
				"  \"get_score_info\": {\r\n" + 
				"    \"finish_score\": 0, \r\n" + 
				"    \"unfinish_score\": 0\r\n" + 
				"  }, \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"plan_term\": 5, \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"scheduler_json\": {\r\n" + 
				"    \"interval\": 1, \r\n" + 
				"    \"next_time\": \""+time+"\", \r\n" + 
				"    \"start_time\": \""+time+"\", \r\n" + 
				"    \"times\": 3, \r\n" + 
				"    \"type\": \"period\", \r\n" + 
				"    \"scheduler_id\": \"\", \r\n" + 
				"    \"status\": \"on\"\r\n" + 
				"  }, \r\n" + 
				"  \"is_all_in\": false, \r\n" + 
				"  \"study_time_limit\": 0, \r\n" + 
				"  \"supervisor_id\": \""+userId+"\", \r\n" + 
				"  \"supervisor_paper_read\": \"true\", \r\n" + 
				"  \"term_type\": \"D\", \r\n" + 
				"  \"times\": \"\", \r\n" + 
				"  \"user_ids\": \""+userId+"\", \r\n" + 
				"  \"stage_json\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stage_name\": \"step one\", \r\n" + 
				"      \"order\": \"false\", \r\n" + 
				"      \"sort\": 1, \r\n" + 
				"      \"course\": [\r\n" + 
				"        {\r\n" + 
				"          \"course_id\": \""+rescourseId+"\", \r\n" + 
				"          \"course_sort\": 0, \r\n" + 
				"          \"course_type\": 3, \r\n" + 
				"          \"flag\": 1\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, addUrl);
	}
	
	/**   
	 * @Title: openStatus   
	 * @Description: TODO(启用定时学习)   
	 * @param: @param id
	 * @param: @param scheduler_id
	 * @param: @param time
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static  String openStatus(String id,String scheduler_id,String time) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"interval\": 1, \r\n" + 
				"  \"scheduler_id\": \""+scheduler_id+"\", \r\n" + 
				"  \"next_time\": \""+time+"\", \r\n" + 
				"  \"start_time\": \""+time+"\", \r\n" + 
				"  \"status\": \"on\", \r\n" + 
				"  \"template_id\": \""+id+"\", \r\n" + 
				"  \"times\": 5, \r\n" + 
				"  \"type\": \"period\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token,"scope","study", openStatusUrl(scheduler_id));
	}
	
	/**   
	 * @Title: deleteTimerStudy   
	 * @Description: TODO(删除定时学习任务)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteTimerStudy(String id) {
		return PostRequestTools.RequestFormDataByPost(token, deleteStudyUrl(id));
	}

	/**
	 * @Title: addSingleTimerStudy
	 * @Description: TODO(新增单次学习任务)
	 * @param: @param title
	 * @param: @param next_time
	 * @param: @param start_time
	 * @param: @param resource_id
	 * @param: @param course_id
	 * @param: @param paperId
	 * @param: @param paperName
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String addSingleTimerStudy(String title,String next_time,String start_time,
											 String course_id,String paperId,String paperName) {
		return PostRequestTools.RequestBodyByPost("{\r\n" +
				"  \"title\": \""+title+"\", \r\n" +
				"  \"ding_img_url\": \"\", \r\n" +
				"  \"get_score\": true, \r\n" +
				"  \"plan_certificate_id\": \"\", \r\n" +
				"  \"progress\": 100, \r\n" +
				"  \"get_score_info\": {\r\n" +
				"    \"finish_score\": 0, \r\n" +
				"    \"unfinish_score\": 0\r\n" +
				"  }, \r\n" +
				"  \"department_ids\": \"\", \r\n" +
				"  \"group_ids\": \"\", \r\n" +
				"  \"plan_term\": 5, \r\n" +
				"  \"post_ids\": \"\", \r\n" +
				"  \"scheduler_json\": {\r\n" +
				"    \"interval\": 0, \r\n" +
				"    \"next_time\": \""+next_time+"\", \r\n" +
				"    \"start_time\": \""+start_time+"\", \r\n" +
				"    \"times\": 0, \r\n" +
				"    \"type\": \"once\", \r\n" +
				"    \"scheduler_id\": \"\", \r\n" +
				"    \"status\": \"on\"\r\n" +
				"  }, \r\n" +
				"  \"is_all_in\": false, \r\n" +
				"  \"study_time_limit\": 0, \r\n" +
				"  \"supervisor_id\": \""+userId+"\", \r\n" +
				"  \"supervisor_paper_read\": \"true\", \r\n" +
				"  \"term_type\": \"D\", \r\n" +
				"  \"times\": \"\", \r\n" +
				"  \"user_ids\": \""+userId+"\", \r\n" +
				"  \"stage_json\": [\r\n" +
				"    {\r\n" +
				"      \"content\": \"\", \r\n" +
				"      \"stage_name\": \"step one\", \r\n" +
				"      \"order\": \"false\", \r\n" +
				"      \"sort\": 1, \r\n" +
				"      \"course\": [\r\n" +
				"        {\r\n" +
				"          \"course_id\": \""+course_id+"\", \r\n" +
				"          \"course_sort\": 0, \r\n" +
				"          \"course_type\": 1, \r\n" +
				"          \"flag\": 1\r\n" +
				"        }, \r\n" +
				"        {\r\n" +
				"          \"flag\": 2, \r\n" +
				"          \"type\": \"ex\", \r\n" +
				"          \"course_sort\": 1, \r\n" +
				"          \"course_id\": \"\", \r\n" +
				"          \"title\": \"exam\", \r\n" +
				"          \"cheat_flag\": 0, \r\n" +
				"          \"exam_duration\": 45, \r\n" +
				"          \"mark_type\": 1, \r\n" +
				"          \"paper_id\": \""+paperId+"\", \r\n" +
				"          \"paper_title\": \""+paperName+"\", \r\n" +
				"          \"summary\":\"this is a summary\",\r\n"+
				"          \"pass_line\": 60, \r\n" +
				"          \"question_bank_list\": \"\", \r\n" +
				"          \"question_mode\": 1, \r\n" +
				"          \"show_knowledge\": \"show\", \r\n" +
				"          \"s_repeat_exam\": \"true\", \r\n" +
				"          \"multiple_count\": \"\", \r\n" +
				"          \"multiple_unit_score\": \"\", \r\n" +
				"          \"fill_blank_count\": \"\", \r\n" +
				"          \"fill_blank_unit_score\": \"\", \r\n" +
				"          \"short_answer_count\": \"\", \r\n" +
				"          \"short_answer_unit_score\": \"\", \r\n" +
				"          \"single_count\": \"\", \r\n" +
				"          \"single_unit_score\": \"\", \r\n" +
				"          \"true_or_false_count\": \"\", \r\n" +
				"          \"true_or_false_unit_score\": \"\", \r\n" +
				"          \"total_score\": 100, \r\n" +
				"          \"answer_parsing\": 5, \r\n" +
				"          \"passing_score\": \"60.0\", \r\n" +
				"          \"cut_screen_count\": 0, \r\n" +
				"          \"re_exam_rule\": 0, \r\n" +
				"          \"re_exam_number\": 0, \r\n" +
				"          \"view_rule\": 1, \r\n" +
				"          \"score_rule\": 0\r\n" +
				"        }\r\n" +
				"      ]\r\n" +
				"    }\r\n" +
				"  ], \r\n" +
				"  \"access_token\": \""+token+"\"\r\n" +
				"}", token, addUrl);
	}

	public static String addSingleTimerStudy(String title,String next_time,String start_time,String resource_id,
											 String course_id,String paperId,String paperName) {
		return PostRequestTools.RequestBodyByPost("{\r\n" +
				"  \"title\": \""+title+"\", \r\n" +
				"  \"ding_img_url\": \"\", \r\n" +
				"  \"get_score\": true, \r\n" +
				"  \"plan_certificate_id\": \"\", \r\n" +
				"  \"progress\": 100, \r\n" +
				"  \"get_score_info\": {\r\n" +
				"    \"finish_score\": 0, \r\n" +
				"    \"unfinish_score\": 0\r\n" +
				"  }, \r\n" +
				"  \"department_ids\": \"\", \r\n" +
				"  \"group_ids\": \"\", \r\n" +
				"  \"plan_term\": 5, \r\n" +
				"  \"post_ids\": \"\", \r\n" +
				"  \"scheduler_json\": {\r\n" +
				"    \"interval\": 0, \r\n" +
				"    \"next_time\": \""+next_time+"\", \r\n" +
				"    \"start_time\": \""+start_time+"\", \r\n" +
				"    \"times\": 0, \r\n" +
				"    \"type\": \"once\", \r\n" +
				"    \"scheduler_id\": \"\", \r\n" +
				"    \"status\": \"on\"\r\n" +
				"  }, \r\n" +
				"  \"is_all_in\": false, \r\n" +
				"  \"study_time_limit\": 0, \r\n" +
				"  \"supervisor_id\": \""+userId+"\", \r\n" +
				"  \"supervisor_paper_read\": \"true\", \r\n" +
				"  \"term_type\": \"D\", \r\n" +
				"  \"times\": \"\", \r\n" +
				"  \"user_ids\": \""+userId+"\", \r\n" +
				"  \"stage_json\": [\r\n" +
				"    {\r\n" +
				"      \"content\": \"\", \r\n" +
				"      \"stage_name\": \"step one\", \r\n" +
				"      \"order\": \"false\", \r\n" +
				"      \"sort\": 1, \r\n" +
				"      \"course\": [\r\n" +
				"        {\r\n" +
				"          \"course_id\": \""+resource_id+"\", \r\n" +
				"          \"course_sort\": 0, \r\n" +
				"          \"course_type\": 3, \r\n" +
				"          \"flag\": 1\r\n" +
				"        }, \r\n" +
				"        {\r\n" +
				"          \"course_id\": \""+course_id+"\", \r\n" +
				"          \"course_sort\": 1, \r\n" +
				"          \"course_type\": 1, \r\n" +
				"          \"flag\": 1\r\n" +
				"        }, \r\n" +
				"        {\r\n" +
				"          \"flag\": 2, \r\n" +
				"          \"type\": \"ex\", \r\n" +
				"          \"course_sort\": 2, \r\n" +
				"          \"course_id\": \"\", \r\n" +
				"          \"title\": \"exam\", \r\n" +
				"          \"cheat_flag\": 0, \r\n" +
				"          \"exam_duration\": 45, \r\n" +
				"          \"mark_type\": 1, \r\n" +
				"          \"paper_id\": \""+paperId+"\", \r\n" +
				"          \"paper_title\": \""+paperName+"\", \r\n" +
				"          \"summary\":\"this is a summary\",\r\n"+
				"          \"pass_line\": 60, \r\n" +
				"          \"question_bank_list\": \"\", \r\n" +
				"          \"question_mode\": 1, \r\n" +
				"          \"show_knowledge\": \"show\", \r\n" +
				"          \"s_repeat_exam\": \"true\", \r\n" +
				"          \"multiple_count\": \"\", \r\n" +
				"          \"multiple_unit_score\": \"\", \r\n" +
				"          \"fill_blank_count\": \"\", \r\n" +
				"          \"fill_blank_unit_score\": \"\", \r\n" +
				"          \"short_answer_count\": \"\", \r\n" +
				"          \"short_answer_unit_score\": \"\", \r\n" +
				"          \"single_count\": \"\", \r\n" +
				"          \"single_unit_score\": \"\", \r\n" +
				"          \"true_or_false_count\": \"\", \r\n" +
				"          \"true_or_false_unit_score\": \"\", \r\n" +
				"          \"total_score\": 100, \r\n" +
				"          \"answer_parsing\": 5, \r\n" +
				"          \"passing_score\": \"60.0\", \r\n" +
				"          \"cut_screen_count\": 0, \r\n" +
				"          \"re_exam_rule\": 0, \r\n" +
				"          \"re_exam_number\": 0, \r\n" +
				"          \"view_rule\": 1, \r\n" +
				"          \"score_rule\": 0\r\n" +
				"        }\r\n" +
				"      ]\r\n" +
				"    }\r\n" +
				"  ], \r\n" +
				"  \"access_token\": \""+token+"\"\r\n" +
				"}", token, addUrl);
	}
	
	/**   
	 * @Title: querySchedulers   
	 * @Description: TODO(查看定时任务的调度信息)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String querySchedulers(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, querySchedulersUrl(id));
	}
	
	/**   
	 * @Title: updateStatus   
	 * @Description: TODO(停用定时考试模板)   
	 * @param: @param id
	 * @param: @param type 
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String updateCloseStatus(String id) {
		return PostRequestTools.RequestBodyByPost("{\"type\":\"off\",\"access_token\":\""+token+"\"}", token, updateStatusUrl(id));
	}
	
	/**   
	 * @Title: queryInfo   
	 * @Description: TODO(查看详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, queryInfoUrl(id));
	}
	
	/**   
	 * @Title: queryList   
	 * @Description: TODO(查看列表)   
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryList(String keyword,String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"status",status,"page_number","1","page_size","20",
				"keyword",keyword,query_list_url);
	}
}
