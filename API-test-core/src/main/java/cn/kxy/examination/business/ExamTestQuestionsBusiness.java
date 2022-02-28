package cn.kxy.examination.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.common.utils.CommonData;
import com.lazy.httpclient.utils.HttpRequest;

public class ExamTestQuestionsBusiness {
	public static String token = TokenData.getMangerToken();
	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String enterpriseUrl=  EnterpriseDataUrl.getEnterpriseUrl();
	public static String enterpriseId=EnterpriseData.getEnterpriseId();
	public static String AddUrl = exam_url + "course/question/add";

	public static String queryOneUrl = exam_url + "course/question/getOne";

	public static String queryListUrl = exam_url + "course/question/getList";
	
	public static String isReferenceWithPlanUrl = exam_url +  "course/question/isReferenceWithPlan";
	
	public static String deleteUrl = exam_url + "course/question/delete";
	
	public static String editUrl = exam_url +  "course/question/edit";
	
	public static String moveUrl = exam_url +"questionBank/moveQuestionsBank";
	
	public static String is_can_delete_url = exam_url + "v2/"+enterpriseId+"/question/is_can_delete";
	
	public static String check_delete_url =enterpriseUrl + "v2/"+enterpriseId+"/users/"+UserBusiness.getUserId()+"/delete/check";
	
	public static String checkDelete (String id) {
		return HttpRequest.get(check_delete_url).query("id", id).query("type", "question").query("page_number","1").
				query("page_size", "10").query("access_token",token).send().body();
	}
	
	
	//判断试题被引用是是否可删除
	public static String isCanDelete(String question_ids) {
		return HttpRequest.get(is_can_delete_url).query("question_ids",question_ids).query("access_token",token ).send().body();
	}
	
	public static String editQuestion(String id,String questionBankId ,String option_id01,String option_id02) {
		return PostRequestTools.RequestFormDataByPost(token, "analysis", "selenium", "answer", "[{\"title\":\"driver\","
				+ "\"id\":\""+option_id01+"\",\"answerImage\":\"\"},{\"title\":\"close\",\"isAnswer\":true,\"id\":"
				+ "\""+option_id02+"\",\"answerImage\":\"\"}]","difficulty","1","id",id,"questionBankId",questionBankId,
				"title","Selenium Study","type","1",editUrl);
	}
	
	//新增试题
	public static String addQuestions(String title, String type, String difficulty, String analysis,
			String knowledgePoint, String titleImage, String analysisImage, String answer) {

		String knowledge[] = { knowledgePoint };
		return PostRequestTools.RequestFormDataByPost(token, "questionBankId",
				ExaminationBusines.getFirstId(), "title", title, "type", type, "difficulty", difficulty, "analysis",
				analysis, "knowledgePoint", knowledge, "titleImage", titleImage, "analysisImage", analysisImage,"isOrderMatch","true",
				"answer", answer, AddUrl);

	}
	
	
	public static String addQuestions(String title, String questionBankId,String type, String difficulty, String analysis,
			String knowledgePoint, String titleImage, String analysisImage, String answer) {

		return PostRequestTools.RequestFormDataByPost(token, "questionBankId",
				questionBankId, "title", title, "type", type, "difficulty", difficulty, "isOrderMatch","true","analysis",
				analysis, "knowledgePoint", "[\""+knowledgePoint+"\"]", "titleImage", titleImage, "analysisImage", analysisImage,
				"answer", answer, AddUrl);
	}
	
	//编辑试题
	public static String editQuestions(String title, String type, String difficulty, String analysis,
			String knowledgePoint, String titleImage, String analysisImage, String answer,String id) {
		return PostRequestTools.RequestFormDataByPost(token, "questionBankId",
				ExaminationBusines.getFirstId(), "title", title, "type", type, "difficulty", difficulty, "analysis",
				analysis, "knowledgePoint", knowledgePoint, "titleImage", titleImage, "analysisImage", analysisImage,
				"id",id,"answer", answer, editUrl);
	}

	//获取第一个试题的id
	public static String getFirstExamId() {
		
		String res = GetRequestTools.RequestQueryParamsByGet("keyword", "", "onlySeeMe", "false", "questionBankId",
				ExaminationBusines.getFirstId(), "type", "", "pageSize", "20", "pageNumber", "1", "access_token", token,
				queryListUrl);
		
		String id = (String) JSONPath.read(res, "$.list[0].id");
		return id ;

	}
	
	
	public static void creatQuestionBank() {
		if (ExaminationBusines.getFirstTotalCount()==0|| ExaminationBusines.getFirstName()==null) {
			ExaminationBusines.createQuestionBank("exam"+CommonData.getStringRandom(3), "isAll", "", "");
			ExamTestQuestionsBusiness.addQuestions("singleChoice", "1", "1", "", "", "", "", "[{\"title\":\"one\",\"id\":\"\"},{\"title\":\"two\",\"isAnswer\":true,\"id\":\"\"}]");
		}
	}
	

	//获取试题详细信息
	public static String getQuestionsInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("id", id, "access_token", token, queryOneUrl);
	}
	//获取列表
	public static String getQuestionsList(String keyword, String onlySeeMe,String type) {
		
		String res = GetRequestTools.RequestQueryParamsByGet("keyword", keyword, "onlySeeMe", onlySeeMe, "questionBankId",
				ExaminationBusines.getFirstId(), "type", type, "pageSize", "20", "pageNumber", "1", "access_token", token,
				queryListUrl);
		return res ;
	}
	
	public static String getQuestionsList(String keyword, String questionBankId,String onlySeeMe,String type) {
		
		String res = GetRequestTools.RequestQueryParamsByGet("keyword", keyword, "onlySeeMe", onlySeeMe, "questionBankId",
				questionBankId, "type", type, "pageSize", "20", "pageNumber", "1", "access_token", token,
				queryListUrl);
		return res ;
	}
	
	//移动试题
	public static String moveQuestionBank(String questionIds,String questionBankId) {
		return PostRequestTools.RequestFormDataByPost(token, "questionIds",questionIds, "questionBankId",questionBankId, moveUrl);
		
	}
	
	//是否可以删除弹框
	public static String isReferenceWithPlan(String id) {
		
		return GetRequestTools.RequestParamsByGet("id",id, "access_token",token,isReferenceWithPlanUrl);
	}
	//删除试题
	public static String deleteQuestions(String id) {
		return PostRequestTools.RequestFormDataByPost(token, "ids", id, deleteUrl);
	}

}
