package lecture.cases;

import cn.kxy.base.business.BaseBusiness;
import cn.kxy.examination.business.PaperExportBusiness;
import cn.kxy.lecturer.business.LecturerListBusiness;
import cn.kxy.setting.bussiness.UserBusiness;
import cn.kxy.study.business.StudyTaskBusiness;
import com.alibaba.fastjson.JSONPath;
import com.lazy.common.utils.CommonData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLecturerOfflineSign {
String outer_name = "RYIHJL" + CommonData.getStringRandom(5);
	
	@Test(description="新增外部讲师",priority=1)
	public void testAddOutsideLecturer() {
		String res = LecturerListBusiness.addLecturer(outer_name,"1", "13526231231","outside" ,"", BaseBusiness.lecturerLevel, "auto", "jmeter");
		String id02 =(String)JSONPath.read(res, "$.id");
		Assert.assertTrue(!id02.isEmpty(), "新增外部讲师实际返回结果："+res);
	}
	String id = "";
	@Test(description = "获取讲师id",priority = 2)
	public void getLecturerId() {
		id = LecturerListBusiness.getIdByKeyword(outer_name);
	}
	
	String study_name = "Offline Lecturer Task" + CommonData.getStringRandom(7);
	String study_id = "";
	String lagId = "";
	String user_name = UserBusiness.getUsername();
	@Test(description="添加线下学习任务",priority=3)
	public void testAddOfflineTypeStudyTask() {
//		String res = StudyTaskBusiness.addOfflineTypeStudyTask(study_name, DateUtil.getTimeStamp(0, ""), DateUtil.getTimeStamp(3, ""),
//				DateUtil.getRegularDateForHHMM(1), DateUtil.getRegularDateForHHMM(2), id, DateUtil.getTimeStamp(1, ""));
//		String msg= (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "新增计划成功！","添加线下学习任务，实际返回结果:"+res);
	}
	
	@Test(description = "查看讲师的线下签到数据",priority = 4)
	public void testQueryLecturerLatojas() {
//		String res = LecturerListBusiness.queryLecturerLatojas(id, "off");
//		String address = (String)JSONPath.read(res, "$.list[0].address");
//		String title = (String)JSONPath.read(res, "$.list[0].title");
//		int should_count = JsonPath.read(res, "$.list[0].should_count");
//		Assert.assertNotNull(address, "查看讲师的线下签到数据，实际返回结果:"+res);
//		Assert.assertNotNull(title, "查看讲师的线下签到数据，实际返回结果:"+res);
//		Assert.assertNotNull(should_count, "查看讲师的线下签到数据，实际返回结果:"+res);
	}
	@Test(description = "清除所有导出数据",priority = 5)
	public void deleteAllRecord() {
		String res = PaperExportBusiness.deleteAllRecord();
		Assert.assertTrue(res.contains("OK"),"清除所有导出数据,实际返回结果："+res);
	}
	@Test(description = "导出讲师的线下签到数据",priority = 6)
	public void testExportLatojas() {
		String res = LecturerListBusiness.exportLatojas(id,"");
		Assert.assertTrue(res.contains("导出列表成功"),"导出讲师的线下签到数据"+res);
	}
	
	@Test(description = "通过关键字导出讲师的线下签到数据",priority = 7)
	public void testExportLatojasByKeyword() {
		String res = LecturerListBusiness.exportLatojas(id,"off");
		Assert.assertTrue(res.contains("导出列表成功"),"通过关键字导出讲师的线下签到数据"+res);
	}
	
	@Test(description = "导出讲师的线下签到数据，在下载中心查看",priority  = 8)
	public void exportRecordList() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String res = PaperExportBusiness.exportRecordList();
		String status = (String)JSONPath.read(res, "$.list[0].status");
		String status_1 = (String)JSONPath.read(res, "$.list[1].status");
		Assert.assertFalse(status=="FAILED", "查看导出的结果:"+res);
		Assert.assertFalse(status_1=="FAILED", "查看导出的结果:"+res);
	}
	
	@Test(description="获取学习任务的id",priority = 9)
	public void testGetStudyTaskList() {
		String res = StudyTaskBusiness.getStudyTaskList(study_name, "true", "2", "");
		study_id= (String)JSONPath.read(res, "$.list[0].id");
	}
	
	@Test(description = "不删除学习任务时删除讲师",priority = 10)
	public void deleteLecturerByKeyword() {
//		String res = LecturerListBusiness.deleteLecturerByKeyword(outer_name);
//		String msg = (String)JSONPath.read(res, "$.message");
//		Assert.assertEquals(msg, "lecturer relation plan, can not delete","不删除学习任务时删除讲师，实际返回结果："+res);
	}
	

	
	@Test(description = "删除线下学习任务的id",priority = 11)
	public void deleteStudyTask () {
//		String res = StudyTaskBusiness.deleteStudyTask(study_id);
//		String msg= (String)JSONPath.read(res, "$.msg");
//		Assert.assertEquals(msg, "删除学习计划成功","删除线下学习任务的id，实际返回结果:"+res);
	}
	
	@Test(description = "删除讲师",priority = 12)
	public void deleteLecturer() {
		String res = LecturerListBusiness.deleteLecturerByKeyword(outer_name);
		String msg = (String)JSONPath.read(res, "$.deleted");
		Assert.assertEquals(msg, "true","删除讲师实际返回结果："+res);
	}
	
}
