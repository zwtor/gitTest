package communitygroup.cases;

import cn.kxy.community.group.CircleBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCircle {

	@Test(description = "初始化数据",priority = 1)
	public void init() {
		String title = "Math" + CommonData.getStringRandom(5);
		String res = CircleBusiness.queryCircle("");
		Boolean enable = (Boolean)JSONPath.read(res, "$.data.content[0].enable");
		if (enable==null) {
			CircleBusiness.addCircle(title);
		}
	}
	String id = "";
	Boolean enable ;
	@Test(description = "查看管理端圈子列表",priority = 2)
	public void testQueryCircle() {
		String res = CircleBusiness.queryCircle("");
		String title = (String)JSONPath.read(res, "$.data.content[0].name");
		id = (String)JSONPath.read(res, "$.data.content[0].id");
		enable = (Boolean)JSONPath.read(res, "$.data.content[0].enable");
		Assert.assertNotNull(title, "查看管理端圈子列表，实际返回结果："+res);
	}
	
	@Test(description = "对圈子状态进行关闭设置",priority = 3)
	public void testOpenstat() {
		if (enable) {
			 CircleBusiness.stat(id, "false");
		}
		String res = CircleBusiness.stat(id, "false");
		Assert.assertTrue(res.contains("success"),"关闭圈子实际返回结果："+res);
	}
	
	@Test(description = "对圈子状态进行开启设置",priority = 4)
	public void testClosestat() {
		if (enable) {
			 CircleBusiness.stat(id, "false");
		}
		String res = CircleBusiness.stat(id, "true");
		Assert.assertTrue(res.contains("success"),"开启圈子实际返回结果："+res);
	}
	
}
