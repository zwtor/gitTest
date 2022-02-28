package cn.kxy.authentication.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.httpclient.utils.HttpRequest;

public class JobEvaluationBusiness {
	
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String evaluation_url = EnterpriseDataUrl.getEvaluationUrl();
	public static String enterprise_url = EnterpriseDataUrl.getEnterpriseUrl();
	public static String user_id  = UserBusiness.getUserId();
	public static String evaluation_type_url = evaluation_url + "v1/"+enterprise_id+"/evaluation/type";
	public static String save_evaluation_url = evaluation_url + "v1/"+enterprise_id+"/evaluation/save";
	public static String list_url  = evaluation_url + "v1/"+enterprise_id+"/evaluation/list";
	
	public static String tools_now_list_url = enterprise_url + "v2/"+enterprise_id+"/evaluation/tools_now_list";
	
	public static String tools_url =enterprise_url + "v2/"+enterprise_id+"/evaluation/tools";
	
	public static String ability_list_url =enterprise_url + "v2/"+enterprise_id+"/evaluation/ability_list";
	
	public static String exportUrl (String id) {
		return evaluation_url + "v1/"+enterprise_id+"/evaluation/"+id+"/export";
	}
	
	public static String appOpenEvaluationUrl (String id) {
		return evaluation_url + "v1/"+enterprise_id+"/user/"+user_id+"/evaluation/"+id+"/open_evaluation";
	}
	
	public static String evaluationDetailUrl (String id) {
		return  evaluation_url + "v1/"+enterprise_id+"/evaluation/"+id+"/detail";
	}
	
	public static String evaluationListlUrl (String id) {
		return  evaluation_url + "v1/"+enterprise_id+"/evaluation/"+id+"/list";
	}
	
	public static String deleteUrl (String id) {
		return evaluation_url + "v1/"+enterprise_id+"/evaluation/"+id+"/delete";
	}
	
	public static String cancelUrl (String id) {
		return evaluation_url + "v1/"+enterprise_id+"/evaluation/"+id+"/unpublish";
	}
	
	public static String publishUrl (String id) {
		return evaluation_url + "v1/"+enterprise_id+"/evaluation/"+id+"/publish";
	}
	
	public static String exportExcel(String id) {
		return HttpRequest.get(exportUrl(id)).query("access_token",token).query("user_ids", user_id).send().body();
	}
	
	public static String queryAppOpenEvaluation(String id) {
		return HttpRequest.post(appOpenEvaluationUrl(id)).query("access_token",token).query("enterprise_id", enterprise_id)
				.query("user_id", user_id).data("{}").send().body();
	}
	
	public static String queryToolsList() {
		return HttpRequest.get(tools_url).query("access_token", token).query("page_number", "1").query("page_size", "20").
				send().body();
	}
	
	public static String queryAbilityList() {
		return HttpRequest.get(ability_list_url).query("access_token", token).query("page_number", "1").query("page_size", "20").
				send().body();
	}
	
	/**   
	 * @Title: queryToolsList   
	 * @Description: TODO  查看工具的列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static int queryToolsNowList() {
		return HttpRequest.get(tools_now_list_url).query("access_token", token).query("source","tool").send().statusCode();
	}
	
	//岗位测评详情的列表
	public static String queryEvaluationList(String id,String keyword,String status) {
		return HttpRequest.get(evaluationListlUrl(id)).query("access_token",token).query("page_number", "1")
				.query("page_size", "20").query("keyword", keyword).query("status",status).send().body();
	}
	
	public static String queryEvaluationDetail(String id) {
		return HttpRequest.get(evaluationDetailUrl(id)).query("access_token",token).send().body();
	}
	
	public static String deleteEvaluation(String id) {
		return HttpRequest.post(deleteUrl(id)).query("access_token",token).send().body();
	}
	
	public static String publishEvaluation(String id) {
		return HttpRequest.post(publishUrl(id)).query("access_token",token).send().body();
	}
	
	public static String calcelEvaluation(String id) {
		return HttpRequest.post(cancelUrl(id)).query("access_token",token).send().body();
	}
	
	public static String queryList(String status,String keyword,String only_see_me,String product_id) {
		return HttpRequest.get(list_url).query("access_token",token).query("status", status).query("only_see_me", "true")
				.query("product_id",product_id).query("keyword", keyword).query("page_number", "1").query("page_size", "20").send().body();
	}
	
	public static String saveEvaluation(String title ,String open_result,String product_id,String term_type,String status) {
		return HttpRequest.post(save_evaluation_url).query("access_token",token).data("{\n" + 
				"  \"title\":\""+title+"\",\n" + 
				"  \"product_id\":"+product_id+",\n" + 
				"  \"term_type\":\""+term_type+"\",\n" + 
				"  \"start_time\":\"\",\n" + 
				"  \"end_time\":\"\",\n" + 
				"  \"open_result\":"+open_result+",\n" + 
				"  \"supervisor_Id\":\""+user_id+"\",\n" + 
				"  \"source\":\"evaluation\",\n" + 
				"  \"user_ids\":\""+user_id+"\",\n" + 
				"  \"department_ids\":\"\",\n" + 
				"  \"group_ids\":\"\",\n" + 
				"  \"post_ids\":\"\",\n" + 
				"  \"authority_range\":false,\n" + 
				"  \"status\":"+status+",\n" + 
				"  \"model_id\":0,\n" + 
				"  \"finish_promote\":true,\n" + 
				"  \"certificate_id\":\"\",\n" + 
				"  \"is_get_score\":false,\n" + 
				"  \"study_score\":{\n" + 
				"    \"finish_score\":1\n" + 
				"  },\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}\n" + 
				"").send().body();
	}
	
	public static String saveEvaluation(String title ,String open_result,int product_id,String term_type,String status) {
		return HttpRequest.post(save_evaluation_url).query("access_token",token).data("{\n" + 
				"  \"title\":\""+title+"\",\n" + 
				"  \"product_id\":"+product_id+",\n" + 
				"  \"term_type\":\""+term_type+"\",\n" + 
				"  \"start_time\":\"\",\n" + 
				"  \"end_time\":\"\",\n" + 
				"  \"open_result\":"+open_result+",\n" + 
				"  \"supervisor_Id\":\""+user_id+"\",\n" + 
				"  \"source\":\"evaluation\",\n" + 
				"  \"user_ids\":\""+user_id+"\",\n" + 
				"  \"department_ids\":\"\",\n" + 
				"  \"group_ids\":\"\",\n" + 
				"  \"post_ids\":\"\",\n" + 
				"  \"authority_range\":false,\n" + 
				"  \"status\":"+status+",\n" + 
				"  \"model_id\":0,\n" + 
				"  \"finish_promote\":true,\n" + 
				"  \"certificate_id\":\"\",\n" + 
				"  \"is_get_score\":false,\n" + 
				"  \"study_score\":{\n" + 
				"    \"finish_score\":1\n" + 
				"  },\n" + 
				"  \"access_token\":\""+token+"\"\n" + 
				"}\n" + 
				"").send().body();
	}
	
	public static String queryeEvaluationType() {
		return HttpRequest.get(evaluation_type_url).query("access_token",token).send().body();
	}
	
}
