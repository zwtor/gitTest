package archives.cases;

import cn.kxy.archives.business.StaffArchivesBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.DateUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestStaffarchives {

	String user_name = UserBusiness.getUsername();
	@Test(description="查询员工档案",priority=1)
	public void testGetUserTrainArchivesList() {
		String res = StaffArchivesBusiness.getUserTrainArchivesList(user_name, DateUtil.getRegularDateForYYDDMM(-30),  DateUtil.getRegularDateForYYDDMM(-1), "1","incumbency");
		String name = (String)JSONPath.read(res, "$.list[0].name");
		System.out.println("this is a Staffarchives module");
		Assert.assertEquals(name, user_name,"查询员工档案，实际返回结果："+res);
	}
	@Test(description="根据时间查询员工档案列表",priority=2)
	public void testGetUserTrainArchivesListByTime() {
		String res = StaffArchivesBusiness.getUserTrainArchivesList("", DateUtil.getRegularDateForYYDDMM(-30),  DateUtil.getRegularDateForYYDDMM(-1), "","");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>0, "根据时间查询员工档案列表,个数进行校验"+res);
	}
	@Test(description="根据人员名称查看员工档案列表",priority=3)
	public void testgetUserTrainArchivesListByUsername() {
		String res = StaffArchivesBusiness.getUserTrainArchivesList("","",  "", "1","");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>0, "根据人员名称查看员工档案列表,个数进行校验"+res);
	}
	@Test(description="根据在职状态查看员工档案列表",priority=4)
	public void testgetUserTrainArchivesListBydimission_incumbency() {
		String res = StaffArchivesBusiness.getUserTrainArchivesList("", "",  "", "","incumbency");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>0, "根据在职状态查询员工档案列表,个数进行校验"+res);

	}
	@Test(description="根据离职状态查看员工档案列表",priority=5)
	public void testGetUserTrainArchivesListBydimission_quit() {
		String res = StaffArchivesBusiness.getUserTrainArchivesList("", "",  "", "","quit");
		int total = (int)JSONPath.read(res, "$.total");
		Assert.assertTrue(total>=0, "根据离职状态查询员工档案列表,个数进行校验"+res);
	}
	@Test(description="查看员工档案全部状态列表",priority=6)
	public void testGetUserTrainArchivesDetailAllList() {
		String  res = StaffArchivesBusiness.getUserTrainArchivesDetailList("", "","", "", "0");
		String msg = (String)JSONPath.read(res, "$.msg");
//		Long userTrainBeginTimeDate = (Long)JSONPath.read(res, "$.data.detailList.list[0].userTrainBeginTimeDate");
//		Long userTrainEndTimeDate = (Long)JSONPath.read(res, "$.data.detailList.list[0].userTrainEndTimeDate");
//		Assert.assertNotNull(userTrainBeginTimeDate,"开始时间校验"+res);
//		Assert.assertNotNull(userTrainEndTimeDate,"结束时间校验"+res);
		Assert.assertEquals(msg, "查询成功","查看员工档案全部状态列表"+res);
	}
	@Test(description="查看员工档案的新员工列表",priority=7)
	public void testGetUserTrainArchivesDetailNewList() {
		String  res = StaffArchivesBusiness.getUserTrainArchivesDetailList("", "",  "", "", "1");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查看员工档案的新员工列表"+res);
	}
	@Test(description="查看员工档案学习列表",priority=8)
	public void testGetUserTrainArchivesDetailStudyList() {
		String  res = StaffArchivesBusiness.getUserTrainArchivesDetailList("", "",  "", "", "2");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查看员工档案学习列表"+res);
	}
	@Test(description="查看员工档案选修任务列表",priority=9)
	public void testGetUserTrainArchivesDetailList() {
		String  res = StaffArchivesBusiness.getUserTrainArchivesDetailList("", "",  "", "", "3");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查看员工档案选修任务列表"+res);
	}
	@Test(description="查看员工档案的考试列表",priority=10)
	public void testGetUserTrainArchivesDetailExamList() {
		String  res = StaffArchivesBusiness.getUserTrainArchivesDetailList("", "",  "", "", "4");
		String msg = (String)JSONPath.read(res, "$.msg");
		Assert.assertEquals(msg, "查询成功","查看员工档案的考试列表"+res);
	}
	
	@Test(description = "清除所有导出数据",priority = 11)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	
	@Test(description="导出在职员工任务档案的任务汇总数据",priority=12)
	public void testGetTaskExportUserTrainCode() {
		int code = StaffArchivesBusiness.getTaskExportUserTrainCode("incumbency");
		Assert.assertEquals(code, 200,"导出在职员工任务档案的任务汇总数据："+code);
	}
	
	
	@Test(description="导出离职员工任务档案的任务汇总数据",priority=13)
	public void testGetTaskExportUserTrainquitCode() {
		int code = StaffArchivesBusiness.getTaskExportUserTrainCode("quit");
		Assert.assertEquals(code, 200,"导出任务档案的任务汇总数据:"+code);
	}
	@Test(description="导出在职员工任务档案明细",priority=14)
	public void testGetTaskDetailExportCode() {
		int code = StaffArchivesBusiness.getTaskDetailExportCode("incumbency");
		Assert.assertEquals(code, 200,"导出在职员工任务档案明细"+code);
	}
	
	@Test(description="导出在职员工任务档案明细",priority=15)
	public void testGetTaskDetailExportquitCode() {
		int code = StaffArchivesBusiness.getTaskDetailExportCode("quit");
		Assert.assertEquals(code, 200,"导出在职员工任务档案明细"+code);
	}
	
	@Test(description = "导出在职员工任务档案的任务明细",priority = 16)
	public void exportUserTrainArchivesDetailList() {
		int code = StaffArchivesBusiness.exportUserTrainArchivesDetailList("incumbency",UserBusiness.getUserId());
		Assert.assertEquals(code, 200,"导出任务档案的任务明细:"+code);
		
	}
	@Test(description = "导出员工数据后，在下载中心查看",priority  = 17)
	public void exportRecordList() {
		String res = PaperExportBusiness.exportRecordList();
		String status = (String)JSONPath.read(res, "$.list[0].status");
		String status_1 = (String)JSONPath.read(res, "$.list[1].status");
		String status_2 = (String)JSONPath.read(res, "$.list[4].status");
		String status_3 = (String)JSONPath.read(res, "$.list[3].status");
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_1=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_3=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_2=="FAILED", "查看导出的结果:"+res);
	}
	
}
