package setting.cases;

import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPosts {

	String post_id = "";
	String children_post_id = "";
	String child_name = "";
	@Test(description = "获取学员管理的岗位列表",priority = 1)
	public void testQueryPostsManage() {
		String res = UserBusiness.queryPostsManage("");
		post_id = (String)JSONPath.read(res, "$.posts[1].id");
		children_post_id = (String)JSONPath.read(res, "$.posts[1].children[0].id");
		child_name = (String)JSONPath.read(res, "$.posts[1].children[0].post_name");
		String post_name = (String)JSONPath.read(res, "$.posts[1].post_name");
		Assert.assertNotNull(post_name,"获取学员管理的岗位列表，实际返回结果："+res);
	}
	@Test(description = "getPrincipalUserByPost接口验证" ,priority = 2)
	public void testGetPrincipalUserByPost() {
		String res = UserBusiness.getPrincipalUserByPost(children_post_id);
		String name = (String)JSONPath.read(res, "$.data.depathmentName");
		Assert.assertEquals(name, child_name,"getPrincipalUserByPost接口验证"+res);
	}
	
	@Test(description = "往岗位添加人员",priority = 3)
	public void testAddUsersToPost() {
//		String res = UserBusiness.addUsersToPost(children_post_id);
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "success","往岗位添加人员"+res);
	}
	String user_name = UserBusiness.getUsername();
	@Test(description = "往岗位添加人员,查看子岗位列表页",priority = 4)
	public void testQueryPostUser() {
//		String res = UserBusiness.queryPostUser(children_post_id,user_name,"ding");
//		String name = (String)JSONPath.read(res, "$.list[0].user_name");
//		Assert.assertEquals(name,user_name,"往岗位添加人员,查看子岗位列表页"+res);
	}
	@Test(description = "往岗位添加人员,查看岗位组的人数",priority = 5)
	public void queryPostsManageNum() {
//		String res = UserBusiness.queryPostsManage("");
//		int num = (int)JSONPath.read(res, "$.posts[1].children[0].user_count");
//		Assert.assertTrue(num>1,"往岗位添加人员,查看岗位组的人数"+res);
	}
	
	@Test(description = "删除岗位下的人员",priority = 6)
	public void testDelUsersFromPost() {
//		String res = UserBusiness.delUsersFromPost(children_post_id);
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "success","删除岗位下的人员"+res);
	}
	@Test(description = "删除岗位下的人员,查看子岗位列表页",priority = 7)
	public void testQueryPostUserByDelete() {
//		String res = UserBusiness.queryPostUser(children_post_id,user_name,"ding");
//		String name = (String)JSONPath.read(res, "$.list[0].user_name");
//		Assert.assertNull(name,"往岗位添加人员,查看子岗位列表页"+res);
	}
}
