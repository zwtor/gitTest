package cn.kxy.group.a.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class OncallBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String evaluation_url = EnterpriseDataUrl.getEvaluationUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String studyplan_add_url = enterprise_url + "plan/study/add/";
	public static String resource_getList_url = enterprise_url + "course/resource/getList/";
	public static String getOne_url = enterprise_url + "plan/myTask/getOne/";
	public static String save_progress_url(String studyPlan_id,String course_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/studies/"+studyPlan_id+"/courses/"+course_id+"/resources/"+course_id+"/save_progress/";
	}
	public static String getScoreRecord_url = enterprise_url + "score/getScoreRecord/";
	public static String score_delete_url = enterprise_url + "v2/"+enterpriseId+"/scores/delete/";
	public static String study_projects_save_url = enterprise_url + "v2/"+enterpriseId +"/study_projects/save/";
	public static String delete_study_url = enterprise_url + "plan/study/delete/";
	public static String study_projects_query_url = enterprise_url + "course/queryCourseByPage/";
	public static String project_save_progress_url(String projectCourse_id,String course_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/study_projects/"+projectCourse_id+"/courses/"+course_id+"/resources/"+course_id+"/save_progress/";
	}
	public static String start_study_url(String project_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/study_projects/"+project_id+"/start_study/";
	}
	public static String study_project_delete_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_projects/"+project_id+"/delete/";
	}
	public static String add_questionnaire_url = enterprise_url + "v2/"+enterpriseId +"/questionnaire/";
	public static String copy_questionnaire_url(String questionnaire_id) {
		return enterprise_url + "v2/"+"/questionnaire/"+questionnaire_id+"/copy/";
	}
	public static String delete_questionnaire_url(String questionnaire_id) {
		return enterprise_url + "v2/"+enterpriseId +"/questionnaire/"+questionnaire_id+"/delete/";
	}
	public static String add_investigate_url = enterprise_url + "v2/"+enterpriseId +"/investigate/";
	public static String questionnaires_query_url (String questionnaire_id,String investigate_id) {
		return enterprise_url + "v2/"+enterpriseId+"/investigates/"+investigate_id+"/questionnaires/"+questionnaire_id;
	}
	public static String submit_investigate_url(String researches_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/researches/"+researches_id+"/submit/";
	}
	public static String delete_investigate_url(String investigate_id) {
		return enterprise_url + "v2/"+enterpriseId+"/investigates/"+investigate_id+"/delete/";
	}
	public static String queryQuestionBankList_url = exam_url + "questionBank/queryQuestionBankList/";
	public static String exam_add_url = exam_url + "plan/exam/add/";
	public static String query_exams_url (String examPlan_id) {
		return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+examPlan_id+"/query/";
	}
	public static String submit_exam_url (String examPlan_id) {
		return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+examPlan_id+"/submit/";
	}
	public static String question_id_url (String examPlan_id) {
		return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+examPlan_id+"/query/";
	}
	public static String exam_result_url (String examPlan_id) {
		return exam_url + "v2/"+enterpriseId+"/exams/"+examPlan_id+"/result/";
	}
	public static String delete_exam_url = exam_url + "plan/exam/delete/";
	public static String evaluation_tools_list_url = enterprise_url + "v2/"+enterpriseId+"/evaluation/tools/";
	public static String evaluation_save_url = evaluation_url + "v1/"+enterpriseId+ "/evaluation/save/";
	public static String my_evaluation_list_url = evaluation_url + "v2/"+enterpriseId+ "/user/"+user_id+"/my_evaluation_list/";
	public static String evaluation_unpublish_url(String evaluation_id) {
		return evaluation_url + "v1/"+enterpriseId+ "/evaluation/"+evaluation_id+"/unpublish/";
	}
	public static String evaluation_delete_url(String evaluation_id) {
		return evaluation_url + "v1/"+enterpriseId+ "/evaluation/"+evaluation_id+"/delete/";
	}
	public static String qualifications_add_url = enterprise_url + "v1/qualifications/";
	public static String postmap_add_url = enterprise_url + "v2/"+enterpriseId+ "/post_maps/";
	public static String my_postmap_list = enterprise_url + "v2/"+enterpriseId+ "/users/"+user_id+"/post_maps/";
	public static String update_postmap_url(String postmap_id) {
		return enterprise_url + "v2/"+enterpriseId+ "/post_maps/"+postmap_id+"/update_status/";
	}
	public static String delete_postmap_url(String postmap_id) {
		return enterprise_url + "v2/"+enterpriseId+ "/post_maps/"+postmap_id+"/delete/";
	}
	public static String certificate_list_url = enterprise_url + "certificate/getList/";
	public static String save_or_update_base_info_url = enterprise_url + "v2/"+enterpriseId+"/employees/save_or_update_base_info/";
	public static String update_content_url = enterprise_url + "v2/"+enterpriseId+"/employees/update_content/";
	public static String update_setting_publish_url = enterprise_url + "v2/"+enterpriseId+"/employees/update_setting_publish/";
	public static String exportTrainMonitor_url = enterprise_url + "plan/employeeTrain/exportTrainMonitor/";
	public static String delete_employ_train_task = enterprise_url + "/plan/employeeTrain/deleteEmployTrainTask/";
	public static String delete_qualifications_url(String qualifications_id) {
		return enterprise_url + "v1/qualifications/"+qualifications_id+"/delete/";
	}
	public static String qualifications_items_url (String qualifications_id) {
		return enterprise_url + "v2/"+enterpriseId+"/qualifications/"+qualifications_id+"/items/";
	}
	public static String load_resources_url (String qualifications_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/studies/"+qualifications_id+"/load_resources/";
	}
	public static String check_award_url (String postmap_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/post_maps/"+postmap_id;
	}
	public static String get_award_url (String postmap_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id+"/post_maps/"+postmap_id+"/get_awards/";
	}
	public static String getScoreRank_url = enterprise_url + "/score/getScoreRank/";
	public static String qualifications_exam_url (String qualifications_id) {
		return enterprise_url + "v2/"+enterpriseId +"/qualifications/"+qualifications_id+"/items/";
	}
	public static String qualifications_state_url (String qualifications_id) {
		return enterprise_url + "v2/"+enterpriseId +"/qualifications/"+qualifications_id+"/state/";
	}
	public static String markingPaper_url = exam_url + "/plan/exam/batchSubmitMarkingPaper/";
	public static String query_postmap_exam_url (String exam_qualifications_id) {
		return exam_url + "v2/"+enterpriseId+"/exams/"+exam_qualifications_id+"/query/";
	}

	

	/**   
	 * @Title: ResourceGetList  
	 * @Description: TODO  获取课件列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ResourceGetList(String contentType) {		
	    return HttpRequest.get(resource_getList_url).query("access_token", token).query("resourceClassify","0").query("contentType",contentType)
	    		.send().body();
	}
	
	
	/**   
	 * @Title: StudyAdd  
	 * @Description: TODO  创建学习任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyPlanAdd(String title,String courseId) {		
	    return HttpRequest.post(studyplan_add_url).query("access_token", token).data("{\r\n" + 
	    		"      \"title\": \""+title+"\",\r\n" + 
	    		"      \"beginTime\": "+DateUtil.getTimeStamp(-7, "")+",\r\n" + 
				"      \"endTime\": "+DateUtil.getTimeStamp(9, "")+",\r\n" +
	    		"      \"projectId\": \"\",\r\n" + 
	    		"      \"summary\": \"\",\r\n" + 
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
	    		"            \"finishScore\": 2,\r\n" + 
	    		"            \"unFinishScore\": 0\r\n" + 
	    		"      },\r\n" + 
	    		"      \"scheduleSetting\": \"free_mode\",\r\n" + 
	    		"      \"studyTimeLimit\": 0,\r\n" + 
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
	    		"                              \"courseId\": \""+courseId+"\",\r\n" + 
	    		"                              \"courseSort\": 1,\r\n" + 
	    		"                              \"courseType\": 3,\r\n" + 
	    		"                              \"flag\": 1,\r\n" + 
	    		"                              \"mappingId\": \"\",\r\n" + 
	    		"                              \"resource_lock\": false\r\n" + 
	    		"                        }\r\n" + 
	    		"                  ]\r\n" + 
	    		"            }\r\n" + 
	    		"      ],\r\n" + 
	    		"      \"stagePass\": false,\r\n" + 
	    		"      \"finishEvaluation\": true,\r\n" + 
	    		"      \"openEvaluationResult\": false,\r\n" + 
	    		"      \"action\": \"published\",\r\n" + 
	    		"      \"free\": true,\r\n" + 
	    		"      \"official_price_str\": \"\",\r\n" + 
	    		"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
	    		.send().body();
	}
	
	
	
	/**   
	 * @Title: GetOne  
	 * @Description: TODO  学习任务详情页
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetOne(String studyPlan_id) {		
	    return HttpRequest.get(getOne_url).query("access_token", token).query("id",studyPlan_id)
	    		.send().body();
	}
	
	
	/**   
	 * @Title: SaveProcess  
	 * @Description: TODO  完成学习任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SaveProcess(String studyPlan_id,String course_id,String tempTime) {		
	    return HttpRequest.post(save_progress_url(studyPlan_id,course_id)).query("access_token", token).data("{\r\n" + 
	    		"      \"progress\": 100,\r\n" + 
	    		"      \"recent_start\": 0,\r\n" + 
	    		"      \"tempTime\": \""+tempTime+"\",\r\n" + 
	    		"      \"access_token\": \""+token+"\"\r\n" + 
	    		"}")
	    		.send().body();
	}
	
	
	/**   
	 * @Title: GetScoreRecord  
	 * @Description: TODO 获取学分排行榜-我的学分行为列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String GetScoreRecord(String startTime,String endTime) {		
	    return HttpRequest.get(getScoreRecord_url).query("access_token", token).query("userId",user_id)
	    		.query("startTime",startTime).query("endTime",endTime)
	    		.send().body();
	}
	
	
	
	/**   
	 * @Title: ScoreDelete  
	 * @Description: TODO   撤回积分
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ScoreDelete(String score_record_ids) {		
	    return HttpRequest.post(score_delete_url).query("access_token", token).data("{\r\n" + 
	    		"      \"score_record_ids\": [\r\n" + 
	    		"            \""+score_record_ids+"\"\r\n" + 
	    		"      ],\r\n" + 
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
	public static String StudyProjectSaveBaseInfo(String title,String class_id,String cover,String base_cover) {		
		return HttpRequest.post(study_projects_save_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"base_info\",\r\n" + 
				"      \"base_info\": {\r\n" + 
				"            \"title\": \""+title+"\",\r\n" + 
				"            \"lecturer_id\": 0,\r\n" + 
				"            \"summary\": \"\",\r\n" + 
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
	public static String StudyProjectSaveStageContent(String project_id,String course_id) {		
		return HttpRequest.post(study_projects_save_url).query("access_token", token).data("{\r\n" + 
				"      \"save_step\": \"stage_content\",\r\n" + 
				"      \"id\": \""+project_id+"\",\r\n" + 
				"      \"stage_content\": {\r\n" + 
				"            \"stage_pass\": \"false\",\r\n" + 
				"            \"stages\": [\r\n" + 
				"                  {\r\n" + 
				"                        \"content\": \"\",\r\n" + 
				"                        \"stage_name\": \"阶段一\",\r\n" + 
				"                        \"is_order\": false,\r\n" + 
				"                        \"sort\": 1,\r\n" + 
				"                        \"resources\": [\r\n" + 
				"                              {\r\n" + 
				"                                    \"course_id\": \""+course_id+"\",\r\n" + 
				"                                    \"sort\": 0,\r\n" + 
				"                                    \"type\": 3,\r\n" + 
				"                                    \"resource_lock\": false\r\n" + 
				"                              }\r\n" + 
				"                        ]\r\n" + 
				"                  }\r\n" + 
				"            ],\r\n" + 
				"            \"exam_lock\": \"done\",\r\n" + 
				"            \"sync_progress\": \"false\"\r\n" + 
				"      },\r\n" + 
				"      \"access_token\": \"9751c7afcdd485ee21cb3e2ea3ed04dd\"\r\n" + 
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
				"            \"progress\": 0,\r\n" + 
				"            \"times\": \"\",\r\n" + 
				"            \"operation_times\": 1,\r\n" + 
				"            \"finish_evaluation\": false,\r\n" + 
				"            \"certificate_id\": \"\",\r\n" + 
				"            \"is_get_score\": true,\r\n" + 
				"            \"study_score\": {\r\n" + 
				"                  \"finish_score\": 3,\r\n" + 
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
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
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
	 * @Title: StudyProjectsQuery   
	 * @Description: TODO  学习平台知识库下查询学习项目
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String StudyProjectsQuery(String title) {
		return HttpRequest.get(study_projects_query_url).query("access_token", token).query("title",title)
				.query("classifyType","all")
				.send().body();	
	}
	
	
	/**   
	 * @Title: ProjectSaveProcess  
	 * @Description: TODO  完成学习项目
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ProjectSaveProcess(String projectCourse_id,String course_id,String tempTime) {		
	    return HttpRequest.post(project_save_progress_url(projectCourse_id,course_id)).query("access_token", token).data("{\r\n" + 
	    		"      \"progress\": 100,\r\n" + 
	    		"      \"recent_start\": 0,\r\n" + 
	    		"      \"tempTime\": \""+tempTime+"\",\r\n" + 
	    		"      \"access_token\": \""+token+"\"\r\n" + 
	    		"}")
	    		.send().body();
	}
	
	/**   
	 * @Title: StartStudy   
	 * @Description: TODO  进入岗位测评前的start
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String StartStudy(String project_id) {
		return HttpRequest.post(start_study_url(project_id)).query("access_token", token)
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
	 * @Title: AddQuestionnaire  
	 * @Description: TODO    新建问卷
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AddQuestionnaire(String title,String classid) {		
		return HttpRequest.post(add_questionnaire_url).query("access_token", token).data("{\r\n" + 
				"      \"title\": \""+title+"\",\r\n" + 
				"      \"course_classifys\": [\r\n" + 
				"            \""+classid+"\"\r\n" + 
				"      ],\r\n" + 
				"      \"description\": \"test\",\r\n" + 
				"      \"type\": \"release\",\r\n" + 
				"      \"visible_range\": \"all\",\r\n" + 
				"      \"department_ids\": \"\",\r\n" + 
				"      \"post_ids\": \"\",\r\n" + 
				"      \"group_ids\": \"\",\r\n" + 
				"      \"user_ids\": \"\",\r\n" + 
				"      \"questions\": [\r\n" + 
				"            {\r\n" + 
				"                  \"title\": \"单选\",\r\n" + 
				"                  \"type\": \"single\",\r\n" + 
				"                  \"order_id\": 1,\r\n" + 
				"                  \"must\": \"false\",\r\n" + 
				"                  \"other\": false,\r\n" + 
				"                  \"options\": [\r\n" + 
				"                        {\r\n" + 
				"                              \"order_id\": 1,\r\n" + 
				"                              \"title\": \"选项1\",\r\n" + 
				"                              \"other\": false\r\n" + 
				"                        },\r\n" + 
				"                        {\r\n" + 
				"                              \"order_id\": 2,\r\n" + 
				"                              \"title\": \"选项2\",\r\n" + 
				"                              \"other\": false\r\n" + 
				"                        }\r\n" + 
				"                  ]\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	/**   
	 * @Title: CopyQuestionnaire  
	 * @Description: TODO     复制问卷
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CopyQuestionnaire(String questionnaire_id) {		
		return HttpRequest.get(copy_questionnaire_url(questionnaire_id)).query("access_token", token).send().body();
	}
	
	
	/**   
	 * @Title: DeleteQuestionnaire  
	 * @Description: TODO     删除问卷
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteQuestionnaire(String questionnaire_id) {		
		return HttpRequest.post(delete_questionnaire_url(questionnaire_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: AddInvestigate_url  
	 * @Description: TODO     新建调研任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AddInvestigate(String questionnaire_id,String title) {		
		return HttpRequest.post(add_investigate_url).query("access_token", token).data("{\r\n" + 
				"      \"actor_type\": \"part\",\r\n" + 
				"      \"anonymous_string\": \"true\",\r\n" + 
				"      \"open_result_string\": \"false\",\r\n" + 
				"      \"group_ids\": \"\",\r\n" + 
				"      \"department_ids\": \"\",\r\n" + 
				"      \"user_ids\": \""+user_id+"\",\r\n" + 
				"      \"post_ids\": \"\",\r\n" + 
				"      \"questionnaire_id\": \""+questionnaire_id+"\",\r\n" + 
				"      \"term_type\": \"permanent\",\r\n" + 
				"      \"start_time\": \"\",\r\n" + 
				"      \"stop_time\": \"\",\r\n" + 
				"      \"type\": \"release\",\r\n" + 
				"      \"title\": \""+title+"\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	/**   
	 * @Title: QueryQuestionnaire  
	 * @Description: TODO     查询调研任务信息
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryQuestionnaire(String questionnaire_id,String investigate_id) {		
		return HttpRequest.get(questionnaires_query_url(questionnaire_id,investigate_id)).query("access_token", token)
				.send().body();
	}
	
	/**   
	 * @Title: SubmitInvestigate  
	 * @Description: TODO     提交调研任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SubmitInvestigate(String researches_id) {		
		return HttpRequest.post(submit_investigate_url(researches_id)).query("access_token", token).data("{\r\n" + 
				"	\"questions\": [{\r\n" + 
				"		\"id\": \""+researches_id+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \"test\",\r\n" + 
				"			\"other_answer\": \"\"\r\n" + 
				"		}\r\n" + 
				"	}]\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: DeleteInvestigate  
	 * @Description: TODO     删除调研任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteInvestigate(String investigate_id) {		
		return HttpRequest.post(delete_investigate_url(investigate_id)).query("access_token", token)
				.send().body();
	}
	
	/**   
	 * @Title: QueryQuestionBankList  
	 * @Description: TODO  查询题库id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryQuestionBankList(String keyword){	
		return HttpRequest.get(queryQuestionBankList_url).query("access_token", token).query("keyword",keyword)
				.send().body();
	}
	
	
	
	/**   
	 * @Title: ExamAdd  
	 * @Description: TODO  创建考试任务-随机试卷最高成绩无限重考
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ExamAdd(String beginTime,String endTime,String questionBankId,String title) throws Exception{	
		return HttpRequest.post(exam_add_url).query("access_token", token).query("anonymousMarking","undefined")
				.query("beginTime",URLEncoder.encode(beginTime, "UTF-8"))
				.query("endTime",URLEncoder.encode(endTime, "UTF-8"))
				.query("answerParsing","5").query("authorityRange","false").query("cheatFlag","0").query("cutScreenCount","-1")
				.query("examCertificateId","0").query("examDuration","20").query("faceRecognition","off").query("fillBlankCount","0")
				.query("fillBlankType","1").query("fillBlankUnitScore","0").query("isAllIn","false").query("isGetScore","false")
				.query("markType","1").query("multipleCount","0").query("multipleUnitScore","0").query("passLine","100")
				.query("passingScore","100").query("questionBankId",questionBankId).query("questionMode","2").query("reExamNumber","0")
				.query("reExamRule","1").query("repeatExam","true").query("scoreRule","1").query("shortAnswerCount","0")
				.query("shortAnswerType","1").query("shortAnswerUnitScore","0").query("showKnowledge","show").query("singleCount","1")
				.query("singleUnitScore","10").query("supervisorId",user_id).query("supervisorPaper","true").query("title",title)
				.query("trueOrFalseCount","0").query("trueOrFalseUnitScore","0").query("userIds",user_id).query("viewRule","1")				
				.send().body();
	}
	
	
	/**   
	 * @Title: QueryExams  
	 * @Description: TODO  考试任务答题前查询接口
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryExams(String examPlan_id){	
		return HttpRequest.get(query_exams_url(examPlan_id)).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: SubmitExam  
	 * @Description: TODO  提交试卷
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SubmitExam(String examPlan_id,String question_id){	
		return HttpRequest.post(submit_exam_url(examPlan_id)).query("access_token", token).data("{\r\n" + 
				"      \"questions\": [\r\n" + 
				"            {\r\n" + 
				"                  \"id\": \""+question_id+"\",\r\n" + 
				"                  \"answer\": [],\r\n" + 
				"                  \"answer_image\": \"\"\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"makeup_count\": 0,\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: StudyPlanExamId  
	 * @Description: TODO  查询试卷下的各试题id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryQuestionId(String studyPlan_ExamId){	
		return HttpRequest.get(question_id_url(studyPlan_ExamId)).query("access_token", token)
				.send().body();
	}
	
	/**   
	 * @Title: ExamResult  
	 * @Description: TODO  考试任务数据监控-详情页
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ExamResult(String studyPlan_ExamId){	
		return HttpRequest.get(exam_result_url(studyPlan_ExamId)).query("access_token", token).query("userId",user_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: DelateExam  
	 * @Description: TODO  删除考试任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DelateExam(String exam_plan_id){	
		return HttpRequest.post(delete_exam_url).query("access_token", token).query("id",exam_plan_id)
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
	 * @Title: EvaluationSave  
	 * @Description: TODO  创建测评任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationSave(String title,Integer product_id,String start_time,String end_time) {		
		return HttpRequest.post(evaluation_save_url).query("access_token", token).data("{\r\n" + 
				"      \"title\": \""+title+"\",\r\n" + 
				"      \"product_id\": \""+product_id+"\",\r\n" + 
				"      \"term_type\": \"permanent\",\r\n" + 
				"      \"start_time\": \""+start_time+"\",\r\n" + 
				"      \"end_time\": \"0\",\r\n" + 
				"      \"supervisor_Id\": \""+user_id+"\",\r\n" + 
				"      \"source\": \"evaluation\",\r\n" + 
				"      \"user_ids\": \""+user_id+"\",\r\n" + 
				"      \"department_ids\": \"\",\r\n" + 
				"      \"group_ids\": \"\",\r\n" + 
				"      \"post_ids\": \"\",\r\n" + 
				"      \"authority_range\": false,\r\n" + 
				"      \"status\": \"1\",\r\n" + 
				"      \"model_id\": \"\",\r\n" + 
				"      \"finish_promote\": false,\r\n" + 
				"      \"certificate_id\": \"\",\r\n" + 
				"      \"is_get_score\": \"false\",\r\n" + 
				"      \"study_score\": {\r\n" + 
				"            \"finish_score\": 1\r\n" + 
				"      },\r\n" + 
				"      \"evaluation_tool_type\": \"tool\",\r\n" + 
				"      \"evaluation_way_self\": \"false\",\r\n" + 
				"      \"evaluation_way_superior\": \"false\",\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	
	/**   
	 * @Title: EvaluationList
	 * @Description: TODO  移动端-测评任务列表-未完成列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationList(String keyword,String status) {		
	    return HttpRequest.get(my_evaluation_list_url).query("access_token", token).query("keyword",keyword).query("status",status)
	    		.send().body();
	}
	
	
	/**   
	 * @Title: EvaluationUnpublish  
	 * @Description: TODO  取消测评任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationUnpublish(String evaluation_id) {		
		return HttpRequest.post(evaluation_unpublish_url(evaluation_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: EvaluationDelete  
	 * @Description: TODO  删除测评任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String EvaluationDelete(String evaluation_id) {		
		return HttpRequest.post(evaluation_delete_url(evaluation_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	
	/**
	 * @throws UnsupportedEncodingException    
	 * @Title: DeleteStudy  
	 * @Description: TODO 添加岗位认证
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QualificationsAdd(String certificateId,String scopes,String stageJson,String title) throws UnsupportedEncodingException {		
		return HttpRequest.post(qualifications_add_url).query("access_token", token).query("certificateId",certificateId)
				.query("scopes",URLEncoder.encode(scopes, "utf-8")).query("stageJson",URLEncoder.encode(stageJson, "utf-8"))
				.query("stagePass","false").query("studyTimeLimit","0").query("summary","test").query("title",title)
				.query("visibleRange","part").send().body();
	}
	
	
	
	/**   
	 * @Title: AddPostMap  
	 * @Description: TODO  新建岗位地图
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String AddPostMap(String qualifications_id1,String qualifications_id2,String map_template,String cover,String title) {		
		return HttpRequest.post(postmap_add_url).query("access_token", token).data("{\r\n" + 
				"      \"checkpoint_unlock\": \"pass\",\r\n" + 
				"      \"checkpoints\": [\r\n" + 
				"            {\r\n" + 
				"                  \"qualification_id\": \""+qualifications_id1+"\",\r\n" + 
				"                  \"score\": 1,\r\n" + 
				"                  \"sort\": 1\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"                  \"qualification_id\": \""+qualifications_id2+"\",\r\n" + 
				"                  \"score\": 2,\r\n" + 
				"                  \"sort\": 2\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"map_template\": \""+map_template+"\",\r\n" + 
				"      \"cover\": \""+cover+"\",\r\n" +
				"      \"introduction\": \"test\",\r\n" + 
				"      \"is_extra_score\": true,\r\n" + 
				"      \"name\": \""+title+"\",\r\n" + 
				"      \"send_edit_message\": false,\r\n" + 
				"      \"visible_range\": \"part\",\r\n" + 
				"      \"scopes\": {\r\n" + 
				"            \"department_list\": [],\r\n" + 
				"            \"group_list\": [],\r\n" + 
				"            \"post_list\": [],\r\n" + 
				"            \"user_list\": [\r\n" + 
				"                  {\r\n" + 
				"                        \"id\": \""+user_id+"\"\r\n" + 
				"                  }\r\n" + 
				"            ]\r\n" + 
				"      },\r\n" + 
				"      \"checkpoint_count\": 2,\r\n" + 
				"      \"checkpoint_score\": 10,\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: MyPostMap
	 * @Description: TODO  移动端-岗位地图列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String MyPostMap() {		
	    return HttpRequest.get(my_postmap_list).query("access_token", token)
	    		.send().body();
	}
	
	
	/**   
	 * @Title: UpdatePostMap
	 * @Description: TODO  停用岗位地图
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UpdatePostMap(String postmap_id,String status) {		
	    return HttpRequest.post(update_postmap_url(postmap_id)).query("access_token", token).data("{\r\n" + 
	    		"      \"status\": \""+status+"\",\r\n" + 
	    		"      \"access_token\": \""+token+"\"\r\n" + 
	    		"}")
	    		.send().body();
	}
	
	/**   
	 * @Title: DaletePostMap
	 * @Description: TODO  删除岗位地图
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DaletePostMap(String postmap_id) {		
	    return HttpRequest.post(delete_postmap_url(postmap_id)).query("access_token", token).data("{\r\n" + 
	    		"      \"access_token\": \""+token+"\"\r\n" + 
	    		"}")
	    		.send().body();
	}
	
	
	/**   
	 * @Title: CertificateList
	 * @Description: TODO  证书列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CertificateList(String keyword) {		
	    return HttpRequest.get(certificate_list_url).query("access_token", token).query("keyword",keyword)
	    		.send().body();
	}
	
	
	/**   
	 * @Title: SaveOrUpdateBaseInfo  
	 * @Description: TODO  新员工培训第一步
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SaveOrUpdateBaseInfo(String train_name) {	
		String current_time = String.valueOf(System.currentTimeMillis());
		return HttpRequest.post(save_or_update_base_info_url).query("access_token", token).data("{\r\n" + 
				"  \"train_name\":\""+train_name+"\",\r\n" + 
				"  \"train_limit\":\"2\",\r\n" + 
				"  \"summary\":\"\",\r\n" + 
				"  \"classify_id\":\"\",\r\n" + 
				"  \"effect_time\":"+current_time+",\r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
		 
		
	}
	
	/**   
	 * @Title: UpdateContent  
	 * @Description: TODO  新员工培训第二步
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UpdateContent(String employee_train_id,String course_id) {
		return HttpRequest.post(update_content_url).query("access_token", token).data("{\r\n" + 
				"      \"plan_id\": \""+employee_train_id+"\",\r\n" + 
				"      \"stage_json\": [\r\n" + 
				"            {\r\n" + 
				"                  \"content\": \"\",\r\n" + 
				"                  \"stage_name\": \"阶段1\",\r\n" + 
				"                  \"is_order\": false,\r\n" + 
				"                  \"stage_sort\": 1,\r\n" + 
				"                  \"resources\": [\r\n" + 
				"                        {\r\n" + 
				"                              \"course_id\": \""+course_id+"\",\r\n" + 
				"                              \"course_sort\": 0,\r\n" + 
				"                              \"course_type\": 3,\r\n" + 
				"                              \"flag\": 1\r\n" + 
				"                        }\r\n" + 
				"                  ]\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"stage_pass\": false,\r\n" + 
				"      \"sync_progress\": \"false\",\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	/**   
	 * @Title: UpdateSettingPublish  
	 * @Description: TODO  新员工培训第三步
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UpdateSettingPublish(String employee_train_id) {		
		return HttpRequest.post(update_setting_publish_url).query("access_token", token).data("{\r\n" + 
				"    \"plan_id\": \""+employee_train_id+"\",\r\n" + 
				"    \"save_step\": 3,\r\n" + 
				"    \"override_sub_dept\": 1,\r\n" + 
				"    \"is_new_employee\": false,\r\n" + 
				"    \"department_ids\": \"1\",\r\n" + 
				"    \"post_ids\": \"\",\r\n" + 
				"    \"study_time_limit\": 0,\r\n" + 
				"    \"face_recognition\": \"off\",\r\n" + 
				"    \"progress\": 100,\r\n" + 
				"    \"times\": \"\",\r\n" + 
				"    \"operation_times\": 1,\r\n" + 
				"    \"certificate_id\": \"\",\r\n" + 
				"    \"study_score\": \"{\\\"finishScore\\\":0,\\\"unFinishScore\\\":0}\",\r\n" + 
				"    \"supervisor_id\": \""+user_id+"\",\r\n" + 
				"    \"supervisor_paper\": true,\r\n" + 
				"    \"is_get_score\": true,\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: ExportTrainMonitor
	 * @Description: TODO  新员工任务导出
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ExportTrainMonitor(String employee_train_id) {		
	    return HttpRequest.get(exportTrainMonitor_url).query("access_token", token).query("queryTaskInfo","false").query("taskStatus","0")
	    		.query("planId",employee_train_id)
	    		.send().body();
	}
	
	
	/**   
	 * @Title: DeleteEmployTrainTask  
	 * @Description: TODO  删除新员工培训任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String DeleteEmployTrainTask(String planId) {
		return HttpRequest.post(delete_employ_train_task).query("access_token", token).query("planId",planId).
				send().body();
	}
	
	
	/**   
	 * @Title: DeleteQualifications  
	 * @Description: TODO  删除岗位认证
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String DeleteQualifications(String qualifications_id) {
		return HttpRequest.post(delete_qualifications_url(qualifications_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}").
				send().body();
	}
	
	
	/**   
	 * @Title: QualificationsItems  
	 * @Description: TODO  获取岗位认证信息
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String QualificationsItems(String qualifications_id) {
		return HttpRequest.get(qualifications_items_url(qualifications_id)).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: LoadResources  
	 * @Description: TODO  加载岗位认证资源
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String LoadResources(String qualifications_id) {
		return HttpRequest.post(load_resources_url(qualifications_id)).query("access_token", token)
				.send().body();
	}

	
	/**   
	 * @Title: CheckAward  
	 * @Description: TODO  岗位地图判断是否可以获得额外学分
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String CheckAward(String postmap_id) {
		return HttpRequest.get(check_award_url(postmap_id)).query("access_token", token)
				.send().body();
	}
	
	
	/**   
	 * @Title: GetAward  
	 * @Description: TODO  岗位地图获取额外学分
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String GetAward(String postmap_id) {
		return HttpRequest.post(get_award_url(postmap_id)).query("access_token", token).data("{\r\n" + 
				"      \"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	
	/**
	 * @throws UnsupportedEncodingException    
	 * @Title: GetScoreRank  
	 * @Description: TODO  查询学分排行榜
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String GetScoreRank(String startTime,String endTime){
		return HttpRequest.get(getScoreRank_url).query("access_token", token).query("startTime",startTime)
				.query("endTime",endTime)
				.send().body();
	}
	
	
	/**   
	 * @Title: QualificationsExamId  
	 * @Description: TODO 获取岗位认证-考试id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QualificationsExamId(String qualifications_id) {		
		return HttpRequest.get(qualifications_exam_url(qualifications_id)).query("access_token", token).send().body();
	}
	
	
	/**   
	 * @Title: QueryQualificationsState  
	 * @Description: TODO 查询岗位认证状态
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryQualificationsState(String qualifications_id) {		
		return HttpRequest.get(qualifications_state_url(qualifications_id)).query("access_token", token).send().body();
	}
	
	
	
	/**
	 * @throws UnsupportedEncodingException    
	 * @Title: MarkingPaper  
	 * @Description: TODO    人工阅卷
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String MarkingPaper(String planId,String questionJsonList) throws UnsupportedEncodingException {		
		return HttpRequest.post(markingPaper_url).query("access_token", token).query("planId",planId)
				.query("questionJsonList",URLEncoder.encode(questionJsonList, "utf-8")).query("userId",user_id)
				.send().body();
	}
	
	/**   
	 * @Title: QueryPostmapExam  
	 * @Description: TODO    获取岗位地图里的考试时长
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QueryPostmapExam(String exam_qualifications_id) {		
		return HttpRequest.get(query_postmap_exam_url(exam_qualifications_id)).query("access_token", token).send().body();
	}

	
	
	
}
