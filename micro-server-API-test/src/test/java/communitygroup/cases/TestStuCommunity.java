package communitygroup.cases;

import cn.kxy.community.group.ArticleManageBusiness;
import cn.kxy.community.group.CreationBusiness;
import cn.kxy.community.group.TopicBusiness;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestStuCommunity {

	@Test(description = "查看学员端列表",dataProvider = "hometype",priority = 1)
	public void testQueryList(String type_title,String type,String exp) {
		String res = TopicBusiness.queryHotList(type);
		System.out.println("this is a Community module");
		Assert.assertTrue(res.contains(exp),"查看学员端列表，实际返回结果："+res);
	}
	
	@Test(description = "查看学员端搜索列表",dataProvider = "searchtype",priority = 2)
	public void testSearchList(String type_title,String type,String exp) {
		String res = ArticleManageBusiness.searchDiffList("",type);
		Assert.assertTrue(res.contains(exp),"查看学员端搜索列表，实际返回结果："+res);
	}
	
	@Test(description = "查看创作中心的数据",priority = 3)
	public void testQueryCreationData() {
		String res = CreationBusiness.queryCreationData();
		Assert.assertTrue(res.contains("success"),"查看学员端搜索列表，实际返回结果："+res);
	}
	@Test(description = "查看学员端我的创作中心列表",dataProvider = "Creationtype",priority = 4)
	public void testQueryMyCreation(String type_title,String type,String exp) {
		String res = CreationBusiness.queryMyCreation(type);
//		Assert.assertTrue(res.contains(exp),"查看学员端我的创作中心列表，实际返回结果："+res);
	}
	
	@Test(description = "读取app社区消息",priority = 5)
	public void testReadMessage() {
		String res = CreationBusiness.readMessage();
		Assert.assertTrue(res.contains("success"),"读取app社区消息，实际返回结果："+res);
	}
	
	@Test(description = "查看app社区消息列表",priority = 6)
	public void testQueryAppMessageList() {
		String queryAppMessageList = CreationBusiness.queryAppMessageList();
		Assert.assertTrue(queryAppMessageList.contains("success"),"查看app社区消息列表，实际返回结果："+queryAppMessageList);
	}
	
	@DataProvider(name = "Creationtype")
	public Object[][] queryCreationtype() {
		Object[][] obj = new Object[][] {  { "学员端查看创作中心问答列表", "answers", "success" },
				{ "学员端查看创作中心文章列表", "articles", "success" } ,{ "学员端查看创作中心提问列表", "questions", "success" }};
		return obj;
	}
	
	@DataProvider(name = "searchtype")
	public Object[][] queryListByType() {
		Object[][] obj = new Object[][] { { "学员端查看搜索热门列表", "all", "success"},/* { "学员端查看搜索问答列表", "qa", "success" },*/
				{ "学员端查看搜索文章列表", "article", "success" } ,{ "学员端查看搜索提问列表", "question", "success" }};
		return obj;
	}
	
	@DataProvider(name = "hometype")
	public Object[][] queryListByDifferentType() {
		Object[][] obj = new Object[][] { { "学员端查看热门列表", "", "success"}, { "学员端查看问题列表", "QUESTION", "success" },
				{ "学员端查看文章列表", "ARTICLE", "success" } };
		return obj;
	}
	
}
