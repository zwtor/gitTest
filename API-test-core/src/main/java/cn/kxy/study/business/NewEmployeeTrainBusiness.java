package cn.kxy.study.business;

import cn.kxy.base.business.*;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;

public class NewEmployeeTrainBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	
	public static String exam_url = ExamDataUrl.getNewExamUrl();
	
	public static String userId = UserBusiness.getUserId();
	public static String getEnterpriseVersionUrl = enterpriseUrl + "enterprise/getEnterpriseVersion";
	
	public static String getUserInfoByDeptUrl = enterpriseUrl + "plan/employeeTrain/getUserInfoByDept";
	
	public static String list_url =enterpriseUrl + "plan/employeeTrain/getList";
	
	public static String getListCount = enterpriseUrl + "plan/employeeTrain/getListCount";
	
	public static String beginAndStopTaskUrl =enterpriseUrl + "plan/employeeTrain/beginAndStopTask";
	
	public static String deleteEmployTrainTaskUrl = enterpriseUrl + "plan/employeeTrain/deleteEmployTrainTask";
	
	public static String monitors_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/plans/"+id+"/monitors";
	}
	
	public static String queryInfoUrl =enterpriseUrl + "plan/employeeTrain/getEmployeeTrainTask";
	
	public static String add_url = enterpriseUrl + "plan/employeeTrain/add";
	
	public static String tree_url = enterpriseUrl + "v2/"+enterpriseId+"/departments/tree";
	
	public static String posts_url =enterpriseUrl + "v2/"+enterpriseId+"/posts";
	
	public static String edit_url =enterpriseUrl + "plan/employeeTrain/edit";
	
	public static String exportTrainMonitorUrl = enterpriseUrl + "plan/employeeTrain/exportTrainMonitor";
	
	public static String exam_result_url = exam_url + "plan/exam/result";
	

	public static String assignUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/employee_train/"+id+"/assign";
	}
	
	public static String trainEmployeeDelay_url = enterpriseUrl + "plan/employeeTrain/trainEmployeeDelay";
	
	public static String delete_monitor_url(String plan_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/plans/"+plan_id+"/users/delete";
	}
	
	public static String exam_markings_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/studies/"+id+"/exam_markings";
	}
	
	public static String getTrainExamPlanPendingList_url = exam_url + "plan/exam/getTrainExamPlanPendingList";
	
	public static String modify_url = enterpriseUrl + "v2/enterprises/"+enterpriseId+"/operations/approvers/modify";
	
	public static String operations_list_url = enterpriseUrl + "v2/enterprises/"+enterpriseId+"/operations";
	
	public static String user_monitors_exportUrl (String oper_id) {
		return  enterpriseUrl + "v2/enterprises/"+enterpriseId+"/operations/"+oper_id+"/user_monitors_export";
	}
	
	
	public static String  newOperExport(String oper_id) {
		return HttpRequest.get(user_monitors_exportUrl(oper_id)).query("access_token", token).
				query("department_ids", "1").query("approve_status", "pass").send().body();
	}
	
	public static String saveOperationUrl (String oper_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/operations/"+oper_id+"/save";
	}
	
	public static String user_monitorsUrl (String oper_id) {
		return  enterpriseUrl + "v2/enterprises/"+enterpriseId+"/operations/"+oper_id+"/user_monitors";
	}
	
	public static String tutorialsAnalysisUrl (String plan_id) {
		return  enterpriseUrl + "v2/"+enterpriseId+"/plan/"+plan_id+"/tutorials_analysis";
	}
	
	public static String tutorialsAnalysis(String keyword,String plan_id) {
		return HttpRequest.get(tutorialsAnalysisUrl(plan_id)).query("keyword",keyword).query("page_number","1").query("page_size","10").query("access_token",token)
				.send().body();
	}
	
	public static String useOperativalMonitors(String oper_id) {
		return HttpRequest.post(user_monitorsUrl(oper_id)).query("access_token", token).data("{\n" + 
				"  \"department_ids\":[\n" + 
				"    \"1\"],\n" + 
				"  \"position_ids\":[\n" + 
				"  ],\n" + 
				"  \"submit_status\":\"\",\n" + 
				"  \"approve_status\":\"pass\",\n" + 
				"  \"user_name\":\"\",\n" + 
				"  \"page_number\":1,\n" + 
				"  \"page_size\":20,\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}\n" + 
				"").send().body();
	}
	
	/**   
	 * @Title: saveOperation   
	 * @Description: TODO   保存实操
	 * @param: @param oper_id
	 * @param: @param status   0保存实操，1提交实操
	 * @param: @param operation_source
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String saveOperation(String oper_id,String status,String operation_source) {
		return HttpRequest.post(saveOperationUrl(oper_id)).query("access_token", token).query("enterprise_id", enterpriseId).
				query("status", status).query("user_id", userId).query("map_id","").data("{\r\n" + 
						"	\"operation\": [{\r\n" + 
						"		\"content\": \"this is first opercontent\",\r\n" + 
						"		\"step\": 1,\r\n" + 
						"		\"resources\": []\r\n" + 
						"	}],\r\n" + 
						"	\"operation_source\": \""+operation_source+"\"\r\n" + 
						"}").send().body();
	}
	
	/**   
	 * @Title: queryOperaApprovalList   
	 * @Description: TODO  查看实操审批列表
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryOperaApprovalList(String id) {
		return HttpRequest.post(operations_list_url).query("access_token",token).
				data("{\"entity_type\":\"study\",\"entity_id\":\""+id+"\",\"access_token\":\""+token+"\"}").send().body();
	}
	
	/**   
	 * @Title: modifyApprovalPerson   
	 * @Description: TODO  编辑实操审批人
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static  String modifyApprovalPerson(String id) {
		return HttpRequest.post(modify_url).query("access_token",token).data(""
				+ "{\n" + 
				"  \"type\":\"or\",\n" + 
				"  \"approvers\":[\n" + 
				"    [\n" + 
				"      {\n" + 
				"        \"id\":\""+userId+"\"\n" + 
				"      }]],\n" + 
				"  \"operation_ids\":[\n" + 
				"    \""+id+"\"],\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}\n" + 
				"").send().body();
	}
	
	/**   
	 * @Title: getTrainExamPlanPendingList   
	 * @Description: TODO  
	 * @param: @param planId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String getTrainExamPlanPendingList(String planId) {
		
		return GetRequestTools.RequestParamsByGet("access_token" ,token,"userList","[{\"userId\":\""+userId+"\",\"planId\":\""+planId+"\"}]",getTrainExamPlanPendingList_url);
	}
	
	/**   
	 * @Title: queryExamMarking   
	 * @Description: TODO   查看待阅卷列表
	 * @param: @param id
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryExamMarking(String id,String keyword) {
		return HttpRequest.get(exam_markings_url(id)).query("access_token", token).query("keyword", keyword).query("page_number", "1").
				query("page_size", "20").send().body();
	}
	
	/**   
	 * @Title: deleteMonitorByUserId   
	 * @Description: TODO  移除新员工任务
	 * @param: @param plan_id
	 * @param: @param user_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String deleteMonitorByUserId(String plan_id,String user_id) {
		return HttpRequest.post(delete_monitor_url(plan_id)).query("access_token", token).data("{\"user_ids\":[\""+user_id+"\"],"
				+ "\"access_token\":\""+token+"\"}").send().body();
	}
	
	/**   
	 * @Title: trainEmployeeDelay   
	 * @Description: TODO  新员工任务延期
	 * @param: @param planId
	 * @param: @param userIds
	 * @param: @param delayDay
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String trainEmployeeDelay(String planId,String userIds,String delayDay) {
		return HttpRequest.post(trainEmployeeDelay_url).query("planId", planId).query("userIds", userIds).query("delayDay", delayDay)
				.query("access_token", token).data("{\"access_token\":\""+token+"\"}").send().body();
		
	}
	
	/**   
	 * @Title: addNewExam    
	 * @Description: TODO   新增带有考试的新员工任务
	 * @param: @param name
	 * @param: @param paperId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addNewExam(String name ,String paperId) {
		return PostRequestTools.RequestFormDataByPost(token, "departmentIds","1", "effectTime", DateUtil.getRegularDateForHHMMSS(-1), 
				"isGetScore","true","overrideSubDept","1","progress","100","studyScore","{\"finishScore\":0,\"unFinishScore\":0}",
				"studyTimeLimit","0","supervisorId",userId,"supervisorPaper","true","times","1","trainLimit","10",
				"trainName",name,"stagePass","false","stageJson","[\r\n" + 
						"  {\r\n" + 
						"    \"content\":\"\",\r\n" + 
						"    \"stageName\":\"阶段一\",\r\n" + 
						"    \"isOrder\":false,\r\n" + 
						"    \"stageSort\":1,\r\n" + 
						"    \"course\":[\r\n" + 
						"      {\r\n" + 
						"        \"courseSort\":0,\r\n" + 
						"        \"title\":\"PassedPaper\",\r\n" + 
						"        \"type\":\"ex\",\r\n" + 
						"        \"cheatFlag\":0,\r\n" + 
						"        \"faceRecognition\":\"off\",\r\n" + 
						"        \"examDuration\":45,\r\n" + 
						"        \"summary\":\"this is a summary\",\r\n" + 
						"        \"flag\":2,\r\n" + 
						"        \"courseId\":\"\",\r\n" + 
						"        \"markType\":3,\r\n" + 
						"        \"paperId\":\""+paperId+"\",\r\n" + 
						"        \"paperTitle\":\"PassedPaper\",\r\n" + 
						"        \"passLine\":60,\r\n" + 
						"        \"questionBankList\":\"\",\r\n" + 
						"        \"questionMode\":1,\r\n" + 
						"        \"showKnowledge\":\"show\",\r\n" + 
						"        \"repeatExam\":true,\r\n" + 
						"        \"multipleCount\":\"\",\r\n" + 
						"        \"multipleUnitScore\":\"\",\r\n" + 
						"        \"fillBlankCount\":\"\",\r\n" + 
						"        \"fillBlankUnitScore\":\"\",\r\n" + 
						"        \"shortAnswerCount\":\"\",\r\n" + 
						"        \"shortAnswerUnitScore\":\"\",\r\n" + 
						"        \"singleCount\":\"\",\r\n" + 
						"        \"singleUnitScore\":\"\",\r\n" + 
						"        \"trueOrFalseCount\":\"\",\r\n" + 
						"        \"trueOrFalseUnitScore\":\"\",\r\n" + 
						"        \"totalScore\":100,\r\n" + 
						"        \"answerParsing\":5,\r\n" + 
						"        \"passingScore\":\"60.0\",\r\n" + 
						"        \"cutScreenCount\":-1,\r\n" + 
						"        \"reExamRule\":0,\r\n" + 
						"        \"reExamNumber\":0,\r\n" + 
						"        \"scoreRule\":0,\r\n" + 
						"        \"viewRule\":1\r\n" + 
						"      }]\r\n" + 
						"  }]\r\n" + 
						"",add_url);
	}
	
	//新员工指派任务
	public static String  assignNewTask(String id,String group_ids,String user_ids,String department_ids,String post_ids) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"group_ids\":[\r\n" + 
				"    \""+group_ids+"\"],\r\n" + 
				"  \"user_ids\":[\r\n" + 
				"  \""+user_ids+"\"],\r\n" + 
				"  \"department_ids\":[\r\n" + 
				"    \""+department_ids+"\"],\r\n" + 
				"  \"post_ids\":[\r\n" + 
				"    \""+post_ids+"\"],\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"", token, assignUrl(id));
	}
	
	
	public static String addNewEmployeeOpera (String name) {
		return PostRequestTools.RequestFormDataByPost(token, "departmentIds","1", "effectTime", DateUtil.getRegularDateForHHMMSS(-1), 
				"isGetScore","true","overrideSubDept","1","progress","100","studyScore","{\"finishScore\":0,\"unFinishScore\":0}",
				"studyTimeLimit","0","supervisorId",userId,"supervisorPaper","true","times","1","trainLimit","10","operationTimes","1",
				"trainName",name,"stagePass","false","faceRecognition","off","stageJson","[\r\n" + 
						"  {\r\n" + 
						"    \"content\":\"\",\r\n" + 
						"    \"stageName\":\"step one\",\r\n" + 
						"    \"isOrder\":false,\r\n" + 
						"    \"stageSort\":1,\r\n" + 
						"    \"course\":[\r\n" + 
						"      {\r\n" + 
						"        \"courseType\":10,\r\n" + 
						"        \"courseSort\":0,\r\n" + 
						"        \"flag\":5,\r\n" + 
						"        \"type\":\"sc\",\r\n" + 
						"        \"id\":\"\",\r\n" + 
						"        \"title\":\"new operation\",\r\n" + 
						"        \"content\":\"des\",\r\n" + 
						"        \"resources\":[\r\n" + 
						"        ],\r\n" + 
						"        \"scoreSetting\":{\r\n" + 
						"          \"scoreSwitch\":\"off\",\r\n" + 
						"          \"score\":100\r\n" + 
						"        },\r\n" + 
						"        \"workflowJson\":{\r\n" + 
						"          \"type\":\"or\",\r\n" + 
						"          \"userIds\":[\r\n" + 
						"            \""+userId+"\"]\r\n" + 
						"        }\r\n" + 
						"      }]\r\n" + 
						"  }]\r\n" + 
						"",add_url);
	}
	
	public static String addNewEmployeeTrick (String name,String rescourse_id,String stagePass) {
		return PostRequestTools.RequestFormDataByPost(token, "departmentIds","1", "effectTime", DateUtil.getRegularDateForHHMMSS(-1), 
				"isGetScore","true","overrideSubDept","1","progress","100","studyScore","{\"finishScore\":0,\"unFinishScore\":0}",
				"studyTimeLimit","0","supervisorId",userId,"supervisorPaper","true","times","1","trainLimit","10",
				"trainName",name,"stagePass",stagePass,"stageJson","[\r\n" + 
						"  {\r\n" + 
						"    \"content\": \"\", \r\n" + 
						"    \"stageName\": \"step one\", \r\n" + 
						"    \"isOrder\": false, \r\n" + 
						"    \"stageSort\": 1, \r\n" + 
						"    \"course\": [\r\n" + 
						"      {\r\n" + 
						"        \"courseId\": \""+rescourse_id+"\", \r\n" + 
						"        \"courseSort\": 0, \r\n" + 
						"        \"courseType\": 3, \r\n" + 
						"        \"flag\": 1\r\n" + 
						"      }\r\n" + 
						"    ]\r\n" + 
						"  }\r\n" + 
						"]",add_url);
	}
	
	public static String queryNewStudyExamInfoUrl (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/studies/"+id+"/exams";
	}
	
	/**   
	 * @Title: queryExamResult   
	 * @Description: TODO(查看新员工考试的数据)   
	 * @param: @param planId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryExamResult(String planId) {
		return	GetRequestTools.RequestQueryParamsByGet("access_token", token,"planId",planId,"tabId","0", exam_result_url);
	}
	
	/**   
	 * @Title: queryNewStudyExamInfo   
	 * @Description: TODO(查看新员工考试分析)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryNewStudyExamInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryNewStudyExamInfoUrl(id));
	}
	
	/**   
	 * @Title: getExportTrainMonitorCode   
	 * @Description: TODO(导出数据)   
	 * @param: @param id
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static String  getExportTrainMonitorCode(String id,String taskStatus) {
		return HttpRequest.get(exportTrainMonitorUrl).query("queryTaskInfo","true").query("pageNumber","1").query("pageSize","20")
		.query("planId",id).query("departmentId","1").query("taskStatus",taskStatus).query("access_token",token).send().body();
	}
	/**   
	 * @Title: queryMonitors   
	 * @Description: TODO(数据监控)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryMonitors(String id,String taskStatus) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"queryTaskInfo","true","pageNumber","1",
				"pageSize","20","taskStatus",taskStatus,"planId",id,monitors_url(id));
	}
	
	public static String queryMonitors(String id,String taskStatus,String keyword,String departmentId,String postId,String qualifiedStatus) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"queryTaskInfo","true","pageNumber","1","postId",postId,
				"pageSize","20","taskStatus",taskStatus,"planId",id,"keyword",keyword,"departmentId",departmentId,"qualifiedStatus",qualifiedStatus,monitors_url(id));
	}
	
	/**   
	 * @Title: deleteEmployTrainTask   
	 * @Description: TODO(删除)   
	 * @param: @param planId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteEmployTrainTask (String planId) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, "planId", planId, deleteEmployTrainTaskUrl);
	}
	/**   
	 * @Title: editNewEmployTrainByPost   
	 * @Description: TODO(编辑新员工)   
	 * @param: @param id
	 * @param: @param planId
	 * @param: @param trainName
	 * @param: @param effectTime
	 * @param: @param rescourse_id
	 * @param: @param departmentIds
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String editNewEmployTrainByPost(String id,String planId,String trainName,String effectTime,String rescourse_id,String departmentIds) {
		return PostRequestTools.RequestFormDataByPost(token,"departmentIds",departmentIds, "id",id,"planId",planId, "effectTime", effectTime, 
				"isGetScore","true","status","2","overrideSubDept","0","progress","100","studyScore","{\"finishScore\":0,\"unFinishScore\":0}",
				"studyTimeLimit","45","supervisorId",userId,"supervisorPaper","true","newEmployee","false","trainLimit","10",
				"trainName",trainName,"type","","stageJson","[\r\n" + 
						"  {\r\n" + 
						"    \"content\": \"\", \r\n" + 
						"    \"stageName\": \"step one\", \r\n" + 
						"    \"isOrder\": false, \r\n" + 
						"    \"stageSort\": 1, \r\n" + 
						"    \"course\": [\r\n" + 
						"      {\r\n" + 
						"        \"courseId\": \""+rescourse_id+"\", \r\n" + 
						"        \"courseSort\": 0, \r\n" + 
						"        \"courseType\": 3, \r\n" + 
						"        \"flag\": 1\r\n" + 
						"      }\r\n" + 
						"    ]\r\n" + 
						"  }\r\n" + 
						"]",edit_url);
	}
	
	/**   
	 * @Title: queryInfo   
	 * @Description: TODO(查看新员工培训详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"id",id, queryInfoUrl);
	}
	
	public static String getDepartmentId() {
		String res = queryDepartmentList();
		String id = (String )JSONPath.read(res, "$.children[0].id");
		return id;
	}
	
	public static String getPostId() {
		
		String reString = GetRequestTools.RequestQueryParamsByGet("access_token", token,"filter_auth","true", posts_url);
		String id = (String)JSONPath.read(reString, "$.posts[0].children[0].id");
		return id;
	}
	
	public static String addNewEmployTrainByPost(String trainName,String departmentIds,String effectTime,String rescourse_id) {
		return PostRequestTools.RequestFormDataByPost(token, "departmentIds",departmentIds, "effectTime", effectTime, 
				"isGetScore","true","overrideSubDept","1","progress","100","studyScore","{\"finishScore\":0,\"unFinishScore\":0}",
				"studyTimeLimit","0","supervisorId",userId,"supervisorPaper","true","times","1","trainLimit","10",
				"trainName",trainName,"type","2","stageJson","[\r\n" + 
						"  {\r\n" + 
						"    \"content\": \"\", \r\n" + 
						"    \"stageName\": \"step one\", \r\n" + 
						"    \"isOrder\": false, \r\n" + 
						"    \"stageSort\": 1, \r\n" + 
						"    \"course\": [\r\n" + 
						"      {\r\n" + 
						"        \"courseId\": \""+rescourse_id+"\", \r\n" + 
						"        \"courseSort\": 0, \r\n" + 
						"        \"courseType\": 3, \r\n" + 
						"        \"flag\": 1\r\n" + 
						"      }\r\n" + 
						"    ]\r\n" + 
						"  }\r\n" + 
						"]",add_url);
	}
	
	/**   
	 * @Title: queryDepartmentList   
	 * @Description: TODO(查看部门列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryDepartmentList() {
		return GetRequestTools.RequestQueryParamsByGet("recursion", "false","query_user_count","false", "filter_auth","true","access_token",token,tree_url);
		
	}
	/**   
	 * @Title: addNewEmployTrain   
	 * @Description: TODO()   
	 * @param: @param trainName
	 * @param: @param departmentPostId
	 * @param: @param effectTime
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addNewEmployTrainByDepart(String trainName,String departmentIds,String effectTime,String rescourse_id,
			String course_id,String paperId,String newEmployee,String overrideSubDept) {
		return PostRequestTools.RequestFormDataByPost(token,"newEmployee",newEmployee,"overrideSubDept",overrideSubDept, "departmentIds",departmentIds, "effectTime", effectTime, 
				"isGetScore","true","overrideSubDept","1","progress","100","studyScore","{\"finishScore\":0,\"unFinishScore\":0}",
				"studyTimeLimit","0","supervisorId",userId,"supervisorPaper","true","times","1","trainLimit","10",
				"trainName",trainName,"type","1","stageJson","[\r\n" + 
						"  {\r\n" + 
						"    \"content\": \"\", \r\n" + 
						"    \"stageName\": \"step one\", \r\n" + 
						"    \"isOrder\": false, \r\n" + 
						"    \"stageSort\": 1, \r\n" + 
						"    \"course\": [\r\n" + 
						"      {\r\n" + 
						"        \"courseId\": \""+rescourse_id+"\", \r\n" + 
						"        \"courseSort\": 0, \r\n" + 
						"        \"courseType\": 3, \r\n" + 
						"        \"flag\": 1\r\n" + 
						"      }, \r\n" + 
						"      {\r\n" + 
						"        \"courseId\": \""+course_id+"\", \r\n" + 
						"        \"courseSort\": 1, \r\n" + 
						"        \"courseType\": 1, \r\n" + 
						"        \"flag\": 1\r\n" + 
						"      }, \r\n" + 
						"      {\r\n" + 
						"        \"courseSort\": 2, \r\n" + 
						"        \"title\": \""+BaseBusiness.pass_paper_name+"\", \r\n" + 
						"        \"type\": \"ex\", \r\n" + 
						"        \"cheatFlag\": 0, \r\n" + 
						"        \"examDuration\": 45, \r\n" + 
						"        \"summary\": \"this is a summary\", \r\n" + 
						"        \"flag\": 2, \r\n" + 
						"        \"courseId\": \"\", \r\n" + 
						"        \"markType\": 1, \r\n" + 
						"        \"paperId\": \""+paperId+"\", \r\n" + 
						"        \"paperTitle\": \""+BaseBusiness.pass_paper_name+"\", \r\n" + 
						"        \"passLine\": 60, \r\n" + 
						"        \"questionBankList\": \"\", \r\n" + 
						"        \"questionMode\": 1, \r\n" + 
						"        \"showKnowledge\": \"show\", \r\n" + 
						"        \"repeatExam\": true, \r\n" + 
						"        \"multipleCount\": \"\", \r\n" + 
						"        \"multipleUnitScore\": \"\", \r\n" + 
						"        \"fillBlankCount\": \"\", \r\n" + 
						"        \"fillBlankUnitScore\": \"\", \r\n" + 
						"        \"shortAnswerCount\": \"\", \r\n" + 
						"        \"shortAnswerUnitScore\": \"\", \r\n" + 
						"        \"singleCount\": \"\", \r\n" + 
						"        \"singleUnitScore\": \"\", \r\n" + 
						"        \"trueOrFalseCount\": \"\", \r\n" + 
						"        \"trueOrFalseUnitScore\": \"\", \r\n" + 
						"        \"totalScore\": 100, \r\n" + 
						"        \"answerParsing\": 5, \r\n" + 
						"        \"passingScore\": \"60.0\", \r\n" + 
						"        \"cutScreenCount\": 0, \r\n" + 
						"        \"reExamRule\": 0, \r\n" + 
						"        \"reExamNumber\": 0, \r\n" + 
						"          \"viewRule\": 1, \r\n" +
						"        \"scoreRule\": 0\r\n" + 
						"      }\r\n" + 
						"    ]\r\n" + 
						"  }\r\n" + 
						"]",add_url);
	}
	
	/**   
	 * @Title: beginAndStopTask   
	 * @Description: TODO(停用或者已启用新员工培训)   
	 * @param: @param trainId
	 * @param: @param status（1--启用，2--停用）
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String beginAndStopTask(String trainId,String status) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, "trainId", trainId, "status", status, beginAndStopTaskUrl);
	}
	
	/**   
	 * @Title: getListCount   
	 * @Description: TODO(获取新员工培训任务的列表个数)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getListCount(String  taskType) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"statusType","0","taskType",taskType,
				"onlySeeMe","false",getListCount);
	}
	
	/**   
	 * @Title: queryList   
	 * @Description: TODO(查看新员工培训列表)   
	 * @param: @param keyword
	 * @param: @param onlySeeMe
	 * @param: @param statusType
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryList(String keyword,String onlySeeMe,String statusType,String taskType,String departmentId,String postId) {
		return GetRequestTools.RequestQueryParamsByGet("keyword",keyword,"access_token", token,"pageNumber","1",
				"pageSize","20","onlySeeMe",onlySeeMe, "statusType",statusType,"taskType",taskType,list_url);
	}
	
	/**   
	 * @Title: getUserInfoByDept   
	 * @Description: TODO(通过部门查看用户)   
	 * @param: @param departmentId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String  getUserInfoByDept(String departmentId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"taskType","0","departmentId",departmentId, getUserInfoByDeptUrl);
	}
	
	/**   
	 * @Title: getEnterpriseVersion   
	 * @Description: TODO(获取企业版本信息)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getEnterpriseVersion() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, getEnterpriseVersionUrl);
	}
	
}
