package cn.kxy.my.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.httpclient.utils.HttpRequest;
import com.lazy.httpclient.utils.HttpResponse;

public class MyBusiness {

	public static String token = TokenData.getMangerToken();
	public static String enterpriseId=EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl= EnterpriseDataUrl.getEnterpriseUrl();
	public static String planformUrl = EnterpriseDataUrl.getPlatformUrl();
	public static String userId = UserBusiness.getUserId();
	public static String queryCertificateUrl = enterpriseUrl + "v2/" + enterpriseId
			+ "/certificate_details";

	public static String queryScoreSummaryUrl = enterpriseUrl + "score/getMyScoreSummary";

	public static String getUserTrainArchivesDetailListUrl = enterpriseUrl
			+ "report/trainArchives/getUserTrainArchivesDetailList";
	public static String exportScoreRecordUrl = enterpriseUrl +"score/exportScoreRecord";
	
	public static String getScoreRecordUrl =enterpriseUrl + "score/getScoreRecord";
	
	public static String queryMyPurchaseUrl = planformUrl +"v2/users/"+userId+"/courses";
	
	public static String querymy_create_coursesUrl = enterpriseUrl  + "v2/"+enterpriseId+"/users/"+userId+"/my_create_courses";
	
	public static String queryMyCollectionUrl=  planformUrl + "shopping/collection/getList";
	
	public static String queryMyOrderUrl =planformUrl + "admin/bizOrder/getOrderList";
	
	public static String queryMylatojasUrl  = enterpriseUrl + "v2/"+enterpriseId+"/users/"+userId+"/latojas";
	
	public static String certificate_details_export_url =enterpriseUrl + "v2/"+enterpriseId+"/certificate_details_export";
	
	public static String user_course_archives_url = enterpriseUrl + "v2/"+enterpriseId+"/user_course_archives";
	
	public static String user_qualification_archives_url = enterpriseUrl + "v2/"+enterpriseId+"/user_qualification_archives";
	
	public static String user_qualification_archives_export_url = enterpriseUrl +"v2/"+enterpriseId+"/user_qualification_archives_export";
	
