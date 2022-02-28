package cn.kxy.archives.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.httpclient.utils.HttpRequest;
import com.lazy.httpclient.utils.HttpResponse;

import static io.restassured.RestAssured.given;

public class StaffArchivesBusiness {
	public static String enterpriseUrl=  EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId=EnterpriseData.getEnterpriseId();
	public static String userId = UserBusiness.getUserId();
	
	public static String getUserTrainArchivesListUrl = enterpriseUrl+"report/trainArchives/getUserTrainArchivesList";
	
	public static String getUserTrainArchivesDetailListUrl =enterpriseUrl + "report/trainArchives/getUserTrainArchivesDetailList";
	
	public static String course_archives_url =enterpriseUrl + "v2/"+enterpriseId+"/course_archives";
	
	public static String user_course_archives =enterpriseUrl + "v2/"+enterpriseId+"/user_course_archives";
	
	public static String qualification_archives_url = enterpriseUrl + "v2/"+enterpriseId+"/qualification_archives";
	
	public static String user_qualification_archives_url = enterpriseUrl +"v2/"+enterpriseId+"/user_qualification_archives";
	
	public static String exportUserTrainArchivesListUrl = enterpriseUrl + "report/trainArchives/exportUserTrainArchivesList";
	
	public static String detail_export_url =enterpriseUrl + "v2/"+enterpriseId+"/user_plans_archives_detail_export";
	
	public static String selfArchivesListUrl = enterpriseUrl + "v2/"+enterpriseId+"/course_archives_export";
	
	public static String selfArchivesInfoUrl = enterpriseUrl + "v2/"+enterpriseId+"/course_archives_detail_export";
	
	public static String qualificationArchivesListUrl = enterpriseUrl + "v2/"+enterpriseId+"/qualification_archives_export";
	
	public static String qualificationArchivesInfoUrl = enterpriseUrl + "v2/"+enterpriseId+"/qualification_archives_detail_export";

	public static String exportUserTrainArchivesDetailListUrl = enterpriseUrl + "report/trainArchives/exportUserTrainArchivesDetailList";
	
	
	public static int exportUserTrainArchivesDetailList(String dimission_status,String user_id) {
		return HttpRequest.get(exportUserTrainArchivesDetailListUrl).query("dimission_status", dimission_status).query("pageNumber", "1").query("pageSize", "20").
				 query("access_token", token).query("trainType","0").query("userId",user_id).query("access_token",token).contentType("application/json;charset=UTF-8")
					.query("beginTime","").query("endTime","").send().statusCode();
	}
	
