package cn.kxy.data.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class DataBusiness {
	public static String token = TokenData.getMangerToken();
	
	public static String enterpriseUrl= EnterpriseDataUrl.getEnterpriseUrl();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String dataSummaryUrl= enterpriseUrl + "dashboard/summary/enterprise";
	
	public static String learn_url = enterpriseUrl +"dashboard/statistics/learn";
	
	public static String exam_url = enterpriseUrl +"dashboard/statistics/exam";
	
	public static String count_url =enterpriseUrl + "dashboard/user/count";
	
	public static String cockpits_url = enterpriseUrl + "v2/"+enterpriseId+"/cockpits";
	
	public static String plan_url = enterpriseUrl + "v2/"+enterpriseId+"/cockpits/plans";
	
	public static String exam_data_url = enterpriseUrl + "v2/"+enterpriseId+"/cockpits/exams";
	
	public static String queryStudyDataInfoUrl (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/cockpits/plans/"+id;
	}
	
	public static String queryExamDataInfoUrl(String id) {
		return enterpriseUrl  + "v2/"+enterpriseId+"/cockpits/exams/"+id;
	}
	
	/**   
	 * @Title: queryExamDataInfo   
	 * @Description: TODO  查看考试任务列表的任务详情
	 * @param: @param id
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryExamDataInfo(String id,String status) {
		return HttpRequest.get(queryExamDataInfoUrl(id)).query("access_token", token).query("enterprise_id", enterpriseId).query("page_number", "1")
				.query("page_size", "10").query("user_id", user_id).query("status", status).send().body();
	}
	
	/**   
	 * @Title: queryStudyDataInfo   
	 * @Description: TODO 查看学习任务列表的任务详情
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryStudyDataInfo(String id) {
		return HttpRequest.get(queryStudyDataInfoUrl(id)).query("access_token", token).query("enterprise_id", enterpriseId).query("page_number", "1")
				.query("page_size", "10").query("user_id", user_id).send().body();
	}
	
	/**   
	 * @Title: queryPlanData   
	 * @Description: TODO 查询学习数据
	 * @param: @param keyword
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryPlanData(String keyword,String status) {
		return HttpRequest.get(plan_url).query("access_token",token).query("enterprise_id",enterpriseId).query("keyword",keyword).
				query("page_number","1").query("page_size","10").query("status",status).query("user_id",user_id).
				send().body();
	}
	
	/**   
	 * @Title: queryExamData   
	 * @Description: TODO （查询考试数据）
	 * @param: @param keyword
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryExamData(String keyword,String status) {
		return HttpRequest.get(exam_data_url).query("access_token",token).query("enterprise_id",enterpriseId).query("keyword",keyword).
				query("page_number","1").query("page_size","10").query("status",status).query("user_id",user_id).
				send().body();
	}
	
	
	/**   
	 * @Title: queryAppData   
	 * @Description: TODO(查看移动端数据)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAppData() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "enterprise_id", enterpriseId,"user_id",user_id, cockpits_url);
	}
	
	/**   
	 * @Title: queryUserCount   
	 * @Description: TODO(查看企业授权人数)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryUserCount() {
		 return GetRequestTools.RequestQueryParamsByGet("count", "3", "access_token", token, count_url);
	}
	
	/**   
	 * @Title: queryExamStatisics   
	 * @Description: TODO(查看考试统计面板)   
	 * @param: @param startDate
	 * @param: @param endDate
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryExamStatisics(String startDate,String endDate) {
		return GetRequestTools.RequestQueryParamsByGet("startDate", startDate, "endDate", endDate,"access_token",token, exam_url);
	}
	/**   
	 * @Title: queryLearnStatisics   
	 * @Description: TODO(查看学习统计面板)   
	 * @param: @param startDate
	 * @param: @param endDate
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryLearnStatisics(String startDate,String endDate) {
		return GetRequestTools.RequestQueryParamsByGet("startDate", startDate, "endDate", endDate,"access_token",token, learn_url);
	}
	
	public static String queryDataSummary() {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, dataSummaryUrl);
	}
	//获取考试未完成的数量
	public static int getexamPlanUnpassCount() {
		String res = queryDataSummary();
		int unpassCount = (int)JSONPath.read(res, "$.examPlanUnpassCount");
		return unpassCount;
	}
	//获取考试的总数量
	public static int getexamPlanCount() {
		String res = queryDataSummary();
		int examPlanCount = (int)JSONPath.read(res, "$.examPlanCount");
		return examPlanCount;
	}
	//获取考试已完成的数量
	public static int getexamPlanPassCount() {
		String res = queryDataSummary();
		int examPlanPassCount = (int)JSONPath.read(res, "$.examPlanPassCount");
		return examPlanPassCount;
	}
}
