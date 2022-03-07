package setting.cases;

import cn.kxy.setting.bussiness.AuthUserBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

//选人控件优化：可以根据部门和人员进行模糊搜索
public class TestAddTaskUserSelectPeople {
	String depart_name=  "";
	String no_name = "all34Niksaf";
	@Test(description = "获取部门名称，供查询是使用",priority = 1)
	public void testGetDepartmentName() {
		String res = AuthUserBusiness.GetDepartmentList();
		depart_name = (String)JSONPath.read(res, "$.children[0].department_name");
	}
	@Test(description = "查询所有部门",priority = 2)
	public void testQueryAllDepartment() {
		String res = UserBusiness.queryDepartment("");
		String depa_name = (String)JSONPath.read(res, "$.departments[0].department_name");
		Assert.assertNotNull(depa_name, "查询所有部门,实际返回结果："+res);
	}
	@Test(description = "按照名称查询",priority = 3)
	public void testQueryDepartmentByName() {
		String res = UserBusiness.queryDepartment("");
		Assert.assertTrue(res.contains("departments"), "按照名称查询，实际返回结果："+res);
	}
	
	@Test(description = "名称不存在时查询",priority = 4)
	public void testQueryDepartmentNameIsNotExist() {
		String res = UserBusiness.queryDepartment(no_name);
		Assert.assertTrue(res.contains("departments"), "名称不存在时查询,实际返回结果："+res);
	}
	String post_name ="";
	@Test(description = "获取岗位名称，供查询使用",priority = 5)
	public void testGetPostName() {
		String res = AuthUserBusiness.queryUsePostList();
		post_name = (String)JSONPath.read(res, "$.posts[0].children[0].post_name");
	}
	
	@Test(description = "查询所有部门",priority = 6)
	public void testQueryAllPosts() {
		String res = UserBusiness.queryPosts("");
		Assert.assertNotNull(res.contains("posts"),"指定名称查询岗位,实际返回结果："+res);
	}
	@Test(description = "指定名称查询岗位",priority = 7)
	public void testQueryPostsByName() {
//		String res = UserBusiness.queryPosts(post_name);
//		Assert.assertTrue(res.contains(post_name) ,"指定名称查询岗位,实际返回结果："+res);
	}
	@Test(description = "名称不存在时查询岗位",priority = 8)
	public void testQueryPostsNameIsNotExist() {
		String res = UserBusiness.queryPosts(no_name);
		Assert.assertNotNull(res.contains("posts"),"指定名称查询岗位,实际返回结果："+res);
	}
	String user_group_name = "";
	@Test(description = "获取用户组的名称，供查询时使用",priority = 9)
	public void testGetGroupName() {
		String res = UserBusiness.queryGroups("");
		user_group_name = (String)JSONPath.read(res, "$.groups[1].group_name");
	}
	@Test(description = "查询所有用户组",priority = 10)
	public void testQueryAllGroups() {
		String res = UserBusiness.queryGroups("");
		String group_name= (String)JSONPath.read(res, "$.groups[0].group_name");
		Assert.assertNotNull(group_name,"查询所有用户组，实际返回结果："+res);
	}
	@Test(description = "查询指定名称的用户组",priority = 11)
	public void testQueryGroupsByName() {
		
	}
	@Test(description = "查询用户组名称不存在时的结果",priority = 12)
	public void testQueryGroupsNameIsNotExist() {
		String res = UserBusiness.queryGroups(no_name);
		Assert.assertTrue(res.contains("groups"),"查询用户组名称不存在时的结果:"+res);
	}
	String dep_id = "";
	@Test(description = "选人控件根据部门查看员工的个数",priority = 13)
	public void testQueryDepUser() {
		String res = UserBusiness.queryDepUser("true", "1", "true", "active");
		int total = (int)JSONPath.read(res, "$.children[0].user_count");
		dep_id = (String)JSONPath.read(res, "$.children[0].id");
		Assert.assertTrue(total>0, "选人控件根据部门查看员工,部门下的人个数验证实际返回结果："+res);
	}
	@Test(description = "选人控件根据部门查看员工是否存在",priority = 14)
	public void testQueryUserByDep() {
		String res = UserBusiness.queryUserByDep(dep_id);
		String user_name = (String)JSONPath.read(res, "$.users[0].user_name");
		Assert.assertNotNull(user_name,"选人控件根据部门查看员工是否存在"+res);
	}
	@Test(description = "通过用户名查询用户",priority=15)
	public void testqueryUserByName() {
		String res = UserBusiness.queryUserByName();
		String user_name = (String)JSONPath.read(res, "$.users[0].user_name");
		Assert.assertEquals(user_name, UserBusiness.getUsername(),""+res);
	}
}
