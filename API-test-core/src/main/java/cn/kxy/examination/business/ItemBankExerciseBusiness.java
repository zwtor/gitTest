package cn.kxy.examination.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.ExamDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class ItemBankExerciseBusiness {

	public static String exam_url= ExamDataUrl.getNewExamUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String addUrl = exam_url + "v2/" + user_id + "/practice";
	public static String enterpriseUrl= EnterpriseDataUrl.getEnterpriseUrl();
	public static String queryInfoUrl = exam_url + "v2/" + user_id + "/practices";

	public static String queryPracticeListUrl = exam_url + "v2/" + user_id + "/practices";

	public static String getEditUrl(String id) {
		return exam_url + "v2/" + user_id + "/practices/" + id + "/edit";
	}

	public static String getUpdateStatusUrl(String id) {
		return exam_url + "v2/" +user_id + "/practices/" + id + "/update_status";
	}
	public static String getMonitorsInfoUrl(String id) {
		return exam_url + "v2/" + user_id+ "/practices/" + id + "/monitors";
	}
	
	public static String export_url(String id) {
		return enterpriseUrl+"v2/"+enterpriseId+"/practices/"+id+"/monitors/export";
	}
	
	public static String deleteUrl(String id) {
		return enterpriseUrl + "v2/practice/"+id+"/delete";
	}
	
	public static String queryInfoUrl (String id) {
		return exam_url + "v2/"+enterpriseId+"/practices/"+id+"/query";
	}
	
	public static String deleteExce(String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, deleteUrl(id));
	}
	
	/**   
	 * @Title: queryInfo    
	 * @Description: TODO  查看题库练习详情
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String  queryInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "id", id, queryInfoUrl(id));
	}
	
	/**   
	 * @Title: getExportCode   
	 * @Description: TODO(导出题库练习的数据)   
	 * @param: @param id
	 * @param: @param keyword
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static String  exportPractice(String id,String keyword) {
		return HttpRequest.get(export_url(id)).query("keyword", keyword).query("page_number", "1").query("page_size","20").query("department_id", "1").
				 query("access_token", token).query("post_id","").contentType("application/json;charset=UTF-8")
					.send().body();
				
	}

	// 新增题库练习
	public static String addPractice(String title, String question_bank, String part, 
			String post_ids) {
		return PostRequestTools.RequestBodyByPost("{\"title\":\"" + title
				+ "\",\"cover\":\"https://oss.coolcollege.cn/1789918333643132928.png\",\"question_bank_list\":[\""
				+ question_bank + "\"],\"visible_range\":\"" + part + "\",\"user_ids\":\"" + user_id
				+ "\",\"department_ids\":\"\",\"group_ids\":\"\",\"post_ids\":\"" + post_ids + "\",\"access_token\":\""
				+ token + "\"}", token, addUrl);

	}
	
	public static String addPractice(String title, String question_bank, String part, String userId,
			String post_ids) {
		return PostRequestTools.RequestBodyByPost("{\"title\":\"" + title
				+ "\",\"cover\":\"https://oss.coolcollege.cn/1789918333643132928.png\",\"question_bank_list\":[\""
				+ question_bank + "\"],\"visible_range\":\"" + part + "\",\"user_ids\":\"" + userId
				+ "\",\"department_ids\":\"\",\"group_ids\":\"\",\"post_ids\":\"" + post_ids + "\",\"access_token\":\""
				+ token + "\"}", token, addUrl);

	}
	
	public static String addPractice(String title,String question_bank_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"cover\": \"https://oss.coolcollege.cn/1789918333643132928.png\", \r\n" + 
				"  \"question_bank_list\": [\r\n" + 
				"    \""+question_bank_id+"\"\r\n" + 
				"  ], \r\n" + 
				"  \"visible_range\": \"part\", \r\n" + 
				"  \"user_ids\": \""+user_id+"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, addUrl);
	}

	// 查询题库练习列表
	public static String queryPracticeList(String keyword, String status) {
		return GetRequestTools.RequestQueryParamsByGet("keyword", keyword, "status", status, "access_token",
				token, queryPracticeListUrl);

	}

	// 编辑题库练习
	public static String editPractice(String title, String question_bank, String visible_range, String user_ids,
			String post_ids, String id) {
		return PostRequestTools.RequestBodyByPost("{\"title\":\"" + title
				+ "\",\"cover\":\"https://oss.coolcollege.cn/1789918333643132928.png\",\"question_bank_list\":[\""
				+ question_bank + "\"],\"visible_range\":\"" + visible_range + "\",\"user_ids\":\"" + user_ids
				+ "\",\"department_ids\":\"\",\"group_ids\":\"\",\"post_ids\":\"" + post_ids + "\",\"id\":\"" + id
				+ "\",\"access_token\":\"" + token + "\"}", token, getEditUrl(id));

	}

	// 获取第一个题库练习的id
	public static String getFirstId() {

		String res = queryPracticeList("", "all");

		String id = (String) JSONPath.read(res, "$.list[0].id");

		return id;
	}

	// 获取第一个题库练习的id
	public static String getFirstId(String name) {

		String res = queryPracticeList(name, "all");

		String id = (String) JSONPath.read(res, "$.list[0].id");

		return id;
	}

	// 获取第一个题库练习的状态
	public static String getFirstStatus() {
		

		String res = queryPracticeList("", "all");

		String status = (String) JSONPath.read(res, "$.list[0].status");

		return status;
	}

	// 获取第一个题库练习的状态
	public static String getStatusByKeyword(String name) {

		String res = queryPracticeList(name, "all");

		String status = (String) JSONPath.read(res, "$.list[0].status");

		return status;
	}

	// 获取第一个题库练习的标题
	public static String getFirstTitle() {

		String res = queryPracticeList("", "all");

		String title = (String) JSONPath.read(res, "$.list[0].title");

		return title;
	}

	// 更新题库练习列表
	public static String updateStatus(String status,String id) {
		return PostRequestTools.RequestBodyByPost(
				"{\"status\":\"" + status + "\",\"access_token\":\"" + token + "\"}", token,
				getUpdateStatusUrl(id));
	}

	public static String queryMonitors(String keyword,String id) {
		return GetRequestTools.RequestParamsByGet("keyword", keyword, "access_token", token,
				getMonitorsInfoUrl(id));
	}
	
	public static String queryMonitors(String keyword,String id,String department_id,String post_id) {
		return GetRequestTools.RequestParamsByGet("keyword", keyword, "access_token", token,"department_id",
				department_id,"post_id",post_id,getMonitorsInfoUrl(id));
	}


}
