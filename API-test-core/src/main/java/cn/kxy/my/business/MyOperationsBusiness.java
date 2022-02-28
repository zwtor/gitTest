package cn.kxy.my.business;

import cn.kxy.authentication.business.PostAuthenticationBusiness;
import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

public class MyOperationsBusiness {

	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String userId = UserBusiness.getUserId();
	public static String add_study_task_url =enterpriseUrl + "plan/study/add";
	
	public static String operations_url =enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/operations";
	
	public static String query_approval_url (String oper_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/operations/"+oper_id+"/approval";
	}
	
	public static String query_approval_comment_url (String instances_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/instances/"+instances_id+"/comment";
	}
	
	public static String query_operation_url(String oper_id) {
		return enterpriseUrl + "/v2/"+enterpriseId+"/users/"+userId+"/operations/"+oper_id;
	}
	
	public static String saveOperationUrl (String oper_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/operations/"+oper_id+"/save";
	}
	
	public static String approvalOperation_url (String oper_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/operations/"+oper_id;
	}
	
	public static String queryOperationsDraftUrl (String operations_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/operations/"+operations_id+"/draft/query";
	}
	
	public static String saveOperationsDraftUrl (String operations_id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/operations/"+operations_id+"/draft/save";
	}
	public static String saveOperationsDraft(String oper_id,String suggest) {
		return HttpRequest.post(saveOperationsDraftUrl(oper_id)).query("access_token", token).data("{\"suggest\":\""+suggest+"\",\"score\":0,"
				+ "\"resources\":[],\"access_token\":\""+token+"\"}").send().body();
	}
	
	
	public static String  queryOperationsDraft(String operations_id) {
		return HttpRequest.get(queryOperationsDraftUrl(operations_id)).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: addPostAuthSc   
	 * @Description: TODO  新增岗位认证的实操
	 * @param: @param title
	 * @param: @param certificateId
	 * @param: @param sc_title
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addPostAuthSc(String title,String certificateId,String sc_title ) {
		return HttpRequest.post(PostAuthenticationBusiness.addUrl).query("access_token", token).form("stagePass", "false")
				.form("studyTimeLimit", "0").form("summary","this is a summary").form("title", title).form("visibleRange", "part")
				.form("certificateId", certificateId).form("scopes", "{\r\n" + 
						"  \"department_list\":[\r\n" + 
						"  ],\r\n" + 
						"  \"user_list\":[\r\n" + 
						"    {\r\n" + 
						"      \"id\":\""+userId+"\",\r\n" + 
						"      \"name\":\""+UserBusiness.getUsername()+"\",\r\n" + 
						"      \"type\":2\r\n" + 
						"    }],\r\n" + 
						"  \"group_list\":[\r\n" + 
						"  ],\r\n" + 
						"  \"post_list\":[\r\n" + 
						"  ]\r\n" + 
						"}\r\n" + 
						"").form("stageJson", "[\r\n" + 
								"  {\r\n" + 
								"    \"content\":\"\",\r\n" + 
								"    \"stageName\":\"step one\",\r\n" + 
								"    \"isOrder\":false,\r\n" + 
								"    \"stageSort\":1,\r\n" + 
								"    \"course\":[\r\n" + 
								"      {\r\n" + 
								"        \"courseType\":10,\r\n" + 
								"        \"courseSort\":0,\r\n" + 
								"        \"flag\":4,\r\n" + 
								"        \"type\":\"sc\",\r\n" + 
								"        \"id\":\"\",\r\n" + 
								"        \"title\":\""+sc_title+"\",\r\n" + 
								"        \"content\":\"this is a content\",\r\n" + 
								"        \"resources\":[\r\n" + 
								"        ],\r\n" + 
								"        \"scoreSetting\":{\r\n" + 
								"          \"scoreSwitch\":\"on\",\r\n" + 
								"          \"score\":100\r\n" + 
								"        },\r\n" + 
								"        \"workflowJson\":{\r\n" + 
								"          \"type\":\"or\",\r\n" + 
								"          \"userIds\":[\r\n" + 
								"            \""+userId+"\"]\r\n" + 
								"        }\r\n" + 
								"      }]\r\n" + 
								"  }]\r\n" + 
								"").send().body();
	}
	
	/**   
	 * @Title: approvalOperation   
	 * @Description: TODO  审核实操 
	 * @param: @param oper_id
	 * @param: @param suggest
	 * @param: @param status   2驳回，1通过
	 * @param: @param score
	 * @param: @param operation_source
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String approvalOperation(String oper_id,String suggest,String status,String score,String operation_source) {
		return HttpRequest.post(approvalOperation_url(oper_id)).query("access_token", token).query("enterprise_id", enterpriseId).
				query("user_id", userId).data("{\r\n" + 
						"	\"suggest\": \""+suggest+"\",\r\n" + 
						"	\"score\": "+score+",\r\n" + 
						"	\"status\": "+status+",\r\n" + 
						"	\"resources\": [],\r\n" + 
						"	\"operation_source\": \""+operation_source+"\"\r\n" + 
						"}").send().body();
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
				query("status", status).query("user_id", userId).data("{\r\n" + 
						"	\"operation\": [{\r\n" + 
						"		\"content\": \"this is first opercontent\",\r\n" + 
						"		\"step\": 1,\r\n" + 
						"		\"resources\": []\r\n" + 
						"	}, {\r\n" + 
						"		\"content\": \"this is second opercontent\",\r\n" + 
						"		\"step\": 2,\r\n" + 
						"		\"resources\": []\r\n" + 
						"	}],\r\n" + 
						"	\"operation_source\": \""+operation_source+"\"\r\n" + 
						"}").send().body();
	}
	
	/**   
	 * @Title: queryOperationInfo   
	 * @Description: TODO   查看实操详情
	 * @param: @param oper_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryOperationInfo(String oper_id) {
		return HttpRequest.get(query_operation_url(oper_id)).query("access_token", token).query("enterprise_id", enterpriseId)
				.query("user_id", userId).send().body();
	}
	
	public static String addStudyOperation(String title,String beginTime,String endTime,String user_id,String pc_name ) {
		return HttpRequest.post(add_study_task_url).data("{\r\n" + 
				"  \"title\":\""+title+"\",\r\n" + 
				"  \"beginTime\":"+beginTime+",\r\n" + 
				"  \"endTime\":"+endTime+",\r\n" + 
				"  \"summary\":\"\",\r\n" + 
				"  \"dingImgUrl\":\"\",\r\n" + 
				"  \"departmentIds\":\"\",\r\n" + 
				"  \"supervisor_department_ids\":[\r\n" + 
				"  ],\r\n" + 
				"  \"groupIds\":\"\",\r\n" + 
				"  \"isGetScore\":true,\r\n" + 
				"  \"isNoticeAll\":0,\r\n" + 
				"  \"planCertificateId\":\"\",\r\n" + 
				"  \"postIds\":\"\",\r\n" + 
				"  \"progress\":0,\r\n" + 
				"  \"studyScore\":{\r\n" + 
				"    \"finishScore\":0,\r\n" + 
				"    \"unFinishScore\":0\r\n" + 
				"  },\r\n" + 
				"  \"studyTimeLimit\":0,\r\n" + 
				"  \"faceRecognition\":\"off\",\r\n" + 
				"  \"supervisorId\":\""+user_id+"\",\r\n" + 
				"  \"supervisorPaper\":true,\r\n" + 
				"  \"authorityRange\":false,\r\n" + 
				"  \"times\":\"\",\r\n" + 
				"  \"operationTimes\":1,\r\n" + 
				"  \"userIds\":\""+user_id+"\",\r\n" + 
				"  \"stageJson\":[\r\n" + 
				"    {\r\n" + 
				"      \"content\":\"\",\r\n" + 
				"      \"stageName\":\"stage one\",\r\n" + 
				"      \"isOrder\":false,\r\n" + 
				"      \"stageSort\":1,\r\n" + 
				"      \"course\":[\r\n" + 
				"        {\r\n" + 
				"          \"courseType\":10,\r\n" + 
				"          \"courseSort\":0,\r\n" + 
				"          \"flag\":5,\r\n" + 
				"          \"type\":\"sc\",\r\n" + 
				"          \"id\":\"\",\r\n" + 
				"          \"title\":\""+pc_name+"\",\r\n" + 
				"          \"content\":\"this is a content\",\r\n" + 
				"          \"resources\":[\r\n" + 
				"          ],\r\n" + 
				"          \"scoreSetting\":{\r\n" + 
				"            \"scoreSwitch\":\"on\",\r\n" + 
				"            \"score\":100\r\n" + 
				"          },\r\n" + 
				"          \"workflowJson\":{\r\n" + 
				"            \"type\":\"or\",\r\n" + 
				"            \"userIds\":[\r\n" + 
				"              \""+user_id+"\"]\r\n" + 
				"          }\r\n" + 
				"        }]\r\n" + 
				"    }],\r\n" + 
				"  \"stagePass\":false,\r\n" + 
				"  \"finishEvaluation\":false,\r\n" + 
				"  \"openEvaluationResult\":false,\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"").query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: queryApprovalComment   
	 * @Description: TODO  查看pc实操的审核意见
	 * @param: @param instances_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryApprovalComment(String instances_id) {
		return HttpRequest.get(query_approval_comment_url(instances_id)).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: queryOperationApprovalInfo   
	 * @Description: TODO  查看实操审批详情
	 * @param: @param oper_id
	 * @param: @param submit_user_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryOperationApprovalInfo(String oper_id,String submit_user_id) {
		return HttpRequest.get(query_approval_url(oper_id)).query("submit_user_id", submit_user_id).query("access_token",token)
				.send().body();
	}
	/**   
	 * @Title: queryPcOperations   
	 * @Description: TODO  查看pc端我的实操列表
	 * @param: @param keyword
	 * @param: @param status
	 * @param: @param start_time
	 * @param: @param end_time
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryPcOperations(String keyword,String status,String start_time,String end_time) {
		return HttpRequest.get(operations_url).query("access_token", token).query("keyword", keyword).query("status", status)
				.query("start_time", start_time).query("end_time",end_time).query("page_number", "1").query("page_size", "10")
				.send().body();	
	}
}
