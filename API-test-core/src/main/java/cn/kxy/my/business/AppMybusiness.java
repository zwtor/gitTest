package cn.kxy.my.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;

public class AppMybusiness {
	
	public static String enterpriseUrl=  EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId=EnterpriseData.getEnterpriseId();
	public static String userId = UserBusiness.getUserId();
	public static String planformUrl = EnterpriseDataUrl.getPlatformUrl();
	public static String queryAuthApproveListUrl = enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/qualifications/operations";
	
	public static String approveUrl (String operationId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/operations/"+operationId;
	}
	
	public static String queryPostAuthScInfoUrl (String operationId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/operations/"+operationId+"/approval";
	}
	
	public static String statistics_url = enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/statistics";
	
	public static String getSysBannerListUrl = enterpriseUrl + "index/banner/getSysBannerList";
	
	public static String orders_url = planformUrl + "v2/orders";
	
	public static String courses_url = planformUrl + "v2/users/"+enterpriseId+"/courses";
	
	public static String approval_courses_url = enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/approval_courses";
	
	public static String suggestions_url = planformUrl + "v2/suggestions";
	
	public static String help_documents_url =planformUrl + "v2/sys/help_documents";
	
	public static String help_documents_info_url (String id) {
		return planformUrl +"v2/sys/help_documents/"+id;
	}
	
	public static String scores_url = enterpriseUrl + "v2/"+enterpriseId+"/scores";
	
	public static String today_scores_url = enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/scores";
	
	public static String languages_config_url =enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/languages/config";
	
	public static String certificates_url = enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/certificates";
	
	public static String approval_url =enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/qualifications/operations";
	
	/**   
	 * @Title: qualificationsApproval   
	 * @Description: TODO(认证审核列表)   
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String qualificationsApproval(String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "enterprise_id", 
				enterpriseId, "page_number","1","page_size","10","status",status,"user_id",userId,approval_url);
	}
	
	/**   
	 * @Title: queryCertificates   
	 * @Description: TODO(查看证书列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryCertificates() {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "enterprise_id",enterpriseId,"user_id",userId,certificates_url);
	}
	
	/**   
	 * @Title: queryTodayScores   
	 * @Description: TODO(查询学分列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryTodayScores () {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "enterprise_id",enterpriseId,"page_number","1",
				"page_size","20","user_id",userId,"type","3",today_scores_url);
	}
	
	/**   
	 * @Title: queryScores   
	 * @Description: TODO(查询学分列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryScoresList () {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "enterprise_id",enterpriseId,"page_number","1",
				"page_size","20","user_id",userId,"type","3",scores_url);
	}
	
	/**   
	 * @Title: setLanguage   
	 * @Description: TODO(设置语言)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String setLanguage(String language ) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"language\": \""+language+"\"\r\n" + 
				"}", token, "enterprise_id",enterpriseId, "user_id",userId, languages_config_url);
	}
	
	/**   
	 * @Title: queryHelpDocumentsInfo   
	 * @Description: TODO(查看帮助文档详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryHelpDocumentsInfo(String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "enterprise_id",enterpriseId,"user_id",userId,help_documents_info_url(id));
	}
	
	/**   
	 * @Title: queryHelpDocumentsList   
	 * @Description: TODO(查看帮助文档列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryHelpDocumentsList () {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "enterprise_id",enterpriseId,"page_number","1",
				"page_size","20","user_id",userId,"type","3",help_documents_url);
	}
	
	/**   
	 * @Title: querySuggestionsList   
	 * @Description: TODO(查看意见反馈列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String querySuggestionsList () {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "enterprise_id",enterpriseId,"page_number","1",
				"page_size","20","user_id",userId,suggestions_url);
	}
	
	/**   
	 * @Title: approvalCourse   
	 * @Description: TODO()   
	 * @param: @param status (approval--待审批   approved--已审批)
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String approvalCourse(String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "enterprise_id",enterpriseId,"page_number","1",
				"page_size","20","status",status,"user_id",userId,approval_courses_url);
	}
	
	/**   
	 * @Title: queryCourse   
	 * @Description: TODO(查询已购课程)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryCourse(String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "enterprise_id",enterpriseId,"page_number","1",
				"page_size","20","keyword",keyword,"user_id",userId,courses_url);
	}
	
	/**   
	 * @Title: queryMyOrder   
	 * @Description: TODO(查看我的订单)    
	 * @param: @return   all--全部   unpaid--未付款     paid--已付款
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryMyOrder(String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"enterprise_id",enterpriseId,"user_id",userId,
				"status","all","page_number","1","page_size","20",orders_url);
	}
	
	/**   
	 * @Title: getSysBannerList   
	 * @Description: TODO(获取移动端banner信息)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getSysBannerList() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "enterprise_id", enterpriseId, "user_id",userId,
				getSysBannerListUrl);
	}
	
	/**   
	 * @Title: queryStatistics   
	 * @Description: TODO(查询审批管理列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryStatistics () {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "enterprise_id", enterpriseId,"user_id",userId, statistics_url);
	}
	
	/**   
	 * @Title: queryPostAuthScInfo   
	 * @Description: TODO(查询实操考核详情)   
	 * @param: @param operationId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryOperationInfo(String operationId) {
		return GetRequestTools.RequestQueryParamsByGet("user_id", userId,"submit_user_id",userId,
				"access_token",token,"enterprise_id",enterpriseId,queryPostAuthScInfoUrl(operationId));
	}
	
	
	
	/**   
	 * @Title: approvePostAuth   
	 * @Description: TODO(驳回或者同意审核：2--驳回，3--通过)   
	 * @param: @param operationId
	 * @param: @param suggest
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String approvePostAuth(String operationId,String suggest,String status) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"suggest\": \""+suggest+"\",\r\n" + 
				"	\"status\": "+status+"\r\n" + 
				"}", token, approveUrl(operationId));
	}
	
	/**   
	 * @Title: queryAuthApproveList   
	 * @Description: TODO(查看实操审批列表：1--待审批，2--已审批)   
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryOperationApproveList(String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token, "status", status,"page_number","1","page_size","20", 
				"enterprise_id",enterpriseId,"user_id",userId,queryAuthApproveListUrl);
	}
	
	
	public static String getAuthApproveInfoId (String operationId) {
		String res = queryOperationInfo(operationId);
		String id = (String)JSONPath.read(res, "$.data.id");
		return id;
	}
	
	public static String getAuthApproveListIdByStatus (String status) {
		String res = queryOperationApproveList(status);
		String id = (String)JSONPath.read(res, "$.list[0].operation_review_list[0].id");
		return id;
	}
	
}