	public static String user_course_archives_export_url = enterpriseUrl + "v2/"+enterpriseId+"/user_course_archives_export";
	
	
	/**   
	 * @Title: getUserCourseArchivesExportCode   
	 * @Description: TODO(导出我的自学档案)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getUserCourseArchivesExportCode() {
		return HttpResponse.getstatusCode(user_course_archives_export_url, token, "user_id", userId,"status","all","dimission_status","incumbency");
	}
	
	/**   
	 * @Title: getUserQualificationArchivesExport   
	 * @Description: TODO(获取导出我的认证档案code)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getUserQualificationArchivesExportCode() {
		return HttpResponse.getstatusCode(user_qualification_archives_export_url, token, "user_id", userId,"status","all","dimission_status","incumbency");
	}
	
	/**   
	 * @Title: queryUserQualificationArchives   
	 * @Description: TODO(查看认证档案)   
	 * @param: @param status （all--全部， studying--进行中  approving--待审核  finished--已完成）
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryUserQualificationArchives (String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "page_size", "20","page_number","1", 
				"user_id",userId,"dimission_status","incumbency","status",status,user_qualification_archives_url);
	}
	
	/**   
	 * @Title: queryUserCourseArchives   
	 * @Description: TODO(查看自学档案)   
	 * @param: @param status （finished--已完成   studying--进行中  ""--全部）
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryUserCourseArchives (String status) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "page_number", "1","page_size","10", 
				"user_id",userId,"dimission_status","incumbency","status",status,user_course_archives_url);
	}
	
	/**   
	 * @Title: getCertificateDetailsCode   
	 * @Description: TODO(导出我的证书)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getCertificateDetailsCode() {
		return HttpResponse.getstatusCode(certificate_details_export_url, token, "user_id", userId,"page_size","10","page_number","1");
	}
	/**   
	 * @Title: queryScoreRecordList   
	 * @Description: TODO(查看我的学分记录列表)   
	 * @param: @param startTime
	 * @param: @param endTime
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryScoreRecordList(String startTime,String endTime) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, "pageNumber", "1","pageSize","20", 
				"startTime",startTime,"endTime",endTime,getScoreRecordUrl);
	}
	
	/**   
	 * @Title: getExportScoreRecordCode   
	 * @Description: TODO(导出学分记录)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getExportScoreRecordCode() {
		return HttpResponse.getstatusCode(exportScoreRecordUrl, token, "excludeSelf", "false");
	}
	/**   
	 * @Title: queryMylatojas   
	 * @Description: TODO(查看我负责的签到)   
	 * @param: @param keyword
	 * @param: @param query_type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryMylatojas(String keyword,String query_type) {
		return GetRequestTools.RequestQueryParamsByGet("keyword",keyword,"query_type",query_type,"access_token", token,"page_number","1",
				"page_size","20",queryMylatojasUrl);
	}
	
	/**   
	 * @Title: queryMyOrder   
	 * @Description: TODO(查询我的订单)   
	 * @param: @param isEnterprise
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryMyOrder(String isEnterprise,String status) {
		return GetRequestTools.RequestQueryParamsByGet("isEnterprise",isEnterprise,"status",status,"access_token", token,"page_number","1",
				"page_size","20",queryMyOrderUrl);
	}
	/**   
	 * @Title: queryMyCollection   
	 * @Description: TODO(查看我的收藏)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryMyCollection () {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"page_number","1",
				"page_size","20",queryMyCollectionUrl);
	}
	
	/**   
	 * @Title: queryMypurchase   
	 * @Description: TODO(查询我的购买)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryMypurchase() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"isAdmin","1","page_number","1",
				"page_size","20",queryMyPurchaseUrl);
	}
	
	/**   
	 * @Title: querMyCreateCourses   
	 * @Description: TODO(查询我的创建课程)   
	 * @param: @param course_status
	 * @param: @param title
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String querMyCreateCoursesList(String course_status,String title) {
		return GetRequestTools.RequestQueryParamsByGet("course_status",course_status, "title",title,"page_number","1","page_size" ,"20",
				"access_token",token,querymy_create_coursesUrl);
	}
	
	/**   
	 * @Title: getIdByKeyword   
	 * @Description: TODO()   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getMyCreateCoursesIdByKeyword(String course_status,String title) {
		String res = querMyCreateCoursesList(course_status, title);
		String id = (String)JSONPath.read(res, "$.list[0].course_id");
		return id;
	}

	// --------------------------------------------------我的证书相关方法-----------------------------------------------------------------------------
	// 查看证书列表
	public static String queryMyCertificate(String source_type, String begin_time, String end_time,
			String certificate_name) {
		return GetRequestTools.RequestQueryParamsByGet("page_number", "1", "page_size", "20", "source_type",
				source_type, "user_id", UserBusiness.getUserId(), "begin_time", begin_time, "end_time", end_time,
				"certificate_name", certificate_name, "access_token", token, queryCertificateUrl);

	}

	// 获取最新证书名称
	public static String getMyCertificateName(String name) {
		String res = queryMyCertificate("0", "", "", name);
		String certificate_name = (String) JSONPath.read(res, "$.list[0].certificate_name");
		return certificate_name;
	}
	// --------------------------------------------------我的学分相关方法-----------------------------------------------------------------------------

	// 获取我的学分详情
	public static String getScoreSummary() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryScoreSummaryUrl);

	}

	// 获取我的学分
	public static int getMyTotalScore() {
		String res = getScoreSummary();
		int Score = (int) JSONPath.read(res, "$.totalScore");
		return Score;
	}

	// --------------------------------------------------我的档案相关方法-----------------------------------------------------------------------------

	/**   
	 * @Title: getUserTrainArchivesDetailList   
	 * @Description: TODO()   
	 * @param: @param beginTime
	 * @param: @param endTime
	 * @param: @param trainType (0--全部 、 1--新员工培训 、2--学习任务 、 3--选修任务 、 4--考试任务)
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getUserTrainArchivesDetailList(String beginTime, String endTime ,
			String trainType) {
		
		return  HttpRequest.get(getUserTrainArchivesDetailListUrl).query("access_token", token).query("beginTime", beginTime).
				query("endTime", endTime).query("pageSize", "20").query("trainType", trainType).
		 query("pageNumber", "1").contentType("application/json;charset=UTF-8")
			.send().body();
	}

	// 获取证书个数
	public static int getMyCertificateTotal() {
		String res = queryMyCertificate("0", "", "", "");
		int total = (int) JSONPath.read(res, "$.total");
		return total;
	}

}
