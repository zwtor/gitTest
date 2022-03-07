package setting.cases;

import cn.kxy.setting.bussiness.AuthUserBusiness;
import cn.kxy.setting.bussiness.RoleManagerBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRoleFunction {
	String name = "DepRole"+CommonData.getStringRandom(5);
	@Test(description = "新增角色",priority = 1)
	public void testAddRole() {
		String res = RoleManagerBusiness.addRole(name);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "保存成功","新增角色,实际返回结果："+res);
	}
	String role_id = "";
	@Test(description = "获取角色id",priority = 2)
	public void testGetRoleId() {
		String res = RoleManagerBusiness.queryRoleList(name);
		role_id = (String)JSONPath.read(res, "$.data[0].id");
	}
	String dep_id = "";
	@Test(description = "获取部门id",priority = 3)
	public void testGetDepartmentList(){
		String res = AuthUserBusiness.GetDepartmentList();
		dep_id = (String)JSONPath.read(res, "$.children[0].id");
	}
	String user_id = "";
	@Test(description = "获取用户id",priority = 4)
	public void testGetUserId() {
		user_id = UserBusiness.getUserId();
	}
	
	@Test(description = "用户追加新的角色--通过部门",priority = 5)
	public void testAppendUserRole() {
		String res = RoleManagerBusiness.appendUserRole(dep_id, "", "",role_id,"");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"用户追加新的角色成功","用户追加新的角色--通过部门"+res);
	}
	
	@Test(description = "验证角色加用户是否成功--通过部门",priority = 6)
	public void testAppendUserRoleIsSuccess() {
		String res = RoleManagerBusiness.getUsersByRoleId(role_id, "");
		String name = (String)JSONPath.read(res, "$.userList.list[0].name");
		Assert.assertTrue(!name.isEmpty(),"验证角色加用户是否成功--通过部门,实际返回结果："+res);
	}
	
	@Test(description = "删除新增的角色",priority = 7)
	public void testDeleteRole() {
		String res = RoleManagerBusiness.deleteRole(role_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除角色成功","删除新增的角色,实际返回结果："+res);
	}
	
	String name_1 = "GroupRole"+CommonData.getStringRandom(5);
	@Test(description = "新增角色",priority = 8)
	public void testAddRole_0() {
		String res = RoleManagerBusiness.addRole(name_1);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "保存成功","新增角色,实际返回结果："+res);
	}
	String role_id_1 = "";
	@Test(description = "获取角色id",priority = 9)
	public void testGetRoleId_1() {
		String res = RoleManagerBusiness.queryRoleList(name_1);
		role_id_1 = (String)JSONPath.read(res, "$.data[0].id");
	}
	
	String group_id = "";
	@Test(description = "获取用户组id",priority = 10)
	public void testGetGroupId() {
		String res = AuthUserBusiness.queryUserGroupList();
		group_id = (String)JSONPath.read(res, "$.groups[0].id");
	}
	
	@Test(description = "用户追加新的角色--通过部门,用户组",priority = 11)
	public void testAppendUserRoleByGroup() {
		String res = RoleManagerBusiness.appendUserRole(dep_id, group_id, "",role_id_1,"");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"用户追加新的角色成功","用户追加新的角色--通过部门,用户组"+res);
	}
	@Test(description = "验证角色加用户是否成功--通过部门,用户组",priority = 12)
	public void testAppendUserRoleIsSuccessByGroup() {
		String res = RoleManagerBusiness.getUsersByRoleId(role_id_1, "");
		String name = (String)JSONPath.read(res, "$.userList.list[0].name");
		Assert.assertTrue(!name.isEmpty(),"验证角色加用户是否成功--通过部门,用户组,实际返回结果："+res);
	}
	
	@Test(description = "删除新增的角色",priority = 13)
	public void testDeleteRoleByGroup() {
		String res = RoleManagerBusiness.deleteRole(role_id_1);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除角色成功","删除新增的角色,实际返回结果："+res);
	}
	
	String name_2 = "PostRole"+CommonData.getStringRandom(5);
	@Test(description = "新增角色",priority = 14)
	public void testAddRole_2() {
		String res = RoleManagerBusiness.addRole(name_2);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "保存成功","新增角色,实际返回结果："+res);
	}
	String role_id_2 = "";
	@Test(description = "获取角色id",priority = 15)
	public void testGetRoleId_2() {
		String res = RoleManagerBusiness.queryRoleList(name_2);
		role_id_2 = (String)JSONPath.read(res, "$.data[0].id");
	}
	
	String post_id = "";
	@Test(description = "获取岗位id",priority = 16)
	public void testGetPostId() {
		String res = AuthUserBusiness.queryUsePostList();
		post_id = (String)JSONPath.read(res, "$.posts[0].id");
	}
	
	@Test(description = "用户追加新的角色--通过部门,用户组,岗位组",priority = 17)
	public void testAppendUserRoleByPost() {
		String res = RoleManagerBusiness.appendUserRole(dep_id, group_id, post_id,role_id_2,"");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"用户追加新的角色成功","用户追加新的角色--通过部门,用户组,岗位组"+res);
	}
	@Test(description = "验证角色加用户是否成功--通过部门,用户组",priority = 18)
	public void testAppendUserRoleIsSuccessByPost() {
		String res = RoleManagerBusiness.getUsersByRoleId(role_id_2, "");
		String name = (String)JSONPath.read(res, "$.userList.list[0].name");
		Assert.assertTrue(!name.isEmpty(),"验证角色加用户是否成功--通过部门,用户组,岗位组,实际返回结果："+res);
	}
	
	@Test(description = "删除新增的角色---通过部门,用户组,岗位组",priority = 19)
	public void testDeleteRoleByPost() {
		String res = RoleManagerBusiness.deleteRole(role_id_2);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除角色成功","删除新增的角色---通过部门,用户组,岗位组,实际返回结果："+res);
	}
	
	String name_3 = "PostGroupUserIdRole"+CommonData.getStringRandom(5);
	@Test(description = "新增角色",priority = 20)
	public void testAddRole_3() {
		String res = RoleManagerBusiness.addRole(name_3);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "保存成功","新增角色,实际返回结果："+res);
	}
	String role_id_3 = "";
	@Test(description = "获取角色id",priority = 21)
	public void testGetRoleId_3() {
		String res = RoleManagerBusiness.queryRoleList(name_3);
		role_id_3 = (String)JSONPath.read(res, "$.data[0].id");
	}
	
	@Test(description = "用户追加新的角色--通过部门,用户组,岗位组,用户id",priority = 22)
	public void testAppendUserRoleByPostGroupUserId() {
		String res = RoleManagerBusiness.appendUserRole(dep_id, group_id, post_id,role_id_3,user_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"用户追加新的角色成功","用户追加新的角色--通过部门,用户组,岗位组，用户id"+res);
	}
	@Test(description = "验证角色加用户是否成功--通过部门,用户组",priority = 23)
	public void testAppendUserRoleIsSuccessByPostGroupUserId() {
		String res = RoleManagerBusiness.getUsersByRoleId(role_id_3, "");
		String name = (String)JSONPath.read(res, "$.userList.list[0].name");
		Assert.assertTrue(!name.isEmpty(),"验证角色加用户是否成功--通过部门,用户组,岗位组，用户id,实际返回结果："+res);
	}
	
	@Test(description = "删除新增的角色---通过部门,用户组,岗位组，用户id",priority = 24)
	public void testDeleteRoleByPostGroupUserId() {
		String res = RoleManagerBusiness.deleteRole(role_id_3);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "删除角色成功","删除新增的角色---通过部门,用户组,岗位组，用户id,实际返回结果："+res);
	}
}
