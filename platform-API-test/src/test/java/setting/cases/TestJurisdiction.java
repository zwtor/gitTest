package setting.cases;

import cn.kxy.setting.bussiness.AuthUserBusiness;
import cn.kxy.setting.bussiness.JurisdictionBusiness;
import cn.kxy.setting.bussiness.RoleManagerBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("管辖范围设置页面测试")
public class TestJurisdiction {

	String dep_id = "";
	String source_user_id = "";
	String post_id = "";
	String group_id = "";
	String id_01 = "";
	@Test(description = "获取用户id",priority = 1)
	public void testGetUserId() {
		String res = RoleManagerBusiness.queryRoleList("");
		id_01  = (String)JSONPath.read(res, "$.data[0].id");
		String role_res = RoleManagerBusiness.getUsersByRoleId(id_01, "");
		source_user_id =(String)JSONPath.read(role_res, "$.userList.list[0].id");
	}
	@Test(description = "获取部门id",priority = 2)
	public void testGetDepartmentList(){
		String res = AuthUserBusiness.GetDepartmentList();
		dep_id = (String)JSONPath.read(res, "$.children[0].id");
	}
	
	@Test(description = "获取用户组id",priority = 3)
	public void testGetGroupId() {
		String res = AuthUserBusiness.queryUserGroupList();
		group_id = (String)JSONPath.read(res, "$.groups[0].id");
	}
	
	@Test(description = "获取岗位id",priority = 4)
	public void testGetPostId() {
		String res = AuthUserBusiness.queryUsePostList();
		post_id = (String)JSONPath.read(res, "$.posts[0].id");
	}
	@Test(description = "给用户设置管辖范围--仅自己,项目查看范围--我创建及负责的项目/任务",priority = 5)
	public void testSaveAuthorityRangesOnlyMe() {
		String res = JurisdictionBusiness.saveAuthorityRanges(source_user_id, "only_me", "", "",
				"", "", "self", "false");
		Boolean msg = (Boolean)JSONPath.read(res, "$.success");
		Assert.assertTrue(msg,"给用户设置管辖范围--仅自己,项目查看范围--我创建及负责的项目/任务"+res);
	}
	@Test(description = "给用户设置管辖范围--仅自己,项目查看范围--我管辖范围内员工（包括本人）创建及负责的项目/任务",priority = 6)
	public void testSaveAuthorityRangesOnlyMeAuthority() {
		String res = JurisdictionBusiness.saveAuthorityRanges(source_user_id, "only_me", "", "",
				"", "", "authority", "false");
		Boolean msg = (Boolean)JSONPath.read(res, "$.success");
		Assert.assertTrue(msg,"给用户设置管辖范围--全公司,项目查看范围--我管辖范围内员工（包括本人）创建及负责的项目/任务"+res);
	}
	@Test(description = "给用户设置管辖范围--全公司,项目查看范围--我创建及负责的项目/任务",priority = 7)
	public void testSaveAuthorityRangesAll() {
		String res = JurisdictionBusiness.saveAuthorityRanges(source_user_id, "all", "", "",
				"", "", "self", "false");
		Boolean msg = (Boolean)JSONPath.read(res, "$.success");
		Assert.assertTrue(msg,"给用户设置管辖范围--全公司,项目查看范围--我创建及负责的项目/任务"+res);
	}
	
	@Test(description = "给用户设置管辖范围--所在部门及子部门,项目查看范围--我创建及负责的项目/任务",priority = 8)
	public void testSaveAuthorityRangesDept_with_sub_ids() {
		String res = JurisdictionBusiness.saveAuthorityRanges(source_user_id, "dept_with_sub_ids", "", "",
				"", "", "self", "false");
		Boolean msg = (Boolean)JSONPath.read(res, "$.success");
		Assert.assertTrue(msg,"给用户设置管辖范围--所在部门及子部门,项目查看范围--我创建及负责的项目/任务"+res);
	}
	
	@Test(description = "给用户设置管辖范围--自定义,用户组，岗位组，部门， 用户,项目查看范围--我创建及负责的项目/任务",priority = 9)
	public void testSaveAuthorityRangesCustom() {
		String res = JurisdictionBusiness.saveAuthorityRanges(source_user_id, "custom", UserBusiness.getUserId(), dep_id,
				post_id, group_id, "self", "false");
		Boolean msg = (Boolean)JSONPath.read(res, "$.success");
		Assert.assertTrue(msg,"给用户设置管辖范围--自定义,用户组，岗位组，部门， 用户,项目查看范围--我创建及负责的项目/任务"+res);
	}
	@Test(description = "给管理员设置管辖范围后，查询管辖范围依旧是全公司",priority=10)
	public void testQueryAuthorityRanges() {
		String res = JurisdictionBusiness.queryAuthorityRanges(source_user_id);
		String range_type = (String)JSONPath.read(res, "$.range_type");
		Assert.assertEquals(range_type, "all","给管理员设置管辖范围后，查询管辖范围依旧是全公司"+res);
	}
	
	@Test(description = "查询管辖范围的设置",priority = 11)
	public void testQueryJurisdictionSetting() {
		String res = JurisdictionBusiness.queryJurisdictionSetting();
		String title  = (String)JSONPath.read(res,"$.title");
		Assert.assertEquals(title,"管辖范围设置","查询管辖范围的设置，实际返回结果："+res);
	}
	
}
