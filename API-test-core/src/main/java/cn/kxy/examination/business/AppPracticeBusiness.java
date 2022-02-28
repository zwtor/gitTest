package cn.kxy.examination.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;

public class AppPracticeBusiness {
	public static String token = TokenData.getMangerToken();
	public static String exam_url = ExamDataUrl.getNewExamUrl();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
    public static String  enterprise_url  =  EnterpriseDataUrl.getEnterpriseUrl();
	public static String user_id = UserBusiness.getUserId();
	
	
	public static String queryListUrl() {
		return enterprise_url + "v2/" + enterpriseId + "/users/" + user_id + "/practices";
	}

	public static String queryQuestionUrl(String id) {
		return exam_url + "v2/" + enterpriseId + "/users/" + user_id + "/practices/"
				+id + "/query_question";

	}

	public static String alertUrl() {
		return exam_url + "v2/" + enterpriseId + "/users/" + user_id + "/practices/alert/query";
	}

	public static String queryWrongUrl(String id) {
		return exam_url + "v2/" + enterpriseId + "/users/" +user_id + "/practices/"
				+ id + "/query_wrong_question";

	}

	public static String query_question_bank_url(String id) {
		return exam_url + "v2/" + enterpriseId + "/users/" + user_id+ "/practices/"
				+ id + "/query_question_bank";
	}

	public static String submitUrl(String id,String questionsid) {
		return exam_url + "v2/" + enterpriseId + "/users/" + user_id + "/practices/"
				+ id + "/questions/" + questionsid + "/save";
	}

	// 手机端查看练习列表页
	public static String queryPracticeList(String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "keyword", keyword, "page_number", "1",
				"page_size", "20", queryListUrl());
	}

	// 查询练习的详情
	public static String queryPracticeInfo(String type, String continueType, String id, String question_bank_id) {
		return GetRequestTools.RequestParamsByGet("type", type, "continue", continueType, "access_token", token,
				"question_bank_id", question_bank_id, queryQuestionUrl(id));
	}

	// 查询题库中的练习
	public static String query_question_bank(String continuetype, String id) {
		return GetRequestTools.RequestQueryParamsByGet("continue", continuetype, "access_token", token,
				query_question_bank_url(id));

	}

	// 根据name获取题库的id
	public static String getBankIdByName(String id) {
		String res = query_question_bank("false", id);
		String id0 = (String) JSONPath.read(res, "$.list[0].question_bank_id");
		return id0;
	}

	// 查询错题详情
	public static String queryWrongInfo(String type, String continuetype, String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "type", type, "continue", continuetype,
				queryWrongUrl(id));
	}

	// 是否弹窗
	public static String queryIsAlert(String type) {
		return GetRequestTools.RequestQueryParamsByGet("type", type, "access_token", token, alertUrl());
	}

	// 获取第一个问题的id
	public static String getFirstIdByQusetion(String id) {
		String res = queryPracticeInfo("fixed", "false", id, "");
		String id0 = (String) JSONPath.read(res, "$.questions[0].id");
		return id0;
	}

	// 获取第二个问题的id
	public static String getSecondIdByQusetion(String id) {
		String res = queryPracticeInfo("fixed", "false", id, "");
		String id0 = (String) JSONPath.read(res, "$.questions[1].id");
		return id0;
	}

	// 获取第一个问题正确答案的id
	public static String getFirstRightAnwserIdByQusetion(String id) {
		String res = queryPracticeInfo("fixed", "false", id, "");
		String id0 = (String) JSONPath.read(res, "$.questions[0].options[2].id");
		return id0;
	}

	// 获取第二个问题正确答案的id
	public static String getSecondRightAnwserIdByQusetion(String id) {
		String res = queryPracticeInfo("fixed", "false", id, "");
		String id0 = (String) JSONPath.read(res, "$.questions[1].options[2].id");
		return id0;
	}

	// 获取第一个问题错误答案的id
	public static String getFirstFalseAnwserIdByQusetion(String id) {
		String res = queryPracticeInfo("fixed", "false", id, "");
		String id0 = (String) JSONPath.read(res, "$.questions[0].options[0].id");
		return id0;
	}

	// 获取第二个问题错误答案的id
	public static String getSecondFalseAnwserIdByQusetion(String id) {
		String res = queryPracticeInfo("fixed", "false", id, "");
		String id0 = (String) JSONPath.read(res, "$.questions[1].options[0].id");
		return id0;
	}

	// 交卷
	public static String submitFirst(String id, String answerId,String is_correct, String order_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + "	\"type\": \"single\",\r\n" + "	\"answer\": [\""
				+ answerId + "\"],\r\n" + "	\"is_correct\": "+is_correct+",\r\n" + "	\"order_id\": " + order_id + ",\r\n"
				+ "	\"access_token\": \"" + token + "\"\r\n" + "}", token, submitUrl(id,getFirstIdByQusetion(id)));

	}
	
	public static String submitSecond(String id, String answerId,String is_correct, String order_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + "	\"type\": \"single\",\r\n" + "	\"answer\": [\""
				+ answerId + "\"],\r\n" + "	\"is_correct\": "+is_correct+",\r\n" + "	\"order_id\": " + order_id + ",\r\n"
				+ "	\"access_token\": \"" + token + "\"\r\n" + "}", token, submitUrl(id,getSecondIdByQusetion(id)));

	}
}
