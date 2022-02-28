package study.cases;

import cn.kxy.setting.bussiness.UserBusiness;
import com.lazy.common.utils.CommonData;
import org.testng.annotations.Test;

public class TestSignManage {

	String lagId = "";
	String user_name = UserBusiness.getUsername();
	String lec_name = "TUHBB"+CommonData.getStringRandom(5);
	@Test(description="新增外部讲师",priority=1)
	public void testAddOutsideLecturer() {
//		String res = LecturerListBusiness.addLecturer(lec_name,"1", "13526231231","outside" ,"", BaseBusiness.outlecturerLevel, "", "jmeter");
//		String id02 =(String)JSONPath.read(res, "$.id");
//		Assert.assertTrue(!id02.isEmpty(), "新增外部讲师实际返回结果："+res);
	}
	String lec_id = "";
	@Test(description = "获取讲师id",priority = 2)
	public void getLecturerId() {
//		lec_id = LecturerListBusiness.getIdByKeyword(lec_name);
//		LecturerListBusiness.updateStatus("1", lec_id);
	}
	
	String offline_name= "offline_study_name"+CommonData.getStringRandom(8);
	String offline_id= "";
	String xx_Id = "";
	String latojasId = "";
	@Test(description="新增正常签到的线下学习任务",priority=3)
	public void testAddOfflineXXStudyTask() {
		
//		String date = DateUtil.getOneHourLaterStamp();
//		
//		String date2 =DateUtil.getTimeStamp(2, "");
//		System.out.println(lec_id);
//		String addOfflineTypeStudyTask = StudyTaskBusiness.addOfflineTypeStudyTask(offline_name, DateUtil.getTimeStamp(0, ""), DateUtil.getTimeStamp(2, ""),
//				DateUtil.getDateTimeStamp(date), DateUtil.getDateTimeStamp(date2), lec_id, date, date2);
//		System.out.println(addOfflineTypeStudyTask);
//		String res = AppStudyBusiness.queryStudyList("unfinished");
//		offline_id=(String) JSONPath.read(res, "$.list[0].id");
	
	}
	
	@Test(description = "在未结束列表查看签到列表",priority = 4)
	public void queryUnfinishedMylatojas() {
//		String res = MyBusiness.queryMylatojas("", "unfinished");
//		String xx_title = (String)JSONPath.read(res, "$.list[0].title");
//		Assert.assertNotNull(xx_title,"在未结束列表查看签到列表,实际返回结果"+res);
	}
	

	
	@Test(description = "获取线下签到的信息",priority = 5)
	public void queryInfo() {
//		AppStudyBusiness.loadResource(offline_id);
//		String info_res = AppStudyBusiness.queryInfo(offline_id);
//		xx_Id = (String) JSONPath.read(info_res, "$.stages[0].course_list[0].id");
//		String LatojasInfo_res = AppStudyBusiness.queryLatojasInfo(offline_id, xx_Id);
//		latojasId = (String) JSONPath.read(LatojasInfo_res, "$.sign_in_list[0].id");
	}
	@Test(description = "查看pc端签到管理的详情",priority = 6)
	public void queryLaInfo() {
//		String res = AppStudyBusiness.queryLaInfo(offline_id,xx_Id,"");
//		Assert.assertTrue(res.contains("查询成功"),"查看pc端签到管理的详情，实际返回结果："+res);
	}
	@Test(description = "APP端在签到时间2小时之内签到", priority = 7)
	public void testOfflineSignIn() {
//		String res = AppStudyBusiness.offlineSignIn(latojasId);
//		String status = (String) JSONPath.read(res, "$.status");
//		Assert.assertEquals(status, "signed", "APP端在签到时间2小时之内签到" + res);
	}
	

	@Test(description = "签到后在未结束列表查看签到列表",priority =8)
	public void queryUnFinishedMylatojas() {
//		String res = MyBusiness.queryMylatojas("线下签到的名称", "unfinished");
//		String xx_title = (String)JSONPath.read(res, "$.list[0].title");
//		int actual_count = (int)JSONPath.read(res, "$.list[0].actual_count");
//		Assert.assertNotNull(xx_title,"签到后在未结束列表查看签到列表,实际返回结果"+res);
//		Assert.assertTrue(actual_count>=0,"签到后在未结束列表查看签到列表,实际返回结果"+res);
	}
	
	@Test(description = "签到后在未结束列表查看签到列表",priority =9)
	public void queryFinishedMylatojas() {
//		String res = MyBusiness.queryMylatojas("线下签到的名称", "finished");
//		Assert.assertTrue(res.contains(""),"签到后在未结束列表查看签到列表,实际返回结果"+res);
	}
	
	@Test(description = "删除任务",priority = 10)
	public void deleteStudyTask () {
//		StudyTaskBusiness.deleteStudyTask(offline_id);
	}
	@Test(description = "删除讲师",priority = 11)
	public void deleteLecturer() {
//		LecturerListBusiness.deleteLecturerByKeyword(lec_name);
	}
	
}
