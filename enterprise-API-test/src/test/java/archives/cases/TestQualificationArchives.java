package archives.cases;

import cn.kxy.archives.business.StaffArchivesBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestQualificationArchives {
	String user_name = UserBusiness.getUsername();
	@Test(description="查看认证档案列表",priority=1)
	public void testQueryQualificationArchivesList() {
		String res = StaffArchivesBusiness.queryQualificationArchivesList("", "1", "incumbency");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertNotNull(total,"查看认证档案列表,实际返回结果："+res);
	}
	@Test(description="根据用户查看认证档案列表",priority=2)
	public void testQueryQualificationArchivesListByUser() {
		String res = StaffArchivesBusiness.queryQualificationArchivesList(user_name, "1", "incumbency");
		String name = (String)JSONPath.read(res, "$.list[0].name");
		Assert.assertEquals(name, user_name,"根据用户查看认证档案列表，实际返回结果："+res);
	}
	@Test(description="查看认证档案的档案详情列表（all）",priority=3)
	public void testQueryQualificationArchivesAllInfo() {
		String res = StaffArchivesBusiness.queryQualificationArchivesInfo("incumbency", "all");
		int total = (int)JSONPath.read(res, "$.user.finished_count");
		Assert.assertTrue(total>=0, "查看认证档案的档案详情列表（all）"+res);
	}
	@Test(description="查看认证档案的档案详情列表（学习中）",priority=4)
	public void testQueryQualificationArchivesStudyingInfo() {
		String res = StaffArchivesBusiness.queryQualificationArchivesInfo("incumbency", "studying");
		int total = (int)JSONPath.read(res, "$.user.finished_count");
		Assert.assertTrue(total>=0, "查看认证档案的档案详情列表（学习中）"+res);
	}
	@Test(description="查看认证档案的档案详情列表（待审核）",priority=5)
	public void testQueryQualificationArchivesApprovingInfo() {
		String res = StaffArchivesBusiness.queryQualificationArchivesInfo("incumbency", "approving");
		int total = (int)JSONPath.read(res, "$.user.finished_count");
		Assert.assertTrue(total>=0, "查看认证档案的档案详情列表（待审核）"+res);
	}
	@Test(description="查看认证档案的档案详情列表（已完成）",priority=6)
	public void testQueryQualificationArchivesFinishedInfo() {
		String res = StaffArchivesBusiness.queryQualificationArchivesInfo("incumbency", "finished");
		int total = (int)JSONPath.read(res, "$.user.finished_count");
		Assert.assertTrue(total>=0, "查看认证档案的档案详情列表（已完成）"+res);
	}
	@Test(description = "清除所有导出数据",priority = 7)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	@Test(description="导出认证汇总数据",priority=8)
	public void testGetSelfArchivesListCode() {
		int code = StaffArchivesBusiness.getQualificationArchivesListCode();
		Assert.assertEquals(code, 200,"导出认证汇总数据:"+code);
	}
	@Test(description="导出认证明细数据",priority=9)
	public void testGetQualificationArchivesInfoCode() {
		int code = StaffArchivesBusiness.getQualificationArchivesInfoCode();
		Assert.assertEquals(code, 200,"导出认证明细数据:"+code);
	}
	@Test(description = "查看导出的结果",priority = 10)
	public void exportRecordList() {
		String res = PaperExportBusiness.exportRecordList();
		String status = (String)JSONPath.read(res, "$.list[0].status");
		String status_1 = (String)JSONPath.read(res, "$.list[1].status");
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);	
		Assert.assertFalse(status_1=="FAILED", "查看导出的结果:"+res);	
	}
	
}
