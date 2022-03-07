package cn.kxy.group.a.business;
/**
 * @author wenlingzhi
 *2021年8月18日
 */

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

public class OffLineCourseBusiness {
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String offlinecourse_url = EnterpriseDataUrl.getOfflineCourseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String add_lecturers_url = enterprise_url + "v2/"+enterpriseId+"/lecturers/add/";
	public static String lecturer_level_add_url = enterprise_url + "v2/"+enterpriseId+"/lecturer_level/";
	public static String study_add_url = enterprise_url + "plan/study/add/";
	public static String delete_lecturer_url(String lecturer_id) {
		return enterprise_url + "v2/"+enterpriseId+"/lecturers/"+lecturer_id+"/delete/";
	}
	public static String delete_lecturer_level_url = enterprise_url + "v2/"+enterpriseId+"/lecturer_level/batch_delete/";
	public static String getone_url = enterprise_url + "plan/myTask/getOne/";
	public static String sign_analysis_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/offline-courses/"+offlinecourse_id+"/sign-analysis/";
	}
	public static String sign_url(String sign_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/users/"+user_id+"/signs/"+sign_id;
	}
	public static String courses_detail_url(String lecturer_id) {
		return enterprise_url + "v2/"+enterpriseId+"/lecturers/"+lecturer_id+"/courses/";
	}
	public static String score_detail_url = enterprise_url + "v2/"+enterpriseId+"/integral/"+user_id+"/get_score_detail_page/";
	public static String class_pay_url (String lecturer_id) {
		return enterprise_url + "v2/"+enterpriseId+"/lecturer/"+lecturer_id+"/query_class_pay/";
	}
	public static String update_status_url(String lecturer_id) {
		return enterprise_url + "v2/"+enterpriseId+"/lecturers/"+lecturer_id+"/update_status/";
	}
	public static String delete_study_url = enterprise_url + "plan/study/delete/";
	public static String edit_study_url = enterprise_url + "plan/study/edit/";
	public static String getEmployeeTrainTask_url = enterprise_url + "plan/employeeTrain/getEmployeeTrainTask/";
	public static String summary_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/offline-courses/"+offlinecourse_id+"/summary/";
	}
	public static String settlement_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/offline-courses/"+offlinecourse_id+"/settlement/";
	}
	public static String evaluate_list_url = offlinecourse_url + "v2/enterprises/"+enterpriseId+"/users/"+user_id+"/evaluate-list/";
	public static String evaluate_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/users/"+user_id+"/course/"+offlinecourse_id+"/submit-evaluate-content/";
	}
	public static String evaluate_user_list_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/users/"+user_id+"/course/"+offlinecourse_id+"/lecturer-evaluate-user-list/";
	}
	public static String evaluate_detail_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/users/"+user_id+"/course/"+offlinecourse_id+"/my-evaluate-info/";
	}
	public static String evaluatemy_detail_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/users/"+user_id+"/course/"+offlinecourse_id+"/user-evaluate-lecturer-list/";
	}
	public static String evaluatemyinfo_detail_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/users/"+user_id+"/course/"+offlinecourse_id+"/evaluate-my-info/";
	}
	public static String studyplan_monitor_url (String studyplan_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/plans/"+studyplan_id+"/offline-courses/list/";
	}
	public static String offline_courses_monitor_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/offline-courses/"+offlinecourse_id+"/monitors/";
	}
	public static String sign_monitor_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/offline-courses/"+offlinecourse_id+"/monitors/";
	}
	public static String evaluate_analysis_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/"+enterpriseId+"/course/"+offlinecourse_id+"/evaluate-analysis/";
	}
	public static String evaluate_analysis_detail_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/"+enterpriseId+"/course/"+offlinecourse_id+"/evaluate-analysis/evaluate-info-list/";
	}
	public static String offline_course_export_url (String studyplan_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/plans/"+studyplan_id+"/offline-course/list/export/";
	}
	public static String offline_course_monitor_export_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/offline-courses/"+offlinecourse_id+"/monitors-export/";
	}
	public static String sign_export_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/offline-courses/"+offlinecourse_id+"/sign-analysis-export/";
	}
	public static String evaluate_analysis_export_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/"+enterpriseId+"/course/"+offlinecourse_id+"/evaluate-analysis/export-evaluate-info-list/";
	}
	public static String studyProject_add_url = enterprise_url + "v2/"+enterpriseId+"/study_projects/save/";
	public static String project_process_url (String project_course_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/study_projects/"+project_course_id+"/query/";
	}
	public static String edit_project_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId+"/study_projects/"+project_id+"/edit/";
	}
	public static String study_project_delete_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_projects/"+project_id+"/delete/";
	}
	public static String getlecturter_url = enterprise_url + "v2/"+enterpriseId+"/lecturers/";
	public static String sign_courses_list_url = offlinecourse_url + "v2/enterprises/"+enterpriseId+"/sign-courses/";
	public static String sign_course_info_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/offline-courses/"+offlinecourse_id+"/info/";
	}
	public static String sign_course_monitors_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/offline-courses/"+offlinecourse_id+"/monitors/";
	}
	public static String teachings_url = offlinecourse_url + "v2/enterprises/"+enterpriseId+"/offline-courses/teachings/";
	public static String score_submit_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/offline-courses/"+offlinecourse_id+"/score-submit/";
	}
	public static String approval_submit_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/offline-courses/"+offlinecourse_id+"/approval-submit/";
	}
	public static String approval_user_url (String offlinecourse_id) {
		return offlinecourse_url + "v2/enterprises/"+enterpriseId+"/offline-courses/"+offlinecourse_id+"/users/";
	}
	
	
	
	/**   
	 * @Title: GetLecturter  
	 * @Description: TODO      查询讲师
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetLecturter(String keyword) {	
		return HttpRequest.get(getlecturter_url).query("access_token", token).query("keyword",keyword).query("status","")
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
	 * @Title: StudyPlanEdit  
	 * @Description: TODO  编辑学习任务培训时间
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyPlanEdit(String title,String beginTime,String endTime,String stageId,String courseId,String mappingId,
			String cover,String begin_time,String end_time,String offline_course_setting_list_id,String course_duration,String lecturer_id,
			String offline_course_sign_config_list_id,String studyplan_id) {	
		return HttpRequest.post(edit_study_url).query("access_token", token).data("{\r\n" + 
				"      \"title\": \""+title+"\",\r\n" + 
				"      \"beginTime\": \""+beginTime+"\",\r\n" + 
				"      \"endTime\": \""+endTime+"\",\r\n" + 
				"      \"projectId\": \"\",\r\n" + 
				"      \"summary\": \"\",\r\n" + 
				"      \"sync_progress\": \"false\",\r\n" + 
				"      \"classify_id\": \"999\",\r\n" + 
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
				"      \"studyTimeLimit\": 30,\r\n" + 
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
				"                  \"stageId\": \""+stageId+"\",\r\n" + 
				"                  \"startTime\": null,\r\n" + 
				"                  \"endTime\": null,\r\n" + 
				"                  \"course\": [\r\n" + 
				"                        {\r\n" + 
				"                              \"courseSort\": 0,\r\n" + 
				"                              \"flag\": 3,\r\n" + 
				"                              \"id\": \""+courseId+"\",\r\n" + 
				"                              \"type\": \"xx\",\r\n" + 
				"                              \"mappingId\": \""+mappingId+"\",\r\n" + 
				"                              \"title\": \""+title+"\",\r\n" + 
				"                              \"resource_classify\": \"999\",\r\n" + 
				"                              \"cover\": \""+cover+"\",\r\n" + 
				"                              \"cover_type\": 0,\r\n" + 
				"                              \"department_id\": \"\",\r\n" + 
				"                              \"trainees\": \"\",\r\n" + 
				"                              \"begin_time\": \""+begin_time+"\",\r\n" + 
				"                              \"end_time\": \""+end_time+"\",\r\n" + 
				"                              \"attachment\": [],\r\n" + 
				"                              \"offline_course_setting_list\": [\r\n" + 
				"                                    {\r\n" + 
				"                                          \"id\": \""+offline_course_setting_list_id+"\",\r\n" + 
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
				"                              \"introduction\": \"introduction test\",\r\n" + 
				"                              \"budget\": 1000,\r\n" + 
				"                              \"offline_course_sign_config_list\": [\r\n" + 
				"                                    {\r\n" + 
				"                                          \"name\": \"第1课\",\r\n" + 
				"                                          \"qrcode_type\": \"statics\",\r\n" + 
				"                                          \"sign_in_begin_time\": \""+begin_time+"\",\r\n" + 
				"                                          \"sign_in_end_time\": \""+end_time+"\",\r\n" + 
				"                                          \"sign_out_begin_time\": \""+begin_time+"\",\r\n" + 
				"                                          \"sign_out_end_time\": \""+end_time+"\",\r\n" + 
				"                                          \"sort\": 1,\r\n" + 
				"                                          \"id\": \""+offline_course_sign_config_list_id+"\"\r\n" + 
				"                                    }\r\n" + 
				"                              ],\r\n" + 
				"                              \"is_open_evaluate\": true,\r\n" + 
				"                              \"is_open_offline_assessment\": true,\r\n" + 
				"                              \"is_open_remind\": false,\r\n" + 
				"                              \"is_open_schedule\": false,\r\n" + 
				"                              \"is_open_upload\": true,\r\n" + 
				"                              \"remind_type\": \"offline_course\",\r\n" + 
				"                              \"advance_minute\": 0,\r\n" + 
				"                              \"is_lecturer_remind\": null,\r\n" + 
				"                              \"is_student_remind\": null,\r\n" + 
				"                              \"is_director_remind\": null,\r\n" + 
				"                              \"is_lecturer_student_evaluate\": true,\r\n" + 
				"                              \"is_student_lecturer_evaluate\": true,\r\n" + 
				"                              \"is_student_course_evaluate\": true,\r\n" + 
				"                              \"lecturer_student_evaluate_content\": {\r\n" + 
				"                                    \"evaluate_type\": \"score\",\r\n" + 
				"                                    \"passing_score\": 60,\r\n" + 
				"                                    \"questionnaire_id\": \"\",\r\n" + 
				"                                    \"questionnaire_name\": \"\",\r\n" + 
				"                                    \"total_score\": 100\r\n" + 
				"                              },\r\n" + 
				"                              \"student_lecturer_evaluate_content\": {\r\n" + 
				"                                    \"evaluate_type\": \"star\",\r\n" + 
				"                                    \"passing_score\": \"\",\r\n" + 
				"                                    \"questionnaire_id\": \"\",\r\n" + 
				"                                    \"questionnaire_name\": \"\",\r\n" + 
				"                                    \"total_score\": \"\"\r\n" + 
				"                              },\r\n" + 
				"                              \"student_course_evaluate_content\": {\r\n" + 
				"                                    \"evaluate_type\": \"star\",\r\n" + 
				"                                    \"passing_score\": \"\",\r\n" + 
				"                                    \"questionnaire_id\": \"\",\r\n" + 
				"                                    \"questionnaire_name\": \"\",\r\n" + 
				"                                    \"total_score\": \"\"\r\n" + 
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
				"      \"id\": \""+studyplan_id+"\",\r\n" + 
				"      \"planStatus\": \"unfinished\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: lecturerLeverAdd  
	 * @Description: TODO  创建讲师等级
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String LecturerLeverAdd(String name,String standard,String type,String pay_standard) {	
		return HttpRequest.post(lecturer_level_add_url).query("access_token", token).data("{\r\n" + 
				"      \"id\": \"\",\r\n" + 
				"      \"name\": \""+name+"\",\r\n" + 
				"      \"standard\": \""+standard+"\",\r\n" + 
				"      \"type\": \""+type+"\",\r\n" + 
				"      \"pay_standard\": \""+pay_standard+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: AddLecturers  
	 * @Description: TODO      添加讲师
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AddLecturers(String lecturer_name,String avatar,String type,String lever_id,String level_start_time) {	
		return HttpRequest.post(add_lecturers_url).query("access_token", token).data("{\r\n" + 
				"      \"lecturer_name\": \""+lecturer_name+"\",\r\n" + 
				"      \"avatar\": \""+avatar+"\",\r\n" + 
				"      \"sex\": 0,\r\n" + 
				"      \"mobile\": \"\",\r\n" + 
				"      \"type\": \""+type+"\",\r\n" + 
				"      \"user_id\": \""+user_id+"\",\r\n" + 
				"      \"level\": \""+lever_id+"\",\r\n" + 
				"      \"adept_field\": \"\",\r\n" + 
				"      \"lecturer_describe\": \"\",\r\n" + 
				"      \"level_start_time\": \""+level_start_time+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: DeleteLecturer  
	 * @Description: TODO      删除讲师
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteLecturer(String lecturer_id) {	
		return HttpRequest.post(delete_lecturer_url(lecturer_id)).query("access_token", token)
				.send().body();
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
	 * @Title: GetOne  
	 * @Description: TODO      获取学习任务-线下课id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetOne(String studyplan_id) {	
		return HttpRequest.get(getone_url).query("access_token", token).query("id",studyplan_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: SignAnalysis  
	 * @Description: TODO      获取学习任务-线下课二维码id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SignAnalysis(String offlinecourse_id) {	
		return HttpRequest.get(sign_analysis_url(offlinecourse_id)).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: Sign  
	 * @Description: TODO      学习任务-线下课考勤-签到
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String Sign(String sign_id,String sign_style) {	
		return HttpRequest.post(sign_url(sign_id)).query("access_token", token).data("{\r\n" + 
				"	\"qc_time\": \"\",\r\n" + 
				"	\"sign_style\": \""+sign_style+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: CoursesDetail  
	 * @Description: TODO      讲师列表-查看-授课明细
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CoursesDetail(String lecturer_id,String start_time,String end_time,String biz_type) {	
		return HttpRequest.get(courses_detail_url(lecturer_id)).query("access_token", token).query("start_time",start_time)
				.query("end_time",end_time).query("biz_type",biz_type)
				.send().body();
	}
	
	
	/**   
	 * @Title: ScoreDetail  
	 * @Description: TODO      讲师列表-查看-积分明细
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ScoreDetail(String begin_time,String end_time) {	
		return HttpRequest.get(score_detail_url).query("access_token", token).query("begin_time",begin_time)
				.query("end_time",end_time).query("type","8")
				.send().body();
	}
	
	
	/**   
	 * @Title: ClassPay  
	 * @Description: TODO      讲师列表-查看-课酬明细
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ClassPay(String lecturer_id,String start_time,String end_time) {	
		return HttpRequest.get(class_pay_url(lecturer_id)).query("access_token", token).query("start_time",start_time)
				.query("end_time",end_time).query("type","8")
				.send().body();
	}
	
	
	/**   
	 * @Title: UpdateStatus  
	 * @Description: TODO     开启/停用讲师状态
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UpdateStatus(String lecturer_id,String status) {	
		return HttpRequest.post(update_status_url(lecturer_id)).query("access_token", token).data("{\r\n" + 
				"      \"status\": \""+status+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
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
	 * @Title: GetEmployeeTrainTask  
	 * @Description: TODO    获取学习任务详情
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetEmployeeTrainTask(String study_id) {		
		return HttpRequest.get(getEmployeeTrainTask_url).query("access_token", token).query("planId",study_id)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: OfflineCoursesSummary  
	 * @Description: TODO    线下课总结
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String OfflineCoursesSummary(String offlinecourse_id,String summary) {		
		return HttpRequest.post(summary_url(offlinecourse_id)).query("access_token", token).data("{\r\n" + 
				"      \"actual_cost\": 2,\r\n" + 
				"      \"summary\": \""+summary+"\",\r\n" + 
				"      \"summary_attachment_id_list\": [],\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: OfflineCoursesSettlement  
	 * @Description: TODO    线下课结算
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String OfflineCoursesSettlement(String offlinecourse_id,String lecturer_id) {		
		return HttpRequest.post(settlement_url(offlinecourse_id)).query("access_token", token).data("{\r\n" + 
				"      \"integral\": \"6\",\r\n" + 
				"      \"subsidy\": 3,\r\n" + 
				"      \"is_lecturer_subsidy\": false,\r\n" + 
				"      \"lecture_id\": \""+lecturer_id+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: EvaluateList  
	 * @Description: TODO    我的评价列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluateList(String type,String keyword,String status,String evaluate_type) {		
		return HttpRequest.get(evaluate_list_url).query("access_token", token).query("type",type).query("keyword",keyword)
				.query("status",status).query("evaluate_type",evaluate_type)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: EvaluateStudent  
	 * @Description: TODO    给学员评价
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluateStudent(String offlinecourse_id,String evaluate_id) {		
		return HttpRequest.post(evaluate_url(offlinecourse_id)).query("access_token", token).data("{\r\n" + 
				"      \"evaluate_id\": \""+evaluate_id+"\",\r\n" + 
				"      \"evaluate_type\": \"score\",\r\n" + 
				"      \"evaluate_object_id\": \""+user_id+"\",\r\n" + 
				"      \"evaluate_content_vo\": {\r\n" + 
				"            \"evaluate_vo\": {\r\n" + 
				"                  \"score\": 5,\r\n" + 
				"                  \"comments\": \"evaluate student test\",\r\n" + 
				"                  \"content\": \"\",\r\n" + 
				"                  \"passing_score\": \"60\",\r\n" + 
				"                  \"total_score\": \"100\"\r\n" + 
				"            }\r\n" + 
				"      },\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: EvaluateList  
	 * @Description: TODO    我的评价列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluateUserList(String offlinecourse_id,String user_status,String is_evaluated,String evaluate_id) {		
		return HttpRequest.get(evaluate_user_list_url(offlinecourse_id)).query("access_token", token).query("user_status",user_status)
				.query("is_evaluated",is_evaluated).query("evaluate_id",evaluate_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: EvaluateStudent  
	 * @Description: TODO    给讲师评价
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluateLecurter(String offlinecourse_id,String evaluate_id) {		
		return HttpRequest.post(evaluate_url(offlinecourse_id)).query("access_token", token).data("{\r\n" + 
				"      \"evaluate_object_id\": \""+offlinecourse_id+"\",\r\n" + 
				"      \"evaluate_type\": \"star\",\r\n" + 
				"      \"evaluate_id\": \""+evaluate_id+"\",\r\n" + 
				"      \"evaluateContentVo\": {\r\n" + 
				"            \"evaluateVo\": {\r\n" + 
				"                  \"star\": 4\r\n" + 
				"            }\r\n" + 
				"      },\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: LecurterEvaluateDetail  
	 * @Description: TODO    查看给讲师评价详情
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluateDetail(String offlinecourse_id,String evaluate_id,String is_evaluated,String evaluate_object_id) {		
		return HttpRequest.get(evaluate_detail_url(offlinecourse_id)).query("access_token", token).query("evaluate_id",evaluate_id)
				.query("is_evaluated",is_evaluated).query("evaluate_object_id",evaluate_object_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: EvaluateCourse  
	 * @Description: TODO    给课程评价
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluateCourse(String offlinecourse_id,String evaluate_id) {		
		return HttpRequest.post(evaluate_url(offlinecourse_id)).query("access_token", token).data("{\r\n" + 
				"      \"evaluate_object_id\": \""+offlinecourse_id+"\",\r\n" + 
				"      \"evaluate_type\": \"star\",\r\n" + 
				"      \"evaluate_id\": \""+evaluate_id+"\",\r\n" + 
				"      \"evaluateContentVo\": {\r\n" + 
				"            \"evaluateVo\": {\r\n" + 
				"                  \"star\": 4\r\n" + 
				"            }\r\n" + 
				"      },\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: EvaluateMyDetail  
	 * @Description: TODO    查看学员对我的评价详情
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluateMyDetail(String offlinecourse_id,String evaluate_id,String lecturer_id) {		
		return HttpRequest.get(evaluatemy_detail_url(offlinecourse_id)).query("access_token", token).query("evaluate_id",evaluate_id)
				.query("lecturer_id",lecturer_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: EvaluateMyLecurterDetail  
	 * @Description: TODO    查看讲师对我的评价详情
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluateMyLecurterDetail(String offlinecourse_id,String evaluate_id,String type) {		
		return HttpRequest.get(evaluatemyinfo_detail_url(offlinecourse_id)).query("access_token", token).query("evaluate_id",evaluate_id)
				.query("type",type).query("evaluate_user_id",user_id)
				.send().body();
	}
		
	/**   
	 * @Title: StudyPlanMonitor  
	 * @Description: TODO    学习任务数据-线下课
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PlanMonitor(String id,String source_type) {		
		return HttpRequest.get(studyplan_monitor_url(id)).query("access_token", token).query("source_type",source_type)
				.query("page_number","1").query("page_size","20")
				.send().body();
	}
	
	
	/**   
	 * @Title: OfflineCoursesMonitor  
	 * @Description: TODO    学习任务数据-线下课-人员监控
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String OfflineCoursesMonitor(String offlinecourse_id,String studyplan_id,String keyword) {		
		return HttpRequest.get(offline_courses_monitor_url(offlinecourse_id)).query("access_token", token).query("plan_id",studyplan_id)
				.query("department_id","").query("post_id","").query("keyword",keyword).query("sign_id","")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: OfflineCoursesMonitor  
	 * @Description: TODO    学习任务数据-线下课-考勤详情
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SignMonitor(String offlinecourse_id,String studyplan_id,String keyword,String sign_id) {		
		return HttpRequest.get(sign_monitor_url(offlinecourse_id)).query("access_token", token).query("plan_id",studyplan_id)
				.query("department_id","").query("post_id","").query("keyword",keyword).query("sign_id","")
				.send().body();
	}
	
	
	/**   
	 * @Title: EvaluateAnalysis  
	 * @Description: TODO    学习任务数据-线下课-评价分析列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluateAnalysis(String offlinecourse_id) {		
		return HttpRequest.get(evaluate_analysis_url(offlinecourse_id)).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: EvaluateAnalysisDatail  
	 * @Description: TODO    学习任务数据-线下课-评价分析-评价详情
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluateAnalysisDatail(String offlinecourse_id,String type,String evaluate_id,String evaluate_type,
			String lecturer_id) {		
		return HttpRequest.get(evaluate_analysis_detail_url(offlinecourse_id)).query("access_token", token).query("type",type)
				.query("evaluate_id",evaluate_id).query("evaluate_type",evaluate_type).query("lecturer_id",lecturer_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: OfflineCourseExport  
	 * @Description: TODO    学习任务-线下课导出
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String OfflineCourseExport(String studyplan_id,String source_type) {		
		return HttpRequest.get(offline_course_export_url(studyplan_id)).query("access_token", token).query("source_type",source_type)
				.send().body();
	}
	
	
	/**   
	 * @Title: OfflineCourseMonitorExport  
	 * @Description: TODO    学习任务-线下课-人员监控导出
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String OfflineCourseMonitorExport(String offlinecourse_id,String studyplan_id) {		
		return HttpRequest.get(offline_course_monitor_export_url(offlinecourse_id)).query("access_token", token)
				.query("plan_id",studyplan_id).query("source","management")
				.send().body();
	}
	
	
	/**   
	 * @Title: SignExport  
	 * @Description: TODO    学习任务-线下课-考勤分析导出
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SignExport(String offlinecourse_id) {		
		return HttpRequest.get(sign_export_url(offlinecourse_id)).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: EvaluateAnalysisExport  
	 * @Description: TODO    学习任务-线下课-评价分析-评价详情导出
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluateAnalysisExport(String offlinecourse_id,String evaluate_id,String evaluate_type,String lecturer_id,
			String type) {		
		return HttpRequest.get(evaluate_analysis_export_url(offlinecourse_id)).query("access_token", token).query("evaluate_id",evaluate_id)
				.query("evaluate_type",evaluate_type).query("lecturer_id",lecturer_id).query("type",type)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: ProjectProcess  
	 * @Description: TODO    学习项目获取学习进度
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ProjectProcess(String project_course_id) {		
		return HttpRequest.get(project_process_url(project_course_id)).query("access_token", token).query("app_type","")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: EditProject  
	 * @Description: TODO      编辑学习项目时间
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EditProject(String project_id,String project_offlinecourse_id,String title,String cover,String begin_time,
			String end_time,String course_duration,String offline_course_setting_list_id,String lecturer_id,
			String offline_course_sign_config_list_id) {
		return HttpRequest.post(edit_project_url(project_id)).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"stage_content\",\r\n" + 
				"      \"stage_content\": {\r\n" + 
				"            \"stages\": [\r\n" + 
				"                  {\r\n" + 
				"                        \"id\": \""+project_id+"\",\r\n" + 
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
				"                                    \"course_id\": \""+project_offlinecourse_id+"\",\r\n" + 
				"                                    \"offline_course\": {\r\n" + 
				"                                          \"id\": \""+project_offlinecourse_id+"\",\r\n" + 
				"                                          \"title\": \""+title+"\",\r\n" + 
				"                                          \"cover\": \""+cover+"\",\r\n" + 
				"                                          \"cover_type\": 0,\r\n" + 
				"                                          \"department_id\": \"\",\r\n" + 
				"                                          \"resource_classify\": \"999\",\r\n" + 
				"                                          \"trainees\": \"\",\r\n" + 
				"                                          \"begin_time\": \""+begin_time+"\",\r\n" + 
				"                                          \"end_time\": \""+end_time+"\",\r\n" + 
				"                                          \"attachment\": [],\r\n" + 
				"                                          \"offline_course_setting_list\": [\r\n" + 
				"                                                {\r\n" + 
				"                                                      \"id\": \""+offline_course_setting_list_id+"\",\r\n" + 
				"                                                      \"name\": \"第1课\",\r\n" + 
				"                                                      \"sort\": 1,\r\n" + 
				"                                                      \"begin_time\": \""+begin_time+"\",\r\n" + 
				"                                                      \"end_time\": \""+end_time+"\"\r\n" + 
				"                                                }\r\n" + 
				"                                          ],\r\n" + 
				"                                          \"course_duration\": 63,\r\n" + 
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
				"                                                      \"sort\": 1,\r\n" + 
				"                                                      \"id\": \""+offline_course_sign_config_list_id+"\"\r\n" + 
				"                                                }\r\n" + 
				"                                          ],\r\n" + 
				"                                          \"is_open_evaluate\": true,\r\n" + 
				"                                          \"is_open_offline_assessment\": true,\r\n" + 
				"                                          \"is_open_remind\": false,\r\n" + 
				"                                          \"is_open_schedule\": false,\r\n" + 
				"                                          \"is_open_upload\": false,\r\n" + 
				"                                          \"remind_type\": \"offline_course\",\r\n" + 
				"                                          \"advance_minute\": 0,\r\n" + 
				"                                          \"is_lecturer_remind\": null,\r\n" + 
				"                                          \"is_student_remind\": null,\r\n" + 
				"                                          \"is_director_remind\": null,\r\n" + 
				"                                          \"is_lecturer_student_evaluate\": true,\r\n" + 
				"                                          \"is_student_lecturer_evaluate\": true,\r\n" + 
				"                                          \"is_student_course_evaluate\": true,\r\n" + 
				"                                          \"lecturer_student_evaluate_content\": {\r\n" + 
				"                                                \"evaluate_type\": \"score\",\r\n" + 
				"                                                \"passing_score\": 60,\r\n" + 
				"                                                \"questionnaire_id\": \"\",\r\n" + 
				"                                                \"questionnaire_name\": \"\",\r\n" + 
				"                                                \"total_score\": 100\r\n" + 
				"                                          },\r\n" + 
				"                                          \"student_lecturer_evaluate_content\": {\r\n" + 
				"                                                \"evaluate_type\": \"star\",\r\n" + 
				"                                                \"passing_score\": \"\",\r\n" + 
				"                                                \"questionnaire_id\": \"\",\r\n" + 
				"                                                \"questionnaire_name\": \"\",\r\n" + 
				"                                                \"total_score\": \"\"\r\n" + 
				"                                          },\r\n" + 
				"                                          \"student_course_evaluate_content\": {\r\n" + 
				"                                                \"evaluate_type\": \"star\",\r\n" + 
				"                                                \"passing_score\": \"\",\r\n" + 
				"                                                \"questionnaire_id\": \"\",\r\n" + 
				"                                                \"questionnaire_name\": \"\",\r\n" + 
				"                                                \"total_score\": \"\"\r\n" + 
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
	 * @Title: SignCoursesList  
	 * @Description: TODO     培训操作-签到管理
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SignCoursesList(String keyword,String train_status) {		
		return HttpRequest.get(sign_courses_list_url).query("access_token", token).query("keyword",keyword).query("train_status",train_status)
				.query("page_number","1").query("page_size","20")
				.send().body();
	}
	
	
	/**   
	 * @Title: SignCoursesInfo  
	 * @Description: TODO     培训操作-签到管理-查看详情
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SignCoursesInfo(String offlinecourse_id) {		
		return HttpRequest.get(sign_course_info_url(offlinecourse_id)).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: SignCoursesMonitor  
	 * @Description: TODO     培训操作-签到管理-查看详情-人员监控
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SignCoursesMonitor(String offlinecourse_id) {		
		return HttpRequest.get(sign_course_monitors_url(offlinecourse_id)).query("access_token", token).query("page_number","1")
				.query("page_size","20").query("department_id","").query("post_id","").query("keyword","").query("sign_id","")
				.send().body();
	}
	
	
	
	/**   
	 * @Title: Teachings  
	 * @Description: TODO     培训操作-授课管理
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String Teachings(String keyword,String train_status) {		
		return HttpRequest.get(teachings_url).query("access_token", token).query("keyword",keyword).query("train_status",train_status)
				.query("page_number","1").query("page_size","20")
				.send().body();
	}
	
	
	/**   
	 * @Title: ScoreSubmit  
	 * @Description: TODO     培训操作-授课管理-录入学员成绩
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ScoreSubmit(String offlinecourse_id) {		
		return HttpRequest.post(score_submit_url(offlinecourse_id)).query("access_token", token).data("{\r\n" + 
				"      \"user_id\": \""+user_id+"\",\r\n" + 
				"      \"score\": 10,\r\n" + 
				"      \"remark\": \"\",\r\n" + 
				"      \"resource_ids\": [],\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: ApprovalSubmit  
	 * @Description: TODO     培训操作-成绩审批-审批
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ApprovalSubmit(String offlinecourse_id,String status) {		
		return HttpRequest.post(approval_submit_url(offlinecourse_id)).query("access_token", token).data("{\r\n" + 
				"      \"status\": \""+status+"\",\r\n" + 
				"      \"user_id\": \""+user_id+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: ApprovalUser  
	 * @Description: TODO     培训操作-成绩审批-审核评审-已审核
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ApprovalUser(String offlinecourse_id,String user_status,String is_evaluated) {		
		return HttpRequest.get(approval_user_url(offlinecourse_id)).query("access_token", token).query("page_number","1")
				.query("page_size","10").query("user_status",user_status).query("is_evaluated",is_evaluated).query("evaluate_id","")
				.send().body();
	}
	
	
	
}
