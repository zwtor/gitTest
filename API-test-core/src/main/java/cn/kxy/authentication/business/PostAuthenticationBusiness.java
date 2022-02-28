package cn.kxy.authentication.business;

import cn.kxy.base.business.*;
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

public class PostAuthenticationBusiness {
	public static String enterpriseUrl=  EnterpriseDataUrl.getEnterpriseUrl();
	
	public static String examUrl = ExamDataUrl.getNewExamUrl();
	public static String token = TokenData.getMangerToken();
	
	public static String enterpriseId=EnterpriseData.getEnterpriseId();
	
	public static String userId = UserBusiness.getUserId();
	
	public static String username = UserBusiness.getUsername();
	
	public static String queryListUrl = enterpriseUrl +"v1/qualifications";

	public static String addUrl = enterpriseUrl +"v1/qualifications";

	public static String exam_record_url =enterpriseUrl + "plan/employeeTrain/getTrainExamRecord";
	
	public static String getTrainExamPlanPendingListUrl =examUrl + "plan/exam/getTrainExamPlanPendingList";
	
	public static String batchSubmitMarkingPaperUrl = examUrl + "plan/exam/batchSubmitMarkingPaper";
	
	public static String queryExamResultUrl(String examId) {
		return examUrl + "v2/"+enterpriseId+"/exams/"+examId+"/result";
	}

	public static String study_record_url (String id) {
		return examUrl+"v2/"+enterpriseId+"/plans/"+id+"/study_record";
	}
	
	public static String queryInfoUrl(String qualificationsId) {
		return enterpriseUrl+"v1/qualifications/"+qualificationsId;
	}
	
	public static String queryAuthenticationPeopleUrl (String qualificationsId) {
		return enterpriseUrl + "v1/qualifications/"+qualificationsId+"/users";
	}
	
	public static String StartShutdownUrl (String qualificationsId) {
		return enterpriseUrl + "v1/qualifications/"+qualificationsId+"/action";
	}
	
	public static String deleteUrl (String qualificationsId) {
		return enterpriseUrl + "v1/qualifications/"+qualificationsId+"/delete";
	}
	
	public static String monitorsUrl (String qualificationsId) {
		return enterpriseUrl +"v2/"+enterpriseId+"/qualifications/"+qualificationsId+"/monitors";
	}

	public static String update_approver_url(String qualificationsId) {
		return enterpriseUrl +"v2/"+enterpriseId+"/qualifications/"+qualificationsId+"/update_approver";
	}
	
	public static String edit_url(String qualificationsId) {
		return enterpriseUrl + "v1/qualification/"+qualificationsId+"/update";
	}
	
	public static String exam_markings_url (String qualificationsId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/qualifications/"+qualificationsId+"/exam_markings";
	}
	
	public static String monitors_export_url (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/qualifications/"+id+"/monitors_export";
	}
	
	public static String export_qualifications_user_url (String qualificationsId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/qualifications/"+qualificationsId+"/users/export";
	}
	
	public static int getExportQualificationUserCode (String qualificationsId) {
		return  HttpRequest.get(export_qualifications_user_url(qualificationsId)).query("status", "0").query("pageNumber","1").
				 query("access_token", token).query("pageSize","20").contentType("application/json;charset=UTF-8")
					.send().statusCode();
	}
	
	public static String addPostTrick(String title,String certificateId,String stagePass,String courseid_01,String courseid_02, String courseid_03) {
		return HttpRequest.post(addUrl).query("access_token", token).form("title", title).form("summary", "des")
				.form("certificateId", certificateId).form("visibleRange", "part").form("studyTimeLimit", "0").form("stagePass", stagePass)
				.form("scopes", "{\"department_list\":[],\"user_list\":"
						+ "[{\"id\":\""+userId+"\",\"name\":\""+username+"\",\"type\":2}],\"group_list\":[],\"post_list\":[]}").form("workflowJson", "{\"type\":\"or\",\"userIds\":[\""+userId+"\"]}")
				.form("stageJson", "[{\r\n" + 
						"	\"content\": \"\",\r\n" + 
						"	\"stageName\": \"stage one\",\r\n" + 
						"	\"isOrder\": true,\r\n" + 
						"	\"stageSort\": 1,\r\n" + 
						"	\"course\": [{\r\n" + 
						"		\"courseId\": \""+courseid_01+"\",\r\n" + 
						"		\"courseSort\": 0,\r\n" + 
						"		\"courseType\": 3,\r\n" + 
						"		\"flag\": 1\r\n" + 
						"	}, {\r\n" + 
						"		\"courseId\": \""+courseid_02+"\",\r\n" + 
						"		\"courseSort\": 1,\r\n" + 
						"		\"courseType\": 3,\r\n" + 
						"		\"flag\": 1\r\n" + 
						"	}]\r\n" + 
						"}, {\r\n" + 
						"	\"content\": \"\",\r\n" + 
						"	\"stageName\": \"stagetwo\",\r\n" + 
						"	\"isOrder\": true,\r\n" + 
						"	\"stageSort\": 2,\r\n" + 
						"	\"course\": [{\r\n" + 
						"		\"courseId\": \""+courseid_03+"\",\r\n" + 
						"		\"courseSort\": 0,\r\n" + 
						"		\"courseType\": 3,\r\n" + 
						"		\"flag\": 1\r\n" + 
						"	}]\r\n" + 
						"}]").send().body();
	}
	
