package cn.kxy.setting.bussiness;

import cn.kxy.base.business.EnterpriseData;
import cn.kxy.base.business.EnterpriseDataUrl;
import cn.kxy.base.business.TokenData;
import com.lazy.httpclient.utils.HttpRequest;

public class RoleManagerBusiness {
	public static String token = TokenData.getMangerToken();
	public static String enterprise_id = EnterpriseData.getEnterpriseId();
	public static String enterprise_url= EnterpriseDataUrl.getEnterpriseUrl();
	public static String user_id = UserBusiness.getUserId();
	public static String user_name = UserBusiness.getUsername();
	
	public static String getUsersByRoleId_url = enterprise_url + "system/role/getUsersByRoleId";
	
	public static String role_list_url = enterprise_url + "system/role/list";
	
	public static String add_role_url = enterprise_url + "system/role/add";
	
	public static String  edit_role_url= enterprise_url + "system/role/edit";
	
	public static String delete_url = enterprise_url + "system/role/delete";
	
	public static String appendUserRole_url = enterprise_url + "system/role/appendUserRole";
	
	public static String delete_role_user_url = enterprise_url + "system/role/deleteUserRole";
	
	/**   
	 * @Title: deleteUserRole   
	 * @Description: TODO 删除角色下的用户
	 * @param: @param roleId
	 * @param: @param userIds
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String deleteUserRole (String roleId,String userIds) {
		return HttpRequest.post(delete_role_user_url).query("access_token", token).form("roleId", roleId)
				.form("userIds",userIds).send().body();
	}
	
	/**   
	 * @Title: appendUserRole   
	 * @Description: TODO  往角色添加用户
	 * @param: @param departmentIds
	 * @param: @param groupIds
	 * @param: @param postIds
	 * @param: @param roleId
	 * @param: @param userIds
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String appendUserRole (String departmentIds,String groupIds,String postIds,String roleId,String userIds) {
		return HttpRequest.post(appendUserRole_url).query("access_token", token).form("departmentIds", departmentIds)
				.form("groupIds", groupIds).form("postIds", postIds).form("roleId", roleId).form("userIds", userIds)
				.send().body();
	}
	
	/**   
	 * @Title: deleteRole   
	 * @Description: TODO   删除角色
	 * @param: @param id
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String deleteRole (String id) {
		return HttpRequest.post(delete_url).query("access_token",token).form("id",id).send().body();
	}
	
	/**   
	 * @Title: editRole   
	 * @Description: TODO  编辑角色
	 * @param: @param id
	 * @param: @param roleName
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String editRole(String id,String roleName) {
		return HttpRequest.post(edit_role_url).query("access_token", token).form("id", id).form("roleName", roleName).send().body();
	}
	
	/**   
	 * @Title: addRole   
	 * @Description: TODO  添加角色
	 * @param: @param roleName
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String addRole (String roleName) {
		return HttpRequest.post(add_role_url).query("access_token", token).form("roleName",roleName).send().body();
	}
	
	/**   
	 * @Title: queryRoleList   
	 * @Description: TODO 查询角色列表
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String queryRoleList(String keyword) {
		return HttpRequest.get(role_list_url).query("access_token", token).query("keyword", keyword).send().body();
	}
	
	/**   
	 * @Title: getUsersByRoleId   
	 * @Description: TODO  根据不同角色id查询人员
	 * @param: @param roleId
	 * @param: @param keyword
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */  
	public static String getUsersByRoleId(String roleId,String keyword) {
		return HttpRequest.get(getUsersByRoleId_url).query("roleId", roleId).query("pageNumber", "1").query("pageSize", "20").query("keyword",keyword)
				.query("access_token", token).send().body();
	}
	
}
