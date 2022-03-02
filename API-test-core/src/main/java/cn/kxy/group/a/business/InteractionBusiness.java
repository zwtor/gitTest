package cn.kxy.group.a.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.common.utils.DateUtil;
import com.lazy.httpclient.utils.HttpRequest;

public class InteractionBusiness {

	public static String enterprise_url = EnterpriseDataUrl.getInteractionUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String competition_save_url = enterprise_url + "v1/"+enterpriseId+"/competition/save/";
	public static String competition_list_url = enterprise_url + "v1/"+enterpriseId+"/competition/"+"/user/"+user_id+"/list/";
	
	public static String competiton_edit_url (String id) {
		return enterprise_url + "v1/"+enterpriseId+"/competition/"+id+"/edit/";
	}
	
	public static String queryDataListUrl(String id) {
		return enterprise_url + "v1/"+enterpriseId+"/competition/"+id+"/user/list";
	}
	
	public static String competition_invite_url = enterprise_url + "v1/"+enterpriseId+"/competition/invite/";
	public static String my_home_url = enterprise_url + "v1/"+enterpriseId+"/competition/user/"+user_id+"/my_home/";
	public static String my_pk_list_url = enterprise_url + "v1/"+enterpriseId+"/competition/user/"+user_id+"/my_pk/list/";
	public static String pk_start_url (String instance_id) {
		return enterprise_url + "v1/"+enterpriseId+"/competition/"+"/"+instance_id+"/"+"/start/";
	}
	public static String pk_status_url =  enterprise_url + "v1/"+enterpriseId+"/competition/user/"+user_id+"/status/";
	public static String pk_detail_url (String id) {
		return enterprise_url + "v1/"+enterpriseId+"/competition/"+"/"+id+"/"+"/detail/";
	}
	
	public static String save_url (String instance_id) {
		return enterprise_url + "v1/"+enterpriseId+"/competition/"+"/"+instance_id+"/"+"/user/"+user_id+"/save/";
	}
	
	public static String statistics_url (String instance_id) {
		return enterprise_url + "v1/"+enterpriseId+"/competition/"+"/"+instance_id+"/"+"/user/"+user_id+"/statistics/";
	}
	
	public static String answer_url (String instance_id) {
		return enterprise_url + "v1/"+enterpriseId+"/competition/"+"/"+instance_id+"/"+"/user/"+user_id+"/answer/";
	}
	
	public static String result_url (String instance_id) {
		return enterprise_url + "v1/"+enterpriseId+"/competition/"+"/"+instance_id+"/"+"/result/";
	}
	
	public static String answer_list_url = enterprise_url + "v1/"+enterpriseId+"/competition/user/"+user_id+"/answer_list/";
	public static String top_ranking_url = enterprise_url + "v1/"+enterpriseId+"/competition/"+"/top_ranking/";
		
	public static String deleteUrl(String id) {
		return enterprise_url + "v1/"+enterpriseId+"/competition/"+id+"/delete";
	}
	
	public static String export_list_url = enterprise_url + "v1/"+enterpriseId+"/competition/user/"+user_id+"/list/export";
	
	public static String deletePkGame(String id) {
		return HttpRequest.post(deleteUrl(id)).query("access_token", token).data("{\"access_token\":\""+token+"\"}").send().body();	
	}
	
	public static String exportDataUrl (String id) {
		return enterprise_url + "v1/"+enterpriseId+"/competition/"+id+"/user/list/export";
	}
	
	public static String exportDataList(String id) {
		return HttpRequest.get(exportDataUrl(id)).query("type","not_participating").query("page_size","10").query("page_number","1")
				.query("begin_time",DateUtil.getTimeStamp(-7, "")).query("end_time",DateUtil.getTimeStamp(0, ""))
				.query("access_token", token).send().body();	
	}
	
	public static String exportList(String keyword) {
		return HttpRequest.get(export_list_url).query("type","multiple").query("page_size","10").query("page_number","1")
				.query("keyword", keyword).query("begin_time",DateUtil.getTimeStamp(-7, "")).query("end_time",DateUtil.getTimeStamp(0, ""))
				.query("access_token", token).send().body();	
	}
	public static String report_list_url = enterprise_url + "v1/"+enterpriseId+"/competition/report/list/";
	public static String user_report_list_url = enterprise_url + "v1/"+enterpriseId+"/competition/user/"+user_id+"/report/list/";
	