	/**   
	 * @Title: getQualificationArchivesListCode   
	 * @Description: TODO(获取认证明细数据的code)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getQualificationArchivesInfoCode() {
		return HttpResponse.getstatusCode(qualificationArchivesInfoUrl, token, "dimission_status", "incumbency");
	}
	
	/**   
	 * @Title: getQualificationArchivesInfoCode   
	 * @Description: TODO(获取认证汇总数据的code)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getQualificationArchivesListCode() {
		return HttpResponse.getstatusCode(qualificationArchivesListUrl, token, "dimission_status", "incumbency");
	}
	/**   
	 * @Title: getSelfArchivesInfoCode   
	 * @Description: TODO(获取自学明细状态码)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getSelfArchivesInfoCode(String dimission_status) {
		return HttpResponse.getstatusCode(selfArchivesInfoUrl, token, "dimission_status", dimission_status);
	}
	
	/**   
	 * @Title: getSelfArchivesListCode   
	 * @Description: TODO(自学汇总数据的状态码)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getSelfArchivesListCode(String dimission_status) {
		return HttpResponse.getstatusCode(selfArchivesListUrl, token, "dimission_status", dimission_status,"page_number","1");
	}
	
	/**   
	 * @Title: getDetailExportCode   
	 * @Description: TODO(任务档案的任务明细数据)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getTaskDetailExportCode(String dimission_status) {
		return HttpResponse.getstatusCode(detail_export_url, token, "dimission_status", dimission_status);
	}
	
	/**   
	 * @Title: getExportUserTrainCode   
	 * @Description: TODO(获取任务档案的任务汇总数据状态码)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getTaskExportUserTrainCode(String dimission_status) {
		return HttpResponse.getstatusCode(exportUserTrainArchivesListUrl, token, "dimission_status", dimission_status,"pageNumber","1","pageSize","20");
	}
	/**   
	 * @Title: queryQualificationArchivesInfo   
	 * @Description: TODO(查看认证档案详情)   
	 * @param: @param dimission_status
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryQualificationArchivesInfo(String dimission_status,String status) {
		
		return given().queryParam("user_id", userId).queryParam("dimission_status", dimission_status)
				.queryParam("status", status).queryParam("page_number", "1")
				.queryParam("page_size", "20").queryParam("access_token", token).get(user_qualification_archives_url)
				.asString();
	}
	
	
	/**   
	 * @Title: queryQualificationArchivesList   
	 * @Description: TODO(查看认证档案列表)   
	 * @param: @param keyword
	 * @param: @param department_id
	 * @param: @param dimission_status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryQualificationArchivesList  (String keyword,String department_id,String dimission_status) {
		
		return given().queryParam("dimission_status", dimission_status)
				.queryParam("keyword", keyword).queryParam("page_number", "1")
				.queryParam("page_size", "20").queryParam("access_token", token).get(qualification_archives_url)
				.asString();
	}
	
	/**   
	 * @Title: querySelfStudyInfo   
	 * @Description: TODO(查看自学档案详情)   
	 * @param: @param department_id
	 * @param: @param dimission_status
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String querySelfStudyInfo(String dimission_status,String status) {
		
		return given().queryParam("user_id", userId).queryParam("dimission_status", dimission_status)
				.queryParam("status", status).queryParam("page_number", "1")
				.queryParam("page_size", "20").queryParam("access_token", token).get(user_course_archives)
				.asString();
	}
	
public static String querySelfStudyInfo(String dimission_status,String status,String user_id) {
		
		return given().queryParam("user_id", user_id).queryParam("dimission_status", dimission_status)
				.queryParam("status", status).queryParam("page_number", "1")
				.queryParam("page_size", "20").queryParam("access_token", token).get(user_course_archives)
				.asString();
	}
	
	/**   
	 * @Title: querySelfStudy   
	 * @Description: TODO(查看自学档案列表)   
	 * @param: @param keyword
	 * @param: @param department_id
	 * @param: @param dimission_status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String querySelfStudyList  (String keyword,String department_id,String dimission_status) {
		return given().queryParam("department_id", department_id).queryParam("dimission_status", dimission_status)
				.queryParam("keyword", keyword).queryParam("page_number", "1")
				.queryParam("page_size", "20").queryParam("access_token", token).get(course_archives_url)
				.asString();
	}
	
	/**   
	 * @Title: getUserTrainArchivesDetailList   
	 * @Description: TODO(查看档案列表详情)   
	 * @param: @param keyword
	 * @param: @param beginTime
	 * @param: @param endTime
	 * @param: @param departmentId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getUserTrainArchivesDetailList(String keyword,String beginTime,String endTime,String departmentId,String trainType ) {
		return GetRequestTools.RequestQueryParamsByGet("beginTime", beginTime,"endTime",endTime,"departmentId",departmentId,"dimission_status","incumbency",
				"keyword",keyword,"pageNumber","1","pageSize","20","userId",userId,"trainType",trainType,"access_token",token,getUserTrainArchivesDetailListUrl);
	}
	
	public static String getUserTrainArchivesDetailList(String keyword,String beginTime,String endTime,String departmentId,String trainType,String user_id ) {
		return GetRequestTools.RequestQueryParamsByGet("beginTime", beginTime,"endTime",endTime,"departmentId",departmentId,"dimission_status","incumbency",
				"keyword",keyword,"pageNumber","1","pageSize","20","userId",user_id,"trainType",trainType,"access_token",token,getUserTrainArchivesDetailListUrl);
	}
	
	/**   
	 * @Title: getUserTrainArchivesList   
	 * @Description: TODO(查看任务档案)   
	 * @param: @param keyword
	 * @param: @param beginTime
	 * @param: @param endTime
	 * @param: @param departmentId
	 * @param: @param incumbency
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getUserTrainArchivesList(String keyword,String beginTime,String endTime,String departmentId,String incumbency) {
		return GetRequestTools.RequestQueryParamsByGet("beginTime", beginTime,"endTime",endTime,"departmentId",departmentId,"dimission_status",incumbency,
				"keyword",keyword,"pageNumber","1","pageSize","20","access_token",token,getUserTrainArchivesListUrl);
	}
	
}
