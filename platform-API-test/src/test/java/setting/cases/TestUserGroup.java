package setting.cases;

import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestUserGroup {

	String name = "UserGroup" +CommonData.getStringRandom(5);
	String group_id = "";
	@Test(description = "新增用户组",priority = 1)
	public void testAddUserGroup() {
		String res = UserBusiness.addUserGroup(name);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"ok","新增用户组，实际返回结果："+res);
	}
	
	@Test(description = "通过关键字查看用户组",priority = 2)
	public void testQueryGroups() {
		String res = UserBusiness.queryGroups(name);
		String title = (String)JSONPath.read(res, "$.groups[0].group_name");
		group_id = (String)JSONPath.read(res, "$.groups[0].id");
		Assert.assertEquals(title, name,"查看用户组"+res);
	}
	@Test(description = "用户组重命名",priority = 3)
	public void testUpdateUserGroup() {
		String res = UserBusiness.updateUserGroup(group_id, name);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"ok","用户组重命名，实际返回结果："+res);
	}
	@Test(description = "往用户组加人",priority= 4)
	public void testAddUserToGroup() {
		String res = UserBusiness.AddUserToGroup(group_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"新增成功","往用户组加人，实际返回结果："+res);
	}
	
	@Test(description = "往用户组加人后,查看用户组的人数",priority = 5)
	public void testQueryGroups11() {
		String res = UserBusiness.queryGroups(name);
		int user_count = (int)JSONPath.read(res, "$.groups[0].user_count");
		Assert.assertEquals(user_count, 1,"往用户组加人后,查看用户组的人数"+res);
		
	}
	
	@Test(description = "查看用户组的全部列表",priority = 6)
	public void testQueryUserByGroupAll() {
		String res = UserBusiness.queryUserByGroup(group_id, "");
		String user_name = (String)JSONPath.read(res, "$.list[0].user_name");
		String department_names = (String)JSONPath.read(res, "$.list[0].department_names[0]");
		String active = (String)JSONPath.read(res, "$.list[0].active");
		Long create_time = (Long)JSONPath.read(res, "$.list[0].create_time");
		Assert.assertNotNull(user_name,"查看用户组的全部列表,用户名进行验证"+res);
		Assert.assertNotNull(department_names,"查看用户组的全部列表,部门进行验证"+res);
		Assert.assertNotNull(active,"查看用户组的全部列表,激活验证进行验证"+res);
		Assert.assertNotNull(create_time,"查看用户组的全部列表,加入时间进行验证"+res);
	}
	@Test(description = "通过关键字查看用户组",priority = 7)
	public void testQueryUserByGroupByName() {
		String res = UserBusiness.queryUserByGroup(group_id, UserBusiness.getUsername());
		String user_name = (String)JSONPath.read(res, "$.list[0].user_name");
		Assert.assertNotNull(user_name,"通过关键字查看用户组,用户名进行验证"+res);
	}
	
	@Test(description = "用户组下存在人时，不允许删人",priority = 8)
	public void testDeleteUserGroupExist() {
		String res = UserBusiness.deleteUserGroup(group_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"用户组下有用户,不允许删除,请先删除用户组下用户","用户组下存在人时，不允许删人，实际返回结果："+res);
	}
	
	@Test(description = "删除用户",priority = 9)
	public void testDeleteUserToGroup() {
		String res = UserBusiness.deleteUserToGroup(group_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"ok","新增用户组，实际返回结果："+res);
	}
	
	@Test(description = "删除用户组",priority = 10)
	public void testDeleteUserGroup() {
		String res = UserBusiness.deleteUserGroup(group_id);
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg,"ok","删除用户组，实际返回结果："+res);
	}
}
