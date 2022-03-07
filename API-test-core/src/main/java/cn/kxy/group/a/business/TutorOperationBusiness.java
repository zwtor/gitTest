package cn.kxy.group.a.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TutorOperationBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String tutor_url = EnterpriseDataUrl.getTutorsUrl();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	
	public static String study_add_url = enterprise_url + "plan/study/add/";
	public static String myTask_getList_url = enterprise_url + "plan/myTask/getList/";
	public static String myTask_operationId_url = enterprise_url + "plan/myTask/getOne/";
	
	public static String lever_list_url = tutor_url + "v1/"+enterpriseId+"/tutors/levels/list/";
	public static String tutors_add_url = tutor_url + "v1/"+enterpriseId+"/tutors/add/";
	public static String apprentice_add_url (String tutor_id) {
		return tutor_url + "v1/"+enterpriseId+"/tutors/"+"/"+tutor_id+"/"+"/apprentice/add/";
	}
	public static String update_status_url (String tutor_id) {
		return tutor_url + "v1/"+enterpriseId+"/tutors/"+"/"+tutor_id+"/"+"/update/status/";
	}
	public static String tutor_list_url = tutor_url + "v1/"+enterpriseId+"/tutors/list/";
	public static String apprentices_list_url (String tutor_id) {
		return tutor_url + "v1/"+enterpriseId+"/tutors/"+"/"+tutor_id+"/"+"/apprentices/list/";
	}
	public static String apprentices_delete_url (String apprentices_id) {
		return tutor_url + "v1/"+enterpriseId+"/apprentices/"+"/"+apprentices_id+"/"+"/delete/";
	}
	public static String tutor_delete_url (String tutor_id) {
		return tutor_url + "v1/"+enterpriseId+"/tutors/"+tutor_id+"/delete/";
	} 
	
	public static String operations_tutors_url(String myTaskoperation_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id +"/operations/"+myTaskoperation_id;
	} 
	
	public static String operation_save_url(String myTaskoperation_id) {
		return enterprise_url + "v2/"+enterpriseId+"/users/"+user_id +"/operations/"+myTaskoperation_id +"/save/";
	}
	
	public static String approval_url(String myTaskoperation_id) {
		return enterprise_url + "v2/"+enterpriseId +"/operations/"+myTaskoperation_id + "/approval/";
	}
	public static String submit_operation_url(String approval_id) {
		return enterprise_url + "v2/"+enterpriseId +"/operations/"+approval_id;
	}
	public static String delete_study_url = enterprise_url + "plan/study/delete/";
	
	public static String study_projects_save_url = enterprise_url + "v2/"+enterpriseId +"/study_projects/save/";
	
	public static String study_projects_query_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_projects/"+project_id+"/query/";
	}
	public static String study_project_delete_url (String project_id) {
		return enterprise_url + "v2/"+enterpriseId +"/study_projects/"+project_id+"/delete/";
	}
	public static String qualifications_add_url = enterprise_url + "v1/qualifications/";
	public static String certificate_list_url = enterprise_url + "certificate/getList/";
	public static String qualifications_items_url (String qualifications_id){
		return enterprise_url + "v2/"+enterpriseId +"/qualifications/"+qualifications_id+"/items/";
	}
	public static String qualifications_opertion_save_url (String qualifications_opertion_id) {
		return enterprise_url + "v2/"+enterpriseId +"/users/"+user_id+"/operations/" + qualifications_opertion_id +"/save/";
	}
	public static String submit_qualifications_opertion_url (String qualifications_approval_id) {
		return enterprise_url + "v2/"+enterpriseId +"/operations/"+qualifications_approval_id;
		
	}
	public static String detele_qualifications_url (String qualifications_id) {
		return enterprise_url + "v1/qualifications/"+qualifications_id +"/delete/";
	}
		

		
	/**   
	 * @Title: StudyAdd  
	 * @Description: TODO  创建学习任务
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyAdd(String title,String type) {		
		return HttpRequest.post(study_add_url).query("access_token", token).data("{\r\n" + 
				"      \"title\": \""+title+"\",\r\n" + 
				"      \"beginTime\": "+DateUtil.getTimeStamp(-7, "")+",\r\n" + 
				"      \"endTime\": "+DateUtil.getTimeStamp(9, "")+",\r\n" +
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
				"      \"progress\": 0,\r\n" + 
				"      \"studyScore\": {\r\n" + 
				"            \"finishScore\": 0,\r\n" + 
				"            \"unFinishScore\": 0\r\n" + 
				"      },\r\n" + 
				"      \"scheduleSetting\": \"free_mode\",\r\n" + 
				"      \"studyTimeLimit\": 0,\r\n" + 
				"      \"faceRecognition\": \"off\",\r\n" + 
				"      \"supervisorId\": \""+user_id+"\",\r\n" + 
				"      \"supervisorPaper\": true,\r\n" + 
				"      \"authorityRange\": false,\r\n" + 
				"      \"times\": \"\",\r\n" + 
				"      \"operationTimes\": 1,\r\n" + 
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
				"                              \"courseType\": 10,\r\n" + 
				"                              \"courseSort\": 0,\r\n" + 
				"                              \"flag\": 5,\r\n" + 
				"                              \"type\": \"sc\",\r\n" + 
				"                              \"id\": \"\",\r\n" + 
				"                              \"title\": \""+title+"\",\r\n" + 
				"                              \"content\": \"test content\",\r\n" + 
				"                              \"resources\": [],\r\n" + 
				"                              \"scoreSetting\": {\r\n" + 
				"                                    \"scoreSwitch\": \"off\",\r\n" + 
				"                                    \"score\": 100\r\n" + 
				"                              },\r\n" + 
				"                              \"workflowJson\": {\r\n" + 
				"                                    \"type\": \""+type+"\",\r\n" + 
				"                                    \"userIds\": []\r\n" + 
				"                              },\r\n" + 
				"                              \"mappingId\": \"\"\r\n" + 
				"                        }\r\n" + 
				"                  ]\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"stagePass\": false,\r\n" + 
				"      \"finishEvaluation\": false,\r\n" + 
				"      \"openEvaluationResult\": false,\r\n" + 
				"      \"action\": \"published\",\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();

	}
	
	/**   
	 * @Title: MyTaskGetList   
	 * @Description: TODO  学员端-我的学习任务列表
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String MyTaskGetList(String keyword) {
		return HttpRequest.get(myTask_getList_url).query("pageNumber","1").query("pageSize","20").query("keyword",keyword)
				.query("access_token", token).send().body();	
	}
	
	
	/**   
	 * @Title: MyTaskOperation 
	 * @Description: TODO  学员端-我的学习任务下实操作业
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String MyTaskOperation(String mytask_id) {
		return HttpRequest.get(myTask_operationId_url).query("id",mytask_id)
				.query("access_token", token).send().body();	
	}
	
	
	/**   
	 * @Title: OperationsTutors   
	 * @Description: TODO  提交实操作业-选择导师
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String OperationsTutors(String myTaskoperation_id) {
		return HttpRequest.get(operations_tutors_url(myTaskoperation_id)).query("access_token", token).send().body();	
	}
	
	/**   
	 * @Title: OperationSave  
	 * @Description: TODO 提交实操作业-提交
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String OperationSave(String approval_user,String myTaskoperation_id,String status) {		
		return HttpRequest.post(operation_save_url(myTaskoperation_id)).query("access_token", token).query("status",status).data("{\r\n" + 
				"      \"approval_user\": \""+approval_user+"\",\r\n" + 
				"      \"operation\": [\r\n" + 
				"            {\r\n" + 
				"                  \"content\": \"operation save content\",\r\n" + 
				"                  \"step\": 1,\r\n" + 
				"                  \"resources\": []\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"operation_source\": \"study\",\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();

	}
	
	/**   
	 * @Title: Approval  
	 * @Description: TODO 获取审批实操作业的入参id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String Approval(String myTaskoperation_id) {		
		return HttpRequest.get(approval_url(myTaskoperation_id)).query("access_token", token).query("submit_user_id",user_id)
				.send().body();
	}
	
	
	/**   
	 * @Title: SubmitOperation  
	 * @Description: TODO 审批实操作业
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SubmitOperation(String approval_id) {		
		return HttpRequest.post(submit_operation_url(approval_id)).query("access_token", token).data("{\r\n" + 
				"      \"suggest\": \"test suggset\",\r\n" + 
				"      \"score\": 0,\r\n" + 
				"      \"status\": 3,\r\n" + 
				"      \"resources\": [],\r\n" + 
				"      \"operation_source\": \"study\",\r\n" + 
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
	public static String StudyProjectSaveStageContent(String project_id) {		
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
				"                                    \"type\": 10,\r\n" + 
				"                                    \"courseSort\": 0,\r\n" + 
				"                                    \"sort\": 0,\r\n" + 
				"                                    \"flag\": 4,\r\n" + 
				"                                    \"course_id\": \"\",\r\n" + 
				"                                    \"title\": \"导师审批的实操作业\",\r\n" + 
				"                                    \"content\": \"content test\",\r\n" + 
				"                                    \"resources\": [],\r\n" + 
				"                                    \"scoreSetting\": {\r\n" + 
				"                                          \"scoreSwitch\": \"off\",\r\n" + 
				"                                          \"score\": 100\r\n" + 
				"                                    },\r\n" + 
				"                                    \"workflowJson\": {\r\n" + 
				"                                          \"type\": \"tutor\",\r\n" + 
				"                                          \"userIds\": []\r\n" + 
				"                                    }\r\n" + 
				"                              }\r\n" + 
				"                        ]\r\n" + 
				"                  }\r\n" + 
				"            ]\r\n" + 
				"      },\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
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
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	
	/**   
	 * @Title: StudyProjectsQuery  
	 * @Description: TODO 学习项目指派时查询
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyProjectsQuery(String project_id) {		
		return HttpRequest.get(study_projects_query_url(project_id)).query("access_token", token).send().body();
	}
	
	
	/**   
	 * @Title: StudyPlanAdd  
	 * @Description: TODO  学习项目的指派
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyPlanAdd(String title,String class_id,String course_id) {		
		return HttpRequest.post(study_add_url).query("access_token", token).data("{\r\n" + 
				"      \"title\": \""+title+"\",\r\n" + 
				"      \"beginTime\": "+DateUtil.getTimeStamp(-7, "")+",\r\n" + 
				"      \"endTime\": "+DateUtil.getTimeStamp(9, "")+",\r\n" +
				"      \"summary\": \"\",\r\n" + 
				"      \"classify_id\": \""+class_id+"\",\r\n" + 
				"      \"dingImgUrl\": \"\",\r\n" + 
				"      \"departmentIds\": \"\",\r\n" + 
				"      \"supervisor_department_ids\": [],\r\n" + 
				"      \"groupIds\": \"\",\r\n" + 
				"      \"isGetScore\": true,\r\n" + 
				"      \"isNoticeAll\": 0,\r\n" + 
				"      \"planCertificateId\": \"\",\r\n" + 
				"      \"postIds\": \"\",\r\n" + 
				"      \"progress\": 0,\r\n" + 
				"      \"studyScore\": {\r\n" + 
				"            \"finishScore\": 0,\r\n" + 
				"            \"unFinishScore\": 0\r\n" + 
				"      },\r\n" + 
				"      \"scheduleSetting\": \"free_mode\",\r\n" + 
				"      \"studyTimeLimit\": 0,\r\n" + 
				"      \"faceRecognition\": \"off\",\r\n" + 
				"      \"supervisorId\": \""+user_id+"\",\r\n" + 
				"      \"supervisorPaper\": true,\r\n" + 
				"      \"authorityRange\": false,\r\n" + 
				"      \"times\": \"\",\r\n" + 
				"      \"operationTimes\": 1,\r\n" + 
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
				"                              \"courseType\": 10,\r\n" + 
				"                              \"courseSort\": 0,\r\n" + 
				"                              \"flag\": 5,\r\n" + 
				"                              \"type\": \"sc\",\r\n" + 
				"                              \"id\": \""+course_id+"\",\r\n" + 
				"                              \"title\": \""+title+"\",\r\n" + 
				"                              \"content\": \"content test\",\r\n" + 
				"                              \"resources\": [],\r\n" + 
				"                              \"scoreSetting\": {\r\n" + 
				"                                    \"scoreSwitch\": \"off\",\r\n" + 
				"                                    \"score\": 100\r\n" + 
				"                              },\r\n" + 
				"                              \"workflowJson\": {\r\n" + 
				"                                    \"type\": \"tutor\",\r\n" + 
				"                                    \"userIds\": []\r\n" + 
				"                              },\r\n" + 
				"                              \"mappingId\": \"\"\r\n" + 
				"                        }\r\n" + 
				"                  ]\r\n" + 
				"            }\r\n" + 
				"      ],\r\n" + 
				"      \"stagePass\": false,\r\n" + 
				"      \"finishEvaluation\": false,\r\n" + 
				"      \"openEvaluationResult\": false,\r\n" + 
				"      \"action\": \"published\",\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
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
	 * @Title: CertificateGetList  
	 * @Description: TODO 获取证书列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CertificateGetList() {		
		return HttpRequest.get(certificate_list_url).query("access_token", token).send().body();
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
	 * @Title: QualificationsItems  
	 * @Description: TODO 移动端-获取实操作业提交里的id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QualificationsItems(String qualifications_id) {
		return HttpRequest.get(qualifications_items_url(qualifications_id)).query("access_token", token).send().body();
	}
	
	
	/**   
	 * @Title: QualificationsOpertionSave  
	 * @Description: TODO 移动端提交实操作业
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String QualificationsOpertionSave(String qualifications_opertion_id,String approval_user) {		
		return HttpRequest.post(qualifications_opertion_save_url(qualifications_opertion_id)).query("access_token", token)
				.query("status","1").data("{\r\n" + 
				"	\"operation\": [{\r\n" + 
				"		\"content\": \"test\",\r\n" + 
				"		\"step\": 1,\r\n" + 
				"		\"resources\": []\r\n" + 
				"	}],\r\n" + 
				"	\"operation_source\": \"qualification\",\r\n" + 
				"	\"approval_user\": \""+approval_user+"\"\r\n" +   //approval_user是导师对应的user_id,导师列表接口返回该字段
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: SubmitQualificationsOpertion  
	 * @Description: TODO 移动端审批实操作业
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String SubmitQualificationsOpertion(String qualifications_approval_id) {		
		return HttpRequest.post(submit_qualifications_opertion_url(qualifications_approval_id)).query("access_token", token).data("{\r\n" + 
				"	\"suggest\": \"test\",\r\n" + 
				"	\"score\": 0,\r\n" + 
				"	\"status\": 3,\r\n" + 
				"	\"resources\": [],\r\n" + 
				"	\"operation_source\": \"qualification\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: DeleteQualifications  
	 * @Description: TODO 删除岗位认证
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String DeleteQualifications(String qualifications_id) {		
		return HttpRequest.post(detele_qualifications_url(qualifications_id)).query("access_token", token).send().body();
	}
	
	
	/**   
	 * @Title: LeverList  
	 * @Description: TODO  查询导师等级列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String LeverList( ) {	
		return HttpRequest.get(lever_list_url).query("access_token", token).send().body();
	}
	
	
	/**   
	 * @Title: TutorsLeverAdd  
	 * @Description: TODO  创建导师
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String TutorsAdd(String level_id) {	
		return HttpRequest.post(tutors_add_url).query("access_token", token).data("{\r\n" + 
				"  \"user_id\":\""+user_id+"\",\r\n" + 
				"  \"gender\":\"female\",\r\n" + 
				"  \"level_id\":\""+level_id+"\",\r\n" + 
				"  \"description\":\"\",\r\n" + 
				"  \"avatar\":\"\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	
	/**   
	 * @Title: ApprenticeAdd  
	 * @Description: TODO  添加带教人员接口
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ApprenticeAdd(String tutor_id) {
		return HttpRequest.post(apprentice_add_url(tutor_id)).query("access_token", token).data("{\r\n" + 				
				"  \"user_ids\": [\r\n" + 
				"    \""+user_id+"\"\r\n" + 
				"  ], \r\n" + 				
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	
	/**   
	 * @Title: UpDateStatus  
	 * @Description: TODO  开启/停用导师
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UpDateStatus(String tutor_id,String status) {	
		return HttpRequest.post(update_status_url(tutor_id)).query("access_token", token).data("{\r\n" + 
				"  \"status\":\""+status+"\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: TutorList  
	 * @Description: TODO  查询导师列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String TutorList( ) {	
		return HttpRequest.get(tutor_list_url).query("access_token", token).
				send().body();
	}
	
	/**   
	 * @Title: ApprenticesList  
	 * @Description: TODO  查询导师带教关系
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ApprenticesList(String tutor_id) {	
		return HttpRequest.get(apprentices_list_url(tutor_id)).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: ApprenticesDelete  
	 * @Description: TODO  删除导师带教关系
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ApprenticesDelete(String apprentices_id) {	
		return HttpRequest.post(apprentices_delete_url(apprentices_id)).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: LeverDelete  
	 * @Description: TODO  删除导师
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String ToturDelete(String tutor_id) {	
		return HttpRequest.post(tutor_delete_url(tutor_id)).query("access_token", token).
				send().body();
	}
	
	 
}
