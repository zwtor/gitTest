package cn.kxy.group.a.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;

public class StudyPlanPublishBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String study_add_url = enterprise_url + "plan/study/add/";
	public static String studyPlan_getList_url = enterprise_url + "plan/study/getList/";
	public static String myTask_getList_url = enterprise_url + "plan/myTask/getList/";
	public static String studyPlan_cancel_url = enterprise_url +"v2/"+enterpriseId+"/employees/cancel_publish_plan/";
	public static String studyPlan_detail_url = enterprise_url +"plan/employeeTrain/getEmployeeTrainTask/";
	public static String studyPlan_publish_url = enterprise_url + "plan/study/edit/";
	public static String studyPlan_delete_url = enterprise_url + "plan/study/delete/";

    
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
	 * @Title: StudyPlanGetList   
	 * @Description: TODO  学习任务列表
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String StudyPlanGetList(String keyword) {
		return HttpRequest.get(studyPlan_getList_url).query("pageNumber","1").query("pageSize","20").query("keyword",keyword)
				.query("access_token", token).send().body();	
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
	public static String MyTaskGetList(String type,String keyword) {
		return HttpRequest.get(myTask_getList_url).query("type",type).query("keyword",keyword)
				.query("access_token", token).send().body();	
	}
	
	
	/**   
	 * @Title: StudyPlanCancel   
	 * @Description: TODO  取消发布学习任务
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String StudyPlanCancel(String plan_id) {
		return HttpRequest.post(studyPlan_cancel_url).query("access_token", token).query("plan_id",plan_id)
				.send().body();	
	}
	
	
	/**   
	 * @Title: StudyPlanDetail   
	 * @Description: TODO  学习任务详情页
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String StudyPlanDetail(String plan_id) {
		return HttpRequest.get(studyPlan_detail_url).query("access_token", token).query("planId",plan_id)
				.send().body();	
	}
	
	
	/**   
	 * @Title: StudyPlanPublish   
	 * @Description: TODO  学习任务重新发布
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String StudyPlanPublish(String title,String stageId,String course_id,String mappingId,String planId) {
		return HttpRequest.post(studyPlan_publish_url).query("access_token", token).data("{\r\n" + 
				"      \"title\": \""+title+"\",\r\n" + 
				"      \"beginTime\": "+DateUtil.getTimeStamp(-7, "")+",\r\n" + 
				"      \"endTime\": "+DateUtil.getTimeStamp(9, "")+",\r\n" +
				"      \"projectId\": \"\",\r\n" + 
				"      \"summary\": \"\",\r\n" + 
				"      \"classify_id\": \"999\",\r\n" + 
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
				"                  \"stageId\": \""+stageId+"\",\r\n" + 
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
				"                              \"content\": \"test content\",\r\n" + 
				"                              \"resources\": [],\r\n" + 
				"                              \"scoreSetting\": {\r\n" + 
				"                                    \"scoreSwitch\": \"off\",\r\n" + 
				"                                    \"score\": 100\r\n" + 
				"                              },\r\n" + 
				"                              \"workflowJson\": {\r\n" + 
				"                                    \"type\": \"tutor\",\r\n" + 
				"                                    \"userIds\": []\r\n" + 
				"                              },\r\n" + 
				"                              \"mappingId\": \""+mappingId+"\",\r\n" + 
				"                              \"job_type\": \"common\",\r\n" + 
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
				"      \"id\": \""+planId+"\",\r\n" + 
				"      \"planStatus\": \"unfinished\",\r\n" + 
				"    \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();	
	}
	
	
	/**   
	 * @Title: StudyPlanDelete   
	 * @Description: TODO  学习任务删除
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String StudyPlanDelete(String id) {
		return HttpRequest.post(studyPlan_delete_url).query("access_token", token).query("id",id)
				.send().body();	
	}
	
	
}
