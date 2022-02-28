package cn.kxy.archives.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;
import com.lazy.httpclient.utils.HttpResponse;

public class ScoreRankListBusiness {
	public static String enterpriseUrl=  EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId=EnterpriseData.getEnterpriseId();
	public static String userId = UserBusiness.getUserId();
	
	public static String getScoreRankListUrl = enterpriseUrl +"score/getScoreRank";
	
	public static String getMyScoreSummaryUrl = enterpriseUrl +"score/getMyScoreSummary";
	
	public static String getScoreRecordUrl = enterpriseUrl +"score/getScoreRecord";
	
	public static String deleteScoreUrl =enterpriseUrl +"v2/"+enterpriseId+"/scores/delete";
	
	public static String exportScoreRankUrl = enterpriseUrl + "score/exportScoreRank";
	
	public static String exportScoreRecordUrl = enterpriseUrl + "score/exportScoreRecord";
	
	public static String award_score_url = enterpriseUrl + "v2/"+enterpriseId+"/scores/award";
	
	/**   
	 * @Title: awardScore   
	 * @Description: TODO  手动导入学分
	 * @param: @param time
	 * @param: @param award_reason
	 * @param: @param score
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String awardScore (String time ,String award_reason,String score) {
		return HttpRequest.post(award_score_url).query("access_token",token).data("{\r\n" + 
				"  \"group_ids\":\"\",\r\n" + 
				"  \"department_ids\":\"\",\r\n" + 
				"  \"user_ids\":\""+userId+"\",\r\n" + 
				"  \"post_ids\":\"\",\r\n" + 
				"  \"time\":"+time+",\r\n" + 
				"  \"award_reason\":\""+award_reason+"\",\r\n" + 
				"  \"score\":"+score+",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"").send().body();
	}
	
	public static int exportScoreRecordByUser() {
		return HttpRequest.get(exportScoreRecordUrl).query("userId", userId).query("pageNumber","1").query("pageSize","20").
				 query("access_token", token).query("excludeSelf","false").contentType("application/json;charset=UTF-8")
					.send().statusCode();
	}
	
	public static int getExportScoreRankCodeByTime (String startTime,String endTime) {
		return HttpResponse.getstatusCode(exportScoreRankUrl, token, "startTime", startTime,"endTime",endTime);
	}
	
	public static int getExportScoreRankCodeByUser () {
		return HttpResponse.getstatusCode(exportScoreRankUrl, token, "keyword", UserBusiness.getUsername());
	}
	
	/**   
	 * @Title: getExportScoreRankCode   
	 * @Description: TODO(导出学分排行榜)   
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getExportScoreRankCode() {
		return HttpResponse.getstatusCode(exportScoreRankUrl, token, "excludeSelf", "false");
	}
	
	/**   
	 * @Title: deleteScore   
	 * @Description: TODO(撤销学分)   
	 * @param: @param score_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteScore(String score_id) {
		return PostRequestTools.RequestBodyByPost("{\"score_record_ids\":[\""+score_id+"\"],\"access_token\":\""+token+"\"}", token, deleteScoreUrl);
	}

	/**   
	 * @Title: getScoreRecord   
	 * @Description: TODO(查看个人学分记录)   
	 * @param: @param startTime
	 * @param: @param endTime
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getScoreRecord(String startTime,String endTime) {
			return GetRequestTools.RequestQueryParamsByGet("excludeSelf", "false", "userId",userId,"startTime",startTime,
					"endTime",endTime,"pageNumber","1","pageSize","20","access_token",token,getScoreRecordUrl);
	}
	
	/**   
	 * @Title: getMyScoreSummary   
	 * @Description: TODO(查看学分总览)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getMyScoreSummary() {
		return GetRequestTools.RequestQueryParamsByGet("userId", userId, "access_token", token, getMyScoreSummaryUrl);
	}
	
	/**   
	 * @Title: getScoreRankList   
	 * @Description: TODO(查看学分排行)   
	 * @param: @param keyword
	 * @param: @param startTime
	 * @param: @param endTime
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getScoreRankList(String keyword,String startTime,String endTime) {
		return GetRequestTools.RequestQueryParamsByGet("keyword",keyword,"excludeSelf", "false", "departmentId","1","startTime",startTime,
				"endTime",endTime,"pageNumber","1","pageSize","20","access_token",token,getScoreRankListUrl);
	}
}
