package cn.kxy.investigationresearch.business;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
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

public class InvestigatesBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();
	public static String queryListUrl = enterpriseUrl + "v2/" + enterpriseId + "/investigates";
	public static String cancelUrl(String name, String status) {
		return enterpriseUrl + "v2/" + enterpriseId + "/investigates/" + getIdByKeyword(name, status) + "/cancel_release";
	}
	//调研统计
	public static String statisticsUrl(String name, String status) {
		return enterpriseUrl + "v2/" + enterpriseId + "/investigates/" + getIdByKeyword(name, status) + "/statistics";
	}
	public static String publicUrl(String id) {
		return enterpriseUrl + "v2/" + enterpriseId + "/investigates/" + id + "/release";
	}
	
	public static String checkUrl(String id) {
		return enterpriseUrl +"/v2/"+enterpriseId+"/investigates/"+id +"/check";
	}
	
	public static String deleteUrl(String name, String status) {
		return enterpriseUrl + "v2/" + enterpriseId + "/investigates/" + getIdByKeyword(name, status) + "/delete";
	}
	
	public static String editUrl(String id) {
		return enterpriseUrl + "v2/" + enterpriseId + "/investigates/" + id + "/edit";
	}
	public static String addUrl = enterpriseUrl + "v2/"+enterpriseId+"/investigate";
	
	public static String queryInfoUrl(String id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/investigates/"+id;
	}
	
	/**   
	 * @Title: qqueryInfo   
	 * @Description: TODO(查询调研任务详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String qqueryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, queryInfoUrl(id));
	}
	
	/**   
	 * @Title: chechIsCanCancel_Delete   
	 * @Description: TODO(检查调研任务是否可以取消删除)   
	 * @param: @param name
	 * @param: @param status
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String chechIsCanCancel_Delete(String type, String id) {
		HttpResponse res=HttpRequest.get(checkUrl(id)).query("type",type).query("access_token", token).send();
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
	 * @Title: statisticsInvestigates   
	 * @Description: TODO(查看调研统计结果)   
	 * @param: @param name
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String statisticsInvestigates(String name, String status) {
		return PostRequestTools.RequestQueryParamByPost("access_token", token,  statisticsUrl(name, status));
	}
	
	public static void cancelDeleteInvestigates(String name) {
		cancelInvestigates(name, "release");
		deleteInvestigates(name, "draft");
	}
	
	/**   
	 * @Title: addInvestigates   
	 * @Description: TODO(新增调研任务，供业务流校验使用)   
	 * @param: @param anonymous_string
	 * @param: @param open_result_string
	 * @param: @param questionnaireName
	 * @param: @param term_type
	 * @param: @param start_time
	 * @param: @param stop_time
	 * @param: @param title
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addInvestigates(String anonymous,String open_result,
			String term_type,String start_time,String stop_time,String title,String type) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"actor_type\": \"part\", \r\n" + 
				"  \"anonymous_string\": \""+anonymous+"\", \r\n" + 
				"  \"open_result_string\": \""+open_result+"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"user_ids\": \""+UserBusiness.getUserId()+"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"questionnaire_id\": \""+QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release")+"\", \r\n" + 
				"  \"term_type\": \""+term_type+"\", \r\n" + 
				"  \"start_time\": \""+start_time+", \r\n" + 
				"  \"stop_time\": \""+stop_time+"\", \r\n" + 
				"  \"type\": \""+type+"\", \r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, addUrl);
	}
	
	/**   
	 * @Title: addRealNameInvestigates   
	 * @Description: TODO(短期使用，实名不公开结果)   
	 * @param: @param title
	 * @param: @param start_time
	 * @param: @param stop_time
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addShortTermInvestigates(String title,String start_time,String stop_time) {
		return  PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"actor_type\": \"part\", \r\n" + 
				"  \"anonymous_string\": \"false\", \r\n" + 
				"  \"open_result_string\": \"false\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"user_ids\": \""+UserBusiness.getUserId()+"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"questionnaire_id\": \""+QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release")+"\", \r\n" + 
				"  \"term_type\": \"custom\", \r\n" + 
				"  \"start_time\": \""+start_time+"\", \r\n" + 
				"  \"stop_time\": \""+start_time+"\", \r\n" + 
				"  \"type\": \"release\", \r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, addUrl);
	
	}
	
	/**   
	 * @Title: addNomalInvestigates   
	 * @Description: TODO(新增调研任务，长期使用，匿名公开结果，供增删改查使用)   
	 * @param: @param questionnaireName
	 * @param: @param title
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addNomalInvestigates(String questionnaireName,String title,String type) {
			return PostRequestTools.RequestBodyByPost("{\r\n" + 
					"  \"actor_type\": \"part\", \r\n" + 
					"  \"anonymous_string\": \"true\", \r\n" + 
					"  \"open_result_string\": \"true\", \r\n" + 
					"  \"group_ids\": \"\", \r\n" + 
					"  \"department_ids\": \"\", \r\n" + 
					"  \"user_ids\": \""+UserBusiness.getUserId()+"\", \r\n" + 
					"  \"post_ids\": \"\", \r\n" + 
					"  \"questionnaire_id\": \""+QuestionnaireBusiness.getIdByKeyword(questionnaireName, "release")+"\", \r\n" + 
					"  \"term_type\": \"permanent\", \r\n" + 
					"  \"start_time\": \"\", \r\n" + 
					"  \"stop_time\": \"\", \r\n" + 
					"  \"type\": \""+type+"\", \r\n" + 
					"  \"title\": \""+title+"\", \r\n" + 
					"  \"access_token\": \""+token+"\"\r\n" + 
					"}", token, addUrl);
	}
	
	/**   
	 * @Title: addNomalInvestigates   
	 * @Description: TODO(新增调研任务，实名不公开结果)   
	 * @param: @param questionnaireName
	 * @param: @param title
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addRealNameInvestigates(String title,String type) {
			return PostRequestTools.RequestBodyByPost("{\r\n" + 
					"  \"actor_type\": \"part\", \r\n" + 
					"  \"anonymous_string\": \"false\", \r\n" + 
					"  \"open_result_string\": \"false\", \r\n" + 
					"  \"group_ids\": \"\", \r\n" + 
					"  \"department_ids\": \"\", \r\n" + 
					"  \"user_ids\": \""+UserBusiness.getUserId()+"\", \r\n" + 
					"  \"post_ids\": \"\", \r\n" + 
					"  \"questionnaire_id\": \""+QuestionnaireBusiness.getIdByKeyword(BaseBusiness.questionnaireName, "release")+"\", \r\n" + 
					"  \"term_type\": \"permanent\", \r\n" + 
					"  \"start_time\": \"\", \r\n" + 
					"  \"stop_time\": \"\", \r\n" + 
					"  \"type\": \""+type+"\", \r\n" + 
					"  \"title\": \""+title+"\", \r\n" + 
					"  \"access_token\": \""+token+"\"\r\n" + 
					"}", token, addUrl);
	}
	
	/**   
	 * @Title: editInvestigates   
	 * @Description: TODO(编辑调研任务)   
	 * @param: @param questionnaireName
	 * @param: @param type
	 * @param: @param title
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String editInvestigates(String questionnaireName,String type ,String title,String id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"actor_type\": \"part\", \r\n" + 
				"  \"anonymous_string\": \"false\", \r\n" + 
				"  \"open_result_string\": \"false\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"user_ids\": \""+UserBusiness.getUserId()+"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"questionnaire_id\": \""+QuestionnaireBusiness.getIdByKeyword(questionnaireName, "release")+"\", \r\n" + 
				"  \"term_type\": \"custom\", \r\n" + 
				"  \"start_time\": \""+DateUtil.getRegularDate(1)+"\", \r\n" + 
				"  \"stop_time\": \""+DateUtil.getRegularDate(2)+"\", \r\n" + 
				"  \"type\": \""+type+"\", \r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"access_token\": \"b7d84a3ba75dfe6cdd81af6ccff8a4d9\"\r\n" + 
				"}", token, editUrl(id));
	}
	
	/**   
	 * @Title: getIdByKeyword   
	 * @Description: TODO(根据关键字获取id)   
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryList(String keyword) {
		HttpResponse res=HttpRequest.get(queryListUrl).query("keyword", keyword).query("create_status", "all").
				query("status","release").query("access_token", token).
				query("page_size", "20").query("page_number", "1").send();
		HttpEntity resEntity = res.response().getEntity();  
		String msg = "" ;
        try {
			 msg = EntityUtils.toString(resEntity, "utf-8");
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return msg;  
	}
	public static String getIdByKeyword(String keyword) {
		String res = queryList(keyword);
		String id = (String)JSONPath.read(res, "$.questionnaires.list[0].id");
		return id;
	}
	/**   
	 * @Title: queryLsit   
	 * @Description: TODO(查看调研任务列表)   
	 * @param: @param keyword
	 * @param: @param create_status
	 * @param: @param course_classify
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryLsit(String keyword, String view_range, String status) {
		HttpResponse res=HttpRequest.get(queryListUrl).query("keyword", keyword).query("view_range", view_range).
				query("status",status).query("access_token", token).
				query("page_size", "20").query("page_number", "1").send();
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
	 * @Title: cancelinvestigates   
	 * @Description: TODO(取消发布)   
	 * @param: @param name
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String cancelInvestigates(String name, String status) {
		return PostRequestTools.RequestQueryParamByPost("access_token", token,  cancelUrl(name, status));
	}
	
	/**   
	 * @Title: publicInvestigates   
	 * @Description: TODO(发布调研任务)   
	 * @param: @param name
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String publicInvestigates(String id) {
		return PostRequestTools.RequestQueryParamByPost("access_token", token, publicUrl(id));
	}
	/**   
	 * @Title: deleteInvestigates   
	 * @Description: TODO(删除调研任务)   
	 * @param: @param name
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteInvestigates(String name, String status) {
		return PostRequestTools.RequestQueryParamByPost("access_token", token, deleteUrl(name, status));

	}
	/**   
	 * @Title: getIdByKeyword   
	 * @Description: TODO(根据关键字和状态（草稿箱、已发布）获取id)   
	 * @param: @param name
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getIdByKeyword(String name, String status) {
		String res = queryLsit(name, "all", status);
		String id = (String) JSONPath.read(res, "$.investigates.list[0].id");
		return id;
	}
	
}
