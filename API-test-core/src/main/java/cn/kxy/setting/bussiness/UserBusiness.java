package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.alibaba.fastjson.JSONPath;
import com.lazy.assured.utils.GetRequestTools;
import com.lazy.httpclient.utils.HttpRequest;

public class UserBusiness {
	public static String token = TokenData.getMangerToken();
	
	public static String enterprise_url= EnterpriseDataUrl.getEnterpriseUrl();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String queryuserUrl =enterprise_url  + "profile/get";
	public static String getDeparmentsUrl = enterprise_url + "system/department/getDeparmentsById";
	
	public static String departments_url = enterprise_url + "v2/"+enterprise_id+"/departments";
	
	public static String query_posts_url =enterprise_url + "v2/"+enterprise_id+"/posts";
	
	public static String query_groups_url = enterprise_url + "v2/"+enterprise_id+"/groups";
	
	public static String dep_tree_url = enterprise_url + "v2/"+enterprise_id+"/departments/tree";
	
	public static String queryUserUrl = enterprise_url +"v2/"+enterprise_id+"/users";
	
	public static String addUserGroupUrl = enterprise_url + "sys/userGroup/addUserGroup";
	
	public static String updateUserGroupUrl = enterprise_url + "sys/userGroup/updateUserGroup";
	
	public static String userAddGroupUrl = enterprise_url + "sys/userGroup/batchAddUsers";
	
	public static String batchDeleteUsersUrl = enterprise_url + "sys/userGroup/batchDeleteUsers";
	
	public static String deleteUserGroupUrl =enterprise_url + "sys/userGroup/deleteUserGroup";
	
	
	public static String queryGroupUserUrl(String group_id) {
		return enterprise_url +  "v2/"+enterprise_id+"/groups/"+group_id+"/users";
	}
	
	public static String queryUserByDepUrl (String dep_id ) {
		return  enterprise_url + "v2/"+enterprise_id+"/departments/"+dep_id+"/users";
	}
	
	public static String queryDepUserUrl (String dep_id) {
		return enterprise_url + "v2/"+enterprise_id+"/departments/"+dep_id+"/users";
	}
	
	public static String setUserInfoUrl =enterprise_url + "v2/"+enterprise_id+"/user/infor/set";
	
	public static String getPrincipalUserByPostUrl = enterprise_url + "admin/post/getPrincipalUserByPost";
	
	public static String addUsersToPostUrl =enterprise_url + "admin/post/addUsersToPost";
	
	public static String queryPostUserUrl (String post_id) {
		return enterprise_url + "v2/"+enterprise_id+"/posts/"+post_id+"/users";
	}	
	
	
	public static String delUsersFromPostUrl = enterprise_url + "admin/post/delUsersFromPost";
	
	public static String user_url = enterprise_url + "v2/"+enterprise_id +"/departments/1/users";
	
	public static String getUserIdByKeyWord(String keyword) {
		 String res = HttpRequest.get(user_url).query("access_token", token).query("recursion","true").query("filter_auth","true")
				.query("user_type","active").query("type", "user_name").query("page_number","1").query("page_size","20").query("keyword",keyword).
				query("sync_organization","ding").send().body();
		 String user_id = (String)JSONPath.read(res, "$.list[0].id");
		 return user_id ;
	}
	
	public static String delUsersFromPost(String post_id) {
		return HttpRequest.post(delUsersFromPostUrl).query("access_token", token).form("id", post_id).form("userIds",UserBusiness.getUserId())
				.form("postId", post_id).send().body();
	}
	
	public static String queryPostUser(String post_id,String user_name,String sync_organization) {
		return HttpRequest.get(queryPostUserUrl(post_id)).query("access_token", token).query("user_name",user_name ).query("page_number", "1")
				.query("page_size","20").query("filter_auth","true").query("sync_organization",sync_organization).send().body();
	}
	
	public static String addUsersToPost(String post_id) {
		return HttpRequest.post(addUsersToPostUrl).query("access_token", token).form("id", post_id).form("userIds",UserBusiness.getUserId())
				.form("departmentIds", "").form("postId", post_id).send().body();
	}
	
	public static String getPrincipalUserByPost(String postId) {
		return HttpRequest.get(getPrincipalUserByPostUrl).query("access_token", token).query("postId",postId ).query("id", postId)
		.send().body();
	}
	
	public static String setUserInfoByTwo (String user_id,String extend_value_1,String extend_value_2) {
		return HttpRequest.post(setUserInfoUrl).query("access_token", token).data("{\r\n" + 
				"  \"user_id\":\""+user_id+"\",\r\n" + 
				"  \"extend_list\":[\r\n" + 
				"    {\r\n" + 
				"      \"column_key\":\"column1\",\r\n" + 
				"      \"extend_value\":\""+extend_value_1+"\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"column_key\":\"column2\",\r\n" + 
				"      \"extend_value\":\""+extend_value_2+"\"\r\n" + 
				"    }],\r\n" + 
				"  \"face_url\":\"\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}").send().body();
	}
	
	public static String setUserInfoByThree (String user_id,String extend_value_1,String extend_value_2,String extend_value_3) {
		return HttpRequest.post(setUserInfoUrl).query("access_token", token).data("{\r\n" + 
				"  \"user_id\":\"0845300515775174\",\r\n" + 
				"  \"extend_list\":[\r\n" + 
				"    {\r\n" + 
				"      \"column_key\":\"column1\",\r\n" + 
				"      \"extend_value\":\""+extend_value_1+"\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"column_key\":\"column3\",\r\n" + 
				"      \"extend_value\":\""+extend_value_3+"\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"column_key\":\"column2\",\r\n" + 
				"      \"extend_value\":\""+extend_value_2+"\"\r\n" + 
				"    }],\r\n" + 
				"  \"face_url\":\"\",\r\n" + 
				"  \"access_token\":\""+token+"\"\r\n" + 
				"}\r\n" + 
				"").send().body();
	}
	
