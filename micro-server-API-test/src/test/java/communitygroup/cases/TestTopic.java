package communitygroup.cases;

import cn.kxy.community.group.TopicBusiness;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTopic {

	String tag_name = "";
	Boolean status ;
	@Test(description = "查看话题列表",priority = 1)
	public void testQueryList() {
		String res = TopicBusiness.queryList("");
//		tag_name =  (String)JSONPath.read(res, "$.data.content[0].tag");
//		status =  (Boolean)JSONPath.read(res, "$.data.content[0].status");
		Assert.assertTrue(res.contains("success"), "查看管理端圈子列表，实际返回结果："+res);
	}
	@Test(description = "打开话题",priority = 2)
	public void testChangeTopicStatus() {
		/*if (status) {
			TopicBusiness.changeTopicStatus(tag_name, "disable");
		}
		String res = TopicBusiness.changeTopicStatus(tag_name , "on");
		Assert.assertTrue(res.contains("success"),"打开话题，实际返回结果："+res);*/
	}
	
	@Test(description = "关闭话题",priority = 3)
	public void test() {
		/*String res = TopicBusiness.changeTopicStatus(tag_name, "disable");
		Assert.assertTrue(res.contains("success"),"关闭话题，实际返回结果："+res);*/
	}
}
