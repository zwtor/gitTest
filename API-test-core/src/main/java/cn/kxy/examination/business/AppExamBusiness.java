package cn.kxy.examination.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;

public class AppExamBusiness {
	public static String token = TokenData.getMangerToken();
	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String analysisUrl(String status) {
		return exam_url +"v2/"+enterpriseId+"/users/"+user_id+"/exams/"+getFirstIdByStatus(status)+"/answer_analysis";
	}
	
	public static String analysisUrlById(String id) {
		return exam_url +"v2/"+enterpriseId+"/users/"+user_id+"/exams/"+id+"/answer_analysis";
	}
	
	public static String queryListUrl() {
		return exam_url + "v2/" + enterpriseId + "/users/" + user_id + "/exams";
	}

	//移动端考试说明页url
	public static String queryListInfoUrl(String status) {
		return exam_url + "v2/" + enterpriseId + "/exams/"+getFirstIdByStatus(status)+"/query";
	}
	
	public static String queryListInfoUrlByid(String id) {
		return exam_url + "v2/" + enterpriseId + "/exams/"+id+"/query";
	}

	//移动端考试详情
	public static String queryInfoUrl (String id) {
		return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+id+"/query";
	}
	
	
	public static String rankingUrl(String id) {
		return exam_url +"v2/exams/"+id+"/mobile/ranking";
	}
	
	public static String resultUrl(String status) {
		return  exam_url +"v2/"+enterpriseId+"/users/"+user_id+"/exams/"+getFirstIdByStatus(status)+"/result";
	}
	
	public static String resultUrlByid(String id) {
		return  exam_url +"v2/"+enterpriseId+"/users/"+user_id+"/exams/"+id+"/result";
	}
	
	public static String myranking_url(String id) {
		return exam_url + "v2/"+enterpriseId+"/users/"+user_id+"/exams/"+id+"/myranking";
	}
	
	public static String queryMyranking(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "enterprise_id",enterpriseId,"user_id",user_id,myranking_url(id));
	}
	
	public static String queryResult(String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, resultUrl(status));
		
	}
	
	public static String queryResultById(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, resultUrlByid(id));
		
	}
	
	public static String checkUrl(String status) {
		return exam_url +"v2/"+enterpriseId+"/users/"+user_id+"/exams/"+getFirstIdByStatus(status)+"/check";
	}
	
	public static String checkUrlByid(String id) {
		return exam_url +"v2/"+enterpriseId+"/users/"+user_id+"/exams/"+id+"/check";
	}
	
	public static String checkFunctionById(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, checkUrlByid(id));
	}
	
	//检查是否继续考试
	public static String checkFunction(String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, checkUrl(status));
	}
	//查看答案解析
	public static String queryAnalysis(String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, analysisUrl(status));
	}
	
	public static String queryAnalysisById(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, analysisUrlById(id));
	}
	
	//查询等级排名
	public static String queryRanking(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id",enterpriseId, "user_id",user_id,rankingUrl(id));
	}
	
	// 查询c端考试任务列表
	public static String queryMyExamTaskList(String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "status", status,
				queryListUrl());

	}
	//通过状态获取第一个id
	public static String getFirstIdByStatus(String status) {
		String res = queryMyExamTaskList(status);
		String id = (String) JSONPath.read(res, "$.list[0].id");
		return id;
	}
	//查看考试说明页详情
	public static String queryListInfo(String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"user_id",user_id, queryListInfoUrl(status));
	}
	//查看考试说明页详情
	public static String queryListInfoById(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"user_id",user_id, queryListInfoUrlByid(id));
	}

	//查看移动端考试详情
	public static String queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"user_id",user_id,"enterprise_id",enterpriseId, queryInfoUrl(id));
	}
}
