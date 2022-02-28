package cn.kxy.study.business;

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

public class AppStudyBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String studyUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String examUrl = ExamDataUrl.getNewExamUrl();
	public static String userId = UserBusiness.getUserId();
	
	public static String query_list_url = studyUrl + "v2/"+enterpriseId+"/users/"+userId+"/studies";
	
	public static String loadResourcesurl(String id) {
		return studyUrl + "v2/"+enterpriseId+"/users/"+userId+"/studies/"+id+"/load_resources";
	}
	
	public static String queryInfoUrl(String id) {
		return studyUrl +"v2/"+enterpriseId+"/users/"+userId+"/studies/"+id+"/query";
	}
	
	public static String  courses_list_url(String course_id) {
		return studyUrl +"v2/"+enterpriseId+"/users/"+userId+"/courses/"+course_id;
	}
	
	public static String course_info_url(String id,String course_id) {
		return studyUrl + "v2/"+enterpriseId+"/users/"+userId+"/studies/"+id+"/courses/"+course_id;
	}
	

	public static String load_resources_url(String id) {
		return studyUrl+"v2/"+enterpriseId+"/users/"+userId+"/studies/"+id+"/load_resources";
	}
	
	public static String save_progress_url(String studyId,String courseId,String resourcesId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/studies/"+studyId+"/courses/"+courseId+"/resources/"+resourcesId+"/save_progress";
	}

	public static String queryStudyCourseInfoUrl(String coursesId) {
		return studyUrl + "v2/"+enterpriseId+"/users/"+userId+"/courses/"+coursesId;
	}
	
	public static String likeUrl (String coursesId) {
		return studyUrl + "v2/"+enterpriseId+"/users/"+userId+"/courses/"+coursesId+"/like";
	}
	
	public static String addCommentUrl (String coursesId) {
		return studyUrl + "v2/"+enterpriseId+"/users/"+userId+"/courses/"+coursesId+"/comment_commit";
	}
	
	public static String checkExamUrl(String examId) {
		return examUrl + "v2/" + enterpriseId + "/users/" + userId + "/exams/"
				+ examId+ "/check";
	}
	public static String submitExamUrl(String examId) {
		return examUrl + "v2/" + enterpriseId + "/users/" + userId + "/exams/"
				+ examId + "/submit";
	}
	public static String queryExamInfoUrl(String examId) {
		return examUrl + "v2/" + enterpriseId + "/users/" + userId+ "/exams/"
				+ examId + "/query";
	}
	public static String queryExamListInfoUrl(String examId) {
		return examUrl + "v2/" + enterpriseId + "/exams/"
				+ examId + "/query";
	}
	public static String queryExamresultUrl(String examId) {
		return examUrl + "v2/" + enterpriseId + "/users/" + userId + "/exams/"
				+ examId + "/result";
	}
	public static String queryExamanswer_analysisUrl(String examId) {
		return examUrl + "v2/" + enterpriseId + "/users/" + userId+ "/exams/"
				+ examId + "/answer_analysis";
	}
	
	public static String queryExamanswer_rankingUrl(String examId) {
		return examUrl + "v2/exams/"+examId+"/mobile/ranking";
	}
	
	public static String study_record_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/plans/"+id+"/study_record";
	}
	
	public static String study_record_info_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/plans/"+id+"/study_records/list";
	}
	
	public static String study_record_export_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/plans/"+id+"/study_record_export";
	}
	
	
	public static String getTrainExamRecordUrl =enterpriseUrl + "plan/employeeTrain/getTrainExamRecord";
	
	public static String latojasInfoUrl(String id,String latojasId) {
		return studyUrl + "v2/"+enterpriseId+"/users/"+userId+"/studies/"+id+"/latojas/"+latojasId;
	}
	
	
	public static String signInUrl(String latojasId) {
		return studyUrl + "v2/"+enterpriseId+"/users/"+userId+"/clock_times/"+latojasId;
	}
	public static String queryAppInvestigatesInfoUrl(String investigatesId,String questionId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/investigates/"+investigatesId+"/questionnaires/"+questionId;
	}
	
	public static String submitUrl(String id,String investigatesId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/studies/"+id+"/investigates/"+investigatesId+"/submit";
	}
	
	public static String exportLatojaMonitorDetailListUrl = enterpriseUrl + "plan/employeeTrain/exportLatojaMonitorDetailList";
	
	public static String exportLatojaMonitorDetailList(String planId,String latojaId) {
		return HttpRequest.get(exportLatojaMonitorDetailListUrl).query("access_token",token).query("departmentId","1").query("planId",planId)
				.query("latojaId",latojaId).query("keyword",UserBusiness.getUsername()).send().body();
	}
	
	public static String statistics_exportUrl (String investigates) {
		return enterpriseUrl + "v2/"+enterpriseId+"/investigates/"+investigates+"/statistics_export";
	}
	
	public static String statistics_export_url (String id,String investigates) {
		return enterpriseUrl + "v2/"+enterpriseId+"/studies/"+id+"/investigates/"+investigates+"/statistics_export";
	}
	
	public static String exportInvestigatesDetailList(String planId,String investigates,String study_type) {
		return HttpRequest.get(statistics_export_url(planId,investigates)).query("access_token",token).query("departmentId","1").query("planId",planId)
				.query("study_type",study_type).query("keyword",UserBusiness.getUsername()).send().body();
	}
	
	public static String statisticsExport(String investigates) {
		return HttpRequest.get(statistics_exportUrl(investigates)).query("access_token",token).send().body();
	}
	
	public static String queryLaInfoUrl (String la_id){
		return enterpriseUrl + "v2/"+enterpriseId+"/latojas/"+la_id+"/query";
	}
	
	public static String queryLaInfo (String plan_id,String la_id,String keyword) {
		return HttpRequest.get(queryLaInfoUrl(la_id)).query("access_token",token).query("plan_id",plan_id).query("keyword",keyword)
				.query("page_number","1").query("page_size","20").send().body();
				
	}
	
	/**   
	 * @Title: submit   
	 * @Description: TODO(提交统计结果)   
	 * @param: @param status
	 * @param: @param threeOption
	 * @param: @param fouroOtion
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String submit(String id,String investigatesId,String questionId_one,String options_one,String questionId_two,String options_two,String questionId_three,
			String questionId_four,String threeOption,String fouroOtion) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"questions\": [{\r\n" + 
				"		\"id\": \""+questionId_one+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \""+options_one+"\"\r\n" + 
				"		}\r\n" + 
				"	}, {\r\n" + 
				"		\"id\": \""+questionId_two+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \""+options_two+"\"\r\n" + 
				"		}\r\n" + 
				"	}, {\r\n" + 
				"		\"id\": \""+questionId_three+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \""+threeOption+"\",\r\n" + 
				"			\"other_answer\": \"\"\r\n" + 
				"		}\r\n" + 
				"	}, {\r\n" + 
				"		\"id\": \""+questionId_four+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \""+fouroOtion+"\",\r\n" + 
				"			\"other_answer\": \"\"\r\n" + 
				"		}\r\n" + 
				"	}]\r\n" + 
				"}", token, submitUrl(id,investigatesId));
	}
	
	
	/**   
	 * @Title: queryAppInvestigatesInfo   
	 * @Description: TODO(查看学习任务中的调研任务)   
	 * @param: @param investigatesId
	 * @param: @param questionId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppInvestigatesInfo(String investigatesId,String questionId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, queryAppInvestigatesInfoUrl(investigatesId,questionId)) ;
	}
	
	/**   
	 * @Title: offlineSignIn   
	 * @Description: TODO(线下签到)   
	 * @param: @param latojasId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String offlineSignIn(String latojasId) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"qc_time\": \"\"\r\n" + 
				"}", token, signInUrl(latojasId));
	}
	
	/**   
	 * @Title: queryLatojasInfo   
	 * @Description: TODO(查看线下签到详情)   
	 * @param: @param id
	 * @param: @param latojasId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryLatojasInfo(String id,String latojasId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, latojasInfoUrl(id,latojasId));
	}
	
	/**   
	 * @Title: getTrainExamRecord   
	 * @Description: TODO(查看考试记录)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getTrainExamRecord(String id) {
		return GetRequestTools.RequestQueryParamsByGet("userId",userId,"access_token",token, "planId", id, getTrainExamRecordUrl);
	}
	
	
	/**   
	 * @Title: queryStudyRecord   
	 * @Description: TODO(查看学习记录)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryStudyRecord(String id) {
		return GetRequestTools.RequestQueryParamsByGet("user_id",userId,"access_token",token,study_record_url(id));
	}
	
	public static String queryStudyRecordInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("user_id",userId,"access_token",token,study_record_info_url(id));
	}
	
	public static String studyRecordExport(String id) {
		return GetRequestTools.RequestQueryParamsByGet("user_id",userId,"access_token",token,study_record_export_url(id));
	}
	
	
	
	/**   
	 * @Title: queryExamanswerRanking   
	 * @Description: TODO(查看考试的等级)   
	 * @param: @param examId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryExamanswerRanking(String examId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,queryExamanswer_rankingUrl(examId));
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
	 * @Title: addPostAuJthCourseComment   
	 * @Description: TODO(添加评论)   
	 * @param: @param coursesId
	 * @param: @param comment
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addStudyCourseComment(String coursesId,String comment) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"comment\": \""+comment+"\"\r\n" + 
				"}", token, addCommentUrl(coursesId));
	}
	
	/**   
	 * @Title: PostAuthLikeCourse   
	 * @Description: TODO(对学习的课程进行点赞)   
	 * @param: @param coursesId
	 * @param: @param like
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StudyLikeCourse(String coursesId,String like) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"status\": \""+like+"\"\r\n" + 
				"}", token, "enterprise_id",enterpriseId,"user_id",userId,likeUrl(coursesId));
	}
	
	/**   
	 * @Title: queryPostAuthCourseInfo   
	 * @Description: TODO(查询学习的课程详情)   
	 * @param: @param coursesId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryStudyCourseInfo(String coursesId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, queryStudyCourseInfoUrl(coursesId));
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
	 * @Title: queryCourseInfo   
	 * @Description: TODO(查询课程详情)   
	 * @param: @param id
	 * @param: @param course_id
	 * @param: @param study_type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryCourseInfo(String id,String course_id,String study_type) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "study_type",study_type,course_info_url(id,course_id));
	}
	
	/**   
	 * @Title: queryCoursesList   
	 * @Description: TODO(查询学习中的课程列表)   
	 * @param: @param course_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryCoursesList(String course_id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,  courses_list_url(course_id));
	}
	
	/**   
	 * @Title: queryInfo   
	 * @Description: TODO(查看APP端学习详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,  queryInfoUrl(id));
	}
	
	public static String queryAppInfo(String id) {
		return HttpRequest.get(queryInfoUrl(id)).query("user_id", userId).
				query("enterprise_id", enterpriseId).query("access_token", token).send().body();
	}
	
	/**   
	 * @Title: loadResources   
	 * @Description: TODO(加载资源)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String loadResource(String id) {
		HttpResponse res=HttpRequest.post(loadResourcesurl(id)).query("access_token", token).query("user_id", userId).
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
	 * @Title: queryStudyList   
	 * @Description: TODO(查询APP端学习的列表)   
	 * @param: @param status unfinished--未完成  ，finished--已完成，overdue--已逾期
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryStudyList(String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "page_number","1","status",status,"page_size","20",query_list_url);
	}
	
}
