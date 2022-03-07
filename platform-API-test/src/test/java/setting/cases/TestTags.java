package setting.cases;

import cn.kxy.setting.bussiness.TagBusiness;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestTags {

	
	
	@Test(description = "查看不同类型的热门推荐标签",priority = 1,dataProvider = "type")
	public void testQueryHotTags(String s,String type) {
		String res = TagBusiness.queryHotTags(type);
		Assert.assertTrue(res.contains("200"),s+"类型的热门推荐标签"+res);
	}
	
	@Test(description = "查看不同类型的标签",priority = 2,dataProvider = "type")
	public void testQueryTagsTypes(String s,String type) {
		String res = TagBusiness.queryTagsTypes(type);
		Assert.assertTrue(res.contains("data"),s+"不同类型的标签"+res);

	}
	
	@Test(description = "搜索标签",priority = 3)
	public void testSearchType() {
		String res = TagBusiness.searchType("");
		Assert.assertTrue(res.contains("data"),"不输入内容搜索标签"+res);
	}
	
	@Test(description = "根据关键字搜索标签",priority = 4)
	public void testSearchTagsBykeyword() {
		String res = TagBusiness.searchType("阿达");
		Assert.assertTrue(res.contains("data"),"不同类型的标签"+res);
		
	}
	
	@DataProvider(name = "type")
	public Object[][] statusKey() {
		Object[][] obj = new Object[][] { 
				{"查看全部",""},{"查看讲师","lecturer"},{"查看题库类型","practice"},{"查看课程","course"},
				{"查看课件","resource"},{"查看学习任务","studyTask"},{"查看调研任务","investigateTask"},{"查看考试任务","examTask"},
				{"查看问卷","questionnaire"},{"查看学习项目","project"},{"查看试卷","paper"}
			};
		return obj;
	}
}
