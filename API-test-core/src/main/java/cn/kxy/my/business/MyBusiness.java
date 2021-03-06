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
	 * @Description: TODO(????????????????????????)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getUserCourseArchivesExportCode() {
		return HttpResponse.getstatusCode(user_course_archives_export_url, token, "user_id", userId,"status","all","dimission_status","incumbency");
	}
	
	/**   
	 * @Title: getUserQualificationArchivesExport   
	 * @Description: TODO(??????????????????????????????code)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getUserQualificationArchivesExportCode() {
		return HttpResponse.getstatusCode(user_qualification_archives_export_url, token, "user_id", userId,"status","all","dimission_status","incumbency");
	}
	
	/**   
	 * @Title: queryUserQualificationArchives   
	 * @Description: TODO(??????????????????)   
	 * @param: @param status ???all--????????? studying--?????????  approving--?????????  finished--????????????
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
	 * @Description: TODO(??????????????????)   
	 * @param: @param status ???finished--?????????   studying--?????????  ""--?????????
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
	 * @Description: TODO(??????????????????)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getCertificateDetailsCode() {
		return HttpResponse.getstatusCode(certificate_details_export_url, token, "user_id", userId,"page_size","10","page_number","1");
	}
	/**   
	 * @Title: queryScoreRecordList   
	 * @Description: TODO(??????????????????????????????)   
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
	 * @Description: TODO(??????????????????)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getExportScoreRecordCode() {
		return HttpResponse.getstatusCode(exportScoreRecordUrl, token, "excludeSelf", "false");
	}
	/**   
	 * @Title: queryMylatojas   
	 * @Description: TODO(????????????????????????)   
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
	 * @Description: TODO(??????????????????)   
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
	 * @Description: TODO(??????????????????)   
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
	 * @Description: TODO(??????????????????)   
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
	 * @Description: TODO(????????????????????????)   
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

	// --------------------------------------------------????????????????????????-----------------------------------------------------------------------------
	// ??????????????????
	public static String queryMyCertificate(String source_type, String begin_time, String end_time,
			String certificate_name) {
		return GetRequestTools.RequestQueryParamsByGet("page_number", "1", "page_size", "20", "source_type",
				source_type, "user_id", UserBusiness.getUserId(), "begin_time", begin_time, "end_time", end_time,
				"certificate_name", certificate_name, "access_token", token, queryCertificateUrl);

	}

	// ????????????????????????
	public static String getMyCertificateName(String name) {
		String res = queryMyCertificate("0", "", "", name);
		String certificate_name = (String) JSONPath.read(res, "$.list[0].certificate_name");
		return certificate_name;
	}
	// --------------------------------------------------????????????????????????-----------------------------------------------------------------------------

	// ????????????????????????
	public static String getScoreSummary() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryScoreSummaryUrl);

	}

	// ??????????????????
	public static int getMyTotalScore() {
		String res = getScoreSummary();
		int Score = (int) JSONPath.read(res, "$.totalScore");
		return Score;
	}

	// --------------------------------------------------????????????????????????-----------------------------------------------------------------------------

	/**   
	 * @Title: getUserTrainArchivesDetailList   
	 * @Description: TODO()   
	 * @param: @param beginTime
	 * @param: @param endTime
	 * @param: @param trainType (0--?????? ??? 1--??????????????? ???2--???????????? ??? 3--???????????? ??? 4--????????????)
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

	// ??????????????????
	public static int getMyCertificateTotal() {
		String res = queryMyCertificate("0", "", "", "");
		int total = (int) JSONPath.read(res, "$.total");
		return total;
	}

}
