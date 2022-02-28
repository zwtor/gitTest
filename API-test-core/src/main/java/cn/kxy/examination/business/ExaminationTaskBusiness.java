package cn.kxy.examination.business;

import cn.kxy.authentication.business.CertificateBusiness;
import cn.kxy.base.business.BaseBusiness;
import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;
import com.lazy.httpclient.utils.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ExaminationTaskBusiness {
	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String token = TokenData.getMangerToken();
	public static String addUrl = exam_url + "plan/exam/add";

	public static String queryListUrl = exam_url + "plan/exam/getList";

	public static String deleteUrl = exam_url + "plan/exam/delete";

	public static String delayUrl = exam_url + "plan/exam/planExamDelay";

	public static String changeUrl = exam_url + "plan/exam/changeExamUsers";

	public static String remindUrl = exam_url + "plan/exam/examRemind";

	public static String editUrl = exam_url + "plan/exam/edit";
 
	public static String queryUntestedInfoUrl = exam_url + "plan/exam/getPlanExamInfoByPlanId";

	public static String makeUpUrl = exam_url + "plan/exam/addMakeUpExamV2";

	public static String PersonnelmonitorUrl = exam_url + "plan/exam/result";

	public static String queryQuestionAnalysisUrl = exam_url + "plan/exam/queryQuestionAnalysis";

	public static String getIntervalStatisticalUrl = exam_url + "plan/exam/getIntervalStatistical";

	public static String getExamPlanPendingListUrl = exam_url + "plan/exam/getExamPlanPendingList";

	public static String batchSubmitMarkingPaperUrl = exam_url + "plan/exam/batchSubmitMarkingPaper";

	
	
	public static String submitExamUrl(String name) {
		return exam_url + "v2/" + enterpriseId + "/users/" + UserBusiness.getUserId() + "/exams/"
				+ getIdByKeyword(name) + "/submit";
	}
	public static String submitExamByIdUrl(String id) {
		return exam_url + "v2/" + enterpriseId + "/users/" + UserBusiness.getUserId() + "/exams/"
				+ id+ "/submit";
	}

	public static String makeupExam_monitorsUrl(String name) {
		return exam_url + "v2/" + enterpriseId + "/exams/" + getIdByKeyword(name)
				+ "/makeupExam_monitors";
	}
	
	public static String makeupExam_monitorsUrlById(String id) {
		return exam_url + "v2/" + enterpriseId + "/exams/" +id
				+ "/makeupExam_monitors";
	}
	
	public static String exportExamResultUrl = exam_url + "plan/exam/exportExamResult";

	public static String export_detail_url(String id) {
		return exam_url + "v2/"+enterpriseId+"/exams/"+id+"/export_detail";
	}
	
	public static String answer_detail_export_url(String id) {
		return exam_url + "v2/"+enterpriseId+"/exams/"+id+"/answer_detail_export";
	}
	
	public static String exportQuestionAnalysisUrl = exam_url +"plan/exam/exportQuestionAnalysis";
	
	public static String makeupExam_monitors_export_url(String id) {
		return exam_url + "v2/"+enterpriseId+"/exams/"+id+"/makeupExam_monitors_export";
	}
	
	public static String makeup_exam_url = exam_url + "plan/exam/addMakeUpExamV2";
	
	public static String historical_achievements_url(String id) {
		return exam_url + "v2/"+enterpriseId+"/plans/"+id+"/exam/historical_achievements";
	}
	
	public static String update_makeup_time_url (String exam_id) {
		return exam_url + "v2/"+enterpriseId+"/exams/"+exam_id+"/update_makeup_time";
	}
	
	public static String exam_rate_user_url = exam_url + "v2/"+enterpriseId+"/rate_user";
	
	/**   
	 * @Title: queryExamRateUser   
	 * @Description: TODO  查看试题的正确率和错误率
	 * @param: @param plan_id
	 * @param: @param question_id
	 * @param: @param rate_type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryExamRateUser(String plan_id,String question_id,String rate_type) {
		return HttpRequest.get(exam_rate_user_url).query("plan_id", plan_id).query("question_id", question_id).query("rate_type", rate_type)
				.query("access_token", token).query("page_number","1").query("page_size","20").send().body();
	}
	
	/**   
	 * @Title: delayMakeupExam   
	 * @Description: TODO   延期补考
	 * 
	 * @param: @param exam_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String delayMakeupExam(String exam_id,String makeupExamEndTime) {
		return HttpRequest.post(update_makeup_time_url(exam_id)).query("access_token", token).data("{\r\n" + 
				"  \"makeupExamEndTime\":"+makeupExamEndTime+",\r\n" + 
				"  \"userIds\":[\r\n" + 
				"    \""+user_id+"\"],\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"").send().body();
	}
	
	/**   
	 * @Title: queryHistoricalRecord   
	 * @Description: TODO  查看历史考试成绩
	 * @param: @param id
	 * @param: @param commit_time_start
	 * @param: @param commit_time_end
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryHistoricalRecord(String id,String commit_time_start,String commit_time_end) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "user_id",user_id,"page_number","1",
				"page_size","10","commit_time_start",commit_time_start,"commit_time_end",commit_time_end,historical_achievements_url(id));
	}
	
	/**   
	 * @Title: addMakeUpExam   
	 * @Description: TODO  补考
	 * @param: @param examPlanId
	 * @param: @param startTime
	 * @param: @param endTime
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addMakeUpExam(String examPlanId,String startTime,String endTime) {
		return PostRequestTools.RequestFormDataByPost(token,"startTime",startTime, "endTime", endTime, "examPlanId", 
				examPlanId, "type", "3", makeup_exam_url);
	}
	
	/**   
	 * @Title: getExportMakeupExamCode   
	 * @Description: TODO(导出补考明细)   
	 * @param: @param id
	 * @param: @param keyword
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getExportMakeupExamCode(String id,String keyword) {
		return HttpResponse.getstatusCode(makeupExam_monitors_export_url(id), token, "departmentId","1","keyword",keyword);
	}
	
	/**   
	 * @Title: getExportQuestionAnalysisCode   
	 * @Description: TODO(导出试题分析)   
	 * @param: @param id
	 * @param: @param type
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getExportQuestionAnalysisCode(String id,String type) {
		return HttpResponse.getstatusCode(exportQuestionAnalysisUrl, token, "planId",id,"type",type);
	}
	
	/**   
	 * @Title: getExportAnswerDetailCode   
	 * @Description: TODO(导出试题作答明细)   
	 * @param: @param id
	 * @param: @param keyword
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getExportAnswerDetailCode(String id,String keyword) {
		return HttpResponse.getstatusCode(answer_detail_export_url(id), token, "department_id","1","keyword",keyword);

	}
	
	/**   
	 * @Title: getExportExamDetailCode   
	 * @Description: TODO(导出学员作答明细)   
	 * @param: @param id
	 * @param: @param keyword
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getExportExamDetailCode(String id,String keyword) {
		return HttpResponse.getstatusCode(export_detail_url(id), token, "department_id","1","keyword",keyword);

	}
	
	public static int getExportExamDetailCode(String id,String keyword,String tabId) {
		return HttpResponse.getstatusCode(export_detail_url(id), token,"tabId",tabId, "department_id","1","keyword",keyword);

	}
	
	/**   
	 * @Title: getExportExamResultCode   
	 * @Description: TODO(导出考试结果)   
	 * @param: @param planId
	 * @param: @param keyword
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getExportExamResultCode(String planId,String keyword) {
		return HttpResponse.getstatusCode(exportExamResultUrl, token, "department_id","1","planId",planId,"keyword",keyword);
	}
	
	public static int getExportExamResultCode(String planId) {
		return HttpResponse.getstatusCode(exportExamResultUrl, token, "department_id","1","planId",planId,"keyword",UserBusiness.getUsername());
	}
	
	/**   
	 * @Title: getIntervalStatistical   
	 * @Description: TODO(获取列表数据)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getIntervalStatistical() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "onlySeeMe", "false",
				getIntervalStatisticalUrl);

	}

	// 阅卷
	public static String submitMarkingPaper(String planId, String questionId, String score) {
		return PostRequestTools.RequestFormDataByPost(token, "planId", planId, "userId",
				UserBusiness.getUserId(), "questionJsonList",
				"[{\"questionId\":\"" + questionId + "\",\"score\":" + score + "}]", batchSubmitMarkingPaperUrl);
	}

	// 获取阅卷的试题详情
	public static String getExamPlanPendingList(String name) {
		return GetRequestTools.RequestQueryParamsByGet("planId", getIdByKeyword(name), "access_token", token,
				getExamPlanPendingListUrl);
	}
	
	public static String getExamPlanPendingList(String id,String makeupCount) {
		return GetRequestTools.RequestQueryParamsByGet("planId", id, "access_token", token,"makeupCount",makeupCount,"userId",user_id,
				getExamPlanPendingListUrl);
	}

	public static String getExamPendingList(String id) {
		return HttpRequest.get(getExamPlanPendingListUrl).query("planId", id).query("access_token", token)
				.send().body();
	}
	
	// 获取
	public static String getInfoUrl(String id) {
		return exam_url + "v2/" + enterpriseId + "/exams/" + id;

	}

	// 发送补考通知
	public static String makeUpExam(String examPlanId, String startTime, String endTime, String type,String makeupUserIds) {
		return PostRequestTools.RequestFormDataByPost(token, "examPlanId", examPlanId, "startTime",
				startTime, "endTime", endTime, "type", type,"makeupUserIds" ,makeupUserIds,makeUpUrl);
	}

	// 查询列表
	public static String queryList(String keyword, String queryIntervalType, String onlySeeMe) {
		return given().queryParam("keyword", keyword).queryParam("queryIntervalType", queryIntervalType)
				.queryParam("sortName", "createTime").queryParam("timestamp", System.currentTimeMillis())
				.queryParam("onlySeeMe", onlySeeMe).queryParam("access_token", token).get(queryListUrl)
				.asString();
	}
	public static String submitPassByIdExam(String id) {
		String res01 = GetRequestTools.RequestQueryParamsByGet("access_token", token,
				MyExamTaskBusiness.queryInfoByIdUrl(id));

		String question_id01 = (String) JSONPath.read(res01, "$.questions[0].id");
		String question_id02 = (String) JSONPath.read(res01, "$.questions[1].id");
		String anwser_id01 = (String) JSONPath.read(res01, "$.questions[0].options[2].id");

		String res = PostRequestTools.RequestBodyByPost("{\"questions\":[{\"id\":\"" + question_id01
				+ "\",\"answer\":[\"" + anwser_id01 + "\"]}," + "{\"id\":\"" + question_id02
				+ "\",\"answer\":[\"2\",\"4\"]}],\"access_token\":\"" + token + "\"}", token,
				"status", "manual", submitExamByIdUrl(id));
		return res;
	}
	

	public static String submitPassExam(String name) {
		String res01 = GetRequestTools.RequestQueryParamsByGet("access_token", token,
				MyExamTaskBusiness.queryInfoUrl(name));

		String question_id01 = (String) JSONPath.read(res01, "$.questions[0].id");
		String question_id02 = (String) JSONPath.read(res01, "$.questions[1].id");
		String anwser_id01 = (String) JSONPath.read(res01, "$.questions[0].options[2].id");

		String res = PostRequestTools.RequestBodyByPost("{\"questions\":[{\"id\":\"" + question_id01
				+ "\",\"answer\":[\"" + anwser_id01 + "\"]}," + "{\"id\":\"" + question_id02
				+ "\",\"answer\":[\"2\",\"4\"]}],\"access_token\":\"" + token + "\"}", token,
				"status", "manual", submitExamUrl(name));
		return res;
	}
	//直接考试通过
	public static String passToDoExam(String name) {
		ExaminationTaskBusiness.creatRewardExamTask("1", "hide", "10", "100", "1", name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.pass_paper_name), "60", "0", "false", "2",
				CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), "12", "0", "0", "0", "0", "true", "false",
				UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		// 交卷
		ExaminationTaskBusiness.submitPassExam(name);
		// 阅卷
		String res = ExaminationTaskBusiness.getExamPlanPendingList(name);
		String id =(String)JSONPath.read(res, "$.data.paperVo.showQuestionInfo[0].answer.bizQuestionOptionList[0].questionId");
		String res0 = ExaminationTaskBusiness.submitMarkingPaper(ExaminationTaskBusiness.getIdByKeyword(name), id, "60");
		return res0;
	}
	//直接考试失败
	public static String failToDoExam(String name) {
		ExaminationTaskBusiness.creatRewardExamTask("1", "show", "60", "100", "2", name,
				DateUtil.getRegularDate(0), DateUtil.getRegularDate(2), "false", UserBusiness.getUserId(),
				PaperBusiness.getIdByKeyword(BaseBusiness.paper_name), "60", "", "true", "1",
				CertificateBusiness.getIdByKeyword(BaseBusiness.certificate_name), "12", "0", "0", "0", "0", "true", "false",
				UserBusiness.getUserId(), "{\"missScore\":4,\"passScore\":6,\"unPassScore\":2}");
		String res = MyExamTaskBusiness.submitFailBlankExam(name);
		return res;
	}
	
	// 获取试卷id
	public static String getPaperId() {
		String res = PaperBusiness.queryList(BaseBusiness.paper_name, "false");

		String id = (String) JSONPath.read(res, "$.list[0].id");
		return id;
	}

	// 获取第一个id
	public static String getIdByName(String name) {
		String res = queryList(name, "0", "false");
		String id = (String) JSONPath.read(res, "$.list[0].id");
		return id;
	}

	// 获取第一个name
	public static String getFirstName() {
		String res = queryList("", "0", "false");
		String id = (String) JSONPath.read(res, "$.list[0].title");
		return id;
	}

	// 通过关键字获取id
	public static String getIdByKeyword(String name) {
		String res = queryList(name, "0", "false");
		String id = (String) JSONPath.read(res, "$.list[0].id");
		return id;
	}

	// 补考明细
	public static String queryMakeupExamMonitors(String planId, String tabId, String departmentId, String postId,
			String keyword, String name) {
		return GetRequestTools.RequestQueryParamsByGet("planId", planId, "tabId", tabId, "departmentId", departmentId,
				"postId", postId, "keyword", keyword, "pageNumber", "1", "pageSize", "20", "access_token",
				token, makeupExam_monitorsUrl(name));
	}
	
	/**   
	 * @Title: queryMakeupExamMonitorsById   
	 * @Description: TODO  通过考试Id 查看补考明细
	 * @param: @param planId
	 * @param: @param tabId
	 * @param: @param departmentId
	 * @param: @param postId
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryMakeupExamMonitorsById(String planId, String tabId, String departmentId, String postId,
			String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("planId", planId, "tabId", tabId, "departmentId", departmentId,
				"postId", postId, "keyword", keyword, "pageNumber", "1", "pageSize", "20", "access_token",
				token, makeupExam_monitorsUrlById(planId));
	}

	// 查询试题分析
	public static String queryQuestionAnalysis(String planId, String keyword, String type) {
		HttpResponse res=HttpRequest.get(queryQuestionAnalysisUrl).query("planId", planId).query("access_token", token).
				query("keyword", keyword).query("type",type).send();
		HttpEntity resEntity = res.response().getEntity();  
		String msg = "" ;
        try {
			 msg = EntityUtils.toString(resEntity, "utf-8");
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return msg;  
	}

	// 考试人员监控
	public static String queryPersonnelmonitorResult(String planId, String tabId, String departmentId, String postId,
			String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("planId", planId, "tabId", tabId, "departmentId", departmentId,
				"postId", postId, "keyword", keyword, "pageNumber", "1", "pageSize", "20", "access_token",
				token, PersonnelmonitorUrl);
	}

	//新增随机考试任务
	public static String creatRandomExamTask(String questionMode, String showKnowledge, String passLine, String singleCount,
			String singleUnitScore, String multipleCount, String multipleUnitScore, String trueOrFalseCount,
			String trueOrFalseUnitScore, String fillBlankCount, String fillBlankUnitScore, String shortAnswerCount,
			String shortAnswerUnitScore, String questionBankId,String answerParsing, String title,String beginTime, String endTime, String isAllIn,
			String userIds, String examDuration, String cheatFlag, String repeatExam, String markType,
			String examCertificateId, String passingScore, String cutScreenCount, String reExamRule,
			String reExamNumber, String scoreRule,String examScore, String isGetScore, String supervisorPaper, String supervisorId,String authorityRange) {
		return given().queryParam("access_token", token).formParam("questionMode", questionMode)
				.formParam("showKnowledge", showKnowledge).formParam("passLine", passLine)
				.formParam("singleCount", singleCount).formParam("singleUnitScore", singleUnitScore)
				.formParam("multipleCount", multipleCount).formParam("multipleUnitScore", multipleUnitScore)
				.formParam("trueOrFalseCount", trueOrFalseCount).formParam("trueOrFalseUnitScore", trueOrFalseUnitScore)
				.formParam("fillBlankCount", fillBlankCount).formParam("fillBlankUnitScore", fillBlankUnitScore)
				.formParam("shortAnswerCount", shortAnswerCount).formParam("shortAnswerUnitScore", shortAnswerUnitScore)
				.formParam("questionBankId", questionBankId).formParam("answerParsing", answerParsing).formParam("title", title)
				.formParam("beginTime", beginTime).formParam("endTime", endTime).formParam("isAllIn", isAllIn).formParam("userIds", userIds)
				.formParam("examDuration", examDuration).formParam("cheatFlag", cheatFlag)
				.formParam("repeatExam", repeatExam).formParam("markType", markType)
				.formParam("examCertificateId", examCertificateId).formParam("passingScore", passingScore)
				.formParam("cutScreenCount", cutScreenCount).formParam("reExamRule", reExamRule)
				.formParam("reExamNumber", reExamNumber).formParam("scoreRule", scoreRule).formParam("examScore", examScore)
				.formParam("isGetScore", isGetScore).formParam("supervisorPaper", supervisorPaper)
				.formParam("supervisorId", supervisorId).formParam("authorityRange",authorityRange)
				.post(addUrl).asString();
	}

	// 新增常规流程使用考试任务
	public static String creatExamTask(String questionMode, String showKnowledge, String passLine, String totalScore,
			String answerParsing, String title, String beginTime, String endTime, String isAllIn, String userIds,
			String paperId, String examDuration, String cheatFlag, String repeatExam, String markType,
			String examCertificateId, String passingScore, String cutScreenCount, String reExamRule,
			String reExamNumber, String scoreRule, String isGetScore, String supervisorPaper, String supervisorId) {
		return given().queryParam("access_token", token).formParam("questionMode", questionMode)
				.formParam("showKnowledge", showKnowledge).formParam("passLine", passLine)
				.formParam("totalScore", totalScore).formParam("answerParsing", answerParsing).formParam("title", title)
				.formParam("beginTime", beginTime).formParam("endTime", endTime).formParam("isAllIn", isAllIn)
				.formParam("userIds", userIds).formParam("paperId", paperId).formParam("examDuration", examDuration)
				.formParam("cheatFlag", cheatFlag).formParam("repeatExam", repeatExam).formParam("markType", markType)
				.formParam("examCertificateId", examCertificateId).formParam("passingScore", passingScore)
				.formParam("cutScreenCount", cutScreenCount).formParam("reExamRule", reExamRule)
				.formParam("reExamNumber", reExamNumber).formParam("scoreRule", scoreRule)
				.formParam("isGetScore", isGetScore).formParam("supervisorPaper", supervisorPaper)
				.formParam("supervisorId", supervisorId).post(addUrl).asString();
	}

	// 查看不同考试状态的人数
	public static String queryUntestedInfo(String keyword, String planId, String type) {
		return GetRequestTools.RequestQueryParamsByGet("keyword", keyword, "planId", planId, "type", type,
				"access_token", token, queryUntestedInfoUrl);
	}
	
	// 查看考试任务详情
		public static String queryInfo(String name) {
			return GetRequestTools.RequestQueryParamsByGet("access_token", token, getInfoUrl(getIdByKeyword(name)));

		}
		
		public static String queryInfoById(String id) {
			return GetRequestTools.RequestQueryParamsByGet("access_token", token, getInfoUrl(id));

		}

	// 新增常规流程使用考试任务
	public static String creatRewardExamTask(String questionMode, String showKnowledge, String passLine,
			String totalScore, String answerParsing, String title, String beginTime, String endTime, String isAllIn,
			String userIds, String paperId, String examDuration, String cheatFlag, String repeatExam, String markType,
			String examCertificateId, String passingScore, String cutScreenCount, String reExamRule,
			String reExamNumber, String scoreRule, String isGetScore, String supervisorPaper, String supervisorId,
			String examScore) {
		return given().queryParam("access_token", token).formParam("questionMode", questionMode)
				.formParam("showKnowledge", showKnowledge).formParam("passLine", passLine)
				.formParam("totalScore", totalScore).formParam("answerParsing", answerParsing).formParam("title", title)
				.formParam("beginTime", beginTime).formParam("endTime", endTime).formParam("isAllIn", isAllIn)
				.formParam("userIds", userIds).formParam("paperId", paperId).formParam("examDuration", examDuration)
				.formParam("cheatFlag", cheatFlag).formParam("repeatExam", repeatExam).formParam("markType", markType)
				.formParam("examCertificateId", examCertificateId).formParam("passingScore", passingScore)
				.formParam("cutScreenCount", 5).formParam("reExamRule", reExamRule)
				.formParam("reExamNumber", reExamNumber).formParam("scoreRule", scoreRule)
				.formParam("isGetScore", isGetScore).formParam("supervisorPaper", supervisorPaper)
				.formParam("supervisorId", supervisorId).formParam("examScore", examScore).post(addUrl).asString();
	}

	// 编辑考试任务
	public static String eidtExamTask(String questionMode, String showKnowledge, String passLine, String totalScore,
			String answerParsing, String title, String beginTime, String endTime, String isAllIn, String userIds,
			String paperId, String examDuration, String cheatFlag, String repeatExam, String markType,
			String examCertificateId, String passingScore, String cutScreenCount, String reExamRule,
			String reExamNumber, String scoreRule, String isGetScore, String supervisorPaper, String supervisorId,
			String id, String planStatus) {
		return given().queryParam("access_token", token).formParam("questionMode", questionMode)
				.formParam("showKnowledge", showKnowledge).formParam("passLine", passLine)
				.formParam("totalScore", totalScore).formParam("answerParsing", answerParsing).formParam("title", title)
				.formParam("beginTime", beginTime).formParam("endTime", endTime).formParam("isAllIn", isAllIn)
				.formParam("userIds", userIds).formParam("paperId", paperId).formParam("examDuration", examDuration)
				.formParam("cheatFlag", cheatFlag).formParam("repeatExam", repeatExam).formParam("markType", markType)
				.formParam("examCertificateId", examCertificateId).formParam("passingScore", passingScore)
				.formParam("cutScreenCount", cutScreenCount).formParam("reExamRule", reExamRule)
				.formParam("reExamNumber", reExamNumber).formParam("scoreRule", scoreRule)
				.formParam("isGetScore", isGetScore).formParam("supervisorPaper", supervisorPaper)
				.formParam("supervisorId", supervisorId).formParam("planStatus", planStatus).formParam("id", id)
				.post(editUrl).asString();
	}

	// 崔考
	public static String remindExam(String planId, String noticeType) {
		return GetRequestTools.RequestParamsByGet("planId", planId, "noticeType", noticeType, "access_token",
				token, remindUrl);

	}

	// 延期考试任务
	public static String delayExamTask(String endTime, String id) {
		return PostRequestTools.RequestFormDataByPost(token, "endTime", endTime, "id", id, delayUrl);
	}

	// 变更考试任务
	public static String changeExamTask(String id, String isNoticeAll, String isAllIn, String groupIds, String userIds,
			String departmentIds, String postIds) {
		return PostRequestTools.RequestFormDataByPost(token, "id", id, "isNoticeAll", isNoticeAll, "isAllIn",
				isAllIn, "groupIds", groupIds, "userIds", userIds, "departmentIds", departmentIds, "postIds", postIds,
				changeUrl);

	}

	// 删除考试任务
	public static String deleteExamTask(String id) {
		return PostRequestTools.RequestFormDataByPost(token, "id", id, deleteUrl);

	}

}