	public static String queryDepUser(String dep_id,String user_name,String sync_organization) {
		return HttpRequest.get(queryDepUserUrl(dep_id)).query("recursion", "true").query("access_token", token).query("user_name",user_name ).query("filter_auth", "true")
		.query("page_number", "1").query("page_size", "20").query("sync_organization",sync_organization).send().body();
	}
	
	public static String deleteUserGroup(String id) {
		return HttpRequest.post(deleteUserGroupUrl).query("access_token", token).form("groupId", id).send().body();
	}
	
	public static String deleteUserToGroup(String id) {
		return HttpRequest.post(batchDeleteUsersUrl).query("access_token", token).form("id", id).form("userIds",UserBusiness.getUserId())
				.form("groupId", id).send().body();
	}
	
	public static String AddUserToGroup(String id) {
		return HttpRequest.post(userAddGroupUrl).query("access_token", token).form("id", id).form("userIds",UserBusiness.getUserId())
				.form("groupId", id).send().body();
	}
	
	/**   
	 * @Title: queryUserByDep   
	 * @Description: TODO  查看用户组的列表
	 * @param: @param dep_id
	 * @param: @param user_name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryUserByGroup(String dep_id,String user_name) {
		return HttpRequest.get(queryGroupUserUrl(dep_id)).query("access_token", token).query("user_name",user_name ).query("filter_auth", "true")
				.query("page_number", "1").query("page_size", "20").send().body();
	}
	
	/**   
	 * @Title: queryUserByDep   
	 * @Description: TODO  查看部门
	 * @param: @param dep_id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryUserByDep(String dep_id) {
		return HttpRequest.get(queryUserByDepUrl(dep_id)).query("access_token", token).query("recursion","false" ).query("filter_auth", "true")
				.query("user_type", "active").send().body();
	}
	
	public static String updateUserGroup(String id,String name) {
		return HttpRequest.post(updateUserGroupUrl).query("access_token", token).form("id", id).form("name",name).send().body();
	}
	
	/**   
	 * @Title: addUserGroup   
	 * @Description: TODO  新增用户组
	 * @param: @param name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addUserGroup(String name) {
		return HttpRequest.post(addUserGroupUrl).query("access_token", token).form("id", "").form("name",name).send().body();
	}
	
	/**   
	 * @Title: queryUserByName   
	 * @Description: TODO  通过用户名查询用户
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryUserByName() {
		return HttpRequest.get(queryUserUrl).query("access_token",token).query("user_name", UserBusiness.getUsername()).query("filter_auth", "true")
				.query("user_type", "active").send().body();
	}
	
	/**   
	 * @Title: queryDepUser   
	 * @Description: TODO 选人控件根据部门查看员工
	 * @param: @param query_user_count
	 * @param: @param parent_id
	 * @param: @param filter_auth
	 * @param: @param user_type
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryDepUser (String query_user_count,String parent_id,String filter_auth,String user_type) {
		return HttpRequest.get(dep_tree_url).query("recursion", "false").query("query_user_count",query_user_count)
				.query("parent_id",parent_id).query("filter_auth", filter_auth).query("user_type",user_type).query("access_token", token)
				.send().body();
	}
	
	/**   
	 * 
	 * @Title: queryGroups   
	 * @Description: TODO()   
	 * @param: @param group_name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryGroups(String group_name) {
		
//		return GetRequestTools.RequestParamsByGet("access_token", token, "group_name", group_name,"filter_auth","true", query_groups_url);
		
		return HttpRequest.get(query_groups_url).query("access_token", token).
				query("group_name",group_name).query("filter_auth", "true")
				.send().body();
	}
	
	/**   
	 * @Title: queryPosts   
	 * @Description: TODO(通过关键字查询岗位)   
	 * @param: @param department_name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryPosts(String post_name) {
		return HttpRequest.get(query_posts_url).query("access_token", token).query("user_type","active").
				query("post_name",post_name).query("filter_auth", "true")
				.send().body();
	}
	public static String queryPostsManage(String sync_organization) {
		return HttpRequest.get(query_posts_url).query("access_token", token).query("sync_organization",sync_organization).
				query("filter_auth", "true").send().body();
	}
	/**   
	 * @Title: queryDepartment   
	 * @Description: TODO(通过关键字查询部门)   
	 * @param: @param department_name
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryDepartment(String department_name) {
		return HttpRequest.get(departments_url).query("access_token", token).
				query("department_name",department_name).query("filter_auth", "true")
				.send().body();
	}
	
	/**   
	 * @Title: getDeparments   
	 * @Description: TODO() getDeparmentsById接口测试  
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String getDeparments() {
		return GetRequestTools.RequestQueryParamsByGet("access_token", token,
				"queryType", "1","pageSize","15", "postType","0",getDeparmentsUrl);
	}
	

	public static String getUserId() {
		String res = GetRequestTools.RequestQueryParamsByGet("access_token", token, queryuserUrl);
		
		String userId = (String) JSONPath.read(res, "$.data.id");
		return userId;
	}
	
	public static String getUsername() {
		String res = GetRequestTools.RequestQueryParamsByGet("access_token", token, queryuserUrl);
		String username = (String) JSONPath.read(res, "$.data.name");
		return username;
	}
}
