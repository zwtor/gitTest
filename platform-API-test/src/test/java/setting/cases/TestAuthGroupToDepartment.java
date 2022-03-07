package setting.cases;

import com.lazy.common.utils.CommonData;
import org.testng.annotations.Test;

public class TestAuthGroupToDepartment {
	String name = "YourAuth"+CommonData.getStringRandom(5);
	String id = "";
	@Test(description = "新增数据权限组",priority = 1)
	public void testAddAuthGroup() {
//		String res = AuthorityGroupBusiness.addAuthorityGroup(name);
//		String message = (String)JSONPath.read(res, "$.message");
//		Assert.assertEquals(message, "success","新增权限组，实际返回结果："+res);
	}
	
	@Test(description = "查询数据权限组",priority = 2)
	public void testQueryList() {
//		String res = AuthorityGroupBusiness.queryAuthGroupList(name);
//		id = (String)JSONPath.read(res, "$.data.list[0].auth_group_id");
//		String auth_group_name = (String)JSONPath.read(res, "$.data.list[0].auth_group_name");
//		Assert.assertEquals(auth_group_name, name,"查询数据权限组，实际返回结果："+res);
	}
	String department_id = "";
	String department_name = "";
	@Test(description = "在岗位组下新增部门时，查看部门列表",priority = 3)
	public void testGetDepartmentList() {
//		String res = AuthUserBusiness.GetDepartmentList();
//		department_id = (String)JSONPath.read(res, "$.children[0].id");
//		department_name = (String)JSONPath.read(res, "$.children[0].department_name");
		
//		String department_name = (String)JSONPath.read(res, "$.department_name");
//		Assert.assertNotNull(department_name, "在岗位组下新增部门时，查看部门列表，实际返回结果："+res);
	}
	@Test(description = "在岗位组下新增部门时，查看岗位列表",priority = 4)
	public void testQueryUsePostList() {
//		String res = AuthUserBusiness.queryUsePostList();
//		String post_name = (String)JSONPath.read(res, "$.posts[0].children[0].post_name");
//		Assert.assertNotNull(post_name, "在岗位组下新增部门时，查看岗位列表，实际返回结果："+res);
	}
	@Test(description = "在岗位组下新增部门时，查看用户组列表",priority = 5)
	public void testQueryUserGroupList() {
//		String res = AuthUserBusiness.queryUserGroupList();
//		String group_name = (String)JSONPath.read(res, "$.groups[0].group_name");
//		Assert.assertNotNull(group_name, "在岗位组下新增部门时，查看用户组列表，实际返回结果："+res);
	}
	@Test(description = "在数据权限组下新增岗位组",priority = 6)
	public void testAddAuthGroupByDepartment() {
//		String res = AuthorityGroupBusiness.addAuthGroup(id, department_id, department_name, "department");
//		String message = (String)JSONPath.read(res, "$.message");
//		Assert.assertEquals(message, "success","在数据权限组下新增岗位组，实际返回结果："+res);
	}
	@Test(description = "查询在数据权限组下新增岗位列表",priority = 7)
	public void testQueryDepartment() {
//		String res = AuthorityGroupBusiness.queryDepartment(id, "");
//		String relate_name = (String)JSONPath.read(res, "$.data.list[0].relate_name");
//		Assert.assertNotNull(relate_name,"查询在数据权限组下新增岗位列表，实际返回结果："+res);
	}
	@Test(description = "查看岗位组列表",priority = 8)
	public void testQueryDepartUser() {
//		String res = AuthUserBusiness.queryDepartUser(department_id);
//		Assert.assertTrue(res.contains("total"), "查看岗位组列表,实际返回结果："+res);
	}
	@Test(description = "删除数据权限组下新增的岗位",priority = 9)
	public void testDelete() {
//		String res = AuthorityGroupBusiness.deleteAuthhDepartment(id, department_id);
//		String message = (String)JSONPath.read(res, "$.message");
//		Assert.assertEquals(message, "success","删除数据权限组下新增的岗位，实际返回结果："+res);
	}
	@Test (description = "删除数据权限组",priority =10)
	public void testDeleteAuthGroup() {
//		String res = AuthorityGroupBusiness.deleteAuthGroup(id);
//		String message = (String)JSONPath.read(res, "$.message");
//		Assert.assertEquals(message, "success","删除数据权限组，实际返回结果："+res);
	}
	@Test(description = "在数据权限组下新增岗位组,id为空时",priority = 11)
	public void testAddAuthGroupByDepartmentIdIsEmpty() {
//		String res = AuthorityGroupBusiness.addAuthGroup("", "", "", "department");
//		Assert.assertTrue(res.contains("Not Found"), "在数据权限组下新增岗位组,id为空时,实际返回结果："+res);
	}
	
}
