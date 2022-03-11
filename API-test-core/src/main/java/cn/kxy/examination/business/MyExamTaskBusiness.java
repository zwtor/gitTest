package cn.kxy.examination.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class MyExamTaskBusiness {
	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	static String user_id = UserBusiness.getUserId();
	public static String queryListURl() {
		return exam_url + "v2/" + enterpriseId + "/users/" + user_id + "/exams";
	}

	public static String checkUrl(String name) {

		return exam_url + "v2/" + enterpriseId + "/users/" + user_id+ "/exams/"
				+ getFirstId(name) + "/check";
	}

	public static String submitUrl(String name) {

		return exam_url + "v2/" + enterpriseId + "/users/" + user_id+ "/exams/"
				+ getFirstId(name) + "/submit";
	}

	public static String queryresultUrl(String name) {
		return exam_url + "v2/" + enterpriseId + "/users/" + user_id + "/exams/"
				+ getFirstId(name) + "/result";
	}

	public static String queryresultUrlById(String id) {
		return exam_url + "v2/" + enterpriseId + "/users/" + user_id + "/exams/"
				+ id + "/result";
	}
	
	public static String queryanswer_analysisUrl(String name) {
		return exam_url + "v2/" + enterpriseId + "/users/" + user_id + "/exams/"
				+ getFirstId(name) + "/answer_analysis";
	}
	
	public static String queryanswer_analysisUrl_byid(String id) {
		return exam_url + "v2/" + enterpriseId + "/users/" + user_id + "/exams/"
				+ id + "/answer_analysis";
	}
	
	public static String queryAnswerAnalysisById(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "userId",user_id,queryanswer_analysisUrl_byid(id));

	}

	// 查看答案解析
	public static String queryAnswerAnalysis(String name) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryanswer_analysisUrl(name));

	}

	public static String submitExamUrl(String name) {
		return exam_url + "v2/" + enterpriseId + "/users/" + user_id + "/exams/"
				+ ExaminationTaskBusiness.getIdByKeyword(name)+ "/submit";
	}

	public static String submitExamUrlById(String id) {
		return exam_url + "v2/" + enterpriseId + "/users/" + user_id + "/exams/"
				+ id+ "/submit";
	}
	
	public static String queryInfoUrl(String name) {
		return exam_url + "v2/" + enterpriseId + "/users/" + user_id + "/exams/"
				+ getIdByKeyword(name) + "/query";
	}
	
	public static String queryInfoByIdUrl(String id) {
		return exam_url + "v2/" + enterpriseId + "/users/" + user_id + "/exams/"
				+ id + "/query";
	}
	
	public static String query_my_info (String exam_id) {
		return exam_url + "v2/"+enterpriseId+"/exams/"+exam_id+"/query";
	}
	
	public static String queryMyExamInfo(String id) {
		return HttpRequest.get(queryInfoByIdUrl(id)).query("access_token", token).send().body();
	}
	
	public static String queryMyInfo(String id) {
		return HttpRequest.get(query_my_info(id)).query("access_token", token).send().body();
	}

	public static String submitBlankExamById(String id) {
		String res01 = GetRequestTools.RequestQueryParamsByGet("access_token", token, queryInfoByIdUrl(id));

		String question_id01 = (String) JSONPath.read(res01, "$.questions[0].id");
		String question_id02 = (String) JSONPath.read(res01, "$.questions[1].id");
		String res = PostRequestTools.RequestBodyByPost(
				"{\"questions\":[{\"id\":\"" + question_id01 + "\",\"answer\":[]}," + "{\"id\":\"" + question_id02
						+ "\",\"answer\":[]}]," + "\"access_token\":\"" + token + "\"}",
				token, "status", "manual", submitExamUrlById(id));
		return res;
	}
	
	// 交白卷
	public static String submitBlankExam(String name) {
		String res01 = GetRequestTools.RequestQueryParamsByGet("access_token", token, queryInfoUrl(name));

		String question_id01 = (String) JSONPath.read(res01, "$.questions[0].id");
		String question_id02 = (String) JSONPath.read(res01, "$.questions[1].id");
		String res = PostRequestTools.RequestBodyByPost(
				"{\"questions\":[{\"id\":\"" + question_id01 + "\",\"answer\":[]}," + "{\"id\":\"" + question_id02
						+ "\",\"answer\":[]}]," + "\"access_token\":\"" + token + "\"}",
				token, "status", "manual", submitExamUrl(name));
		return res;
	}

	// 交白卷含答案id
	public static String submitFailBlankExam(String name) {
		String res01 = GetRequestTools.RequestQueryParamsByGet("access_token", token, queryInfoUrl(name));

		String question_id01 = (String) JSONPath.read(res01, "$.questions[0].id");
		String question_id02 = (String) JSONPath.read(res01, "$.questions[1].id");
		String question_id03 = (String) JSONPath.read(res01, "$.questions[2].id");
		String question_id04 = (String) JSONPath.read(res01, "$.questions[3].id");
		String question_id05 = (String) JSONPath.read(res01, "$.questions[4].id");

		String question_id01_type = (String) JSONPath.read(res01, "$.questions[0].type");
		String question_id02_type = (String) JSONPath.read(res01, "$.questions[1].type");
		String question_id03_type = (String) JSONPath.read(res01, "$.questions[2].type");
		String question_id04_type = (String) JSONPath.read(res01, "$.questions[3].type");
		String question_id05_type = (String) JSONPath.read(res01, "$.questions[4].type");
		String anwser_id01 = "";

		String anwser_id03 = "";
		String anwser_id04 = "";
		String anwser_id05 = "";

		if (question_id01_type != "blank" && question_id01_type != "short_answer") {
			anwser_id01 = (String) JSONPath.read(res01, "$.questions[0].options[0].id");
		} else {
			anwser_id01 = "1";
		}
		String anwser_id02 = "";
		if (question_id02_type != "blank" && question_id02_type != "short_answer") {
			anwser_id02 = (String) JSONPath.read(res01, "$.questions[1].options[0].id");
		} else {
			anwser_id02 = "1";
		}

		if (question_id03_type != "blank" && question_id03_type != "short_answer") {
			anwser_id03 = (String) JSONPath.read(res01, "$.questions[2].options[0].id");
		} else {
			anwser_id03 = "1";
		}

		if (question_id04_type != "blank" && question_id04_type != "short_answer") {
			anwser_id04 = (String) JSONPath.read(res01, "$.questions[3].options[0].id");
		} else {
			anwser_id04 = "1";
		}

		if (question_id05_type != "blank" && question_id05_type != "short_answer") {
			anwser_id05 = (String) JSONPath.read(res01, "$.questions[4].options[0].id");
		} else {
			anwser_id05 = "1";
		}
		String res = PostRequestTools.RequestBodyByPost(
				"{\"questions\":[{\"id\":\"" + question_id01 + "\",\"answer\":[\"" + anwser_id01 + "\"]},{\"id\":\""
						+ question_id02 + "\"," + "\"answer\":[\"" + anwser_id02 + "\",\"1\"]},{\"id\":\""
						+ question_id03 + "\",\"answer\":[\"" + anwser_id03 + "\"]},{\"id\":\"" + question_id04 + "\","
						+ "\"answer\":[\"" + anwser_id04 + "\"]},{\"id\":\"" + question_id05 + "\",\"answer\":[\""
						+ anwser_id05 + "\"]}]," + "\"access_token\":\"" + token + "\"}",
				token, "status", "manual", submitExamUrl(name));
		return res;
	}

	// 查看考试结果
	public static String queryExamResult(String name) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryresultUrl(name));

	}
	
	public static String queryExamResultById(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id",enterpriseId,"user_id",user_id,queryresultUrlById(id));

	}
	public static String getFirstName() {
		String res = queryMyExamTask("all", "");
		String id = (String) JSONPath.read(res, "$.list[0].title");
		return id;
	}
	
	public static String getFirstId(String name) {
		String res = queryMyExamTask("all", name);
		String id = (String) JSONPath.read(res, "$.list[0].id");
		return id;
	}

	// 查询我的考试任务列表
	public static String queryMyExamTask(String status, String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "status", status, "keyword",
				keyword, "page_size", "20", "page_number", "1", queryListURl());
	}
    //
	public static String getIdByKeyword(String name) {
		String res = queryMyExamTask("all", name);
		String id = (String) JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	// 检查能否考试
	public static String checkIsCanExam(String name) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, checkUrl(name));
	}

	public static String checkUrlById(String id ) {
		return exam_url + "v2/" + enterpriseId + "/users/" + user_id+ "/exams/"
				+ id + "/check";
	}

	public static String checkIsCanExamById(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, checkUrlById(id));
	}

}