	/**   
	 * @Title: queryDataList   
	 * @Description: TODO  查看pk赛数据
	 * @param: @param keyword
	 * @param: @param type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryDataList(String id,String keyword,String type) {
		return HttpRequest.get(queryDataListUrl(id)).query("type",type).query("page_size","10").query("page_number","1")
				.query("keyword", keyword).query("begin_time",DateUtil.getTimeStamp(-7, "")).query("end_time",DateUtil.getTimeStamp(0, ""))
				.query("access_token", token).send().body();	
	}
	/**   
	 * @Title: CompetitionSave   
	 * @Description: TODO  创建PK赛
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CompetitionSave(String title ,String type,String is_all,String question_bank,String gain_point,String deduction_point) {
		
		return HttpRequest.post(competition_save_url).query("access_token", token).data("{\r\n" + 
				"  \"title\":\""+title+"\",\r\n" + 
				"  \"description\":\"this is a des\",\r\n" + 
				"  \"end_time\":"+DateUtil.getTimeStamp(9, "")+",\r\n" + 
				"  \"type\":\""+type+"\",\r\n" + 
				"  \"is_all\":\""+is_all+"\",\r\n" + 
				"  \"departments\":\"\",\r\n" + 
				"  \"posts\":\"\",\r\n" + 
				"  \"groups\":\"\",\r\n" + 
				"  \"user_ids\":\""+user_id+"\",\r\n" + 
				"  \"question_bank\":\""+question_bank+"\",\r\n" + 
				"  \"single_num\":1,\r\n" + 
				"  \"judgment_num\":1,\r\n" + 
				"  \"answer_time\":10,\r\n" + 
				"  \"score\":10,\r\n" + 
				"  \"gain_point\":\""+gain_point+"\",\r\n" + 
				"  \"deduction_point\":\""+deduction_point+"\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: CompetitionList   
	 * @Description: TODO  PK赛列表查询
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CompetitionList() {
		return HttpRequest.get(competition_list_url).query("access_token", token).send().body();	
	}
	
	public static String CompetitionList(String keyword,String type) {
		return HttpRequest.get(competition_list_url).query("type",type).query("page_size","10").query("page_number","1").query("keyword", keyword).query("access_token", token).send().body();	
	}
	
	/**   
	 * @Title: CompetitonEdit  
	 * @Description: TODO  编辑PK赛
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CompetitonEdit(String id,String title ,String type,String is_all,String question_bank,
			String gain_point,String deduction_point) {		
		return HttpRequest.post(competiton_edit_url(id)).query("access_token", token).data("{\r\n" + 
				"  \"title\":\""+title+"\",\r\n" + 
				"  \"description\":\"this is a des\",\r\n" + 
				"  \"end_time\":"+DateUtil.getTimeStamp(9, "")+",\r\n" + 
				"  \"type\":\""+type+"\",\r\n" + 
				"  \"is_all\":\""+is_all+"\",\r\n" + 
				"  \"departments\":\"\",\r\n" + 
				"  \"posts\":\"\",\r\n" + 
				"  \"groups\":\"\",\r\n" + 
				"  \"user_ids\":\""+user_id+"\",\r\n" + 
				"  \"question_bank\":\""+question_bank+"\",\r\n" + 
				"  \"single_num\":1,\r\n" + 
				"  \"judgment_num\":1,\r\n" + 
				"  \"answer_time\":10,\r\n" + 
				"  \"score\":10,\r\n" + 
				"  \"gain_point\":\""+gain_point+"\",\r\n" + 
				"  \"deduction_point\":\""+deduction_point+"\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"")
				.send().body();
	}
	
	/**   
	 * @Title: competition_invite_url  
	 * @Description: TODO  移动端发起PK邀请
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String CompetitionInvite(String plan_id) {
		return HttpRequest.post(competition_invite_url).query("access_token", token).data("{\r\n" + 
				"	\"type\": \"multiple\",\r\n" + 
				"	\"instance_id\": \"\",\r\n" + 
				"	\"plan_id\": \""+plan_id+"\",\r\n" + 
				"	\"challenger_captain\": \""+user_id+"\",\r\n" + 
				"	\"recipienter_captain\": \"\",\r\n" + 
				"   \"challenger_team\": [], \r\n" + 				
				"  \"recipienter_team\": [\r\n" + 
				"    \""+user_id+"\"\r\n" + 
				"  ], \r\n" + 				
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}")
				.send().body();
	}
	
	/**   
	 * @Title: MyHome   
	 * @Description: TODO  移动端PK赛首页
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String MyHome() {
		return HttpRequest.get(my_home_url).query("access_token", token).send().body();	
	}
	
	/**   
	 * @Title: MyPKList   
	 * @Description: TODO  移动端我的PK列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String MyPkList(String status) {
		return HttpRequest.get(my_pk_list_url).query("access_token", token).query("status",status)
				.send().body();	
	}
	
	/**   
	 * @Title: MyPkStart   
	 * @Description: TODO  开始对战获取试卷内容
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PkStart(String instance_id) {
		return HttpRequest.get(pk_start_url(instance_id)).query("access_token", token).send().body();	
	}
	
	/**   
	 * @Title: MyPkStatus   
	 * @Description: TODO  开始对战status状态检验
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String MyPkStatus(String plan_id,String instance_id) {
		return HttpRequest.get(pk_status_url).query("access_token", token).query("plan_id",plan_id).query("instance_id",instance_id)
				.send().body();	
	}
	
	/**   
	 * @Title: MyPkDetail   
	 * @Description: TODO  开始对战detail状态检验
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String MyPkDetail(String id) {
		return HttpRequest.get(pk_detail_url(id)).query("access_token", token).send().body();	
	}
	
//	/**   
//	 * @Title: PKSave   
//	 * @Description: TODO  开始对战保存答案
//	 * @param: @return      
//	 * @return: String      
//	 * @throws   
//	 */ 
//	public static String PKSave(String instance_id,String first_questions_id) {
//		return HttpRequest.post(save_url(instance_id)).query("access_token", token).data("{\r\n" + 
//				"   \"questions\":[\r\n" + 
//				"     {\r\n" + 
//				"       \"id\":\""+first_questions_id+"\",\r\n" + 
//				"       \"answer\":\"1799316394198831104\",\r\n" + 
//				"       \"time_long\":1,\r\n" + 
//				"       \"result\":true,\r\n" +
//				"     }]\r\n" + 			
//				"   \"score\": 10,\r\n" + 
//				"	\"access_token\": \""+token+"\"\r\n" + 
//				"}")
//				.send().body();	
//	}
	
