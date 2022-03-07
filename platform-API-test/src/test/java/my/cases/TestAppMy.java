package my.cases;

import cn.kxy.my.business.AppMybusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAppMy {

	@Test(description="APP端查看banner信息",priority=1)
	public void testGetSysBannerList() {
		String res = AppMybusiness.getSysBannerList();
		String id = (String)JSONPath.read(res, "$[0].id");
		Assert.assertNotNull(id, "APP端查看banner信息:"+res);
	}
	@Test(description="查询APP我的订单列表-All",priority=2)
	public void testQueryMyAllOrder() {
		String res = AppMybusiness.queryMyOrder("all");
		int size = (int)JSONPath.read(res, "$.size");
		Assert.assertTrue(size>=0,"查询APP我的订单列表-All"+res);
	}
	@Test(description="查询APP我的订单列表-未付款",priority=3)
	public void testQueryUnpayMyOrder() {
		String res = AppMybusiness.queryMyOrder("unpaid");
		int size = (int)JSONPath.read(res, "$.size");
		Assert.assertTrue(size>=0,"查询APP我的订单列表-未付款"+res);
	}
	
	@Test(description="查询APP我的订单列表-已付款",priority=4)
	public void testQueryPayMyOrder() {
		String res = AppMybusiness.queryMyOrder("paid");
		int size = (int)JSONPath.read(res, "$.size");
		Assert.assertTrue(size>=0,"查询APP我的订单列表-已付款"+res);
	}
	
	@Test(description="查询已购课程列表",priority=5)
	public void testQueryCourse() {
		String res = AppMybusiness.queryCourse("");
		int size = (int)JSONPath.read(res, "$.size");
		Assert.assertTrue(size>=0,"查询已购课程列表"+res);
	}
	@Test(description="查询课程待审批列表",priority=6)
	public void testUnApprovalCourseList() {
		String res = AppMybusiness.approvalCourse("approval");
		int size = (int)JSONPath.read(res, "$.size");
		Assert.assertTrue(size>=0,"查询课程待审批列表"+res);
	}
	
	@Test(description="查询课程已审批列表",priority=7)
	public void testApprovalCourseList() {
		String res = AppMybusiness.approvalCourse("approved");
		int size = (int)JSONPath.read(res, "$.size");
		Assert.assertTrue(size>=0,"查询课程已审批列表"+res);
	}
	@Test(description="查看意见反馈列表",priority=8)
	public void testQuerySuggestionsList() {
		String res = AppMybusiness.querySuggestionsList();
		int size = (int)JSONPath.read(res, "$.size");
		Assert.assertTrue(size>=0,"查看意见反馈列表"+res);
	}
	String help_id = "";
	@Test(description= "查看帮助文档列表",priority=9)
	public void testQueryHelpDocumentsList() {
		String res = AppMybusiness.queryHelpDocumentsList();
		help_id = (String)JSONPath.read(res, "$.list[0].id");
		String title = (String)JSONPath.read(res, "$.list[0].title");
		Assert.assertNotNull(title,"查看帮助文档列表"+res);
		Assert.assertTrue(!title.isEmpty(),"查看帮助文档列表"+res);
	}
	@Test(description="查看帮助文档详情",priority=10)
	public void testQueryHelpDocumentsInfo() {
		String res = AppMybusiness.queryHelpDocumentsInfo(help_id);
		String content = (String)JSONPath.read(res, "$.content");
		Assert.assertNotNull(content,"查看帮助文档详情"+res);
		Assert.assertTrue(!content.isEmpty(),"查看帮助文档详情"+res);
	}
	
	@Test(description="查看我的学分列表",priority=11)
	public void testQueryScoresList() {
		String res = AppMybusiness.queryScoresList();
		String name = (String)JSONPath.read(res, "$.list[0].name");
		int score = (int)JSONPath.read(res, "$.list[0].score");
		Assert.assertNotNull(score, "查看我的学分列表,学分进行校验"+res);
		Assert.assertNotNull(name, "查看我的学分列表，用户名进行校验"+res);
	}
	
	@Test(description="查询今日学分",priority=12)
	public void testQueryTodayScores() {
		String res = AppMybusiness.queryTodayScores();
		int size = (int)JSONPath.read(res, "$.size");
		Assert.assertTrue(size>=0,"查询今日学分"+res);
	}
	
	@Test(description="查询我的证书列表",priority=13)
	public void testQueryCertificates() {
		String res = AppMybusiness.queryCertificates();
		String certificate_name = (String)JSONPath.read(res, "$.certificates[0].certificate_name");
		String certificate_code = (String)JSONPath.read(res, "$.certificates[0].certificate_code");
		String template_image_url = (String)JSONPath.read(res, "$.certificates[0].template_image_url");
		String logo_url = (String)JSONPath.read(res, "$.certificates[0].logo_url");
		Assert.assertNotNull(certificate_name,"查询我的证书列表,证书名称进行校验"+res);
		Assert.assertNotNull(certificate_code,"查询我的证书列表,证书code进行校验"+res);
		Assert.assertNotNull(template_image_url,"查询我的证书列表,模板url进行校验"+res);
		Assert.assertNotNull(logo_url,"查询我的证书列表,企业logo进行校验"+res);
	}
	
	@Test(description="设置繁体中文",priority=14)
	public void testSetzhhkLanguage() {
		String res = AppMybusiness.setLanguage("zh_hk");
		String language = (String)JSONPath.read(res, "$.language");
		Assert.assertEquals(language, "zh_hk","设置繁体中文"+res);
	}
	
	@Test(description="设置英文",priority=15)
	public void testSetLanguage() {
		String res = AppMybusiness.setLanguage("en_us");
		String language = (String)JSONPath.read(res, "$.language");
		Assert.assertEquals(language, "en_us","设置英文"+res);
	}
	@Test(description="设置简体中文",priority=16)
	public void testSetChineseLanguage() {
		String res = AppMybusiness.setLanguage("zh_cn");
		String language = (String)JSONPath.read(res, "$.language");
		Assert.assertEquals(language, "zh_cn","设置简体中文"+res);
	}
	@Test(description = "查看认证待审批列表",priority = 17)
	public void testQualificationsApproval() {
		String res = AppMybusiness.qualificationsApproval("1");
		String total =(String)JSONPath.read(res, "$.total");
		Assert.assertNotNull(total,"查看认证待审批列表,实际返回结果："+res);
	}
	@Test(description = "查看认证已审批列表",priority = 18)
	public void testQualificationsApprovaled() {
		String res = AppMybusiness.qualificationsApproval("2");
		String total =(String)JSONPath.read(res, "$.total");
		Assert.assertNotNull(total,"查看认证已审批列表,实际返回结果："+res);
	}
}
