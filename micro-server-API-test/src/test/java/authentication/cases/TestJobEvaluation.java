package authentication.cases;

import cn.kxy.authentication.business.JobEvaluationBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestJobEvaluation {

	String title = "JobEvaluation" + CommonData.getStringRandom(5);
	int product_id ;
	
	String id = "";
	
	@Test(description = "获取岗位测训的id",priority = 1)
	public void testQueryeEvaluationType() {
		String res = JobEvaluationBusiness.queryeEvaluationType();
		product_id = (int)JSONPath.read(res,"$[0].product_id");
	}
	
	@Test(description = "保存岗位测评至草稿箱",priority = 2)
	public void testSaveEvaluation() {
		String res = JobEvaluationBusiness.saveEvaluation(title, "0", product_id, "permanent","0");
		id = (String)JSONPath.read(res, "$.id");
		Assert.assertTrue(res.contains(title),res);
	}
	
	@Test(description = "在草稿箱页面查看",priority = 3)
	public void testQueryListByDraft() {
		String res = JobEvaluationBusiness.queryList("0", title,"false","");
		String title = (String)JSONPath.read(res, "$.page_info.list[0].title");
		String product_name = (String)JSONPath.read(res, "$.page_info.list[0].product_name");
		String create_user_name = (String)JSONPath.read(res, "$.page_info.list[0].create_user_name");
		Assert.assertNotNull(title,res);
		Assert.assertNotNull(product_name,res);
		Assert.assertNotNull(create_user_name,res);
	}

	@Test(description = "在草稿箱页面通过类型查看",priority = 4)
	public void testQueryListByDraftProId() {
		String res = JobEvaluationBusiness.queryList("0", title,"false","");
		Assert.assertTrue(res.contains("total"),res);
	}
	
	@Test(description = "发布岗位测评",priority = 5)
	public void testPublishEvaluation() {
		String res = JobEvaluationBusiness.publishEvaluation(id);
		Assert.assertTrue(res.contains("SUCCESS"),res);
	}
	@Test(description = "在已发布页面查看",priority = 6)
	public void testQueryListByPublic() {
		String res = JobEvaluationBusiness.queryList("0", title,"true","");
		Assert.assertTrue(res.contains("publish_count"),res);
	}
	
	@Test(description = "查看pc端",priority = 6,dataProvider = "statusKey")
	public void test(String type ,String status ,String exp) {
		String res = JobEvaluationBusiness.queryEvaluationList(id, "", status);
		Assert.assertTrue(res.contains(exp),res);
	}

	@Test(description = "移动端查看岗位测评详情",priority = 7)
	public void testQueryAppOpenEvaluation() {
//		String res = JobEvaluationBusiness.queryAppOpenEvaluation(id);
//		Assert.assertTrue(res.contains("status"),"移动端查看岗位测评详情"+res);
	}
	@Test(description = "取消岗位测评",priority = 8 )
	public void testCalcelEvaluation() {
		String res = JobEvaluationBusiness.calcelEvaluation(id);
		Assert.assertTrue(res.contains("SUCCESS"),res);
	}
	
	@Test(description = "在已发布页面查看",priority = 9)
	public void testQueryListByPublicNoKey() {
		String res = JobEvaluationBusiness.queryList("0", "","true","");
		Assert.assertNotNull(res.contains("publish_count"),res);
	}

	@Test(description = "导出岗位测评的统计数据",priority = 10)
	public void testExportExcel() {
		String res = JobEvaluationBusiness.exportExcel(id);
		Assert.assertTrue(res.contains("测评任务数据明细导出记录已生成"),res);
	}
	
	@Test(description = "查看岗位测评的详情",priority = 11)
	public void testQueryEvaluationDetail() {
		String res = JobEvaluationBusiness.queryEvaluationDetail(id);
		Assert.assertTrue(res.contains(title),res);
	}
	
	@Test(description = "删除岗位测评",priority =12 )
	public void testDeletelEvaluation() {
		String res = JobEvaluationBusiness.deleteEvaluation(id);
		Assert.assertTrue(res.contains("SUCCESS"),res);
	}
	@Test(description = "查看工具列表",priority = 13)
	public void testQueryAbilityList() {
		String queryAbilityList = JobEvaluationBusiness.queryAbilityList();
		Assert.assertTrue(queryAbilityList.contains("is_first_page"));
	}
	
	@Test(description = "查看测训工具列表",priority = 14)
	public void testQueryToolsList() {
		String queryToolsList = JobEvaluationBusiness.queryToolsList();
		Assert.assertTrue(queryToolsList.contains("is_first_page"));
	}
	
	@Test(description = "查看测训能力项目列表",priority = 15)
	public void testQueryToolsNowList() {
		int queryToolsNowList = JobEvaluationBusiness.queryToolsNowList();
		System.out.println(queryToolsNowList);
		Assert.assertEquals(200,queryToolsNowList,queryToolsNowList);
	}
	
	@DataProvider(name = "statusKey")
	public Object[][] statusKey() {
		Object[][] obj = new Object[][] { 
			{"查看全部","","total"},{"查看已完成","0","total"},
			{"查看未完成","2","total"},{"查看已完成生成学习计划","1","total"}

			};
		return obj;
	}
	
}
