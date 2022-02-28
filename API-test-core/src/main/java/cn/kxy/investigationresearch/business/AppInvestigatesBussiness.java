package cn.kxy.investigationresearch.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;

public class AppInvestigatesBussiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	
	
	public static String queryAppInvestigatesListUrl=enterpriseUrl+"v2/"+enterpriseId+"/users/"+UserBusiness.getUserId()+"/researches";
	
	public static String queryAppInvestigatesInfoUrl(String status) {
		return enterpriseUrl + "v2/"+enterpriseId+"/investigates/"+getAppInvestigatesId(status)+"/questionnaires/"+getAppQuestionId(status);
	}
	public static String queryAppStatisticsUrl(String status) {
		return enterpriseUrl  + "v2/"+enterpriseId+"/investigates/"+getAppInvestigatesId(status)+"/statistics";
	}
	
	public static String submitUrl(String status) {
		return enterpriseUrl + "v2/"+enterpriseId+"/users/"+UserBusiness.getUserId()+"/researches/"+getAppInvestigatesId(status)+"/submit";
	}
	
	public static String queryAppInvestigatesInfo(String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, queryAppInvestigatesInfoUrl(status)) ;
	}
	
	/**   
	 * @Title: queryAppStatistics   
	 * @Description: TODO(获取统计结果)   
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppStatistics(String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryAppStatisticsUrl(status));
	}
	
	public static String submitBlank(String status) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"questions\": [{\r\n" + 
				"		\"id\": \""+getInvestigatesQuestionsIdByIndex(status,0)+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \"\"\r\n" + 
				"		}\r\n" + 
				"	}, {\r\n" + 
				"		\"id\": \""+getInvestigatesQuestionsIdByIndex(status,1)+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \"\"\r\n" + 
				"		}\r\n" + 
				"	}, {\r\n" + 
				"		\"id\": \""+getInvestigatesQuestionsIdByIndex(status,2)+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \"\",\r\n" + 
				"			\"other_answer\": \"\"\r\n" + 
				"		}\r\n" + 
				"	}, {\r\n" + 
				"		\"id\": \""+getInvestigatesQuestionsIdByIndex(status,3)+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \"\",\r\n" + 
				"			\"other_answer\": \"\"\r\n" + 
				"		}\r\n" + 
				"	}]\r\n" + 
				"}", token, submitUrl(status));
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
	public static String submit(String status,String threeOption,String fouroOtion) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"questions\": [{\r\n" + 
				"		\"id\": \""+getInvestigatesQuestionsIdByIndex(status,0)+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \""+getInvestigatesFirstOptionIdByIndex(status, 0)+"\"\r\n" + 
				"		}\r\n" + 
				"	}, {\r\n" + 
				"		\"id\": \""+getInvestigatesQuestionsIdByIndex(status,1)+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \""+getInvestigatesSecondOptionIdByIndex(status, 0)+","+getInvestigatesSecondOptionIdByIndex(status, 1)+"\"\r\n" + 
				"		}\r\n" + 
				"	}, {\r\n" + 
				"		\"id\": \""+getInvestigatesQuestionsIdByIndex(status,2)+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \""+threeOption+"\",\r\n" + 
				"			\"other_answer\": \"\"\r\n" + 
				"		}\r\n" + 
				"	}, {\r\n" + 
				"		\"id\": \""+getInvestigatesQuestionsIdByIndex(status,3)+"\",\r\n" + 
				"		\"solution\": {\r\n" + 
				"			\"options\": \""+fouroOtion+"\",\r\n" + 
				"			\"other_answer\": \"\"\r\n" + 
				"		}\r\n" + 
				"	}]\r\n" + 
				"}", token, submitUrl(status));
	}
	
	/**   
	 * @Title: getInvestigatesQuestionsIdByIndex   
	 * @Description: TODO(通过索引查看问卷的id)   
	 * @param: @param status
	 * @param: @param index
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getInvestigatesQuestionsIdByIndex(String status,int index) {
		String res = queryAppInvestigatesInfo(status);
		String  id = (String )JSONPath.read(res, "$.question["+index+"].id");
		return id;
	}
	
	/**   
	 * @Title: getInvestigatesFirstOptionIdByIndex   
	 * @Description: TODO(通过索引获取option的id)   
	 * @param: @param status
	 * @param: @param index
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getInvestigatesFirstOptionIdByIndex(String status,int index) {
		String res = queryAppInvestigatesInfo(status);
		String  id = (String )JSONPath.read(res, "$.question[0].options["+index+"].id");
		return id;
	}
	
	/**   
	 * @Title: getInvestigatesFirstOptionIdByIndex   
	 * @Description: TODO()   
	 * @param: @param status
	 * @param: @param index
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getInvestigatesSecondOptionIdByIndex(String status,int index) {
		String res = queryAppInvestigatesInfo(status);
		String  id = (String )JSONPath.read(res, "$.question[1].options["+index+"].id");
		return id;
	}
	/**   
	 * @Title: queryAppInvestigatesList   
	 * @Description: TODO(查询APP端调研列表)   
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppInvestigatesList(String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "page_number", "1", "page_size","10","status",status,queryAppInvestigatesListUrl);
	}
	/**   
	 * @Title: getAppInvestigatesId   
	 * @Description: TODO(获取第一个调研任务的id)   
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getAppInvestigatesId(String status) {
		String res = queryAppInvestigatesList(status);
		String id = (String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	/**   
	 * @Title: getAppQuestionId   
	 * @Description: TODO(获取第一个问卷的id)   
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getAppQuestionId(String status) {
		String res = queryAppInvestigatesList(status);
		String id = (String)JSONPath.read(res, "$.list[0].questionnaire_id");
		return id;
	}
}