	/**   
	 * @Title: PkStatistics   
	 * @Description: TODO  获取试题得分
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PkStatistics(String instance_id,String type) {
		return HttpRequest.get(statistics_url(instance_id)).query("access_token", token).query("type", type).send().body();	
	}
	
	/**   
	 * @Title: PkAnswer   
	 * @Description: TODO  提交试卷
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PkAnswer(String instance_id,String type) {
		return HttpRequest.get(answer_url(instance_id)).query("access_token", token).query("type", type).send().body();	
	}
	
	/**   
	 * @Title: PkResult   
	 * @Description: TODO  获取得分或者结果
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PkResult(String instance_id,String type) {
		return HttpRequest.get(result_url(instance_id)).query("access_token", token).query("type", type).query("user_id", user_id).
				send().body();	
	}

	/**   
	 * @Title: PkAnswerList   
	 * @Description: TODO  答题记录列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PkAnswerList(String type) {
		return HttpRequest.get(answer_list_url).query("access_token", token).query("type", type).send().body();	
	}
	
	/**   
	 * @Title: PkTopRanking  
	 * @Description: TODO  PK赛排行榜
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PkTopRanking() {
		return HttpRequest.get(top_ranking_url).query("access_token", token).send().body();	
	}
	
	
	/**   
	 * @Title: ReportList  
	 * @Description: TODO  PK赛报表列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String PkReportList(String keyword) {
		return HttpRequest.get(report_list_url).query("access_token", token).query("keyword",keyword).query("page_number","1")
				.query("page_size","20").query("dept_id","")
				.send().body();	
	}
	
	/**   
	 * @Title: UserReportList  
	 * @Description: TODO  PK赛报表参赛结果明细
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String UserReportList(String keyword,String start_time,String end_time) {
		return HttpRequest.get(user_report_list_url).query("access_token", token).query("keyword",keyword).query("page_number","1")
				.query("page_size","20").query("start_time",start_time).query("end_time",end_time)
				.send().body();	
	}

	
	
}
