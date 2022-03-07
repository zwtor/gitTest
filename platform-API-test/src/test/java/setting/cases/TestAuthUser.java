package setting.cases;

import com.lazy.common.utils.CommonData;
import org.testng.annotations.Test;

public class TestAuthUser {
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
	@Test(description = "在数据权限组新增用户",priority = 3)
	public void testAddAuthUser() {
//		String res = AuthorityGroupBusiness.addAuthUser(id);
//		Boolean data = (Boolean)JSONPath.read(res, "$.data");
//		Assert.assertTrue(data,"在数据权限组新增用户"+res);
	}
	
	@Test(description = "在数据权限组新增用户后，在列表页查看",priority = 4)
	public void testQueryAuthUserList() {
//		String res = AuthorityGroupBusiness.queryAuthUserList(id, "");
//		String name = (String)JSONPath.read(res, "$.data.list[0].relate_name");
//		Assert.assertNotNull(name,"在数据权限组新增用户后，在列表页查看"+res);
	}
	@Test(description = "删除数据权限组新增用户",priority = 5)
	public void testDeleteAuthUser() {
//		String res = AuthorityGroupBusiness.deleteAuthUser(id);
//		Boolean data = (Boolean)JSONPath.read(res, "$.data");
//		Assert.assertTrue(data,"删除数据权限组新增用户"+res);
	}
	@Test (description = "删除数据权限组",priority = 6)
	public void testDeleteAuthGroup() {
//		String res = AuthorityGroupBusiness.deleteAuthGroup(id);
//		String message = (String)JSONPath.read(res, "$.message");
//		Assert.assertEquals(message, "success","删除数据权限组，实际返回结果："+res);
	}
	
	
}