	/**   
	 * @Title: getMonitorsExportCode   
	 * @Description: TODO(认证导出数据)   
	 * @param: @param id
	 * @param: @param pass_type
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getMonitorsExportCode(String id,String pass_type) {
		return HttpResponse.getstatusCode(monitors_export_url(id), token, "pass_type",pass_type);
	}
	
	// 阅卷
		public static String submitMarkingPaper(String planId, String questionId, String score) {
			return PostRequestTools.RequestFormDataByPost(token, "planId", planId, "userId",
					UserBusiness.getUserId(), "questionJsonList",
					"[{\"questionId\":\"" + questionId + "\",\"score\":" + score + "}]", batchSubmitMarkingPaperUrl);
		}
	
	/**   
	 * @Title: getTrainExamPlanPendingList   
	 * @Description: TODO(查看待阅卷的列表)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getTrainExamPlanPendingList(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"userList","[{\"userId\":\""+userId+"\",\"planId\":\""+id+"\"}]", getTrainExamPlanPendingListUrl);
	}
	
	/**   
	 * @Title: queryExamMarking   
	 * @Description: TODO(查看待阅卷人数)   
	 * @param: @param keyword
	 * @param: @param qualificationsId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryExamMarking(String keyword,String qualificationsId ) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"keyword", keyword, "page_size","10","page_number","1",exam_markings_url(qualificationsId));
	}
	
	public static String addPostAuthRandomExam(String title ,String questionBankId) {
		return PostRequestTools.RequestFormDataByPost(token, "title", title,  "summary", "des","certificateId", CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), 
				"stageJson","[\r\n" + 
						"  {\r\n" + 
						"    \"content\": \"\", \r\n" + 
						"    \"stageName\": \"阶段一\", \r\n" + 
						"    \"isOrder\": false, \r\n" + 
						"    \"stageSort\": 1, \r\n" + 
						"    \"course\": [\r\n" + 
						"      {\r\n" + 
						"        \"courseSort\": 0, \r\n" + 
						"        \"title\": \"PassedPaper\", \r\n" + 
						"        \"type\": \"ex\", \r\n" + 
						"        \"cheatFlag\": 0, \r\n" + 
						"        \"examDuration\": 45, \r\n" + 
						"        \"summary\": \"aaa\", \r\n" + 
						"        \"flag\": 2, \r\n" + 
						"        \"courseId\": \"\", \r\n" + 
						"        \"markType\": 1, \r\n" + 
						"        \"paperId\": \"\", \r\n" + 
						"        \"paperTitle\": \"\", \r\n" + 
						"        \"passLine\": 45, \r\n" + 
						"        \"questionBankList\": \""+questionBankId+"\", \r\n" + 
						"        \"questionMode\": 2, \r\n" + 
						"        \"showKnowledge\": \"show\", \r\n" + 
						"        \"repeatExam\": true, \r\n" + 
						"        \"multipleCount\": 0, \r\n" + 
						"        \"multipleUnitScore\": 0, \r\n" + 
						"        \"fillBlankCount\": 0, \r\n" + 
						"        \"fillBlankUnitScore\": 0, \r\n" + 
						"        \"shortAnswerCount\": 0, \r\n" + 
						"        \"shortAnswerUnitScore\": 0, \r\n" + 
						"        \"singleCount\": 1, \r\n" + 
						"        \"singleUnitScore\": 10, \r\n" + 
						"        \"trueOrFalseCount\": 0, \r\n" + 
						"        \"trueOrFalseUnitScore\": 0, \r\n" + 
						"        \"totalScore\": \"\", \r\n" + 
						"        \"answerParsing\": 5, \r\n" + 
						"        \"passingScore\": 60, \r\n" + 
						"        \"cutScreenCount\": -1, \r\n" + 
						"        \"reExamRule\": 0, \r\n" + 
						"        \"reExamNumber\": 0, \r\n" + 
						"          \"viewRule\": 1, \r\n" +
						"        \"scoreRule\": 0\r\n" + 
						"      }\r\n" + 
						"    ]\r\n" + 
						"  }\r\n" + 
						"]","workflowJson", "{\"type\":\"or\",\"userIds\":[\""+userId+"\"]}","studyTimeLimit","1","visibleRange","part", 
						"scopes","{\"department_list\":[],\"user_list\":[{\"id\":\""+userId+"\",\"name\":\""+username+"\",\"type\":2}],\"group_list\":[],\"post_list\":[]}",addUrl);
	}
	
	
	/**   
	 * @Title: addPostAuthRandomExam   
	 * @Description: TODO(分阶段添加任务-随机考试)   
	 * @param: @param title
	 * @param: @param certificateId
	 * @param: @param questionBankId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addPostAuthRandomExam(String title,String certificateId,String questionBankId) {
		return PostRequestTools.RequestFormDataByPost(token, "title", title,  "summary", "des","certificateId", certificateId, 
				"stageJson","[\r\n" + 
						"  {\r\n" + 
						"    \"content\": \"\", \r\n" + 
						"    \"stageName\": \"step one\", \r\n" + 
						"    \"isOrder\": false, \r\n" + 
						"    \"stageSort\": 1, \r\n" + 
						"    \"course\": [\r\n" + 
						"      {\r\n" + 
						"        \"courseSort\": 0, \r\n" + 
						"        \"title\": \"randomexam\", \r\n" + 
						"        \"type\": \"ex\", \r\n" + 
						"        \"cheatFlag\": 0, \r\n" + 
						"        \"examDuration\": 45, \r\n" + 
						"        \"summary\": \"des\", \r\n" + 
						"        \"flag\": 2, \r\n" + 
						"        \"courseId\": \"\", \r\n" + 
						"        \"markType\": 3, \r\n" + 
						"        \"paperId\": \"\", \r\n" + 
						"        \"paperTitle\": \"\", \r\n" + 
						"        \"passLine\": 60, \r\n" + 
						"        \"questionBankList\": \""+questionBankId+"\", \r\n" + 
						"        \"questionMode\": 2, \r\n" + 
						"        \"showKnowledge\": \"show\", \r\n" + 
						"        \"repeatExam\": true, \r\n" + 
						"        \"multipleCount\": 0, \r\n" + 
						"        \"multipleUnitScore\": 0, \r\n" + 
						"        \"fillBlankCount\": 1, \r\n" + 
						"        \"fillBlankUnitScore\": 60, \r\n" + 
						"        \"shortAnswerCount\": 0, \r\n" + 
						"        \"shortAnswerUnitScore\": 0, \r\n" + 
						"        \"singleCount\": 1, \r\n" + 
						"        \"singleUnitScore\": 40, \r\n" + 
						"        \"trueOrFalseCount\": 0, \r\n" + 
						"        \"trueOrFalseUnitScore\": 0, \r\n" + 
						"        \"totalScore\": \"\", \r\n" + 
						"        \"answerParsing\": 5, \r\n" + 
						"        \"passingScore\": 60, \r\n" + 
						"        \"cutScreenCount\": -1, \r\n" + 
						"        \"reExamRule\": 1, \r\n" + 
						"        \"reExamNumber\": 0, \r\n" + 
						"        \"viewRule\": 1, \r\n" +
						"        \"scoreRule\": 0\r\n" + 
						"      }, \r\n" + 
						"      {\r\n" + 
						"        \"courseType\": 10, \r\n" + 
						"        \"courseSort\": 1, \r\n" + 
						"        \"flag\": 4, \r\n" + 
						"        \"type\": \"sc\", \r\n" + 
						"        \"id\": \"\", \r\n" + 
						"        \"title\": \"sc\", \r\n" + 
						"        \"content\": \"des\", \r\n" + 
						"        \"resources\": [], \r\n" + 
						"        \"scoreSetting\": {\r\n" + 
						"          \"scoreSwitch\": \"off\", \r\n" + 
						"          \"score\": 100\r\n" + 
						"        }, \r\n" + 
						"        \"workflowJson\": {\r\n" + 
						"          \"type\": \"or\", \r\n" + 
						"          \"userIds\": [\r\n" + 
						"            \""+userId+"\"\r\n" + 
						"          ]\r\n" + 
						"        }\r\n" + 
						"      }\r\n" + 
						"    ]\r\n" + 
						"  }\r\n" + 
						"]","workflowJson", "{\"type\":\"or\",\"userIds\":[\""+userId+"\"]}","studyTimeLimit","1","visibleRange","part", 
						"scopes","{\"department_list\":[],\"user_list\":[{\"id\":\""+userId+"\",\"name\":\""+username+"\",\"type\":2}],\"group_list\":[],\"post_list\":[]}",addUrl);
	}
	
	/**   
	 * @Title: queryExamRecord   
	 * @Description: TODO(查询考试记录)   
	 * @param: @param examId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryExamRecord(String examId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "userId", userId, "planId",examId,exam_record_url);
	}
	
	/**   
	 * @Title: queryExamRecord   
	 * @Description: TODO(查询考试记录)   
	 * @param: @param examId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryExamResult(String examId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"userId",userId,queryExamResultUrl(examId));
	}
	
	/**   
	 * @Title: queryInfo   
	 * @Description: TODO查看岗位认证详情()   
	 * @param: @param qualificationsId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInfo(String qualificationsId) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryInfoUrl(qualificationsId));
	}
	

	/**   
	 * @Title: queryStudyRecord   
	 * @Description: TODO(查询学习记录)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryStudyRecord(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"user_id",userId, study_record_url(id));
	}
	
	/**   
	 * @Title: editPostAuthentication   
	 * @Description: TODO(编辑岗位认证)   
	 * @param: @param qualificationsId
	 * @param: @param title
	 * @param: @param summary
	 * @param: @param certificateId
	 * @param: @param studyTimeLimit
	 * @param: @param coursewareId
	 * @param: @param courseId
	 * @param: @param sc_title
	 * @param: @param sc_content
	 * @param: @param exam_title
	 * @param: @param examDuration
	 * @param: @param paperId
	 * @param: @param paper_name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String editPostAuthentication(String qualificationsId,String title,String summary,String certificateId,String studyTimeLimit,String coursewareId,String courseId
			,String sc_title,String sc_content,String exam_title,String examDuration,String paperId,String paper_name) {
		return PostRequestTools.RequestFormDataByPost(token, "title",title, "summary",summary,"certificateId",certificateId, 
				"stageJson","[\r\n" + 
						"  {\r\n" + 
						"    \"content\": \"\", \r\n" + 
						"    \"stageName\": \"stage one\", \r\n" + 
						"    \"isOrder\": false, \r\n" + 
						"    \"stageSort\": 1, \r\n" + 
						"    \"course\": [\r\n" + 
						"      {\r\n" + 
						"        \"courseId\": \""+coursewareId+"\", \r\n" + 
						"        \"courseSort\": 0, \r\n" + 
						"        \"courseType\": 3, \r\n" + 
						"        \"flag\": 1\r\n" + 
						"      }, \r\n" + 
						"      {\r\n" + 
						"        \"courseId\": \""+courseId+"\", \r\n" + 
						"        \"courseSort\": 1, \r\n" + 
						"        \"courseType\": 1, \r\n" + 
						"        \"flag\": 1\r\n" + 
						"      }, \r\n" + 
						"      {\r\n" + 
						"        \"courseSort\": 2, \r\n" + 
						"        \"title\": \""+exam_title+"\", \r\n" + 
						"        \"type\": \"ex\", \r\n" + 
						"        \"cheatFlag\": 0, \r\n" + 
						"        \"examDuration\": "+examDuration+", \r\n" + 
						"        \"summary\": \"this is a summary\", \r\n" + 
						"        \"flag\": 2, \r\n" + 
						"        \"courseId\": \"\", \r\n" + 
						"        \"markType\": 1, \r\n" + 
						"        \"paperId\": \""+paperId+"\", \r\n" + 
						"        \"paperTitle\": \""+paper_name+"\", \r\n" + 
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
						"        \"cutScreenCount\": -1, \r\n" + 
						"        \"reExamRule\": 1, \r\n" + 
						"        \"reExamNumber\": 0, \r\n" + 
						"        \"viewRule\": 1, \r\n" +
						"        \"scoreRule\": 0\r\n" + 
						"      }, \r\n" + 
						"      {\r\n" + 
						"        \"courseType\": 10, \r\n" + 
						"        \"courseSort\": 1, \r\n" + 
						"        \"flag\": 4, \r\n" + 
						"        \"type\": \"sc\", \r\n" + 
						"        \"id\": \"\", \r\n" + 
						"        \"title\": \""+sc_title+"\", \r\n" + 
						"        \"content\": \""+sc_content+"\", \r\n" + 
						"        \"resources\": [], \r\n" + 
						"        \"scoreSetting\": {\r\n" + 
						"          \"scoreSwitch\": \"off\", \r\n" + 
						"          \"score\": 100\r\n" + 
						"        }, \r\n" + 
						"        \"workflowJson\": {\r\n" + 
						"          \"type\": \"or\", \r\n" + 
						"          \"userIds\": [\r\n" + 
						"            \""+userId+"\"\r\n" + 
						"          ]\r\n" + 
						"        }\r\n" + 
						"      }\r\n" + 
						"    ]\r\n" + 
						"  }\r\n" + 
						"]","workflowJson","{\"type\":\"or\",\"userIds\":[\""+userId+"\"]}","studyTimeLimit",studyTimeLimit,
						"visibleRange","part","scopes","{\r\n" + 
								"  \"department_list\": [ ], \r\n" + 
								"  \"user_list\": [\r\n" + 
								"    {\r\n" + 
								"      \"id\": \""+userId+"\", \r\n" + 
								"      \"name\": \""+UserBusiness.getUsername()+"\", \r\n" + 
								"      \"type\": 2\r\n" + 
								"    }\r\n" + 
								"  ], \r\n" + 
								"  \"group_list\": [ ], \r\n" + 
								"  \"post_list\": [ ]\r\n" + 
								"}",edit_url(qualificationsId));
	}
	
	/**   
	 * @Title: addPostAuthentication   
	 * @Description: TODO(添加固定试卷，系统阅卷的岗位认证)   
	 * @param: @param title
	 * @param: @param summary
	 * @param: @param certificateId
	 * @param: @param studyTimeLimit
	 * @param: @param coursewareId
	 * @param: @param courseId
	 * @param: @param sc_title
	 * @param: @param sc_content
	 * @param: @param exam_title
	 * @param: @param examDuration
	 * @param: @param paperId
	 * @param: @param paper_name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addPostAuthentication(String title,String summary,String certificateId,String studyTimeLimit,String coursewareId,String courseId
			,String sc_title,String sc_content,String exam_title,String examDuration,String paperId,String paper_name) {
		return PostRequestTools.RequestFormDataByPost(token, "title",title, "summary",summary,"certificateId",certificateId, 
				"stageJson","[\r\n" + 
						"  {\r\n" + 
						"    \"content\": \"\", \r\n" + 
						"    \"stageName\": \"stage one\", \r\n" + 
						"    \"isOrder\": false, \r\n" + 
						"    \"stageSort\": 1, \r\n" + 
						"    \"course\": [\r\n" + 
						"      {\r\n" + 
						"        \"courseId\": \""+coursewareId+"\", \r\n" + 
						"        \"courseSort\": 0, \r\n" + 
						"        \"courseType\": 3, \r\n" + 
						"        \"flag\": 1\r\n" + 
						"      }, \r\n" + 
						"      {\r\n" + 
						"        \"courseId\": \""+courseId+"\", \r\n" + 
						"        \"courseSort\": 1, \r\n" + 
						"        \"courseType\": 1, \r\n" + 
						"        \"flag\": 1\r\n" + 
						"      }, \r\n" + 
						"      {\r\n" + 
						"        \"courseSort\": 2, \r\n" + 
						"        \"title\": \""+exam_title+"\", \r\n" + 
						"        \"type\": \"ex\", \r\n" + 
						"        \"cheatFlag\": 0, \r\n" + 
						"        \"examDuration\": "+examDuration+", \r\n" + 
						"        \"summary\": \"this is a summary\", \r\n" + 
						"        \"flag\": 2, \r\n" + 
						"        \"courseId\": \"\", \r\n" + 
						"        \"markType\": 1, \r\n" + 
						"        \"paperId\": \""+paperId+"\", \r\n" + 
						"        \"paperTitle\": \""+paper_name+"\", \r\n" + 
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
						"        \"cutScreenCount\": -1, \r\n" + 
						"        \"reExamRule\": 1, \r\n" + 
						"        \"reExamNumber\": 0, \r\n" + 
						"        \"viewRule\": 1, \r\n" +
						"        \"scoreRule\": 0\r\n" + 
						"      }, \r\n" + 
						"      {\r\n" + 
						"        \"courseType\": 10, \r\n" + 
						"        \"courseSort\": 3, \r\n" + 
						"        \"flag\": 4, \r\n" + 
						"        \"type\": \"sc\", \r\n" + 
						"        \"id\": \"\", \r\n" + 
						"        \"title\": \""+sc_title+"\", \r\n" + 
						"        \"content\": \""+sc_content+"\", \r\n" + 
						"        \"resources\": [], \r\n" + 
						"        \"scoreSetting\": {\r\n" + 
						"          \"scoreSwitch\": \"off\", \r\n" + 
						"          \"score\": 100\r\n" + 
						"        }, \r\n" + 
						"        \"workflowJson\": {\r\n" + 
						"          \"type\": \"or\", \r\n" + 
						"          \"userIds\": [\r\n" + 
						"            \""+userId+"\"\r\n" + 
						"          ]\r\n" + 
						"        }\r\n" + 
						"      }\r\n" + 
						"    ]\r\n" + 
						"  }\r\n" + 
						"]","workflowJson","{\"type\":\"or\",\"userIds\":[\""+userId+"\"]}","studyTimeLimit",studyTimeLimit,
						"visibleRange","part","scopes","{\r\n" + 
								"  \"department_list\": [ ], \r\n" + 
								"  \"user_list\": [\r\n" + 
								"    {\r\n" + 
								"      \"id\": \""+userId+"\", \r\n" + 
								"      \"name\": \""+UserBusiness.getUsername()+"\", \r\n" + 
								"      \"type\": 2\r\n" + 
								"    }\r\n" + 
								"  ], \r\n" + 
								"  \"group_list\": [ ], \r\n" + 
								"  \"post_list\": [ ]\r\n" + 
								"}",addUrl);
	}
	
	/**   
	 * @Title: queryAuthenticationPropleList   
	 * @Description: TODO(查询认证人员列表)   
	 * @param: @param qualificationsId
	 * @param: @param keyword
	 * @param: @param status
	 * @param: @param departmentId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAuthenticationPeopleList (String qualificationsId,String keyword,String status,String departmentId) {
		HttpResponse res=HttpRequest.get(queryAuthenticationPeopleUrl(qualificationsId)).query("access_token", token).query("keyword", keyword).
				query("status", status).query("pageNumber", "1").query("pageSize", "20").send();
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
	 * @Title: changeApprover   
	 * @Description: TODO(更改岗位认证审批人)   
	 * @param: @param qualificationsId
	 * @param: @param type
	 * @param: @param user_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String changeApprover(String qualificationsId,String type,String user_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"type\": \""+type+"\", \r\n" + 
				"  \"user_ids_list\": [\r\n" + 
				"    [\r\n" + 
				"      \""+user_id+"\"\r\n" + 
				"    ]\r\n" + 
				"  ], \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, update_approver_url(qualificationsId));
	}
	
	/**   
	 * @Title: monitorsQualifications   
	 * @Description: TODO(查看岗位认证的监控)   
	 * @param: @param qualificationsId
	 * @param: @param keyword
	 * @param: @param pass_type
	 * @param: @param department_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String monitorsQualifications(String qualificationsId,String keyword,String pass_type,String department_id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"page_number","1","page_size","20",
				"pass_type",pass_type,"keyword",keyword,"department_id",department_id, monitorsUrl(qualificationsId));
	}
	
	/**   
	 * @Title: deleteAuthentication   
	 * @Description: TODO(删除岗位认证)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteAuthentication(String qualificationsId) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, "currentAuthId", qualificationsId, deleteUrl(qualificationsId));
	}
	
	/**   
	 * @Title: StartShutdownPost   
	 * @Description: TODO(启用或者停用岗位认证)   
	 * @param: @param id
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String StartShutdownPost(String qualificationsId,String status) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, "status", status, StartShutdownUrl(qualificationsId));
	}
	
	/**   
	 * @Title: queryList   
	 * @Description: TODO(查询岗位认证列表)   
	 * @param: @param keyword
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryList(String keyword,String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "pageNumber","1","pageSize","20"
				,"keyword",keyword,"status",status,queryListUrl);
	}
	
	public static String getIdByKeyword(String keyword ) {
		String res = queryList(keyword,"0");
		String id = (String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	
}
