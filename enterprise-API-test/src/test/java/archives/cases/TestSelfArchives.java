package archives.cases;

import cn.kxy.archives.business.StaffArchivesBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSelfArchives {

	String user_name = UserBusiness.getUsername();
	
	@Test(description="查看在职员工的自学档案列表",priority=1)
	public void testQuerySelfStudyIncumbencyList() {
		String res = StaffArchivesBusiness.querySelfStudyList("", "1", "incumbency");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0,"查看在职员工的自学档案列表,实际返回结果："+res);
	}
	@Test(description="查看离职员工的自学档案列表",priority=2)
	public void testQuerySelfStudyquitList() {
		String res = StaffArchivesBusiness.querySelfStudyList("", "1", "quit");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(Integer.valueOf(total)>=0,"查看离职员工的自学档案列表,实际返回结果："+res);
	}
	@Test(description="查看离职员工的自学档案列表",priority=3)
	public void testQuerySelfStudyList() {
		String res = StaffArchivesBusiness.querySelfStudyList(user_name, "", "incumbency");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(Integer.valueOf(total)>=0,"查看离职员工的自学档案列表,实际返回结果："+res);
	}
	@Test(description="查看自学档案的全部列表",priority=4)
	public void testQuerySelfStudyAllInfo() {
		String res = StaffArchivesBusiness.querySelfStudyInfo("incumbency", "");
		int total = (int)JSONPath.read(res, "$.user.finished_count");
		Assert.assertTrue(Integer.valueOf(total)>=0,"查看自学档案的全部列表"+res);
	}
	@Test(description="查看自学档案的学习中列表",priority=5)
	public void testQuerySelfStudyingInfo() {
		String res = StaffArchivesBusiness.querySelfStudyInfo("incumbency", "studying");
		int total = (int)JSONPath.read(res, "$.user.finished_count");
		Assert.assertNotNull(total,"查看自学档案的学习中列表"+res);
	}
	@Test(description="查看自学档案的已完成列表",priority=6)
	public void testQuerySelfStudyFinishedInfo() {
		String res = StaffArchivesBusiness.querySelfStudyInfo("incumbency", "finished");
		int total = (int)JSONPath.read(res, "$.user.finished_count");
		Assert.assertNotNull(total,"查看自学档案的已完成列表"+res);
	}
	
	@Test(description = "清除所有导出数据",priority = 7)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	@Test(description="导出在职员工的自学汇总数据",priority= 8 )
	public void testGetSelfArchivesListCode() {
		int code = StaffArchivesBusiness.getSelfArchivesListCode("incumbency");
		Assert.assertEquals(code, 200,"导出在职员工的自学汇总数据:"+code);
	}
	
	@Test(description="导出离职员工自学汇总数据",priority= 9)
	public void testGetSelfArchivesListQuitCode() {
		int code = StaffArchivesBusiness.getSelfArchivesListCode("quit");
		Assert.assertEquals(code, 200,"导出离职员工自学汇总数据:"+code);
	}
	
	@Test(description="导出在职员工自学明细数据",priority= 10)
	public void testGetSelfArchivesInfoCode() {
		int code = StaffArchivesBusiness.getSelfArchivesInfoCode("incumbency");
		Assert.assertEquals(code, 200,"导出在职员工自学明细数据:"+code);
	}
	
	@Test(description="导出离职员工自学明细数据",priority= 11)
	public void testGetSelfArchivesInfoQuitCode() {
		int code = StaffArchivesBusiness.getSelfArchivesInfoCode("quit");
		Assert.assertEquals(code, 200,"导出离职员工自学明细数据:"+code);
	}
	
	@Test(description = "导出自学数据后，在下载中心查看",priority  = 12)
	public void exportRecordList() {
		String res = PaperExportBusiness.exportRecordList();
		
		String status = (String)JSONPath.read(res, "$.list[0].status");
		String status_1 = (String)JSONPath.read(res, "$.list[1].status");
		String status_2 = (String)JSONPath.read(res, "$.list[2].status");
		
		String status_3 = (String)JSONPath.read(res, "$.list[3].status");
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_1=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_3=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_2=="FAILED", "查看导出的结果:"+res);
	}
}
