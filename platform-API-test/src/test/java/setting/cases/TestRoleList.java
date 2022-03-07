package setting.cases;

import cn.kxy.setting.bussiness.RoleManagerBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRoleList {

	String id_0 = "";
	String id_1 = "";
	String id_2 = "";
	String user_id = UserBusiness.getUserId();
	@Test(description = "查询角色列表",priority = 1)
	public void testQueryRoleList() {
		String res = RoleManagerBusiness.queryRoleList("");
		String msg = (String)JSONPath.read(res, "$.msg");
		String roleName_0 = (String)JSONPath.read(res, "$.data[0].roleName");
		id_0  = (String)JSONPath.read(res, "$.data[0].id");
		id_1  = (String)JSONPath.read(res, "$.data[1].id");
		id_2  = (String)JSONPath.read(res, "$.data[2].id");
		int num = (int)JSONPath.read(res, "$.data[0].personNum");
		Assert.assertTrue(num>0,"查询角色列表,主管理员人数显示实际返回结果："+res);
		Assert.assertEquals(roleName_0, "主管理员","查询角色列表,主管理员的roleName实际返回结果："+res);
		Assert.assertEquals(msg, "查询角色列表成功","查询角色列表,msg实际返回结果："+res);
	}
	
	@Test(description = "根据名称查询角色列表",priority = 2)
	public void testQueryRoleListByName() {
		String res = RoleManagerBusiness.queryRoleList("主管理员");
		String roleName_1 = (String)JSONPath.read(res, "$.data[1].roleName");
		String roleName_0 = (String)JSONPath.read(res, "$.data[0].roleName");
		Assert.assertEquals(roleName_0, "主管理员","根据名称查询角色列表,主管理员的roleName实际返回结果："+res);
		Assert.assertNull(roleName_1,"根据名称查询角色列表，实际返回结果" + res);
	}
	
	String user_name = "";
	@Test(description = "根据主管理员查询角色下的员工",priority = 3)
	public void testGetUsersByRoleId_0() {
		String res = RoleManagerBusiness.getUsersByRoleId(id_0, "");
		String roleName = (String)JSONPath.read(res, "$.sysRole.roleName");
		user_name = (String)JSONPath.read(res, "$.userList.list[0].name");
		Assert.assertEquals(roleName, "主管理员","根据主管理员查询角色下的员工,实际返回结果："+res);
	}
	
	@Test(description = "根据普通员工查询角色下的员工",priority = 4)
	public void testGetUsersByRoleId_1() {
		String res = RoleManagerBusiness.getUsersByRoleId(id_1, "");
		String roleName = (String)JSONPath.read(res, "$.sysRole.roleName");
	}
	
	@Test(description = "根据部门负责人查询角色下的员工",priority = 5)
	public void testGetUsersByRoleId_2() {
		String res = RoleManagerBusiness.getUsersByRoleId(id_2, "");
		String roleName = (String)JSONPath.read(res, "$.sysRole.roleName");
	}
	
	@Test(description = "根据名称查询角色下的员工" , priority = 6)
	public void testGetUsersByRoleName() {
		String res = RoleManagerBusiness.getUsersByRoleId(id_0, user_name);
		String name = (String)JSONPath.read(res, "$.userList.list[0].name");
		String department = (String)JSONPath.read(res, "$.userList.list[0].department");
		
		Assert.assertNotNull(department,"根据名称查询角色下的员工,部门不能为空，实际返回结果："+res);
		Assert.assertEquals(user_name, name,"根据名称查询角色下的员工,实际返回结果："+res);
	}
	
	String name = "Role"+CommonData.getStringRandom(5);
	@Test(description = "新增角色",priority = 7)
	public void testAddRole() {
		String res = RoleManagerBusiness.addRole(name);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "保存成功","新增角色,实际返回结果："+res);
	}
	String role_id = "";
	@Test(description = "获取角色id",priority = 8)
	public void testGetRoleId() {
		String res = RoleManagerBusiness.queryRoleList(name);
		role_id = (String)JSONPath.read(res, "$.data[0].id");
	}
	String edit_name = "EditRole"+CommonData.getStringRandom(5);
	@Test(description = "编辑角色",priority = 9)
	public void testEditRole() {
		String res = RoleManagerBusiness.editRole(role_id,edit_name );
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "保存成功","编辑角色,实际返回结果："+res);
	}
	@Test(description = "用户追加新的角色",priority = 10)
	public void testAppendUserRole() {
		String res = RoleManagerBusiness.appendUserRole("", "", "",role_id,user_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"用户追加新的角色成功",""+res);
	}
	
	@Test(description = "验证角色加用户是否成功",priority = 11)
	public void testAppendUserRoleIsSuccess() {
		String res = RoleManagerBusiness.getUsersByRoleId(role_id, UserBusiness.getUsername());
		String name = (String)JSONPath.read(res, "$.userList.list[0].name");
		Assert.assertEquals(UserBusiness.getUsername(), name,"验证角色加用户是否成功,实际返回结果："+res);
	}
	
	@Test(description = "删除角色下的用户",priority = 12)
	public void testDeleteUserRole() {
		String res = RoleManagerBusiness.deleteUserRole(role_id, user_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除用户角色成功","删除角色下的用户,实际返回结果："+res);
	}
	@Test(description = "删除新增的角色",priority = 13)
	public void testDeleteRole() {
		String res = RoleManagerBusiness.deleteRole(role_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除角色成功","删除新增的角色,实际返回结果："+res);
	}
}
