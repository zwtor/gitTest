package cn.kxy.authentication.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpResponse;

public class PostMapBusiness {
	public static String enterpriseUrl=  EnterpriseDataUrl.getEnterpriseUrl();
	
	public static String token = TokenData.getMangerToken();
	
	public static String enterpriseId=EnterpriseData.getEnterpriseId();
	
	public static String userId = UserBusiness.getUserId();
	public static String username = UserBusiness.getUsername();
	
	public static String queryInfoUrl(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/post_maps/"+id+"/query";
	}
	
	public static String post_maps_url = enterpriseUrl + "v2/"+enterpriseId+"/post_maps";

	public static String update_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/post_maps/"+id+"/update_status";
	}
	
	public static String monitor_url(String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/post_maps/"+id+"/users";
	}
	
	public static String edit_url (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/post_maps/"+id+"/update";
	}
	
	public static String add_url = enterpriseUrl + "v2/"+enterpriseId+"/post_maps";
	
	public static String post_maps_users_export_url(String id) {
		return enterpriseUrl +"v2/"+enterpriseId+"/post_maps/"+id+"/users_export";
	}
	
	public static String deleteUrl (String id) {
		return enterpriseUrl + "v2/"+enterpriseId+"/post_maps/"+id+"/delete";
	}
	
	public static String deleteMap(String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, deleteUrl(id));
	}
	
	/**   
	 * @Title: getPostMapsExportCode   
	 * @Description: TODO(岗位地图导出)   
	 * @param: @param id
	 * @param: @param keyword
	 * @param: @return      
	 * @return: int      
	 * @throws   
	 */ 
	public static int getPostMapsExportCode(String id,String keyword) {
		return HttpResponse.getstatusCode(post_maps_users_export_url(id), token, "department_id","1","keyword",keyword);
	}
	
	/**   
	 * @Title: queryInfo   
	 * @Description: TODO(查看岗位详情)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryInfo (String id) {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token, queryInfoUrl(id));
	}
	
	/**   
	 * @Title: addPostMap   
	 * @Description: TODO(添加岗位地图)   
	 * @param: @param name
	 * @param: @param des
	 * @param: @param qualification_id1
	 * @param: @param qualification_id2
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String addPostMap(String name ,String visible_range,String des,String qualification_id1,String qualification_id2,String map_template) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"checkpoint_unlock\": \"pass\", \r\n" + 
				"  \"checkpoints\": [\r\n" + 
				"    {\r\n" + 
				"      \"qualification_id\": \""+qualification_id1+"\", \r\n" + 
				"      \"score\": 3, \r\n" + 
				"      \"sort\": 1\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"qualification_id\": \""+qualification_id2+"\", \r\n" + 
				"      \"score\": 5, \r\n" + 
				"      \"sort\": 2\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"cover\": \"https://oss.coolcollege.cn/1789918456309747712.png\", \r\n" + 
				
				"  \"introduction\": \""+des+"\", \r\n" + 
				"\"map_template\":\""+map_template+"\","+
				"  \"is_extra_score\": false, \r\n" + 
				"  \"name\": \""+name+"\", \r\n" + 
				" \"visible_range\": \""+visible_range+"\", "+
				"  \"send_edit_message\": false, \r\n" + 
				"  \"scopes\": {\r\n" + 
				"    \"department_list\": [ ], \r\n" + 
				"    \"group_list\": [ ], \r\n" + 
				"    \"post_list\": [ ], \r\n" + 
				"    \"user_list\": [\r\n" + 
				"      {\r\n" + 
				"        \"id\": \""+userId+"\", \r\n" + 
				"        \"name\": \""+username+"\"\r\n" + 
				"      }\r\n" + 
				"    ]\r\n" + 
				"  }, \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, add_url);
	}
	
	/**   
	 * @Title: editPostMap   
	 * @Description: TODO(编辑岗位地图)   
	 * @param: @param id
	 * @param: @param qualification_id1
	 * @param: @param qualification_id2
	 * @param: @param title
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String editPostMap(String id,String qualification_id1,String qualification_id2,String title  ) {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"  \"checkpoint_unlock\": \"pass\", \r\n" + 
				"  \"checkpoints\": [\r\n" + 
				"    {\r\n" + 
				"      \"qualification_id\": \""+qualification_id1+"\", \r\n" + 
				"      \"score\": 4, \r\n" + 
				"      \"sort\": 1\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"qualification_id\": \""+qualification_id2+"\", \r\n" + 
				"      \"score\": 2, \r\n" + 
				"      \"sort\": 2\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"cover\": \"https://oss.coolcollege.cn/1789918456309747712.png\", \r\n" + 
				"  \"introduction\": \"this is des\", \r\n" + 
				"  \"is_extra_score\": true, \r\n" + 
				"  \"name\": \""+title+"\", \r\n" + 
				"  \"send_edit_message\": true, \r\n" + 
				"\"map_template\":\"DESERT_OASIS\","+
				" \"visible_range\": \"all\", "+
				"  \"scopes\": {\r\n" + 
				"    \"department_list\": [ ], \r\n" + 
				"    \"group_list\": [ ], \r\n" + 
				"    \"post_list\": [ ], \r\n" + 
				"    \"user_list\": []\r\n" + 
				"  }, \r\n" + 
				"  \"checkpoint_count\": 1, \r\n" + 
				"  \"checkpoint_score\": 3, \r\n" + 
				"  \"access_token\": \""+token+"\"\r\n" + 
				"}", token, edit_url(id));
	}
	
	/**   
	 * @Title: monitorPostMap   
	 * @Description: TODO(查看岗位地图的监控)   
	 * @param: @param id
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String monitorPostMap(String id,String keyword) {
		return GetRequestTools.RequestQueryParamsByGet("keyword", keyword, "page_number","1","page_size","20",
				"department_id","1","access_token",token,monitor_url(id));
	}
	
	/**   
	 * @Title: updateStatus   
	 * @Description: TODO(更新岗位地图状态：open--启用，close--停用)   
	 * @param: @param id
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String updateStatus(String id,String status) {
		return PostRequestTools.RequestBodyByPost("{\"status\":\""+status+"\",\"access_token\":\""+token+"\"}", token, update_url(id));
	}
	
	/**   
	 * @Title: queryPostMapsList   
	 * @Description: TODO(查询岗位地图列表)   
	 * @param: @param keyword
	 * @param: @param pass_type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */ 
	public static String queryPostMapsList (String keyword,String status) {
		return GetRequestTools.RequestQueryParamsByGet("keyword", keyword, "access_token", token,"status",status,
				"page_number","1","page_size","20",post_maps_url);
	}
}
