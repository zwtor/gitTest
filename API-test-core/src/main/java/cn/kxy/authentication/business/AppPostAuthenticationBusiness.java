package cn.kxy.authentication.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;
import com.lazy.httpclient.utils.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class AppPostAuthenticationBusiness {
	public static String enterpriseUrl=  EnterpriseDataUrl.getEnterpriseUrl();
	
	public static String exam_url=  ExamDataUrl.getNewExamUrl();
	public static String token = TokenData.getMangerToken();
	
	public static String enterpriseId=EnterpriseData.getEnterpriseId();
	public static String userId = UserBusiness.getUserId();
	public static String queryAppPostListUrl = enterpriseUrl+"v2/"+enterpriseId+"/users/"+userId+"/qualifications";
	
	public static String queryStartAuthenticationInfoUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/qualifications/"+id;
	}
	
	public static String queryAppPostItemsUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/qualifications/"+id+"/items";
	}
	
	public static String queryAppPostStateUrl (String id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/qualifications/"+id+"/state";
	}
	
	public static String load_resources_url(String id) {
		return enterpriseUrl+"v2/"+enterpriseId+"/users/"+userId+"/studies/"+id+"/load_resources";
	}
	
	public static String save_progress_url(String studyId,String courseId,String resourcesId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/studies/"+studyId+"/courses/"+courseId+"/resources/"+resourcesId+"/save_progress";
	}
	
	public static String queryPostAuthInfoUrl(String id,String coursesId) {
		return enterpriseUrl +"v2/"+enterpriseId+"/users/"+userId+"/studies/"+id+"/courses/"+coursesId;
	}

	public static String queryPostAuthCourseInfoUrl(String coursesId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/courses/"+coursesId;
	}
	
	public static String likeUrl (String coursesId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/courses/"+coursesId+"/like";
	}
	
	public static String addCommentUrl (String coursesId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/courses/"+coursesId+"/comment_commit";
	}
	
	public static String operationInfoUrl (String operationId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/operations/"+operationId;
	}
	
	public static String saveOperationUrl(String operationId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/operations/"+operationId+"/save";
	}
	public static String checkExamUrl(String examId) {
		return exam_url + "v2/" + enterpriseId + "/users/" + userId + "/exams/"
				+ examId+ "/check";
	}
	public static String submitExamUrl(String examId) {
		return exam_url + "v2/" + enterpriseId + "/users/" + userId + "/exams/"
				+ examId + "/submit";
	}
	public static String queryExamInfoUrl(String examId) {
		return exam_url + "v2/" + enterpriseId + "/users/" + userId+ "/exams/"
				+ examId + "/query";
	}
	public static String queryExamListInfoUrl(String examId) {
		return exam_url + "v2/" + enterpriseId + "/exams/"
				+ examId + "/query";
	}
	public static String queryExamresultUrl(String examId) {
		return exam_url + "v2/" + enterpriseId + "/users/" + userId + "/exams/"
				+ examId + "/result";
	}
	public static String queryExamanswer_analysisUrl(String examId) {
		return exam_url + "v2/" + enterpriseId + "/users/" + userId+ "/exams/"
				+ examId + "/answer_analysis";
	}
	
	public static String operations_analysis_url = enterpriseUrl + "v2/enterprises/"+enterpriseId+"/operations/analysis";
	
	public static String queryPostOperListUrl (String id) {
		return enterpriseUrl + "v2/enterprises/"+enterpriseId+"/operations/"+id+"/user_monitors" ;
	}
	
	public static String operations_user_monitors_export (String id) {
		return enterpriseUrl + "v2/enterprises/"+enterpriseId+"/operations/"+id+"/user_monitors_export";
	}
	
	public static String operationsUseMonitorsExport (String id) {
		return HttpRequest.get(operations_user_monitors_export(id)).query("access_token",token).query("submit_status","submitted").
				query("approve_status","pass").send().body();
	}
	
	public static String queryOperInfoUrl (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/operations/"+id+"/approval";
	}
	
	public static String queryOperInfo (String id) {
		return HttpRequest.get(queryOperInfoUrl(id)).query("access_token",token).query("submit_user_id",userId).send().body();
	}
	
	public static String queryPostOperList(String id,String submit_status,String approve_status) {
		return HttpRequest.post(queryPostOperListUrl(id)).query("access_token",token).data("{\r\n" + 
				"  \"department_ids\":[\r\n" + 
				"  ],\r\n" + 
				"  \"position_ids\":[\r\n" + 
				"  ],\r\n" + 
				"  \"submit_status\":\""+submit_status+"\",\r\n" + 
				"  \"approve_status\":\""+approve_status+"\",\r\n" + 
				"  \"user_name\":\"\",\r\n" + 
				"  \"page_number\":1,\r\n" + 
				"  \"page_size\":20,\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"").send().body();
	}
	
	public static String queryOperationAnalysis(String entity_type,String entity_id) {
		return HttpRequest.post(operations_analysis_url).query("access_token",token).data("{\r\n" + 
				"  \"entity_type\":\""+entity_type+"\",\r\n" + 
				"  \"entity_id\":\""+entity_id+"\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"").send().body();
	}
	
	/**   
	 * @Title: submitPassExam   
	 * @Description: TODO()   
	 * @param: @param examId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String submitPassExam(String examId) {
		String res01 =queryExamInfo(examId);
		String question_id01 = (String) JSONPath.read(res01, "$.questions[0].id");
		String question_id02 = (String) JSONPath.read(res01, "$.questions[1].id");
		String anwser_id01 = (String) JSONPath.read(res01, "$.questions[0].options[2].id");

		String res = PostRequestTools.RequestBodyByPost("{\"questions\":[{\"id\":\"" + question_id01
				+ "\",\"answer\":[\"" + anwser_id01 + "\"]}," + "{\"id\":\"" + question_id02
				+ "\",\"answer\":[\"2\",\"4\"]}],\"access_token\":\"" + token + "\"}", token,
				"status", "manual", submitExamUrl(examId));
		return res;
	}
	
	/**   
	 * @Title: queryExamInfo   
	 * @Description: TODO(查询考试详情)   
	 * @param: @param examId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryExamInfo(String examId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"user_id",userId, queryExamInfoUrl(examId));
	}
	
	/**   
	 * @Title: queryExamanswer_analysis   
	 * @Description: TODO(查看答案解析)   
	 * @param: @param examId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryExamanswer_analysis(String examId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"user_id",userId, "random","error40",queryExamInfoUrl(examId));
	}
	
	/**   
	 * @Title: queryExamListInfo   
	 * @Description: TODO(查看考试基本数据)   
	 * @param: @param examId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryExamListInfo(String examId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"user_id",userId, queryExamListInfoUrl(examId));
	}
	
	/**   
	 * @Title: submitBlankExam   
	 * @Description: TODO(交白卷)   
	 * @param: @param examId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String submitBlankExam(String examId) {
		String res01 = GetRequestTools.RequestQueryParamsByGet("access_token", token, queryExamInfoUrl(examId));
		String question_id01 = (String) JSONPath.read(res01, "$.questions[0].id");
		String question_id02 = (String) JSONPath.read(res01, "$.questions[1].id");
		String res = PostRequestTools.RequestBodyByPost(
				"{\"questions\":[{\"id\":\"" + question_id01 + "\",\"answer\":[]}," + "{\"id\":\"" + question_id02
						+ "\",\"answer\":[]}]," + "\"access_token\":\"" + token + "\"}",
				token, "status", "manual", submitExamUrl(examId));
		return res;
	}
	
	/**   
	 * @Title: checkIsCanExam   
	 * @Description: TODO(检查是否可以考试)   
	 * @param: @param examId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String checkIsCanExam(String examId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, checkExamUrl(examId));
	}
	
	/**   
	 * @Title: queryExamResult   
	 * @Description: TODO(查看考试结果)   
	 * @param: @param examId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryExamResult(String examId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryExamresultUrl(examId));

	}
	
	
	/**   
	 * @Title: saveOperation   
	 * @Description: TODO(保存实操训练)   
	 * @param: @param operationId
	 * @param: @param content
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String saveOperation(String operationId,String status,String content) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"operation\": [{\r\n" + 
				"		\"content\": \""+content+"\",\r\n" + 
				"		\"step\": 1,\r\n" + 
				"		\"resources\": []\r\n" + 
				"	}]\r\n" + 
				"}", token, "status", status, "map_id", "undefined", saveOperationUrl(operationId));
	}
	
	
	/**   
	 * @Title: queryOperationInfo   
	 * @Description: TODO(查看实操详情)   
	 * @param: @param operationId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryOperationInfo(String operationId){
		return GetRequestTools.RequestParamsByGet("access_token", token, operationInfoUrl(operationId));
	}
	
	/**   
	 * @Title: addPostAuJthCourseComment   
	 * @Description: TODO(添加评论)   
	 * @param: @param coursesId
	 * @param: @param comment
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addPostAuJthCourseComment(String coursesId,String comment) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"comment\": \""+comment+"\"\r\n" + 
				"}", token, addCommentUrl(coursesId));
	}
	
	/**   
	 * @Title: PostAuthLikeCourse   
	 * @Description: TODO(对岗位认证的课程进行点赞)   
	 * @param: @param coursesId
	 * @param: @param like
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PostAuthLikeCourse(String coursesId,String like) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"status\": \""+like+"\"\r\n" + 
				"}", token, likeUrl(coursesId));
	}
	
	/**   
	 * @Title: queryPostAuthCourseInfo   
	 * @Description: TODO(查询岗位认证的课程详情)   
	 * @param: @param coursesId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryPostAuthCourseInfo(String coursesId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, queryPostAuthCourseInfoUrl(coursesId));
	}
	
	/**   
	 * @Title: queryPostAuthInfo   
	 * @Description: TODO(查询详情)   
	 * @param: @param id
	 * @param: @param coursesId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryPostAuthCourseInfo(String id,String coursesId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryPostAuthInfoUrl(id, coursesId));
	}
	
	/**   
	 * @Title: saveProgress   
	 * @Description: TODO(保存进度)   
	 * @param: @param progress
	 * @param: @param studyId
	 * @param: @param courseId
	 * @param: @param resourcesId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String saveProgress(String  progress,String studyId,String courseId,String resourcesId) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"progress\": "+progress+",\r\n" + 
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}", token, save_progress_url(studyId, courseId, resourcesId));
	}
	
	/**   
	 * @Title: queryStartAuthenticationInfo   
	 * @Description: TODO(查看开始认证的详细信息)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryStartAuthenticationInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, queryStartAuthenticationInfoUrl(id));
	}
	/**   
	 * @Title: getAppPostIdByStatus   
	 * @Description: TODO(获取岗位认证的Id)   
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getAppPostIdByStatus(String status) {
		String res = queryAppPostList(status);
		String id = (String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	/**   
	 * @Title: loadResource   
	 * @Description: TODO(判断加载资源是否成功)   
	 * @param: @param studyId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String loadResource(String id) {
		HttpResponse res=HttpRequest.post(load_resources_url(id)).query("access_token", token).query("user_id", userId).
				query("enterprise_id", enterpriseId).send();
		HttpEntity resEntity = res.response().getEntity();  
		String msg = "" ;
        try {
			 msg = EntityUtils.toString(resEntity, "utf-8");
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return msg;  
	}
	/**   
	 * @Title: queryAppPostItems   
	 * @Description: TODO(查看APP端岗位认证详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppPostItems(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "user_id",userId,queryAppPostItemsUrl(id));
	}
	/**   
	 * @Title: queryAppPostState   
	 * @Description: TODO(查看岗位是否可认证)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppPostState(String id) {
		return GetRequestTools.RequestQueryParamsByGet("user_id", userId, "access_token",token,queryAppPostStateUrl(id));
	}
	/**   
	 * @Title: queryAppPostList   
	 * @Description: TODO(查询APP端认证列表)   
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppPostList(String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "status",status,"page_number","1",
				"page_size","20",queryAppPostListUrl);
	}
}
