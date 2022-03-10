package cn.kxy.study.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.ClassificationBusines;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;

public class StudyProjectBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String userId = UserBusiness.getUserId();
	
	public static String add_url = enterpriseUrl + "v2/"+enterpriseId+"/study_projects/add";
	
	public static String list_url = enterpriseUrl+"v2/"+enterpriseId+"/study_projects_fast";
	
	public static String class_id = ClassificationBusines.getPrimaryId();
	
	public static String updateStatusUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/study_projects/"+id+"/update_status";
	}
	
	public static String loadProjectUrl(String id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/users/"+userId+"/study_projects/"+id+"/load_resources";
	}
	
	public static String check_resource_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/study_projects/"+id+"/check_resource";
	}
	
	public static String query_resource_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/study_projects/"+id+"/query";
	}
	
	public static String assign_url(String id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/study_projects/"+id+"/assign";
	}
	
	public static String push_message_url(String id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/study_projects/"+id+"/push_message";
	}
	
	public static String set_visible_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/study_projects/"+id+"/set_visible";
	}
	
	public static String study_projects_export_url = enterpriseUrl + "v2/1016929454485803038/study_projects_export";
	
	public static String batch_set_visible_url = enterpriseUrl + "v2/"+enterpriseId+"/study_projects/batch_set_visible";
	
	public static String study_audits_log_Url = EnterpriseDataUrl.getFeedUrl() + "v2/"+enterpriseId+"/audits";
	
	public static String delete_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/study_projects/"+id+"/delete";
	}
	
	public static String edit_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/study_projects/"+id+"/update";
	}
	
	public static String user_monitors_export_url(String id) {
		return enterpriseUrl  + "v2/"+enterpriseId+"/study_projects/"+id+"/user_monitors_export";
	}
	
	public static String user_monitors_url(String id) {
		return enterpriseUrl  + "v2/"+enterpriseId+"/study_projects/"+id+"/user_monitors";
	}
	
	public static String signUpUrl (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/enrollments/"+id;
	}
	
	public static String signUpListUrl (String id) {
		return  enterpriseUrl + "v2/"+enterpriseId+"/enrollments/"+id+"/users";
	}
	
	public static String batch_approve_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/enrollments/"+id+"/batch_approve";
	}
	
	public static String approve_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/enrollments/"+id+"/approve";
	}
	
	public static String enroll_approvals_url = enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/enroll_approvals";
	
	public static String app_latojas_url = enterpriseUrl + "v2/"+enterpriseId+"/latojas";
	
	public static String queryAppLatojasDetailUrl (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/latojas/"+id;
	}
	
	public static String queryProjectCourseUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/study_projects/"+id+"/query";
	}
	
	public static String saveProjectCouseProcessUrl(String id,String art_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/study_projects/"+id+"/courses/"+art_id+"/resources/"+art_id+"/save_progress";
	}
	
	public static String startStudyUrl (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/study_projects/"+id+"/start_study";
	}
	
	public static String query_latoja_monitors_url (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/study_projects/"+id+"/latoja_monitors";
	}
	
	public static String query_investigate_monitors_url (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/study_projects/"+id+"/investigate_monitors";
	}
	
	public static String query_exam_monitors_url (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/study_projects/"+id+"/exam_monitors";
	}
	
	public static String queryInvestigateUsersMonitors_url(String id,String inves_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/study_projects/"+id+"/investigates/"+inves_id+"/investigate_users_monitors";
	}
	
	public static String queryInvestigateResultMonitors_url(String id,String inves_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/study_projects/"+id+"/investigates/"+inves_id+"/investigate_result_monitors";
	}
	
	public static String queryInvestigateResultMonitors(String id,String inves_id) {
		return HttpRequest.get(queryInvestigateResultMonitors_url(id,inves_id)).query("access_token", token).send().body();
	}
	public static void main(String[] args) {
		System.out.println(DateUtil.getRegularDateForYYDDMM(-7));
	}
	public static String exportStudyProjects(String keyword) {
		return HttpRequest.get(study_projects_export_url).query("access_token", token).query("begin_time",DateUtil.getRegularDateForYYDDMM(-7))
				.query("end_time",DateUtil.getRegularDateForYYDDMM(0)).query("keyword", keyword).send().body();
	}
	
	/**   
	 * @Title: queryExamMonitors   
	 * @Description: TODO  
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryExamMonitors(String id) {
		return HttpRequest.get(query_exam_monitors_url(id)).query("access_token", token).query("page_number","1").
				query("page_size", "20").send().body();
	}
	/**   
	 * @Title: queryInvestigateUsersMonitors   
	 * @Description: TODO
	 * @param: @param id
	 * @param: @param inves_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryInvestigateUsersMonitors(String id,String inves_id) {
		return HttpRequest.get(queryInvestigateUsersMonitors_url(id,inves_id)).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: queryInvestigateMonitors   
	 * @Description: TODO  查看调研的监控
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryInvestigateMonitors(String id) {
		return HttpRequest.get(query_investigate_monitors_url(id)).query("access_token", token).query("page_number","1").
				query("page_size", "20").send().body();
	}
	
	/**   
	 * @Title: queryLatojaMonitors   
	 * @Description: TODO  查看线下签到的监控
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryLatojaMonitors(String id) {
		return HttpRequest.get(query_latoja_monitors_url(id)).query("access_token", token).query("page_number","1").
				query("page_size", "20").send().body();
	}
	
	public static String startStudy (String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, startStudyUrl(id));
	}
	
	/**   
	 * @Title: queryStudyProjectUserMonitors   
	 * @Description: TODO  查看学习项目监控
	 * @param: @param id
	 * @param: @param study_status
	 * @param: @param qualified_status
	 * @param: @param keyword
	 * @param: @param department_id
	 * @param: @param post_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryStudyProjectUserMonitors(String id,String study_status,String qualified_status,String keyword,String department_id,String post_id) {
		return HttpRequest.get(user_monitors_url(id)).query("page_number", "1").query("page_size","20").query("access_token", token)
				.query("study_status",study_status).query("qualified_status",qualified_status).query("keyword",keyword).
				query("department_id",department_id).send().body();
	}
	
	/**   
	 * @Title: addAuthorityRangeProject   
	 * @Description: TODO  新增管辖范围的学习项目
	 * @param: @param title
	 * @param: @param classify
	 * @param: @param course_id
	 * @param: @param authority_range
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addAuthorityRangeProject(String title,String classify ,String course_id,String authority_range) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\":\""+title+"\",\r\n" + 
				"  \"cover\":\"https://oss.coolcollege.cn/1789917624419880960.png\",\r\n" + 
				"  \"base_cover\":\"https://oss.coolcollege.cn/1789917624419880960.png\",\r\n" + 
				"  \"cover_type\":1,\r\n" + 
				"  \"classify\":\""+classify+"\",\r\n" + 
				"  \"summary\":\"\",\r\n" + 
				"  \"ding_img_url\":\"\",\r\n" + 
				"  \"is_get_score\":true,\r\n" + 
				"  \"certificate_id\":\"\",\r\n" + 
				"  \"progress\":100,\r\n" + 
				"  \"study_score\":{\r\n" + 
				"    \"finish_score\":0,\r\n" + 
				"    \"unfinish_score\":0\r\n" + 
				"  },\r\n" + 
				"  \"study_time_limit\":\"30\",\r\n" + 
				"  \"face_recognition\":\"off\",\r\n" + 
				"  \"supervisor_ids\":[\r\n" + 
				"    \""+userId+"\"],\r\n" + 
				"  \"supervisor_paper\":true,\r\n" + 
				"  \"authority_range\":"+authority_range+",\r\n" + 
				"  \"times\":\"\",\r\n" + 
				"  \"stages\":[\r\n" + 
				"    {\r\n" + 
				"      \"content\":\"\",\r\n" + 
				"      \"stage_name\":\"阶段一\",\r\n" + 
				"      \"is_order\":false,\r\n" + 
				"      \"sort\":1,\r\n" + 
				"      \"resources\":[\r\n" + 
				"        {\r\n" + 
				"          \"course_id\":\""+course_id+"\",\r\n" + 
				"          \"sort\":0,\r\n" + 
				"          \"type\":3\r\n" + 
				"        }]\r\n" + 
				"    }],\r\n" + 
				"  \"is_all\":1,\r\n" + 
				"  \"user_ids\":\"\",\r\n" + 
				"  \"department_ids\":\"\",\r\n" + 
				"  \"supervisor_department_ids\":[\r\n" + 
				"  ],\r\n" + 
				"  \"operationTimes\":\"\",\r\n" + 
				"  \"group_ids\":\"\",\r\n" + 
				"  \"post_ids\":\"\",\r\n" + 
				"  \"enroll_audit\":\"un_need\",\r\n" + 
				"  \"enroll_limit\":\"false\",\r\n" + 
				"  \"limit_count\":1,\r\n" + 
				"  \"stage_pass\":\"false\",\r\n" + 
				"  \"finishEvaluation\":false,\r\n" + 
				"  \"openEvaluationResult\":false,\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"", token, add_url);
	}
	
	/**   
	 * @Title: saveProjectCouseProcess   
	 * @Description: TODO(学习项目启用后，在课程列表页,保存进度)   
	 * @param: @param id
	 * @param: @param art_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String saveProjectCouseProcess(String id,String art_id) {
		
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"progress\": 100,\r\n" + 
				"	\"recent_start\": 1,\r\n" + 
				"	\"tempTime\": "+DateUtil.getTimeStamp(0, "")+",\r\n" + 
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}", token, saveProjectCouseProcessUrl(id,art_id));
	}
	/**   
	 * @Title: queryProjectCourse   
	 * @Description: TODO(学习项目启用后，在课程列表页查看课程详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryProjectCourse(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id", enterpriseId, "user_id",userId,  queryProjectCourseUrl(id));
	}
	
	/**   
	 * @Title: loadProject   
	 * @Description: TODO(学习项目启用后，在课程列表页加载资源)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String loadProject (String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, loadProjectUrl(id));
	}
	
	/**   
	 * @Title: addStageProject   
	 * @Description: TODO(添加阶段闯关的学习项目)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addStageProject(String title,String course_id_01,String course_id_02,String course_id_03) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"title\": \""+title+"\",\r\n" + 
				"	\"cover\": \"https://oss.coolcollege.cn/1789917624419880960.png\",\r\n" + 
				"	\"base_cover\": \"https://oss.coolcollege.cn/1789917624419880960.png\",\r\n" + 
				"	\"cover_type\": 1,\r\n" + 
				"	\"classify\": \""+class_id+"\",\r\n" + 
				"	\"ding_img_url\": \"\",\r\n" + 
				"	\"is_get_score\": true,\r\n" + 
				"	\"certificate_id\": \"\",\r\n" + 
				" \"supervisor_department_ids\": [], \r\n" + 
				"  \"operationTimes\": \"\", "+
				"	\"progress\": 100,\r\n" + 
				"	\"study_score\": {\r\n" + 
				"		\"finish_score\": 0,\r\n" + 
				"		\"unfinish_score\": 0\r\n" + 
				"	},\r\n" + 
				"	\"study_time_limit\": 0,\r\n" + 
				"	\"supervisor_ids\": [\""+userId+"\"],\r\n" + 
				"	\"supervisor_paper\": true,\r\n" + 
				"	\"times\": \"\",\r\n" + 
				"	\"stages\": [{\r\n" + 
				"		\"content\": \"\",\r\n" + 
				"		\"stage_name\": \"stage one\",\r\n" + 
				"		\"is_order\": true,\r\n" + 
				"		\"sort\": 1,\r\n" + 
				"		\"resources\": [{\r\n" + 
				"			\"course_id\": \""+course_id_01+"\",\r\n" + 
				"			\"sort\": 0,\r\n" + 
				"			\"type\": 3\r\n" + 
				"		}, {\r\n" + 
				"			\"course_id\": \""+course_id_02+"\",\r\n" + 
				"			\"sort\": 1,\r\n" + 
				"			\"type\": 3\r\n" + 
				"		}]\r\n" + 
				"	}, {\r\n" + 
				"		\"content\": \"\",\r\n" + 
				"		\"stage_name\": \"stage two\",\r\n" + 
				"		\"is_order\": true,\r\n" + 
				"		\"sort\": 2,\r\n" + 
				"		\"resources\": [{\r\n" + 
				"			\"course_id\": \""+course_id_03+"\",\r\n" + 
				"			\"sort\": 0,\r\n" + 
				"			\"type\": 3\r\n" + 
				"		}]\r\n" + 
				"	}],\r\n" + 
				"	\"is_all\": 1,\r\n" + 
				"	\"user_ids\": \"\",\r\n" + 
				"	\"department_ids\": \"\",\r\n" + 
				"	\"group_ids\": \"\",\r\n" + 
				"	\"post_ids\": \"\",\r\n" + 
				"	\"enroll_audit\": \"un_need\",\r\n" + 
				"	\"enroll_limit\": \"false\",\r\n" + 
				"	\"limit_count\": 1,\r\n" + 
				"	\"stage_pass\": \"true\",\r\n" + 
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_url);
	}
	
	/**   
	 * @Title: addRondomExamStudyProject   
	 * @Description: TODO(添加随机考试的学习项目)   
	 * @param: @param title
	 * @param: @param question_banks_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String  addRondomExamStudyProject(String title,String question_banks_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"cover\": \"https://oss.coolcollege.cn/1789917624419880960.png\", \r\n" + 
				"  \"base_cover\": \"https://oss.coolcollege.cn/1789917624419880960.png\", \r\n" + 
				"  \"cover_type\": 1, \r\n" + 
				"  \"classify\": \""+class_id+"\", \r\n" + 
				"  \"ding_img_url\": \"\", \r\n" + 
				"  \"is_get_score\": true, \r\n" + 
				"  \"certificate_id\": \"\", \r\n" + 
				"  \"progress\": 0, \r\n" + 
				"  \"study_score\": {\r\n" + 
				"    \"finish_score\": 0, \r\n" + 
				"    \"unfinish_score\": 0\r\n" + 
				"  }, \r\n" + 
				"  \"study_time_limit\": 0, \r\n" + 
				" \"supervisor_department_ids\": [], \r\n" + 
				"  \"operationTimes\": \"\", "+
				"  \"supervisor_ids\": [\r\n" + 
				"    \""+userId+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"supervisor_paper\": true, \r\n" + 
				"  \"times\": 1, \r\n" + 
				"  \"stages\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stage_name\": \"step one\", \r\n" + 
				"      \"is_order\": false, \r\n" + 
				"      \"sort\": 1, \r\n" + 
				"      \"resources\": [\r\n" + 
				"        {\r\n" + 
				"          \"type\": 6, \r\n" + 
				"          \"sort\": 0, \r\n" + 
				"          \"course_id\": \"\", \r\n" + 
				"          \"exam\": {\r\n" + 
				"            \"title\": \"DeleteExam\", \r\n" + 
				"            \"cheat_flag\": 0, \r\n" + 
				"            \"exam_duration\": 45, \r\n" + 
				"            \"summary\": \"\", \r\n" + 
				"            \"mark_type\": 1, \r\n" + 
				"            \"paper_id\": \"\", \r\n" + 
				"            \"pass_line\": 60, \r\n" + 
				"            \"question_banks\": [\r\n" + 
				"              \""+question_banks_id+"\"\r\n" + 
				"            ], \r\n" + 
				"            \"question_mode\": 2, \r\n" + 
				"            \"show_knowledge\": \"show\", \r\n" + 
				"            \"repeat_exam\": false, \r\n" + 
				"            \"multiple_count\": 0, \r\n" + 
				"            \"multiple_unit_score\": 0, \r\n" + 
				"            \"fill_blank_count\": 0, \r\n" + 
				"            \"fill_blank_unit_score\": 0, \r\n" + 
				"            \"short_answer_count\": 0, \r\n" + 
				"            \"short_answer_unit_score\": 0, \r\n" + 
				"            \"single_count\": 1, \r\n" + 
				"            \"single_unit_score\": 10, \r\n" + 
				"            \"true_or_false_count\": 0, \r\n" + 
				"            \"true_or_false_unit_score\": 0, \r\n" + 
				"            \"total_score\": \"\", \r\n" + 
				"            \"answer_parsing\": 2, \r\n" + 
				"            \"passing_score\": 60, \r\n" + 
				"            \"cut_screen_count\": -1, \r\n" + 
				"            \"re_exam_rule\": 0, \r\n" + 
				"            \"re_exam_number\": 0, \r\n" + 
				"          \"view_rule\": 1, \r\n" +
				"            \"score_rule\": 0\r\n" + 
				"          }\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"is_all\": 3, \r\n" + 
				"  \"user_ids\": \"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"enroll_audit\": \"un_need\", \r\n" + 
				"  \"enroll_limit\": \"false\", \r\n" + 
				"  \"limit_count\": 1, \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_url);
	}
	
	/**   
	 * @Title: queryAppLatojasDetail   
	 * @Description: TODO(查看app签到详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppLatojasDetail(String id) {
		return  GetRequestTools.RequestQueryParamsByGet ("access_token",token,"enterprise_id",enterpriseId,"user_id",
				userId,queryAppLatojasDetailUrl(id));
	}
	
	/**   
	 * @Title: queryAppLatojasList   
	 * @Description: TODO(查看app线下签到)   
	 * @param: @param type  （0--未结束，1--已结束）
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppLatojasList (String type) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "page_number", "1", "page_size","10", 
				"enterprise_id",enterpriseId,"user_id",userId,"type",type,app_latojas_url);
	}
	
	/**   
	 * @Title: signUpAppList   
	 * @Description: TODO(App审批列表)   
	 * @param: @param id
	 * @param: @param status (unapproved--待审批  、 approved--已审批)
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String signUpAppList(String id,String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "page_number", "1", "page_size","10", 
				"enterprise_id",enterpriseId,"user_id",userId,"status",status,signUpListUrl(id));
	}
	
	/**   
	 * @Title: queryAppApprovalList   
	 * @Description: TODO(报名审批列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppApprovalList() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "enterprise_id", enterpriseId,"user_id",userId, 
				"page_number","1","page_size","20",enroll_approvals_url);
	}
	
	/**   
	 * @Title: approveSignUp   
	 * @Description: TODO(单独审批)   
	 * @param: @param id
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String approveSignUp(String id,String status) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"status\": \""+status+"\", \r\n" + 
				"  \"user_id\": \""+userId+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, approve_url(id));
	}
	
	/**   
	 * @Title: approveSignUp   jijmmsalmkjlkmmmmmmnvimnnkknmnk
	 * sd    sdsdamsdn 是的，s
	 * @Description: TODO(批量审批)   
	 * @param: @param id
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String batchApproveSignUp(String id,String status) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"status\": \""+status+"\", \r\n" + 
				"  \"user_ids\": [\r\n" + 
				"    \""+userId+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, batch_approve_url(id));
	}
	
	/**   
	 * @Title: signUpList   
	 * @Description: TODO(web端)   
	 * @param: @param id
	 * @param: @param keyword
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String signUpList(String id,String keyword,String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"department_id", "1", "page_number", "1", "page_size","10", 
				"type","1","keyword",keyword,"status",status,signUpListUrl(id));
	}
	
	/**   
	 * @Title: signUp   
	 * @Description: TODO(学习项目报名)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String signUp(String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, signUpUrl(id));
	}
	
	/**   
	 * @Title: getExportMonitorsCode   
	 * @Description: TODO(导出学习项目)   
	 * @param: @param id
	 * @param: @param study_status
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static String getExportMonitorsCode(String id,String study_status) {
		return HttpRequest.get(user_monitors_export_url(id)).query("access_token", token).query("department_id","1").query("qualified_status","0")
		.query("study_status",study_status).send().body();
	}
	

	public static String addStudyProjectOfflineSingUp(String title,String xx_title,String beginTime,String endTime ,
			 String lecturerId,String clockTime,String enroll_audit,String enroll_limit,String limit_count)  {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"cover\": \"https://oss.coolcollege.cn/1789917624419880960.png\", \r\n" + 
				"  \"base_cover\": \"https://oss.coolcollege.cn/1789917624419880960.png\", \r\n" + 
				"  \"cover_type\": 1, \r\n" + 
				"  \"classify\": \""+class_id+"\", \r\n" + 
				"  \"ding_img_url\": \"\", \r\n" + 
				"  \"is_get_score\": true, \r\n" + 
				"  \"certificate_id\": \"\", \r\n" + 
				"  \"progress\": 100, \r\n" + 
				" \"supervisor_department_ids\": [], \r\n" + 
				"  \"operationTimes\": \"\", "+
				"  \"study_score\": {\r\n" + 
				"    \"finish_score\": 0, \r\n" + 
				"    \"unfinish_score\": 0\r\n" + 
				"  }, \r\n" + 
				"  \"study_time_limit\": 0, \r\n" + 
				"  \"supervisor_ids\": [\r\n" + 
				"    \""+userId+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"supervisor_paper\": true, \r\n" + 
				"  \"times\": 1, \r\n" + 
				" \"stages\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stage_name\": \"Step one\", \r\n" + 
				"      \"is_order\": false, \r\n" + 
				"      \"sort\": 1, \r\n" + 
				"      \"resources\": [\r\n" + 
				"        {\r\n" + 
				"          \"sort\": 0, \r\n" + 
				"          \"type\": 7, \r\n" + 
				"          \"course_id\": \"\", \r\n" + 
				"          \"latoja\": {\r\n" + 
				"            \"address\": \"xian\", \r\n" + 
				"            \"title\": \""+xx_title+"\", \r\n" + 
				"            \"introduction\": \"<p>des</p>\", \r\n" + 
				"            \"begin_time\": "+beginTime+", \r\n" + 
				"            \"end_time\": "+endTime+", \r\n" + 
				"            \"lecturer_id\": \""+lecturerId+"\", \r\n" + 
				"            \"clock_times\": [\r\n" + 
				"              {\r\n" + 
				"                \"clock_time\": "+clockTime+", \r\n" + 
				"                \"sort\": 0, \r\n" + 
				"                \"id\": \"\"\r\n" + 
				"              }\r\n" + 
				"            ], \r\n" + 
				"            \"directors\": [\r\n" + 
				"              \""+userId+"\"\r\n" + 
				"            ], \r\n" + 
				"            \"qrcode_type\": \"statics\"\r\n" + 
				"          }\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], "+
				"  \"is_all\": 1, \r\n" + 
				"  \"user_ids\": \"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"enroll_audit\": \""+enroll_audit+"\", \r\n" + 
				"  \"enroll_limit\": \""+enroll_limit+"\", \r\n" + 
				"  \"limit_count\": "+limit_count+", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_url);
	}
	
	
	/**   
	 * @Title: addStudyProjectSingUp   
	 * @Description: TODO()   
	 * @param: @param title
	 * @param: @param paper_id
	 * @param: @param mark_type (1系统阅卷2.人工阅卷)
	 * @param: @param repeat_exam
	 * @param: @param answer_parsing (5--交卷后可查看解析)
	 * @param: @param re_exam_rule (0--学员及格后完成重考)
	 * @param: @param re_exam_number
	 * @param: @param enroll_audit(manual_audit--自动审批  automatic_audit)
	 * @param: @param enroll_limit(false  true)
	 * @param: @param limit_count
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addStudyProjectSingUp(String title ,String paper_id,String mark_type,String repeat_exam,String answer_parsing,
			String re_exam_rule,String re_exam_number,String enroll_audit,String enroll_limit,String limit_count)  {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"cover\": \"https://oss.coolcollege.cn/1789917624419880960.png\", \r\n" + 
				"  \"base_cover\": \"https://oss.coolcollege.cn/1789917624419880960.png\", \r\n" + 
				"  \"cover_type\": 1, \r\n" + 
				"  \"classify\": \""+class_id+"\", \r\n" + 
				"  \"ding_img_url\": \"\", \r\n" + 
				"  \"is_get_score\": true, \r\n" + 
				"  \"certificate_id\": \"\", \r\n" + 
				" \"supervisor_department_ids\": [ ], \r\n" + 
				"  \"operationTimes\": \"\", "+
				"  \"progress\": 100, \r\n" + 
				"  \"study_score\": {\r\n" + 
				"    \"finish_score\": 0, \r\n" + 
				"    \"unfinish_score\": 0\r\n" + 
				"  }, \r\n" + 
				"  \"study_time_limit\": 0, \r\n" + 
				"  \"supervisor_ids\": [\r\n" + 
				"    \""+userId+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"supervisor_paper\": true, \r\n" + 
				"  \"times\": 1, \r\n" + 
				"  \"stages\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stage_name\": \"step one\", \r\n" + 
				"      \"is_order\": false, \r\n" + 
				"      \"sort\": 1, \r\n" + 
				"      \"resources\": [\r\n" + 
			       
				"        {\r\n" + 
				"          \"type\": 6, \r\n" + 
				"          \"sort\": 1, \r\n" + 
				"          \"course_id\": \"\", \r\n" + 
				"          \"exam\": {\r\n" + 
				"            \"title\": \"PassedPaper\", \r\n" + 
				"            \"cheat_flag\": 1, \r\n" + 
				"            \"exam_duration\": 45, \r\n" + 
				"            \"summary\": \"this is a summary\", \r\n" + 
				"            \"mark_type\": "+mark_type+", \r\n" + 
				"            \"paper_id\": \""+paper_id+"\", \r\n" + 
				"            \"pass_line\": 60, \r\n" + 
				"            \"question_banks\": [ ], \r\n" + 
				"            \"question_mode\": 1, \r\n" + 
				"            \"show_knowledge\": \"show\", \r\n" + 
				"            \"repeat_exam\": "+repeat_exam+", \r\n" + 
				"            \"multiple_count\": \"\", \r\n" + 
				"            \"multiple_unit_score\": \"\", \r\n" + 
				"            \"fill_blank_count\": \"\", \r\n" + 
				"            \"fill_blank_unit_score\": \"\", \r\n" + 
				"            \"short_answer_count\": \"\", \r\n" + 
				"            \"short_answer_unit_score\": \"\", \r\n" + 
				"            \"single_count\": \"\", \r\n" + 
				"            \"single_unit_score\": \"\", \r\n" + 
				"            \"true_or_false_count\": \"\", \r\n" + 
				"            \"true_or_false_unit_score\": \"\", \r\n" + 
				"            \"total_score\": 100, \r\n" + 
				"            \"answer_parsing\": "+answer_parsing+", \r\n" + 
				"            \"passing_score\": \"60.0\", \r\n" + 
				"            \"cut_screen_count\": 1, \r\n" + 
				"            \"re_exam_rule\": "+re_exam_rule+", \r\n" + 
				"            \"re_exam_number\": "+re_exam_number+", \r\n" + 
				"          \"view_rule\": 1, \r\n" +
				"            \"score_rule\": 0\r\n" + 
				"          }\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"is_all\": 1, \r\n" + 
				"  \"user_ids\": \"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"enroll_audit\": \""+enroll_audit+"\", \r\n" + 
				"  \"enroll_limit\": \""+enroll_limit+"\", \r\n" + 
				"  \"limit_count\": "+limit_count+", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_url);
	}
	
	/**   
	 * @Title: editStudyProject   
	 * @Description: TODO(编辑学习项目)   
	 * @param: @param id
	 * @param: @param title
	 * @param: @param classify
	 * @param: @param paper_id
	 * @param: @param certificate_id
	 * @param: @param resource_id
	 * @param: @param course_id
	 * @param: @param questionnaire_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String editStudyProject(String id,String exam_id,String ques_id,String title ,String classify,String paper_id,String certificate_id,String resource_id,String course_id,String questionnaire_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"cover\": \"https://oss.coolcollege.cn/1789917624419880960.png\", \r\n" + 
				"  \"base_cover\": \"https://oss.coolcollege.cn/1789917624419880960.png\", \r\n" + 
				"  \"cover_type\": 1, \r\n" + 
				"  \"classify\": \""+classify+"\", \r\n" + 
				"  \"ding_img_url\": \"\", \r\n" + 
				"  \"is_get_score\": true, \r\n" + 
				"  \"certificate_id\": \""+certificate_id+"\", \r\n" + 
				"  \"progress\": 100, \r\n" + 
				"  \"study_score\": {\r\n" + 
				"    \"finish_score\": 2, \r\n" + 
				"    \"unfinish_score\": 0\r\n" + 
				"  }, \r\n" + 
				"  \"study_time_limit\": 0, \r\n" + 
				"  \"supervisor_ids\": [\r\n" + 
				"    \""+userId+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"supervisor_paper\": true, \r\n" + 
				"  \"times\": 1, \r\n" + 
				"  \"stages\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stage_name\": \"step one\", \r\n" + 
				"      \"is_order\": false, \r\n" + 
				"      \"sort\": 1, \r\n" + 
				"      \"resources\": [\r\n" + 
				"        {\r\n" + 
				"          \"course_id\": \""+resource_id+"\", \r\n" + 
				"          \"sort\": 0, \r\n" + 
				"          \"type\": 3\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"course_id\": \""+course_id+"\", \r\n" + 
				"          \"sort\": 1, \r\n" + 
				"          \"type\": 1\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"type\": 6, \r\n" + 
				"          \"sort\": 2, \r\n" + 
				"          \"course_id\": \""+exam_id+"\", \r\n" + 
				"          \"exam\": {\r\n" + 
				"            \"title\": \"PassedPaper\", \r\n" + 
				"            \"cheat_flag\": 0, \r\n" + 
				"            \"exam_duration\": 45, \r\n" + 
				"            \"summary\": \"this is a summary\", \r\n" + 
				"            \"mark_type\": 1, \r\n" + 
				"            \"paper_id\": \""+paper_id+"\", \r\n" + 
				"            \"pass_line\": 60, \r\n" + 
				"            \"question_banks\": [ ], \r\n" + 
				"            \"question_mode\": 1, \r\n" + 
				"            \"show_knowledge\": \"show\", \r\n" + 
				"            \"repeat_exam\": true, \r\n" + 
				"            \"multiple_count\": \"\", \r\n" + 
				"            \"multiple_unit_score\": \"\", \r\n" + 
				"            \"fill_blank_count\": \"\", \r\n" + 
				"            \"fill_blank_unit_score\": \"\", \r\n" + 
				"            \"short_answer_count\": \"\", \r\n" + 
				"            \"short_answer_unit_score\": \"\", \r\n" + 
				"            \"single_count\": \"\", \r\n" + 
				"            \"single_unit_score\": \"\", \r\n" + 
				"            \"true_or_false_count\": \"\", \r\n" + 
				"            \"true_or_false_unit_score\": \"\", \r\n" + 
				"            \"total_score\": 100, \r\n" + 
				"            \"answer_parsing\": 5, \r\n" + 
				"            \"passing_score\": \"60.0\", \r\n" + 
				"            \"cut_screen_count\": -1, \r\n" + 
				"            \"re_exam_rule\": 0, \r\n" + 
				"            \"re_exam_number\": 0, \r\n" + 
				"            \"score_rule\": 0\r\n" + 
				"          }\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"sort\": 3, \r\n" + 
				"          \"type\": 9, \r\n" + 
				"          \"course_id\": \""+ques_id+"\", \r\n" + 
				"          \"questionnaire_id\": \""+questionnaire_id+"\"\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"is_all\": 1, \r\n" + 
				"  \"user_ids\": \"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"supervisor_department_ids\": [ ], \r\n" + 
				"  \"operationTimes\": \"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"enroll_audit\": \"un_need\", \r\n" + 
				"  \"enroll_limit\": \"true\", \r\n" + 
				"  \"limit_count\": 1, \r\n" + 
				"  \"stage_pass\": \"false\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, edit_url(id));
	}
	
	/**   
	 * @Title: deleteStudyProject   
	 * @Description: TODO(删除学习项目)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteStudyProject(String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, delete_url(id));
	}
	
	/**   
	 * @Title: queryStudyAuditsLog   
	 * @Description: TODO(查看日志)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryStudyAuditsLog (String source_id) {
		return GetRequestTools.RequestQueryParamsByGet("page_size","10","source_id",
				source_id, "access_token",token,study_audits_log_Url);
	}
	
	/**   
	 * @Title: batchSetStudyProjectVisible   
	 * @Description: TODO(批量设置可见范围)   
	 * @param: @param is_all
	 * @param: @param user_ids
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String batchSetStudyProjectVisible(String is_all,String user_ids,String id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"is_all\": "+is_all+", \r\n" + 
				"  \"user_ids\": \""+user_ids+"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"project_ids\": \""+id+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, batch_set_visible_url);
	}
	
	/**   
	 * @Title: setVisible   
	 * @Description: TODO(设置单个学习项目的可见范围)   
	 * @param: @param id
	 * @param: @param is_all（1--全公司，2--部门，3--仅自己）
	 * @param: @param user_ids
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String  setVisible(String id,String is_all,String user_ids) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"is_all\": "+is_all+", \r\n" + 
				"  \"user_ids\": \""+user_ids+"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, set_visible_url(id));
	}
	
	/**   
	 * @Title: pushMessage   
	 * @Description: TODO(推送消息)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String pushMessage(String id) {
		return PostRequestTools.RequestBodyByPost("{\"user_ids\":\""+userId+"\",\"department_ids\":\"\","
				+ "\"group_ids\":\"\",\"post_ids\":\"\",\"access_token\":\""+token+"\"}", token, push_message_url(id));
	}
	
	/**   
	 * @Title: assignStudyProject   
	 * @Description: TODO(指派学习项目)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String assignStudyProject(String id,String plan_title,String plan_begin_time,String plan_end_time) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"plan_title\": \""+plan_title+"\", \r\n" + 
				"  \"plan_begin_time\": "+plan_begin_time+", \r\n" + 
				"  \"plan_end_time\": "+plan_end_time+", \r\n" + 
				"  \"is_notice_all\": false, \r\n" + 
				"  \"department_ids\": [ ], \r\n" + 
				"  \"user_ids\": [\r\n" + 
				"    \""+userId+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"post_ids\": [ ], \r\n" + 
				"  \"group_ids\": [ ], \r\n" + 
				"  \"supervisor_ids\": [\r\n" + 
				"    \""+userId+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, assign_url(id));
	}
	
	/**   
	 * @Title: queryResource   
	 * @Description: TODO(查看学习项目详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static  String queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, query_resource_url(id));
	}
	/**   
	 * @Title: checkResource   
	 * @Description: TODO(检查资源是否可用)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static  String checkResource(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, check_resource_url(id));
	}
	
	/**   
	 * @Title: updateStatus   
	 * @Description: TODO(更新状态)   
	 * @param: @param id
	 * @param: @param type（disabled--停用。normal--启用）
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String updateStatus(String id,String status) {
		return PostRequestTools.RequestBodyByPost("{\"status\":\""+status+"\",\"access_token\":\""+token+"\"}", token, updateStatusUrl(id));
	}
	
	/**   
	 * @Title: queryLearningProjectList   
	 * @Description: TODO(查看列表)   
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryLearningProjectList(String keyword,String classify_id) {
		return GetRequestTools.RequestQueryParamsByGet("classify_id",classify_id,"access_token",token,"keyword",keyword, "page_size","20","page_number","1", "only_see_me", "true", list_url);
	}
	
	public static String queryLearningProjectList(String status) {
		return GetRequestTools.RequestQueryParamsByGet("status",status,"access_token",token,"keyword","", "page_size","20","page_number","1","only_see_me", "true", list_url);
	}
	
	/**   
	 * @Title: addStudyProject   
	 * @Description: TODO()   
	 * @param: @param title
	 * @param: @param classify
	 * @param: @param paper_id
	 * @param: @param certificate_id
	 * @param: @param resource_id
	 * @param: @param course_id
	 * @param: @param questionnaire_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addStudyProject(String title ,String classify,String paper_id,String certificate_id,String resource_id,String course_id,String questionnaire_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"cover\": \"https://oss.coolcollege.cn/1789917624419880960.png\", \r\n" + 
				"  \"base_cover\": \"https://oss.coolcollege.cn/1789917624419880960.png\", \r\n" + 
				"  \"cover_type\": 1, \r\n" + 
				"  \"classify\": \""+classify+"\", \r\n" + 
				"  \"ding_img_url\": \"\", \r\n" + 
				"  \"is_get_score\": true, \r\n" + 
				"  \"certificate_id\": \""+certificate_id+"\", \r\n" + 
				"  \"progress\": 100, \r\n" + 
				"  \"study_score\": {\r\n" + 
				"    \"finish_score\": 5, \r\n" + 
				"    \"unfinish_score\": 0\r\n" + 
				"  }, \r\n" + 
				"  \"study_time_limit\": 0, \r\n" + 
				"  \"supervisor_ids\": [\r\n" + 
				"    \"1791273585709551616\"\r\n" + 
				"  ], \r\n" + 
				"  \"supervisor_paper\": true, \r\n" + 
				"  \"times\": 1, \r\n" + 
				"  \"stages\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stage_name\": \"阶段一\", \r\n" + 
				"      \"is_order\": false, \r\n" + 
				"      \"sort\": 1, \r\n" + 
				"      \"resources\": [\r\n" + 
				"        {\r\n" + 
				"          \"course_id\": \""+resource_id+"\", \r\n" + 
				"          \"sort\": 0, \r\n" + 
				"          \"type\": 3\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"course_id\": \""+course_id+"\", \r\n" + 
				"          \"sort\": 1, \r\n" + 
				"          \"type\": 1\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"type\": 6, \r\n" + 
				"          \"sort\": 2, \r\n" + 
				"          \"course_id\": \"\", \r\n" + 
				"          \"exam\": {\r\n" + 
				"            \"title\": \"exam\", \r\n" + 
				"            \"cheat_flag\": 0, \r\n" + 
				"            \"exam_duration\": 45, \r\n" + 
				"            \"summary\": \"this is a summary\", \r\n" + 
				"            \"mark_type\": 1, \r\n" + 
				"            \"paper_id\": \""+paper_id+"\", \r\n" + 
				"            \"pass_line\": 60, \r\n" + 
				"            \"question_banks\": [ ], \r\n" + 
				"            \"question_mode\": 1, \r\n" + 
				"            \"show_knowledge\": \"show\", \r\n" + 
				"            \"repeat_exam\": true, \r\n" + 
				"            \"multiple_count\": \"\", \r\n" + 
				"            \"multiple_unit_score\": \"\", \r\n" + 
				"            \"fill_blank_count\": \"\", \r\n" + 
				"            \"fill_blank_unit_score\": \"\", \r\n" + 
				"            \"short_answer_count\": \"\", \r\n" + 
				"            \"short_answer_unit_score\": \"\", \r\n" + 
				"            \"single_count\": \"\", \r\n" + 
				"            \"single_unit_score\": \"\", \r\n" + 
				"            \"true_or_false_count\": \"\", \r\n" + 
				"            \"true_or_false_unit_score\": \"\", \r\n" + 
				"            \"total_score\": 100, \r\n" + 
				"            \"answer_parsing\": 5, \r\n" + 
				"            \"passing_score\": \"60.0\", \r\n" + 
				"            \"cut_screen_count\": -1, \r\n" + 
				"            \"re_exam_rule\": 0, \r\n" + 
				"          \"viewRule\": 1, \r\n" +
				"            \"re_exam_number\": 0, \r\n" + 
				"            \"view_rule\": 1, \r\n" +
				"            \"score_rule\": 0\r\n" + 
				"          }\r\n" + 
				"        }, \r\n" + 
				"        {\r\n" + 
				"          \"sort\": 3, \r\n" + 
				"          \"type\": 9, \r\n" + 
				"          \"course_id\": \"\", \r\n" + 
				"          \"questionnaire_id\": \""+questionnaire_id+"\"\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"is_all\": 1, \r\n" + 
				"  \"user_ids\": \""+userId+"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"supervisor_department_ids\": [ ], \r\n" + 
				"  \"operationTimes\": \"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"enroll_audit\": \"un_need\", \r\n" + 
				"  \"enroll_limit\": \"true\", \r\n" + 
				"  \"limit_count\": 1, \r\n" + 
				"  \"stage_pass\": \"false\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_url);
	}
	
}
