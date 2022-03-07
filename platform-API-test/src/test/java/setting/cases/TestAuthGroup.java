package setting.cases;

import com.lazy.common.utils.CommonData;
import org.testng.annotations.Test;

public class TestAuthGroup {

	String name = "MyAuth"+CommonData.getStringRandom(5);
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
	@Test (description = "编辑数据权限组",priority = 3)
	public void testEditAuthGroup() {
//		String res = AuthorityGroupBusiness.editAuthorityGroup(id, "YourGroup"+CommonData.getStringRandom(3));
//		String message = (String)JSONPath.read(res, "$.message");
//		Assert.assertEquals(message, "success","编辑数据权限组，实际返回结果："+res);
	}
	@Test (description = "删除数据权限组",priority = 4)
	public void testDeleteAuthGroup() {
//		String res = AuthorityGroupBusiness.deleteAuthGroup(id);
//		String message = (String)JSONPath.read(res, "$.message");
//		Assert.assertEquals(message, "success","删除数据权限组，实际返回结果："+res);
	}
	
	
	@Test(description = "新增数据权限组name为Null",priority = 5)
	public void testAddNameIsNull() {
//		String res = AuthorityGroupBusiness.addAuthorityGroup(null);
//		String message = (String)JSONPath.read(res, "$.message");
//		Assert.assertEquals(message, "success","新增权限组，实际返回结果："+res);
	}
	String null_id = "";
	@Test(description = "查询数据权限组",priority = 6)
	public void testQueryListIsNull() {
		/*String res = AuthorityGroupBusiness.queryAuthGroupList("null");
		null_id = (String)JSONPath.read(res, "$.data.list[0].auth_group_id");
		String auth_group_name = (String)JSONPath.read(res, "$.data.list[0].auth_group_name");
		Assert.assertEquals(auth_group_name, "null","查询数据权限组，实际返回结果："+res);*/
	}
	@Test (description = "删除数据权限组,名称为null时",priority = 7)
	public void testDeleteNullGroup() {
//		String res = AuthorityGroupBusiness.deleteAuthGroup(null_id);
//		String message = (String)JSONPath.read(res, "$.message");
//		Assert.assertEquals(message, "success","删除数据权限组，实际返回结果："+res);
	}
	
	@Test(description = "编辑数据权限组时，id为空",priority = 8)
	public void testEditIsEmpty() {
//		String res = AuthorityGroupBusiness.editAuthorityGroup("", "");
//		int status = (int)JSONPath.read(res, "$.status");
//		Assert.assertEquals(status, 404,"编辑数据权限组时，id为空，实际返回结果："+res);
	}
	
	@Test(description = "删除数据权限组，id不存在时",priority = 9)
	public void testDeleteIdIsNotExist() {
//		String res = AuthorityGroupBusiness.deleteAuthGroup("223");
//		Boolean data = (Boolean)JSONPath.read(res, "$.data");
//		Assert.assertFalse(data,"编辑数据权限组时，id为空，实际返回结果："+res);
	}
	@Test(description = "删除数据权限组，id为空时",priority = 10)
	public void testDeleteIdIsEmpty() {
//		String res = AuthorityGroupBusiness.deleteAuthGroup("");
//		Boolean data = (Boolean)JSONPath.read(res, "$.data");
//		Assert.assertFalse(data,"编辑数据权限组时，id为空，实际返回结果："+res);
	}
	@Test(description = "删除数据权限组，id为空时",priority = 11)
	public void testDeleteIdIsNull() {
//		String res = AuthorityGroupBusiness.deleteAuthGroup(null);
//		Boolean data = (Boolean)JSONPath.read(res, "$.data");
//		Assert.assertFalse(data,"删除数据权限组时，id为空，实际返回结果："+res);
	}
}
