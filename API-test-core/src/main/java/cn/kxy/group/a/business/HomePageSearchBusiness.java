package cn.kxy.group.a.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

public class HomePageSearchBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String add_lecturers_url = enterprise_url + "v2/"+enterpriseId+"/lecturers/add/";
	public static String lecturer_level_add_url = enterprise_url + "v2/"+enterpriseId+"/lecturer_level/";
	public static String studyProject_add_url = enterprise_url + "v2/"+enterpriseId+"/study_projects/save/";
	public static String study_add_url = enterprise_url + "plan/study/add/";
	public static String search_course_url = enterprise_url + "v2/"+enterpriseId+"/search/type/course/";
	public static String search_task_url = enterprise_url + "v2/"+enterpriseId+"/search/type/task/";
	public static String search_lecturer_url = enterprise_url + "v2/"+enterpriseId+"/search/type/lecturer/";
	public static String delete_study_url = enterprise_url + "plan/study/delete/";
	public static String study_project_delete_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_projects/"+project_id+"/delete/";
	}
	public static String update_status_url(String lecturer_id) {
		return enterprise_url + "v2/"+enterpriseId+"/lecturers/"+lecturer_id+"/update_status/";
	}
	public static String delete_lecturer_url(String lecturer_id) {
		return enterprise_url + "v2/"+enterpriseId+"/lecturers/"+lecturer_id+"/delete/";
	}
	public static String delete_lecturer_level_url = enterprise_url + "v2/"+enterpriseId+"/lecturer_level/batch_delete/";
	public static String search_guessUList_url = enterprise_url + "v2/"+enterpriseId+"/search/guessUList/";
	public static String search_history_url = enterprise_url + "v2/"+enterpriseId+"/search/history/";


	
	/**   
	 * @Title: StudyProjectSaveBaseInfo  
	 * @Description: TODO 创建学习项目第一步
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyProjectSaveBaseInfo(String title,String lecturer_id,String class_id,String style_type,String cover,
			String base_cover) {		
		return HttpRequest.post(studyProject_add_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"base_info\",\r\n" + 
				"      \"base_info\": {\r\n" + 
				"            \"title\": \""+title+"\",\r\n" + 
				"            \"lecturer_id\": \""+lecturer_id+"\",\r\n" + 
				"            \"summary\": \"\",\r\n" + 
				"            \"classify\": \""+class_id+"\",\r\n" + 
				"            \"style_type\": \""+style_type+"\",\r\n" + 
				"            \"cover\": \""+cover+"\",\r\n" + 
				"            \"base_cover\": \""+base_cover+"\",\r\n" + 
				"            \"cover_type\": 1,\r\n" + 
				"            \"ding_img_url\": \"\"\r\n" + 
				"      },\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: StudyProjectSaveStageContent  
	 * @Description: TODO 创建学习项目第二步
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyProjectSaveStageContent(String project_id,String title,String cover,String begin_time,String end_time,
			String course_duration,String lecturer_id) {
		return HttpRequest.post(studyProject_add_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"stage_content\",\r\n" + 
				"      \"id\": \""+project_id+"\",\r\n" + 
				"      \"stage_content\": {\r\n" + 
				"            \"stages\": [\r\n" + 
				"                  {\r\n" + 
				"                        \"id\": \"\",\r\n" + 
				"                        \"content\": \"\",\r\n" + 
				"                        \"stage_name\": \"阶段1\",\r\n" + 
				"                        \"is_order\": false,\r\n" + 
				"                        \"sort\": 1,\r\n" + 
				"                        \"progress\": 100,\r\n" + 
				"                        \"exam_times\": \"\",\r\n" + 
				"                        \"operation_times\": \"\",\r\n" + 
				"                        \"finish_evaluation\": \"false\",\r\n" + 
				"                        \"is_get_score\": \"\",\r\n" + 
				"                        \"certificate_id\": \"\",\r\n" + 
				"                        \"resources\": [\r\n" + 
				"                              {\r\n" + 
				"                                    \"sort\": 0,\r\n" + 
				"                                    \"type\": 7,\r\n" + 
				"                                    \"course_id\": \"\",\r\n" + 
				"                                    \"offline_course\": {\r\n" + 
				"                                          \"title\": \""+title+"\",\r\n" + 
				"                                          \"cover\": \""+cover+"\",\r\n" + 
				"                                          \"cover_type\": 1,\r\n" + 
				"                                          \"department_id\": \"\",\r\n" + 
				"                                          \"resource_classify\": \"\",\r\n" + 
				"                                          \"trainees\": \"\",\r\n" + 
				"                                          \"begin_time\": \""+begin_time+"\",\r\n" + 
				"                                          \"end_time\": \""+end_time+"\",\r\n" + 
				"                                          \"attachment\": [],\r\n" + 
				"                                          \"offline_course_setting_list\": [\r\n" + 
				"                                                {\r\n" + 
				"                                                      \"name\": \"第1课\",\r\n" + 
				"                                                      \"sort\": 1,\r\n" + 
				"                                                      \"begin_time\": \""+begin_time+"\",\r\n" + 
				"                                                      \"end_time\": \""+end_time+"\"\r\n" + 
				"                                                }\r\n" + 
				"                                          ],\r\n" + 
				"                                          \"course_duration\": \""+course_duration+"\",\r\n" + 
				"                                          \"directors\": [\r\n" + 
				"                                                \""+user_id+"\"\r\n" + 
				"                                          ],\r\n" + 
				"                                          \"lecturer_id\": \""+lecturer_id+"\",\r\n" + 
				"                                          \"is_lecturer_confirm\": false,\r\n" + 
				"                                          \"is_lecturer_subsidy\": false,\r\n" + 
				"                                          \"address\": \"\",\r\n" + 
				"                                          \"introduction\": \"\",\r\n" + 
				"                                          \"budget\": 200,\r\n" + 
				"                                          \"offline_course_sign_config_list\": [\r\n" + 
				"                                                {\r\n" + 
				"                                                      \"name\": \"第1课\",\r\n" + 
				"                                                      \"qrcode_type\": \"statics\",\r\n" + 
				"                                                      \"sign_in_begin_time\": \""+begin_time+"\",\r\n" + 
				"                                                      \"sign_in_end_time\": \""+end_time+"\",\r\n" + 
				"                                                      \"sign_out_begin_time\": \""+begin_time+"\",\r\n" + 
				"                                                      \"sign_out_end_time\": \""+end_time+"\",\r\n" + 
				"                                                      \"sort\": 1\r\n" + 
				"                                                }\r\n" + 
				"                                          ],\r\n" + 
				"                                          \"is_open_evaluate\": true,\r\n" + 
				"                                          \"is_open_offline_assessment\": true,\r\n" + 
				"                                          \"is_open_remind\": false,\r\n" + 
				"                                          \"is_open_schedule\": false,\r\n" + 
				"                                          \"is_open_upload\": false,\r\n" + 
				"                                          \"remind_type\": \"offline_course\",\r\n" + 
				"                                          \"advance_minute\": 0,\r\n" + 
				"                                          \"is_lecturer_remind\": false,\r\n" + 
				"                                          \"is_student_remind\": false,\r\n" + 
				"                                          \"is_director_remind\": false,\r\n" + 
				"                                          \"is_lecturer_student_evaluate\": true,\r\n" + 
				"                                          \"is_student_lecturer_evaluate\": true,\r\n" + 
				"                                          \"is_student_course_evaluate\": true,\r\n" + 
				"                                          \"lecturer_student_evaluate_content\": {\r\n" + 
				"                                                \"evaluate_type\": \"score\",\r\n" + 
				"                                                \"questionnaire_id\": \"\",\r\n" + 
				"                                                \"questionnaire_name\": \"\",\r\n" + 
				"                                                \"total_score\": 100,\r\n" + 
				"                                                \"passing_score\": 60\r\n" + 
				"                                          },\r\n" + 
				"                                          \"student_lecturer_evaluate_content\": {\r\n" + 
				"                                                \"evaluate_type\": \"star\",\r\n" + 
				"                                                \"questionnaire_id\": \"\",\r\n" + 
				"                                                \"questionnaire_name\": \"\",\r\n" + 
				"                                                \"total_score\": \"\",\r\n" + 
				"                                                \"passing_score\": \"\"\r\n" + 
				"                                          },\r\n" + 
				"                                          \"student_course_evaluate_content\": {\r\n" + 
				"                                                \"evaluate_type\": \"star\",\r\n" + 
				"                                                \"questionnaire_id\": \"\",\r\n" + 
				"                                                \"questionnaire_name\": \"\",\r\n" + 
				"                                                \"total_score\": \"\",\r\n" + 
				"                                                \"passing_score\": \"\"\r\n" + 
				"                                          },\r\n" + 
				"                                          \"approvers\": [\r\n" + 
				"                                                \""+user_id+"\"\r\n" + 
				"                                          ],\r\n" + 
				"                                          \"assessment_total_score\": 100,\r\n" + 
				"                                          \"assessment_pass_score\": 70\r\n" + 
				"                                    },\r\n" + 
				"                                    \"resource_lock\": false\r\n" + 
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
		return HttpRequest.post(studyProject_add_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"settings\",\r\n" + 
				"      \"id\": \""+project_id+"\",\r\n" + 
				"      \"settings\": {\r\n" + 
				"            \"study_time_limit\": 0,\r\n" + 
				"            \"face_recognition\": \"off\",\r\n" + 
				"            \"progress\": 100,\r\n" + 
				"            \"times\": \"\",\r\n" + 
				"            \"operation_times\": \"\",\r\n" + 
				"            \"finish_evaluation\": false,\r\n" + 
				"            \"certificate_id\": \"\",\r\n" + 
				"            \"is_get_score\": true,\r\n" + 
				"            \"study_score\": {\r\n" + 
				"                  \"finish_score\": 0,\r\n" + 
				"                  \"unfinish_score\": 0\r\n" + 
				"            },\r\n" + 
				"            \"is_all\": 3,\r\n" + 
				"            \"user_ids\": \"\",\r\n" + 
				"            \"department_ids\": \"\",\r\n" + 
				"            \"group_ids\": \"\",\r\n" + 
				"            \"post_ids\": \"\",\r\n" + 
				"            \"supervisor_ids\": [],\r\n" + 
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
				"            \"open_learning_group\": \"false\",\r\n" + 
				"            \"exam_lock\": \"done\",\r\n" + 
				"            \"sync_progress\": \"false\",\r\n" + 
				"            \"stage_pass\": \"false\"\r\n" + 
				"      },\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: StudyPlanAdd  
	 * @Description: TODO  创建线下课的学习任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyPlanAdd(String title,String beginTime,String endTime,String cover,String begin_time,String end_time,
			String course_duration,String lecturer_id,String evaluate_type,String questionnaire_id,
			String questionnaire_name) {	
		return HttpRequest.post(study_add_url).query("access_token", token).data("{\r\n" + 
				"      \"title\": \""+title+"\",\r\n" + 
				"      \"beginTime\": \""+beginTime+"\",\r\n" + 
				"      \"endTime\": \""+endTime+"\",\r\n" + 
				"      \"projectId\": \"\",\r\n" + 
				"      \"summary\": \"\",\r\n" + 
				"      \"sync_progress\": \"false\",\r\n" + 
				"      \"classify_id\": \"\",\r\n" + 
				"      \"dingImgUrl\": \"\",\r\n" + 
				"      \"departmentIds\": \"\",\r\n" + 
				"      \"supervisor_department_ids\": [],\r\n" + 
				"      \"groupIds\": \"\",\r\n" + 
				"      \"isGetScore\": true,\r\n" + 
				"      \"isNoticeAll\": 0,\r\n" + 
				"      \"planCertificateId\": \"\",\r\n" + 
				"      \"postIds\": \"\",\r\n" + 
				"      \"progress\": 100,\r\n" + 
				"      \"studyScore\": {\r\n" + 
				"            \"finishScore\": 0,\r\n" + 
				"            \"unFinishScore\": 0\r\n" + 
				"      },\r\n" + 
				"      \"scheduleSetting\": \"free_mode\",\r\n" + 
				"      \"studyTimeLimit\": \"30\",\r\n" + 
				"      \"faceRecognition\": \"off\",\r\n" + 
				"      \"supervisorId\": \""+user_id+"\",\r\n" + 
				"      \"supervisorPaper\": true,\r\n" + 
				"      \"authorityRange\": false,\r\n" + 
				"      \"times\": \"\",\r\n" + 
				"      \"operationTimes\": \"\",\r\n" + 
				"      \"userIds\": \""+user_id+"\",\r\n" + 
				"      \"dingGroupIds\": \"\",\r\n" + 
				"      \"stageJson\": [\r\n" + 
				"            {\r\n" + 
				"                  \"content\": \"\",\r\n" + 
				"                  \"stageName\": \"阶段1\",\r\n" + 
				"                  \"isOrder\": false,\r\n" + 
				"                  \"stageSort\": 1,\r\n" + 
				"                  \"stageId\": \"\",\r\n" + 
				"                  \"startTime\": null,\r\n" + 
				"                  \"endTime\": null,\r\n" + 
				"                  \"course\": [\r\n" + 
				"                        {\r\n" + 
				"                              \"courseSort\": 0,\r\n" + 
				"                              \"flag\": 3,\r\n" + 
				"                              \"id\": \"\",\r\n" + 
				"                              \"type\": \"xx\",\r\n" + 
				"                              \"mappingId\": \"\",\r\n" + 
				"                              \"title\": \""+title+"\",\r\n" + 
				"                              \"resource_classify\": \"\",\r\n" + 
				"                              \"cover\": \""+cover+"\",\r\n" + 
				"                              \"cover_type\": 1,\r\n" + 
				"                              \"department_id\": \"\",\r\n" + 
				"                              \"trainees\": \"\",\r\n" + 
				"                              \"begin_time\": \""+begin_time+"\",\r\n" + 
				"                              \"end_time\": \""+end_time+"\",\r\n" + 
				"                              \"attachment\": [],\r\n" + 
				"                              \"offline_course_setting_list\": [\r\n" + 
				"                                    {\r\n" + 
				"                                          \"name\": \"第1课\",\r\n" + 
				"                                          \"sort\": 1,\r\n" + 
				"                                          \"begin_time\": \""+begin_time+"\",\r\n" + 
				"                                          \"end_time\": \""+end_time+"\"\r\n" + 
				"                                    }\r\n" + 
				"                              ],\r\n" + 
				"                              \"course_duration\": \""+course_duration+"\",\r\n" + 
				"                              \"directors\": [\r\n" + 
				"                                    \""+user_id+"\"\r\n" + 
				"                              ],\r\n" + 
				"                              \"lecturer_id\": \""+lecturer_id+"\",\r\n" + 
				"                              \"is_lecturer_confirm\": true,\r\n" + 
				"                              \"is_lecturer_subsidy\": false,\r\n" + 
				"                              \"address\": \"address test\",\r\n" + 
				"                              \"introduction\": \"introdution test\",\r\n" + 
				"                              \"budget\": 1000,\r\n" + 
				"                              \"offline_course_sign_config_list\": [\r\n" + 
				"                                    {\r\n" + 
				"                                          \"name\": \"第1课\",\r\n" + 
				"                                          \"qrcode_type\": \"statics\",\r\n" + 
				"                                          \"sign_in_begin_time\": \""+beginTime+"\",\r\n" + 
				"                                          \"sign_in_end_time\": \""+end_time+"\",\r\n" + 
				"                                          \"sign_out_begin_time\": \""+beginTime+"\",\r\n" + 
				"                                          \"sign_out_end_time\": \""+end_time+"\",\r\n" + 
				"                                          \"sort\": 1\r\n" + 
				"                                    }\r\n" + 
				"                              ],\r\n" + 
				"                              \"is_open_evaluate\": true,\r\n" + 
				"                              \"is_open_offline_assessment\": true,\r\n" + 
				"                              \"is_open_remind\": false,\r\n" + 
				"                              \"is_open_schedule\": false,\r\n" + 
				"                              \"is_open_upload\": true,\r\n" + 
				"                              \"remind_type\": \"offline_course\",\r\n" + 
				"                              \"advance_minute\": 0,\r\n" + 
				"                              \"is_lecturer_remind\": false,\r\n" + 
				"                              \"is_student_remind\": false,\r\n" + 
				"                              \"is_director_remind\": false,\r\n" + 
				"                              \"is_lecturer_student_evaluate\": true,\r\n" + 
				"                              \"is_student_lecturer_evaluate\": true,\r\n" + 
				"                              \"is_student_course_evaluate\": true,\r\n" + 
				"                              \"lecturer_student_evaluate_content\": {\r\n" + 
				"                                    \"evaluate_type\": \"score\",\r\n" + 
				"                                    \"questionnaire_id\": \"\",\r\n" + 
				"                                    \"questionnaire_name\": \"\",\r\n" + 
				"                                    \"total_score\": 100,\r\n" + 
				"                                    \"passing_score\": 60\r\n" + 
				"                              },\r\n" + 
				"                              \"student_lecturer_evaluate_content\": {\r\n" + 
				"                                    \"evaluate_type\": \""+evaluate_type+"\",\r\n" + 
				"                                    \"questionnaire_id\": \""+questionnaire_id+"\",\r\n" + 
				"                                    \"questionnaire_name\": \""+questionnaire_name+"\",\r\n" + 
				"                                    \"total_score\": \"\",\r\n" + 
				"                                    \"passing_score\": \"\"\r\n" + 
				"                              },\r\n" + 
				"                              \"student_course_evaluate_content\": {\r\n" + 
				"                                    \"evaluate_type\": \""+evaluate_type+"\",\r\n" + 
				"                                    \"questionnaire_id\": \""+questionnaire_id+"\",\r\n" + 
				"                                    \"questionnaire_name\": \""+questionnaire_name+"\",\r\n" + 
				"                                    \"total_score\": \"\",\r\n" + 
				"                                    \"passing_score\": \"\"\r\n" + 
				"                              },\r\n" + 
				"                              \"approvers\": [\r\n" + 
				"                                    \""+user_id+"\"\r\n" + 
				"                              ],\r\n" + 
				"                              \"assessment_total_score\": 100,\r\n" + 
				"                              \"assessment_pass_score\": 70,\r\n" + 
				"                              \"resource_lock\": false\r\n" + 
				"                        }\r\n" + 
				"                  ]\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"stagePass\": false,\r\n" + 
				"      \"finishEvaluation\": false,\r\n" + 
				"      \"openEvaluationResult\": false,\r\n" + 
				"      \"action\": \"published\",\r\n" + 
				"      \"free\": true,\r\n" + 
				"      \"official_price_str\": \"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: SearchCourse  
	 * @Description: TODO      首页按课程搜索
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SearchCourse(String keyword) {	
		return HttpRequest.get(search_course_url).query("access_token", token).query("keyword",keyword).query("pageNo","1")
				.query("pageSize","10").query("source","h5")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: SearchTask  
	 * @Description: TODO      首页按任务搜索
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SearchTask(String keyword) {	
		return HttpRequest.get(search_task_url).query("access_token", token).query("keyword",keyword).query("pageNo","1")
				.query("pageSize","10").query("source","h5")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: SearchLecturer  
	 * @Description: TODO      首页按讲师搜索
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SearchLecturer(String keyword) {	
		return HttpRequest.get(search_lecturer_url).query("access_token", token).query("keyword",keyword).query("pageNo","1")
				.query("pageSize","10").query("source","h5")
				.send().body();
	}
	
	/**   
	 * @Title: DeleteStudy  
	 * @Description: TODO 删除学习任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteStudy(String study_id) {		
		return HttpRequest.post(delete_study_url).query("access_token", token).query("id",study_id).send().body();
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
	 * @Title: DeleteLecturerLever  
	 * @Description: TODO      删除讲师等级
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteLecturerLever(String lever_id) {	
		return HttpRequest.post(delete_lecturer_level_url).query("access_token", token).data("{\r\n" + 
				"      \"level_ids\": [\r\n" + 
				"            \""+lever_id+"\"\r\n" + 
				"      ],\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: SearchGessUList  
	 * @Description: TODO      移动端首页搜索-猜你喜欢
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SearchGessUList(String top) {	
		return HttpRequest.get(search_guessUList_url).query("access_token", token).query("top",top)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: SearchHistory  
	 * @Description: TODO      移动端首页搜索-历史搜索
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SearchHistory(String top) {	
		return HttpRequest.get(search_history_url).query("access_token", token).query("top",top)
				.send().body();
	}
	
}
