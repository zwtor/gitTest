package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.httpclient.utils.HttpRequest;
public class AuthUserBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String enterprise_url= EnterpriseDataUrl.getEnterpriseUrl();
	public static String user_id = UserBusiness.getUserId();
	public static String user_name = UserBusiness.getUsername();
	public static String user_group_url = enterprise_url + "v2/"+enterprise_id+"/groups"; 
	public static String department_url =enterprise_url + "v2/"+enterprise_id+"/departments/tree";
	public static String queryDepartUserUrl(String dep_id) {
		return enterprise_url + "v2/"+enterprise_id+"/departments/"+dep_id+"/users";
	}
	public static String post_group_url = enterprise_url + "v2/"+enterprise_id+"/posts";
	
	public static  String queryDepartUser(String id) {
		return HttpRequest.get(queryDepartUserUrl(id)).query("recursion", "true").query("page_number", "1").
				query("page_size", "20").query("filter_auth", "true").query("user_type", "active")
				.query("access_token", token).send().body();
	}
	
	

	/**   
	 * @Title: queryUsePostList   
	 * @Description: TODO(查看岗位列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryUsePostList() {
		return GetRequestTools.RequestParamsByGet("access_token",token,"filter_auth","true","post_id","1",post_group_url);
	}
	
	/**   
	 * @Title: queryUserGroupList   
	 * @Description: TODO(获取用户组列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryUserGroupList() {
		return GetRequestTools.RequestParamsByGet("access_token",token,"filter_auth","true",user_group_url);
	}
	
	/**   
	 * @Title: GetDepartment   
	 * @Description: TODO(查询部门列表)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static  String GetDepartmentList() {
		return HttpRequest.get(department_url).query("recursion", "false").query("query_user_count", "true").
				query("parent_id", "1").query("filter_auth", "true").query("user_type", "active")
				.query("access_token", token).send().body();
	}
}
