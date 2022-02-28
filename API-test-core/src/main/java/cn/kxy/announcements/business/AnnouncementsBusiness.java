package cn.kxy.announcements.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class AnnouncementsBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterpriseId = EnterpriseData.getEnterpriseId();
	public static String enterpriseUrl = EnterpriseDataUrl.getEnterpriseUrl();

	public static String addUrl = enterpriseUrl + "v2/"+enterpriseId+"/announcements";
	public static String queryListUrl = enterpriseUrl +"v2/"+enterpriseId+"/announcements";
	public static String user_id = UserBusiness.getUserId();
	public static String deleteUrl(String announcementsId) {
		return enterpriseUrl +"v2/"+enterpriseId+"/announcements/"+announcementsId+"/delete";
	}
	public static String editUrl(String announcementsId) {
		return enterpriseUrl +"v2/"+enterpriseId+"/announcements/"+announcementsId+"/edit";
	}
	
	public static String stickAnnouncementUrl (String announcementsId) {
		return enterpriseUrl + "v2/"+enterpriseId+"/announcements/"+announcementsId+"/stick";
	}
	
	public static String queryInfoUrl (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/announcements/"+id;
	}
	
	public static String query_app_List_url = enterpriseUrl + "v2/"+enterpriseId+"/user/"+user_id+"/announcements";
	
	/**   
	 * @Title: queryAppList   
	 * @Description: TODO  查看移动端公告列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryAppList() {
		return HttpRequest.get(query_app_List_url).query("access_token", token).query("enterprise_id", enterpriseId)
				.query("page_number", "1").query("page_size", "20").query("user_id", user_id).send().body();
	}
	
	/**   
	 * @Title: queryInfo   
	 * @Description: TODO(查询公告详情)   
	 * @param: @param id
	 * @param: @param source
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInfo (String id,String source) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,"source",source, queryInfoUrl(id));
	}
	
	/**   
	 * @Title: stickAnnouncement   
	 * @Description: TODO(对公告进行顶置和取消顶置操作)   
	 * @param: @param announcementsId
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String stickAnnouncement(String announcementsId,String status) {
		return PostRequestTools.RequestBodyByPost("{\"status\":\""+status+"\",\"access_token\":\""+token+"\"}", token, stickAnnouncementUrl(announcementsId));
	}
	
	/**   
	 * @Title: deleteAnnouncements   
	 * @Description: TODO()删除公告   
	 * @param: @param announcementsId
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String deleteAnnouncements(String announcementsId) {
		return PostRequestTools.RequestBodyByPost("", token, deleteUrl(announcementsId));
	}
	
	/**   
	 * @Title: queryAnnouncementsList   
	 * @Description: TODO(查询公告列表)   
	 * @param: @param keywords
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryAnnouncementsList(String keywords) {
		return GetRequestTools.RequestQueryParamsByGet("access_token",token,"keywords", keywords,"page_number","1","page_size","20", queryListUrl);
	}
	
	/**   
	 * @Title: editannouncements   
	 * @Description: TODO(编辑公告)   
	 * @param: @param announcementsId
	 * @param: @param title
	 * @param: @param status
	 * @param: @param context
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String editAnnouncements(String announcementsId,String title,String status,String context,String id,String is_all,String user_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\": \""+title+"\", \r\n" + 
				"  \"status\": \""+status+"\", \r\n" + 
				"  \"context\": \"<p>"+context+"</p>\", \r\n" + 
				"  \"is_all\": \""+is_all+"\", \r\n" + 
				"  \"user_ids\": \""+user_id+"\", \r\n" + 
				"  \"department_ids\": \"\", \r\n" + 
				"  \"group_ids\": \"\", \r\n" + 
				"  \"post_ids\": \"\", \r\n" + 
				"  \"id\": \""+id+"\", \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, editUrl(announcementsId));
		
	}
	
	/**   
	 * @Title: getIdByKeyword   
	 * @Description: TODO(通过关键字得到id)   
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String getIdByKeyword(String keyword) {
		String res = queryAnnouncementsList(keyword);
		String id = (String)JSONPath.read(res, "$.list[0].id");
		return id;
	}
	
	/**   
	 * @Title: addAnnouncements   
	 * @Description: TODO(新增公告OPEN  CLOSED)   
	 * @param: @param title
	 * @param: @param status
	 * @param: @param context
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addAnnouncements(String title,String status,String context,String is_all,String user_id) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"title\":\""+title+"\",\r\n" + 
				"  \"status\":\""+status+"\",\r\n" + 
				"  \"context\":\"{\\\"blocks\\\":[{\\\"key\\\":\\\"48p0i\\\",\\\"text\\\":\\\""+context+"\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}}\",\r\n" + 
				"  \"is_all\":\""+is_all+"\",\r\n" + 
				"  \"user_ids\":\""+user_id+"\",\r\n" + 
				"  \"department_ids\":\"\",\r\n" + 
				"  \"group_ids\":\"\",\r\n" + 
				"  \"post_ids\":\"\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"", token, addUrl);
	}
	
}
