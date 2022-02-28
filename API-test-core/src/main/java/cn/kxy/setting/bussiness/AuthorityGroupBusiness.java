package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.assured.utils.PostRequestTools;
import com.lazy.httpclient.utils.HttpResponse;

public class AuthorityGroupBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String authority_url = EnterpriseDataUrl.getAuthorityUrl();
	public static String user_id = UserBusiness.getUserId();
	public static String user_name = UserBusiness.getUsername();
	public static String add_url =authority_url + "v2/enterprises/"+enterprise_id+"/authority/basic_operations/add";

	public static String query_list_url = authority_url+"v2/enterprises/"+enterprise_id+"/authority/basic_operations/query/1/30";
	
	public static String edit_url (String id) {
		return authority_url + "v2/enterprises/"+enterprise_id+"/authority/basic_operations/update/"+id;
	}
	public static String delete_url = authority_url + "v2/enterprises/"+enterprise_id+"/authority/basic_operations/delete";
	
	public static String queryListByAuthGroupUrl(String id) {
		return authority_url + "v2/enterprises/"+enterprise_id+"/authority/users_relation/query/"+id+"/user/1/20";
	}
	
	public static String addAuthUserUrl(String id) {
		return authority_url + "v2/enterprises/"+enterprise_id+"/authority/users_relation/add/"+id;
	}
	public static String deleteAuthUserUrl(String id) {
		return authority_url + "v2/enterprises/"+enterprise_id+"/authority/users_relation/delete/"+id;
	}
	public static String queryDepartmentUrl(String id) {
		return authority_url + "v2/enterprises/"+enterprise_id+"/authority/users_relation/query/"+id+"/department/1/20";
	}
	
	public static String deleteAuthDepartmentUrl(String id) {
		return authority_url + "v2/enterprises/"+enterprise_id+"/authority/users_relation/delete/"+id;
	}
	/**   
	 * @Title: deleteAuthhDepartment   
	 * @Description: TODO(删除数据权限组下的部门)   
	 * @param: @param id
	 * @param: @param depart_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String deleteAuthhDepartment(String id,String depart_id) {
		return HttpResponse.postJsonGetString(deleteAuthDepartmentUrl(id), "{\r\n" + 
				"	\"relate_id_type\": [\""+depart_id+"department\"],\r\n" + 
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}");
	}
	
	/**   
	 * @Title: queryDepartment   
	 * @Description: TODO(查询数据权限组下的部门)   
	 * @param: @param id
	 * @param: @param relate_name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryDepartment(String id,String relate_name) {
		return GetRequestTools.RequestQueryParamsByGet("relate_name", relate_name, queryDepartmentUrl(id));
	}
	
	/**   
	 * @Title: addAuthGroup   
	 * @Description: TODO(往权限组下，新增部门，用户组，岗位组)   
	 * @param: @param id
	 * @param: @param relateId
	 * @param: @param relateName
	 * @param: @param relateType
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addAuthGroup(String id,String relateId,String relateName,String relateType) {
		return HttpResponse.postJsonGetString(addAuthUserUrl(id),"[{\r\n" + 
				"	\"relateId\": \""+relateId+"\",\r\n" + 
				"	\"relateName\": \""+relateName+"\",\r\n" + 
				"	\"relateType\": \""+relateType+"\",\r\n" + 
				"	\"relateIdType\": \""+relateId+"relateType\",\r\n" + 
				"	\"createUser\": \""+user_id+"\"\r\n" + 
				"}]");
	}
	
	/**   
	 * @Title: addAuthUser   
	 * @Description: TODO(往权限组加用户)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addAuthUser(String id) {
		return HttpResponse.postJsonGetString(addAuthUserUrl(id),"[{\r\n" + 
				"	\"relateId\": \""+user_id+"\",\r\n" + 
				"	\"relateName\": \""+user_name+"\",\r\n" + 
				"	\"relateType\": \"user\",\r\n" + 
				"	\"relateIdType\": \""+user_id+"user\",\r\n" + 
				"	\"createUser\": \""+user_id+"\"\r\n" + 
				"}]");
	}
	/**   
	 * @Title: queryAuthUserList   
	 * @Description: TODO(查询权限组里面的用户列表)   
	 * @param: @param id
	 * @param: @param relate_name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryAuthUserList(String id,String relate_name) {
		return GetRequestTools.RequestQueryParamsByGet("relate_name", relate_name, queryListByAuthGroupUrl(id));
	}
	
	/**   
	 * @Title: deleteAuthUser   
	 * @Description: TODO(删除权限组里面的用户)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String deleteAuthUser(String id) {
		return HttpResponse.postJsonGetString(deleteAuthUserUrl(id), "{\r\n" + 
				"	\"relate_id_type\": [\""+user_id+"user\"],\r\n" + 
				"	\"access_token\": \""+token+"\"\r\n" + 
				"}");
	}
	
	/**   
	 * @Title: addAuthorityGroup   
	 * @Description: TODO(新增岗位权限组)   
	 * @param: @param name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addAuthorityGroup(String name) {
		return HttpResponse.postJson(add_url, "{\"auth_group_name\":\""+name+"\",\"access_token\":\""+token+"\"}", "access_token", token);
	}
	
	/**   
	 * @Title: editAuthorityGroup   
	 * @Description: TODO(编辑岗位权限组)   
	 * @param: @param id
	 * @param: @param name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String editAuthorityGroup(String id,String name) {
		return HttpResponse.postJson(edit_url(id), "{\"auth_group_name\":\""+name+"\",\"access_token\":\""+token+"\"}", "access_token", token);
	}
	/**   
	 * @Title: queryAuthGroupList   
	 * @Description: TODO(查询权限岗位组)   
	 * @param: @param group_name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryAuthGroupList(String group_name) {
		return PostRequestTools.RequestFormDataByPost(token,"group_name",group_name, query_list_url);
	}
	/**   
	 * @Title: deleteAuthGroup   
	 * @Description: TODO(删除权限岗位组)   
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String deleteAuthGroup(String id) {
		return HttpResponse.postJsonGetString(delete_url, "{\"auth_group_ids\":[\""+id+"\"],\"access_token\":\""+token+"\"}");
	}
	
}
