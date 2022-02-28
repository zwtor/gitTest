package cn.kxy.study.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;
import com.lazy.httpclient.utils.HttpResponse;

public class StudyHistoryResult {
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String exam_url = ExamDataUrl.getNewExamUrl();
	public static String user_id = UserBusiness.getUserId();
	public static String add_study_task_url =enterprise_url + "plan/study/add";
	
	public static String queryExamInfoUrl (String exam_id) {
		return exam_url + "v2/"+enterprise_id+"/users/"+user_id+"/exams/"+exam_id+"/query";
	}
	public static String queryHistoricalResultUrl(String plan_id) {
		return exam_url + "v2/"+enterprise_id+"/plans/"+plan_id+"/exam/historical_achievements";
	}
	
	public static String saveUrl (String exam_id) {
		return exam_url + "/v2/"+enterprise_id+"/users/"+user_id+"/exams/"+exam_id+"/save";
	}
	
	public static String submitUrl(String exam_id) {
		return exam_url + "v2/"+enterprise_id+"/users/"+user_id+"/exams/"+exam_id+"/submit";
	}
	
	public static String queryResultUrl (String exam_id) {
		return exam_url + "v2/"+enterprise_id+"/exams/"+exam_id+"/result";
	}
	
	public static String historicalAchievementsExportUrl (String id) {
		return exam_url + "v2/"+enterprise_id+"/plans/"+id+"/exam/historical_achievements_export";
	}
	
	public static int getExportHistoryResultCode(String id) {
		 HttpResponse resp = HttpRequest.get(historicalAchievementsExportUrl(id)).query("user_id", user_id).
				 query("access_token", token).contentType("application/json;charset=UTF-8")
					.send();
				return resp.statusCode();
	}
	
