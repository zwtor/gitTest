package cn.kxy.course.resources.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class MicroCourseBusines {
	public static String enterprise_url=  EnterpriseDataUrl.getEnterpriseUrl();
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String user_id = UserBusiness.getUserId();
	public static String query_list_url =enterprise_url + "v2/"+enterprise_id+"/users/"+user_id+"/micro_courses";

	public static String distinct_url = enterprise_url + "v2/"+enterprise_id+"/departments/users/distinct_new";
	
	/**   
	 * @Title: queryDistinctNum   
	 * @Description: TODO(查看微课选人后的人数)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryDistinctNum() {
		return PostRequestTools.RequestBodyByPost("{\r\n" + 
				"	\"dept_ids\": [],\r\n" + 
				"	\"user_ids\": [\""+user_id+"\"]\r\n" + 
				"}", token, distinct_url);
	}
	
	/**   
	 * @Title: queryMicroCourseList   
	 * @Description: TODO(查看微课列表页)   
	 * @param: @param status
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryMicroCourseList(String status) {
		return HttpRequest.get(query_list_url).query("access_token", token).query("enterprise_id", enterprise_id)
				.query("page_number", "1").query("page_size", "20").query("status", status)
				.query("user_id", user_id).send().body();
	}
	
}
