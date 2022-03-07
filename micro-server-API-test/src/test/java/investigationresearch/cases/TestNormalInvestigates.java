package investigationresearch.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.investigationresearch.business.InvestigatesBusiness;
import cn.lazy.init.cases.InitExam;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNormalInvestigates extends InitExam {
	String title = "Investigates"+CommonData.getStringRandom(3);
	String id="";
	@Test(description="新增调研任务保存到草稿箱",priority=1)
	public void testAddInvestigates() {
		
		String res = InvestigatesBusiness.addNomalInvestigates(BaseBusiness.questionnaireName, title, "draft");
		String status = (String)JSONPath.read(res, "$.status") ;
		Assert.assertEquals(status, "true","新增调研任务保存到草稿箱实际返回结果："+res);
	}
	
	
	@Test(description="查询调研任务详情",priority=2)
	public void testQueryInfo() {
		String res = InvestigatesBusiness.qqueryInfo(InvestigatesBusiness.getIdByKeyword(title, "draft"));
		String name = (String)JSONPath.read(res, "$.title") ;
		Assert.assertEquals(name, title,"查询调研任务详情实际返回结果："+res);;
	}

	@Test(description="查看调研任务草稿箱列表",priority=3)
	public void testQueryDraftList() {
		String res = InvestigatesBusiness.queryLsit(title, "all", "draft");
		
		String name = (String)JSONPath.read(res, "$.investigates.list[0].title") ;
		String username = (String)JSONPath.read(res, "$.investigates.list[0].create_user") ;
		Long create_time = (Long)JSONPath.read(res, "$.investigates.list[0].create_time") ;
		id=(String)JSONPath.read(res, "$.investigates.list[0].id");
		Assert.assertTrue(create_time!=null,"查看调研任务草稿箱列表实际返回结果："+res);
//		Assert.assertEquals(username,UserBusiness.getUsername() ,"查看调研任务草稿箱列表实际返回结果："+res);
		Assert.assertEquals(name,title ,"查看调研任务草稿箱列表实际返回结果："+res);
		
	}
	
	@Test(description="在草稿箱页面发布调研任务",priority=4)
	public void testPublicInvestigates() {
		String id = InvestigatesBusiness.getIdByKeyword(title, "draft");
		String res = InvestigatesBusiness.publicInvestigates(id);
		String status = (String)JSONPath.read(res, "$.status") ;
		Assert.assertEquals(status, "true","在草稿箱页面发布调研任务实际返回结果："+res);
	}
	
	@Test(description="查看问卷已发布列表",priority=5)
	public void testQueryReleaseList() {
		String res = InvestigatesBusiness.queryLsit(title, "own", "release");
		String name = (String)JSONPath.read(res, "$.investigates.list[0].title") ;
		String username = (String)JSONPath.read(res, "$.investigates.list[0].create_user") ;
		Long create_time = (Long)JSONPath.read(res, "$.investigates.list[0].create_time") ;
		Assert.assertTrue(create_time!=null,"查看调研任务草稿箱列表实际返回结果："+res);
//		Assert.assertEquals(username,UserBusiness.getUsername() ,"查看调研任务草稿箱列表实际返回结果："+res);
		Assert.assertEquals(name,title ,"查看调研任务草稿箱列表实际返回结果："+res);
	}
	
	@Test(description="在已发布页面取消调查任务",priority=6)
	public void testCancleInvestigates() {
		String res = InvestigatesBusiness.cancelInvestigates(title, "release");
		String status = (String)JSONPath.read(res, "$.status") ;
		Assert.assertEquals(status, "true","在已发布页面取消调查任务实际返回结果："+res);
	}
	
	@Test(description="在草稿箱页面删除调查任务",priority=7)
	public void testDeleteInvestigates() {
		String res = InvestigatesBusiness.deleteInvestigates(title, "draft");
		String status = (String)JSONPath.read(res, "$.status") ;
		Assert.assertEquals(status, "true","在草稿箱页面删除调查任务实际返回结果："+res);
	}
	
	@Test(description="直接发布调查任务，然后取消，删除",priority=8)
	public void testPublic_Cancel_Delete_Investigates() {
		String name = "Investigates"+CommonData.getStringRandom(3);
		InvestigatesBusiness.addNomalInvestigates(BaseBusiness.questionnaireName, name, "release");
		InvestigatesBusiness.cancelInvestigates(name, "release");
		String res = InvestigatesBusiness.deleteInvestigates(name, "draft");
		String res01 = InvestigatesBusiness.queryLsit(name, "all", "draft");
		int total = (int)JSONPath.read(res01, "$.investigates.total");
		String status = (String)JSONPath.read(res, "$.status") ;
		Assert.assertEquals(status, "true","在已发布页面删除调查任务实际返回结果："+res);
		Assert.assertEquals(total, 0,"直接发布调查任务，然后取消，删除实际返回结果："+res+",列表页个数实际为："+total);
	}
	
	@Test(description="编辑调查任务到已发布，然后取消，删除",priority=9)
	public void testEdit_Cancel_Delete_Investigates() {
		String name = "Investigates"+CommonData.getStringRandom(3);
		String edit_name = "Investigates"+CommonData.getStringRandom(3);
		InvestigatesBusiness.addNomalInvestigates(BaseBusiness.questionnaireName, name, "draft");
		InvestigatesBusiness.editInvestigates(BaseBusiness.questionnaireName, "release", edit_name,InvestigatesBusiness.getIdByKeyword(name, "draft") );
		InvestigatesBusiness.cancelInvestigates(edit_name, "release");
		String res = InvestigatesBusiness.deleteInvestigates(edit_name, "draft");
		String res01 = InvestigatesBusiness.queryLsit(edit_name, "all", "draft");
		int total = (int)JSONPath.read(res01, "$.investigates.total");
		String status = (String)JSONPath.read(res, "$.status") ;
		Assert.assertEquals(status, "true","在已发布页面删除调查任务实际返回结果："+res);
		Assert.assertEquals(total, 0,"直接发布调查任务，然后取消，删除实际返回结果："+res+",列表页个数实际为："+total);
	}
	
	@Test(description="编辑调查任务到草稿箱，然后删除",priority=10)
	public void testEdit_Delete_Investigates() {
		String name = "Investigates"+CommonData.getStringRandom(3);
		String edit_name = "Investigates"+CommonData.getStringRandom(3);
		InvestigatesBusiness.addNomalInvestigates(BaseBusiness.questionnaireName, name, "draft");
		InvestigatesBusiness.editInvestigates(BaseBusiness.questionnaireName, "draft", edit_name,InvestigatesBusiness.getIdByKeyword(name, "draft") );
		String res = InvestigatesBusiness.deleteInvestigates(edit_name, "draft");
		String res01 = InvestigatesBusiness.queryLsit(edit_name, "all", "draft");
		int total = (int)JSONPath.read(res01, "$.investigates.total");
		String status = (String)JSONPath.read(res, "$.status") ;
		Assert.assertEquals(status, "true","在已发布页面删除调查任务实际返回结果："+res);
		Assert.assertEquals(total, 0,"直接发布调查任务，然后取消，删除实际返回结果："+res+",列表页个数实际为："+total);
	}
	
	@Test(description="检查调研任务是否可以取消")
	public void testCheckeCancelInvestigates() {
		String name = "Investigates"+CommonData.getStringRandom(2);
		InvestigatesBusiness.addNomalInvestigates(BaseBusiness.questionnaireName, name, "release");
		String res = InvestigatesBusiness.chechIsCanCancel_Delete("release",InvestigatesBusiness.getIdByKeyword(name, "release"));
		String status= (String)JSONPath.read(res, "$.status");
		InvestigatesBusiness.cancelDeleteInvestigates(name);
		Assert.assertEquals(status, "true","检查调研任务是否可以取消实际返回结果："+res);
	}
	@Test(description="检查调研任务是否可以删除")
	public void testCheckeDeleteInvestigates() {
		String name = "Investigates"+CommonData.getStringRandom(2);
		InvestigatesBusiness.addNomalInvestigates(BaseBusiness.questionnaireName, name, "draft");
		String res = InvestigatesBusiness.chechIsCanCancel_Delete("draft", InvestigatesBusiness.getIdByKeyword(name, "draft"));
		String status= (String)JSONPath.read(res, "$.status");
		InvestigatesBusiness.deleteInvestigates(name, "draft");
		Assert.assertEquals(status, "true","检查调研任务是否可以删除实际返回结果："+res);
	}
	
}