	/**   
	 * @Title: queryExamResult   
	 * @Description: TODO (查询历史成绩)
	 * @param: @param exam_id
	 * @param: @param makeup_count
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryExamResult(String exam_id,String makeup_count) {
		return HttpRequest.get(queryResultUrl(exam_id)).query("access_token", token).query("userId", user_id)
				.query("makeup_count", makeup_count).send().body();
	}
	
	/**   
	 * @Title: addManualMarking   
	 * @Description: TODO (添加人工阅卷的学习任务，保存最新成绩)
	 * @param: @param title
	 * @param: @param paper_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addManualMarking(String title ,String paper_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"beginTime\": "+DateUtil.getTimeStamp(0, "")+", \r\n" + 
				"  \"endTime\": "+DateUtil.getTimeStamp(7, "")+", \r\n" + 
				"  \"dingImgUrl\": \"\", \r\n" + 
				"  \"departmentIds\": \"\", \r\n" + 
				"  \"groupIds\": \"\", \r\n" + 
				"  \"isGetScore\": true, \r\n" + 
				"  \"isNoticeAll\": 0, \r\n" + 
				"  \"planCertificateId\": \"\", \r\n" + 
				"  \"postIds\": \"\", \r\n" + 
				"  \"progress\": 0, \r\n" + 
				"  \"studyScore\": {\r\n" + 
				"    \"finishScore\": 0, \r\n" + 
				"    \"unFinishScore\": 0\r\n" + 
				"  }, \r\n" + 
				"  \"studyTimeLimit\": 0, \r\n" + 
				"  \"supervisorId\": \""+user_id+"\", \r\n" + 
				"  \"supervisorPaper\": true, \r\n" + 
				"  \"times\": 1, \r\n" + 
				"  \"userIds\": \""+user_id+"\", \r\n" + 
				"  \"stageJson\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stageName\": \"stage one\", \r\n" + 
				"      \"isOrder\": false, \r\n" + 
				"      \"stageSort\": 1, \r\n" + 
				"      \"course\": [\r\n" + 
				"        {\r\n" + 
				"          \"courseSort\": 0, \r\n" + 
				"          \"title\": \"PassedPapermark\", \r\n" + 
				"          \"type\": \"ex\", \r\n" + 
				"          \"cheatFlag\": 0, \r\n" + 
				"          \"examDuration\": 45, \r\n" + 
				"          \"summary\": \"this is a summary\", \r\n" + 
				"          \"flag\": 2, \r\n" + 
				"          \"courseId\": \"\", \r\n" + 
				"          \"markType\": 3, \r\n" + 
				"          \"paperId\": \""+paper_id+"\", \r\n" + 
				"          \"paperTitle\": \"PassedPaper\", \r\n" + 
				"          \"passLine\": 60, \r\n" + 
				"          \"questionBankList\": \"\", \r\n" + 
				"          \"questionMode\": 1, \r\n" + 
				"          \"showKnowledge\": \"show\", \r\n" + 
				"          \"repeatExam\": true, \r\n" + 
				"          \"multipleCount\": \"\", \r\n" + 
				"          \"multipleUnitScore\": \"\", \r\n" + 
				"          \"fillBlankCount\": \"\", \r\n" + 
				"          \"fillBlankUnitScore\": \"\", \r\n" + 
				"          \"shortAnswerCount\": \"\", \r\n" + 
				"          \"shortAnswerUnitScore\": \"\", \r\n" + 
				"          \"singleCount\": \"\", \r\n" + 
				"          \"singleUnitScore\": \"\", \r\n" + 
				"          \"trueOrFalseCount\": \"\", \r\n" + 
				"          \"trueOrFalseUnitScore\": \"\", \r\n" + 
				"          \"totalScore\": 100, \r\n" + 
				"          \"answerParsing\": 5, \r\n" + 
				"          \"passingScore\": \"60.0\", \r\n" + 
				"          \"cutScreenCount\": -1, \r\n" + 
				"          \"reExamRule\": 1, \r\n" + 
				"          \"reExamNumber\": 0, \r\n" + 
				"          \"viewRule\": 1, \r\n" +
				"          \"scoreRule\": 0\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"stagePass\": false, \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_study_task_url);
	}
	
	public static String submitAnswer(String exam_id,String first_question_id,String first_answer,String second_question_id,String second_answer
			,String makeup_count) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"questions\": [\r\n" + 
				"    {\r\n" + 
				"      \"id\": \""+first_question_id+"\", \r\n" + 
				"      \"answer\": ["+first_answer+"]\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"id\": \""+second_question_id+"\", \r\n" + 
				"      \"answer\": ["+second_answer+"]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"makeup_count\": "+makeup_count+", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, "status", "manual", submitUrl(exam_id));
	}
	
	/**   
	 * @Title: saveAnswer   
	 * @Description: TODO 保存答案
	 * @param: @param exam_id
	 * @param: @param questions_id
	 * @param: @param answer
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String saveAnswer(String exam_id,String questions_id,String answer) {
		return PostRequestTools.RequestBodyByPost("{\"questions\":[{\"id\":\""+questions_id+"\",\"answer\":[\""+answer+"\"]}],\"access_token\":\""+token+"\"}", token, saveUrl(exam_id));
	}
	
	/**   
	 * @Title: queryExamInfo   
	 * @Description: TODO 查看学习任务的考试详情
	 * @param: @param exam_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryExamInfo(String exam_id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryExamInfoUrl(exam_id));
	}
	
	/**   
	 * @Title: queryHistoricalResult   
	 * @Description: TODO  (学习任务查询历史成绩)
	 * @param: @param plan_id
	 * @param: @param commit_time_start
	 * @param: @param commit_time_end
	 * @param: @param user_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryHistoricalResult(String plan_id,String commit_time_start,String commit_time_end) {
		return HttpRequest.get(queryHistoricalResultUrl(plan_id)).query("commit_time_start", commit_time_start).query("commit_time_end", commit_time_end)
				.query("user_id", user_id).query("page_number", "1").query("page_size", "20").query("access_token",token).send().body();
	}
	
	/**   
	 * @Title: addExamHistoryResult   
	 * @Description: TODO  添加
	 * @param: @param title
	 * @param: @param exam_title
	 * @param: @param paperId
	 * @param: @param scoreRule 0-最新成绩，1-最高成绩
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addExamHistoryResult(String title,String exam_title,String paperId,String scoreRule) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"beginTime\": "+DateUtil.getTimeStamp(0, "")+", \r\n" + 
				"  \"endTime\": "+DateUtil.getTimeStamp(7, "")+", \r\n" + 
				"  \"dingImgUrl\": \"\", \r\n" + 
				"  \"departmentIds\": \"\", \r\n" + 
				"  \"groupIds\": \"\", \r\n" + 
				"  \"isGetScore\": true, \r\n" + 
				"  \"isNoticeAll\": 0, \r\n" + 
				"  \"planCertificateId\": \"\", \r\n" + 
				"  \"postIds\": \"\", \r\n" + 
				"  \"progress\": 0, \r\n" + 
				"  \"studyScore\": {\r\n" + 
				"    \"finishScore\": 0, \r\n" + 
				"    \"unFinishScore\": 0\r\n" + 
				"  }, \r\n" + 
				"  \"studyTimeLimit\": 0, \r\n" + 
				"  \"supervisorId\": \""+user_id+"\", \r\n" + 
				"  \"supervisorPaper\": true, \r\n" + 
				"  \"times\": 1, \r\n" + 
				"  \"userIds\": \""+user_id+"\", \r\n" + 
				"  \"stageJson\": [\r\n" + 
				"    {\r\n" + 
				"      \"content\": \"\", \r\n" + 
				"      \"stageName\": \"Stage One\", \r\n" + 
				"      \"isOrder\": false, \r\n" + 
				"      \"stageSort\": 1, \r\n" + 
				"      \"course\": [\r\n" + 
				"        {\r\n" + 
				"          \"courseSort\": 0, \r\n" + 
				"          \"title\": \""+exam_title+"\", \r\n" + 
				"          \"type\": \"ex\", \r\n" + 
				"          \"cheatFlag\": 0, \r\n" + 
				"          \"examDuration\": 45, \r\n" + 
				"          \"summary\": \"this is a summary\", \r\n" + 
				"          \"flag\": 2, \r\n" + 
				"          \"courseId\": \"\", \r\n" + 
				"          \"markType\": 1, \r\n" + 
				"          \"paperId\": \""+paperId+"\", \r\n" + 
				"          \"paperTitle\": \"PassedPaper\", \r\n" + 
				"          \"passLine\": 60, \r\n" + 
				"          \"questionBankList\": \"\", \r\n" + 
				"          \"questionMode\": 1, \r\n" + 
				"          \"showKnowledge\": \"show\", \r\n" + 
				"          \"repeatExam\": true, \r\n" + 
				"          \"multipleCount\": \"\", \r\n" + 
				"          \"multipleUnitScore\": \"\", \r\n" + 
				"          \"fillBlankCount\": \"\", \r\n" + 
				"          \"fillBlankUnitScore\": \"\", \r\n" + 
				"          \"shortAnswerCount\": \"\", \r\n" + 
				"          \"shortAnswerUnitScore\": \"\", \r\n" + 
				"          \"singleCount\": \"\", \r\n" + 
				"          \"singleUnitScore\": \"\", \r\n" + 
				"          \"trueOrFalseCount\": \"\", \r\n" + 
				"          \"trueOrFalseUnitScore\": \"\", \r\n" + 
				"          \"totalScore\": 100, \r\n" + 
				"          \"answerParsing\": 5, \r\n" + 
				"          \"passingScore\": \"60.0\", \r\n" + 
				"          \"cutScreenCount\": -1, \r\n" + 
				"          \"reExamRule\": 1, \r\n" + 
				"          \"reExamNumber\": 0, \r\n" + 
				"          \"viewRule\": 1, \r\n" +
				"          \"scoreRule\": "+scoreRule+"\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"stagePass\": false, \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_study_task_url);
	}
}
