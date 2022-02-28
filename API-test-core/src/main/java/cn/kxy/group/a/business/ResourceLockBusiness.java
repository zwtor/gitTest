package cn.kxy.group.a.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

public class ResourceLockBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String exam_url = ExamDataUrl.getNewExamUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String study_projects_save_url = enterprise_url + "v2/"+enterpriseId +"/study_projects/save/";
	public static String courseResource_getList_url = enterprise_url + "course/resource/getList/";
	public static String course_query_url = enterprise_url + "course/queryCourseByPage/";
	public static String paper_getList_url = exam_url + "paper/getList/";
	public static String evaluation_tools_list_url = enterprise_url + "v2/"+enterpriseId+"/evaluation/tools/";
	public static String query_questionnaires_url = enterprise_url + "v2/"+enterpriseId+"/questionnaires/";
	public static String query_lock_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId+"/study_projects/"+project_id+"/query/";
	}
	public static String study_project_delete_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_projects/"+project_id+"/delete/";
	}
	public static String study_project_edit_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_projects/"+project_id+"/edit/";
	}
	public static String share_ding_url = enterprise_url + "v2/"+enterpriseId +"/multi_terminal_login/sync_standard/";
	public static String share_wx_url = enterprise_url + "v2/wechat_applet/create_wxacode/";
	
	
	/**   
	 * @Title: CourseResourceGetList  
	 * @Description: TODO  查询课件列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CourseResourceGetList(String resourceClassify,String contentType){	
		return HttpRequest.get(courseResource_getList_url).query("access_token", token).query("resourceClassify",resourceClassify)
				.query("contentType",contentType).send().body();
	}
	
	
	/**   
	 * @Title: CourseQuery  
	 * @Description: TODO  查询课程列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CourseQuery(String image_text){	
		return HttpRequest.get(course_query_url).query("access_token", token).query("queryType","0").query("scope","0")
				.query("filterQuota","false").query("courseType","course").query("image_text",image_text)
				.send().body();
	}
	
	
	/**   
	 * @Title: PaperGetList  
	 * @Description: TODO  查询试卷列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PaperGetList(){	
		return HttpRequest.get(paper_getList_url).query("access_token", token).query("sortOrder","desc").query("sortName","createTime")
				.send().body();
	}	
	
	/**   
	 * @Title: StudyProjectSaveBaseInfo  
	 * @Description: TODO 创建学习项目第一步
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyProjectSaveBaseInfo(String title,String class_id,String cover,String base_cover) {		
		return HttpRequest.post(study_projects_save_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"base_info\",\r\n" + 
				"      \"base_info\": {\r\n" + 
				"            \"title\": \""+title+"\",\r\n" + 
				"            \"lecturer_id\": 0,\r\n" + 
				"            \"summary\": \"\",\r\n" + 
				"  \"style_type\": \"normal\","+
				"            \"classify\": \""+class_id+"\",\r\n" + 
				"            \"cover\": \""+cover+"\",\r\n" + 
				"            \"base_cover\": \""+base_cover+"\",\r\n" + 
				"            \"cover_type\": 1,\r\n" + 
				"            \"ding_img_url\": \"\"\r\n" + 
				"      },\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: StudyProjectSaveStageContent  
	 * @Description: TODO 创建学习项目第二步
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyProjectSaveStageContent(String project_id,String resource_id,Boolean resource_lockTemp,
			Boolean resource_lock,String course_id,String imageText_id,String paper_id,Long begin_time,Long end_time,
			Long clock_time,Long End_time,String questionnaires_id,String productId) {
		return HttpRequest.post(study_projects_save_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"stage_content\",\r\n" + 
				"      \"id\": \""+project_id+"\",\r\n" + 
				"      \"stage_content\": {\r\n" + 
				"            \"stage_pass\": \"false\",\r\n" + 
				"            \"stages\": [\r\n" + 
				"                  {\r\n" + 
				"                        \"content\": \"\",\r\n" + 
				"                        \"stage_name\": \"阶段1\",\r\n" + 
				"                        \"is_order\": false,\r\n" + 
				"                        \"sort\": 1,\r\n" + 
				"                        \"resources\": [\r\n" + 
				"                              {\r\n" + 
				"                                    \"course_id\": \""+resource_id+"\",\r\n" + 
				"                                    \"sort\": 0,\r\n" + 
				"                                    \"type\": 3,\r\n" + 
				"                                    \"resource_lock\": \""+resource_lockTemp+"\"\r\n" + 
				"                              },\r\n" + 
				"                              {\r\n" + 
				"                                    \"course_id\": \""+course_id+"\",\r\n" + 
				"                                    \"sort\": 1,\r\n" + 
				"                                    \"type\": 1,\r\n" + 
				"                                    \"resource_lock\": \""+resource_lock+"\"\r\n" + 
				"                              },\r\n" + 
				"                              {\r\n" + 
				"                                    \"course_id\": \""+imageText_id+"\",\r\n" + 
				"                                    \"sort\": 2,\r\n" + 
				"                                    \"type\": 1,\r\n" + 
				"                                    \"resource_lock\": \""+resource_lock+"\"\r\n" + 
				"                              },\r\n" + 
				"                              {\r\n" + 
				"                                    \"type\": 6,\r\n" + 
				"                                    \"sort\": 3,\r\n" + 
				"                                    \"course_id\": \"\",\r\n" + 
				"                                    \"exam\": {\r\n" + 
				"                                          \"title\": \"考试\",\r\n" + 
				"                                          \"cheat_flag\": 0,\r\n" + 
				"                                          \"exam_duration\": 11,\r\n" + 
				"                                          \"face_recognition\": \"off\",\r\n" + 
				"                                          \"summary\": \"\",\r\n" + 
				"                                          \"mark_type\": 1,\r\n" + 
				"                                          \"paper_id\": \""+paper_id+"\",\r\n" + 
				"                                          \"pass_line\": 40,\r\n" + 
				"                                          \"question_banks\": [],\r\n" + 
				"                                          \"question_mode\": 1,\r\n" + 
				"                                          \"show_knowledge\": \"show\",\r\n" + 
				"                                          \"repeat_exam\": true,\r\n" + 
				"                                          \"multiple_count\": \"\",\r\n" + 
				"                                          \"multiple_unit_score\": \"\",\r\n" + 
				"                                          \"fill_blank_count\": \"\",\r\n" + 
				"                                          \"fill_blank_unit_score\": \"\",\r\n" + 
				"                                          \"fill_blank_type\": 1,\r\n" + 
				"                                          \"short_answer_count\": \"\",\r\n" + 
				"                                          \"short_answer_unit_score\": \"\",\r\n" + 
				"                                          \"short_answer_type\": 1,\r\n" + 
				"                                          \"single_count\": \"\",\r\n" + 
				"                                          \"single_unit_score\": \"\",\r\n" + 
				"                                          \"true_or_false_count\": \"\",\r\n" + 
				"                                          \"true_or_false_unit_score\": \"\",\r\n" + 
				"                                          \"total_score\": 100,\r\n" + 
				"                                          \"answer_parsing\": 5,\r\n" + 
				"                                          \"passing_score\": \"40.0\",\r\n" + 
				"                                          \"cut_screen_count\": -1,\r\n" + 
				"                                          \"re_exam_rule\": 0,\r\n" + 
				"                                          \"re_exam_number\": 0,\r\n" + 
				"                                          \"score_rule\": 0,\r\n" + 
				"                                          \"view_rule\": 1,\r\n" + 
				"                                          \"proportional\": []\r\n" + 
				"                                    },\r\n" + 
				"                                    \"resource_lock\": \""+resource_lock+"\"\r\n" + 
				"                              },\r\n" + 
				"                              {\r\n" + 
				"                                    \"sort\": 4,\r\n" + 
				"                                    \"type\": 7,\r\n" + 
				"                                    \"course_id\": \"\",\r\n" + 
				"                                    \"latoja\": {\r\n" + 
				"                                          \"address\": \"培训地址\",\r\n" + 
				"                                          \"title\": \"线下培训\",\r\n" + 
				"                                          \"introduction\": \"<p>1111</p>\",\r\n" + 
				"                                          \"begin_time\": "+begin_time+",\r\n" + 
				"                                          \"end_time\": "+end_time+",\r\n" + 
				"                                          \"clock_times\": [\r\n" + 
				"                                                {\r\n" + 
				"                                                      \"clock_time\": "+begin_time+",\r\n" + 
				"                                                      \"end_time\": "+End_time+",\r\n" + 
				"                                                      \"sort\": 0,\r\n" + 
				"                                                      \"id\": \"\"\r\n" + 
				"                                                }\r\n" + 
				"                                          ],\r\n" + 
				"                                          \"directors\": [\r\n" + 
				"                                                \""+user_id+"\"\r\n" + 
				"                                          ],\r\n" + 
				"                                          \"qrcode_type\": \"statics\",\r\n" + 
				"                                          \"assessment\": []\r\n" + 
				"                                    },\r\n" + 
				"                                    \"resource_lock\": \""+resource_lock+"\"\r\n" + 
				"                              },\r\n" + 
				"                              {\r\n" + 
				"                                    \"sort\": 5,\r\n" + 
				"                                    \"type\": 9,\r\n" + 
				"                                    \"course_id\": \"\",\r\n" + 
				"                                    \"questionnaire_id\": \""+questionnaires_id+"\",\r\n" + 
				"                                    \"resource_lock\": \""+resource_lock+"\",\r\n" + 
				"                                    \"title\": \"调研\"\r\n" + 
				"                              },\r\n" + 
				"                              {\r\n" + 
				"                                    \"type\": 10,\r\n" + 
				"                                    \"courseSort\": 6,\r\n" + 
				"                                    \"sort\": 6,\r\n" + 
				"                                    \"flag\": 4,\r\n" + 
				"                                    \"course_id\": \"\",\r\n" + 
				"                                    \"title\": \"实操作业\",\r\n" + 
				"                                    \"content\": \"111\",\r\n" + 
				"                                    \"resources\": [],\r\n" + 
				"                                    \"scoreSetting\": {\r\n" + 
				"                                          \"scoreSwitch\": \"off\",\r\n" + 
				"                                          \"score\": 100\r\n" + 
				"                                    },\r\n" + 
				"                                    \"workflowJson\": {\r\n" + 
				"                                          \"type\": \"or\",\r\n" + 
				"                                          \"userIds\": [\r\n" + 
				"                                                \""+user_id+"\"\r\n" + 
				"                                          ]\r\n" + 
				"                                    },\r\n" + 
				"                                    \"job_type\": \"common\",\r\n" + 
				"                                    \"resource_lock\": \""+resource_lock+"\"\r\n" + 
				"                              }\r\n" + 
				"                              {\r\n" + 
				"                                    \"sort\": 1,\r\n" + 
				"                                    \"flag\": 6,\r\n" + 
				"                                    \"type\": 11,\r\n" + 
				"                                    \"evaluation\": {\r\n" + 
				"                                          \"id\": \"\",\r\n" + 
				"                                          \"title\": \"岗位测评\",\r\n" + 
				"                                          \"product_id\": \""+productId+"\",\r\n" + 
				"                                          \"model_id\": 0\r\n" + 
				"                                    },\r\n" + 
				"                                    \"resource_lock\": \""+resource_lock+"\"\r\n" + 
				"                              }\r\n" + 
				"                        ]\r\n" + 
				"                  }\r\n" + 
				"            ]\r\n" + 
				"      },\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: StudyProjectSaveSettings  
	 * @Description: TODO 创建学习项目第三步
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyProjectSaveSettings(String project_id) {		
		return HttpRequest.post(study_projects_save_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"settings\",\r\n" + 
				"      \"id\": \""+project_id+"\",\r\n" + 
				"      \"settings\": {\r\n" + 
				"            \"study_time_limit\": 0,\r\n" + 
				"            \"face_recognition\": \"off\",\r\n" + 
				"            \"progress\": 100,\r\n" + 
				"            \"times\": \"\",\r\n" + 
				"            \"operation_times\": \"\",\r\n" + 
				"            \"finish_evaluation\": true,\r\n" + 
				"            \"certificate_id\": \"\",\r\n" + 
				"            \"is_get_score\": true,\r\n" + 
				"            \"study_score\": {\r\n" + 
				"                  \"finish_score\": 0,\r\n" + 
				"                  \"unfinish_score\": 0\r\n" + 
				"            },\r\n" + 
				"            \"is_all\": 1,\r\n" + 
				"            \"user_ids\": \"\",\r\n" + 
				"            \"department_ids\": \"\",\r\n" + 
				"            \"group_ids\": \"\",\r\n" + 
				"            \"post_ids\": \"\",\r\n" + 
				"            \"supervisor_ids\": [\r\n" + 
				"                  \""+user_id+"\"\r\n" + 
				"            ],\r\n" + 
				"            \"supervisor_paper\": true,\r\n" + 
				"            \"authority_range\": false,\r\n" + 
				"            \"supervisor_department_ids\": [],\r\n" + 
				"            \"enroll_audit\": \"un_need\",\r\n" + 
				"            \"enroll_limit\": \"false\",\r\n" + 
				"            \"limit_count\": 1,\r\n" + 
				"            \"open_evaluation_result\": false,\r\n" + 
				"            \"is_free\": true,\r\n" + 
				"            \"official_price\": \"\",\r\n" + 
				"            \"preferential_price\": \"\",\r\n" + 
				"            \"open_learning_group\": \"false\"\r\n" + 
				"      },\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: EvaluationToolsList  
	 * @Description: TODO  测训工具列表-按名称查询
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationToolsList(String keyword) {		
	    return HttpRequest.get(evaluation_tools_list_url).query("access_token", token).query("keyword",keyword).send().body();
	}
	
	
	/**   
	 * @Title: QueryQuestionnaires  
	 * @Description: TODO  查询问卷列表
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryQuestionnaires() {		
	    return HttpRequest.get(query_questionnaires_url).query("access_token", token).query("status","release")
	    		.query("create_status","all")
	    		.send().body();
	}
	
	
	/**   
	 * @Title: QueryLock  
	 * @Description: TODO  学习项目指派页资源是否锁定判断
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryLock(String project_id) {		
	    return HttpRequest.get(query_lock_url(project_id)).query("access_token", token)
	    		.send().body();
	}
	
	/**   
	 * @Title: DeleteProject  
	 * @Description: TODO 删除学习项目
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteProject(String project_id) {		
		return HttpRequest.post(study_project_delete_url(project_id)).query("access_token", token).send().body();
	}
	
	
	/**   
	 * @Title: StudyProjectEdit  
	 * @Description: TODO 编辑学习项目资源锁定
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyProjectEdit(String project_id,String resource_id,Boolean resource_lockTemp,
			Boolean resource_lock,String course_id,String imageText_id,String paper_id,Long begin_time,Long end_time,
			Long clock_time,Long End_time,String questionnaires_id,String productId) {
		return HttpRequest.post(study_project_edit_url(project_id)).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"stage_content\",\r\n" + 
				"      \"id\": \""+project_id+"\",\r\n" + 
				"      \"stage_content\": {\r\n" + 
				"            \"stage_pass\": \"false\",\r\n" + 
				"            \"stages\": [\r\n" + 
				"                  {\r\n" + 
				"                        \"content\": \"\",\r\n" + 
				"                        \"stage_name\": \"阶段1\",\r\n" + 
				"                        \"is_order\": false,\r\n" + 
				"                        \"sort\": 1,\r\n" + 
				"                        \"resources\": [\r\n" + 
				"                              {\r\n" + 
				"                                    \"course_id\": \""+resource_id+"\",\r\n" + 
				"                                    \"sort\": 0,\r\n" + 
				"                                    \"type\": 3,\r\n" + 
				"                                    \"resource_lock\": \""+resource_lockTemp+"\"\r\n" + 
				"                              },\r\n" + 
				"                              {\r\n" + 
				"                                    \"course_id\": \""+course_id+"\",\r\n" + 
				"                                    \"sort\": 1,\r\n" + 
				"                                    \"type\": 1,\r\n" + 
				"                                    \"resource_lock\": \""+resource_lock+"\"\r\n" + 
				"                              },\r\n" + 
				"                              {\r\n" + 
				"                                    \"course_id\": \""+imageText_id+"\",\r\n" + 
				"                                    \"sort\": 2,\r\n" + 
				"                                    \"type\": 1,\r\n" + 
				"                                    \"resource_lock\": \""+resource_lock+"\"\r\n" + 
				"                              },\r\n" + 
				"                              {\r\n" + 
				"                                    \"type\": 6,\r\n" + 
				"                                    \"sort\": 3,\r\n" + 
				"                                    \"course_id\": \"\",\r\n" + 
				"                                    \"exam\": {\r\n" + 
				"                                          \"title\": \"考试\",\r\n" + 
				"                                          \"cheat_flag\": 0,\r\n" + 
				"                                          \"exam_duration\": 11,\r\n" + 
				"                                          \"face_recognition\": \"off\",\r\n" + 
				"                                          \"summary\": \"\",\r\n" + 
				"                                          \"mark_type\": 1,\r\n" + 
				"                                          \"paper_id\": \""+paper_id+"\",\r\n" + 
				"                                          \"pass_line\": 40,\r\n" + 
				"                                          \"question_banks\": [],\r\n" + 
				"                                          \"question_mode\": 1,\r\n" + 
				"                                          \"show_knowledge\": \"show\",\r\n" + 
				"                                          \"repeat_exam\": true,\r\n" + 
				"                                          \"multiple_count\": \"\",\r\n" + 
				"                                          \"multiple_unit_score\": \"\",\r\n" + 
				"                                          \"fill_blank_count\": \"\",\r\n" + 
				"                                          \"fill_blank_unit_score\": \"\",\r\n" + 
				"                                          \"fill_blank_type\": 1,\r\n" + 
				"                                          \"short_answer_count\": \"\",\r\n" + 
				"                                          \"short_answer_unit_score\": \"\",\r\n" + 
				"                                          \"short_answer_type\": 1,\r\n" + 
				"                                          \"single_count\": \"\",\r\n" + 
				"                                          \"single_unit_score\": \"\",\r\n" + 
				"                                          \"true_or_false_count\": \"\",\r\n" + 
				"                                          \"true_or_false_unit_score\": \"\",\r\n" + 
				"                                          \"total_score\": 100,\r\n" + 
				"                                          \"answer_parsing\": 5,\r\n" + 
				"                                          \"passing_score\": \"40.0\",\r\n" + 
				"                                          \"cut_screen_count\": -1,\r\n" + 
				"                                          \"re_exam_rule\": 0,\r\n" + 
				"                                          \"re_exam_number\": 0,\r\n" + 
				"                                          \"score_rule\": 0,\r\n" + 
				"                                          \"view_rule\": 1,\r\n" + 
				"                                          \"proportional\": []\r\n" + 
				"                                    },\r\n" + 
				"                                    \"resource_lock\": \""+resource_lock+"\"\r\n" + 
				"                              },\r\n" + 
				"                              {\r\n" + 
				"                                    \"sort\": 4,\r\n" + 
				"                                    \"type\": 7,\r\n" + 
				"                                    \"course_id\": \"\",\r\n" + 
				"                                    \"latoja\": {\r\n" + 
				"                                          \"address\": \"培训地址\",\r\n" + 
				"                                          \"title\": \"培训\",\r\n" + 
				"                                          \"introduction\": \"<p>1111</p>\",\r\n" + 
				"                                          \"begin_time\": "+begin_time+",\r\n" + 
				"                                          \"end_time\": "+end_time+",\r\n" + 
				"                                          \"clock_times\": [\r\n" + 
				"                                                {\r\n" + 
				"                                                      \"clock_time\": "+begin_time+",\r\n" + 
				"                                                      \"end_time\": "+End_time+",\r\n" + 
				"                                                      \"sort\": 0,\r\n" + 
				"                                                      \"id\": \"\"\r\n" + 
				"                                                }\r\n" + 
				"                                          ],\r\n" + 
				"                                          \"directors\": [\r\n" + 
				"                                                \""+user_id+"\"\r\n" + 
				"                                          ],\r\n" + 
				"                                          \"qrcode_type\": \"statics\",\r\n" + 
				"                                          \"assessment\": []\r\n" + 
				"                                    },\r\n" + 
				"                                    \"resource_lock\": \""+resource_lock+"\"\r\n" + 
				"                              },\r\n" + 
				"                              {\r\n" + 
				"                                    \"sort\": 5,\r\n" + 
				"                                    \"type\": 9,\r\n" + 
				"                                    \"course_id\": \"\",\r\n" + 
				"                                    \"questionnaire_id\": \""+questionnaires_id+"\",\r\n" + 
				"                                    \"resource_lock\": \""+resource_lock+"\",\r\n" + 
				"                                    \"title\": \"调研\"\r\n" + 
				"                              },\r\n" + 
				"                              {\r\n" + 
				"                                    \"type\": 10,\r\n" + 
				"                                    \"courseSort\": 6,\r\n" + 
				"                                    \"sort\": 6,\r\n" + 
				"                                    \"flag\": 4,\r\n" + 
				"                                    \"course_id\": \"\",\r\n" + 
				"                                    \"title\": \"实操作业\",\r\n" + 
				"                                    \"content\": \"111\",\r\n" + 
				"                                    \"resources\": [],\r\n" + 
				"                                    \"scoreSetting\": {\r\n" + 
				"                                          \"scoreSwitch\": \"off\",\r\n" + 
				"                                          \"score\": 100\r\n" + 
				"                                    },\r\n" + 
				"                                    \"workflowJson\": {\r\n" + 
				"                                          \"type\": \"or\",\r\n" + 
				"                                          \"userIds\": [\r\n" + 
				"                                                \""+user_id+"\"\r\n" + 
				"                                          ]\r\n" + 
				"                                    },\r\n" + 
				"                                    \"job_type\": \"common\",\r\n" + 
				"                                    \"resource_lock\": \""+resource_lock+"\"\r\n" + 
				"                              }\r\n" + 
				"                              {\r\n" + 
				"                                    \"sort\": 1,\r\n" + 
				"                                    \"flag\": 6,\r\n" + 
				"                                    \"type\": 11,\r\n" + 
				"                                    \"evaluation\": {\r\n" + 
				"                                          \"id\": \"\",\r\n" + 
				"                                          \"title\": \"岗位测评\",\r\n" + 
				"                                          \"product_id\": \""+productId+"\",\r\n" + 
				"                                          \"model_id\": 0\r\n" + 
				"                                    },\r\n" + 
				"                                    \"resource_lock\": \""+resource_lock+"\"\r\n" + 
				"                              }\r\n" + 
				"                        ]\r\n" + 
				"                  }\r\n" + 
				"            ]\r\n" + 
				"      },\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: ShareDing  
	 * @Description: TODO 学习项目分享-钉钉
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ShareDing() {		
		return HttpRequest.get(share_ding_url).query("access_token", token).send().body();
	}
	
	
	/**   
	 * @Title: ShareWx  
	 * @Description: TODO 学习项目分享-微信
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ShareWx(String flag,String scene,String enterpriseId) {	
		return HttpRequest.post(share_wx_url).query("access_token", token).data("{\r\n" + 
				"      \"flag\": \""+flag+"\",\r\n" + 
				"      \"scene\": \""+scene+"\",\r\n" + 
				"      \"enterprise_id\": \""+enterpriseId+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	
	
}
