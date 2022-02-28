package cn.kxy.study.business;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpResponse;

public class LiveBroadcastBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String live_url = EnterpriseDataUrl.getLiveUrl();
	public static String user_id = UserBusiness.getUserId();
	public static String add_url =live_url + "v2/enterprises/"+enterprise_id+"/live_rooms";
	
	public static String list_url = live_url + "v2/enterprises/"+enterprise_id+"/live_rooms";
	
	public static String delete_url(String id) {
		return live_url +"v2/enterprises/"+enterprise_id+"/live_rooms/"+id+"/delete";
	}
	
	/**   
	 * @Title: getLiveList   
	 * @Description: TODO(获取直播列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String getLiveList() {
		return GetRequestTools.RequestQueryParamsByGet("page_number", "1","page_size","10",
				"access_token",token, list_url);
	}
	
	/**   
	 * @Title: deleteLive   
	 * @Description: TODO(删除直播)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String deleteLive(String id) {
		return PostRequestTools.RequestBodyByPost("{\"access_token\":\""+token+"\"}", token, delete_url(id));
	}
	
	public static String addLive(String name,String save_to_resource,String max_viewer) {
		return HttpResponse.postJson(add_url, "{\r\n" + 
				"	\"name\": \""+name+"\",\r\n" + 
				"	\"save_to_resource\": \""+save_to_resource+"\",\r\n" + 
				"	\"max_viewer\": "+max_viewer+",\r\n" + 
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}", "access_token", token);
			}

}
 