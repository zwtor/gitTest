package investigationresearch.cases;

import cn.kxy.investigationresearch.business.InvestigatesBusiness;
import cn.kxy.investigationresearch.business.QuestionnaireBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestExtremelyInvestigates  {
	@Test(description = "根据keyword, create_status, course_classify, status类型查看问卷管理模块", dataProvider = "QuertListByDifferentType")
	public void testQuertListByDifferentType(String kind, String keyword, String view_range,
			String status) {
		String res = InvestigatesBusiness.queryLsit(keyword,view_range, status);
		int total = (int) JSONPath.read(res, "$.investigates.total");
		Assert.assertTrue(total>=0, "异常场景下，不同关键字不存在的情况下，显示默认数据，但不会报错，实际返回结果："+res);
	}
	
	@DataProvider(name = "QuertListByDifferentType")
	public Object[][] QuertListByDifferentType() {
		Object[][] obj = new Object[][] { { "全部为null的时候进行查看", "", "", ""}, { "发布状态不存在的情况下", "", "", "0154" },
				{ "关键字不存在的情况", "56565", "1", "1" }, { "可见范围状态存在的情况", "6546", "" ,"44"} };
		return obj;
	}
	@Test(description="当title为null，发布调研任务时，msg校验 ")
	public void testPublicInvestigatesTitleIsNull() {
		String res = InvestigatesBusiness.addNomalInvestigates("", "", "");
		String msg = (String)JSONPath.read(res, "$.data[0].message");
		Assert.assertEquals(msg, "title not empty","当title为null，发布调研任务时，msg校验 "+res);
	}
	@Test(description = "根据keyword, create_status, course_classify, status类型查看问卷管理模块", dataProvider = "QuertListByDifferentQuerType",priority=5)
	public void testQuertListByDifferentType(String kind, String keyword, String create_status, String course_classify,
			String status, String exp) {
		String res = QuestionnaireBusiness.queryLsit(keyword, create_status, course_classify, status);
		int total = (int) JSONPath.read(res, "$.questionnaires.total");
		Assert.assertTrue(total>=0, "异常场景下，不同关键字不存在的情况下，显示默认数据，但不会报错，实际返回结果："+res);
	}
	
	@DataProvider(name = "QuertListByDifferentQuerType")
	public Object[][] QuertListByDifferentQuerType() {
		Object[][] obj = new Object[][] { { "全部为null的时候进行查看", "", "", "", "", "" },
				{ "关键字不存在的情况", "56565", "1", "1", "1","0" }, { "分类不存在的情况", "", "", "025154", "" ,""} };
		return obj;
	}
	
	@Test(description="当id为null时，查看问卷详情，不会报错 ",priority=6)
	public void testQueryInfoIdIsNull() {
		String res=QuestionnaireBusiness.queryInfo("", "");
		String name = (String)JSONPath.read(res, "$.title");
		Assert.assertTrue(!name.isEmpty(),"当id为null时，查看问卷详情，不会报错 ，默认查看第一个问卷详情实际返回结果："+res);
	}
	@Test(description="当id为null，发布问卷时，不会报错 ",priority=7)
	public void testPublicQuestionnaireIdIsNull() {
		String res=QuestionnaireBusiness.publicQuestionnaire("", "");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertTrue(!msg.isEmpty(),"当id为null时，查看问卷详情，不会报错 ："+res);
	}
	@Test(description="当id为null时，删除问卷不会报错 ",priority=8)
	public void testDeleteQuestionnaireIsnull() {
	
//		QuestionnaireBusiness.addNormalQuestionnaire("name024165", "this is a description", "release");
//		String res=QuestionnaireBusiness.deleteQuestionnaire("", "");
//		String msg = (String)JSONPath.read(res, "$.msg");
//		Assert.assertTrue(!msg.isEmpty(),"当id为null时，删除问卷不会报错，默认删除第一个 ，实际返回结果："+res);
	}
}
